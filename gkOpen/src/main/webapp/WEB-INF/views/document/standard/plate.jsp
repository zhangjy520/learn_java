<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <meta name="renderer" content="webkit">
</head>
<style>
    p{
        margin: 20px 0 !important;
    }
    h4, h5{
        font-weight: bold;
    }
    span{
        margin-left: 0 !important;
        padding: 0 10px;
    }
</style>
<body>
<!--导航栏-->

<main class="container">
    <div id="document-content">
        <%@include file="../../common/docLeftMenu.jsp"%>
        <section class="col-xs-9">
            <h1 style="margin-bottom:10px;">空间动态模板技术规范</h1>
            <div>
                <h4>第三方自定义开发文档</h4>
                <p>为方便第三方的开发，保证开发过程中页面的完整、美化、及合理性，防止页面中CSS，JS文件和原有的文件发生冲突，特列出以下说明：</p>
            </div>
            <div>
                <h4>一、防止发生冲突注意事项</h4>
                <p>对于自定义模块开发，在最外层必须带上一个有class名的div盒子，这样是为了在对子元素进行自定义的样式时，可以有一个父级倚靠，防止给子元素直接加样式引起和别的页面的样式冲突。</p>
                <p>图示中的标签及样式的写法仅仅只是参考用，和页面实际表现无关。开发者可以根据自己的需求进行适当的修改和添加。但是弹出播放视频事件和预览相册事件是已固定了的。</p>
                <p>对于自定义的模块的样式书写，可以在行间直接写，也可以在用&lt;style&gt;写内嵌，只是在写内嵌时需注意把父级class命带上，但是禁止引及外联CSS文件和JS文件。</p>
            </div>
            <div>
                <h4>二、开发文件管理规范</h4>
                <p>文夹命名规则：用简短有意义的英文或者拼音（不能出现中文）来命名，并需要全部小写。例如（emotions,mail）;</p>
                <p>图片命名规则：第一个单词首写字母小写，之后每个单词首写字母大写，或者全部小写，单词间用下划线接连。例如（btn_sign.gif , bgTipBox.png）;</p>
                <p>JS和CSS的使用：由于开发项目的特殊性，原则上不能使用外联JS和CSS文件(在开发调试时，可以引用“<link href="http://css.huijiaoyun.cn/tianyu_edu_dev/common/css/public.css" rel="stylesheet" type="text/css" />”)，在开发过程中，如需要新增CSS样式，可写行间样式或是根据提供的dome标准添加有限的class名。</p>
            </div>
            <div>
                <h4>三、开发者XHTML书写规范</h4>
                <p>禁止要额外引用JS和CSS文件，如确实需要，可以写在行间和内嵌。(在开发调试时，可以引用“<link href="http://css.huijiaoyun.cn/tianyu_edu_dev/common/css/public.css" rel="stylesheet" type="text/css" />”)；</p>
                <p>编码必须遵循w3c标准,标签、属性及属性命名必须由小写字母及下划线数字组成,且所有标签必须闭合;属性值必须用双引号包括;</p>
                <p>充分利用无兼容性问题的html自身标签,比如span,em,strong,label,等等;需要为html元素添加自定义属性的时候,首先要考虑下有没有默认的已有的合适标签去设置,如果没有,可以使用须以”data-”为前缀来添加自定义属性，避免使用”data:”等其他命名方式;</p>
                <p>语义化html,如标题根据重要性用h1~h6(同一页面只能有一个h1),段落标记用p,列表用ul,内联元素中不可嵌套块级元素;</p>
                <p>尽可能减少div嵌套;</p>
                <p>必须为含有描述性表单元素(input,textarea)添加label;</p>
                <p>能以背景形式呈现的图片,尽量写入CSS样式中;</p>
                <p>图片必须加上alt属性，并且需要使用绝对路径；给重要的元素和截断的元素加上title;</p>
                <p>不是标签一部分的特殊符号都用编码表示:比如<(<)&>(>)&空格()&?(?)等等;</p>
                <p>给区块代码及重要功能(比如循环)加上合理的注释,方便后台添加功能:注释格式,‘–-’只能在注释的始末位置,不能出现2个或2个以上的"--"。</p>
            </div>
            <div>
                <h4>四、Demo文档使用方法</h4>
                <span style="font-weight: bold;">文字大小</span>
                <table>
                    <tr>
                        <td width="27%">字号</td>
                        <td width="46%">引用class名</td>
                        <td width="27%">示例</td>
                    </tr>
                    <tr>
                        <td>12</td>
                        <td>Class:f12</td>
                        <td>12</td>
                    </tr>
                    <tr>
                        <td>14</td>
                        <td>Class:14</td>
                        <td>14</td>
                    </tr>
                    <tr>
                        <td>16</td>
                        <td>Class:16</td>
                        <td>16</td>
                    </tr>
                    <tr>
                        <td>18</td>
                        <td>Class:18</td>
                        <td>18</td>
                    </tr>
                </table>
                <span style="font-weight: bold;">文字颜色</span>
                <table>
                    <tr>
                        <td>使用范围</td>
                        <td>引用class名</td>
                        <td>示例</td>
                    </tr>
                    <tr>
                        <td>超链接普通文字颜色</td>
                        <td>Class:blue</td>
                        <td><span style="background:#3D95D5;color:#fff;">示例</span></td>
                    </tr>
                    <tr>
                        <td>网页，简介，说明等普通文字颜色</td>
                        <td>Class:c555</td>
                        <td><span style="background:#555;color:#fff;">示例</span></td>
                    </tr>
                    <tr>
                        <td>网页标题，正文，重要提示等文字颜色</td>
                        <td>Class:c222</td>
                        <td><span style="background:#222;color:#fff;">示例</span></td>
                    </tr>
                    <tr>
                        <td>特别提示，重要数据，价格等文字颜色</td>
                        <td>Class:red</td>
                        <td><span style="background:#A82929;color:#fff;">示例</span></td>
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