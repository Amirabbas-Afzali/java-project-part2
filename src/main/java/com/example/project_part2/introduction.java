package com.example.project_part2;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class introduction extends  Application  {
    public static introduction introduction1=new introduction();
    Color dotcolor1 = Color.WHITE;

    Hyperlink link = new Hyperlink("www.linkedin.com/in/amirreza-zameni-ateni-096889247");
    Hyperlink link2= new Hyperlink("https://www.linkedin.com/in/amir-a-afzali-b539b1226");
   Hyperlink part2 = new Hyperlink("https://github.com/Amirabbas-Afzali/java-project-part2");
   Hyperlink part1= new Hyperlink("https://github.com/Amirabbas-Afzali/java-project-part1");

    final ListView listView = new ListView();
    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane pane = new AnchorPane();

        Image image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\gradient-blur-wallpaper.jpg");
        ImageView imageView=new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitWidth(1300);
        imageView.setFitHeight(760);

        Label label1=new Label("Developed by :");
        label1.setFont(Font.font("Footlight MT Light",35));
        label1.setLayoutX(40);
        label1.setLayoutY(550);
        label1.setTextFill(dotcolor1);
        //============================
       Image prof=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\amir.jpg");
        ImageView proffield=new ImageView(CreatAccount.getRoundedImage(prof,200));
        proffield.setX(360);
        proffield.setY(590);
        proffield.setFitHeight(80);proffield.setFitWidth(80);//eight(70);

        Image linkdin1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\download-icon-linkedin+logo+media+network+share+social+icon-1320192913911966641_256.png");
        ImageView aaa=new ImageView(linkdin1);
        aaa.setX(445);
        aaa.setY(660);
        aaa.setFitHeight(23);aaa.setFitWidth(23);

        ImageView aaa1=new ImageView(linkdin1);
        aaa1.setX(885);
        aaa1.setY(660);
        aaa1.setFitHeight(23);aaa1.setFitWidth(23);

        Label label2=new Label("Amirabbas Afzali");
        label2.setFont(Font.font("Footlight MT Light",25));
        label2.setLayoutX(470);
        label2.setLayoutY(615);
        label2.setTextFill(dotcolor1);
        //=====================
        Image prof2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\ramzoon.jpg");
        ImageView proffield2=new ImageView(CreatAccount.getRoundedImage(prof2,200));
        proffield2.setX(800);
        proffield2.setY(590);
        proffield2.setFitHeight(80);proffield2.setFitWidth(80);//eight(70);

        Image back=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\B.png");
        ImageView bbb=new ImageView(back);
        bbb.setX(20);
        bbb.setY(690);
        bbb.setFitHeight(40);bbb.setFitWidth(40);//eight(70);

        Label label3=new Label("Amirreza Zameni");
        label3.setFont(Font.font("Footlight MT Light",25));
        label3.setLayoutX(920);
        label3.setLayoutY(615);
        label3.setTextFill(dotcolor1);

        Label label5=new Label("Special Thanks to\n              dr.Vahdat   ,   dr.Hashemi\n              and anyone who helped us (TAs , Friends , Stackoverflow & . . . )");
        label5.setFont(Font.font("Freestyle Script",30));
        label5.setLayoutX(240);
        label5.setLayoutY(200);
        label5.setTextFill(dotcolor1);

        Label title=new Label("OWL Messenger");
        title.setFont(Font.font("Freestyle Script",60));
        title.setLayoutX(545);
        title.setLayoutY(2);
        title.setTextFill(dotcolor1);

        Label EE=new Label("EESUT");
        EE.setFont(Font.font("Footlight MT Light",40));
        EE.setLayoutX(1100);
        EE.setLayoutY(50);
        EE.setTextFill(dotcolor1);

        Image profgit=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\git.png");
        ImageView proffield7=new ImageView(profgit);
        proffield7.setX(810);
        proffield7.setY(440);
        proffield7.setFitHeight(50);proffield7.setFitWidth(50);//eight(70);

        Button button=new Button();
        button.setLayoutX(20);
        button.setLayoutY(690);
        button.setPrefHeight(40);
        button.setPrefWidth(40);
        button.setOpacity(0);

        pane.getChildren().add(imageView);
        pane.getChildren().addAll(label1,proffield,proffield2,label2,label3,aaa,aaa1,title,proffield7,bbb,label5,EE);
        pane.getChildren().add(button);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                try {
                    pane.getChildren().clear();
                    Main.personalpageSTART();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
            link.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    getHostServices().showDocument(link.getText());
                }
            });
        link2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    getHostServices().showDocument(link2.getText());
                }
            });
        part1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getHostServices().showDocument(part1.getText());
            }
        });
        part2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getHostServices().showDocument(part2.getText());
            }
        });

        part1.setLayoutX(875);part1.setLayoutY(443);
        part2.setLayoutX(875);part2.setLayoutY(463);
        link2.setLayoutX(473);link2.setLayoutY(660);
        link.setLayoutX(915);link.setLayoutY(660);
      pane.getChildren().addAll(link,link2,part1,part2);
       Scene scene = new Scene(pane, 1300, 750);
        primaryStage.setTitle("Introduction");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
