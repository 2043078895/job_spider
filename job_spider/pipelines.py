# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

from job_spider.items import JobItem
from job_spider.items import JobDetail
#from mydb import  mydb
from twisted.internet import  defer
from twisted.enterprise import  adbapi
class JobSpiderPipeline(object):
    def __init__(self):
        self.dbpool=adbapi.ConnectionPool('pymysql',"localhost","root","root","job",charset='utf8',use_unicode=True,connect_timeout=5)
#    @staticmethod
#    def parse_mysql_url(url):
#        dj_database_url.urlparse()
        
    @defer.inlineCallbacks
    def process_item(self, item, spider):
#        if isinstance(item,JobItem):
#            self.save_JobItem(item)
#        elif isinstance(item,JobDetail):
#            self.save_JobDetail(item)
        try:
            yield self.dbpool.runInteraction(self.do_replace,item)
        except Exception as e:
            print(e)
        defer.returnValue(item)
#        return item
        
    @staticmethod
    def do_replace(tx,item):
        if isinstance(item,JobItem):
            sql='''insert into main_info(id,searchResultJobName,searchResultJobType,searchResultCompanyname,searchResultJobAdrNum,searchResultJobdescription,searchResultUrl,crawl_timestamp)  values(%s,%s,%s,%s,%s,%s,%s,%s)'''
            args=(
            item['parmaryId'],
            item['searchResultJobName'],
            item['searchResultJobType'],
            item['searchResultCompanyname'],
            item['searchResultJobAdrNum'],
            item['searchResultJobdescription'],
            item['searchResultUrl'],
            item['crawl_timestamp']                    
            )            
        elif isinstance(item,JobDetail):
            sql='''insert into detail(mainInfoId,companyIndustry,companyScale,companyType,currentJobCity,positionType,numberOfDemand,postDatetime,positionDescription)  values(%s,%s,%s,%s,%s,%s,%s,%s,%s)'''
            args=(
              item['mainInfoId'],
                item['companyIndustry'],
                item['companyScale'],
                item['companyType'],
                item['currentJobCity'],
                item['positionType'],
                item['numberOfDemand'],
                item['postDatetime'],
                item['positionDescription']                                                 

              )              
        tx.execute(sql,args)
#    def open_spider(self, spider):
#        self.db = mydb()
    def save_JobItem(self,item):
        try:
            sql='insert into main_info(id,searchResultJobName,searchResultJobType,searchResultCompanyname,searchResultJobAdrNum,searchResultJobdescription,searchResultUrl,crawl_timestamp)  values(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')'%(item['parmaryId'],item['searchResultJobName'],item['searchResultJobType'],item['searchResultCompanyname'],item['searchResultJobAdrNum'],item['searchResultJobdescription'],item['searchResultUrl'],item['crawl_timestamp'])
            self.db.insert(sql)
        except Exception as e:
            self.log(item)            
            self.log(e)
    def save_JobDetail(self,item):        
        try:
            sql='insert into detail(mainInfoId,companyIndustry,companyScale,companyType,currentJobCity,positionType,numberOfDemand,postDatetime,positionDescription)  values(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')'%(item['mainInfoId'],item['companyIndustry'],item['companyScale'],item['companyType'],item['currentJobCity'],item['positionType'],item['numberOfDemand'],item['postDatetime'],item['positionDescription'])                
            self.db.insert(sql)
        except Exception as e:
            self.log(item)
            self.log(e)
    def close_spider(self, spider):
#        self.db.db_close()
        self.dbpool.close()
            