package com.gdut.service;

import com.gdut.pojo.User;
import com.gdut.util.CRUDUtils;

import java.util.ArrayList;

public class SoftwareService {
    public SoftwareService() {
    }

    //查看网站总资金
    public long checkTotalFunds(){
        String sql1="select enterpriseFund from enterprise";
        String sql2="select personalFund from user";
        long totalFunds=0L;
        for (ArrayList<Object> list : CRUDUtils.query(sql2)) {
            totalFunds=totalFunds+(Integer) list.get(0);
        }
        for (ArrayList<Object> list : CRUDUtils.query(sql1)) {
            totalFunds=totalFunds+(Integer) list.get(0);
        }
        return totalFunds;
    }

    //进行封禁
    public void banned(User user){
        String sql1="select * from user where username=?";
        if (!CRUDUtils.query(sql1,user.getUsername()).isEmpty()){
            String sql2="update user set bannedState=0 where username=?";
            CRUDUtils.update(sql2,user.getUsername());
        }
    }

    //进行封禁
    public void liftTheban(User user){
        String sql1="select * from user where username=?";
        if (!CRUDUtils.query(sql1,user.getUsername()).isEmpty()){
            String sql2="update user set bannedState=1 where username=?";
            CRUDUtils.update(sql2,user.getUsername());
        }
    }

}
