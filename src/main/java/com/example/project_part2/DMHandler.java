package com.example.project_part2;

import com.example.project_part2.USER.User;
import com.mysql.cj.util.SearchMode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.SQLException;
public class DMHandler {
    DirectMassage directMassage;
    AnchorPane myPane;
    Button OpenChat=new Button();
    ImageView Profile,myBack;
    Label label=new Label();
    User Viewer;
    boolean ForSearch;
    DMHandler(DirectMassage _directMassage, AnchorPane anchorPane,User user,double X,double Y,boolean forSearch){
      ForSearch=forSearch;
        directMassage=_directMassage;
        Viewer=user;
        myPane=anchorPane;
        SetProperties();
        SetX(X);
        SetY(Y);
        ShowProperties();
        OpenChat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("open chat");
                if(forSearch){
                    SearchMethod();
                }
                else {
                OpenChatMethod();}
            }
        });
    }

    void SetProperties(){
        if (directMassage.isGroup){
            label.setText(((Group)directMassage).GroupID);
            myBack=new ImageView(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\GroupBack.png"));            Image image=new Image(((Group)directMassage).ProfilePath);
            image=getRoundedImage(image,200);
            Profile=new ImageView(image);
        }
        else {
            if (!ForSearch){
                directMassage.UserNamesInChat.remove(Viewer.UserName);
                label.setText(directMassage.UserNamesInChat.get(0));
                directMassage.UserNamesInChat.add(Viewer.UserName);
                myBack=new ImageView(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\PVBack.png"));                Image image=new Image(MAINInformation.mainInformation.users.get(label.getText()).profilepicpath);
                image=getRoundedImage(image,200);
                Profile=new ImageView(image);
            }
            else {
                label.setText(directMassage.UserNamesInChat.get(0));
                myBack=new ImageView(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\PVBack.png"));                Image image=new Image(MAINInformation.mainInformation.users.get(label.getText()).profilepicpath);
                image=getRoundedImage(image,200);
                Profile=new ImageView(image);
            }
        }
        OpenChat.setText("      ");
        OpenChat.setFont(Font.font(24));
        OpenChat.setBackground(null);
        myBack.setFitWidth(250);
        myBack.setFitHeight(100);
        Profile.setFitHeight(75);
        Profile.setFitWidth(75);
        label.setFont(Font.font(20));
    }

    void SetX(double X){
        myBack.setTranslateX(X);
        label.setTranslateX(X+100);
        Profile.setTranslateX(X+10);
        OpenChat.setTranslateX(X+5);
    }
    void SetY(double Y){
        myBack.setTranslateY(Y);
        OpenChat.setTranslateY(Y+15);
        Profile.setTranslateY(Y+12);
        label.setTranslateY(OpenChat.getTranslateY()+23);
        Profile.setPreserveRatio(true);
        myBack.setPreserveRatio(true);
    }
    void SearchMethod() {
        if (!directMassage.isGroup){
            try {
                DirectMassage.NewDirectMassage(Viewer.UserName,directMassage.UserNamesInChat.get(0));

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                ((Group)directMassage).addMember(Viewer.UserName);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            Main.ChatAndPvsStart(Viewer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    void ShowProperties(){
        myPane.getChildren().add(myBack);
        myPane.getChildren().add(Profile);
        myPane.getChildren().add(OpenChat);
        myPane.getChildren().add(label);
    }
    void OpenChatMethod(){
        ChatAndPvs.OpenDirectMassage(this.directMassage,Viewer);
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