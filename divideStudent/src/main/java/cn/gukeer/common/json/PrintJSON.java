/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package cn.gukeer.common.json;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintJSON {
	
	
	public static void write(HttpServletResponse response, String content) {
		response.reset();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter pw=response.getWriter();
			pw.write(content);
			pw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
