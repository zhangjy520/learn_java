<?php
$themename = "DanXs";
$shortname = "xs";
$categories = get_categories('hide_empty=0&orderby=name');
$wp_cats = array();
foreach ($categories as $category_list ) {
       $wp_cats[$category_list->cat_ID] = $category_list->cat_name;
}
array_unshift($wp_cats, "Choose a category"); 
$options = array ( 
array( "name" => $themename." Options",
	"type" => "title"), 

//首页布局设置

    array( "name" => "首页布局",
           "type" => "section"),
    array( "type" => "open"),

	array(	"name" => "首页左侧分类ID设置",
			"desc" => "输入分类ID，显示更多分类，请用英文逗号＂,＂隔开",
            "id" => $shortname."_cat",
            "type" => "text",
            "std" => "1,2,3,4"),

	array(  "name" => "是否显示评论",
			"desc" => "默认显示",
            "id" => $shortname."_comment",
            "type" => "select",
            "std" => "Hide",
            "options" => array("Display", "Hide")),	
//广告

    array( "type" => "close"),
	array( "name" => "广告设置",
			"type" => "section"),
	array( "type" => "open"),
 
 	array(	"name" => "评论右侧广告",
            "desc" => "",
            "id" => $shortname."_ad_commentad",
            "type" => "textarea",
            "std" => ''),

	array(  "name" => "是否显示正文结束时的广告",
			"desc" => "默认显示",
            "id" => $shortname."_adzwfoot",
            "type" => "select",
            "std" => "Hide",
            "options" => array("Display", "Hide")),

	array(	"name" => "输入广告代码",
            "desc" => "",
            "id" => $shortname."_ad_zwfoot",
            "type" => "textarea",
            "std" => ''),

	array(  "name" => "是否显示正文头部广告",
			"desc" => "默认显示",
            "id" => $shortname."_adt",
            "type" => "select",
            "std" => "Hide",
            "options" => array("Display", "Hide")),

	array(	"name" => "输入广告代码",
            "desc" => "",
            "id" => $shortname."_ad_t",
            "type" => "textarea",
            "std" => ''),	

//SEO设置
    array( "type" => "close"),
	array( "name" => "网站SEO设置及流量统计",
       "type" => "section"),
	array( "type" => "open"),

	array(	"name" => "描述（Description）",
			"desc" => "",
			"id" => $shortname."_description",
			"type" => "textarea",
            "std" => "小土哥的博客专注于养生，网页编程，网站优化等方面的知识，分享养生健康，讨论网页编程技巧，实现Wordpress优化！"),

	array(	"name" => "关键词（KeyWords）",
            "desc" => "",
            "id" => $shortname."_keywords",
            "type" => "textarea",
            "std" => "爱推吧，Era，WordPress主题，WordPress插件，WordPress优化，网站优化，Wordpress，div+css，主题isblog，主题iwp，wp，免费建站，WordPress工作站，theme，WordPress原创主题，WordPress主题下载，三蛋免费空间，000webhost，godaddy优惠码，ituibar"),

	array("name" => "作者",
            "desc" => "",
            "id" => $shortname."_author",
            "type" => "text",
            "std" => "作者：唐家三少"),
			
			array("name" => "作品信息",
            "desc" => "",
            "id" => $shortname."_novelinfo",
            "type" => "textarea",
            "std" => ""),
			
	array("name" => "统计代码",
            "desc" => "",
            "id" => $shortname."_track_code",
            "type" => "textarea",
            "std" => ""),			
	array("name" => "输入你的网站地图的地址",
            "desc" => "你的网站地图，给搜索引擎用的",
            "id" => $shortname."_map",
            "type" => "text",
            "std" => ""),
	array(	"type" => "close")
);
function mytheme_add_admin() {
global $themename, $shortname, $options;
if ( $_GET['page'] == basename(__FILE__) ) {
	if ( 'save' == $_REQUEST['action'] ) {
		foreach ($options as $value) {
		update_option( $value['id'], $_REQUEST[ $value['id'] ] ); }
foreach ($options as $value) {
	if( isset( $_REQUEST[ $value['id'] ] ) ) { update_option( $value['id'], $_REQUEST[ $value['id'] ]  ); } else { delete_option( $value['id'] ); } }
	header("Location: admin.php?page=theme_option.php&saved=true");
die;
}
else if( 'reset' == $_REQUEST['action'] ) {
	foreach ($options as $value) {
		delete_option( $value['id'] ); }
	header("Location: admin.php?page=theme_option.php&reset=true"); //这里的 theme_option.php 就是这个文件的名称
die;
}
} 
add_theme_page($themename." Options", "主题设置", 'edit_themes', basename(__FILE__), 'mytheme_admin');
}
function mytheme_add_init() {
$file_dir=get_bloginfo('template_directory');
wp_enqueue_style("functions", $file_dir."/options/option.css", false, "1.0", "all");
wp_enqueue_script("rm_script", $file_dir."/options/rm_script.js", false, "1.0");
}
function mytheme_admin() { 
global $themename, $shortname, $options;
$i=0; 
if ( $_REQUEST['saved'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' 主题设置已保存</strong></p></div>';
if ( $_REQUEST['reset'] ) echo '<div id="message" class="updated fade"><p><strong>'.$themename.' 主题已重新设置</strong></p></div>'; 
?>
<div class="wrap rm_wrap">
<h2><?php echo $themename; ?>主题设置</h2> 
<div class="rm_opts">
<form method="post">
<?php foreach ($options as $value) {
switch ( $value['type'] ) { 
case "open":
?> 
<?php break; 
case "close":
?> 
</div>
</div>
<?php break; 
case "title":
?>
<p style="font-size:14px;">主题<a href="http://ituibar.com/topics-isblog-release/" title="DanXs主题下载" target="_blank"><?php echo $themename;?></a> 由<a href="http://ituibar.com"  target="_blank">Era</a>设计.</p> 
<?php break; 
case 'text':
?>
<div class="rm_input rm_text">
	<label for="<?php echo $value['id']; ?>"><?php echo $value['name']; ?></label>
 	<input name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" type="<?php echo $value['type']; ?>" value="<?php if ( get_settings( $value['id'] ) != "") { echo stripslashes(get_settings( $value['id'])  ); } else { echo $value['std']; } ?>" />
	<small><?php echo $value['desc']; ?></small><div class="clearfix"></div> 
</div>
<?php
break; 
case 'textarea':
?>
<div class="rm_input rm_textarea">
	<label for="<?php echo $value['id']; ?>"><?php echo $value['name']; ?></label>
 	<textarea name="<?php echo $value['id']; ?>" type="<?php echo $value['type']; ?>" cols="" rows=""><?php if ( get_settings( $value['id'] ) != "") { echo stripslashes(get_settings( $value['id']) ); } else { echo $value['std']; } ?></textarea>
	<small><?php echo $value['desc']; ?></small><div class="clearfix"></div> 
 </div>  
<?php
break; 
case 'select':
?>
<div class="rm_input rm_select">
	<label for="<?php echo $value['id']; ?>"><?php echo $value['name']; ?></label>	
	<select name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>">
	<?php foreach ($value['options'] as $option) { ?>
	<option <?php if (get_settings( $value['id'] ) == $option) { echo 'selected="selected"'; } ?>><?php echo $option; ?></option><?php } ?>
	</select>
	<small><?php echo $value['desc']; ?></small><div class="clearfix"></div>
</div>
<?php
break; 
case "checkbox":
?>
<div class="rm_input rm_checkbox">
	<label for="<?php echo $value['id']; ?>"><?php echo $value['name']; ?></label>	
	<?php if(get_option($value['id'])){ $checked = "checked=\"checked\""; }else{ $checked = "";} ?>
	<input type="checkbox" name="<?php echo $value['id']; ?>" id="<?php echo $value['id']; ?>" value="true" <?php echo $checked; ?> />
	<small><?php echo $value['desc']; ?></small><div class="clearfix"></div>
</div>
<?php break; 
case "section":
$i++;
?>
<div class="rm_section">
<div class="rm_title">
	<h3 class="inactive"><?php echo $value['name']; ?></h3><span class="submit"><input name="save<?php echo $i; ?>" type="submit" value="保存" /></span>
	<div class="clearfix"></div>
</div>
<div class="rm_options"> 
<?php break; 
}
}
?> 
 <?php
function show_id() {
	global $wpdb;
	$request = "SELECT $wpdb->terms.term_id, name FROM $wpdb->terms ";
	$request .= " LEFT JOIN $wpdb->term_taxonomy ON $wpdb->term_taxonomy.term_id = $wpdb->terms.term_id ";
	$request .= " WHERE $wpdb->term_taxonomy.taxonomy = 'category' ";
	$request .= " ORDER BY term_id asc";
	$categorys = $wpdb->get_results($request);
	foreach ($categorys as $category) { 
		$output = '<li>'.$category->name."( <em>".$category->term_id.'</em> )</li>';
		echo $output;
	}
}
?>
<div class="show_id">欢迎光临<br /><font style="font-size:14px;"color=#ff0000><a href="<?php bloginfo(url);?>"><?php bloginfo(name); ?></a><br /></font><h4>查看本站所有分类ID</h4><ul><?php show_id();?></ul>
<strong>如果您感觉主题不错，且有能力，你可以通过支付宝来捐赠我,谢谢！<br/>支付宝帐号:<br /><font style="color:red;font-size:14px;font-family:Arial;">sxamsky@yeah.net</font></strong>
</div>
<input type="hidden" name="action" value="save" />
</form>
<form method="post">
	<p class="submit">
	<input name="reset" type="submit" value="重置" /><font color=#ff0000> 提示：此按钮将恢复主题初始状态，您的所有设置将消失！</font>
	<input type="hidden" name="action" value="reset" />
	</p>
</form>
</div> 
<?php
}
?>
<?php
add_action('admin_init', 'mytheme_add_init');
add_action('admin_menu', 'mytheme_add_admin');
?>