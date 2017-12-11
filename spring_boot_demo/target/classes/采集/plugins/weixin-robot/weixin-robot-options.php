<?php 
add_action('admin_head','weixin_robot_admin_head');
function weixin_robot_admin_head(){
	global $plugin_page;
	if(in_array($plugin_page, array('weixin-robot', 'weixin-robot-advanced', 'weixin-robot-custom-reply', 'weixin-robot-custom-menu', 'weixin-robot-stats', 'weixin-robot-summary', 'weixin-robot-messages', 'weixin-robot-about'))){
?>
	<style type="text/css">
	#icon-weixin-robot{background-image: url("<?php echo WEIXIN_ROBOT_PLUGIN_URL; ?>/weixin-32.png");background-repeat: no-repeat;}
	h3{margin:20px 0;font-size: 20px;line-height: 23px;}
	</style>
	<script type="text/javascript">
	jQuery(function(){
		jQuery('span.delete a').click(function(){
			return confirm('确实要删除吗?'); 
		}); 
	});
	</script> 
<?php
	}
}

function weixin_robot_about_page() {
	?>
	<div class="wrap" style="width:600px;">
		<div id="icon-weixin-robot" class="icon32"><br></div>
		<h2>微信机器人高级版</h2>

		<p>如果你需要一些更强大的功能，你可以尝试下：<a href="http://wpjam.net/item/weixin-robot-advanced/">微信机器人高级版</a>，相对基础版，高级版本让你你设置更加容易，可以在后台设置 微信 Token 等，还有一些更加强大的功能，比如输入 n 返回最新文章，输入 r 返回随机文章等等，并且这些关键字都可以自定义。高级版还支持自定义文本或者图文回复。以及详细的数据统计等功能，并且高级版。</p>

		<p><img src="http://wpjamnet.qiniudn.com/wp-content/uploads/2013/04/wx-custom-replies.png" alt="微信高级版-自定义回复" /></p>

		<p>高级版是收费的，猛击这里查看和购买<a href="http://wpjam.net/item/weixin-robot-advanced/">微信机器人高级版</a>。</p>

		<h2>定制功能更强大的微信机器人</h2>

		<p>如果高级版还不能满足你你的需求，或者你的需求比较奇怪或者希望有更强大微信机器人，寻找快速的主机和优化你的 WordPress 来运行你的微信机器人，</p>
		<p><strong style="color:red; font-weight:bold;">请联系 Denis，QQ：11497107。</strong></p>
	</div>
	<?php
}

function weixin_robot_get_option_labels($type){
	if($type == 'basic'){
		$option_group               =   'weixin-robot-basic-group';
	    $option_name = $option_page =   'weixin-robot-basic';
	    $option_section             =   'weixin-robot-basic-section';
	    $section_title				=   '';

	    $option_fileds = array(
			array('name'=>'weixin_token',					'title'=>'微信 Token',		'type'=>'text'),
			array('name'=>'weixin_default',					'title'=>'默认缩略图',		'type'=>'text'),
			array('name'=>'weixin_keyword_allow_length',	'title'=>'搜索关键字最大长度',	'type'=>'text',		'description'=>'一个汉字算两个字节，一个英文单词算两个字节，空格不算，搜索多个关键字可以用空格分开！'),
			array('name'=>'weixin_count',					'title'=>'返回结果最大条数',	'type'=>'text',		'description'=>'微信接口最多支持返回10个'), 
			array('name'=>'weixin_welcome',					'title'=>'欢迎语',			'type'=>'textarea'),
			array('name'=>'weixin_keyword_too_long',		'title'=>'超过最大长度提示语',	'type'=>'textarea',	'description'=>'设置超过最大长度提示语，留空则不回复！'),
			array('name'=>'weixin_not_found',				'title'=>'搜索结果为空提示语',	'type'=>'textarea',	'description'=>'可以使用 [keyword] 代替相关的搜索关键字，留空则不回复！'),
			array('name'=>'weixin_voice',					'title'=>'语音回复',			'type'=>'textarea',	'description'=>'设置语言的默认回复文本，留空则不回复！'),
		//	array('name'=>'weixin_disable_stats',			'title'=>'屏蔽统计',			'type'=>'checkbox'), 
		//	array('name'=>'weixin_app_id',					'title'=>'AppID',			'type'=>'text',		'description'=>'设置自定义菜单的所需的 AppID，如果没申请，可不填！'),
		//	array('name'=>'weixin_app_secret',				'title'=>'APPSecret',		'type'=>'text',		'description'=>'设置自定义菜单的所需的 APPSecret，如果没申请，可不填！'),
		);

		$option_fileds = apply_filters('weixin_basic_fileds',$option_fileds);

	    $field_callback 			=	'weixin_robot_settings_field_callback';
	    $field_validate				=	'weixin_robot_basic_validate';
	    $section_callback 			=	'';
	    
	}elseif($type == 'advanced'){
		
	}
    
    return compact('option_group','option_name','option_page','option_section','section_title','option_fileds','field_callback','field_validate','section_callback');
}

