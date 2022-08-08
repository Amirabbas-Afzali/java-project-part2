package com.example.project_part2;

import com.example.project_part2.DataBaseController.PostTableDBC;
import com.example.project_part2.DataBaseController.StaticTableDBC;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.Post;
import com.example.project_part2.USER.BusinessUser;
import com.example.project_part2.USER.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class CreatenewBUSPost implements Initializable {

  //  Image image1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\plouzane-lighthouse-in-france-near-the-sea-ocean-111-big.jpg");
 //   Image image2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\theme2\\3.jpg");
  //  Image image3=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\dark.jpg");

    public void SETThEME(){
        if(CompletePersonalInformation.SetTheme==1){
            anch1.getStylesheets().add(getClass().getResource("Style3a/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==2){
            anch1.getStylesheets().add(getClass().getResource("Style3/ss.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
        else if(CompletePersonalInformation.SetTheme==3){
            anch1.getStylesheets().add(getClass().getResource("StyleDark/dark.css").toExternalForm());
            anch1.getStyleClass().add("body");
        }
    }
    @FXML
    AnchorPane anch1;

  public static   User user;

public String profpath;
    @FXML
    TextArea cap,des;
    @FXML
    ImageView photo,bckgr;
    @FXML
    Label label;


    public void Back() throws IOException {
        Main.personalpageSTART();
    }

    public void Create() throws SQLException, IOException {
        if (cap.getText().length() > 0 && des.getText().length() > 0&&profpath!=null) {
            String str1=createpostcode();
            BusinessPost businessPost = new BusinessPost(str1, cap.getText(), des.getText(), (BusinessUser) user, new Date(),profpath);
            PostTableDBC.postTableDBC.setPost(businessPost);
            MAINInformation.mainInformation.posts.put(str1,businessPost);
           user.posts.add(businessPost);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SETThEME();
    }
}
