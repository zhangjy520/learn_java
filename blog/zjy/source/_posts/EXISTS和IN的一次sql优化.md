title: EXISTS和IN的一次sql优化
author: zjy
date: 2018-11-22 16:33:30
tags:
---
# 场景

导入配置项，发现触发了如下方法，让我本地很卡，mysql cpu高占用。下面开始分析这个方法：
    
![upload successful](/paste/pasted-18.png)


卡在这，红色选中方法

![upload successful](/paste/pasted-19.png)


对应sql：

      <select id="getRelationSubFieldsByCI" resultMap="BaseResultMap">
              select
              <include refid="Base_Column_List" />
              from (
              SELECT
              <include refid="Base_Column_List" />
              FROM CM_FieldTab WHERE relationSubProperty =
              #{fieldCode,jdbcType=VARCHAR}) temp
              WHERE EXISTS ( SELECT
              <include refid="Base_Column_List" />
              FROM CM_FieldTab XTab WHERE XTab.code = temp.relationProperty
              AND XTab.dataType = #{dataType,jdbcType=VARCHAR}
              <if test="dataSources !=null and dataSources.size>0">
                  and XTab.dataSource in
                  <foreach collection="dataSources" item="item" open="("
                      separator="," close=")">
                      #{item}
                  </foreach>
              </if>
              )
          </select>

执行实例：
	
        SELECT		 
            id, CODE, NAME, aliasName, parentId, fieldTypeId, assetTypeId,
            defaultValue, inputType,
            dataType, dataSource, maxLength, `condition`, `require`, editable, `unique`, inlay,
            sysDefault,
            visible, sortNum, style, enableFlag, relationId, canReadUserGroups,
            canWriteUserGroups,
            cascadeParentId, cascadeType, cascadeFieldId, relationPoint, normalQueryCondition,
            muiltShowNum, fuzzyQuery, uniqueTypeId, relationFlag,
            relationProperty, relationSubProperty,
            editPropertyFlag, syncPropertyFlag, pointX, pointY, memo, createUser, createDate,
            lastUpdateUser, lastUpdateDate	 
            FROM (
            SELECT		 
            id, CODE, NAME, aliasName, parentId, fieldTypeId, assetTypeId,
            defaultValue, inputType,
            dataType, dataSource, maxLength, `condition`, `require`, editable, `unique`, inlay,
            sysDefault,
            visible, sortNum, style, enableFlag, relationId, canReadUserGroups,
            canWriteUserGroups,
            cascadeParentId, cascadeType, cascadeFieldId, relationPoint, normalQueryCondition,
            muiltShowNum, fuzzyQuery, uniqueTypeId, relationFlag,
            relationProperty, relationSubProperty,
            editPropertyFlag, syncPropertyFlag, pointX, pointY, memo, createUser, createDate,
            lastUpdateUser, lastUpdateDate

            FROM CM_FieldTab WHERE relationSubProperty = 
            'lastUpdateUser') temp 
            WHERE EXISTS ( SELECT		 
            id, CODE, NAME, aliasName, parentId, fieldTypeId, assetTypeId,
            defaultValue, inputType,
            dataType, dataSource, maxLength, `condition`, `require`, editable, `unique`, inlay,
            sysDefault,
            visible, sortNum, style, enableFlag, relationId, canReadUserGroups,
            canWriteUserGroups,
            cascadeParentId, cascadeType, cascadeFieldId, relationPoint, normalQueryCondition,
            muiltShowNum, fuzzyQuery, uniqueTypeId, relationFlag,
            relationProperty, relationSubProperty,
            editPropertyFlag, syncPropertyFlag, pointX, pointY, memo, createUser, createDate,
            lastUpdateUser, lastUpdateDate

            FROM CM_FieldTab XTab WHERE XTab.code = temp.relationProperty
            AND XTab.dataType = 202		 
                AND XTab.dataSource IN
                 (  
                    'c7a6ce6c85a144afd92bf1581c8223a4b'
                 , 
                    'c2d9117efa42041bc97f1d85ac1249cf0'
                 , 
                    'c0947a4949e3b4699bc176fb38b9e4b88'
                 ) 

            )
            
            
            
            
执行效果：4min22秒的sql被放在了循环体内部。心够大,够强


![upload successful](/paste/pasted-22.png)

简化sql结构：执行1min 2 sec

![upload successful](/paste/pasted-24.png)

分析该sql，好像需求是：自表连接。取code=relationProperty但是id不同的

简化版本1：很慢1min 2 sec

![upload successful](/paste/pasted-25.png)

简化版本2：很慢1min 2 sec

![upload successful](/paste/pasted-26.png)

再度解剖，需求是不是就是查，该表的relationProperty有code关联的数据啊，那我这样写有什么问题吗？耗时：0.050sec

![upload successful](/paste/pasted-27.png)

还原执行实例查询条件：

![upload successful](/paste/pasted-28.png)

对应的xml改为：

![upload successful](/paste/pasted-29.png)

因为EXISTS  相当于一个loop,  循环次数等于 A(左表)的数据行数，所以案例的sql，左边表基本是全量遍历的.


拓展：
# Sql的 EXISTS和IN关键字使用场景

    1. in()适合B表比A表数据小的情况
    2. exists()适合B表比A表数据大的情况
    3. 当A表数据与B表数据一样大时,in与exists效率差不多,可任选一个使用.
    
    
    
  ## in 的逻辑
      select * from A where id in(select id from B)
 以上查询使用了in语句,in()只执行一次,它查出B表中的所有id字段并缓存起来.之后,检查A表的id是否与B表中的id相等,如果相等则将A表的记录加入结果集中,直到遍历完A表的所有记录.
它的查询过程类似于以下过程
      List resultSet=[];
      Array A=(select * from A);
      Array B=(select id from B);

      for(int i=0;i<A.length;i++) {
         for(int j=0;j<B.length;j++) {
            if(A[i].id==B[j].id) {
               resultSet.add(A[i]);
               break;
            }
         }
      }
      return resultSet;
可以看出,当B表数据较大时不适合使用in(),因为它会B表数据全部遍历一次.

如:A表有10000条记录,B表有1000000条记录,那么最多有可能遍历10000*1000000次,效率很差.

再如:A表有10000条记录,B表有100条记录,那么最多有可能遍历10000*100次,遍历次数大大减少,效率大大提升.

结论:in()适合B表比A表数据小的情况


## exists 的逻辑
    select a.* from A a where exists(select 1 from B b where a.id=b.id)
    
 以上查询使用了exists语句,exists()会执行A.length次,它并不缓存exists()结果集,因为exists()结果集的内容并不重要,重要的是结果集中是否有记录,如果有则返回true,没有则返回false.
它的查询过程类似于以下过程
    List resultSet=[];
    Array A=(select * from A)

    for(int i=0;i<A.length;i++) {
       if(exists(A[i].id) {    //执行select 1 from B b where b.id=a.id是否有记录返回
           resultSet.add(A[i]);
       }
    }
    return resultSet;
当B表比A表数据大时适合使用exists(),因为它没有那么遍历操作,只需要再执行一次查询就行.

如:A表有10000条记录,B表有1000000条记录,那么exists()会执行10000次去判断A表中的id是否与B表中的id相等.

如:A表有10000条记录,B表有100000000条记录,那么exists()还是执行10000次,因为它只执行A.length次,可见B表数据越多,越适合exists()发挥效果.

再如:A表有10000条记录,B表有100条记录,那么exists()还是执行10000次,还不如使用in()遍历10000*100次,因为in()是在内存
里遍历比较,而exists()需要查询数据库,我们都知道查询数据库所消耗的性能更高,而内存比较很快.

结论:exists()适合B表比A表数据大的情况

当A表数据与B表数据一样大时,in与exists效率差不多,可任选一个使用.

## 总结
EXISTS(包 括 NOT EXISTS )子句的返回值是一个BOOL值。 EXISTS内部有一个子查询语句(SELECT ... FROM...)， 我将其称为EXIST的内查询语句。其内查询语句返回一个结果集。 EXISTS子句根据其内查询语句的结果集空或者非空，返回一个布尔值。

一种通俗的可以理解为：将外查询表的每一行，代入内查询作为检验，如果内查询返回的结果取非空值，则EXISTS子句返回TRUE，这一行行可作为外查询的结果行，否则不能作为结果。

分 析器会先看语句的第一个词，当它发现第一个词是SELECT关键字的时候，它会跳到FROM关键字，然后通过FROM关键字找到表名并把表装入内存。接着 是找WHERE关键字，如果找不到则返回到SELECT找字段解析，如果找到WHERE，则分析其中的条件，完成后再回到SELECT分析字段。最后形成 一张我们要的虚表。

WHERE关键字后面的是条件表达式。条件表达式计算完成后，会有一个返回值，即非0或0，非0即为真(true)，0即为假(false)。同理WHERE后面的条件也有一个返回值，真或假，来确定接下来执不执行SELECT。

分 析器先找到关键字SELECT，然后跳到FROM关键字将STUDENT表导入内存，并通过指针找到第一条记录，接着找到WHERE关键字计算它的条件表 达式，如果为真那么把这条记录装到一个虚表当中，指针再指向下一条记录。如果为假那么指针直接指向下一条记录，而不进行其它操作。一直检索完整个表，并把 检索出来的虚拟表返回给用户。EXISTS是条件表达式的一部分，它也有一个返回值(true或false)。

EXISTS与IN的使用效率的问题，通常情况下采用exists要比in效率高，因为IN不走索引，但要看实际情况具体使用：

IN适合于外表大而内表小的情况；EXISTS适合于外表小而内表大的情况。
 