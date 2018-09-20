title: jq/java下分别合并指定列单元格
author: zjy
date: 2018-09-13 17:58:42
tags:
---
# 需求
	给定表格html字符串。需要对指定的列进行单元格合并操作
    
# html代码
	 <table id="teamtable" width=”450″ border=”1″ cellspacing=”0″ cellpadding=”2″ bordercolor=”#009900″>
        <tbody>
        <tr class='tableHead'>
            <td colspan=3>设备类型</td>
            <td rowspan="2">核心网络机房</td>
            <td rowspan="2">信息中心北机房</td>
            <td rowspan="2">中心站机房</td>
            <td rowspan="2">信息中心南机房</td>
            <td rowspan="2">未入机房</td>
            <td rowspan="2">小计</td>
        </tr>
        <tr class='tableHead'>
            <td>2级分类</td>
            <td>3级分类</td>
            <td>4级分类</td>
        </tr>
        <tr>
            <td>其他计算机设备</td>
            <td></td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>存储设备</td>
            <td>光纤线路</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>存储设备</td>
            <td>其他存储设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>存储设备</td>
            <td>存储用光纤交换机</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>存储设备</td>
            <td>磁带库</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>存储设备</td>
            <td>磁盘阵列</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>VPN设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>入侵检测设备</td>
            <td></td>
            <td>0</td>
            <td>2</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>2</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>上网行为管理设备</td>
            <td>0</td>
            <td>1</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>1</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>代理设备</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>其他安全设备</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>其它安全网关</td>
            <td>0</td>
            <td>3</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>3</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>准入设备</td>
            <td>1</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>1</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>堡垒机</td>
            <td>0</td>
            <td>1</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>1</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>安全网关</td>
            <td>邮件安全网关</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>密码设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>漏洞扫描设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>计算机终端安全设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>防火墙</td>
            <td>应用防火墙</td>
            <td>0</td>
            <td>7</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>7</td>
        </tr>
        <tr>
            <td>安全设备</td>
            <td>防火墙</td>
            <td>网络防火墙</td>
            <td>0</td>
            <td>4</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>4</td>
        </tr>
        <tr>
            <td>机房辅助设备</td>
            <td>其他机房辅助设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>机房辅助设备</td>
            <td>机房环境监控设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>机房辅助设备</td>
            <td>机柜</td>
            <td></td>
            <td>4</td>
            <td>0</td>
            <td>23</td>
            <td>1</td>
            <td>12</td>
            <td>40</td>
        </tr>
        <tr>
            <td>计算机专用设备</td>
            <td>专用终端</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机专用设备</td>
            <td>其它计算机专用设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机专用设备</td>
            <td>负载均衡设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>专线</td>
            <td>企业</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>专线</td>
            <td>总部</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>交换机</td>
            <td></td>
            <td>4</td>
            <td>55</td>
            <td>13</td>
            <td>0</td>
            <td>8</td>
            <td>80</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>终端接入设备</td>
            <td>上网卡</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>终端接入设备</td>
            <td>其它终端接入设备</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机网络设备</td>
            <td>路由器</td>
            <td></td>
            <td>2</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>2</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>PC服务器</td>
            <td></td>
            <td>0</td>
            <td>91</td>
            <td>124</td>
            <td>0</td>
            <td>49</td>
            <td>264</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>便携式计算机</td>
            <td>其它便携式计算机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>便携式计算机</td>
            <td>平板电脑</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>便携式计算机</td>
            <td>笔记本电脑</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>其他设备</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>台式机</td>
            <td>一体机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>台式机</td>
            <td>专用台式机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>台式机</td>
            <td>其它台式机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>台式机</td>
            <td>普通台式机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>台式机</td>
            <td>瘦客户机</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>计算机设备</td>
            <td>小型机</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>输入输出设备</td>
            <td>打印机</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td>输入输出设备</td>
            <td>扫描仪</td>
            <td></td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
        </tr>
        <tr>
            <td colspan=3>总计</td>
            <td>11</td>
            <td>164</td>
            <td>160</td>
            <td>1</td>
            <td>69</td>
            <td>405</td>
        </tr>
        </tbody>
    </table>
    
# jq合并方法：
		
    jQuery.fn.rowspan = function (colIdx) { //封装的一个JQuery小插件
        return this.each(function () {
            var that;
            $('tr', this).each(function (row) {
                $('td:eq(' + colIdx + ')', this).filter(':visible').each(function (col) {
                    if (that != null && $(this).html() == $(that).html()) {
                        rowspan = $(that).attr("rowSpan");
                        if (rowspan == undefined) {
                            $(that).attr("rowSpan", 1);
                            rowspan = $(that).attr("rowSpan");
                        }
                        rowspan = Number(rowspan) + 1;
                        $(that).attr("rowSpan", rowspan);
                        $(this).hide();
                    } else {
                        that = this;
                    }
                });
            });
        });
    }
    
    调用： 			 $("table").rowspan(0);
                        $("table").rowspan(1);
                        $("table").find("td").each(function () {
                            if ($(this).css('display') == 'none') {
                                $(this).remove()
                            }
                        });
                        
                        
                        
# java合并方法
		
        使用的jar
        	<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
            <dependency>
                <groupId>org.jsoup</groupId>
                <artifactId>jsoup</artifactId>
                <version>1.8.3</version>
            </dependency>
            
            
         java代码：
         
          import com.github.pagehelper.util.StringUtil;
          import org.springframework.util.StringUtils;
          import org.jsoup.nodes.Element;
          import org.jsoup.select.Elements;

          import java.io.Serializable;
          import java.util.ArrayList;
          import java.util.List;
         /**
           * @Author zhangjianyu
           * @Description //传递table,以及列号,会自动合并1-index的列。start=1
           * @Param
           * @return
           **/
          public static Element rowSpan(Element table,int index){
              for (int in = 1; in <=index; in++) {
                  Elements tds = table.select("tr td:nth-child("+in+")");
                  int co=1;
                  for (int i = 0; i < tds.size(); i++) {
                      Element td = tds.get(i);
                      if (i<tds.size()-1){
                          if (td.text().equals(tds.get(i+1).text())){
                              co++;
                          }else{
                              if(StringUtils.isEmpty(tds.get(i+1-co).attr("rowspan"))){
                                  tds.get(i+1-co).attr("rowspan",""+co+"");
                                  co = 1;
                              }
                          }
                      }
                  }
                  for (Element ele : tds) {
                      System.out.println(ele.attr("rowspan"));
                      if (!"noMerge".equals(ele.parent().attr("class"))&&StringUtils.isEmpty(ele.attr("rowspan"))){
                          ele.attr("display","none");
                      }
                  }
              }
              for (Element s : table.select("tr")) {
                  for (Element d:s.select("td")) {
                      if("none".equals(d.attr("display"))){
                          d.remove();
                      }
                  }
              }
              return table;
          }
          
          
          
          
## 效果图前后对比

合并前
![upload successful](/paste/pasted-4.png)

合并后
![upload successful](/paste/pasted-5.png)