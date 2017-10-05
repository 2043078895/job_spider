# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from scrapy import  Item,Field


class JobItem(Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    parmaryId=Field()#主键
    searchResultJobName=Field()
    searchResultCompanyname=Field()
    searchResultJobAdrNum=Field()
    searchResultJobType=Field()
    searchResultJobdescription=Field()    
    searchResultUrl=Field()
    crawl_timestamp=Field()
    jobSiteName=Field()
class JobDetail(Item):
    mainInfoId=Field()#外键
    companyIndustry=Field()#公司行业
    companyScale=Field()#公司规模
    companyType=Field()#公司类型
    currentJobCity=Field()#所在城市
    positionType=Field()#职位类型
    numberOfDemand=Field()#招聘人数
    postDatetime=Field()#发布时间
    positionDescription=Field()#职位描述
    salary=Field()
#    crawl_timestamp=time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
