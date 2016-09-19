package com.lk.credittemplate.emailNotification;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by Anupam on 18-Feb-16.
 */
public class LoginResultBean {

    @JsonProperty
    private boolean status;
    @JsonProperty
    private String session_auth_token;
    @JsonProperty
    private Timestamp expiryTime;
    @JsonProperty
    private String msg;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSession_auth_token() {
        return session_auth_token;
    }

    public void setSession_auth_token(String session_auth_token) {
        this.session_auth_token = session_auth_token;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Timestamp expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
