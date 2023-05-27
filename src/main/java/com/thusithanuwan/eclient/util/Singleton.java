package com.thusithanuwan.eclient.util;

import com.thusithanuwan.eclient.model.EmailMessageBean;

public class Singleton {
    private static Singleton instance;
    private EmailMessageBean message;

    private Singleton(){
    }

    public EmailMessageBean getMessage() {
        return message;
    }
    public void setMessage(EmailMessageBean message){
        this.message = message;
    }


    public static Singleton getInstance() {
        return (instance == null) ? instance = new Singleton() : instance;
    }


}
