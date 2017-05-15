package org.smart4j.framework.controller;

import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.service.HelloService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
@Controller
public class HelloServlet{

	@Inject
	private HelloService hs;

	@Action("get:/hello")
	public View test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		hs.eat();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(new Date());
		View view = new View("logina.jsp");
		view.addModel("currentTime",currentTime);
		return view;
	}
	
	
}
