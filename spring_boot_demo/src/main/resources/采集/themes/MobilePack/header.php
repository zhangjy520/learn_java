<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" <?php language_attributes() ?>>
<head>
<meta charset="<?php bloginfo( 'charset' ); ?>" />
<meta name="viewport" content="width=device-width" />
<title>
	<?php
	global $page, $paged;
	wp_title( '|', true, 'right' );
	bloginfo( 'name' );
	$site_description = get_bloginfo( 'description', 'display' );
	if ( $site_description && ( is_home() || is_front_page() ) )
		echo " | $site_description";
	?>
</title>
<link rel="stylesheet" type="text/css" media="all" href="<?php bloginfo( 'stylesheet_url' ); ?>" />
<link rel="stylesheet" href="<?php bloginfo('template_url'); ?>/highlight.css" />
<link rel="pingback" href="<?php bloginfo( 'pingback_url' ); ?>" />
<?php wp_head(); ?>
<script type="text/javascript" src="<?php bloginfo('stylesheet_directory'); ?>/jquery.min.js" ></script>
<?php if ( is_home() || is_archive() || is_search()) { ?>
<script type="text/javascript">
	$('.load_more_cont a').live('click', function(e) {
		e.preventDefault();
	        $('.load_more_text a').html('加载中...');
			$.ajax({
			type: "GET",
			url: $(this).attr('href') + '#main',
			dataType: "html",
			success: function(out) {
				result = $(out).find('#content .post_box');
				nextlink = $(out).find('.load_more_cont a').attr('href');
	                    $("#content").append(result.fadeIn(500));
	                    $('.load_more_text a').html('查看更多...');
				if (nextlink != undefined) {
					$('.load_more_cont a').attr('href', nextlink);
				} else {
					$('.load_more_cont').remove();
	                $('#content').append('<div class="clear"></div>');
				}
			}
		});
	});
</script>
<?php } ?>
<script type="text/javascript">
$(function(){
    $(".menu").bind("click",function(){
	    var $content = $(this).next(".menu-main");
	    if($content.is(":visible")){
			$content.hide();
		}else{
			$content.show();
		}
	})
})
</script>
</head>
<body class="custom-background">
<div id="wrapper">
	<div id="header">
		<h1><a href="<?php echo get_option('home'); ?>/"><span class="blog-name"><?php bloginfo('name'); ?></span><span  class="blog-title"><?php bloginfo('description'); ?></span ></a></h1>
		<span class="menu">&equiv;</span>
		<div class="menu-main">
			<ul><?php wp_list_cats('show_count=0&list=1&sort_column=name&hierarchical=0&exclude='); ?></ul>
		</div>
	</div>