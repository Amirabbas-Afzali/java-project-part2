package com.example.project_part2;

import com.example.project_part2.USER.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class NewGroup implements Initializable {

    public static Group myGroup;
    public static User Viewer;
    public static boolean New;
    public static List<UserForGroupSet> UsersInChatSet,UserNamesToAdd;

    @FXML
    private Button AddButton;

    @FXML
    Label Warnings;

    @FXML
    private TextArea BioArea;

    @FXML
    private CheckBox CheckPrivate;

    @FXML
    private TextField IDField;

    @FXML
    Pane MainPane;

    @FXML
    private AnchorPane MembersAnchor;

    @FXML
    private TextField SearchField;

    @FXML
    private Button SetButton;

    @FXML
    ImageView prof;

    @FXML
    ScrollPane AddScroll,MemberScroll;

    @FXML
    private AnchorPane UserToAddAnchor;

    @FXML
    void AddMember() {

    }

    @FXML
    void Back() throws SQLException, IOException {
        // TODO: 8/4/2022 the group for edit
        Main.ChatAndPvsStart(Viewer);
    }

    @FXML
    void EditOrCreate() {
        if (New){
            if (Group.IDCheck(IDField.getText().trim())){
                if (profpath!=null){
                    if (BioArea.getText()!=null&&!"".equals(BioArea.getText())&&profpath.length()>2){
                        try {
                            myGroup=new Group(Viewer.UserName,new ArrayList<>() ,IDField.getText().trim(),CheckPrivate.isSelected());
                            myGroup.ProfilePath=profpath;
                            myGroup.setBio(BioArea.getText());
                            New=false;
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        SetEditOptions();
                    }
                    else {
                        //set label
                        Warnings.setText("Enter Bio");
                    }
                }
                else {
                    Warnings.setText("choose photo");
                }
            }
            else {
                //set label
                Warnings.setText("This ID is Already Taken");
            }
        }
        else {
            if (Group.IDCheck(IDField.getText().trim())){
                if (BioArea.getText()!=null&&!"".equals(BioArea.getText())){
                    try {
                        myGroup.GroupID=IDField.getText();
                        myGroup.IsPrivate=CheckPrivate.isSelected();
                        if (profpath.length()>2){
                            myGroup.ProfilePath=profpath;
                        }
                        myGroup.setBio(BioArea.getText());
                        Warnings.setText("Edited");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    //set label
                    Warnings.setText("Enter Bio");
                }
            }
            else {
                //set label
                Warnings.setText("This ID is Already Taken");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (New){
            SetNewOptions();
        }
        else {
            SetEditOptions();
        }
    }
    public void SetEditOptions(){
        Warnings.setText("");
        MemberScroll.setVisible(true);
        AddScroll.setVisible(true);
        AddButton.setVisible(true);
        MembersAnchor.setVisible(true);
        UserToAddAnchor.setVisible(true);
        SearchField.setVisible(true);
        BioArea.setText(myGroup.bio);
        IDField.setText(myGroup.GroupID);
        SetButton.setText("Set");
        prof.setImage(getRoundedImage(new Image(myGroup.ProfilePath),200));
        //Special For Add or ... ---------------------------
        SetGroupMembers();
        SetUsersToAdd();
        //--------------------------------------------------
    }


    public void SetGroupMembers(){
        UsersInChatSet=new ArrayList<>();
        double Y=5;
        for (String i: myGroup.UserNamesInChat){
            UsersInChatSet.add(new UserForGroupSet(
                    MAINInformation.mainInformation.users.get(i),myGroup,Viewer,5,Y,MembersAnchor
            ));
            Y+=75;
        }
        MembersAnchor.setPrefHeight(Y+100);
    }
    @FXML
    public void SetUsersToAdd(){
        UserToAddAnchor.getChildren().clear();
        UserNamesToAdd=new ArrayList<>();
        double Y=5;
        if (SearchField.getText()==null||"".equals(SearchField.getText())){
            for (String i: Viewer.FollowingsList){
                if (!myGroup.UserNamesInChat.contains(i)){
                    UserNamesToAdd.add(new UserForGroupSet(
                            MAINInformation.mainInformation.users.get(i),myGroup,Viewer,5,Y,UserToAddAnchor
                    ));
                    Y+=75;
                }
            }
        }
        else {
            for (User i:MAINInformation.mainInformation.users.values() ){
                if (i.UserName.contains(SearchField.getText())&&!myGroup.UserNamesInChat.contains(i.UserName)){
                    UserNamesToAdd.add(new UserForGroupSet(i,myGroup,Viewer,5,Y,UserToAddAnchor));
                    Y+=75;
                }
            }
        }
        UserToAddAnchor.setPrefHeight(Y+100);
    }
    public void SetNewOptions(){
        Warnings.setText("");
        MemberScroll.setVisible(false);
        AddScroll.setVisible(false);
        AddButton.setVisible(false);
        MembersAnchor.setVisible(false);
        UserToAddAnchor.setVisible(false);
        SearchField.setVisible(false);
        SetButton.setText("Create");
    }





    //Add image...
    public String profpath;
    public void addprof() throws FileNotFoundException {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainstage);
        if (file != null) {
            Image image = new Image(new FileInputStream(file.getPath()));
            prof.setImage(getRoundedImage(image,200));
            profpath=file.getPath();
        }
    }
    public static Image getRoundedImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }

}