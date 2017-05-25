<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="isStudent" type="java.lang.Boolean" required="false"%>
<button id="generateAccountAll" class="btn btn-white btn-sm" onclick="generateAccountAll(${isStudent})" data-toggle="tooltip" data-placement="top"><i class="fa fa-trash-o"> ${isStudent == 'true'?'生成学生账号':'生成学生家长账号'}</i>
                        </button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
$(document).ready(function() {
    $('#${id} thead tr th input.i-checks').on('ifChecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	  $('#${id} tbody tr td input.i-checks').iCheck('check');
    	});

    $('#${id} thead tr th input.i-checks').on('ifUnchecked', function(event){ //ifCreated 事件应该在插件初始化之前绑定 
    	  $('#${id} tbody tr td input.i-checks').iCheck('uncheck');
    	});
    
});

	function generateAccountAll(isStudent){
		//alert(isStudent);
		// var url = $(this).attr('data-url');
		  var str="";
		  var ids="";
		  var num=0;
		  $("#${id} tbody tr td input.i-checks:checkbox").each(function(){
		    if(true == $(this).is(':checked')){
		      str+=$(this).attr("id")+",";
		      num++;
		    }
		  });
		  if(str.substr(str.length-1)== ','){
		    ids = str.substr(0,str.length-1);
		  }
		  if(ids == ""){
			top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
			return;
		  }
			top.layer.confirm('确认要生成账号吗?', {icon: 3, title:'系统提示'}, function(index){
				var message;	
			$.ajax({
		        type:"GET",
		        url:"${url}?ids="+ids+"&isStudent="+isStudent,
		        datatype: "json",
		        beforeSend: function(){
		        	
		        	if(isStudent){
		        		message = "学生账号";
		        	}else{
		        		message = "学生家长账号";
		        	}
		        	loading("正在生成"+ num +"条"+message+"数据（没有学籍号的不能生成），请稍等...");
		        },
		        success:function(data){
		        	closeTip();
		        	var result = num-data;
		        	if(!top.$.jBox.tip.mess){top.$.jBox.tip.mess=1;top.$.jBox.tip("生成"+data+"条"+message+"数据成功！"+(result==0?"":result+"条数据未生成，可能原因：1已经生成；2没有学籍号"),"success",{persistent:true,opacity:0});$("#messageBox").show(700);}
		        },
		        
		        //调用出错执行的函数
		        error: function(){
		        	closeTip();
		            //请求出错处理
		        	alert("请求失败！");
		        }        
		     });
		    top.layer.close(index);
		});
		 

	}
	
</script>
