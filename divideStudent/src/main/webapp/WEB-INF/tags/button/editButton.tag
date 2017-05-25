<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="width" type="java.lang.String" required="false"%>
<%@ attribute name="height" type="java.lang.String" required="false"%>
<%@ attribute name="target" type="java.lang.String" required="false"%>
<button class="btn" style="background: #1AB394;color: #ffffff" onclick="moveTo()" data-toggle="tooltip" data-placement="top">
	移动到
</button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
function moveTo(){
	// var url = $(this).attr('data-url');
	  var str="";
	  var ids="";
	  $("#${id} tbody tr td input.i-checks:checkbox").each(function(){
	    if(true == $(this).is(':checked')){
	      str+=$(this).attr("id")+",";
	    }
	  });
	  if(str.substr(str.length-1)== ','){
	    ids = str.substr(0,str.length-1);
	  }
	  if(ids == ""){
		top.layer.alert('请至少选择一条数据!', {icon: 0, title:'警告'});
		return;
	  }
	  openDialog("确定要移动选中的记录吗?","${url}&ids="+ids,"${width==null?'800px':width}", "${height==null?'500px':height}","${target}");
	};
</script>