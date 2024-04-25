package com.gdut.pojo;

//企业实体类
public class Enterprise {
    private String enterpriseName;//企业名
    private int NumberOfEnterprise;//企业人数
    private String EnterpriseWorkDirection;//企业工作方向
    private int state;//企业状态

    private int enterpriseFund;//企业资金

    public Enterprise(String enterpriseName, int numberOfEnterprise, String enterpriseWorkDirection, int state, int enterpriseFund, String newEnterpriserName) {
        this.enterpriseName = enterpriseName;
        NumberOfEnterprise = numberOfEnterprise;
        EnterpriseWorkDirection = enterpriseWorkDirection;
        this.state = state;
        this.enterpriseFund = enterpriseFund;
        this.newEnterpriserName = newEnterpriserName;
    }

    public int getEnterpriseFund() {
        return enterpriseFund;
    }

    public void setEnterpriseFund(int enterpriseFund) {
        this.enterpriseFund = enterpriseFund;
    }

    private String newEnterpriserName;//如果对企业群名字进行修改,修改后的新名字
    public String getNewEnterpriserName() {
        return newEnterpriserName;
    }

    public void setNewEnterpriserName(String newEnterpriserName) {
        this.newEnterpriserName = newEnterpriserName;
    }


    public Enterprise() {
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getNumberOfEnterprise() {
        return NumberOfEnterprise;
    }

    public void setNumberOfEnterprise(int numberOfEnterprise) {
        NumberOfEnterprise = numberOfEnterprise;
    }

    public String getEnterpriseWorkDirection() {
        return EnterpriseWorkDirection;
    }

    public void setEnterpriseWorkDirection(String enterpriseWorkDirection) {
        EnterpriseWorkDirection = enterpriseWorkDirection;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Enterprise{" +
                "enterpriseName='" + enterpriseName + '\'' +
                ", NumberOfEnterprise=" + NumberOfEnterprise +
                ", EnterpriseWorkDirection='" + EnterpriseWorkDirection + '\'' +
                ", state=" + state +
                '}';
    }
}
