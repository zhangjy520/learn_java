###### ---
title: iframe跨域嵌套头部 
date: 2017-05-05 13:51:49
tags: js   跨域
---

# 需求

  ```
 不同域名，使用iframe，想互相获取html
 
 请查找 navigator.rar文件
 
  ```
  
# 实现思路
```
使用js listener 消息发送html代码。




头部公共菜单使用方法：

（原理是将平台的头部菜单代码作为消息跨域发送到第三方应用）

前提：
	平台的头部菜单，使用的css，js ，png，jpg静态资源文件支持外链访问
	例如：在第三方应用中 ,如下语句能访问到对应资源  
		<script src ="www.pingtai.com/js">
		<link src ="www.pingtai.com/css">
		<img src = "www.pingtai.com/***.">
		
	平台，第三方通过message.js插件来传递头部菜单代码
		

安装步骤：

	切忌：平台用的logo等静态资源文件一定是外链能够访问的！否则代码同步到第三方应用，他的logo显示不出来

	上面说到是将平台头部菜单作为消息跨域发送，那么就有一个发送者（平台），一个接收者（第三方应用）
	
	
	1：作为平台的开发者：发送头部菜单代码：

		<script>
			 window.onload = function(){
				 var messenger = new Messenger('iframe1', 'header'); // header是消息的key
				 
				 messenger.addTarget(window.parent, 'parent'); 
					var msg = document.getElementById("header").innerHTML;
					messenger.targets["parent"].send(msg);
				 }
	
		</script>
		
	2：作为第三方应用对接者
	
		2-1： 引用平台提供的css，js外链样式以及js 
		2-2:  页面引用iframe src为平台提供的头部菜单页面	
			 <iframe id="iframe" src="../platform/header.html" frameborder="0" style="display:none"></iframe>
			 
		2-3 在页面添加头部菜单的div
				<div id="header">
				</div> 
		2-4 接受消息，将消息（此消息是平台发送的头部菜单的代码）放到2-3建的header里面
		

```

# 实现代码如下：
message.js 代码
```
window.Messenger = (function() {

    // 消息前缀, 建议使用自己的项目名, 避免多项目之间的冲突
    // !注意 消息前缀应使用字符串类型
    var prefix = "koolearn",
        supportPostMessage = 'postMessage' in window,
        lastHash = document.location.hash,
        intervalId,
        cacheId = 1,
        extend = function() {
            var args = arguments,
                o = args[0],
                len = args.length,
                curr;
            for (var j = 1; j < len; j++) {
                curr = args[j];
                for (var i in curr) {
                    curr.hasOwnProperty(i) && (o[i] = curr[i]);
                }
            }
            return o;
        };

    /**
     * [Target description]
     * @param {object} target Target 类, 消息对象
     * @param {string} name   名字,iframe的id
     * @param {string} prefix 前缀
     */
    function Target(target, name, prefix) {
        var errMsg = '';
        if (arguments.length < 2) {
            errMsg = 'target error - target and name are both required';
        } else if (typeof target != 'object') {
            errMsg = 'target error - target itself must be window object';
        } else if (typeof name != 'string') {
            errMsg = 'target error - target name must be string type';
        }
        if (errMsg) {
            throw new Error(errMsg);
        }

        this.target = target;
        this.name = name;
        this.prefix = prefix;
    }

    /**
     * 消息拼接
     * @param  {[string]} msg
     * @return {[string]}
     */
    Target.prototype.handleMsg = function(msg) {
        //prefix|name__Messenger__msg
        return this.prefix + '|' + this.name + '__Messenger__' + msg;
    };

    /**
     * 往 target 发送消息, 出于安全考虑, 发送消息会带上前缀
     * @type {[type]}
     */
    Target.prototype.send = supportPostMessage ?
        // IE8+ 以及现代浏览器支持
        function(msg) {
            this.target.postMessage(this.handleMsg(msg), '*');
        } :
        // 兼容IE 6/7
        function(msg, targetUrl) {
            targetUrl = (targetUrl || this.target.location || parent.location.href) + '';
            //修改hash
            this.target.location = targetUrl.replace(/#.*$/, '') + '#' + (+new Date) + (cacheId++) + '&' + this.handleMsg(msg);
        };

    /**
     * 默认配置项，目前就一个
     * @type {Object}
     */
    var defaultOpts = {
        delay: 200
    };

    // 信使类
    // 创建Messenger实例时指定, 必须指定Messenger的名字, (可选)指定项目名, 以避免Mashup类应用中的冲突
    // !注意: 父子页面中projectName必须保持一致, 否则无法匹配
    function Messenger(messengerName, projectName) {
        this.targets = {};
        this.name = messengerName;
        this.listenFunc = [];
        this.prefix = projectName || prefix;
        this.opts = extend({}, defaultOpts);
        this.initListen();
    }

    /**
     * set opts
     * @param {object} opts
     */
    Messenger.prototype.setOpts = function(opts) {
        this.opts = extend(this.opts, opts || {});
    };

    /**
     * 添加一个消息对象
     * @param {object} target
     * @param {string} name
     */
    Messenger.prototype.addTarget = function(target, name) {
        this.targets[name] = new Target(target, name, this.prefix);
    };

    /**
     * 移除一个消息对象
     * @param  {string} name
     * @return {}
     */
    Messenger.prototype.removeTarget = function(name) {
        delete this.targets[name];
    };

    /**
     * 初始化消息监听
     * @return {null}
     */
    Messenger.prototype.initListen = function() {
        var self = this;
        /**
         * [generalCallback description]
         * @param  {string} msg prefix|name__Messenger__msg
         * @return {[type]}     [description]
         */
        var generalCallback = function(msg) {
            if (typeof msg == 'object' && msg.data) {
                msg = msg.data;
            }

            var msgPairs = msg.split('__Messenger__');
            var msg = msgPairs[1];
            var pairs = msgPairs[0].split('|');
            var prefix = pairs[0];
            var name = pairs[1];

            if (prefix + name !== self.prefix + self.name) {
                console.warn('error ');
                return;
            }

            for (var i = 0, len = self.listenFunc.length; i < len; i++) {
                self.listenFunc[i](msg);
            }
        };

        //高级浏览器
        if (supportPostMessage) {
            if ('addEventListener' in document) {
                window.addEventListener('message', generalCallback, false);
            } else if ('attachEvent' in document) {
                window.attachEvent('onmessage', generalCallback);
            }
            return;
        }

        // 兼容IE 6/7
        intervalId && clearInterval(intervalId);
        intervalId = null;
        intervalId = setInterval(function() {
            var hash = document.location.hash,
                re = /^#?\d+&/;
            if (hash !== lastHash && re.test(hash)) {
                lastHash = hash;
                generalCallback(hash.replace(re, ''));
            }
        }, self.opts.delay);
    };

    /**
     * 监听消息
     * @param  {Function} callback
     * @return {[type]}
     */
    Messenger.prototype.listen = function(callback) {
        var i = 0;
        var len = this.listenFunc.length;
        var cbIsExist = false;
        for (; i < len; i++) {
            if (this.listenFunc[i] == callback) {
                cbIsExist = true;
                break;
            }
        }
        if (!cbIsExist) {
            this.listenFunc.push(callback);
        }
    };

    /**
     * 注销监听
     * @return {[type]} [description]
     */
    Messenger.prototype.clear = function() {
        this.listenFunc.length = 0;
    };

    /**
     * 广播消息,给所有的消息对象发送消息
     * @param  {[type]} msg [description]
     * @return {[type]}     [description]
     */
    //低版本浏览器要求提供url 这里不行
    Messenger.prototype.send = function(msg) {
        var targets = this.targets,
            target;
        for (target in targets) {
            if (targets.hasOwnProperty(target)) {
                targets[target].send(msg);
            }
        }
    };
    return Messenger;
}());

```