function weixin_robot_get_default_option($option_name){
	if($option_name == 'weixin-robot-basic'){
		return array(
			'weixin_token'					=> 'weixin',
			'weixin_default'				=> '',
			'weixin_keyword_allow_length'	=> '16',
			'weixin_count'					=> '5',
			'weixin_welcome'				=> "欢迎关注，请输入关键字搜索！",
			'weixin_keyword_too_long'		=> '你输入的关键字太长了，系统没法处理了，请等待公众账号管理员到微信后台回复你吧。',
			'weixin_not_found'				=> '抱歉，没有找到与[keyword]相关的文章，要不你更换一下关键字，可能就有结果了哦 :-)',
			'weixin_voice'					=> '系统暂时还不支持语音回复，直接发送文本来搜索吧。\n获取更多帮助信息请输入：h。',
			'weixin_disable_stats'			=> '1',
			'new'		=> 'n',
			'rand'		=> 'r', 
			'hot'		=> 't',
			'comment'	=> 'c',
			'hot-7'		=> 't7',
			'comment-7'	=> 'c7',
			'hot-30'	=> 't30',
			'comment-30'=> 'c30'
		);
	}
}

function weixin_robot_get_option($option_name){
	$weixin_robot_option = get_option( $option_name );

	if($option_name == 'weixin-robot-basic' && $weixin_robot_option){
		return $weixin_robot_option;
	}else{
		$defaults = weixin_robot_get_default_option($option_name);
		return wp_parse_args($weixin_robot_option, $defaults);
	}
}

/*向下兼容*/
function weixin_robot_get_default_basic_option(){
	return weixin_robot_get_default_option( 'weixin-robot-basic' );
}
function weixin_robot_get_default_advanced_option(){
 	return weixin_robot_get_default_option( 'weixin-robot-advanced' );
}
function weixin_robot_get_basic_option(){
	return weixin_robot_get_option('weixin-robot-basic' );
}
function weixin_robot_get_advanced_option(){
	return weixin_robot_get_option('weixin-robot-advanced' );
}

function weixin_robot_basic_setting_page() {
	extract(weixin_robot_get_option_labels('basic'));
	?>
	<div class="wrap">
		<div id="icon-weixin-robot" class="icon32"><br></div>
		<h2>基本设置</h2>
		<form action="options.php" method="POST">
			<?php settings_fields( $option_group ); ?>
			<?php do_settings_sections( $option_name  ); ?>
			<?php submit_button(); ?>
		</form>
	</div>
	<?php
}

function weixin_robot_advanced_setting_page() {
	extract(weixin_robot_get_option_labels('advanced'));
	?>
	<div class="wrap">
		<div id="icon-weixin-robot" class="icon32"><br></div>
		<h2>高级设置</h2>
		<form action="options.php" method="POST">
			<?php settings_fields( $option_group ); ?>
			<?php do_settings_sections( $option_name ); ?>
			<?php submit_button(); ?>
		</form>
	</div>
	<?php
}

add_action( 'admin_init', 'weixin_robot_admin_init' );
function weixin_robot_admin_init() {
	weixin_robot_add_settings('basic');
	//weixin_robot_add_settings('advanced');
}

function weixin_robot_add_settings($type){
	extract(weixin_robot_get_option_labels($type));
	register_setting( $option_group, $option_name, $field_validate );
	add_settings_section( $option_section, $section_title, $section_callback, $option_page );
	foreach ($option_fileds as $field) {
		$field['option'] = $option_name;
		add_settings_field( 
			$field['name'],
			$field['title'],		
			$field_callback,	
			$option_page, 
			$option_section,	
			$field
		);
	}

}

function weixin_robot_settings_field_callback($args) {
	$weixin_robot_option = weixin_robot_get_option($args['option']);

	$value = isset($weixin_robot_option[$args['name']])?$weixin_robot_option[$args['name']]:'';

	if($args['type'] == 'text'){
		echo '<input type="text" name="'.$args['option'].'['.$args['name'].']" value="'.$value.'" class="regular-text" />';
	}elseif($args['type'] == 'textarea'){
		echo '<textarea name="'.$args['option'].'['.$args['name'].']" rows="6" cols="50" class="regular-text code">'.$value.'</textarea>';
	}elseif($args['type'] == 'checkbox'){
		$checked = $value?'checked':'';
		echo '<input type="checkbox" name="'.$args['option'].'['.$args['name'].']" value="1" '.$checked.' />';
	}
	if(isset($args['description'])) echo '<p class="description">'.$args['description'].'</p>';
}

function weixin_robot_advanced_section_callback(){
	echo '<p style="color:red; font-weight:bold;">修改下面的关键字，请主要修改下基本设置中欢迎语中对应的关键字。</p>';
}

function weixin_robot_basic_validate( $weixin_robot_basic ) {
	$current = get_option( 'weixin-robot-basic' );

	if ( !is_numeric( $weixin_robot_basic['weixin_keyword_allow_length'] ) ){
		$weixin_robot_basic['weixin_keyword_allow_length'] = $current['weixin_keyword_allow_length'];
		add_settings_error( 'weixin-robot-basic', 'invalid-int', '搜索关键字最大长度必须为数字。' );
	}
	if ( !is_numeric( $weixin_robot_basic['weixin_count'] ) ){
		$weixin_robot_basic['weixin_count'] = $current['weixin_count'];
		add_settings_error( 'weixin-robot-basic', 'invalid-int', '返回结果最大条数必须为数字。' );
	}elseif($weixin_robot_basic['weixin_count'] > 10){
		$weixin_robot_basic['weixin_count'] = 10;
		add_settings_error( 'weixin-robot-basic', 'invalid-int', '返回结果最大条数不能超过10。' );
	}

	return $weixin_robot_basic;
}