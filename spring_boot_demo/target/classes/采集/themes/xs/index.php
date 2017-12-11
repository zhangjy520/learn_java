<?php get_header(); ?>
		<div class="container_12">
			<div class="grid_12 content">
				<?php $display_categories = explode(',', get_option('xs_cat') ); foreach ($display_categories as $category) { ?>
				<?php
					query_posts( array(
						'showposts' =>1,
						'cat' => $category,
						'post__not_in' => $do_not_duplicate
						)
					);
				?>
				<div class="cat_box">
					<h3><?php single_cat_title(); ?></h3>
					<div class="shadow"></div>
					<div class="clear"></div>					
				  		<?php
							query_posts( array(
								'showposts' =>-1,
								'cat' => $category,
								'offset' => 0,
								'order'=>ASC,
								'post__not_in' => $do_not_duplicate
								)
				 			);
						?>
						<ul class="cat_post">
						<?php while (have_posts()) : the_post(); ?>				
							<li><a href="<?php the_permalink() ?>" rel="bookmark" title="<?php the_title(); ?>">
								<?php echo cut_str($post->post_title,60); ?></a></li>				
						<?php endwhile; ?>
						</ul>						
					<?php wp_reset_query(); ?>
				</div>
				<?php } ?>				
			</div>
		</div>
<?php get_footer(); ?>
