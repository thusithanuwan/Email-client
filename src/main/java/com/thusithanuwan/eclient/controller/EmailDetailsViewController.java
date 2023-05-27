package com.thusithanuwan.eclient.controller;

import com.thusithanuwan.eclient.util.Singleton;
import javafx.scene.control.Label;
import javafx.scene.web.WebView;

public class EmailDetailsViewController {
    public Label lblSubject;
    public Label lblSender;
    public WebView messageRender;

    /*----------------------------------------------------------------------------------------------*/
    private Singleton singleton = Singleton.getInstance();

    /*----------------------------------------------------------------------------------------------*/



    public void initialize() {

        messageRender.getEngine().loadContent(singleton.getMessage().getMessage());
        lblSender.setText("Sender : " + singleton.getMessage().getSender());
        lblSubject.setText("Subject : " + singleton.getMessage().getSubject());

    }














}
