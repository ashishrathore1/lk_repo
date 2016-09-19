package com.lk.credittemplate.emailNotification;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * Created by Anupam on 17-Feb-16.
 */
public class UserEmail implements Principal {

    private String userName;
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return userName;
    }

    public boolean implies(Subject subject) {
        return false;
    }
}
