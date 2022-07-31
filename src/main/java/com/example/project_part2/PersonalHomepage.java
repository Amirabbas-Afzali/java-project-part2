package com.example.project_part2;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PersonalHomepage implements Initializable {
    public static PersonalHomepage personalHomepage=new PersonalHomepage();
    public static User USER;
    public static List<Post> timelineposts;

    public static  Button[] buttons;
    public static  Button[] buttonslike;
    public static  Button[] buttonsinfo;
    public static     Label []labels;
    public static     ImageView []imageViews;
    public static TextArea commentfield;
    public static Button send;
    public static Button cancel;
    boolean com=false;

    @FXML
    ImageView img;
    @FXML
    TextField t1;
    @FXML
    AnchorPane tallpane;
    @FXML
    Pane PANE;
    @FXML
    Label label1;
    @FXML
    Button but1;

    Image comment=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\comment.png");
    Image info=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\information.png");
    Image like=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\like.png");
    Image dislike=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\dislike.png");

    Image prof;
    ImageView proffield;


    public void start(User usertemp) throws SQLException {
        boolean flag=true;
        while (flag){
            if(!usertemp.isreport){
                String str, strtemp = "Enter a number:\n1.Create new post\n2.show timeline\n3.Complete or Edit personal information" +
                        "\n4.My chats and PVs\n5.My contacts (followers & followings & ...)" +
                        "\n6.Show My posts\n7.Add Story\n8.back ";
                System.out.println(strtemp);
                str = Main.scanner.nextLine();
                str=str.trim();
                if (str.equals("1")) {
                    CreatenewPost.createnewPost.user=usertemp;
                    CreatenewPost.createnewPost.start();
                }
                else if (str.equals("2")) {
                    // ShowTimeline.showTimeline.user=;
                    ShowTimeline.showTimeline.start(usertemp);
                }
                else if (str.equals("3")) {
                    CompletePersonalInformation.completePersonalInformation.user=usertemp;
                    CompletePersonalInformation.completePersonalInformation.start();
                }
                else if (str.equals("4")) {
                    MychatsandPVs.mychatsandPVs.user=usertemp;
                    MychatsandPVs.mychatsandPVs.start();
                }
                else if (str.equals("5")) {
                    Mycontacts.mycontacts.start(usertemp);
                }
                else if (str.equals("6")) {
                    ShowPosts.showPosts.start(usertemp,usertemp);
                }
                else if (str.equals("7")) {
                    CreatenewStory.createnewStory.user=usertemp;
                    CreatenewStory.createnewStory.start();
                }
                else if (str.equals("8")) {
                    flag=false;
                    SignIn.signIn.start();
                }
                else {
                    System.out.println("Invalid command!");
                }}
            else {   String str, strtemp = "Enter a number:\n1.show timeline\n2.Complete or Edit personal information" +
                    "\n3.My chats and PVs\n4.My contacts (followers & followings & ...)" +
                    "\n5.back ";
                System.out.println(strtemp);
                str = Main.scanner.nextLine();
                str=str.trim();

                if (str.equals("1")) {
                    ShowTimeline.showTimeline.start(usertemp);
                }
                else if (str.equals("2")) {
                    CompletePersonalInformation.completePersonalInformation.user=usertemp;
                    CompletePersonalInformation.completePersonalInformation.start();
                }
                else if (str.equals("3")) {
                    MychatsandPVs.mychatsandPVs.user=usertemp;
                    MychatsandPVs.mychatsandPVs.start();
                }
                else if (str.equals("4")) {
                    Mycontacts.mycontacts.start(usertemp);
                }
                else if (str.equals("5")) {
                    flag=false;
                    SignIn.signIn.start();
                }
                else {
                    System.out.println("Invalid command!");
                }
            }

        }
    }

    public void addcomment(Post post){
        com=true;
         commentfield=new TextArea();
         send=new Button("Add");
        cancel=new Button("Cancel");
         send.setLayoutX(1198);
        send.setLayoutY(520);
        cancel.setLayoutX(1135);
        cancel.setLayoutY(520);
        commentfield.setLayoutX(1080);
        commentfield.setLayoutY(350);
        commentfield.setPrefHeight(200);
        commentfield.setPrefWidth(180);
        commentfield.setPromptText("Add a comment . . .");
        PANE.getChildren().add(commentfield);
        PANE.getChildren().addAll(send,cancel);

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    if(event.getSource()==send) {

                        if(commentfield.getText().length()>0){
                        try {
                            post.addcomment(USER,commentfield.getText());
                          com=false;
                        PANE.getChildren().removeAll(commentfield,send,cancel);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }}

                    }
                if(event.getSource()==cancel) {
                  PANE.getChildren().removeAll(commentfield,send,cancel);
                    com=false;
                }

                event.consume();
            }

        };


            send.setOnMouseClicked(handler);
            cancel.setOnMouseClicked(handler);

    }

    public void but1func(){
        System.out.println("oooooooo");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        label1.setText(USER.UserName);
        prof=new Image(USER.profilepicpath);
        proffield=new ImageView(CreatAccount.getRoundedImage(prof,200));
        proffield.setX(1140);proffield.setY(30);
        proffield.setFitHeight(100);proffield.setFitWidth(100);
        PANE.getChildren().add(proffield);
        buttons=new Button[timelineposts.size()];
        buttonslike=new Button[timelineposts.size()];
       buttonsinfo=new Button[timelineposts.size()];
         labels =new Label[timelineposts.size()];
        imageViews =new ImageView[timelineposts.size()];
               setscrollpane(USER.posts);
    }

    public void refresh(List<Post> posts){
        int a=posts.size();
        boolean liked;
        int i=0;
        for(Post posttemp:posts){
            if(posttemp.LikedList.contains(USER.UserName)){liked=true;}
            else {liked=false;}
            if(!posttemp.Kind){
                labels[i].setText("Likes : "+posttemp.LikedList.size()+"\n\nCaption :  "+posttemp.Caption);}
            else {
                BusinessPost businessPost=(BusinessPost) posttemp;
                labels[i].setText("Likes : "+posttemp.LikedList.size()+"\n\nCaption :  "+posttemp.Caption+"\n\nDescription : "+businessPost.description);
            }

            tallpane.getChildren().remove(buttonslike[i]);
            tallpane.getChildren().remove(imageViews[i]);
            if(!liked){ imageViews[i] = new ImageView(like);}
            else { imageViews[i]  = new ImageView(dislike);}

            imageViews[i] .setFitWidth(35);
            imageViews[i] .setFitHeight(35);
            imageViews[i] .setX(650);
            imageViews[i] .setY(500*(i)+250);
            buttonslike[i].setLayoutX(650);
            buttonslike[i].setLayoutY(500*(i)+252);
            buttonslike[i].setPrefHeight(20);
            buttonslike[i].setPrefWidth(32);
            buttonslike[i].setOpacity(0);
            tallpane.getChildren().add( imageViews[i] );
            tallpane.getChildren().add(buttonslike[i]);
            i++;
        }
    }

        public void setscrollpane(List<Post> posts){
        int a=posts.size();
        tallpane.setPrefHeight(510*a);
        int i=0;
          boolean liked;
        for(Post posttemp:posts){
            if(posttemp.LikedList.contains(USER.UserName)){liked=true;}
            else {liked=false;}
            Image image=new Image(posttemp.photopath);
            ImageView imageView=new ImageView(image);
            imageView.setX(70);
            imageView.setY(500*i+20);
            imageView.setFitWidth(575);
            imageView.setPreserveRatio(true);
            //===================
            ImageView imageViewcom = new ImageView(comment);
            imageViewcom.setFitWidth(35);
            imageViewcom.setFitHeight(35);
            imageViewcom.setX(650);
            imageViewcom.setY(500*(i)+300);
            buttons[i]=new Button();
            buttons[i].setLayoutX(650);
            buttons[i].setLayoutY(500*(i)+302);
            buttons[i].setPrefHeight(20);
            buttons[i].setPrefWidth(32);
            buttons[i].setOpacity(0);
            tallpane.getChildren().add(imageViewcom);
            tallpane.getChildren().add(buttons[i]);
            //===================

            if(!liked){ imageViews[i] = new ImageView(like);}
            else {imageViews[i] = new ImageView(dislike);}
            imageViews[i].setFitWidth(35);
            imageViews[i].setFitHeight(35);
            imageViews[i].setX(650);
            imageViews[i].setY(500*(i)+250);
            buttonslike[i]=new Button();
            buttonslike[i].setLayoutX(650);
            buttonslike[i].setLayoutY(500*(i)+252);
            buttonslike[i].setPrefHeight(20);
            buttonslike[i].setPrefWidth(32);
            buttonslike[i].setOpacity(0);
            tallpane.getChildren().add(imageViews[i]);
            tallpane.getChildren().add(buttonslike[i]);
            //====================
            ImageView imageViewinfo = new ImageView(info);
            imageViewinfo.setFitWidth(35);
            imageViewinfo.setFitHeight(35);
            imageViewinfo.setX(650);
            imageViewinfo.setY(500*(i)+350);
            buttonsinfo[i]=new Button();
            buttonsinfo[i].setLayoutX(650);
            buttonsinfo[i].setLayoutY(500*(i)+352);
            buttonsinfo[i].setPrefHeight(20);
            buttonsinfo[i].setPrefWidth(30);
            buttonsinfo[i].setOpacity(0);
            tallpane.getChildren().add(imageViewinfo);
            tallpane.getChildren().add(buttonsinfo[i]);
            //============================
            labels[i] =new Label();
         if(!posttemp.Kind){
             labels[i].setText("Likes : "+posttemp.LikedList.size()+"\n\nCaption :  "+posttemp.Caption);}
         else {
             BusinessPost businessPost=(BusinessPost) posttemp;
             labels[i].setText("Likes : "+posttemp.LikedList.size()+"\n\nCaption :  "+posttemp.Caption+"\n\nDescription : "+businessPost.description);
         }
            labels[i].setLayoutX(110);
            labels[i].setLayoutY(500*(i)+420);
            tallpane.getChildren().addAll(imageView, labels[i]);
            i++;
        }

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                for(int i=0;i<a;i++){
                    if(event.getSource()==buttons[i])
                    {
                    if(!com){  addcomment(posts.get(i));}
                    }
                if(event.getSource()==buttonslike[i])
                {
                    System.out.println(i);
                    if(!posts.get(i).LikedList.contains(USER.UserName)){
                  posts.get(i).LikedList.add(USER.UserName);}
                    else {posts.get(i).LikedList.remove(USER.UserName);}
                             refresh(posts);
                }
                }

                event.consume();
            }

        };

        //Adding Handler for the play and pause button
        for(int j=0;j<a;j++){
            buttons[j].setOnMouseClicked(handler);
            buttonslike[j].setOnMouseClicked(handler);
            buttonsinfo[j].setOnMouseClicked(handler);
        }
    }

    public void newpost() throws IOException {
          if(  USER.Kind){
                          CreatenewBUSPost.user=USER;
                        Main.CreateBUSpostSTART();}
                      else {
                          CreatenewORDPost.user=USER;
                          Main.CreateORDpostSTART();}
    }

    public void myinfo(){}
    public void newstory(){}
    public void sugg(){}
    public void contact(){}
    public void direct(){}

}
