package com.lk.credittemplate.emailNotification;

/**
 * Created by Anupam on 18-Feb-16.
 */


public class MailDetailsModel {

    //@JsonProperty
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    //@JsonProperty
    private String templateId;

    //@JsonProperty
    //@NotEmpty
    private String mailTo;

    //@JsonProperty
    // @NotEmpty
    private String mailFrom;

    //@JsonProperty
    private String mailCc;

    //@JsonProperty
    private String mailBcc;

    //@JsonProperty
    // @NotEmpty
    private String mailSubject;

    //@JsonProperty
    private Boolean mailRetryEnabled;

    //@JsonProperty
    private String waitTime;

    private String variables;

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
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

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailDetailsModel)) return false;

        MailDetailsModel that = (MailDetailsModel) o;

        if (!getId().equals(that.getId())) return false;
        if (!getMailCc().equals(that.getMailCc())) return false;
        if (!getMailBcc().equals(that.getMailBcc())) return false;
        if (!getTemplateId().equals(that.getTemplateId())) return false;
        if (!getMailFrom().equals(that.getMailFrom())) return false;
        if (!getMailSubject().equals(that.getMailSubject())) return false;
        if (!getMailTo().equals(that.getMailTo())) return false;
        if (!getMailRetryEnabled().equals(that.getMailRetryEnabled())) return false;
        if (!getWaitTime().equals(that.getWaitTime())) return false;


        return true;
    }


}
