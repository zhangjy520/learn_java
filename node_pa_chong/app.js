var express = require('express');
var app = express();
var request = require('request');
var parseString = require('xml2js').parseString;
var jmsAdress = 'http://jira.lexisoft.cc:82/browse/KP-629';
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
    request.get(jmsAdress,{headers:{'Cookie':'JSESSIONID=418DC2A403E27CCC4F427E524A591F2C; seraph.rememberme.cookie=10315%3A5ad67e1c2e4f414c4a0f9aaf4a242fb6bc5760cb; atlassian.xsrf.token=BW8V-JNAR-UIBW-R1MI|46fefd94cc232b98f8a2908582830b699243ca35|lin'}},function(error,response,body){
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
