个人博客搭建本地文件

\blog\zjy\source\_posts   下写md文件


git bash here


安装 hexo

hexo  g   编译


hexo  d   部署，推送到git  520那个


hexo  server   本地4000端口启动


# 安装hexo
npm install hexo-cli g
# 初始化博客文件夹
hexo init blog
# 切换到该路径
cd blog
# 安装hexo的扩展插件
npm install
# 安装其它插件
npm install hexo-server --save
npm install hexo-admin --save
npm install hexo-generator-archive --save
npm install hexo-generator-feed --save
npm install hexo-generator-search --save
npm install hexo-generator-tag --save
npm install hexo-deployer-git --save
npm install hexo-generator-sitemap --save