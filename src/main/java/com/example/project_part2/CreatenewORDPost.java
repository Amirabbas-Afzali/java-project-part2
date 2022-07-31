package com.example.project_part2;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.StaticTableDBC;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.OrdinaryPost;
import com.example.project_part2.POST.Post;
import com.example.project_part2.USER.BusinessUser;
import com.example.project_part2.USER.OrdinaryUser;
import com.example.project_part2.USER.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static com.example.project_part2.Main.mainstage;


public class CreatenewORDPost {
    public static   User user;

    public String profpath;
    @FXML
    TextArea cap;
    @FXML
    ImageView photo;
    @FXML
    Label label;


    public void Back() throws IOException {
        Main.personalpageSTART();
    }

    public void Create() throws SQLException, IOException {
        if (cap.getText().length() > 0 &&profpath!=null) {
            String str1=createpostcode();
            OrdinaryPost ordinaryPost = new OrdinaryPost(str1, cap.getText(), (OrdinaryUser) user, new Date(),((OrdinaryUser) user).Private,profpath);
            PostTableDBC.postTableDBC.setPost(ordinaryPost);
            MAINInformation.mainInformation.posts.put(str1,ordinaryPost);
            user.posts.add(ordinaryPost);
            Main.personalpageSTART();
        }
        else {label.setText("Error !");}
    }

    public void addprof() throws FileNotFoundException {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainstage);
        if (file != null) {
            Image image = new Image(new FileInputStream(file.getPath()));
            photo.setImage(image);
            profpath=file.getPath();
        }
    }

    public String createpostcode() throws SQLException {
        String result=String.valueOf(Post.PostCodeStatic);
        Post.PostCodeStatic++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("Post",Post.PostCodeStatic);
        return result;
    }
}
