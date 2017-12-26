<?php
/*basic hook */
add_filter('weixin_token',					'wpjam_basic_filter');
add_filter('weixin_default',				'wpjam_basic_filter');
add_filter('weixin_welcome',				'wpjam_basic_filter');
add_filter('weixin_voice',					'wpjam_basic_filter');
add_filter('weixin_keyword_allow_length',	'wpjam_basic_filter');
add_filter('weixin_keyword_too_long',		'wpjam_basic_filter');
add_filter('weixin_count',					'wpjam_basic_filter');
add_filter('weixin_not_found',				'wpjam_basic_filter');

function wpjam_basic_filter($original){
	$weixin_robot_basic = weixin_robot_get_basic_option();

	global $wp_current_filter;

	//最后一个才是当前的 filter
	$wpjam_current_filter = $wp_current_filter[count($wp_current_filter)-1];

	if(isset($weixin_robot_basic[$wpjam_current_filter])){
		if($weixin_robot_basic[$wpjam_current_filter ]){
			return $weixin_robot_basic[$wpjam_current_filter];
		}
	}else{
		return $original;
	}
}



add_action("wp_head","wpjam_weixin_robot_share_head",99);
function wpjam_weixin_robot_share_head(){
	if(is_singular() && is_weixin()){
	global $post;
?>
<script type="text/javascript">
var dataForWeixin={
	appId:	"",
	img:	"<?php echo get_post_weixin_thumb($post,array(120,120)); ?>",
	url:	"<?php the_permalink($post->ID);?>",
	title:	"<?php echo $post->post_title; ?>",
	desc:	"<?php echo get_post_excerpt($post); ?>",
	fakeid:	"",
};
(function(){
	var onBridgeReady=function(){
		// 发送给好友; 
		WeixinJSBridge.on('menu:share:appmessage', function(argv){
			WeixinJSBridge.invoke('sendAppMessage',{
				"appid":		dataForWeixin.appId,
				"img_url":		dataForWeixin.img,
				"img_width":	"120",
				"img_height":	"120",
				"link":				dataForWeixin.url,
				"desc":				dataForWeixin.desc,
				"title":			dataForWeixin.title
			}, function(res){<?php do_action('weixin_share','SendAppMessage');?>});
		});
		// 分享到朋友圈;
		WeixinJSBridge.on('menu:share:timeline', function(argv){
			WeixinJSBridge.invoke('shareTimeline',{
			"img_url":dataForWeixin.img,
			"img_width":"120",
			"img_height":"120",
			"link":dataForWeixin.url,
			"desc":dataForWeixin.desc,
			"title":dataForWeixin.title
			}, function(res){<?php do_action('weixin_share','ShareTimeline');?>});
		});
		// 分享到微博;
		WeixinJSBridge.on('menu:share:weibo', function(argv){
			WeixinJSBridge.invoke('shareWeibo',{
			"content":dataForWeixin.title+' '+dataForWeixin.url,
			"url":dataForWeixin.url
			}, function(res){<?php do_action('weixin_share','ShareWeibo');?>});
		});
		// 分享到Facebook
		WeixinJSBridge.on('menu:share:facebook', function(argv){
			WeixinJSBridge.invoke('shareFB',{
			"img_url":dataForWeixin.img,
			"img_width":"120",
			"img_height":"120",
			"link":dataForWeixin.url,
			"desc":dataForWeixin.desc,
			"title":dataForWeixin.title
			}, function(res){<?php do_action('weixin_share','ShareFB');?>});
		});
	};
	if(document.addEventListener){
		document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	}else if(document.attachEvent){
		document.attachEvent('WeixinJSBridgeReady'   , onBridgeReady);
		document.attachEvent('onWeixinJSBridgeReady' , onBridgeReady);
	}
})();
</script>
<?php 
	}
}

function is_weixin(){ // 判断当前用户是否为微信用户
	if ( isset($_SERVER['HTTP_USER_AGENT']) ) {
		if ( strpos($_SERVER['HTTP_USER_AGENT'], 'MicroMessenger') !== false ) {
			return true;
		}
	}
	return false;
}

if(!function_exists('get_post_excerpt')){
    //获取日志摘要
    function get_post_excerpt($post, $excerpt_length=240){
        if(!$post) $post = get_post();

        $post_excerpt = $post->post_excerpt;

        if($post_excerpt == ''){
            $post_content = $post->post_content;

            $post_content = do_shortcode($post_content);

            $post_content = wp_strip_all_tags( $post_content );

            $excerpt_length = apply_filters('excerpt_length', $excerpt_length);     
            $excerpt_more   = apply_filters('excerpt_more', ' ' . '&hellip;');

            $post_excerpt = mb_strimwidth($post_content,0,$excerpt_length,$excerpt_more,'utf-8');
        }

        $post_excerpt = wp_strip_all_tags( $post_excerpt );
        $post_excerpt = trim( preg_replace( "/[\n\r\t ]+/", ' ', $post_excerpt ), ' ' );

        return $post_excerpt;
    }
}

if(!function_exists('get_post_first_image')){
	function get_post_first_image($post_content){
		preg_match_all('|<img.*?src=[\'"](.*?)[\'"].*?>|i', $post_content, $matches);
		if($matches){	 
			return $matches[1][0];
		}else{
			return false;
		}
	}
}

function get_post_weixin_thumb($post,$size){
	$thumbnail_id = get_post_thumbnail_id($post->ID);
	if($thumbnail_id){
		$thumb = wp_get_attachment_image_src($thumbnail_id, $size);
		$thumb = $thumb[0];
	}else{
		$thumb = get_post_first_image($post->post_content);
	}

	if(empty($thumb)){
		$thumb = apply_filters('weixin_default',WEIXIN_DEFAULT);
	}
	
	$thumb = apply_filters('weixin_thumb',$thumb,$size,$post);

	return $thumb;
}

//加强搜索相关性
if(!function_exists('wpjam_search_orderby')){
    add_filter('posts_orderby_request', 'wpjam_search_orderby',10,2);
    function wpjam_search_orderby($orderby,$wp_query){
        global $wpdb;

        if($wp_query->is_search){

            $keyword = stripslashes($wp_query->query_vars['s']);

            $n = !empty($q['exact']) ? '' : '%';

            preg_match_all('/".*?("|$)|((?<=[\r\n\t ",+])|^)[^\r\n\t ",+]+/', $keyword, $matches);
            $search_terms = array_map('_search_terms_tidy', $matches[0]);

            $case_when = "0";

            foreach( (array) $search_terms as $term ){
                $term = esc_sql( like_escape( $term ) );
                $case_when .=" + (CASE WHEN {$wpdb->posts}.post_title LIKE '{$term}' THEN 3 ELSE 0 END) + (CASE WHEN {$wpdb->posts}.post_title LIKE '{$n}{$term}{$n}' THEN 2 ELSE 0 END) + (CASE WHEN {$wpdb->posts}.post_content LIKE '{$n}{$term}{$n}' THEN 1 ELSE 0 END)";
            }

            return "({$case_when}) DESC, {$wpdb->posts}.post_modified DESC";
        }else{
            return $orderby;
        }
    }
}


add_filter('weixin_thumb','wpjam_weixin_thumb_filter',10,3);
function wpjam_weixin_thumb_filter($thumb,$size,$post){
	if(function_exists('wpjam_get_post_thumbnail_src')){
		if(wpjam_has_post_thumbnail()){
			$thumb = wpjam_get_post_thumbnail_src($post, $size);
		}	
	}
	return $thumb;
}

