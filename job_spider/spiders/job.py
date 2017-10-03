# -*- coding: utf-8 -*-
import scrapy
from job_spider.items import JobItem
from job_spider.items import JobDetail
from scrapy import  Request

import time
import hashlib
import random
class JobSpider(scrapy.Spider):
    name = "job"
    def __init__(self, is_crawl_all=False, *args, **kwargs):        
        super(JobSpider, self).__init__(*args, **kwargs)
        self.start_urls = ['https://xiaoyuan.zhaopin.com/full/0/0_0_210500,160400,160000,160500,160200,300100,160100,160600_0_0_-1_0_1_1',
                'https://xiaoyuan.zhaopin.com/full/0/0_0_121000,129900,121100,121200,201200,120700,300000_0_0_-1_0_1_1',
                'https://xiaoyuan.zhaopin.com/full/0/0_0_200300,200302,201400,201300,300200,300300_0_0_-1_0_1_1',
                'https://xiaoyuan.zhaopin.com/full/0/0_0_120400,120200,170500,170000_0_0_-1_0_1_1',
                'https://xiaoyuan.zhaopin.com/full/0/0_0_140000,140100,140200_0_0_-1_0_1_1',
                'https://xiaoyuan.zhaopin.com/full/0/0_0_180000,180100,150000_0_0_-1_0_1_1']        
        self.is_crawl_all=is_crawl_all#外部传入参数，是否爬取整个站点        
#    allowed_domains = ["zhaopin.com"]\

    
    def parse(self, response):
#        db=mydb()
        for  detailInfo in  response.xpath("//div[@class='searchResultItemDetailed']"):
            if not self.is_crawl_all:
                try:
                    post_hour=int(detailInfo.css('p.searchResultJobAdrNum span em::text').extract()[1].split('小时前')[0])
    #                self.logger.error(post_hour)
    #                self.log(post_hour,level=self.logging.ERROR)
                    if time.localtime().tm_hour-post_hour<0:
                        continue
                except Exception as e:                
                    continue            
            Job=JobItem()        
            Job['searchResultJobName']=detailInfo.css('p.searchResultJobName a::text').extract_first()
            Job['searchResultJobType']=detailInfo.css('p.searchResultJobName span::text').extract_first()
            Job['searchResultCompanyname']=detailInfo.css('p.searchResultCompanyname span::text').extract_first()
            Job['searchResultJobAdrNum']=detailInfo.css('p.searchResultJobAdrNum span em.searchResultJobCityval::text').extract_first()
            Job['searchResultJobdescription']=detailInfo.css('p.searchResultJobdescription span::text').extract_first()
            Job['searchResultUrl']=detailInfo.css('p.searchResultJobName a::attr(href)').extract_first()
            Job['crawl_timestamp']=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            id_str=Job['searchResultJobName']+Job['searchResultCompanyname']+Job['searchResultJobAdrNum']+Job['crawl_timestamp']+str(random.randint(1,1000))
            Job['parmaryId']=hashlib.md5(id_str.encode('utf-8')).hexdigest()
#            yield Job
#            sql='insert into main_info(id,searchResultJobName,searchResultJobType,searchResultCompanyname,searchResultJobAdrNum,searchResultJobdescription,searchResultUrl,crawl_timestamp)  values(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')'%(Job['parmaryId'],Job['searchResultJobName'],Job['searchResultJobType'],Job['searchResultCompanyname'],Job['searchResultJobAdrNum'],Job['searchResultJobdescription'],Job['searchResultUrl'],Job['crawl_timestamp'])
##            self.log(sql)
#            db.insert(sql)
            yield Job
            info_request=Request('https://'+Job['searchResultUrl'],callback=self.get_detail_info)
#            info_request.meta['db']=db
            info_request.meta['parmaryId']=Job['parmaryId']
            yield info_request
#            yield Request('https://'+Job['searchResultUrl'],callback=lambda response ,mainInfoId=Job['parmaryId'],db:self.get_detail_info(response,Job['parmaryId'],db))
            
        count=0
        
        for page_i in response.xpath("//ul[@id='page']/li/a/span/text()"):            
            if '下一页'  in page_i.extract():
                break
            else:
                count+=1
        next_page=response.urljoin(response.xpath("//ul[@id='page']/li/a/@href")[count].extract())
        yield Request(next_page)
#        db.db_close()
    def get_detail_info(self,response):
#        db=response.meta['db']
        mainInfoId=response.meta['parmaryId']
        jd=JobDetail()
        pre_title=''
        pre_title2=''
        for  i in response.xpath("//ul[@class='cJobDetailInforTopWrap clearfix c3']/li/text()"):
            if '公司行业' in i.extract():
                pre_title='companyIndustry'
                continue
            elif '公司规模' in  i.extract(): 
                pre_title='companyScale'
                continue
            elif '公司类型' in  i.extract(): 
                pre_title='companyType'
                continue
            if len(pre_title)>0:
                jd[pre_title]=i.extract().strip()
        for  i  in  response.xpath("//ul[@class='cJobDetailInforBotWrap clearfix c3']/li/text()"):
            if '工作地点' in i.extract():
                pre_title2='currentJobCity'
                continue
            elif '职位类别' in  i.extract(): 
                pre_title2='positionType'
                continue
            elif '招聘人数' in  i.extract(): 
                pre_title2='numberOfDemand'
                continue
            elif '发布时间' in  i.extract(): 
                pre_title2='postDatetime'
                continue   
            if len(pre_title2)>0:            
                jd[pre_title2]=i.extract().strip()       
        
        jd['positionDescription']= response.xpath("//p[@class='mt20']/text()").extract_first()#发布时间
        jd['mainInfoId']=mainInfoId
        field_d=['companyIndustry','companyScale','companyType','currentJobCity','positionType','numberOfDemand','postDatetime','positionDescription']
        for i in field_d:
            if i  not in jd or jd[i]==None:
                jd[i]=''
        yield jd
#        try:
#            sql='insert into detail(mainInfoId,companyIndustry,companyScale,companyType,currentJobCity,positionType,numberOfDemand,postDatetime,positionDescription)  values(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')'%(jd['mainInfoId'],jd['companyIndustry'],jd['companyScale'],jd['companyType'],jd['currentJobCity'],jd['positionType'],jd['numberOfDemand'],jd['postDatetime'],jd['positionDescription'])                
#            db.insert(sql)
#        except Exception as e:
#            self.log(jd)            
#            self.log(e)
#            self.log(sql)
