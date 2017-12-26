<?php
header("Content-type: text/html;charset=utf-8");
//set_time_limit(0);

$dbname = SAE_MYSQL_DB;

 $host = SAE_MYSQL_HOST_M;
 $port = SAE_MYSQL_PORT;
 $user = SAE_MYSQL_USER;
 $pwd = SAE_MYSQL_PASS;
 
$connect = @mysql_connect("{$host}:{$port}",$user,$pwd,true);
if(!$connect) {
    die("Connect Server Failed: " . mysql_error());
}

if(!mysql_select_db($dbname,$connect)) {
    die("Select Database Failed: " . mysql_error($connect));
}

mysql_query("set names 'utf8'");

$rules = array(
	'start'		=>	'http://www.douluodalu.com.cn/jueshitangmen/6860.html',	//开始采集的url
	'title'		=>	'/<h1>(.*?)<\/h1>/',	//文章title
    'time'		=>	'/发布时间：(.*?)&nbsp;/',	//发布时间
	'content'	=>	'/\"><\/div><p>([\s\S]*?)<div align=center>/',	//内容
	'next'		=>	'/下一篇: <a href=\"(.*?)\"/',	//下一篇网址
    );


//每次排序，取出上一次的最后一篇url
$url = getLatest();

//最后一章的下一篇为空，由此循环
while($url != null && $url != ""){
    $value = get($url);
	
    $value = _prefilter($value);//去除空白字符，空格，回车
    $context = getContent($value);
	$context['url'] = $url;//当前url,同时还有下一篇的url
    $url = $context['next'];
	var_dump($url);
	//防止重复
		if(storage($context)){
			storageWP($context);
		};	
}
echo "采集结束";
mysql_close($connect);

/*入库*/
function storage($content_array){
	global $connect;
	$sql = "insert into `articles` (`id`, `title`, `time`, `url`, `content`) values(null,
	'{$content_array['title']}',
	'{$content_array['time']}',
	'{$content_array['url']}',
	'{$content_array['content']}');";
	$result = mysql_query($sql,$connect);
	return $result;
}

function storageWP($content_array){
		global $connect;	
	$result =  mysql_query("select max(ID) from wp_posts;",$connect);

	$row = mysql_fetch_row($result);
	$last_id = $row[0] +1 ;
	$sql = "INSERT INTO `wp_posts` (`ID`, `post_author`, `post_date`, `post_date_gmt`, `post_content`, `post_title`, `post_excerpt`, `post_status`, `comment_status`, `ping_status`, `post_password`, `post_name`, `to_ping`, `pinged`, `post_modified`, `post_modified_gmt`, `post_content_filtered`, `post_parent`, `guid`, `menu_order`, `post_type`, `post_mime_type`, `comment_count`) VALUES (null,1,'{$content_array['time']}', '{$content_array['time']}', '{$content_array['content']}', '{$content_array['title']}', '', 'publish', 'open', 'open', '', '{$content_array['title']}', '', '', '{$content_array['time']}', '{$content_array['time']}', '', 0, 'http://iniu.sinaapp.com/?p={$last_id}', 0, 'post', '', 0);";

	$result = mysql_query($sql,$connect);

	$sql = "INSERT INTO `wp_term_relationships` (`object_id`, `term_taxonomy_id`, `term_order`) VALUES({$last_id}, 1, 0);";

	$result = mysql_query($sql,$connect);
	return $result;
}
/*返回内容数组，title，context，time，nexturl*/
function getContent($value){
	global $rules;  
	preg_match($rules['title'],$value, $title);

	preg_match($rules['time'],$value, $time);

	preg_match($rules['next'],$value, $next);

	preg_match($rules['content'],$value, $content);

	$context = array(
	'title' => addslashes($title[1]),
	'time' => $time[1],
	'next' => addslashes($next[1]),
	'content' => addslashes($content[1])
	);
	return $context;
}

/*得到最新的一篇文章记录*/
function getLatest(){
  global $connect;
  global $rules;  
  $sql = "SELECT url FROM  `articles` ORDER BY id DESC LIMIT 1";
  $result = mysql_query($sql,$connect);
  $row=mysql_fetch_row($result);
  
  if($row){  
		return $row[0];  
	}else{  
		return $rules['start'];
	} 
 
}

/*Http Get*/
function get($url){
    $ch = curl_init($url) ;
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true) ;
    curl_setopt($ch, CURLOPT_BINARYTRANSFER, true) ;
    $value = curl_exec($ch) ;
	curl_close($ch);
    return $value;
}

/* 对抓去到的内容做简单过滤（过滤空白字符，便于正则匹配）*/
function _prefilter($output) {
	strip_tags($output);
	$output=preg_replace("/\/\/[\S\f\t\v ]*?;[\r|\n]/", "", $output);
	$output=preg_replace("/\<\!\-\-[\s\S]*?\-\-\>/", "", $output);
	$output=preg_replace("/\>[\s]+\</", "><", $output);
	$output=preg_replace("/;[\s]+/", ";", $output);
	$output=preg_replace("/[\s]+\}/", "}", $output);
	$output=preg_replace("/}[\s]+/", "}", $output);
	$output=preg_replace("/\{[\s]+/", "{", $output);
	$output=preg_replace("/([\s]){2,}/", "$1", $output);
	$output=preg_replace("/[\s]+\=[\s]+/", "=", $output);
	$output=preg_replace("/<br \/>/","",$output);
	$output=preg_replace("/\n/","",$output);
	$output=preg_replace("/  /","",$output);
	return $output;
}


?>