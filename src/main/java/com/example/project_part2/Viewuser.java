package com.example.project_part2;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.StoryTableDBC;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.Post;
import com.example.project_part2.USER.BusinessUser;
import com.example.project_part2.USER.OrdinaryUser;
import com.example.project_part2.USER.User;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class Viewuser implements Initializable {
    //Image image1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\31189.jpg");
 //   Image image2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\theme2\\10.jpg");
  //  Image image3=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\dark.jpg");

    public void SETThEME(){
        if(CompletePersonalInformation.SetTheme==1){
            PANE.getStylesheets().add(getClass().getResource("Style10a/ss.css").toExternalForm());
            PANE.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==2){
            PANE.getStylesheets().add(getClass().getResource("Style10/ss.css").toExternalForm());
            PANE.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==3){
            PANE.getStylesheets().add(getClass().getResource("StyleDark/dark.css").toExternalForm());
            PANE.getStyleClass().add("body");
        }
    }


    public static User ThisUser;
    public static List<Post> timelineposts;

    Image comment=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\comment.png");
    Image info=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\information.png");
    Image like=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\like.png");
    Image dislike=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\dislike.png");
    Image black=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\black.png");
    Image LikeStory=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\download-icon-facebook+heart+love+icon-1320166592899480291_256.png");
    Image closepic=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\Close.png");
    Image next=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\next.png");
    Image back=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\back.png");
    public static  Button[] buttons;
    public static  Button[] buttonslike;
    public static  Button[] buttonsinfo;
    public static     Label []labels;
    public static     ImageView []imageViews;
    public static TextArea commentfield;
    public static Button send;
    public static Button cancel;
    boolean com=false;
    boolean ord=false;
    public int index=0;
    public Label label3=null,label4=null,viewlabel=null,likelabel;
    Color dotcolor = Color.FORESTGREEN;
    Color dotcolor1 = Color.WHITE;
    Image prof;

    @FXML
    AnchorPane tallpane,tallpanfollowers,tallpanfollowings,PANE;
    @FXML
    Label intro,block,close;
    @FXML
    ImageView proffield,bckgr;
    @FXML
    ChoiceBox Closelist,Blocklist;

    String ch2,ch3;
    public void searchlist() throws SQLException {
       ch2=(String) Blocklist.getValue();ch3=(String) Closelist.getValue();
         if(ch2.length()>0){
            int reply = JOptionPane.showConfirmDialog(null,  "Do you want to unblock this user?",
                    ":/",JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                if(PersonalHomepage.USER.BlockedMap.containsValue(MAINInformation.mainInformation.users.get(ch2))){
                    PersonalHomepage.USER.setUnBlockedUser(MAINInformation.mainInformation.users.get(ch2));
                    JOptionPane.showMessageDialog(null, "Unblocked !");}
            }
            else if (reply == JOptionPane.NO_OPTION) {
            }
        }
        else if(ch3.length()>0){
            int reply = JOptionPane.showConfirmDialog(null,  "Do you want to remove this user from your close friend list?",
                    ":/",JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                PersonalHomepage.USER.setRemoveCloseFriendUser(MAINInformation.mainInformation.users.get(ch3));
                JOptionPane.showMessageDialog(null, "Removed !");
            }
            else if (reply == JOptionPane.NO_OPTION) {
            }}
        refreshLISTS();
    }

    public void BLO(){
        Closelist.setValue("");
    }
    public void CLO(){
        Blocklist.setValue("");
    }

    public void refreshLISTS(){
        //======================
        List<String> list=new ArrayList<>();
        for(User user:ThisUser.BlockedMap.values()){
            list.add(user.UserName);}
        Blocklist.setItems(FXCollections.observableList(list));
//======================
        List<String> list2=new ArrayList<>();
        for(User user:ThisUser.CloseFriendMap.values()){
            list2.add(user.UserName);}
        Closelist.setItems(FXCollections.observableList(list2));
        //======================
    }

    public void addcomment(Post post){
        com=true;
        commentfield=new TextArea();
        send=new Button("Add");
        cancel=new Button("Cancel");
        send.setLayoutX(918);
        send.setLayoutY(520);
        cancel.setLayoutX(855);
        cancel.setLayoutY(520);
        commentfield.setLayoutX(800);
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
                            post.addcomment(PersonalHomepage.USER,commentfield.getText());
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

    public void setscrollpane(List<Post> posts){
        tallpane.getChildren().clear();
        int a=posts.size();
        tallpane.setPrefHeight(510*a);
        int i=0;
        boolean liked;
        for(Post posttemp:posts){
            if(posttemp.UserNameLiked(PersonalHomepage.USER.UserName)){liked=true;}
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
            Image sender=new Image(MAINInformation.mainInformation.users.get(posttemp.PosterName).profilepicpath);
            ImageView prof=new ImageView(CreatAccount.getRoundedImage(sender,200));
            prof.setFitWidth(46);
            prof.setFitHeight(46);
            prof.setX(15);
            prof.setY(500*(i)+340);
            tallpane.getChildren().add(prof);
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
                labels[i].setText("Likes : "+posttemp.getNumberOfLikes()+"\n\nCaption :  "+posttemp.Caption);}
            else {
                BusinessPost businessPost=(BusinessPost) posttemp;
                labels[i].setText("Likes : "+posttemp.getNumberOfLikes()+"\n\nCaption :  "+posttemp.Caption+"\n\nDescription : "+businessPost.description);
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
                        if(!posts.get(i).UserNameLiked(PersonalHomepage.USER.UserName)){
                            try {
                                posts.get(i).LikedList.add(LikeHandle.NewLikeHandles(PersonalHomepage.USER.UserName,posts.get(i).PostCode,false));
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            try {
                                PostTableDBC.postTableDBC.EditorDeletePost(posts.get(i),false);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            try {
                                String LikeCode="";
                                for (String j:posts.get(i).LikedList){
                                    if (MAINInformation.mainInformation.likeHandleMap.get(j).LikerUserName.equals(PersonalHomepage.USER.UserName)){
                                        LikeCode=j;
                                        PersonalHomepage.USER.addLikedPostCode(LikeCode,false);
                                    }
                                }
                                posts.get(i).addLikeOrRemove(LikeCode,false);
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        refresh(posts);
                    }
                    if(event.getSource()==buttonsinfo[i]){
                        try {
                            ShowPostFXML.MyBack=0;
                            Main.ShowPostFXMLStart(posts.get(i),PersonalHomepage.USER);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
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

    public void refresh(List<Post> posts){
        int a=posts.size();
        boolean liked;
        int i=0;
        for(Post posttemp:posts){
            if(posttemp.UserNameLiked(PersonalHomepage.USER.UserName)){liked=true;}
            else {liked=false;}
            if(!posttemp.Kind){
                labels[i].setText("Likes : "+posttemp.getNumberOfLikes()+"\n\nCaption :  "+posttemp.Caption);}
            else {
                BusinessPost businessPost=(BusinessPost) posttemp;
                labels[i].setText("Likes : "+posttemp.getNumberOfLikes()+"\n\nCaption :  "+posttemp.Caption+"\n\nDescription : "+businessPost.description);
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

    public List<Story> createStorylist(User user1){
        List<Story> ss=new ArrayList<>();
        if(user1.CloseFriendList.contains(PersonalHomepage.USER.UserName)||user1.UserName.equals(PersonalHomepage.USER.UserName)){
            for(Story story:user1.MyStories){
                ss.add(story);
            }
        }
        else {
            for(Story story:user1.MyStories){
                if(!story.IsClose){  ss.add(story);}
            }
        }
        return ss;
    }

    public void storypic(User user1){
        List<Story> userstories=createStorylist(user1);

        Button butClose=new Button();
        butClose.setPrefWidth(60);
        butClose.setPrefHeight(60);
        butClose.setLayoutX(920);
        butClose.setLayoutY(50);
        butClose.setOpacity(0);
        //=============================
        Button butLike=new Button();
        butLike.setPrefWidth(50);
        butLike.setPrefHeight(45);
        butLike.setLayoutX(930);
        butLike.setLayoutY(610);
        butLike.setOpacity(0);
        //=========================
        Button butnext=new Button();
        butnext.setPrefWidth(60);
        butnext.setPrefHeight(60);
        butnext.setLayoutX(1020);
        butnext.setLayoutY(320);
        butnext.setOpacity(0);
        //=========================
        Button butback=new Button();
        butback.setPrefWidth(60);
        butback.setPrefHeight(60);
        butback.setLayoutX(220);
        butback.setLayoutY(320);
        butback.setOpacity(0);
        //=========================
        ImageView imageView =new ImageView(black);
        imageView.setFitWidth(1300);
        imageView.setFitHeight(750);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setOpacity(0.8);
//=========================================
        ImageView imageView1 =new ImageView(black);
        imageView1.setFitWidth(700);
        imageView1.setFitHeight(750);
        imageView1.setX(300);
        imageView1.setY(0);
        imageView1.setOpacity(0);
//=========================================
        viewlabel=new Label("Views");
        viewlabel.setLayoutX(30);
        viewlabel.setLayoutY(410);
        viewlabel.setFont(Font.font("Footlight MT Light",20));
        viewlabel.setTextFill(dotcolor1);
        viewlabel.setWrapText(true);

        ListView <String>listView=new ListView<String>();
        listView.setItems(FXCollections.observableList(userstories.get(index).viewersnameList));
        listView.setTranslateX(30);
        listView.setTranslateY(440);
        listView.setPrefHeight(100);
        listView.setPrefWidth(200);
     //   listView.setBackground(new Background(BLUEVIOLET));
//=========================================
        likelabel=new Label("Likes");
        likelabel.setLayoutX(30);
        likelabel.setLayoutY(570);
        likelabel.setFont(Font.font("Footlight MT Light",20));
        likelabel.setTextFill(dotcolor1);
        likelabel.setWrapText(true);

        ListView <String>listView1=new ListView<String>();
        listView1.setItems(FXCollections.observableList(userstories.get(index).likersnameList));
        listView1.setTranslateX(30);
        listView1.setTranslateY(600);
        listView1.setPrefHeight(100);
        listView1.setPrefWidth(200);
        //listView1.setBackground(new BackgroundFill(Color.BLUEVIOLET, CornerRadii.EMPTY, Insets.EMPTY));
//=========================================
        Image image=new Image(user1.profilepicpath);
        ImageView tempprof=new ImageView(CreatAccount.getRoundedImage(image,200));
        tempprof.setFitWidth(120);
        tempprof.setFitHeight(120);
        tempprof.setX(110);
        tempprof.setY(70);
        //=========================================
        ImageView closebut=new ImageView(closepic);
        closebut.setFitWidth(60);
        closebut.setFitHeight(60);
        closebut.setX(920);
        closebut.setY(50);
        //=========================================
        ImageView nextbut=new ImageView(next);
        nextbut.setFitWidth(60);
        nextbut.setFitHeight(60);
        nextbut.setX(1020);
        nextbut.setY(320);
        //=========================================
        ImageView backbut=new ImageView(back);
        backbut.setFitWidth(60);
        backbut.setFitHeight(60);
        backbut.setX(220);
        backbut.setY(320);
        //=========================================
        ImageView LIKE=new ImageView(LikeStory);
        LIKE.setFitHeight(110);
        LIKE.setFitWidth(110);
        LIKE.setX(900);
        LIKE.setY(580);
        //=========================================
        Image storypic=new Image(userstories.get(index).picturepath);
        ImageView photo=new ImageView(storypic);
        photo.setFitHeight(450);
        photo.setFitWidth(700);
        photo.setX(300);
        photo.setY(130);
        //=========================
        label4=new Label(new Date().getHours()-userstories.get(index).date.getHours()+" hours ago ");
        label4.setLayoutX(400);
        label4.setLayoutY(600);
        label4.setFont(Font.font("Footlight MT Light",25));
        label4.setTextFill(dotcolor1);
        label4.setWrapText(true);

        //==========================
        if(user1.CloseFriendList.contains(PersonalHomepage.USER.UserName)||user1.UserName.equals(PersonalHomepage.USER.UserName)){
            if(userstories.get(index).IsClose){
                label3=new Label("(Close friends)");
                label3.setLayoutX(400);
                label3.setLayoutY(60);
                label3.setFont(Font.font("Footlight MT Light",30));
                label3.setTextFill(dotcolor);
                label3.setWrapText(true);
                ord=true;
            }
            else {ord=false;}
        }
        else {ord=false;}
        //==========================
        if(!userstories.get(index).viewersnameList.contains(PersonalHomepage.USER.UserName)){
            userstories.get(index).viewersnameList.add(PersonalHomepage.USER.UserName);
            try {
                StoryTableDBC.storyTableDBC.DeleteStory( userstories.get(index),false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
//=============================
        PANE.getChildren().add(imageView);
        PANE.getChildren().add(imageView1);
        PANE.getChildren().add(tempprof);
        PANE.getChildren().add(closebut);
        PANE.getChildren().add(backbut);
        PANE.getChildren().add(nextbut);
        PANE.getChildren().add(photo);
        PANE.getChildren().add(LIKE);
        PANE.getChildren().add(label4);
        PANE.getChildren().addAll(butLike,butClose,butnext,butback,listView,listView1,viewlabel,likelabel);
        if(ord&&label3!=null){PANE.getChildren().add(label3);}

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(event.getSource()==butClose){
                    index=0;
                    PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4,listView,listView1,viewlabel,likelabel);
                    if(PANE.getChildren().contains(label3)){PANE.getChildren().remove(label3);}

                }

                if(event.getSource()==butLike){
                    if(!userstories.get(index).likersnameList.contains(PersonalHomepage.USER.UserName)){
                        userstories.get(index).likersnameList.add(PersonalHomepage.USER.UserName);
                        try {
                            StoryTableDBC.storyTableDBC.DeleteStory( userstories.get(index),false);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }

                }

                if(event.getSource()==butnext){
                    if(index<=userstories.size()-2){
                        index++;
                        PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4,listView,listView1,viewlabel,likelabel);
                        if(PANE.getChildren().contains(label3)){PANE.getChildren().remove(label3);}
                        storypic(user1);
                    }
                }

                if(event.getSource()==butback){
                    if(index>0){
                        index--;
                        PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4,listView,listView1,viewlabel,likelabel);
                        if(PANE.getChildren().contains(label3)){PANE.getChildren().remove(label3);}
                        storypic(user1);
                    }
                }

                event.consume();
            }

        };

        butClose.setOnMouseClicked(handler);
        butLike.setOnMouseClicked(handler);
        butnext.setOnMouseClicked(handler);
        butback.setOnMouseClicked(handler);


    }


    public void home() throws IOException {
        Main.personalpageSTART();
    }

     public void viewStory(){
        if(ThisUser.StoryCodeList.size()>0) {
            storypic(ThisUser);
        }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SETThEME();
        if(!ThisUser.Kind){
            OrdinaryUser ordinaryUser=(OrdinaryUser) ThisUser;
            if(ordinaryUser.Private){
        intro.setText("Username : "+ThisUser.UserName+"\nName : "+ThisUser.Name+"\nAge : "+ThisUser.age+"\nbio : "+
                ThisUser.Bio+"\nOrdinaryUser (Private)");}
        else {
                intro.setText("Username : "+ThisUser.UserName+"\nName : "+ThisUser.Name+"\nAge : "+ThisUser.age+"\nbio : "+
                        ThisUser.Bio+"\nOrdinaryUser (Public)");
            }
        }
        else {
            BusinessUser bus=(BusinessUser) ThisUser;
            intro.setText("Username : "+ThisUser.UserName+"\nName : "+ThisUser.Name+"\nAge : "+ThisUser.age+"\nbio : "+
                ThisUser.Bio+"\nBusinessUser ("+bus.buisnessType);}
        timelineposts=ThisUser.posts;
        prof=new Image(ThisUser.profilepicpath);
        proffield.setImage(CreatAccount.getRoundedImage(prof,200));

        buttons=new Button[timelineposts.size()];
        buttonslike=new Button[timelineposts.size()];
        buttonsinfo=new Button[timelineposts.size()];
        labels =new Label[timelineposts.size()];
        imageViews =new ImageView[timelineposts.size()];

           List<User> wer=new ArrayList<>();
        for (User user : ThisUser.FollowerMap.values()){
            wer.add(user);
    }
        List<User> wing=new ArrayList<>();
        for (User user : ThisUser.FollowingMap.values()){
            wing.add(user);
        }
        setFollowers(wer);
        setFollowings(wing);
//======================
        List<String> list=new ArrayList<>();
        for(User user:ThisUser.BlockedMap.values()){
        list.add(user.UserName);}
        Blocklist.setItems(FXCollections.observableList(list));
//======================
        List<String> list2=new ArrayList<>();
        for(User user:ThisUser.CloseFriendMap.values()){
            list2.add(user.UserName);}
        Closelist.setItems(FXCollections.observableList(list2));
 //======================
     // //  for(int i=0;i<timelineposts.size();i++){
//System.out.println(timelineposts.get(i).PostCode);}
        setscrollpane(timelineposts);
    }

    public void setFollowings(List<User> suguser) {
        tallpanfollowings.getChildren().clear();
        if(suguser!=null){
            int i=0;
            tallpanfollowings.setPrefHeight(suguser.size()*70+50);
            for(User user:suguser){
                PreShowUser usertemp=new PreShowUser(user,10,70*i+10,tallpanfollowings);
                i++;
            }}
    }

    public void setFollowers(List<User> suguser) {
        tallpanfollowers.getChildren().clear();
        if(suguser!=null){
            int i=0;
            tallpanfollowers.setPrefHeight(suguser.size()*70+50);
            for(User user:suguser){
                PreShowUser usertemp=new PreShowUser(user,10,70*i+10,tallpanfollowers);
                i++;
            }}
    }
}
