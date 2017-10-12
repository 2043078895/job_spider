# -*- coding: utf-8 -*-
import scrapy
import time
import hashlib
import datetime
from scrapy import Request
from job_spider.items import JobDetail,JobItem
import os
class JobSpider(scrapy.Spider):
    name = 'job_ganji'
#    allowed_domains = ['job_58']
    start_urls=[]
    count=0

    with open('city_urls.txt','r',encoding='utf-8') as fp:        
        for i in fp.readlines():
            if count==5:
                break
            if len(i)>1 and i.startswith('http://'):
                start_urls.append(i.strip()+'zhaopin/')
                count+=1
    

#    def parse(self, response):
#        with open('city_urls.txt','w') as fp:
#            for city_url in  response.xpath("//div[@class='all-city']/dl/dd/a/@href").extract():            
#                fp.write(city_url+'\r\n')
#                yield  Request(city_url,callback=self.enter_job_calss)
            
    def parse(self,response):
         class_urls=response.xpath("//div[@class='f-all-news']/dl/dd/i/a/@href").extract()     
         class_urls=[ response.urljoin(i)  for  i in class_urls if 'http' not in i]
         for class_url in class_urls:             
             yield Request(class_url,callback=self.get_main_url)
    def get_main_url(self,response):
        next_url=response.xpath("//a[@class='next']/@href").extract_first()
        if next_url is not None and len(next_url)>0 :
            next_page=response.urljoin(next_url)
            yield Request(next_page,callback=self.get_main_url)
        mian_info_Item=JobItem()
        for dt in response.xpath("//div[@class='new-dl-wrapper']/div/dl/dt"):
            mian_info_Item['searchResultJobName']=dt.xpath("//a[@class='list_title gj_tongji']/text()").extract_first()
            mian_info_Item['searchResultJobType']='-1'
            mian_info_Item['searchResultUrl']=dt.xpath("//a[@class='list_title gj_tongji']/@href").extract_first()
            mian_info_Item['searchResultCompanyname']= dt.xpath("//div[@class='new-dl-company']/a/@title").extract_first()
            mian_info_Item['searchResultJobAdrNum']=dt.xpath("//dd[@class='pay']/text()").extract_first()            
            mian_info_Item['searchResultJobdescription']='-1'
            mian_info_Item['crawl_timestamp']=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
            id_str=mian_info_Item['searchResultJobName']+mian_info_Item['searchResultCompanyname']+mian_info_Item['searchResultJobAdrNum']+mian_info_Item['searchResultUrl']
            mian_info_Item['parmaryId']=hashlib.md5(id_str.encode('utf-8')).hexdigest()
            mian_info_Item['jobSiteName']='赶集网'
            detail_request=Request(mian_info_Item['searchResultUrl'],callback=self.get_detail_info)
            detail_request.meta['mainInfoId']=mian_info_Item['parmaryId']
            yield detail_request 
            yield mian_info_Item
    def get_detail_info(self,response):
        job_detail=JobDetail()
        job_detail['mainInfoId']=response.meta['mainInfoId']#外键
        job_detail['companyIndustry']=response.xpath("//div[@class='detail-r-companyInfo']//a[contains(@gjalog,'companyindustry')]/text()").extract_first()
        job_detail['companyType']=response.xpath("//div[@class='detail-r-companyInfo']//a[contains(@gjalog,'companynature')]/text()").extract_first()
        job_detail['positionType']=response.xpath("//a[contains(@gjalog,'jobname')]/text()").extract_first()                       
        job_detail['currentJobCity']=response.xpath("//a[contains(@gjalog,'workplace')]/text()").extract_first()                               
        for  i in response.xpath("//div[@class='detail-r-companyInfo']/div"):
            if '公司规模' in i.xpath('text()').extract():
                job_detail['companyScale']=i.xpath('//span/text()').extract_first()
        for  i  in  response.xpath("//ul[@class='clearfix pos-relat']/li"):
            if '招聘人数' in  i.css('::text').extract_first(): 
                job_detail['numberOfDemand']=i.css('em::text').extract_first()
        job_detail['salary']=response.xpath("//ul[@class='clearfix pos-relat']/li/em[@class='salary']/text()").extract_first()        
        for i in  response.xpath("//p[@class='data-sty mb-5']/span/text()").extract():

            if  '更新时间' in i : 
                try:
                    date_str=i.split('：')[1]                    
                    job_detail['postDatetime']=self.map_date_time(date_str)
                except Exception as e :
                    print('时间错误：：：：：：：：'+str(e)+'::::'+date_str)
                    job_detail['postDatetime']='-1'
        job_detail['positionDescription']=''.join(response.xpath("//div[@class='js-tab-show d-l-account fc4b']/*/text()").extract()).strip()
        field_d=['companyIndustry','companyScale','companyType','currentJobCity','positionType','numberOfDemand','postDatetime','positionDescription']
        for i in field_d:
            if i  not in job_detail or job_detail[i]==None:
                job_detail[i]=''
        print(job_detail)
        yield job_detail
    def map_date_time(self,date_str):
        return_value=time.strftime('%Y-%m-%d',time.localtime(time.time()))
        if '今天' in date_str:
            return_value=time.strftime('%Y-%m-%d',time.localtime(time.time()))
        elif '昨天' in date_str:
            now = datetime.datetime.now()
            delta = datetime.timedelta(days=-1)
            return_value=(now+delta).strftime('%Y-%m-%d')
        elif '前天' in  date_str:
            now = datetime.datetime.now()
            delta = datetime.timedelta(days=-2)
            return_value=(now+delta).strftime('%Y-%m-%d')
        else:
            return_value=time.strftime('%Y',time.localtime(time.time()))+'-'+date_str
        return return_value

#            mian_info_Item['title']=dt.xpath("//a[@class='list_title gj_tongji']/text()").extract_first()
#            mian_info_Item['title']=dt.xpath("//a[@class='list_title gj_tongji']/text()").extract_first()
#            mian_info_Item['title']=dt.xpath("//a[@class='list_title gj_tongji']/text()").extract_first()
            
            

             
