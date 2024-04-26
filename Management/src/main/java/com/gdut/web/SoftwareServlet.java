package com.gdut.web;


import com.alibaba.fastjson.JSON;
import com.gdut.pojo.User;
import com.gdut.service.SoftwareService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/software/*")
public class SoftwareServlet extends BaseServlet{

    SoftwareService service=new SoftwareService();

    //查看网站总余额
    public void checkTotalFunds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long l = service.checkTotalFunds();
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", l);
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //进行封禁
    public void banned(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许跨域请求的来源
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        User user = JSON.parseObject(readRequestBody(req), User.class);
        service.banned(user);
        HttpSession session = req.getSession(false);
        session.setAttribute("username","");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", "");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //进行解封
    public void liftTheban(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        service.liftTheban(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", "");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }
}
