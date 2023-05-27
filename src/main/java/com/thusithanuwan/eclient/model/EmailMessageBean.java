package com.thusithanuwan.eclient.model;

import com.sun.source.doctree.SerialTree;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public class EmailMessageBean implements Serializable {

    public static Map<String, Integer> formattedValues = new HashMap<>();  //  use static because every message been can access this

    private SimpleStringProperty sender;
    private SimpleStringProperty subject;
    private SimpleStringProperty size;
    private  String message;
    private boolean flag;

    public EmailMessageBean(String sender, String subject, Integer size , String message, boolean flag) {
        this.sender = new SimpleStringProperty(sender);
        this.subject = new SimpleStringProperty(subject);
        this.size = new SimpleStringProperty(formatSize(size));
        this.message = message;
        this.flag = flag;
    }

    public String getSender() {
        return sender.get();
    }


    public String getSubject() {
        return subject.get();
    }


    public String getSize() {
        return size.get();
    }

    public String getMessage() {
        return message;
    }

    private String formatSize(int size) {
        String returnValue;
        if (size <= 0) {
            returnValue = "0";
        } else if (size < 1024) {
            returnValue = size + " B";
            
        }else if (size < 1048576) {
            returnValue = size/1024 + " kB";

        }else {
            returnValue = size/1048576 + " MB";

        }
        formattedValues.put(returnValue, size);
        return returnValue;
    }


}