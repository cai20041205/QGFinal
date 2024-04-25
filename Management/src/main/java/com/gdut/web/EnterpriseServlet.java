package com.gdut.web;

import com.alibaba.fastjson.JSON;
import com.gdut.pojo.Enterprise;
import com.gdut.service.EnterpriseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/enterprise/*")
public class EnterpriseServlet extends BaseServlet {

    private EnterpriseService service=new EnterpriseService();

    //获取所有企业信息
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", service.query());
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //获取某个公司信息
    public void queryOneEnterprise(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enterprise enterprise = JSON.parseObject(readRequestBody(req), Enterprise.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", service.queryOneEnterprise(enterprise));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //修改企业群信息
    public void updateEnterpriseInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enterprise enterprise = JSON.parseObject(readRequestBody(req), Enterprise.class);
        service.updateEnterpriseInformation(enterprise);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", service.queryOneEnterprise(enterprise));
        map.put("msg", "修改成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

}
