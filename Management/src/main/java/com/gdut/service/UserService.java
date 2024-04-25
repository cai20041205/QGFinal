package com.gdut.service;

import com.gdut.pojo.PaymentMessage;
import com.gdut.pojo.PaymentPasswordChange;
import com.gdut.pojo.User;
import com.gdut.pojo.UserPasswordChange;
import com.gdut.util.CRUDUtils;

import java.util.ArrayList;

public class UserService {
    public UserService() {
    }

    //添加账号
    public boolean addUser(User user) {
        String sql1 = "SELECT * FROM user WHERE username = ?;";
        if (CRUDUtils.query(sql1, user.getUsername()).isEmpty()) {
            String sql2 = "insert into user ( username, password, creat_time, updata_time,identity,enterprise) VALUES (?,?,now(),now(),?,'无')";
            CRUDUtils.update(sql2, user.getUsername(), user.getPassword(), user.getIdentity());
            return true;
        } else {
            return false;
        }
    }

    //验证登录是否成功
    public boolean verifyUser(User user) {
        String sql = "select password from user where username=?";
        if (CRUDUtils.query(sql, user.getUsername()).isEmpty()) {
            return false;
        } else {
            return CRUDUtils.query(sql, user.getUsername()).get(0).get(0).equals(user.getPassword());
        }
    }

    //根据账号得到用户所有数据
    public User getUserByUsername(String username) {
        String sql = "select location,enterprise,name,identity,useEnterpriseFund from user where username=?";
        ArrayList<Object> list = CRUDUtils.query(sql, username).get(0);
        User user = new User();
        user.setName((String) list.get(2));
        user.setEnterprise((String) list.get(1));
        user.setLocation((String) list.get(0));
        user.setIdentity((String) list.get(3));
        user.setUseEnterpriseFund((Integer) list.get(4));
        user.setUsername(username);
        return user;
    }

    //修改用户信息
    public void updatedUser(User user) {
        String sql = "update user set location=?,name=? where username=?";
        CRUDUtils.update(sql, user.getLocation(), user.getName(), user.getUsername());
    }

    //修改密码
    public boolean changePassword(UserPasswordChange s) {
        String sql = "select password from user where username=?";
        if (CRUDUtils.query(sql, s.getUsername()).get(0).get(0).equals(s.getOriginalPassword())) {
            String sql2 = "update user set password=? where username=?";
            CRUDUtils.update(sql2, s.getNewPassword(), s.getUsername());
            return true;
        }
        return false;
    }

    //退出群组
    public void LeaveEnterprise(User user) {
        String sql1 = "update user set enterprise='无',identity='1' where username=?";
        CRUDUtils.update(sql1, user.getUsername());
        String sql2 = "select NumberOfEnterprise from enterprise where enterpriseName=?";
        String sql3 = "update enterprise set NumberOfEnterprise=? where enterpriseName=?";
        int NumberOfEnterprise = ((Integer) CRUDUtils.query(sql2, user.getEnterprise()).get(0).get(0)) - 1;
        CRUDUtils.update(sql3, NumberOfEnterprise, user.getEnterprise());
    }

    //申请成为群组负责人
    public void applyBecomePrincipal(User user) {
        String sql1 = "select * from applydata where username=?";
        if (CRUDUtils.query(sql1, user.getUsername()).isEmpty()) {
            String sql2 = "insert into applydata (username, creat_time, update_time,enterprise) VALUES (?,now(),now(),?)";
            CRUDUtils.update(sql2, user.getUsername(), user.getEnterprise());
        }
    }

    //建立身份
    public void setIdentity(User user) {
        String sql1 = "select identity from user where username=?";
        user.setIdentity((String) CRUDUtils.query(sql1, user.getUsername()).get(0).get(0));
    }

    //查找消息
    public ArrayList<ArrayList<Object>> lookForInformation(User user) {
        String sql = "select username from applydata where enterprise=?";
        return CRUDUtils.query(sql, user.getEnterprise());
    }

    //同意某人的请求成为企业群负责人
    public void agreeOnRequirement(User user) {
        String sql1 = "delete from applydata where username=? and enterprise=?";
        CRUDUtils.update(sql1, user.getUsername(), user.getEnterprise());
        String sql2 = "update user set identity='2' where username=?";
        CRUDUtils.update(sql2, user.getUsername());
    }

    //获取身份
    public String getIdentity(User user) {
        String sql = "select identity from user where username=?";
        return (String) CRUDUtils.query(sql, user.getUsername()).get(0).get(0);
    }

    //申请加入企业群组
    public void applyToJoinEnterprise(User user) {
        String sql1 = "update user set enterprise=? where username=?";
        CRUDUtils.update(sql1, user.getEnterprise(), user.getUsername());
        String sql2 = "select NumberOfEnterprise from enterprise where enterpriseName=?";
        String sql3 = "update enterprise set NumberOfEnterprise=? where enterpriseName=?";
        int NumberOfEnterprise = ((Integer) CRUDUtils.query(sql2, user.getEnterprise()).get(0).get(0)) + 1;
        CRUDUtils.update(sql3, NumberOfEnterprise, user.getEnterprise());
    }

