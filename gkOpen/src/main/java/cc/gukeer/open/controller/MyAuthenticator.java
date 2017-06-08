package cc.gukeer.open.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by LL on 2016/12/2.
 */
public class MyAuthenticator extends Authenticator {

    String userName = "";
    String password = "";

    public MyAuthenticator() {

    }

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
