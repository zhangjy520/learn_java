###### ---
title: redis 作缓存
date: 2017-05-05 13:51:49
tags: java  redis
---

# 需求

  ```
    天气接口，调用限制4000次每天，用户请求次数过多会造成次数上限，使用redis缓存过滤请求
  ```
  
# 实现思路
```
    用户调用----查redis中是否有该城市天气数据存在，若不存在，调用接口并存到redis里面，有效期2小时，若存在2小时的天气信息，返回
```


# redis ubuntu安装启动
```
	wget http://download.redis.io/releases/redis-2.8.9.tar.gz
	
	tar xvfz redis-2.8.9.tar.gz
	
	cd redis-2.8.9
	
	sudo make	
	
	sudo make install
	
	sudo make test
	
	
	启动 脚本文件
	nohup  comman  &  后台启动，关闭终端不关闭
	
	nohup src/redis-server redis.conf &
	
	
	

```


# 实现代码：
```


    @Autowired
    JedisPool jedisPool;
    
    。。。。。
    
public String getWeather(String schoolId) {

        Jedis redis = jedisPool.getResource();

        if (StringUtils.isEmpty(schoolId)) {
            //未传机构id，是定时器查询，调用接口来查询并存入到数据库中
        } else {
            School school = this.selectSchoolById(schoolId);
            String address = school.getXz().split(",")[1];
            //传机构id，是人为发起天气调用，查询缓存中是否有该城市的天气信息
            String res = RedisUtil.find("weather" + address, redis);

            if (StringUtils.isNotEmpty(res)) {
                //缓存中有该城市天气数据，不再调用接口
                return res;
            } else {
                //未查询到天气数据，调用接口查询
                Object queryRes = getApiWeather(address);

                if (GukeerStringUtil.isNullOrEmpty(queryRes))
                    return null;

                String key = "weather" + address;
                try {
                    RedisUtil.add(key, queryRes.toString(), redis);
                    RedisUtil.setExpire(key, 60*120, redis);
                    RedisUtil.returnResource(redis);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return queryRes.toString();
            }
        }
        return null;
    }
    
    
    public static Object getApiWeather(String city) {
        Properties pro = PropertiesUtil.getProperties("db.properties");
        String key = pro.getProperty("weather.key");
        String url = pro.getProperty("weather.url");

        Map param = new HashMap();
        param.put("city", city);
        param.put("key", key);

        String res = null;
        try {
            res = HttpRequestUtil.post(url, null, null, param);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (StringUtils.isEmpty(res))
            return null;

        JSONObject object = JSONObject.fromObject(res);
        JSONArray array = JSONArray.fromObject(object.get("HeWeather5").toString());

        if (array.length() > 0)
            return array.get(0);
        else
            return null;
    }
    
    
    redis配置：
    
    
    
    <?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.2.xsd">

    <!-- 引入jedis配置文件 -->

    <!-- redis连接池的配置 -->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxTotal" value="${redis.maxActive}"/>
        <property name="maxIdle" value="${redis.maxIdle}"/>
        <property name="minIdle" value="${redis.minIdle}"/>
        <property name="testOnBorrow" value="true"/>
        <property name="testOnReturn" value="true"/>
    </bean>

    <!-- redis的连接池pool，不是必选项：timeout/password  -->
    <bean id = "jedisPool" class="redis.clients.jedis.JedisPool">
        <constructor-arg index="0" ref="jedisPoolConfig"/>
        <constructor-arg index="1" value="${redis.host}"/>
        <constructor-arg index="2" value="${redis.port}" type="int"/>
        <constructor-arg index="3" value="${redis.timeout}" type="int"/>
        <constructor-arg index="4" value="${redis.password}"/>
    </bean>

</beans>
    
```