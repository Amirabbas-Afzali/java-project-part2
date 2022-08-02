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
import javafx.scene.text.Font;

import java.io.IOException;

public class PreShowUser {
    User user;
    Label label=new Label();
    ImageView imageView,myBack;
    Button button=new Button();
    AnchorPane myPane;
    PreShowUser(User _user, double X, double Y, AnchorPane anchorPane){
        myPane=anchorPane;
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
                if(!PersonalHomepage.USER.UserName.equals(user.UserName)){
                ViewOtherUsers.ThisUser = user;
                try {
                    Main.ViewOtheruserSTART();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            }
        });

        myPane.getChildren().add(imageView);
        myPane.getChildren().add(button);
        myPane.getChildren().add(label);
    }
    public void setX(double X){
        myBack.setTranslateX(X);
        imageView.setTranslateX(X+9);
        button.setTranslateX(imageView.getTranslateX()+5);
        label.setTranslateX(imageView.getTranslateX()+60);

    }
    public void setY(double Y){
        myBack.setTranslateY(Y);
        imageView.setTranslateY(Y+10);
        button.setTranslateY(imageView.getTranslateY()+5);
        label.setTranslateY(imageView.getTranslateY()+18);
    }
    public void Empty(){
        myPane.getChildren().remove(myBack);
        myPane.getChildren().remove(imageView);
        myPane.getChildren().remove(button);
        myPane.getChildren().remove(label);

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