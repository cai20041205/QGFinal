package com.gdut.pojo;

public class PaymentMessage {
    private String username;//支付人
    private String payee;//收款方
    private String paymentAmount;//支付金额
    private String paymentCode;//支付密码

    private String way;
    private String ip;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public PaymentMessage() {
    }

    public PaymentMessage(String username, String payee, String paymentAmount, String paymentCode, String way, String ip) {
        this.username = username;
        this.payee = payee;
        this.paymentAmount = paymentAmount;
        this.paymentCode = paymentCode;
        this.way = way;
        this.ip = ip;
    }
}
