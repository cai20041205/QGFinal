package com.gdut.web;

import com.alibaba.fastjson.JSON;
import com.gdut.pojo.PaymentMessage;
import com.gdut.pojo.User;
import com.gdut.pojo.UserPasswordChange;
import com.gdut.pojo.PaymentPasswordChange;
import com.gdut.service.UserService;
import com.gdut.util.SendSmsUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserService();

    //登录
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 允许跨域请求的来源
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //判断是否可以登录
        boolean b = userService.verifyUser(user);
        if (b){
            HttpSession session=req.getSession();
            session.setAttribute("username",user.getUsername());
            userService.setIdentity(user);
        }
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", b ? "用户登录成功" : "用户名或密码错误或身份错误");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();

    }

    //注册
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //判断是否可以注册
        boolean b = userService.addUser(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", b ? "用户注册成功" : "注册名已存在");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //获取用户名
    public void getUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 允许跨域请求的来源
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        // 解决中文乱码
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession(false); // 不创建新会话，如果不存在则返回null
        if (session != null) {
            String username = (String) session.getAttribute("username");
            User userByUsername = userService.getUserByUsername(username);
            session.setAttribute("username","");
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("status", 200);
            map.put("data", userByUsername);
            map.put("msg", "用户数据");
            String jsonString = JSON.toJSONString(map);
            // 将 JSON 响应写入到输出流中
            PrintWriter out = resp.getWriter();
            out.write(jsonString);
            out.flush();
        } else {
            System.out.println("会话已失效或不存在");
        }
    }

    //修改用户数据
    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.updatedUser(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", "修改成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //修改用户密码
    public void updateUserPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserPasswordChange userPasswordChange = JSON.parseObject(readRequestBody(req), UserPasswordChange.class);
        boolean b = userService.changePassword(userPasswordChange);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", userPasswordChange);
        map.put("msg", b?"修改成功":"修改失败");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //退出群组
    public void LeaveEnterprise(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.LeaveEnterprise(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", "退出成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //申请成为群组负责人
    public void applyBecomePrincipal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.applyBecomePrincipal(user);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", "申请成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //查找消息
    public void lookForInformation(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", userService.lookForInformation(user));
        map.put("msg", userService.lookForInformation(user).isEmpty()?"没有消息":"有消息");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //同意请求
    public void agreeOnRequirement(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.agreeOnRequirement(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data", user);
        map.put("msg", "已同意");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //获取身份
    public void getIdentity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.getIdentity(user));
        map.put("msg", "用户身份");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //申请加入企业群组
    public void applyToJoinEnterprise(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.applyToJoinEnterprise(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",user);
        map.put("msg", "申请成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //查看个人资金
    public void CheckPersonalFund(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.CheckPersonalFund(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //查看可以使用企业资金的用户
    public void ViewWhoCanUseGroupFunds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.ViewWhoCanUseGroupFunds(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //查看不可以使用企业资金的用户
    public void ViewWhoCannotUseGroupFunds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.ViewWhoCannotUseGroupFunds(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //撤销用户使用企业群组资金能力
    public void RevokePermission(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.RevokePermission(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",user);
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //给予用户使用企业群组资金能力
    public void GivePermission(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.GivePermission(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",user);
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //设置支付密码
    public void changePaymentPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 允许跨域请求的来源
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        PaymentPasswordChange paymentPasswordChange = JSON.parseObject(readRequestBody(req), PaymentPasswordChange.class);
        HttpSession session=req.getSession();
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        if (session.getAttribute("verificationCode").equals(paymentPasswordChange.getVerificationCode())){
            userService.changePaymentPassword(paymentPasswordChange);
            map.put("msg", "修改成功");
        }else  map.put("msg", "验证码错误");
        map.put("data",paymentPasswordChange);
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();

    }

    //发送验证码
    public void sendVerificationCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 允许跨域请求的来源
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:63342");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        String s = SendSmsUtil.generateVerificationCode();
        HttpSession session=req.getSession();
        session.setAttribute("verificationCode",s);
        try {
            SendSmsUtil.sendSMS(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data","");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //发起个人支付
    public void payment(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PaymentMessage paymentMessage = JSON.parseObject(readRequestBody(req), PaymentMessage.class);
        int result = userService.payment(paymentMessage);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",paymentMessage);
        //响应json字符串
        if (result==0){
            map.put("msg", "收款方不存在或没有收款权限");
        }else if (result==1){
            map.put("msg","支付密码错误");
        }else if (result==2){
            map.put("msg","余额不足");
        }else map.put("msg","发起支付成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //发起企业群组支付
    public void payment2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PaymentMessage paymentMessage = JSON.parseObject(readRequestBody(req), PaymentMessage.class);
        int result = userService.payment2(paymentMessage);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",paymentMessage);
        //响应json字符串
        if (result==0){
            map.put("msg", "收款方不存在或没有收款权限");
        }else if (result==1){
            map.put("msg","支付密码错误");
        }else if (result==2){
            map.put("msg","余额不足");
        }else map.put("msg","发起支付成功");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //需要收款的信息
    public void proceeds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.proceeds(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //退回金额
    public void rejection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.rejection(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data","");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //使用个人账号收款
    public void usePersonalCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.usePersonalCollection(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data","");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //使用企业收款
    public void useEnterpriseCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        userService.useEnterpriseCollection(user);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data","");
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //给企业充值
    public void DepositFundsToEnterprise(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PaymentMessage paymentMessage = JSON.parseObject(readRequestBody(req), PaymentMessage.class);
        boolean b = userService.DepositFundsToEnterprise(paymentMessage);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data","");
        if (b){
            map.put("msg","充值成功");
        }else map.put("msg","支付密码错误或余额不足");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    //查看账单
    public void checkPersonalBills(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.checkPersonalBills(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }

    public void getbannedState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        User user = JSON.parseObject(readRequestBody(req), User.class);
        //响应json字符串
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("data",userService.getbannedState(user));
        map.put("msg", "结果");
        String jsonString = JSON.toJSONString(map);
        // 将 JSON 响应写入到输出流中
        PrintWriter out = resp.getWriter();
        out.write(jsonString);
        out.flush();
    }
}
