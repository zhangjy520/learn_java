<?php
/*
Plugin Name: 微信机器人
Plugin URI: http://blog.wpjam.com/project/weixin-robot/
Description: 微信机器人的主要功能就是能够将你的公众账号和你的 WordPress 博客联系起来，搜索和用户发送信息匹配的日志，并自动回复用户，让你使用微信进行营销事半功倍。
Version: 3.1
Author: Denis
Author URI: http://blog.wpjam.com/
*/

define('WEIXIN_ROBOT_PLUGIN_URL', plugins_url('', __FILE__));
define('WEIXIN_ROBOT_PLUGIN_DIR', WP_PLUGIN_DIR.'/'. dirname(plugin_basename(__FILE__)));
define('WEIXIN_ROBOT_PLUGIN_FILE',  __FILE__);

define("WEIXIN_TOKEN", "weixin");	//定义微信 Token，后面 hook
define("WEIXIN_DEFAULT", '');		//定义默认缩略图，后面 hook

add_action('parse_request', 'wpjam_weixin_robot_redirect', 4);
function wpjam_weixin_robot_redirect($wp){
	if(isset($_GET['weixin']) || isset($_GET['yixin']) ){
		global $wechatObj;
		if(!isset($wechatObj)){
			$wechatObj = new wechatCallback();
			$wechatObj->valid();
			exit;
		}
	}
}

class wechatCallback {
	private $keyword		= '';
	private $fromUsername	= '';
	private $toUsername		= '';
	private $textTpl		= '';
	private $picTpl			= '';
	private $response		= '';

	public function valid(){

		if(isset($_GET['debug'])){
			$this->keyword = strtolower(trim($_GET['t']));
			$this->checkSignature();
			$this->responseMsg();
		}else{
			if($this->checkSignature()){
				if(isset($_GET["echostr"])){
					$echoStr = $_GET["echostr"];
					echo $echoStr;					
				}
				$this->responseMsg();
				exit;
			}
		}
	}

	public function responseMsg(){
		//get post data, May be due to the different environments
		$postStr = (isset($GLOBALS["HTTP_RAW_POST_DATA"]))?$GLOBALS["HTTP_RAW_POST_DATA"]:'';

		//extract post data
		if (isset($_GET['debug']) || !empty($postStr)){	
			if(!isset($_GET['debug'])){
				$postObj		= simplexml_load_string($postStr, 'SimpleXMLElement', LIBXML_NOCDATA);

				$this->fromUsername	= $postObj->FromUserName;
				$this->toUsername	= $postObj->ToUserName;

				$this->set_textTpl();
				$this->set_picTpl();
				
				$weixin_message_id = weixin_robot_insert_message($postObj);

				$msgType 		= strtolower(trim($postObj->MsgType));

				if($msgType == 'event'){
					$event = strtolower(trim($postObj->Event));

					if($event == 'subscribe' || $event == 'unsubscribe'){ // 订阅和取消订阅时间
						$this->keyword = $event;
					}elseif($event == 'click'){	//点击事件
						$this->keyword = strtolower(trim($postObj->EventKey));
					}elseif($event == 'view'){	//查看网页事件，估计也进不来。
						exit;
					}
				}elseif($msgType == 'text'){
					$this->keyword = strtolower(trim($postObj->Content));
				}elseif($msgType == 'voice'){
					$this->keyword = '[voice]';
					do_action('weixin_voice',$postObj);
				}elseif($msgType == 'location'){
					do_action('weixin_location',$postObj);
				}
			}else{
				$this->fromUsername = $this->toUsername = '';
				$this->set_textTpl();
				$this->set_picTpl();
			}

			if(empty( $this->keyword ) || strpos($this->keyword, '#') !== false ) {
				echo "";
				exit;
			}

			$textTpl	= $this->get_textTpl();  
			$picTpl		= $this->get_picTpl(); 

			$weixin_custom_keywords = apply_filters('weixin_custom_keywords',array());

			if($weixin_custom_keywords && in_array($this->keyword, $weixin_custom_keywords)){
				do_action('weixin_robot',$this->keyword);
			}elseif( in_array( $this->keyword, array( 'hi', 'h', 'help', '帮助', '您好', '你好', 'subscribe') ) ){
				$weixin_welcome = apply_filters('weixin_welcome',"请输入关键字开始搜索！");
				echo sprintf($textTpl, $weixin_welcome);
				$this->response = 'welcome';
			}elseif($this->keyword == 'unsubscribe' ){
				$weixin_unsubscribe = "你怎么忍心取消对我的订阅？";
				$this->response = 'byebye';
			}elseif($this->keyword == '[voice]' ){
				$weixin_voice = apply_filters('weixin_voice',"系统暂时还不支持语音回复，直接发送文本来搜索吧。\n获取更多帮助信息请输入：h。");
				if($weixin_voice){
					echo sprintf($textTpl, $weixin_voice);
				}
				$this->response = 'voice';
			}else {
				$keyword_length = mb_strwidth(preg_replace('/[\x00-\x7F]/','',$this->keyword),'utf-8')+str_word_count($this->keyword)*2;

				$weixin_keyword_allow_length = apply_filters('weixin_keyword_allow_length',16);
		
				if($keyword_length > $weixin_keyword_allow_length){
					$weixin_keyword_too_long = apply_filters('weixin_keyword_too_long',"你输入的关键字太长了，系统没法处理了，请等待公众账号管理员到微信后台回复你吧。");
					if($weixin_keyword_too_long){
						echo sprintf($textTpl, $weixin_keyword_too_long);
					}
					$this->response = 'too-long';
				}elseif( !empty( $this->keyword )){
					$this->query();
				}
			}
			if(isset($this->response)){
				weixin_robot_update_message($weixin_message_id,$this->response);	
			}
			exit;
		}else {
			echo "";
			exit;
		}
	}

