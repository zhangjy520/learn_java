<?php get_header(); ?>
	<div class="browse">现在位置： <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> &gt; 搜索 <?php echo get_search_query(); ?> 结果</div>
	<div id="main">
		<div id="content">
			<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
	                <?php if($x % 2 == 0) { ?>
	                <div class="post_box">
	                <?php } else { ?>
	                <div class="post_box">
	                <?php } ?>
	                    <div class="sico">&gt;</div><a href="<?php the_permalink(); ?>" title="详细阅读 &gt; <?php the_title_attribute(); ?>" rel="bookmark"><h2 class="entry-title"><?php the_title(); ?></h2></a>
	                </div>
	                <!-- post_box -->
	                <?php if($x % 2 == 1) { ?>
	                    <div class="clear"></div>
	                <?php } ?>
	        <?php $x++; ?>
	        <?php endwhile; ?>
	        <?php endif; ?>
	 		<div class="clear"></div>
		</div>
        <!-- content -->
         <div class="load_more_cont">
          	<div class="load_more_text"><?php next_posts_link('查看更多...') ?></div>
         </div>
         <!-- load_more_cont -->
        <?php wp_reset_query(); ?>
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