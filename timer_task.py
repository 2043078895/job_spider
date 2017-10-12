# -*- coding: utf-8 -*-
"""
Created on Thu Oct  5 22:28:14 2017

@author: Administrator
"""
import sys
sys.path.append(r'../data_process_script') 
import threading
import os
import random
import time
from ZhiLianHanddler import  ZhiLianHanddler

def start_crawl(): 
    print('''Start with today's task!''')  
    print('---------------------------------------------------')
    print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime()))
    print('---------------------------------------------------')
    os.system('scrapy crawl job')
    zhiLianHanddler=ZhiLianHanddler()
    zhiLianHanddler.db_repeat_data_clean()
    s=threading.Timer(24*60*60-random.randint(-3600,3600)+60*(23-time.localtime().tm_hour),start_crawl)
    s.start()   
    print('''end with today's task!''')  
    print('---------------------------------------------------')
    print(time.strftime('%Y-%m-%d %H:%M:%S',time.localtime()))
    print('---------------------------------------------------')
    
           
if __name__=='__main__':
    print('start timer!')
    start_crawl()