	public function query(){

		$weixin_count = apply_filters('weixin_count',5);

		$weixin_query_array = array(
			's' 					=> $this->keyword, 
			'posts_per_page'		=> $weixin_count , 
			'post_status' 			=> 'publish', 
			'ignore_sticky_posts'	=>1 
		);

		$weixin_query_array = apply_filters('weixin_query',$weixin_query_array); 

		if(!$this->response){
			if(isset($weixin_query_array['s'])){
				$this->response = 'query';
			}elseif(isset($weixin_query_array['cat'])){
				$this->response = 'cat';
			}elseif(isset($weixin_query_array['tag_id'])){
				$this->response = 'tag';
			}elseif(isset($weixin_query_array['post__in'])){
				$this->response = 'custom-img';
			}else{
				$this->response = 'advanced';
			}
		}

		$weixin_robot_query = new WP_Query($weixin_query_array);

		$items = '';

		$counter = 0;

		if($weixin_robot_query->have_posts()){
			while ($weixin_robot_query->have_posts()) {
				$weixin_robot_query->the_post();

				global $post;

				$title =get_the_title(); 
				$excerpt = get_post_excerpt($post,150);

				if($counter == 0){
					$thumb = get_post_weixin_thumb($post, array(640,320));
				}else{
					$thumb = get_post_weixin_thumb($post, array(80,80));
				}

				$link = get_permalink();

				$items = $items . $this->get_item($title, $excerpt, $thumb, $link);

				$counter ++;
			}
		}

		$articleCount = count($weixin_robot_query->posts);
		if($articleCount > $weixin_count) $articleCount = $weixin_count;

		if($articleCount){
			echo sprintf($this->picTpl,$articleCount,$items);
		}else{
			$weixin_not_found = apply_filters('weixin_not_found', "抱歉，没有找到与【{$this->keyword}】相关的文章，要不你更换一下关键字，可能就有结果了哦 :-) ", $this->keyword);
			$weixin_not_found = str_replace('[keyword]', '【'.$this->keyword.'】', $weixin_not_found);
			if($weixin_not_found){
				echo sprintf($this->textTpl, $weixin_not_found);
			}
			$this->response = 'not-found';
		}
	}

