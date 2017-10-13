<?php get_header(); ?>
<div class="container_12" id="content">
	<div class="grid_12">	
    	<?php if(have_posts()): ?><?php while(have_posts()):the_post(); ?>		
		<div class="post" id="post-<?php the_ID(); ?>">
			<div class="entry">
			<h1 ><?php the_title(); ?></h1>
				<div class="setfont">设置字体大小:<a href="#" class="increaseFont">大</a><a href="#" class="resetFont">中</a><a href="#" class="decreaseFont">小</a></div>
				<?php if ($wp_query->current_post == 0) : ?>
				<?php if (get_option('xs_adt') == 'Hide') { ?>
				<?php { echo ''; } ?>
				<?php } else { include(TEMPLATEPATH . '/includes/ad_t.php'); } ?>
				<?php endif; ?>				
				<?php the_content(); ?>
			</div>
			<!-- 将此标记放在您希望显示like按钮的位置 -->
			<div class="bdlikebutton" style="margin:0 auto !important ;"></div>
				<?php if (get_option('xs_adzwfoot') == 'Hide') { ?>
				<?php { echo ''; } ?>
				<?php } else { include(TEMPLATEPATH . '/includes/ad_zwfoot.php'); } ?>
		</div><!--内容结束-->
			<?php endwhile; ?>
			<div class="clear"></div>
			<div class="pagenavi">
				(快捷键：←)<?php previous_post_link('%link','上一章', TRUE) ?>
				（快捷键：回车）<a href="<?php bloginfo(url) ;?>" title="返回目录" >回目录</a>
				（快捷键：→）<?php next_post_link('%link','下一章',TRUE) ?>
			</div>
			<div class="clear"></div>
			<?php if (get_option('xs_comment') == 'Hide') { ?>
			<?php echo ''; }else{ ?>
			<p class="jcpl">看网友对 <b><?php the_title(); ?></b> 的精彩评论</p>
			<div class="shadow"></div>
			<div class="comments-template grid_8">
				<?php comments_template(); ?>
			</div>
			<div class="grid_4 commentad">
				<?php include(TEMPLATEPATH . '/includes/commentad.php'); ?>
			</div>
			<div class="clear"></div>
			<?php } ;?>
			<?php else: ?>
			<div class="post">
				<h2 ><?php _e('Not Found'); ?> </h2>
			</div>
			<?php endif; ?>   
	</div>
</div>
<div class="clear"></div>
<?php get_footer(); ?>