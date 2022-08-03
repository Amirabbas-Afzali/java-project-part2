package com.example.project_part2;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.UserTableDBC;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.Post;
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
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Suggestions implements Initializable {

static User USER;
    @FXML
    AnchorPane tallpane,tallpaneuser;
    @FXML
    Pane PANE;
    @FXML
    CheckBox hash,bus;
    @FXML
    TextField post,user;
    @FXML
    ChoiceBox bis;
    @FXML
    ImageView searchpost,searchpost1;

    Image comment=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\comment.png");
    Image info=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\information.png");
    Image like=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\like.png");
    Image dislike=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\dislike.png");

    public static List<Post> searchedposts;
    public static List<User> syggestedUsers;
    public static  Button[] buttons;
    public static  Button[] buttonslike;
    public static  Button[] buttonsinfo;
    public static  Label []labels;
    public static  ImageView []imageViews;
    public static TextArea commentfield;
    public static Button send;
    public static Button cancel;
    boolean com=false;
List<PreShowUser> preShowUsers=new ArrayList<>();

    public void setscrollpaneUSER(List<User> suguser) {
        if(suguser!=null){
        int i=0;
        tallpaneuser.setPrefHeight(suguser.size()*70);
        for(User user:suguser){
    PreShowUser usertemp=new PreShowUser(user,17,70*i+10,tallpaneuser);
    preShowUsers.add(usertemp);
    i++;
        }}

    }

    public void home() throws IOException {
        Main.personalpageSTART();
    }

        public void setscrollpane(List<Post> posts) {
        tallpane.getChildren().clear();
        if(posts!=null){
            if(posts.size()>0){
                int a = posts.size();
            tallpane.setPrefHeight(510 * a);
            int i = 0;
            boolean liked;
            for (Post posttemp : posts) {
                if (posttemp.LikedList.contains(USER.UserName)) {
                    liked = true;
                } else {
                    liked = false;
                }
                Image image = new Image(posttemp.photopath);
                ImageView imageView = new ImageView(image);
                imageView.setX(70);
                imageView.setY(500 * i + 20);
                imageView.setFitWidth(575);
                imageView.setPreserveRatio(true);
                //===================
                ImageView imageViewcom = new ImageView(comment);
                imageViewcom.setFitWidth(35);
                imageViewcom.setFitHeight(35);
                imageViewcom.setX(650);
                imageViewcom.setY(500 * (i) + 300);
                buttons[i] = new Button();
                buttons[i].setLayoutX(650);
                buttons[i].setLayoutY(500 * (i) + 302);
                buttons[i].setPrefHeight(20);
                buttons[i].setPrefWidth(32);
                buttons[i].setOpacity(0);
                tallpane.getChildren().add(imageViewcom);
                tallpane.getChildren().add(buttons[i]);
                //===================

                if (!liked) {
                    imageViews[i] = new ImageView(like);
                } else {
                    imageViews[i] = new ImageView(dislike);
                }
                imageViews[i].setFitWidth(35);
                imageViews[i].setFitHeight(35);
                imageViews[i].setX(650);
                imageViews[i].setY(500 * (i) + 250);
                buttonslike[i] = new Button();
                buttonslike[i].setLayoutX(650);
                buttonslike[i].setLayoutY(500 * (i) + 252);
                buttonslike[i].setPrefHeight(20);
                buttonslike[i].setPrefWidth(32);
                buttonslike[i].setOpacity(0);
                tallpane.getChildren().add(imageViews[i]);
                tallpane.getChildren().add(buttonslike[i]);
                //====================
                Image sender = new Image(MAINInformation.mainInformation.users.get(posttemp.PosterName).profilepicpath);
                ImageView prof = new ImageView(CreatAccount.getRoundedImage(sender, 200));
                prof.setFitWidth(46);
                prof.setFitHeight(46);
                prof.setX(15);
                prof.setY(500 * (i) + 340);
                tallpane.getChildren().add(prof);
                //====================
                ImageView imageViewinfo = new ImageView(info);
                imageViewinfo.setFitWidth(35);
                imageViewinfo.setFitHeight(35);
                imageViewinfo.setX(650);
                imageViewinfo.setY(500 * (i) + 350);
                buttonsinfo[i] = new Button();
                buttonsinfo[i].setLayoutX(650);
                buttonsinfo[i].setLayoutY(500 * (i) + 352);
                buttonsinfo[i].setPrefHeight(20);
                buttonsinfo[i].setPrefWidth(30);
                buttonsinfo[i].setOpacity(0);
                tallpane.getChildren().add(imageViewinfo);
                tallpane.getChildren().add(buttonsinfo[i]);
                //============================
                labels[i] = new Label();
                if (!posttemp.Kind) {
                    labels[i].setText("Likes : " + posttemp.LikedList.size() + "\n\nCaption :  " + posttemp.Caption);
                } else {
                    BusinessPost businessPost = (BusinessPost) posttemp;
                    labels[i].setText("Likes : " + posttemp.LikedList.size() + "\n\nCaption :  " + posttemp.Caption + "\n\nDescription : " + businessPost.description);
                }
                labels[i].setLayoutX(110);
                labels[i].setLayoutY(500 * (i) + 420);
                tallpane.getChildren().addAll(imageView, labels[i]);
                i++;
            }

            EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // TODO Auto-generated method stub
                    for (int i = 0; i < a; i++) {
                        if (event.getSource() == buttons[i]) {
                            if (!com) {
                                addcomment(posts.get(i));
                            }
                        }
                        if (event.getSource() == buttonslike[i]) {
                            System.out.println(i);
                            if (!posts.get(i).LikedList.contains(USER.UserName)) {
                                posts.get(i).LikedList.add(USER.UserName);
                                USER.LikedPostCodes.add(posts.get(i).PostCode);
                                try {
                                    UserTableDBC.userTableDBC.EditorDeleteUser(USER, false);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    PostTableDBC.postTableDBC.EditorDeletePost(posts.get(i), false);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                posts.get(i).LikedList.remove(USER.UserName);
                                USER.LikedPostCodes.remove(posts.get(i).PostCode);

                                try {
                                    UserTableDBC.userTableDBC.EditorDeleteUser(USER,false);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    PostTableDBC.postTableDBC.EditorDeletePost(posts.get(i),false);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }

                            }
                            refresh(posts);
                        }
                    }

                    event.consume();
                }

            };

            //Adding Handler for the play and pause button
            for (int j = 0; j < a; j++) {
                buttons[j].setOnMouseClicked(handler);
                buttonslike[j].setOnMouseClicked(handler);
                buttonsinfo[j].setOnMouseClicked(handler);
            }
        }}
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

    public void hashfill(){
        hash.setSelected(true);bus.setSelected(false);
        post.setVisible(true);
        post.setPromptText("Search Hashtag");
        bis.setVisible(false);
        searchpost.setVisible(true);
        searchpost1.setVisible(false);
        getpost();
    }
    public void busfill(){
        hash.setSelected(false);bus.setSelected(true);
        post.setVisible(false);
        bis.setVisible(true);
        searchpost.setVisible(false);
        searchpost1.setVisible(true);
        getpost();
    }

    public void getuser(){
        for (PreShowUser i:preShowUsers){
            i.Empty();
        }
        updateuser();
    }

    public void getpost(){
          updatepost();
        buttons=new Button[searchedposts.size()];
        buttonslike=new Button[searchedposts.size()];
        buttonsinfo=new Button[searchedposts.size()];
        labels =new Label[searchedposts.size()];
        imageViews =new ImageView[searchedposts.size()];
        setscrollpane(searchedposts);
    }

    public  void updateuser(){
        syggestedUsers=new ArrayList<>();
        if(user.getText()==null||"".equals(user.getText())){
        List<String> userss=ShowTimeline.showTimeline.getSuggestedUsers(USER,10);
        for (String str:userss) {
            syggestedUsers.add(MAINInformation.mainInformation.users.get(str));
        } }
        else {
            syggestedUsers=searchusers(user.getText());
        }
        setscrollpaneUSER(syggestedUsers);
    }

    public  void updatepost(){
      searchedposts=new ArrayList<>();
      if(hash.isSelected()){
      if(post.getText()!=null/*.length()>0*/) {
          List<Integer> postcodes=ShowTimeline.showTimeline.PostsWithHashtag(post.getText(),10);
          for(Integer a:postcodes){
              searchedposts.add(MAINInformation.mainInformation.posts.get(a.toString()));
          }
          //System.out.println("122222222221");
      }
      else {
          searchedposts=ShowTimeline.showTimeline.TimeLinePosts(10,USER);
       //   System.out.println(searchedposts);
       //   System.out.println("133333333333332221");
      }
      }
      else {
          searchedposts=ShowTimeline.showTimeline.getPostWithTopic(findtype((String) bis.getValue()),10);
          //System.out.println("111111111111");
      }

    }

    public BuisnessType findtype(String str){
        //\n1.Tech\n2.Cloth\n3.AD\n4.Artist\n5.Sport"
        if (str!=null){
        if(str.equals("Technology")){return BuisnessType.Tech;}
        if(str.equals("Cloth")){return BuisnessType.Cloth;}
        if(str.equals("AD")){return BuisnessType.AD;}
        if(str.equals("Art")){return BuisnessType.Artist;}
        if(str.equals("Sport")){return BuisnessType.Sport;}
        }
        return BuisnessType.Sport;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> list=new ArrayList<>();
        list.add("Technology");list.add("Cloth");list.add("AD");list.add("Art");list.add("Sport (default)");
        bis.setItems(FXCollections.observableList(list));
        hash.setSelected(true);bus.setSelected(false);
        post.setPromptText("Search Hashtag");
        bis.setVisible(false);
        searchpost.setVisible(true);
        searchpost1.setVisible(false);

        updatepost();

        buttons=new Button[searchedposts.size()];
        buttonslike=new Button[searchedposts.size()];
        buttonsinfo=new Button[searchedposts.size()];
        labels =new Label[searchedposts.size()];
        imageViews =new ImageView[searchedposts.size()];
        setscrollpane(searchedposts);
        updateuser();
    }

    public List<User> searchusers(String str){
        List<User> temp=new ArrayList<>();
        for (User user:MAINInformation.mainInformation.users.values()){
            if(user.UserName.contains(str)&&!user.UserName.equals(USER.UserName)){
                temp.add(user);
            }
        }
        return  temp;
    }

}
