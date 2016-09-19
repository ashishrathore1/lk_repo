package com.lk.credittemplate.emailNotification;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by Anupam on 22-Feb-16.
 */
public class LK_NOTIFICATION_AUTH {

    @JsonProperty
    private String LNA_AUTH_TOKEN;

    @JsonProperty
    private Timestamp LNA_CREATED_DATE;

    public Timestamp getLNA_CREATED_DATE() {
        return LNA_CREATED_DATE;
    }

    public void setLNA_CREATED_DATE(Timestamp LNA_CREATED_DATE) {
        this.LNA_CREATED_DATE = LNA_CREATED_DATE;
    }

    public String getLNA_AUTH_TOKEN() {
        return LNA_AUTH_TOKEN;
    }

    public void setLNA_AUTH_TOKEN(String LNA_AUTH_TOKEN) {
        this.LNA_AUTH_TOKEN = LNA_AUTH_TOKEN;
    }


}
