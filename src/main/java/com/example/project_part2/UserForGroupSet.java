package com.example.project_part2;

import com.example.project_part2.USER.User;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.SQLException;

public class UserForGroupSet {
    Circle AdminCircle=new Circle();
    User user,Viewer;
    Label label=new Label();
    ImageView imageView,myBack;
    Button button=new Button();
    AnchorPane myPane;
    Group group;
    Button MoreOption=new Button(),AddOrRemove=new Button(),AdminOrDisAdd=new Button();
    boolean Show=false;
    UserForGroupSet(User _user,Group _group, User viewer,double X, double Y, AnchorPane anchorPane){
        myPane=anchorPane;
        Viewer=viewer;
        group=_group;
        user=_user;
        Image image=new Image(user.profilepicpath);
        image=getRoundedImage(image,200);
        imageView=new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setTranslateX(X+9);
        imageView.setTranslateY(Y+10);
        imageView.setPreserveRatio(true);

        myBack=new ImageView(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\userbox.png"));
        myBack.setTranslateX(X);
        myBack.setTranslateY(Y);
        myBack.setFitWidth(200);
        myBack.setFitHeight(70);
        myBack.setPreserveRatio(true);
        myPane.getChildren().add(myBack);


        button.setTranslateX(imageView.getTranslateX()+5);
        button.setTranslateY(imageView.getTranslateY()+5);
        button.setText("    ");
        button.setBackground(null);

        label.setText(user.UserName);
        label.setFont(Font.font("BOLD"));
        label.setTranslateX(imageView.getTranslateX()+60);
        label.setTranslateY(imageView.getTranslateY()+18);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // MassageOption();

            }
        });

        myPane.getChildren().add(imageView);
        myPane.getChildren().add(button);
        myPane.getChildren().add(label);


        AddOrRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (group.UserNamesInChat.contains(user.UserName)){
                    try {
                        group.removeMember(user.UserName);
                        myPane.getChildren().remove(AdminCircle);
                        Main.NewGroup(group,false,Viewer);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        group.addMember(user.UserName);
                        Main.NewGroup(group,false,Viewer);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Add or remove");
                ShowExtra();
                // TODO: 8/5/2022
            }
        });
        AdminOrDisAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (group.Admins.contains(user.UserName)){
                    try {
                        group.RemoveAdmin(user.UserName);
                        myPane.getChildren().remove(AdminCircle);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        group.AddAdmin(user.UserName);
                        if (!myPane.getChildren().contains(AdminCircle)){
                            AdminCircle.setFill(Color.YELLOW);
                            myPane.getChildren().add(AdminCircle);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                ShowExtra();

            }
        });
        MoreOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetExtra();
                if (!Show) {
                    ShowExtra();
                    Show=true;
                } else {
                    DisShowExtra();
                    Show=false;
                }
            }
        });
        //Group Extra ---------------------------
        MoreOption.setText("Options");
        MoreOption.setTranslateY(imageView.getTranslateY()+12);
        MoreOption.setTranslateX(imageView.getTranslateX()+160);
        myPane.getChildren().add(MoreOption);

        AdminCircle.setRadius(13);
        AdminCircle.setCenterX(imageView.getTranslateX()+127);
        AdminCircle.setCenterY(imageView.getTranslateY()+25);
        if (group.Admins.contains(user.UserName)){
            AdminCircle.setFill(Color.YELLOW);
            myPane.getChildren().add(AdminCircle);
        }
        if (group.Owener.equals(user.UserName)){
            AdminCircle.setFill(Color.PURPLE);
            myPane.getChildren().add(AdminCircle);
        }

        //---------------------------------------

    }
    public void SetExtra(){
        AddOrRemove.setTranslateX(MoreOption.getTranslateX()+60);
        AddOrRemove.setTranslateY(MoreOption.getTranslateY()-15);
        AdminOrDisAdd.setTranslateX(MoreOption.getTranslateX()+60);
        AdminOrDisAdd.setTranslateY(MoreOption.getTranslateY()+15);
    }
    public void ShowExtra(){
        if (group.UserNamesInChat.contains(user.UserName)){
            // TODO: 8/5/2022 change your own remove
            if ((group.Admins.contains(Viewer.UserName))&&(!user.UserName.equals(group.Owener))&&(!group.Admins.contains(user.UserName))||Viewer.UserName.equals(user.UserName)){
                AddOrRemove.setText("Remove");
                if (!myPane.getChildren().contains(AddOrRemove)){
                    myPane.getChildren().add(AddOrRemove);
                }
            }
            if (group.Owener.equals(Viewer.UserName)){
                AddOrRemove.setText("Remove");
                if (!myPane.getChildren().contains(AddOrRemove)){
                    myPane.getChildren().add(AddOrRemove);
                }
                if (group.Admins.contains(user.UserName)){
                    AdminOrDisAdd.setText("Demote");
                }
                else {
                    AdminOrDisAdd.setText("Promote");
                }
                if (!myPane.getChildren().contains(AdminOrDisAdd)){
                    myPane.getChildren().add(AdminOrDisAdd);
                }
            }
        }
        else {
            AddOrRemove.setText("Add");
            if (!myPane.getChildren().contains(AddOrRemove)){
                myPane.getChildren().add(AddOrRemove);
            }
        }
    }
    public void DisShowExtra(){
        myPane.getChildren().remove(AddOrRemove);
        myPane.getChildren().remove(AdminOrDisAdd);
    }
    public void setX(double X){
        myBack.setTranslateX(X);
        imageView.setTranslateX(X+9);
        button.setTranslateX(imageView.getTranslateX()+5);
        label.setTranslateX(imageView.getTranslateX()+60);

        MoreOption.setTranslateX(imageView.getTranslateX()+75);


        AdminCircle.setRadius(13);
        AdminCircle.setCenterX(imageView.getTranslateX()+127);
        AdminCircle.setCenterY(imageView.getTranslateY()+25);
        SetExtra();

    }
    public void setY(double Y){
        myBack.setTranslateY(Y);
        imageView.setTranslateY(Y+10);
        button.setTranslateY(imageView.getTranslateY()+5);
        MoreOption.setTranslateY(imageView.getTranslateY()+18);
        label.setTranslateY(imageView.getTranslateY()+18);


        AdminCircle.setRadius(13);
        AdminCircle.setCenterX(imageView.getTranslateX()+127);
        AdminCircle.setCenterY(imageView.getTranslateY()+25);
        SetExtra();
    }
    public void Empty(){
        myPane.getChildren().remove(myBack);
        myPane.getChildren().remove(imageView);
        myPane.getChildren().remove(button);
        myPane.getChildren().remove(label);
        myPane.getChildren().remove(MoreOption);

    }

    public static Image getRoundedImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
        //prof.setImage(getRoundedImage(image,200));
    }
}