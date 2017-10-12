# -*- coding: utf-8 -*-
import scrapy
from job_spider.items import JobItem
from job_spider.items import JobDetail
from scrapy import  Request

import time
import hashlib
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
#    allowed_domains = ["zhaopin.com"]

    
    def parse(self, response):                
        count=0
#        time.sleep(10)
        for page_i in response.xpath("//ul[@id='page']/li/a/span/text()"):            
            if '下一页'  in page_i.extract():
                break
            else:
                count+=1
        next_page=response.urljoin(response.xpath("//ul[@id='page']/li/a/@href")[count].extract())
        yield Request(next_page,callback=self.parse)        
        exit_count=0
        for  detailInfo in  response.xpath("//div[@class='searchResultItemDetailed']"):
            if exit_count>3:
                return 
            if not self.is_crawl_all:
                try:
                    post_hour=int(detailInfo.css('p.searchResultJobAdrNum span em::text').extract()[1].split('小时前')[0])
                    if time.localtime().tm_hour-post_hour<0:
                        exit_count+=1
                        continue                        
                except Exception as e:                
                    exit_count+=1
                    continue            
            Job=JobItem()        
            Job['searchResultJobName']=detailInfo.css('p.searchResultJobName a::text').extract_first()
            Job['searchResultJobType']=detailInfo.css('p.searchResultJobName span::text').extract_first()
            Job['searchResultCompanyname']=detailInfo.css('p.searchResultCompanyname span::text').extract_first()
            Job['searchResultJobAdrNum']=detailInfo.css('p.searchResultJobAdrNum span em.searchResultJobCityval::text').extract_first()
            Job['searchResultJobdescription']=detailInfo.css('p.searchResultJobdescription span::text').extract_first()
            Job['searchResultUrl']=detailInfo.css('p.searchResultJobName a::attr(href)').extract_first()
            Job['crawl_timestamp']=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            id_str=Job['searchResultJobName']+Job['searchResultCompanyname']+Job['searchResultJobAdrNum']+Job['searchResultUrl']
            Job['parmaryId']=hashlib.md5(id_str.encode('utf-8')).hexdigest()
            Job['jobSiteName']='智联'
            yield Job        
            info_request=Request('https://'+Job['searchResultUrl'],callback=self.get_detail_info)            
            info_request.meta['parmaryId']=Job['parmaryId']
            yield info_request

    def get_detail_info(self,response):
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
        jd['positionDescription']=''.join(response.xpath("//div[@class='j_cJob_Detail']//*[not(@class='clearfix cJob_Delivery mt24')]/text()").extract()).replace('立即投递','').replace('告诉小伙伴','').replace('举报','').strip()#信息细节
        jd['mainInfoId']=mainInfoId
        jd['salary']=-1#智联网站没有薪水信息        
        field_d=['companyIndustry','companyScale','companyType','currentJobCity','positionType','numberOfDemand','postDatetime','positionDescription']
        for i in field_d:
            if i  not in jd or jd[i]==None:
                jd[i]=''
        yield jd