    //查看个人资金
    public int CheckPersonalFund(User user) {
        String sql = "select personalFund from user where username=?";
        return (Integer) CRUDUtils.query(sql, user.getUsername()).get(0).get(0);
    }

    //查看可以使用企业群组资金的普通用户
    public ArrayList<ArrayList<Object>> ViewWhoCanUseGroupFunds(User user) {
        String sql = "select username from user where identity='1' and enterprise=? and useEnterpriseFund=1";
        return CRUDUtils.query(sql, user.getEnterprise());
    }

    //查看不可以使用企业群组资金的普通用户
    public ArrayList<ArrayList<Object>> ViewWhoCannotUseGroupFunds(User user) {
        String sql = "select username from user where identity='1' and enterprise=? and useEnterpriseFund=0";
        return CRUDUtils.query(sql, user.getEnterprise());
    }

    //撤销用户使用企业群组资金能力
    public void RevokePermission(User user) {
        String sql = "update user set useEnterpriseFund=0 where username=?";
        CRUDUtils.update(sql, user.getUsername());
    }

    //给予用户使用企业群组资金能力
    public void GivePermission(User user) {
        String sql = "update user set useEnterpriseFund=1 where username=?";
        CRUDUtils.update(sql, user.getUsername());
    }

    //修改密码
    public void changePaymentPassword(PaymentPasswordChange paymentPasswordChange) {
        String sql = "update user set paymentPassword=? where username=?";
        CRUDUtils.update(sql, paymentPasswordChange.getPaymentPassword(), paymentPasswordChange.getUsername());
    }

