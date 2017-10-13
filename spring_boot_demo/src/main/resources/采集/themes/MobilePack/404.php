<?php get_header(); ?>
	<div class="browse">现在位置： <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> &gt; 404</div>
	<div id="main">
		<div id="content">
			<p style="text-align:center; font-size:28px; font-weight:bold;	margin: 50px; ">你迷路了?</p>
	 		<div class="clear"></div>
		</div>
        <!-- content -->
	</div>
    <!-- main-->
    <div class="search">
		<div class="search_h">
			<form id="searchform" method="get" action="<?php bloginfo('home'); ?>">
				<input type="text" value="<?php the_search_query(); ?>" name="s" id="s" size="30" />
				<button type="submit">搜索</button>
			</form>
			<div class="clear"></div>
		</div>
	</div>
   <div class="h-cat">
		<ul><?php wp_list_cats('sort_column=name&hierarchical=0&exclude='); ?></ul>
		<div class="clear"></div>
	</div>
<?php get_footer(); ?>