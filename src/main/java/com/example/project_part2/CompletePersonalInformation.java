package com.example.project_part2;

import com.example.project_part2.DateFormat;
import com.example.project_part2.Main;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class CompletePersonalInformation implements Initializable {
    Image image1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\blue-sea-water-and-beach-from-birds-eye-view-for-background-119-medium.jpg");
    Image image2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\theme2\\1.jpg");
    Image image3=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\dark.jpg");

    public void SETThEME(){
        if(CompletePersonalInformation.SetTheme==1){
        bckgr.setImage(image1);}
        else if(CompletePersonalInformation.SetTheme==2){
            bckgr.setImage(image2);
        }
        else if(CompletePersonalInformation.SetTheme==3){
            bckgr.setImage(image3);
        }
    }

    public static com.example.project_part2.CompletePersonalInformation completePersonalInformation=new com.example.project_part2.CompletePersonalInformation();
    public static User user;
    @FXML
    ImageView prof;
    @FXML
     ImageView bckgr;
    @FXML
    Label ordorbus,label12;
    @FXML
    TextField nameF,cityF,Countryf,Biographyf;
    @FXML
    CheckBox male,female,marr,notmarr,them1,them2,them3;
public static int SetTheme=1;
    public String profpath;
public static  ImageView aq =new ImageView();

    public void start() throws SQLException {
        boolean flag = true, flag2 = true, flag3 = true;
        String male,married,city , country ,bio,name;

        System.out.println("Username : "+user.UserName+"\nPassword : "+user.PassWord+
                "\nBirthDate : "+ DateFormat.dateFormat.reportdate2(user.Birthdate)+"\nAge : "
                +user.age);


        System.out.println("Enter your Name :");
        name= Main.scanner.nextLine();
        if(name.length()>0){user.setName(name);}

        System.out.println("Are you male?\n1.Yes\n2.No");
        while (flag2){
            male = Main.scanner.nextLine();
            if(male.equals("1")){user.setIsman(true);flag2=false;}
            else  if(male.equals("2")){user.setIsman(false);flag2=false;}
            else {System.out.println("Invalid command!");}
        }

        System.out.println("Are you married?\n1.Yes\n2.No");
        while (flag3){
            married= Main.scanner.nextLine();
            if(married.equals("1")){user.setMarried(true);flag3=false;}
            else  if(married.equals("2")){user.setMarried(false);flag3=false;}
            else {System.out.println("Invalid command!");}
        }

        System.out.println("Enter your City name :");
        city= Main.scanner.nextLine();
        if(city.length()>0){user.setCity(city);}

        System.out.println("Enter your Country name :");
        country= Main.scanner.nextLine();
        if(country.length()>0){user.setCountry(country);}

        System.out.println("Enter your Biography :");
        bio= Main.scanner.nextLine();
        if(bio.length()>0){user.setBio(bio);}

        System.out.println("Information updated successful.");
    }

    public void them1func(){
        them1.setSelected(true);
        them2.setSelected(false);them3.setSelected(false);
        SetTheme=1;
        SETThEME();
    }

    public void them2func(){
        them1.setSelected(false);them3.setSelected(false);
        them2.setSelected(true);
        SetTheme=2;
        SETThEME();
    }
    public void them3func(){
        them1.setSelected(false);them3.setSelected(true);
        them2.setSelected(false);
        SetTheme=3;
        SETThEME();
    }


    public void Back() throws IOException {
        Main.personalpageSTART();
    }

    public void Create() throws SQLException, IOException {
        user.setName(nameF.getText());
        user.setCity(cityF.getText());
        user.setCountry(Countryf.getText());
        user.setBio(Biographyf.getText());
        if(male.isSelected()){user.setIsman(true);}
        else {user.setIsman(false);}
        if(marr.isSelected()){user.setMarried(true);}
        else {user.setMarried(false);}
        user.setProfilepicpath(profpath);

        Main.personalpageSTART();
    }

    public void notmarrfunc(){
        marr.setSelected(false);
    }
    public void marrfunc(){
        notmarr.setSelected(false);
    }
    public void malefunc(){
        female.setSelected(false);
    }
    public void femalefunc(){
        male.setSelected(false);
    }


    public void addprof() throws FileNotFoundException {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainstage);
        if (file != null) {
            Image image = new Image(new FileInputStream(file.getPath()));
            prof.setImage(getRoundedImage(image,200));
            profpath=file.getPath();
        }
    }

    public static Image getRoundedImage(Image image, double radius) {
        Circle clip = new Circle(image.getWidth() / 2, image.getHeight() / 2, radius);
        ImageView imageView = new ImageView(image);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        return imageView.snapshot(parameters, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SETThEME();
        if(CompletePersonalInformation.SetTheme==1){
            them1.setSelected(true);
            them2.setSelected(false);them3.setSelected(false);}
        else if(CompletePersonalInformation.SetTheme==2){
            them1.setSelected(false);them3.setSelected(false);
            them2.setSelected(true);
        }
        else if(CompletePersonalInformation.SetTheme==3){
            them1.setSelected(false);them3.setSelected(true);
            them2.setSelected(false);
        }

      if(user.Kind){
          BusinessUser businessUser=(BusinessUser) user;
          ordorbus.setText("Business User ("+businessUser.buisnessType +")");}
      else {
          OrdinaryUser businessUser=(OrdinaryUser) user;
          if(businessUser.Private){
          ordorbus.setText("Ordinary User (Private)");}
          else {
              ordorbus.setText("Ordinary User (Public)");}
      }
        label12.setText(user.UserName);
      if(user.Name!=null){nameF.setText(user.Name);}
        if(user.City!=null){cityF.setText(user.City);}
        if(user.Country!=null){Countryf.setText(user.Country);}
        if(user.Bio!=null){Biographyf.setText(user.Bio);}

        if(user.married){marr.setSelected(true);notmarr.setSelected(false);}
        else {       marr.setSelected(false);notmarr.setSelected(true);}

        if(user.isman){male.setSelected(true);female.setSelected(false);}
                else {male.setSelected(false);female.setSelected(true);}

if(user.profilepicpath!=null){profpath=user.profilepicpath;
Image image=new Image(profpath);
prof.setImage(getRoundedImage(image,200));
}

    }
    }