	private function get_item($title, $description, $picUrl, $url){
		if(!$description) $description = $title;

		return
		'
		<item>
			<Title><![CDATA['.html_entity_decode($title, ENT_QUOTES, "utf-8" ).']]></Title>
			<Description><![CDATA['.html_entity_decode($description, ENT_QUOTES, "utf-8" ).']]></Description>
			<PicUrl><![CDATA['.$picUrl.']]></PicUrl>
			<Url><![CDATA['.$url.']]></Url>
		</item>
		';
	}

	public function get_fromUsername(){
		return $this->fromUsername;
	}

	public function get_toUsername(){
		return $this->toUsername;
	}

	public function get_response(){
		return $this->response;
	}

	public function get_textTpl(){
		return $this->textTpl;
	}

	public function get_picTpl(){
		return $this->picTpl;
	}

	public function set_response($response){
		$this->response = $response;
	}

	public function set_textTpl(){
		$this->textTpl = "
			<xml>
				<ToUserName><![CDATA[".$this->fromUsername."]]></ToUserName>
				<FromUserName><![CDATA[".$this->toUsername."]]></FromUserName>
				<CreateTime>".time()."</CreateTime>
				<MsgType><![CDATA[text]]></MsgType>
				<Content><![CDATA[%s]]></Content>
				<FuncFlag>0</FuncFlag>
			</xml>
		";
	}


	public function set_picTpl(){
		$this->picTpl = "
			<xml>
				<ToUserName><![CDATA[".$this->fromUsername."]]></ToUserName>
				<FromUserName><![CDATA[".$this->toUsername."]]></FromUserName>
				<CreateTime>".time()."</CreateTime>
				<MsgType><![CDATA[news]]></MsgType>
				<Content><![CDATA[]]></Content>
				<ArticleCount>%d</ArticleCount>
				<Articles>
				%s
				</Articles>
				<FuncFlag>1</FuncFlag>
			</xml>
		";
	}

	private function checkSignature(){
		$signature = $_GET["signature"];
		$timestamp = $_GET["timestamp"];
		$nonce = $_GET["nonce"];	
				
		$weixin_token = apply_filters('weixin_token',WEIXIN_TOKEN);
		if(isset($_GET['debug'])){
			echo "\n".'WEIXIN_TOKEN：'.$weixin_token;
		}
		$tmpArr = array($weixin_token, $timestamp, $nonce);
		sort($tmpArr);
		$tmpStr = implode( $tmpArr );
		$tmpStr = sha1( $tmpStr );
		
		if( $tmpStr == $signature ){
			return true;
		}else{
			return false;
		}
	}
}

add_action( 'admin_menu', 'weixin_robot_admin_menu' );
function weixin_robot_admin_menu() {
	add_menu_page(						'微信机器人',						'微信机器人',	'manage_options',	'weixin-robot',				'weixin_robot_basic_setting_page',	WEIXIN_ROBOT_PLUGIN_URL.'/weixin-16.ico');
	add_submenu_page( 'weixin-robot',	'基本设置 &lsaquo; 微信机器人',	'基本设置',	'manage_options',	'weixin-robot',				'weixin_robot_basic_setting_page');
	add_submenu_page( 'weixin-robot',	'关于和更新 &lsaquo; 微信机器人',	'关于和更新',			'manage_options',	'weixin-robot-about',		'weixin_robot_about_page');
}

include(WEIXIN_ROBOT_PLUGIN_DIR.'/weixin-robot-options.php');
include(WEIXIN_ROBOT_PLUGIN_DIR.'/weixin-robot-hook.php');

function weixin_robot_insert_message($na){
	return 0;
}
function weixin_robot_update_message($na,$nb){
	return;
}