    //建立个人支付信息
    public int payment(PaymentMessage paymentMessage) {
        String sql1 = "select * from user where username=?";
        String sql2 = "select paymentPassword from user where username=?";
        String sql3 = "select personalFund from user where username=?";
        String sql4 = "insert into payment_message (username,payee,paymentAmount,creat_time,updata_time) values (?,?,?,now(),now())";
        String sql5 = "update user set personalFund=? where username=?";
        String sql6 = "select useEnterpriseFund from user where username=?";
        if (!CRUDUtils.query(sql1, paymentMessage.getPayee()).isEmpty() && (Integer) CRUDUtils.query(sql6, paymentMessage.getUsername()).get(0).get(0) == 1) {
            if (CRUDUtils.query(sql2, paymentMessage.getUsername()).get(0).get(0).equals(paymentMessage.getPaymentCode())) {
                if ((Integer) CRUDUtils.query(sql3, paymentMessage.getUsername()).get(0).get(0) > Integer.parseInt(paymentMessage.getPaymentAmount())) {
                    CRUDUtils.update(sql5, (Integer) CRUDUtils.query(sql3, paymentMessage.getUsername()).get(0).get(0) - Integer.parseInt(paymentMessage.getPaymentAmount()), paymentMessage.getUsername());
                    CRUDUtils.update(sql4, paymentMessage.getUsername(), paymentMessage.getPayee(), paymentMessage.getPaymentAmount());
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    //建立企业支付信息
    public int payment2(PaymentMessage paymentMessage) {
        String sql1 = "select * from user where username=?";
        String sql2 = "select paymentPassword from user where username=?";
        String sql3 = "select enterpriseFund from enterprise where enterpriseName=?";
        String sql4 = "insert into payment_message (username,payee,paymentAmount,creat_time,updata_time) values (?,?,?,now(),now())";
        String sql5 = "update enterprise set enterpriseFund=? where enterpriseName=?";
        String sql6 = "select useEnterpriseFund from user where username=?";
        String sql7 = "select enterprise from user where username=?";
        String enterprise = (String) CRUDUtils.query(sql7, paymentMessage.getUsername()).get(0).get(0);
        if (!CRUDUtils.query(sql1, paymentMessage.getPayee()).isEmpty() && (Integer) CRUDUtils.query(sql6, paymentMessage.getUsername()).get(0).get(0) == 1) {
            if (CRUDUtils.query(sql2, paymentMessage.getUsername()).get(0).get(0).equals(paymentMessage.getPaymentCode())) {
                if ((Integer) CRUDUtils.query(sql3, enterprise).get(0).get(0) > Integer.parseInt(paymentMessage.getPaymentAmount())) {
                    CRUDUtils.update(sql5, (Integer) CRUDUtils.query(sql3, enterprise).get(0).get(0) - Integer.parseInt(paymentMessage.getPaymentAmount()), enterprise);
                    CRUDUtils.update(sql4, paymentMessage.getUsername(), paymentMessage.getPayee(), paymentMessage.getPaymentAmount());
                    return 3;
                }
                return 2;
            }
            return 1;
        }
        return 0;
    }

    //获取收款信息
    public ArrayList<ArrayList<Object>> proceeds(User user) {
        String sql = "select username,paymentAmount from payment_message where payee=? and state=1";
        return CRUDUtils.query(sql, user.getUsername());
    }

    //拒收支付
    public void rejection(User user) {
        String sql = "update payment_message set state=0 where username=?";
        CRUDUtils.update(sql, user.getUsername());
    }

    //个人账号收款
    public void usePersonalCollection(User user) {
        String sql1 = "update payment_message set state=2 where payee=?";
        CRUDUtils.update(sql1, user.getUsername());
        String sql2 = "select paymentAmount from payment_message where payee=?";
        int paymentAmount = (Integer) CRUDUtils.query(sql2, user.getUsername()).get(0).get(0);
        String sql3 = "select personalFund from user where username=?";
        int personalFund = (Integer) CRUDUtils.query(sql3, user.getUsername()).get(0).get(0);
        String sql4 = "update user set personalFund=? where username=?";
        CRUDUtils.update(sql4, personalFund + paymentAmount, user.getUsername());
        String sql5 = "delete from payment_message where payee=?";
        CRUDUtils.update(sql5, user.getUsername());
    }

    //企业收款
    public void useEnterpriseCollection(User user) {
        String sql1 = "update payment_message set state=2 where payee=?";
        CRUDUtils.update(sql1, user.getUsername());
        String sql2 = "select paymentAmount from payment_message where payee=?";
        int paymentAmount = (Integer) CRUDUtils.query(sql2, user.getUsername()).get(0).get(0);
        String sql3 = "select enterprise from user where username=?";
        String enterprise = (String) CRUDUtils.query(sql3, user.getUsername()).get(0).get(0);
        String sql4 = "select enterpriseFund from enterprise where enterpriseName=?";
        int enterpriseFund = (Integer) CRUDUtils.query(sql4, enterprise).get(0).get(0);
        String sql5 = "update enterprise set enterpriseFund=? where enterpriseName=?";
        CRUDUtils.update(sql5, enterpriseFund + paymentAmount, enterprise);
        String sql6 = "delete from payment_message where payee=?";
        CRUDUtils.update(sql6, user.getUsername());
    }

    //给企业充值
    public boolean DepositFundsToEnterprise(PaymentMessage paymentMessage) {
        String sql1 = "select enterprise from user where username=?";
        String enterprise = (String) CRUDUtils.query(sql1, paymentMessage.getUsername()).get(0).get(0);
        String sql2 = "select paymentPassword from user where username=?";
        String sql3 = "select personalFund from user where username=?";
        String sql4 = "select enterpriseFund from enterprise where enterpriseName=?";
        String sql5 = "update enterprise set enterpriseFund=? where enterpriseName=?";
        String sql6 = "update user set personalFund=? where username=?";
        if (CRUDUtils.query(sql2, paymentMessage.getUsername()).get(0).get(0).equals(paymentMessage.getPaymentCode())) {
            if ((Integer) CRUDUtils.query(sql3, paymentMessage.getUsername()).get(0).get(0) > Integer.parseInt(paymentMessage.getPaymentAmount())) {
                  CRUDUtils.update(sql6,(Integer) CRUDUtils.query(sql3, paymentMessage.getUsername()).get(0).get(0)-Integer.parseInt(paymentMessage.getPaymentAmount()),paymentMessage.getUsername());
                  CRUDUtils.update(sql5,(Integer) CRUDUtils.query(sql4, enterprise).get(0).get(0)+Integer.parseInt(paymentMessage.getPaymentAmount()),enterprise);
                  return true;
            }
        }
        return false;
    }

    //查看账单
    public ArrayList<ArrayList<Object>> checkPersonalBills(User user){
        String sql1="select payee,paymentAmount from payment_message where state=0 and username=?";
        ArrayList<ArrayList<Object>> query = CRUDUtils.query(sql1, user.getUsername());
        if (!query.isEmpty()) {
            String sql2 = "select personalFund from user where username=?";
            int personalFund=(Integer) CRUDUtils.query(sql2,user.getUsername()).get(0).get(0);
            String sql3="update user set personalFund=? where username=?";
            String sql4="select paymentAmount from payment_message where state=0 and username=?";
            int paymentAmount=(Integer) CRUDUtils.query(sql4,user.getUsername()).get(0).get(0);
            CRUDUtils.update(sql3,paymentAmount+personalFund,user.getUsername());
            String sql5="delete from payment_message where state=0 and username=?";
            CRUDUtils.update(sql5,user.getUsername());
        }
        return query;
    }

    public int getbannedState(User user){
        String sql="select bannedState from user where username=?";
        return (Integer) CRUDUtils.query(sql,user.getUsername()).get(0).get(0);
    }
}
