package com.example.project_part2;

import com.example.project_part2.DataBaseController.MassageTableDBC;
import com.example.project_part2.USER.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShowMassageHandler {
    public static List<ShowMassageHandler> showMassageHandlers;
    // public static ImageView BackGround;
    boolean ShowExtra=false,isReply=false;
    User Viewer;

    Button OptionButton=new Button(),ProfButton=new Button(),LikeButton=new Button()
            ,ReplyButton=new Button(),ForwardButton=new Button(),MoreOptionButton=new Button(),EditButton=new Button(),EditButton2=new Button();
    ImageView imageView,LikeImage,DisLikeImage,ReplyImage,ForwardImage,MoreOptionImage;
    AnchorPane myPane;
    Massage massage;
    String text;
    TextArea EditArea=new TextArea();
    Label label=new Label(),Likeslabel=new Label(),Comments=new Label();
    Rectangle rectangle=new Rectangle(),UnderRec=new Rectangle(),ColorRec=new Rectangle(),ReplytoRec=new Rectangle();
    ShowMassageHandler(){}
    ShowMassageHandler(Massage _massage, double X, double Y,User user,Color color,boolean isColor){
        if (isColor){
            isReply=true;
            ReplytoRec.setFill(color);

        }
        Random rand = new Random(System.currentTimeMillis());
        ColorRec.setFill(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255),0.99));
        Viewer=user;
        Image image=new Image(Viewer.profilepicpath);
        image=getRoundedImage(image,200);
        imageView=new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        imageView.setTranslateX(X-47);
        imageView.setPreserveRatio(true);


        image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\like.png");
        LikeImage=new ImageView(image);
        LikeImage.setFitHeight(25);
        LikeImage.setFitWidth(25);

        image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\dislike.png");
        DisLikeImage=new ImageView(image);
        DisLikeImage.setFitHeight(25);
        DisLikeImage.setFitWidth(25);

        image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\Reply.png");
        ReplyImage=new ImageView(image);
        ReplyImage.setFitHeight(25);
        ReplyImage.setFitWidth(25);

        image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\forward.png");
        ForwardImage=new ImageView(image);
        ForwardImage.setFitHeight(25);
        ForwardImage.setFitWidth(25);

        image=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\moreoption.png");
        MoreOptionImage=new ImageView(image);
        MoreOptionImage.setFitHeight(25);
        MoreOptionImage.setFitWidth(25);

        OptionButton.setText("Options");
        String _text=_massage.massageString;
        massage=_massage;
        OptionButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#797EF6"),null,null)));
        OptionButton.setFont(Font.font(8));
        StringBuilder stringBuilder=new StringBuilder();
        double a=((Math.ceil( (double) _text.length()/45)*17)+5+15);

        while (_text.length()>45){
            stringBuilder.append(_text, 0, 45).append("\n");
            _text=_text.substring(45);
        }
        stringBuilder.append(_text).append("\n").append(DateFormat.dateFormat.reportdate2(massage.date)).append("    Sender : ").append(massage.SenderUserName);
        text=_text;
        label.setText(stringBuilder.toString());
        label.setTranslateX(X);
        label.setTranslateY(Y);
        rectangle.setTranslateX(X-5);
        rectangle.setTranslateY(Y);
        rectangle.setWidth(300);
        rectangle.setHeight(a);
        UnderRec.setWidth(300);
        UnderRec.setHeight(18);
        UnderRec.setTranslateX(X-5);
        UnderRec.setTranslateY(rectangle.getTranslateY()+rectangle.getHeight()-18);
        OptionButton.setTranslateX(UnderRec.getTranslateX()+240);
        OptionButton.setTranslateY(UnderRec.getTranslateY());
        OptionButton.setMaxHeight(18);
        rectangle.setFill(Color.valueOf("#4ADEDE"));
        UnderRec.setFill(Color.valueOf("#1AA7EC"));
        imageView.setTranslateY(UnderRec.getTranslateY()-20);

        ProfButton.setTranslateX(X-43);
        ProfButton.setTranslateY(UnderRec.getTranslateY()-17);
        ProfButton.setText("   ");
        ProfButton.setBackground(null);

        setColor();
        if (isReply){
            setColor1();
        }



        OptionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MassageOption();
            }
        });
        ProfButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Show user Prof");
            }
        });
        LikeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!massage.UserLikedBefore(Viewer.UserName)){
                    try {
                        massage.AddLike(Viewer.UserName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        massage.RemoveLike(Viewer.UserName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ShowExtra();
                labelUpdate();
            }
        });
        ForwardButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //MassageOption();
                System.out.println("Forward");
                Forward();
            }
        });
        ReplyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // MassageOption();
                System.out.println("reply");
                addReply();
                labelUpdate();
            }

        });

        MoreOptionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                System.out.println("More option");
                ShowReplys();

            }
        });
        EditButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Edit button");
                EditMassage();
            }
        });
        EditButton2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Edit button2");
                EditMassage2();
            }
        });

    }
    public void EditMassage(){
        EditButton2.setTranslateX(EditButton.getTranslateX()+70);
        EditButton2.setTranslateY(EditButton.getTranslateY());
        EditButton2.setText("Edit");
        EditArea.setTranslateY(EditButton.getTranslateY()-60);
        EditArea.setTranslateX(EditButton.getTranslateX()+40);
        EditArea.setMaxWidth(100);
        EditArea.setMaxHeight(50);
        try {
            myPane.getChildren().add(EditArea);
            myPane.getChildren().add(EditButton2);
        }
        catch (Exception e){
            System.out.println("error EditMassage");
        }
    }
    public void EditMassage2(){
        if (EditArea.getText()!=null&&!"".equals(EditArea.getText())){
            massage.massageString=EditArea.getText().replaceAll("\n"," ");
            UpdateText(EditArea.getText().replaceAll("\n"," "));
            try {
                MassageTableDBC.massageTableDBC.EditOrDeleteMassage(massage,false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        this.DisShowExtra();
        myPane.getChildren().remove(EditButton2);
        myPane.getChildren().remove(EditArea);
    }
    public void UpdateText(String _text){
        double Y=this.rectangle.getHeight();
        StringBuilder stringBuilder=new StringBuilder();
        double a=((Math.ceil( (double) _text.length()/45)*17)+5+15);

        while (_text.length()>45){
            stringBuilder.append(_text, 0, 45).append("\n");
            _text=_text.substring(45);
        }
        stringBuilder.append(_text).append("\n").append(DateFormat.dateFormat.reportdate2(massage.date)).append("    Sender : ").append(massage.SenderUserName);
        text=_text;
        label.setText(stringBuilder.toString());
        rectangle.setHeight(a);
        UnderRec.setTranslateY(rectangle.getTranslateY()+rectangle.getHeight()-18);
        OptionButton.setTranslateY(UnderRec.getTranslateY());
        imageView.setTranslateY(UnderRec.getTranslateY()-20);
        ProfButton.setTranslateY(UnderRec.getTranslateY()-17);
        double delta=(this.rectangle.getHeight()-Y);
        for (ShowMassageHandler i:showMassageHandlers){
            if (i.rectangle.getTranslateY()>this.rectangle.getTranslateY()){
                i.ShiftY(delta);
            }
        }
        // TODO: 8/3/2022 check
        myPane.setPrefHeight(myPane.getHeight()+delta);
    }
    public void setColor1(){
        ReplytoRec.setTranslateX(rectangle.getTranslateX());
        ReplytoRec.setTranslateY(rectangle.getTranslateY()-4);
        ReplytoRec.setWidth(rectangle.getWidth());
        ReplytoRec.setHeight(4);
    }
    public void setColor(){
        //color setting
        ColorRec.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth());
        ColorRec.setTranslateY(rectangle.getTranslateY());
        ColorRec.setWidth(7);
        ColorRec.setHeight(10);
        //done
    }
    public void AddtoRoot(AnchorPane anchorPane){
        myPane=anchorPane;
        anchorPane.getChildren().add(rectangle);
        myPane.getChildren().add(imageView);

        anchorPane.getChildren().add(UnderRec);
        anchorPane.getChildren().add(label);
        myPane.getChildren().add(OptionButton);
        myPane.getChildren().add(ProfButton);
        myPane.getChildren().add(ColorRec);
        if (isReply){
            myPane.getChildren().add(ReplytoRec);
        }
    }
    public double getDown(){
        return (rectangle.getTranslateY()+rectangle.getHeight());
    }
    public double getHeight(){
        return rectangle.getHeight();
    }
    public double getUp(){
        return rectangle.getTranslateY();
    }
    public void setX(double X){
        label.setTranslateX(X);
        rectangle.setTranslateX(X-5);
        UnderRec.setTranslateX(X-5);
        OptionButton.setTranslateX(UnderRec.getTranslateX()+240);
        imageView.setTranslateX(X-47);
        ProfButton.setTranslateX(X-43);
        setColor();
        setExtra();
        if (isReply){
            setColor1();
        }
    }
    public void setY(double Y){
        rectangle.setTranslateY(Y);
        label.setTranslateY(Y);
        UnderRec.setTranslateY(rectangle.getTranslateY()+rectangle.getHeight()-18);
        OptionButton.setTranslateY(UnderRec.getTranslateY());
        imageView.setTranslateY(UnderRec.getTranslateY()-20);
        ProfButton.setTranslateY(UnderRec.getTranslateY()-17);
        setColor();
        setExtra();
        if (isReply){
            setColor1();
        }

    }
    public void setExtra(){
        DisLikeImage.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        DisLikeImage.setTranslateY(OptionButton.getTranslateY()-25);
        DisLikeImage.setPreserveRatio(true);

        LikeImage.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        LikeImage.setTranslateY(OptionButton.getTranslateY()-25);
        LikeImage.setPreserveRatio(true);

        LikeButton.setText("  ");
        LikeButton.setBackground(null);
        LikeButton.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        LikeButton.setTranslateY(OptionButton.getTranslateY()-25);

        ReplyImage.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        ReplyImage.setTranslateY(OptionButton.getTranslateY());
        ReplyImage.setPreserveRatio(true);

        ReplyButton.setText("   ");
        ReplyButton.setBackground(null);
        ReplyButton.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        ReplyButton.setTranslateY(OptionButton.getTranslateY());

        ForwardImage.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        ForwardImage.setTranslateY(OptionButton.getTranslateY()+25);
        ForwardImage.setPreserveRatio(true);

        ForwardButton.setText("  ");
        ForwardButton.setBackground(null);
        ForwardButton.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        ForwardButton.setTranslateY(OptionButton.getTranslateY()+25);

        MoreOptionImage.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        MoreOptionImage.setTranslateY(OptionButton.getTranslateY()+50);
        MoreOptionImage.setPreserveRatio(true);

        MoreOptionButton.setText("  ");
        MoreOptionButton.setBackground(null);
        MoreOptionButton.setTranslateX(rectangle.getTranslateX()+rectangle.getWidth()+10);
        MoreOptionButton.setTranslateY(OptionButton.getTranslateY()+50);
        if (massage.SenderUserName.equals(Viewer.UserName)) {
            EditButton.setText("Edit");
            EditButton.setTranslateX(rectangle.getTranslateX() + rectangle.getWidth() + 10);
            EditButton.setTranslateY(OptionButton.getTranslateY() + 75);
        }
        Likeslabel.setTranslateX(LikeImage.getTranslateX()+25);
        Likeslabel.setTranslateY(LikeImage.getTranslateY()+5);

        Comments.setTranslateX(ReplyImage.getTranslateX()+25);
        Comments.setTranslateY(ReplyImage.getTranslateY()+5);

    }
    public void ShowExtra(){
        if (massage.UserLikedBefore(Viewer.UserName)){
            myPane.getChildren().remove(LikeImage);
            myPane.getChildren().add(DisLikeImage);
        }
        else {
            myPane.getChildren().remove(DisLikeImage);
            myPane.getChildren().add(LikeImage);
        }
        if (!myPane.getChildren().contains(LikeButton))
            myPane.getChildren().add(LikeButton);
        if (!myPane.getChildren().contains(ReplyImage)){
            myPane.getChildren().add(ReplyImage);
        }
        if (!myPane.getChildren().contains(ReplyButton)){
            myPane.getChildren().add(ReplyButton);
        }
        if (!myPane.getChildren().contains(ForwardImage)){
            myPane.getChildren().add(ForwardImage);
        }
        if (!myPane.getChildren().contains(ForwardButton)){
            myPane.getChildren().add(ForwardButton);
        }
        if (!myPane.getChildren().contains(MoreOptionImage)){
            myPane.getChildren().add(MoreOptionImage);
        }
        if (!myPane.getChildren().contains(MoreOptionButton)){
            myPane.getChildren().add(MoreOptionButton);
        }
        labelUpdate();
        if (!myPane.getChildren().contains(Likeslabel)){
            myPane.getChildren().add(Likeslabel);
        }
        if (!myPane.getChildren().contains(Comments)){
            myPane.getChildren().add(Comments);
        }
        if (!myPane.getChildren().contains(EditButton)&&massage.SenderUserName.equals(Viewer.UserName)){
            myPane.getChildren().add(EditButton);
        }

    }
    public void MassageOption(){
        System.out.println("Massage Options");
        if (!ShowExtra){
            setExtra();
            ShowExtra();
            ShowExtra=true;
        }
        else {
            ShowExtra=false;
            DisShowExtra();
        }
    }
    public void DisShowExtra(){
        myPane.getChildren().remove(LikeButton);
        myPane.getChildren().remove(LikeImage);
        myPane.getChildren().remove(DisLikeImage);
        myPane.getChildren().remove(ReplyImage);
        myPane.getChildren().remove(ReplyButton);
        myPane.getChildren().remove(ForwardImage);
        myPane.getChildren().remove(ForwardButton);
        myPane.getChildren().remove(MoreOptionImage);
        myPane.getChildren().remove(MoreOptionButton);
        myPane.getChildren().remove(Likeslabel);
        myPane.getChildren().remove(Comments);
        if (massage.SenderUserName.equals(Viewer.UserName)){
            myPane.getChildren().remove(EditButton);
        }
    }
    public void addReply(){
        TextArea textField=new TextArea();
        textField.setTranslateX(ReplyImage.getTranslateX()+30);
        textField.setTranslateY(ReplyImage.getTranslateY());
        textField.setMaxHeight(100);
        textField.setMaxWidth(150);
        Button button=new Button();
        button.setTranslateX(textField.getTranslateX()+50);
        button.setTranslateY(textField.getTranslateY()+105);
        button.setText("Add");
        myPane.getChildren().add(textField);
        myPane.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("new reply : "+textField.getText().replaceAll("\n"," "));

                try {
                    Massage massage1 = new Massage(textField.getText().replaceAll("\n"," "),Viewer);
                    massage.AddReplyToList(massage1.massageCode);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                labelUpdate();
                myPane.getChildren().remove(button);
                myPane.getChildren().remove(textField);
            }
        });
    }
    public void Forward(){
        ListView<String > listView=new ListView<>();
        Button button=new Button();
        listView.setItems(FXCollections.observableArrayList(Viewer.DirectMassageCodes));
        listView.setMaxWidth(150);
        listView.setMaxHeight(100);
        listView.setTranslateX(ForwardButton.getTranslateX()+30);
        listView.setTranslateY(ForwardButton.getTranslateY()-50);
        button.setTranslateX(listView.getTranslateX()+50);
        button.setTranslateY(listView.getTranslateY()+105);
        button.setText("Forward");
        myPane.getChildren().add(listView);
        myPane.getChildren().add(button);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Forwarded to "+listView.getSelectionModel().getSelectedItem());
                // TODO: 8/5/2022 Did forward
                try {
                    String code1 = Massage.NewMassage(Viewer.UserName,massage.massageString);
                    MAINInformation.mainInformation.directmassages.get(Viewer.DMNameToCode(listView.getSelectionModel().getSelectedItem())).addMassage(code1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                myPane.getChildren().remove(button);
                myPane.getChildren().remove(listView);
            }
        });

    }
    public void ShiftX(double howMuch){
        setX(getRight()+howMuch);
    }
    public void ShiftY(double howMuch){
        setY(getUp()+howMuch);
    }
    public double getRight(){
        return rectangle.getTranslateX();
    }
    public double getLeft(){
        return rectangle.getTranslateX()+rectangle.getWidth();
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
    void labelUpdate(){
        Likeslabel.setText(Integer.toString(massage.LikeCodes.size()));
        Massage.sum=0;
        massage.GetThreadSum();
        Massage.sum--;
        Comments.setText(Massage.sum.toString());
        Massage.sum=0;
    }
    public void ShowReplys(){
        List<ShowMassageHandler> toAdd=new ArrayList<>();
        double Y=getDown()+10,Y0=getDown()+10;

        for (String i:massage.ReplyMassagesCodes){
            toAdd.add(new ShowMassageHandler(MAINInformation.mainInformation.massages.get(i),50,Y,Viewer, (Color) this.ColorRec.getFill(),true));
            Y+=(toAdd.get(toAdd.size()-1).getHeight()+10);
        }
        for (ShowMassageHandler i: showMassageHandlers){
            if (i.getUp()>this.getUp()){
                i.ShiftY(Y-Y0);
            }
        }
        showMassageHandlers.addAll(toAdd);
        UpdateCommentAfter();
    }
    void UpdateCommentAfter(){
        double Y=0;
        myPane.getChildren().clear();
        for (ShowMassageHandler i:showMassageHandlers){
            i.AddtoRoot(myPane);
            Y+=(i.getHeight()+10);
        }
        myPane.setPrefHeight(Y+200);
        myPane.setMaxHeight(Y+200);
    }
}