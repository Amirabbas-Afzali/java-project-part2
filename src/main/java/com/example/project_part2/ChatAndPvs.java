package com.example.project_part2;

import com.example.project_part2.USER.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChatAndPvs implements Initializable {

    @FXML
    private AnchorPane ChatAndPvsAnchor;

    @FXML
    private AnchorPane MassagesAnchor;

    public static User Viewer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            String DMCode=DirectMassage.NewDirectMassage(Viewer.UserName,"reza1");
            DirectMassage directMassage1=MAINInformation.mainInformation.directmassages.get(DMCode);
            DMHandler dmHandler=new DMHandler(directMassage1,ChatAndPvsAnchor,Viewer,5,5);
            dmHandler.SetY(50);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void LoadDms(){
        ChatAndPvsAnchor.getChildren().clear();
    }

    @FXML
    void Back() {
        try {
            Main.personalpageSTART();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void OpenDirectMassage(DirectMassage directMassage, User Viewer){

    }
}