平台发送html代码方

```
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8" />
    <title>Html version | Angulr</title>
    <meta name="description" content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/top.css" rel="stylesheet"/>
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div id="header">
<nav class="navbar navbar-inverse navbar-static-top container-fluid fixed-top" role="navigation">
    <div class="navbar-header">
        <a><img src="C:/Users/Administrator/Desktop/navigator1/platform/images/logo1.png"/></a>
    </div>
    <div class="collapse navbar-collapse" id="navbar">
        <div  class="nav navbar-nav nav_nav hidden-sm top-title" id="nav-nav">
            第三方应用名称
        </div>
        <ul class="nav navbar-nav navbar-right main-nav">
            <li class="btn-group">
                <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="glyphicon glyphicon-bell" style="color: #fff;"></span>
                </a> 
                <ul class="dropdown-menu" style="margin-top: 9px;">

                    <li>
                        <a>&nbsp;应用1</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a>&nbsp;应用2</a>
                    </li>
					  <li>
                        <a>&nbsp;应用2</a>
                    </li>
					  <li>
                        <a>&nbsp;应用2</a>
                    </li>
                </ul> 
            </li>

            <!-- Single button -->
            <li class="btn-group">
                <a class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span style="color: #fff;">仲玮</span>
					<span class="caret" style="border-top-color: #7793a7;"></span>
                </a>

                <!-- dropdown -->
                <ul class="dropdown-menu" style="margin-top: 9px;">

                    <li>
                        <a>&nbsp;个人中心</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a>&nbsp;注销</a>
                    </li>
                </ul>
                <!-- / dropdown -->
            </li>
        </ul>
    </div>

</nav>
</div>

<script src="js/message.js"></script>
<script>
 window.onload = function(){
	 var messenger = new Messenger('iframe1', 'header'); 
     
     messenger.addTarget(window.parent, 'parent'); 
		var msg = document.getElementById("header").innerHTML;
		messenger.targets["parent"].send(msg);
	 }
	
</script>
</body>
</html>

```


第三方接收html代码端
```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
     <link href="../platform/css/bootstrap.min.css" rel="stylesheet"/>
     <link href="../platform/css/top.css" rel="stylesheet"/>
     <script src="../platform/js/jquery.js"></script>
     <script src="../platform/js/bootstrap.min.js"></script>
</head>
<body>

<iframe id="iframe" src="../platform/header.html" frameborder="0" style="display:none"></iframe>

<div id="header">
</div> 

这个header div下面就是第三方应用自己的页面内容


<script src="../platform/js/message.js"></script>
	<script type="text/javascript">
	var messenger = new Messenger('parent', 'header'), 
            input = document.getElementById('message');
            messenger.listen(function(msg) { 
                document.getElementById('header').innerHTML=msg;
            }); 
    </script>
</body>
</html>

```