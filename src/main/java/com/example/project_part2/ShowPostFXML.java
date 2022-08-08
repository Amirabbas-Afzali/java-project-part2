package com.example.project_part2;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.UserTableDBC;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.Post;
import com.example.project_part2.USER.User;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ShowPostFXML implements Initializable {
   // Image image1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\300033.png");
 //   Image image2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\theme2\\11.jpg");
   // Image image3=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\dark.jpg");

    public void SETThEME(){
        if(CompletePersonalInformation.SetTheme==1){
            anch1.getStylesheets().add(getClass().getResource("Style11a/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==2){
            anch1.getStylesheets().add(getClass().getResource("Style11/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==3){
            anch1.getStylesheets().add(getClass().getResource("StyleDark/dark.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
    }
    @FXML
    AnchorPane anch1;

    public static User Viewer;
    public static Post post;
    public static Massage massage;
    public static int MyBack=0;
    List<ShowMassageHandler> showMassageHandler=new ArrayList<>();
    List<PreShowUser> LikedUsers=new ArrayList<>();
    List<PreShowUser> ViewedUsers=new ArrayList<>();
    boolean PostKind=false;
    @FXML
    private AnchorPane CommentAnchor;

    @FXML
    ImageView bckgr;

    @FXML
    Button Add;
    Button EditCaption=new Button();

    @FXML
    private ScrollPane CommentScroll;

    @FXML
    private DatePicker EndDate;

    @FXML
    Button UpdateButton,RepostButton;

    @FXML
    private AnchorPane LikeAnchor;

    @FXML
    private ScrollPane LikesScroll;

    @FXML
    private Label DescriptionLabel,CaptionLabel;

    @FXML
    private DatePicker StartDate;

    @FXML
    private AnchorPane ViewAnchor;

    @FXML
    private ScrollPane ViewScroll;

    @FXML
    Button EditPost;

    @FXML
    TextArea CommentArea;
    TextArea EditArea=new TextArea();

    ImageView postImage,SendMassage;

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle) {
        SETThEME();
        postImage=new ImageView(new Image(post.photopath));
        postImage.setTranslateX(20);
        postImage.setTranslateY(30);
        postImage.setFitHeight(300);
        postImage.setFitWidth(300);
        postImage.setPreserveRatio(true);
        anch1.getChildren().add(postImage);

        SendMassage=new ImageView(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\SendMassage.png"));
        SendMassage.setTranslateX(850);
        SendMassage.setTranslateY(547);
        SendMassage.setFitHeight(40);
        SendMassage.setFitWidth(40);
        SendMassage.setPreserveRatio(true);
        anch1.getChildren().add(SendMassage);
        Add.setBackground(null);
        if (post.RepostersList.contains(Viewer.UserName)||post.PosterName.equals(Viewer.UserName)){
            RepostButton.setText("Un Repost");
        }
        else {
            RepostButton.setText("Repost");
        }

        EditCaption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EditCaption();
            }
        });
        EditPost.setVisible(post.PosterName.equals(Viewer.UserName));

        PostKind= post.Kind;
        UpdateLikes();
        UpdateComment();
        if (PostKind){
            ((BusinessPost)post).addView(Viewer.UserName);
            UpdateViews();
        }
        else {
            ViewAnchor.setVisible(false);
            ViewScroll.setVisible(false);
        }
        SetLabels();

    }
    void SetLabels(){
        CaptionLabel.setText("Caption : \n"+LineToLines(post.Caption));

        if (post.Kind){
            DescriptionLabel.setText("Description : \n"+((BusinessPost)post).description);
            DescriptionLabel.setTranslateY(CaptionLabel.getTranslateY()+(double)(CaptionLabel.getText().length()/45)*17+40);
        }
        else {
            DescriptionLabel.setText("");
        }
    }
    String LineToLines(String _text){
        StringBuilder stringBuilder=new StringBuilder();
        double a=((Math.ceil( (double) _text.length()/45)*17)+5+15);

        while (_text.length()>45){
            stringBuilder.append(_text, 0, 45).append("\n");
            _text=_text.substring(45);
        }
        stringBuilder.append(_text);
        return stringBuilder.toString();
    }
    @FXML
    void UpdatePost(){
        EditArea.setTranslateX(150);
        EditArea.setTranslateY(250);
        EditArea.setMaxWidth(200);
        EditArea.setMaxHeight(100);
        EditCaption.setTranslateY(355);
        EditCaption.setTranslateX(230);
        EditCaption.setText("Edit");
        anch1.getChildren().add(EditCaption);
        anch1.getChildren().add(EditArea);
    }
    void EditCaption(){
        if (EditArea.getText()!=null&&!"".equals(EditArea.getText())){
            try {
                post.setCaption(EditArea.getText().replaceAll("\n"," "));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        anch1.getChildren().remove(EditArea);
        anch1.getChildren().remove(EditCaption);
        SetLabels();
    }
    void UpdateComment(){
        CommentAnchor.getChildren().clear();
        double X=50,Y=5;
        List<Integer> integerList=new ArrayList<>();
        showMassageHandler=new ArrayList<>();
        for (String i :post.CommentsCodesList){
            integerList.add(Integer.parseInt(i));
        }
        Collections.sort(integerList);
        for (Integer i:integerList){
            showMassageHandler.add(new ShowMassageHandler(MAINInformation.mainInformation.massages.get(i.toString()),X,Y,Viewer,new Color(1,1,1,0.99),false));
            showMassageHandler.get(showMassageHandler.size()-1).AddtoRoot(CommentAnchor);
            Y+=(showMassageHandler.get(showMassageHandler.size()-1).getHeight()+10);
        }
        ShowMassageHandler.showMassageHandlers= showMassageHandler;
        CommentAnchor.setPrefHeight(Y+200);
        CommentAnchor.setMaxHeight(Y+200);
    }

    @FXML
    void UpdateLikes(){
        LikeAnchor.getChildren().clear();
        boolean DateImport=false;
        if (StartDate.getValue()!=null&&EndDate.getValue()!=null){
            DateImport=true;
        }
        double X=5,Y=5;
        for (String i:post.LikedList){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle2(DateImport,LocalToDate(StartDate.getValue()),LocalToDate(EndDate.getValue()),false,true)){
                LikedUsers.add(new PreShowUser(MAINInformation.mainInformation.users.get(
                        MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName
                ),X,Y,LikeAnchor));
                Y+=75;
            }
        }
        LikeAnchor.setPrefHeight(Y+50);
        LikeAnchor.setMaxHeight(Y+50);

    }
    @FXML
    void UpdateLikeAndComment(){
        if (post.Kind)
            UpdateViews();
        UpdateLikes();
    }
    void UpdateViews(){
        ViewAnchor.getChildren().clear();
        boolean DateImport=false;
        if (StartDate.getValue()!=null&&EndDate.getValue()!=null){
            DateImport=true;
        }
        double X=5,Y=5;
        for (String i:post.LikedList){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle2(DateImport,LocalToDate(StartDate.getValue()),LocalToDate(EndDate.getValue()),true,true)){
                ViewedUsers.add(new PreShowUser(MAINInformation.mainInformation.users.get(
                        MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName
                ),X,Y,ViewAnchor));
                Y+=75;
            }
        }
        ViewAnchor.setPrefHeight(Y+50);
        ViewAnchor.setMaxHeight(Y+50);
    }
    @FXML
    void Back(){
        if (MyBack==0){
            try {
                PersonalHomepage.USER=Viewer;
                Main.personalpageSTART();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (MyBack==1){

        }
    }
    @FXML
    void AddComment() throws SQLException {
        if (CommentArea.getText()!=null&&!"".equals(CommentArea.getText())){
            post.addcomment(Viewer,CommentArea.getText().replaceAll("\n"," "));
        }
        UpdateComment();
    }
    void AddLike(){

    }
    void deleteLike(){

    }
    public static Date LocalToDate(LocalDate input){
        if (input!=null){
            ZoneId zoneId=ZoneId.systemDefault();
            Date result=Date.from(input.atStartOfDay(zoneId).toInstant());
            return result;
        }
        return new Date();
    }
    @FXML
    void RepostManage () throws SQLException {
        if (post.RepostersList.contains(Viewer.UserName)||post.PosterName.equals(Viewer.UserName)){

            Viewer.PostCodesList.remove(post.PostCode);
            Viewer.posts.remove(post);
            UserTableDBC.userTableDBC.EditorDeleteUser(Viewer,false);
            post.RepostersList.remove(Viewer.UserName);
            PostTableDBC.postTableDBC.EditorDeletePost(post,false);
            RepostButton.setText("Repost");
        }
        else {
            Viewer.addPostToPosts(post);
            post.RepostersList.add(Viewer.UserName);
            PostTableDBC.postTableDBC.EditorDeletePost(post,false);
            RepostButton.setText("Un Repost");
        }
    }
}