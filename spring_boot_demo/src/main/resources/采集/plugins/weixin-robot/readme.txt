=== 微信机器人（已支持易信） ===
Contributors: denishua
Donate link: https://me.alipay.com/denishua
Tags: Weixin
Requires at least: 3.0
Tested up to: 3.6.1
Stable tag: trunk
License: GPLv2 or later
License URI: http://www.gnu.org/licenses/gpl-2.0.html

微信机器人将公众账号和 WordPress 博客联系起来，搜索和用户发送信息匹配的日志，并自动回复用户。


== Description ==

微信机器人的主要功能就是能够将你的公众账号和你的 WordPress 博客联系起来，搜索和用户发送信息匹配的日志，并自动回复用户，让你使用微信进行营销事半功倍。

同时也支持易信。

详细介绍： http://blog.wpjam.com/project/weixin-robot/

购买高级版： http://wpjam.net/item/weixin-robot-advanced/


== Installation ==


1. 上传 `weixin-robot.php` 到 `/wp-content/plugins/` 目录
1. 在后台插件菜单激活该插件
1. 在微信后台，将接口配置信息中的 URL 设置为：http://你博客地址/?weixin Token 设置为：weixin


== Screenshots ==

1. 微信接口配置。
2. 微信回复机器人。
3. 微信回复机器人后台设置。

== Changelog ==

= 3.0 =

bug 修复。

= 2.1 =

支持易信

= 2.0.1 =

* 修正bug。

= 2.0 =

* 支持后台设置。

= 1.1 =

* 修正新用户订阅提示问题：新用户订阅，将由之前推送一条“Hello2BizUser”文本，变化为推送一条“subscribe”的事件。
* 新增 WEIXIN_DEFAULT 常量，用于设置，在没有缩略图时候的默认缩略图。


= 1.0.1 =

修正特色图片的问题。

= 1.0 =
* 稳定版本。
* 支持特色图片。
* 支持多个关键字。

= 0.1 =
* 初始版本