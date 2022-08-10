package com.example.project_part2;

import com.example.project_part2.USER.OrdinaryUser;
import com.example.project_part2.USER.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ChatAndPvs implements Initializable {
    public void SETThEME(){
        if(CompletePersonalInformation.SetTheme==1){
            anch1.getStylesheets().add(getClass().getResource("Style5a/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==2){
            anch1.getStylesheets().add(getClass().getResource("Style5/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==3){
            anch1.getStylesheets().add(getClass().getResource("StyleDark/dark.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
    }

    public static DirectMassage MydirectMassage=null;

    public static List<DMHandler> dmHandlers;

    List<ShowMassageHandler> showMassageHandler;
    @FXML
    AnchorPane anch1;

    @FXML
    Button EditButton;

    @FXML
    TextField searchField;

    @FXML
    AnchorPane SearchAnchor;

    @FXML
    private TextArea CommentArea;

    @FXML
    private AnchorPane ChatAndPvsAnchor;

    @FXML
    private AnchorPane MassagesAnchor;

    public static User Viewer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SETThEME();
        LoadDms();
        if (Viewer.DirectMassageCodes.size()>0&&MydirectMassage==null){
            MydirectMassage=MAINInformation.mainInformation.directmassages.get(Viewer.DirectMassageCodes.get(0));
            UpdateComment();
        }
        else {
            if (Viewer.DirectMassageCodes.size()>0){
                UpdateComment();
            }
        }
        if (MydirectMassage!=null){
            if (MydirectMassage.isGroup){
                EditButton.setVisible(true);
                ImageView my= new ImageView(DMHandler.getRoundedImage(new Image(((Group)MydirectMassage).ProfilePath),200));
                my.setTranslateY(30);
                my.setTranslateX(450);
                my.setFitWidth(50);
                my.setFitHeight(50);
                Label L=new Label((((Group) MydirectMassage).GroupID));
                L.setTranslateY(40);
                L.setTranslateX(520);
                L.setFont(new Font(20));
                anch1.getChildren().add(L);
                anch1.getChildren().add(my);
            }
            else {
                EditButton.setVisible(false);
                MydirectMassage.UserNamesInChat.remove(Viewer.UserName);
                ImageView my= new ImageView(DMHandler.getRoundedImage(new Image(MAINInformation.mainInformation.users.get(MydirectMassage.UserNamesInChat.get(0)).profilepicpath),200));
                Label L=new Label(MydirectMassage.UserNamesInChat.get(0));
                MydirectMassage.UserNamesInChat.add(Viewer.UserName);
                my.setTranslateY(30);
                my.setTranslateX(450);
                my.setFitWidth(50);
                my.setFitHeight(50);
                L.setTranslateY(40);
                L.setTranslateX(520);
                L.setFont(new Font(20));
                anch1.getChildren().add(L);
                anch1.getChildren().add(my);
            }
        }
        else {
            EditButton.setVisible(false);
        }
    }

    @FXML
    void SearchGroup(){
        String STR=searchField.getText();
        SearchAnchor.getChildren().clear();
        List<DMHandler> dmHandlers=new ArrayList<>();
        double X=5;
        double Y=5;
        for (User i:MAINInformation.mainInformation.users.values()){
            if (i.UserName.contains(STR)){
                boolean k;
                if (i.Kind){
                    k=true;
                }
                else {
                    k=!((OrdinaryUser)i).Private;
                }
                if (((Viewer.FollowingsList.contains(i.UserName)||k))&&!Viewer.getDMsList().contains(i.UserName)){
                    dmHandlers.add(new DMHandler(new DirectMassage(i.UserName),SearchAnchor,Viewer,X,Y,true));
                    Y+=100;
                }
            }
        }
        for (DirectMassage i:MAINInformation.mainInformation.directmassages.values()){

            if (i.isGroup){
                if (!((Group)i).IsPrivate&&!Viewer.DirectMassageCodes.contains(i.Code)&&((Group) i).GroupID.contains(searchField.getText())){
                    dmHandlers.add(new DMHandler(i,SearchAnchor,Viewer,X,Y,true));
                    Y+=100;
                }
            }
        }
        SearchAnchor.setPrefHeight(Y+50);
    }

    void LoadDms(){
        ChatAndPvsAnchor.getChildren().clear();
        dmHandlers=new ArrayList<>();
        double Y=5;
        for (String i:Viewer.DirectMassageCodes){
            dmHandlers.add(new DMHandler(MAINInformation.mainInformation.directmassages.get(i),ChatAndPvsAnchor,Viewer,5,Y,false));
            Y+=100;
        }
        ChatAndPvsAnchor.setPrefHeight(Y+50);

    }
    public static void OpenDirectMassage(DirectMassage directMassage, User _Viewer){
        MydirectMassage=directMassage;
        Viewer=_Viewer;
        System.out.println("Open Chat");
        try {
            Main.ChatAndPvsStart(Viewer);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    void UpdateComment(){
        MassagesAnchor.getChildren().clear();
        double X=50,Y=5;
        List<Integer> integerList=new ArrayList<>();
        showMassageHandler=new ArrayList<>();
        for (String i :MydirectMassage.MassageCodes){
            integerList.add(Integer.parseInt(i));
        }
        Collections.sort(integerList);
        for (Integer i:integerList){
            showMassageHandler.add(new ShowMassageHandler(MAINInformation.mainInformation.massages.get(i.toString()),X,Y,Viewer,new Color(1,1,1,0.99),false));
            showMassageHandler.get(showMassageHandler.size()-1).AddtoRoot(MassagesAnchor);
            Y+=(showMassageHandler.get(showMassageHandler.size()-1).getHeight()+10);
        }
        ShowMassageHandler.showMassageHandlers= showMassageHandler;
        MassagesAnchor.setPrefHeight(Y+200);
        MassagesAnchor.setMaxHeight(Y+200);
    }

    @FXML
    void AddComment() throws SQLException {
        if (CommentArea.getText()!=null&&!"".equals(CommentArea.getText())){
            MydirectMassage.addMassage(Massage.NewMassage(Viewer.UserName,CommentArea.getText().replaceAll("\n"," ")));
            // post.addcomment(Viewer,CommentArea.getText().replaceAll("\n"," "));
        }
        UpdateComment();
        CommentArea.setText("");
    }

    //----------------------------------------------
    @FXML
    void Back() {
        try {
            MydirectMassage=null;
            Main.personalpageSTART();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void NewGroup(){
        Main.NewGroup(new Group(),true,Viewer);
    }

    @FXML
    void EditeGroup(){
        Main.NewGroup((Group) MydirectMassage,false,Viewer);
    }
}