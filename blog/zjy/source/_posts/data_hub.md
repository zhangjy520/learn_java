---
title: data_hub 数据同步
date: 2017-04-25 13:51:49
tags: java
---

# data_hub 数据同步
	使用技术 ：active_mq,jms等 
	ps : 不对外开放，个人理解

# 生产者发送数据：

1.  获取需要推送的第三方应用，ref_platform_app    简述：给那个第三方应用发

2.  获取平台推送到第三方应用的对象列表（获取平台要发送到第三方哪些表（ref_queue_obj,ref_platform_app,push_obj）） 简述：要发哪些表

3.  获取每个表需要推送哪些字段的集合（集合包含：表名，以及每个表需要同步的字段。当然还有上面的队列名）  简述：要发的表的里面哪些字段

4. 表名，队列名.+detail_obj.name,平台唯一标识，objinfo(表名，表哪些列)     简述：无

5.  查询该表下的所有字段 list<Obj<所有列>>  --通过比对是否在需要同步的列集合里面得到---》 list<obj<所规定列>>  getDatas  简述：根据表名查询所有列数据，然后再遍历列集合，筛选字段得到需要发送的最终数据   表1（字段1，字段3.....）

6. 注意在getData时侯，需要根据路由表来判断该数据是否同步过，同步过的数据不再参与同步，对查询得到的数据进行增删改查的分类封装，保存查询到的数据列表    简述：分类需要发送的数据，增删改，还有加密参数

7.  开始发送数据  （app的 accessKey为app_secret,队列名agent,）   简述：设置发送数据的优先级以及一些加密参数

8.  第六步得到的数据列表ids  更新路由表，将同步标识更新，避免 5，6步得到的数据是已经同步过的



# 消费者接收数据：

监听消息队列获取消息，对消息进行解密验证，获取同步的数据信息，进行入数据库等操作



# data_hub功能解释：

1. 队列名：调用active_mq接口，主要是这个推送过程中产生的一些队列信息。每个队列的生产，消费情况列表。和平台无关，来源 active——mq
		
1. 推送对象绑定：平台发送到应用，使用的队列名，以及每个推送关系，需要推送的表数据对象
2. 用到的表：
    1. ：ref_platform_app  (关联表 open_platfrom,open_app)   
	2. ：ref_queue_obj (表功能如：云平台1到手环1作为一次推送，这次推送绑定的对象是学生表，教师表。这个绑定关系是此表)
	3. ：push_obj 推送对象，即 数据中心支持发送的所有表数据。
	4. ：detail_obj  关联 push_obj
	5. ：detail_obj_column  每个对象对应的属性
	6. : route_*  路由表，判断数据是否同步过
				
				
				
				
				
# 关于爬虫
	/home/data_hub/node/下文件 
	安装 nodejs，npm，forever
	
	修改app.js中   YWRtaW46YWRtaW4=, jmsAdress为爬取页面地址头部
	
	修改之后启动  ： node app.js
	
	后台启动：forever start app.js
	
	```
	var express = require('express');
		var app = express();
		var request = require('request');
		var parseString = require('xml2js').parseString;
		var jmsAdress = 'http://119.23.62.150:8161/admin/xml/queues.jsp';
		// var initAdress = 'http://114.215.29.139:10090/gkopen/rpc/init?tocken=2012335444456871128213845&&id='

		// var options = {
		//     hostname:'114.215.29.139',
		//     port:'8161',
		//     path:'/admin/xml/queues.sjp',
		//     method:'GET',
		//     // headers:{
		//     //     'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
		//     //     'Accept-Encoding':'gzip, deflate',
		//     //     'Accept-Language':'zh-CN,zh;q=0.8',
		//     //     'Connection':'keep-alive',
		//     //     'Authorization':'Basic YWRtaW46bHh0ZXN0',
		//     //     'Cache-Control':'max-age=0',
		//     //     'Upgrade-Insecure-Requests':'1',
		//     //     'Host':'114.215.29.139:8161',
		//     //     'User-Agent':'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.75 Safari/537.36'
		//     // }
		// };
		app.get('/queue/json', function(req,res){
			request.get(jmsAdress,{headers:{'Authorization':'Basic YWRtaW46YWRtaW4='}},function(error,response,body){
			  if (error) {
			  return console.error('upload failed:', error);
			}
			console.log('Upload successful!  Server responded with:', body);
			parseString(body,function(error,data){
			  if (!error){
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

		// app.get('/sync/init',function(req,res){
		  // var id = req.query.id;
		  // request.get(initAdress+id,function(error,response,body){
			// if (error){
			  // return console.error('error');
			// }
			// console.log('adress:',initAdress+id);
			  // console.log('id:',id);
				// console.log('body:',body);
			// res.redirect('http://127.0.0.1:11070/datahub/admin/sync');
		  // })
		// });

		var server = app.listen(11060, function () {
		 console.log("启动成功 11060");
		})
		
```
	
	
	
	
					
				
				
				