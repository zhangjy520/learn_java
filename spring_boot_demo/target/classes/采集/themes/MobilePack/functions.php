<?php
register_nav_menus( array(
         'header' => '弹出菜单',
) );
// 评论回复
function mytheme_comment($comment, $args, $depth) {
   $GLOBALS['comment'] = $comment;
	global $commentcount;
	if(!$commentcount) {
		$page = get_query_var('cpage')-1;
		$cpp=get_option('comments_per_page');
		$commentcount = $cpp * $page;
	}
    ?>
   <li <?php comment_class(); ?> id="comment-<?php comment_ID() ?>">
   <div id="div-comment-<?php comment_ID() ?>">
      <?php $add_below = 'div-comment'; ?>
		<div class="author_box">
			<span  class="comment-author">
				<strong><?php comment_author_link(); ?></strong>&nbsp;
				<span class="datetime">
					<?php comment_date('Y.m.d') ?>
				</span>
				<span class="reply">
					<?php comment_reply_link(array_merge( $args, array('reply_text' => '回复', 'add_below' =>$add_below, 'depth' => $depth, 'max_depth' => $args['max_depth']))); ?></span>
				</span >
		</div>
		<?php if ( $comment->comment_approved == '0' ) : ?>
		您的评论正在等待审核中...
		<br/>
		<?php endif; ?>
		<?php comment_text() ?>
  </div>
<?php
}
function mytheme_end_comment() {
		echo '</li>';
}
//全部结束
?>