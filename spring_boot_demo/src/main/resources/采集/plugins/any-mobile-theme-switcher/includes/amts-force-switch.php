<?php
$mobileThemeText	= get_option('mobile_view_theme_link_text');
$desktopThemeText	= get_option('desktop_view_theme_link_text');	
$desktopSwitchLink	= get_option('show_switch_link_for_desktop');	

if (empty($mobileThemeText)){
	update_option('mobile_view_theme_link_text', 'Switch To Mobile Version');
	$mobileThemeText	= get_option('mobile_view_theme_link_text');
}

if (empty($desktopThemeText)){
	update_option('desktop_view_theme_link_text', 'Switch To Desktop Version');
	$desktopThemeText	= get_option('desktop_view_theme_link_text');	
}

if (empty($desktopSwitchLink)){
	update_option('show_switch_link_for_desktop', 'no');
	$desktopSwitchLink	= get_option('show_switch_link_for_desktop');	
}
?>
<table class="wp-list-table widefat fixed bookmarks">
            <thead>
                <tr>
                    <th>Other Settings (Optional)</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <td>
   
    <table class="form-table">
        <tr valign="top">
        <th scope="row">Switch Mobile Theme Link Text</th>
        <td>
        <input name="mobile_view_theme_link_text" style="width:300px;"  value="<?php echo $mobileThemeText; ?>" type="text" />
        </td>
        </tr>
         
        <tr valign="top">
        <th scope="row">Switch Desktop Theme Link Text</th>
        <td>
        <input name="desktop_view_theme_link_text" style="width:300px;" value="<?php echo $desktopThemeText; ?>" type="text" />
        </td>
        </tr>
        
        <tr valign="top">
        <th scope="row">Do you want to show Switch Mobile Theme link even the vistor is viewing from desktop ?</th>
        <td>
        	<input name="show_switch_link_for_desktop" type="radio" value="yes" <?php echo $desktopSwitchLink == 'yes'?'checked="checked"':''; ?> /> Yes &nbsp;&nbsp;&nbsp;
            <input name="show_switch_link_for_desktop" type="radio" value="no" <?php echo $desktopSwitchLink == 'no'?'checked="checked"':''; ?> /> No <br/><span class="description">Normally, it is <b>NO</b>. It is usually useless to force the visitor to switch to mobile theme when s/he is in desktop.</span>
        </td>
        </tr>
        
       <tr valign="top">
        <th scope="row">&nbsp;</th>
        <td>
        	<input type="submit" class="button-primary" value="<?php _e('Save Changes') ?>" />
        </td>
        </tr>
        
    </table>
    
    <br/>
    
</td></tr></tbody></table>

<br/>