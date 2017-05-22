爬虫页面

下载文件包

/home/node_pa_chong下的所有

安装node

$ wget http://nodejs.org/dist/v0.8.16/node-v0.8.16.tar.gz
$ tar zxvf node-v0.8.16.tar.gz
$ ./configure
$ make && make install

安装好后，在 控制台下输入：
$ node -v
v0.8.16
升级node

首先安装n模块：
        npm install -g n

    升级node.js到最新稳定版
        n stable
        n 0.10.26 //或者可以选择升级到指定版本


    npm -v          #显示版本，检查npm 是否正确安装。
     
    npm install express   #安装express模块
     
    npm install -g express  #全局安装express模块
     
    npm list         #列出已安装模块
     
    npm show express     #显示模块详情
     
    npm update        #升级当前目录下的项目的所有模块
     
    npm update express    #升级当前目录下的项目的指定模块
     
    npm update -g express  #升级全局安装的express模块
     
    npm uninstall express  #删除指定的模块
暂时启动
node app.js

安装forever npm -g install forever

启动 forever start app.js