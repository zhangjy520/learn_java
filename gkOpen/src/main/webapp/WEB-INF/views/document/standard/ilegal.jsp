<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>开发者注册</title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <meta name="renderer" content="webkit">
    <!--[if lt IE 9]>
    <script src="${ctx}/static/js/html5shiv.min.js"></script>
    <script src="${ctx}/static/js/respond.min.js"></script>
    <![endif]-->
</head>
<style>
    p{
        margin: 20px 0 !important;
    }
    h4, h5{
        font-weight: bold;
    }
    span{
        margin-left: auto !important;
        font-weight: bold;
        margin-bottom: 15px !important;
    }
    table{
        margin-left: 0 !important;
    }
</style>
<body>
<!--导航栏-->
<%--<div class="container-fluid">
    <nav class="container">
        <a href="home.html"><h3><img src="images/logo-logo.png" alt=""/></h3></a>
        <ul>
            <li><a href="">首页</a></li>
            <li><a href="document.html">开发文档</a></li>
            <li><a href="#">技术支持</a></li>
        </ul>
        &lt;%&ndash;<div>
            <span class="login">登录</span>
            <span class="regist" onclick="window.open('regist.html')">开发者注册</span>
        </div>&ndash;%&gt;
    </nav>
</div>--%>


<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1>违规处理规范</h1>
            <div>
                <h4>一、应用违规总体说明</h4>
                <p>应用违规：指应用在教育云开放平台上线后，未能遵守《教育云开放平台开发者协议》、相关协议或规则及本办法中的条款说明的行为。</p>
                <p>违规分类：应用违规分为技术违规，运营违规，及特殊类违规。</p>
                <p>对于违反本管理办法或相关规则的应用，教育云开放平台有权单方视情节轻重进行处罚，或中止、终止向用户提供服务。包括对单应用及对开发者的处罚。处罚措施包括但不限于如下措施：</p>
                <ul>
                    <li>警告：由教育云开放平台发出电子书面警告通知，并限时整改。</li>
                    <li>停止邀请及新用户开通入口：教育云开放平台停止违规应用在教育云开放平台上所有应用邀请行为，并限制新用户开通人数为0。</li>
                    <li>关闭应用入口：关闭应用入口后，外网用户将完全无法访问应用进行任何操作，包括已安装了应用的用户，只能看到应用维护中的页面。</li>
                    <li>应用下线：教育云开放平台和开发者终止应用在教育云开放平台的运营，并做下线处理。</li>
                    <li>封停OpenAPI访问：封停访问后，应用访问OpenAPI时将会返回失败，所有OpenAPI都无法成功调用。</li>

                </ul>
            </div>
            <div>
                <h4>二、违规行为的定义和定级</h4>
                <p>根据违规的行为归属，在教育云开放平台上运营的应用违规分为两类：技术违规和运营违规。</p>
                <p>下面详细列举了各类违规的定义以及其级别的划分。</p>
                <span>技术违规</span>
                <table>
                    <tr>
                        <td width="20px">违规级别</td>
                        <td width="23%">违规行为</td>
                        <td width="70%">违规内容说明</td>
                    </tr>
                    <tr>
                        <td rowspan="3">一级违规</td>
                        <td>在外网程序中加入webshell</td>
                        <td>webshell常常被称为匿名用户(入侵者)，通过WEB服务端口对WEB服务器有某种程度上操作的权限，由于其大多是以网页脚本的形式出现，也有人称之为网站后门工具。</td>
                    </tr>
                    <tr>
                        <td>利用教育云开放平台漏洞，进行恶意行为；或通过非正常渠道，获取相关资源</td>
                        <td>恶意行为包括但不限于发布违法违规信息，恶意劫持用户信息，捆绑软件安装，欺诈用户等。非正常渠道是指未通过官方指定或认可的方式或渠道</td>
                    </tr>
                    <tr>
                        <td>发生安全入侵类故障</td>
                        <td>由于开发者的应用原因导致应用系统受到入侵，核心用户数据、计费帐务数据等受到入侵，或者系统文件给恶意窜改，引发入侵扩散可能。 应用首页给非法窜改内容、内容涉及危害性大。 由于应用漏洞引起大面积用户讨论及传播</td>
                    </tr>
                    <tr>
                        <td>二级违规</td>
                        <td>应用被发现存在漏洞，可能会导致被植入木马或钓鱼网页</td>
                        <td>在教育云开放平台上接入的应用，必须在第一时间修复相关的漏洞
                        </td>
                    </tr>
                </table>
                <span>运营违规</span>
                <table>
                    <tr>
                        <td width="20px">违规级别</td>
                        <td width="23%">违规行为</td>
                        <td width="70%">违规内容说明</td>
                    </tr>
                    <tr>
                        <td rowspan="8">一级违规</td>
                        <td>严重违反教育云开放平台开发者协议内容，或通过非正常渠道获取资源和利益</td>
                        <td>教育云开放平台开发者协议可见这里，非正常渠道是指未通过官方指定或认可的方式或渠道</td>
                    </tr>
                    <tr>
                        <td>应用中含有告知诱导用户去教育云开放平台以外的其他平台登录注册及使用等其他的相关说明</td>
                        <td>在应用内含有通知及告知用户去教育云开放平台以外的其他平台注册登录及体验使用的说明，教育云开放平台有权根据违规情节的严重程度，执行相关处罚，情节严重者，教育云开放平台有权对违规应用执行下线处理</td>
                    </tr>
                    <tr>
                        <td>应用中涉及不文明信息、色情、政治敏感信息，或任何其他违法、违反公序良俗或社会公德等信息</td>
                        <td>若应用本身涉及不文明信息、色情、政治敏感信息，或其他任何违法、违反公序良俗或社会公德等信息的，浙江教育有权根据违规情节做相应处罚，情节严重的对应用进行下线处理。 若应用中不文明及敏感信息来自用户上传信息，开发者需有自己的排查过滤机制，一旦发现不文明、色情、政治敏感信息，浙江教育有权根据情节做相应处罚</td>
                    </tr>
                    <tr>
                        <td>发生投诉类故障</td>
                        <td>投诉类故障是指：由于开发者原因（如客服容量有限，服务效率过低等），引起大量用户来教育云平台投诉开发者应用的相关问题，从而导致影响教育云平台客服正常运营的情况</td>
                    </tr>
                    <tr>
                        <td>应用客服服务被用户强烈投诉超过3次（强烈投诉是指用户向腾讯方反馈时，情绪激动态度强硬，统计单位：月）</td>
                        <td>投诉范围包括： 应用客服不积极响应用户的咨询或投诉。 故意将咨询或投诉引导至教育云平台客服。 应用客服服务态度恶劣，以及不响应用户咨询或投诉（限工作时间内）</td>
                    </tr>
                    <tr>
                        <td>已上线应用，应用状态有15天异常</td>
                        <td>由于开发者原因，应用连续15天无法正常进入及使用，且应用中未给出任何说明</td>
                    </tr>
                    <tr>
                        <td>应用中含有妨害未成年人身心健康的内容</td>
                        <td>在以未成年人为对象的网络游戏类应用中，含有诱发未成年人模仿违反社会公德的行为和违法犯罪的行为的内容，以及恐怖、残酷等妨害未成年人身心健康的内容</td>
                    </tr>
                    <tr>
                        <td>应用未按照下线流程下线</td>
                        <td>应用未提交下线申请，或未经浙江省教育中心方同意，直接下线应用。 如果应用不需要在开放教育云平台上运营时，开发者需根据教育云开放平台应用下线流程进行下线操作。 未按流程下线的应用对应的开发者将列入开放平台不良信誉开发者名单中，对此名单内的开发者，浙江省教育中心有权拒绝其新应用上线，以及对其旗下其他应用进行封停等处罚</td>
                    </tr>
                    <tr>
                        <td>二级违规</td>
                        <td>已上线应用，应用状态有7天异常</td>
                        <td>由于开发者原因，应用连续7天无法正常进入及使用，且应用中未给出任何说明</td>
                    </tr>
                </table>
            </div>
            <div>
                <h4>三、应用违规处罚</h4>
                <p>对安全风险较大的技术违规，同时将会给予封停违规应用OpenAPI访问的处罚。</p>
                <p>应用如出现以下违规行为，会根据相应违规级别对应用进行封停用户或罚款处理。</p>
                <span>技术违规及运营违规的处罚规则如下：</span>
                <table>
                    <tr>
                        <td rowspan="2" width="20px">违规级别</td>
                        <td rowspan="2" width="20px">违规次数</td>
                        <td colspan="2">修复时限</td>
                        <td rowspan="2" width="27%">处罚项</td>
                        <td rowspan="2" width="27%">备注</td>
                    </tr>
                    <tr>
                        <td width="18%" style="background: #FBFBFB;">技术违规</td>
                        <td width="18%" style="background: #FBFBFB;">运维违规</td>
                    </tr>
                    <tr>
                        <td rowspan="3">一级</td>
                        <td>一次</td>
                        <td rowspan="3">立即修复，不得晚于8小时</td>
                        <td rowspan="3">立即修复，不得晚于24小时</td>
                        <td>关闭API接口3天</td>
                        <td rowspan="3">对于有入侵风险的安全类违规，要求开发者接到违规通知后30分钟内修复</td>
                    </tr>
                    <tr>
                        <td>两次</td>
                        <td>关闭API接口7天</td>
                    </tr>
                    <tr>
                        <td>三次及以上</td>
                        <td>已上线应用，做应用下线处理，停止该应用在教育云开放平台的运营</td>
                    </tr>
                    <tr>
                        <td rowspan="3">二级</td>
                        <td>一次</td>
                        <td rowspan="3">立即修复，不得晚于24小时</td>
                        <td rowspan="3">立即修复，不得晚于48小时</td>
                        <td>关闭应用入口3天</td>
                        <td rowspan="3">对于涉及安全范畴的违规，且同一问题连续两次处罚后均未修复的，则对违规应用执行关闭应用入口的处罚，直到违规应用修复该项违规。若持续30天仍未修复的：对已上线应用，做应用下线处理，停止该应用在腾讯平台的运营</td>
                    </tr>
                    <tr>
                        <td>两次</td>
                        <td>关闭应用入口7天</td>
                    </tr>
                    <tr>
                        <td>三次及以上</td>
                        <td>已上线应用，做应用下线处理，停止该应用在开放平台的运营</td>
                    </tr>
                </table>


            </div>
        </section>
    </div>
</main>

<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>

</body>
</html>