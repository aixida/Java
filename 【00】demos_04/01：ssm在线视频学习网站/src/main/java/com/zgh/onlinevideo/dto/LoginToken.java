package com.zgh.onlinevideo.dto;

import cn.hutool.crypto.digest.DigestUtil;
import com.zgh.onlinevideo.domain.User;

public class LoginToken {

    private String ip;
    private String now;
    private String userAgent;
    private User user;
    private String token;

    /**
     * 生成自动登录的凭证
     * @return String
     */
    public String generateToken() {
        // 时间 + 用户(email) + IP + 浏览器信息 = （MD5）
        StringBuilder builder = new StringBuilder();
        builder.append(now);
        builder.append(user.getEmail());
        builder.append(ip);
        builder.append(userAgent);
        // MD5 加盐 增加MD5生成的可靠性
        builder.append("adas154@#$%");

        String md5Hex1 = DigestUtil.md5Hex(builder.toString());
        return md5Hex1;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
