package com.gdut.pojo;

public class PaymentPasswordChange {
    private String paymentPassword;//支付密码

    private String verificationCode;//验证码

    private String username;//账号

    public PaymentPasswordChange(String paymentPassword, String verificationCode, String username) {
        this.paymentPassword = paymentPassword;
        this.verificationCode = verificationCode;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public PaymentPasswordChange() {
    }


}
