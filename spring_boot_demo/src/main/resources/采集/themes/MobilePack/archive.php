<?php get_header(); ?>
	<div class="browse">现在位置： <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> &gt; 
		<?php $post = $posts[0]; ?>
		<?php if (is_category()) { ?><?php echo get_category_parents( get_query_var('cat') , true , ' &gt; ' ); ?>文章
		<?php } elseif( is_tag() ) { ?><?php single_tag_title(); ?>
		<?php } elseif (is_day()) { ?><?php the_time('Y年m月'); ?>发表的文章
		<?php } elseif (is_month()) { ?>所有<?php the_time('Y年m月'); ?>文章
		<?php } elseif (is_year()) { ?>Archive for <?php the_time('Y'); ?>
		<?php } elseif (is_author()) { ?><?php wp_title( '');?>发表的所有文章
		<?php } elseif (isset($_GET['paged']) && !empty($_GET['paged'])) { ?>
		<h1>Blog Archives</h1>
		<?php } ?>
	</div>
	<div id="main">
		<div id="content">
		<?php $posts = query_posts($query_string . '&orderby=date&showposts=16'.'&caller_get_posts=10');?>
			<?php if (have_posts()) : ?>
			<?php while ( have_posts() ) : the_post(); ?>
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