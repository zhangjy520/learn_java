<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true"%>
<%@ attribute name="url" type="java.lang.String" required="true"%>
<%@ attribute name="label" type="java.lang.String" required="false"%>
<button class="btn btn-white btn-sm" onclick="allCreate()" data-toggle="tooltip" data-placement="top"><i class="fa fa-plus"> ${label==null?'全部生成':label}</i>
                        </button>
<%-- 使用方法： 1.将本tag写在查询的form之前；2.传入table的id和controller的url --%>
<script type="text/javascript">
	function allCreate(){

		  top.layer.confirm('确认将所有未生成账号的职工进行生成账号的操作?', {icon: 3, title:'系统提示'}, function(index){
			window.location = "${url}";
		    top.layer.close(index);
		  });
		 

	}
</script>