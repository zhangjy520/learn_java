<div id="tabs">
	<ul class="htotabs">
		<li class="widget1"><a href="#tab-widget1">最新文章</a></li>
		<li class="widget2"><a href="#tab-widget2">随机文章</a></li>
		<div class="clear"></div>
	</ul>
	<div class="tab-inside">
		<ul id="tab-widget1">
			<div class="tab_latest">
				<ul>
					<?php $myposts = get_posts('numberposts=6&offset=0&cat=');foreach($myposts as $post) :?>
					<a href="<?php the_permalink(); ?>" rel="bookmark" title="详细阅读 <?php the_title_attribute(); ?>"><li><?php the_title(); ?></li></a>
					<?php endforeach; ?>
				</ul>
				<div class="clear"></div>
			</div>
		</ul>
		<ul id="tab-widget2">
			<div class="tab_latest">
				<ul>
					<?php $rand_posts = get_posts('numberposts=6&orderby=rand'); foreach( $rand_posts as $post ) : ?>
					<a href="<?php the_permalink(); ?>"><li><?php the_title(); ?></li></a>
					<?php endforeach; ?>
				</ul>
			</div>
  		</ul>
	</div>
	<?php wp_reset_query();?>
</div>

<script type="text/javascript">
	jQuery(document).ready(function(){
		jQuery( '.htotabs').each(function(){
			jQuery(this).children( 'li').children( 'a:first').addClass( 'selected' );
		});
		jQuery( '.tab-inside > *').hide();
		jQuery( '.tab-inside > *:first-child').show();
		jQuery( '.htotabs li a').click(function(evt){
			var clicked_tab_ref = jQuery(this).attr( 'href' );
			jQuery(this).parent().parent().children( 'li').children( 'a').removeClass( 'selected' );
			jQuery(this).addClass( 'selected' );
			jQuery(this).parent().parent().parent().children( '.tab-inside').children( '*').hide();
			jQuery( '.tab-inside ' + clicked_tab_ref).fadeIn(500);
			 evt.preventDefault();
		})
	})
</script>