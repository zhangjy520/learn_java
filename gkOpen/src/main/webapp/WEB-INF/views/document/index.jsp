<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开发文档</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
</head>
<style>
    h4,h5{
        font-weight: bold;
    }
    h5{
        margin-bottom: 8px !important;
    }
    p{
        margin-left: auto !important;
    }
    .detial p{
        margin-bottom: 12px !important;
        color: #525252 !important;
    }
</style>
<body>
<!--导航栏-->
<!--开放文档-->

<main class="container">
    <div id="document-content">
        <%@include file="../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>教育云开放平台概述</h1>
            <div class="detial">
                <h4>一、什么是教育云开放平台</h4>
                <p>教育云开放平台（以下简称“开放平台”）是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。</p>
                <p>开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的平台。</p>
                <p>开放平台拥有庞大而真实的用户资源及用户行为轨迹信息，合作伙伴可通过这些信息，更好的发展自己的产品，并可借助于的优质推广渠道，让更多的用户体验自己的产品，并实现有效的用户群转化。</p>
                <p>开放平台，更高的起点，助您更快成长！</p>
            </div>
            <div class="detial">
                <h4>二、开放平台的优势</h4>
                <img src="${ctx}/static/images/openIndex.png">
                <p>开放平台是以“开放”为核心思想，以“合作共赢”为建设理念的服务平台，其主要目的是为广大应用提供商提供一站式、规范的、标准化的开放能力服务。开放平台将全面整合优质互联网教育资源，为用户带来更多优秀的教育产品，推动教育行业定制、创新、进化，并最终促成新教育体系生态圈的建立，促进教育行业的健康发展。合作伙伴可通过开放平台而接入到全新的平台。</p>
                <img src="${ctx}/static/images/openindextt.png" style="margin: 20px 0;">
                <p>教育云平台、第三方应用之间通过接口和数据的相互调用，实现平台间消息通讯。</p>
                <p>其他平台用户通过页面接口授权登录到智慧教育云，使用智慧教育云的资源、第三方应用。</p>
                <p>其他平台通过智慧教育云接口，实现资源、第三方应用的同步。</p>
            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../common/footer.jsp"%>
</body>
</html>