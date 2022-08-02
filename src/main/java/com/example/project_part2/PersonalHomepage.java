package com.example.project_part2;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.StoryTableDBC;
import com.example.project_part2.DataBaseController.UserTableDBC;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class PersonalHomepage implements Initializable {
    public static PersonalHomepage personalHomepage=new PersonalHomepage();
    public static User USER;

    public static List<Post> timelineposts;
    public static List<User> ShowstoriesUsers;
    //public static List<Story>   Allstoreis;
    public static  Button[] buttons;
    public static  Button[] buttonslike;
    public static  Button[] buttonsinfo;
    public static     Label []labels;
    public static     ImageView []imageViews;
    public static TextArea commentfield;
    public static Button send;
    public static Button cancel;
    boolean com=false;
    public static  Button[] buttonsStory;
    public static     ImageView []Sotryuserprof;

    @FXML
    ImageView img;
    @FXML
    TextField t1;
    @FXML
    AnchorPane tallpane,stories;
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
         Image black=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\black.png");
     Image LikeStory=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\download-icon-facebook+heart+love+icon-1320166592899480291_256.png");
        Image close=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\Close.png");
    Image next=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\next.png");
    Image back=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\back.png");

    Image prof;
    Button viewuser;
    @FXML
    ImageView proffield;

    public Circle circle;
    Color dotcolor = Color.FORESTGREEN;
    Color dotcolor1 = Color.WHITE;

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

public void FillStatics(){
        if (USER.FollowingMap!=null){
        List<User> userstory=new ArrayList<>();
        List<Story>   allstory=new ArrayList<>();

        for(User user:USER.FollowingMap.values()){
            if(user.StoryCodeList.size()>0) {
                boolean exist = false;
                if (!user.CloseFriendList.contains(USER.UserName)) {
                    for (String str : user.StoryCodeList) {
                        if (!MAINInformation.mainInformation.stories.get(str).IsClose) {
                            allstory.add(MAINInformation.mainInformation.stories.get(str));
                            exist = true;
                        }
                    }
                }
                else {
                    for (String str : user.StoryCodeList) {
                            allstory.add(MAINInformation.mainInformation.stories.get(str));
                            exist = true;
                    }
                }
               if(exist){userstory.add(user);

               }
            }
        }
      //  PersonalHomepage.Allstoreis=allstory;
        PersonalHomepage.ShowstoriesUsers=userstory;

}}

    public boolean checkisClose(User user){
        if (user.CloseFriendList.contains(USER.UserName)) {
            for (String str : user.StoryCodeList) {
                if (MAINInformation.mainInformation.stories.get(str).IsClose) {
                   return true;
                }
            }
        }
        return false;
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
                      FillStatics();
        label1.setText(USER.UserName);
        prof=new Image(USER.profilepicpath);
        proffield.setImage(CreatAccount.getRoundedImage(prof,200));
      //  proffield.setX(1140);proffield.setY(30);
     //   proffield.setFitHeight(100);proffield.setFitWidth(100);
        //PANE.getChildren().add(proffield);


        buttons=new Button[timelineposts.size()];
        buttonslike=new Button[timelineposts.size()];
       buttonsinfo=new Button[timelineposts.size()];
       buttonsStory=new Button[ShowstoriesUsers.size()];
        Sotryuserprof =new ImageView[ShowstoriesUsers.size()];
         labels =new Label[timelineposts.size()];
        imageViews =new ImageView[timelineposts.size()];
               setscrollpane(timelineposts);
               setscrollpaneStory(ShowstoriesUsers);

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
                  posts.get(i).LikedList.add(USER.UserName);
                    USER.LikedPostCodes.add(posts.get(i).PostCode);
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
                    else {posts.get(i).LikedList.remove(USER.UserName);
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
        for(int j=0;j<a;j++){
            buttons[j].setOnMouseClicked(handler);
            buttonslike[j].setOnMouseClicked(handler);
            buttonsinfo[j].setOnMouseClicked(handler);
        }
    }

    public void intro() throws IOException {
Main.introSTART();
    }

    public void setscrollpaneStory(List<User> users){
        int a=users.size();
        stories.setPrefWidth(50*a);
        int i=0;
        for (User user:users){
           if(checkisClose(user)) {
                circle = new Circle();
                circle.setRadius(40);
                circle.setCenterX(95 * i + 57);
                circle.setCenterY(50);
                circle.setFill(dotcolor);
            }
            buttonsStory[i]=new Button();
            buttonsStory[i].setPrefHeight(70);
            buttonsStory[i].setPrefWidth(70);
            buttonsStory[i].setLayoutX(95*i+22);
            buttonsStory[i].setLayoutY(15);
            buttonsStory[i].setOpacity(0);

           Image image=new Image(user.profilepicpath);
            Sotryuserprof[i]=new ImageView(CreatAccount.getRoundedImage(image,200));
            Sotryuserprof[i].setFitWidth(70);
            Sotryuserprof[i].setFitHeight(70);
            Sotryuserprof[i].setX(95*i+22);
            Sotryuserprof[i].setY(15);
            if(checkisClose(user)) {
                stories.getChildren().add(circle);
            }
            stories.getChildren().add(Sotryuserprof[i]);
            stories.getChildren().add(buttonsStory[i]);
                    i++;
        }


        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                for(int i=0;i<a;i++){
                    if(event.getSource()==buttonsStory[i])
                    {
                                storypic(users.get(i));
                    }
                }
                event.consume();
            }

        };
        for(int j=0;j<a;j++){
            buttonsStory[j].setOnMouseClicked(handler);
        }
    }

    public List<Story> createStorylist(User user1){
        List<Story> ss=new ArrayList<>();
        if(user1.CloseFriendList.contains(USER.UserName)){
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
    boolean ord=false;
public int index=0;
public Label label3=null,label4=null;

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
    Image image=new Image(user1.profilepicpath);
   ImageView tempprof=new ImageView(CreatAccount.getRoundedImage(image,200));
    tempprof.setFitWidth(120);
    tempprof.setFitHeight(120);
    tempprof.setX(110);
    tempprof.setY(70);
      //=========================================
    ImageView closebut=new ImageView(close);
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
    if(user1.CloseFriendList.contains(USER.UserName)){
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
    if(!userstories.get(index).viewersnameList.contains(USER.UserName)){
        userstories.get(index).viewersnameList.add(USER.UserName);
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
    PANE.getChildren().addAll(butLike,butClose,butnext,butback);
    if(ord&&label3!=null){PANE.getChildren().add(label3);}

    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {

            if(event.getSource()==butClose){
                index=0;
                PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4);
                if(PANE.getChildren().contains(label3)){PANE.getChildren().remove(label3);}

            }

          if(event.getSource()==butLike){
              if(!userstories.get(index).likersnameList.contains(USER.UserName)){
                    userstories.get(index).likersnameList.add(USER.UserName);
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
                PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4);
                    if(PANE.getChildren().contains(label3)){PANE.getChildren().remove(label3);}
                    storypic(user1);
            }
            }

            if(event.getSource()==butback){
                if(index>0){
                    index--;
                    PANE.getChildren().removeAll(imageView,imageView1,tempprof,closebut,backbut,nextbut,photo,LIKE,butClose,butLike,butnext,butback,label4);
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

public void exit(){
    Main.mainstage.close();
}
    public  void BACKtologin() throws IOException {
    Main.loginSTART();
    }

    public void viewLoginuser() throws IOException {
    Viewuser.ThisUser=USER;
    Main.ViewuserSTART();
    }

    public void newpost() throws IOException {
          if(  USER.Kind){
                          CreatenewBUSPost.user=USER;
                        Main.CreateBUSpostSTART();}
                      else {
                          CreatenewORDPost.user=USER;
                          Main.CreateORDpostSTART();}
    }
    public void myinfo() throws IOException {
        CompletePersonalInformation.user=USER;
        Main.MyinfoSTART();
    }
    public void newstory() throws IOException {
        CreatenewStory.user=USER;
        Main.AddStorySTART();
    }
    public void sugg() throws IOException {

     //   Suggestions.searchedposts = USER.posts;
     /*   List<User> tem=new ArrayList<>();
        for (User user : USER.FollowingMap.values()){
            tem.add(user);
    }*/
        //Suggestions.syggestedUsers=tem;
        Suggestions.USER=USER;
        Main.suggestSTART();
    }
    public void contact(){}
    public void direct(){}

}
