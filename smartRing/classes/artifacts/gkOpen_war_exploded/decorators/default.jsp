<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

/*这里导入了SiteMesh的标签库 */
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%--OK,there is a decorator begin!<hr />--%>
    /*这里的意思是，被装饰页的title内容将会在这里插入 */
    <decorator:title></decorator:title>

</head>
<body>
<%@ include file="/decorators/header.jsp"%>
<div id="content">
    <sitemesh:body/>
</div>
<%@ include file="/decorators/footer.jsp"%>
</body>
</html>