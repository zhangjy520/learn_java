<?php
/*
Template Name: 最近留言
*/
?>
<?php get_header(); ?>
	<div class="browse">现在位置： <a title="返回首页" href="<?php echo get_settings('Home'); ?>/">首页</a> &gt; <?php the_title(); ?></div>
	<div id="main">
		<div id="content">
			<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
			<div class="primary">
				<div class="message">
					<?php
						global $wpdb;
						$sql = "SELECT DISTINCT ID, post_title, post_password, comment_ID, comment_post_ID, comment_author, comment_date_gmt, comment_approved, comment_type,comment_author_url,comment_author_email, SUBSTRING(comment_content,1,30) AS com_excerpt FROM $wpdb->comments LEFT OUTER JOIN $wpdb->posts ON ($wpdb->comments.comment_post_ID = $wpdb->posts.ID) WHERE comment_approved = '1' AND comment_type = '' AND post_password = '' AND user_id='0' ORDER BY comment_date_gmt DESC LIMIT 30";
						$comments = $wpdb->get_results($sql);
						$output = $pre_HTML;
						foreach ($comments as $comment) {$output .= "\n<li><b>".strip_tags($comment->comment_author).": </b>" . " <a href=\"" . get_permalink($comment->ID) ."#comment-" . $comment->comment_ID . "\" title=\"发表在： " .$comment->post_title . "\">" . strip_tags($comment->com_excerpt)."</a></li>";}
						$output .= $post_HTML;
						echo $output;
					?>
				</div>
			</div>
			<?php endwhile; ?>
			<?php endif; ?>
	 		<div class="clear"></div>
		</div>
        <!-- content -->
	</div>
    <!-- main-->
<?php get_footer(); ?>