package com.lk.credittemplate.emailNotification;

import java.sql.Timestamp;

/**
 * Created by Anupam on 18-Feb-16.
 */
public class MailStatus {

    private String id;
    private String template_id;
    private boolean sent_status;

    public boolean isLogin_valid() {
        return login_valid;
    }

    public void setLogin_valid(boolean login_valid) {
        this.login_valid = login_valid;
    }

    private boolean login_valid;
    private Timestamp timestamp;
    private boolean is_retry;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public boolean isSent_status() {
        return sent_status;
    }

    public void setSent_status(boolean sent_status) {
        this.sent_status = sent_status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean getIs_retry() {
        return is_retry;
    }

    public void setIs_retry(boolean is_retry) {
        this.is_retry = is_retry;
    }
}
