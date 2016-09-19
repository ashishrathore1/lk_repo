package com.lk.credittemplate.emailNotification;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Anupam on 17-Feb-16.
 */
//@Entity
//@Table(name = "LK_NOTIFICATION_TEMPLATE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MailDetailsBean {
    @JsonProperty
    private String id;

    @JsonProperty
    private String templateId;

    @JsonProperty
   @NotEmpty
    private Set<String> mailTo;

    @JsonProperty
   @NotEmpty
    private String mailFrom;

    @JsonProperty
    private String mailCc;

    @JsonProperty
    private String mailBcc;

    @JsonProperty
   @NotEmpty
    //@Column(name = "subject")
    private String mailSubject;

    @JsonProperty
    private Boolean mailRetryEnabled;

    @JsonProperty
    private String waitTime;

    private Map<String,String> variables;

    public Map<String, String> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, String> variables) {
		this.variables = variables;
	}

	public String getMailCc() {
        return mailCc;
    }

    public void setMailCc(String mailCc) {
        this.mailCc = mailCc;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public void setMailBcc(String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public Boolean getMailRetryEnabled() {
        return mailRetryEnabled;
    }

    public void setMailRetryEnabled(Boolean mailRetryEnabled) {
        this.mailRetryEnabled = mailRetryEnabled;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Set<String> getMailTo() {
        return mailTo;
    }

    public void setMailTo(Set<String> mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }
}
