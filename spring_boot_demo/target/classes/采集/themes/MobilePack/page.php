<?php get_header(); ?>
	<div class="browse">现在位置： <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> &gt; <?php the_title(); ?></div>
	<div id="main">
		<div id="content">
			<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
			<div class="primary">
					<h2 class="primary-title"><?php the_title(); ?></h2>
					<div class="archive_info">
						<span class="date"><?php the_time('Y年m月d日') ?></span>
						<span class="comment"> &#8260; <?php comments_popup_link('暂无评论', '评论数 1', '评论数 %'); ?></span>
						<?php if(function_exists('the_views')) { print ' &#8260; 被围观 '; the_views(); print '+';  } ?>
						<span class="edit"><?php edit_post_link('编辑', '  ', '  '); ?></span>
					</div>
				<?php the_content('Read more...'); ?>
			</div>
	        <?php endwhile; ?>
	        <?php endif; ?>
	 		<div class="clear"></div>
		</div>
        <!-- content -->
	</div>
    <!-- main-->
<?php comments_template(); ?>
<?php get_footer(); ?>