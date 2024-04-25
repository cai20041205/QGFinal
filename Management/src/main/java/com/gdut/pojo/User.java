package com.gdut.pojo;

//用户账号信息
public class User {

    private String username;//账号
    private String password;//密码
    private String identity;//身份

    private String name;//昵称
    private String enterprise;//企业群组

    private String location;//地址

    private int personalFund;//个人资金

    private String paymentPassword;//支付密码

    private int useEnterpriseFund;//使用企业资金权限

    public int getUseEnterpriseFund() {
        return useEnterpriseFund;
    }

    public void setUseEnterpriseFund(int useEnterpriseFund) {
        this.useEnterpriseFund = useEnterpriseFund;
    }

    public User(String username, String password, String identity, String name, String enterprise, String location, int personalFund, String paymentPassword, int useEnterpriseFund) {
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.name = name;
        this.enterprise = enterprise;
        this.location = location;
        this.personalFund = personalFund;
        this.paymentPassword = paymentPassword;
        this.useEnterpriseFund = useEnterpriseFund;
    }


    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }


    public int getPersonalFund() {
        return personalFund;
    }

    public void setPersonalFund(int personalFund) {
        this.personalFund = personalFund;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", enterprise='" + enterprise + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
