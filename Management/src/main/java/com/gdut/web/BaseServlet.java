package com.gdut.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {

    protected String readRequestBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder requestBody = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        reader.close();
        return requestBody.toString();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许所有域的请求
        resp.setHeader("Access-Control-Allow-Origin", "*");
        // 允许指定方法的请求
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
        // 允许指定的头部字段
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        // 解决中文乱码
        resp.setContentType("text/html;charset=utf-8");
        //获取请求路径
        String requestURI = req.getRequestURI();
        //获取最后一段请求路径
        String methodName=requestURI.split("/")[requestURI.split("/").length-1];
        //获取字节码文件
        Class<? extends BaseServlet> cls = this.getClass();
        try {
            //获取Method对象
            Method method=cls.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            //执行方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
