title: jQuery前端参数传递方案
author: zjy
date: 2017-07-19 18:04:42
tags:
---
# 前端参数传递方案


## 需求
```
 在页面之间进行传值的时候，如果遇到参数长度非常大的时候，我们直接url传值会400错误，
 参数太长。我们当然可以把参数经过post后台传递，但是请求产生的网络开销呢？下面介绍几种方式好不好
 
```

## cookie

```
将需要传递的参数   

参数传递起始页面：setCookie(key,value);
参数接受页面：getCookie（key）;

代码如下：

function setCookie(name,value)
{
  var Days = 30; //此 cookie 将被保存 30 天
  var exp  = new Date();    //new Date("December 31, 9998");
  exp.setTime(exp.getTime() + Days*24*60*60*1000);
  document.cookie = name + "="+ escape(value) +";expires="+ exp.toGMTString();
}
function getCookie(name)
{
  var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
  if(arr != null) return unescape(arr[2]); return null;
}


但是这样做的问题就是，用户假如禁用了cookie，那么这些方法将无效

```

## LocalStorage 

```
1、localStorage拓展了cookie的4K限制2、localStorage会可以将第一次请求的数据直接存储到本地，这个相当于一个5M大小的针对于前端页面的数据库，相比于cookie可以节约带宽，但是这个却是只有在高版本的浏览器中才支持的localStorage的局限1、浏览器的大小不统一，并且在IE8以上的IE版本才支持localStorage这个属性
2、目前所有的浏览器中都会把localStorage的值类型限定为string类型，这个在对我们日常比较常见的JSON对象类型需要一些转换
3、localStorage在浏览器的隐私模式下面是不可读取的
4、localStorage本质上是对字符串的读取，如果存储内容多的话会消耗内存空间，会导致页面变卡
5、localStorage不能被爬虫抓取到localStorage与sessionStorage的唯一一点区别就是localStorage属于永久性存储，而sessionStorage属于当会话结束的时候，sessionStorage中的键值对会被清空

语法：
获取键值：localStorage.getItem(“key”)
设置键值：localStorage.setItem(“key”,”value”)
清除键值：localStorage.removeItem(“key”)
清除所有键值：localStorage.clear()
获取键值2：localStorage.keyName
设置键值2：localStorage.keyName = “value”

```


## js生成唯一的key 

```
function generateUUID() {
var d = new Date().getTime();
var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) { var r = (d + Math.random()*16)%16 | 0; d = Math.floor(d/16); return (c=='x' ? r : (r&0x3|0x8)).toString(16); });
return uuid;
}; //碰撞率：1/2^^122


function generateUUID() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
    var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
    return v.toString(16);
  });
}


function generateUUID() {
  function S4() {
    return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
  }
  return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}


function generateUUID(len, radix) {
  var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
  var uuid = [], i;
  radix = radix || chars.length;
 
  if (len) {
   // Compact form
   for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
  } else {
   // rfc4122, version 4 form
   var r;
 
   // rfc4122 requires these characters
   uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
   uuid[14] = '4';
 
   // Fill in random data. At i==19 set the high bits of clock sequence as
   // per rfc4122, sec. 4.1.5
   for (i = 0; i < 36; i++) {
    if (!uuid[i]) {
     r = 0 | Math.random()*16;
     uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
    }
   }
  }
 
  return uuid.join('');
}



function generateUUID() {
  var s = [];
  var hexDigits = "0123456789abcdef";
  for (var i = 0; i < 36; i++) {
    s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
  }
  s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
  s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
  s[8] = s[13] = s[18] = s[23] = "-";
 
  var uuid = s.join("");
  return uuid;
}

```