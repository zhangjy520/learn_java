###### ---
title: node js 爬虫
date: 2017-05-05 13:51:49
tags: node js 
---

# 需求

  ```
    数据中心，使用 active mq，需要爬取队列列表信息到项目中
  ```
  
# 实现思路
```
使用node js爬虫实现
```


# 实现方式
```
 资源文件在：learn_java/tree/master/node_pa_chong
 
 1 安装node js
 wget http://nodejs.org/dist/v0.8.16/node-v0.8.16.tar.gz 
 
 tar zxvf node-v0.8.16.tar.gz
  
 ./configure 
  
 make && make install
    
 若需要升级nodejs版本
 
  npm install -g n
  n stable
  n 0.10.26 //或者可以选择升级到指定版本
  
  
  启动nodejs爬虫
  
  forever npm -g install forever
  
  forever start app.js //后台启动
  
  node app.js //控制台启动
  
```

 ## 主要的： app.js 代码如下：
 
 ```
var express = require('express');
var app = express();
var request = require('request');
var parseString = require('xml2js').parseString;
var jmsAdress = 'http://jira.lexisoft.cc:82/browse/KP-629';
// var initAdress = 'http://114.215.29.139:10090/gkopen/rpc/init?tocken=2012335444456871128213845&&id='

/
app.get('/queue/json', function(req,res){
   //这里的headers,针对需要爬区的页面不同，相当于账号信息等验证内容，右键浏览器审查元素可以得到，并不是每个网页都能爬区
    request.get(jmsAdress,{headers:{'Authorization':'Basic YWRtaW46YWRtaW4='}},function(error,response,body){
      if (error) {
      return console.error('upload failed:', error);
    }

    console.log('Upload successful!  Server responded with:', body);
    parseString(body,function(error,data){
      if (!error){
      
      //这里对爬取到的页面进行处理字符串过滤提取
        var _data = JSON.stringify(data)
        var __data = _data.replace(/\[/g,'');
        var ___data = __data.replace(/\]/g,'');
        var ____data = ___data.replace('{"queues":{"queue":','[');
        var _____data = ____data.replace('}}}}','}}]');
        var data = JSON.parse(_____data);
       res.jsonp(data);
     }
    })
    })
});


var server = app.listen(11060, function () {
 console.log("启动成功 11060");
})
    
```