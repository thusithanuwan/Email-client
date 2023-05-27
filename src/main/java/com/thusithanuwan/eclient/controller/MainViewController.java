package com.thusithanuwan.eclient.controller;

import com.thusithanuwan.eclient.model.EmailMessageBean;
import com.thusithanuwan.eclient.model.SampleData;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class MainViewController {

    public ContextMenu contextMenu;

    @FXML
    private TreeView emailFolderTreeView;

    private TreeItem<String> root = new TreeItem<>();


    @FXML
    private TableView<EmailMessageBean> emailTableVIew;

    @FXML
    private WebView messageRender;

    @FXML
    private TableColumn<EmailMessageBean, String> senderCol;

    @FXML
    private TableColumn<EmailMessageBean, String> subjectCol;

    @FXML
    private TableColumn<EmailMessageBean, String> sizeCol;

    SampleData sampleData = new SampleData();

    private MenuItem showDetails = new MenuItem("show details");




    public void initialize() {

        messageRender.getEngine().loadContent("<html>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</html>");

        subjectCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean,String>("subject"));
        senderCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean,String>("sender"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<EmailMessageBean,String>("size"));


/*
         To handle error when sorting the Strings
*/
        sizeCol.setComparator(new Comparator<String>() {

            Integer int1, int2;

            @Override
            public int compare(String o1, String o2) {
                int1 = EmailMessageBean.formattedValues.get(o1);
                int2 = EmailMessageBean.formattedValues.get(o2);
                return int1.compareTo(int2);
            }
        });


/*
         Set root to the TreView
*/
        emailFolderTreeView.setRoot(root);
        root.setValue("example@gmail.com");

        TreeItem<String> inbox = new TreeItem<>("Inbox",resolveIcon("inbox"));
        TreeItem<String> sent = new TreeItem<>("Sent",resolveIcon("sent"));
            TreeItem<String> subItem1 = new TreeItem<>("Subitem1",resolveIcon("subItem"));
            TreeItem<String> subItem2 = new TreeItem<>("Subitem2",resolveIcon("subItem"));
            sent.getChildren().addAll(subItem1,subItem2);
        TreeItem<String> spam = new TreeItem<>("Spam", resolveIcon("spam"));
        TreeItem<String> trash = new TreeItem<>("Trash",resolveIcon(""));

        root.getChildren().addAll(inbox,sent,spam,trash);
        root.setExpanded(true);
        root.setGraphic(resolveIcon("@"));


/*
        add menu item to contextMenu
*/
        contextMenu.getItems().clear();
        contextMenu.getItems().addAll(showDetails);


    }
    private Node resolveIcon(String treeIemValue) {
        String treeItemValue = treeIemValue.toLowerCase();
        ImageView returnIcon;
        try {
            if (treeItemValue.equals("inbox")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/inbox.png")));
            } else if (treeItemValue.equals("sent")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sent2.png")));
            } else if (treeItemValue.equals("spam")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/spam.png")));
            }else if (treeItemValue.equals("subitem")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/sent1.png")));
            }else if (treeItemValue.equals("@")) {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/email.png")));
            }else  {
                returnIcon = new ImageView(new Image(getClass().getResourceAsStream("/images/folder.png")));
            }
        } catch (NullPointerException e) {   // If location of an image wrong we got null point exception
            System.out.println("Invalid Image Location!");  // to handle that we can use try catch block and then catch null point exception
            e.printStackTrace();
            returnIcon = new ImageView();
        }
        returnIcon.setFitWidth(16);
        returnIcon.setFitHeight(16);
        return returnIcon;

    }

/*
     Load item from tree view when we clicked on tree view option
*/
    public void treeViewOnMouseClicked(MouseEvent mouseEvent) {

        TreeItem<String>  selectedItem = (TreeItem<String>) emailFolderTreeView.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            emailTableVIew.setItems(sampleData.emailFolders.get(selectedItem.getValue()));
        }
    }
/*
     Load from email table to messageRender
*/
    public void emailTableViewOnMouseClicked(MouseEvent mouseEvent) {
        EmailMessageBean message = emailTableVIew.getSelectionModel().getSelectedItem();
        if (message != null){
            messageRender.getEngine().loadContent(message.getMessage());
        }
    }


    public void contextMenuSetOnAction(ActionEvent actionEvent) {
        System.out.println((((MenuItem)actionEvent.getTarget()).getText()));
    }
}
