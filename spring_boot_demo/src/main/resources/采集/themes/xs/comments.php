<?php // Do not delete these lines
	if ('comments.php' == basename($_SERVER['SCRIPT_FILENAME']))
		die ('Please do not load this page directly. Thanks!');
	if (!empty($post->post_password)) { // if there's a password
		if ($_COOKIE['wp-postpass_' . COOKIEHASH] != $post->post_password) {  // and it doesn't match the cookie
			?>
			<p class="nocomments">必须输入密码，才能查看评论！</p>
			<?php
			return;
		}
	}
	/* This variable is for alternating comment background */
	$oddcomment = '';
?>
<!-- You can start editing here. -->
<?php if ($comments) : ?>
<!-- 引用 -->	
<?php
  /* Count the totals */
  $numPingBacks = 0;
  $numComments  = 0;
  /* Loop throught comments to count these totals */
  foreach ($comments as $comment)
    if (get_comment_type() != "comment") $numPingBacks++; else $numComments++;
?>	
	<h3 id="comments">
		<span>
		<?php
			$my_email = get_bloginfo ( 'admin_email' );
			$str = "SELECT COUNT(*) FROM $wpdb->comments WHERE comment_post_ID = $post->ID 
			AND comment_approved = '1' AND comment_type = '' AND comment_author_email";
			$count_t = $post->comment_count;
			$count_v = $wpdb->get_var("$str != '$my_email'");
			$count_h = $wpdb->get_var("$str = '$my_email'");
			echo $count_t, " 条评论";		
		?>
		</span>
      <?php if($numPingBacks>0) { ?>引用:<?php echo ' '.$numPingBacks.'';?><?php } ?>
	</h3>
<ol class="commentlist"><?php wp_list_comments('type=comment&callback=isblog_comment&end-callback=isblog_end_comment'); ?></ol>
<!-- 引用 -->
<?php if($numPingBacks>0) { ?>
	<div class="trackbacks">
		<h4 class="backs">查看来自外部的引用:<?php echo ' '.$numPingBacks.'';?></h4>
		<div class="track">
			<ul >
				<?php foreach ($comments as $comment) : ?>
				<?php $comment_type = get_comment_type(); ?>
				<?php if($comment_type != 'comment') { ?>
				<li><?php comment_author_link() ?></li>
				<?php } ?>
				<?php endforeach; ?>
 			</ul>
		</div>
	</div>
<?php } ?>
	<?php if ( get_comment_pages_count() > 1 && get_option( 'page_comments' ) ) : ?>
	<div class="paginate_comments">
		<?php paginate_comments_links(array('prev_text' => '上一页', 'next_text' => '下一页')); ?>
	</div>
	<?php endif; ?>
 <?php else : // this is displayed if there are no comments so far ?>
	<?php if ('open' == $post->comment_status) : ?>
	<h3 id="comments"><span>我来说一句</span></h3>
		<!-- If comments are open, but there are no comments. -->
	 <?php else : // comments are closed ?>
		<!-- If comments are closed. -->
		<p class="nocomments">抱歉!评论已关闭.</p>
	<?php endif; ?>
<?php endif; ?>
	<?php if ('open' == $post->comment_status) : ?>
	<div id="respond">
		<div class="cancel-comment-reply">
			<?php cancel_comment_reply_link(); ?>		
		</div>
		<?php if ( get_option('comment_registration') && !$user_ID ) : ?>
		<p><?php print '您必须'; ?><a href="<?php echo get_option('siteurl'); ?>/wp-login.php?redirect_to=<?php echo urlencode(get_permalink()); ?>"> [ 登录 ] </a>才能发表留言！</p>
		<?php else : ?>		
		<form action="<?php echo get_option('siteurl'); ?>/wp-comments-post.php" method="post" id="commentform">
		<?php if ( $user_ID ) : ?>		
		<p><?php print '登录者：'; ?><a href="<?php echo get_option('siteurl'); ?>/wp-admin/profile.php"><?php echo $user_identity; ?></a>
			<a href="<?php echo wp_logout_url(get_permalink()); ?>" title="退出"><?php print '[ 退出登录 ]'; ?></a>
		</p>
		<div class="clear"></div>
	<?php endif; ?>
	<?php if ( ! $user_ID ): ?>
	<div id="comment-author-info">
			<input type="text" name="author" id="author" class="commenttext icon" value="<?php echo $comment_author; ?>" placeholder="<?php esc_attr_e( '您的大名', 'Era' ); ?>" size="24" tabindex="1" />
			<label for="author">昵称<?php if ($req) echo "（必填）"; ?></label>
	</div>
      <?php endif; ?>
		<!--<p><small><strong>XHTML:</strong> You can use these tags: <code><?php echo allowed_tags(); ?></code></small></p>-->
		<div class="clear"></div>
		<p class="smile"><?php include(TEMPLATEPATH . '/includes/smiley.php'); ?></p>
		<textarea name="comment" id="comment"  placeholder="<?php _e( '来了，就过来吐吐嘈呗', 'isblog' ); ?>" tabindex="4"></textarea>
		<div class="clear"></div>
		<div class="submitted">
			<input class="submit" name="submit" type="submit" id="submit" tabindex="5" value="我要评论"/>
			<?php comment_id_fields(); ?>
		</div>
		<?php do_action('comment_form', $post->ID); ?>
    </form>
	<div class="clear"></div>
    <?php endif; // If registration required and not logged in ?>
	</div>
 <?php endif; // if you delete this the sky will fall on your head ?>