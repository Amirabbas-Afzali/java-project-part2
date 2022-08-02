package com.example.project_part2;

import com.example.project_part2.POST.Post;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;
public class ShowPostFXML implements Initializable {
    public static Post post;
    public static Massage massage;
    @FXML
    private AnchorPane CommentAnchor;
    @FXML
    private ScrollPane CommentScroll;
    @FXML
    private AnchorPane LikeAnchor;
    @FXML
    private ScrollPane LikesScroll;
    @FXML
    private Pane MainPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       /* ShowMassageHandler showMassageHandler=new ShowMassageHandler(massage,50,5,Main.ordinaryUser);
        showMassageHandler.AddtoRoot(CommentAnchor);
        PreShowUser preShowUser=new PreShowUser(Main.ordinaryUser,5,5,LikeAnchor);
        preShowUser.setX(20);
        preShowUser.setY(50);
        showMassageHandler.ShiftY(50);
        showMassageHandler.ShiftY(100);*/


    }
}