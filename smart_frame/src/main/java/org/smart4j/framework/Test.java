package org.smart4j.framework;

import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.util.HttpRequestUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class Test {
	
	public static void main(String[] args) throws IOException {

		Map param = new HashMap();
		param.put("response_type","code");
		param.put("scope","baseInfo");
		param.put("client_id","K6s8Bsfp");
		param.put("source","app");

		String res = HttpRequestUtil.get("http://localhost:8990/open/authorize",null,param);
		System.out.println(res);
	}
}
