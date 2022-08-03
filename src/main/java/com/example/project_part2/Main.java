package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main extends Application {
    static Main main1=new Main();
    public static Stage mainstage;
    public static Scanner scanner = new Scanner(System.in);
    @Override
    public  void start(Stage stage) throws IOException {
        mainstage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        stage.setTitle("OWL");
        stage.getIcons().add(new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\icon\\AppIcon.png"));
        stage.setScene(scene);
        stage.show();
    }


    public static void createaccountSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("CreateNewAccount.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Create New Account");
        mainstage.setScene(scene);
       // mainstage.show();
    }

    public static void passwordrecovSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("passrec.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Password recovery");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void loginSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Login");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void CreateBUSpostSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createbuspost.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Create Post");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void CreateORDpostSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createordpost.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Create Post");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void personalpageSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("personalpage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("OWL");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void suggestSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("suggestion.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("OWL");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void MyinfoSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("completeMyinfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Complete/Edit information");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void AddStorySTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("createStory.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle("Add new story");
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void ViewuserSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewuser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle(PersonalHomepage.USER.UserName);
        mainstage.setScene(scene);
    }

    public static void ViewuserPrivateSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewuserprivate.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle(PersonalHomepage.USER.UserName);
        mainstage.setScene(scene);
    }

    public static void ViewOtheruserSTART() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("viewotherusers.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1300, 750);
        mainstage.setTitle(ViewOtherUsers.ThisUser.UserName);
        mainstage.setScene(scene);
        // mainstage.show();
    }

    public static void main(String[] args)throws SQLException {
        MAINInformation.mainInformation.UpdateMainInfo();
       //SignIn.signIn.start();
        launch();
    }

}