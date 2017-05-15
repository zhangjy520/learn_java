package org.smart4j.framework.controller;

import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.util.WriteStreamAppendUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/get/user/info")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html,charset=utf-8");
        String username = new String(req.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");
        String password = new String(req.getParameter("password").getBytes("ISO-8859-1"), "UTF-8");

        String content = "username : " + username + "  password : " + password;
        WriteStreamAppendUtil.method3(ConfigHelper.getValueByKey("file.path"), content);

    }
}
