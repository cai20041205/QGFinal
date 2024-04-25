package com.gdut.service;

import com.gdut.pojo.Enterprise;
import com.gdut.util.CRUDUtils;

import java.util.ArrayList;

public class EnterpriseService {

    public EnterpriseService() {
    }

    //获取所有公开企业
    public ArrayList<ArrayList<Object>> query() {
        String sql1 = "SELECT enterprise.enterpriseName, enterprise.NumberOfEnterprise, enterprise.EnterpriseWorkDirection,enterpriseFund FROM enterprise WHERE state = 1;";
        return CRUDUtils.query(sql1);
    }

    //读取某个公开企业
    public ArrayList<ArrayList<Object>> queryOneEnterprise(Enterprise enterprise) {
        String sql = "select enterpriseName,EnterpriseWorkDirection,state,enterpriseFund from enterprise where enterpriseName=?";
        return CRUDUtils.query(sql, enterprise.getEnterpriseName());
    }

    //修改企业群信息
    public void updateEnterpriseInformation(Enterprise enterprise) {
        String sql = "update enterprise set enterpriseName=?,EnterpriseWorkDirection=?,state=? where enterpriseName=?";
        CRUDUtils.update(sql, enterprise.getNewEnterpriserName(), enterprise.getEnterpriseWorkDirection(), enterprise.getState(), enterprise.getEnterpriseName());
    }

}
