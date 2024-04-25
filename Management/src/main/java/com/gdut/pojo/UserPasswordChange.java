package com.gdut.pojo;

public class UserPasswordChange {
    private String  originalPassword;//原密码
    private String newPassword;//新密码
    private String username;//账号

    public UserPasswordChange() {
    }

    public UserPasswordChange(String originalPassword, String newPassword, String username) {
        this.originalPassword = originalPassword;
        this.newPassword = newPassword;
        this.username = username;
    }

    public String getOriginalPassword() {
        return originalPassword;
    }

    public void setOriginalPassword(String originalPassword) {
        this.originalPassword = originalPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserPasswordChange{" +
                "originalPassword='" + originalPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
