package com.example.project_part2;

import com.example.project_part2.USER.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ChatAndPvs implements Initializable {

    public static DirectMassage MydirectMassage=null;

    public static List<DMHandler> dmHandlers;

    List<ShowMassageHandler> showMassageHandler;
    @FXML
    private TextArea CommentArea;

    @FXML
    private AnchorPane ChatAndPvsAnchor;

    @FXML
    private AnchorPane MassagesAnchor;

    public static User Viewer;
  ///jy,yfyfifig.gli.ho
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    }

    void LoadDms(){
        ChatAndPvsAnchor.getChildren().clear();
        dmHandlers=new ArrayList<>();
        double Y=5;
        for (String i:Viewer.DirectMassageCodes){
            dmHandlers.add(new DMHandler(MAINInformation.mainInformation.directmassages.get(i),ChatAndPvsAnchor,Viewer,5,Y));
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
    //Comment managment;
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