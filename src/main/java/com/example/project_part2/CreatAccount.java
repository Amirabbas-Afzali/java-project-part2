package com.example.project_part2;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.POST.*;
import com.example.project_part2.PersonalHomepage;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class CreatAccount implements Initializable {
    public static CreatAccount creatAccount=new CreatAccount();

    public void start() throws SQLException {
        boolean flag=true,flag2=true;
        while (flag){
            String description="", temp = "", birthdate, usename="", password = "",usertype="", password2 = "", str, strtemp = "Enter a number:\n1.Create ordinary account\n2.Create business account\n3.end";
            System.out.println(strtemp);
            str = Main.scanner.nextLine();
            str.trim();
            if (str.equals("1")) {
                while (flag2) {
                    temp="";
                    System.out.println("Enter the username :");
                    usename = Main.scanner.nextLine();
                    if (!existUsername(usename)) {
                        System.out.println("Enter the password :");
                        password = Main.scanner.nextLine();
                        if(passtextisnormal(password)){
                            System.out.println("Re-enter the password :");
                            password2 = Main.scanner.nextLine();
                            if (password.equals(password2)) {
                                System.out.println("Enter the birthdate (dd/mm/yyyy) :");
                                birthdate = Main.scanner.nextLine();
                                if (birthdateformisnormal(birthdate)) {
                                    System.out.println("Enter a number :\n1.Create private account\n2.Create public account");
                                    description = Main.scanner.nextLine();
                                    description.trim();
                                   // OrdinaryUser ordinaryUser=new OrdinaryUser(usename,password,birthdate,isprivate(description));
                                  //  UserTableDBC.userTableDBC.setUser(ordinaryUser);
                                //    MAINInformation.mainInformation.users.put(usename,ordinaryUser);
                                    temp = "a";
                                    flag2=false;
                                }
                            } else {
                                System.out.println("Re-enter the password correctly !");
                            }}
                        else{
                            System.out.println("Your password must be 8 characters including upper and lower case letters and numbers !");
                        }
                    } else {
                        System.out.println("This username was taken !");
                    }
                }
                if(temp.equals("a")){
                    PersonalHomepage.personalHomepage.USER=searchuser(usename);
                    PersonalHomepage.personalHomepage.start(searchuser(usename));}
            }
            else if (str.equals("2")) {
                while (flag2) {
                    temp="";
                    System.out.println("Enter the username :");
                    usename = Main.scanner.nextLine();
                    if (!existUsername(usename)) {
                        System.out.println("Enter the password :");
                        password = Main.scanner.nextLine();
                        if(passtextisnormal(password)){
                            System.out.println("Re-enter the password :");
                            password2 = Main.scanner.nextLine();
                            if (password.equals(password2)) {
                                System.out.println("Enter the birthdate (dd/mm/yyyy) :");
                                birthdate = Main.scanner.nextLine();
                                if (birthdateformisnormal(birthdate)) {
                                    System.out.println("Select your business account type :\n1.Tech\n2.Cloth\n3.AD\n4.Artist\n5.Sport");
                                    usertype= Main.scanner.nextLine();
                                   // BusinessUser businessUser=new BusinessUser(usename,password,birthdate,usertype);
                                  //  UserTableDBC.userTableDBC.setUser(businessUser);
                                 //   MAINInformation.mainInformation.users.put(usename,businessUser);
                                    temp = "a";
                                    flag2=false;
                                }
                            }
                            else {
                                System.out.println("Re-enter the password correctly !");
                            }
                        }
                        else{
                            System.out.println("Your password must be 8 characters including upper and lower case letters and numbers !");
                        }
                    }
                    else {
                        System.out.println("This username was taken !");
                    }
                }
                if(temp.equals("a")){
                    PersonalHomepage.personalHomepage.USER=searchuser(usename);
                    PersonalHomepage.personalHomepage.start(searchuser(usename));}
            }
            else if (str.equals("3")) {
                System.out.println("bye!");
                flag=false;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
    public boolean existUsername(String username){
        for(User user: MAINInformation.mainInformation.users.values()){
            if(user.UserName.equals(username)){
                return true;
            }
        }
        return false;
    }
    public boolean birthdateformisnormal(String birthdate){
        if(birthdate.length()!=10){return  false;}
        if(birthdate.charAt(2)!='/'){return false;}
        if(birthdate.charAt(5)!='/'){return false;}
        return  true;
    }
    public User searchuser(String username){
        for(User user: MAINInformation.mainInformation.users.values()){
            if(user.UserName.equals(username)){
                return user;
            }
        }
        return  null;
    }

    public boolean passtextisnormal(String password){
        if(password.length()!=8){return false;}
        boolean big=false,small=false,numb=false;
        for(int i=0;i<8;i++){
            if((int) password.charAt(i)>96&&(int) password.charAt(i)<123){small = true;}
            if((int) password.charAt(i)>47&&(int) password.charAt(i)<58){numb = true;}
            if((int) password.charAt(i)>64&&(int) password.charAt(i)<91){big = true;}
        }
        if(small&&big&&numb){return  true;}
        return false;
    }
    public boolean isprivate(String strr){
        if(strr.equals("1")){return  true;}
        return false;
    }

@FXML
ImageView prof;
    @FXML
    TextField usernameF,dateF;
    @FXML
    PasswordField passF,repassF;
    @FXML
    Label label;
    @FXML
    DatePicker dateB;
    @FXML
    ChoiceBox bus,ord;
    @FXML
    CheckBox buschoice,ordchoice;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Enter your information :");
        List<String> list=new ArrayList<>();
        list.add("Technology");list.add("Cloth");list.add("AD");list.add("Art");list.add("Sport (default)");
        bus.setItems(FXCollections.observableList(list));
        List<String> list2=new ArrayList<>();
        list2.add("Private");list2.add("Public (default)");
        ord.setItems(FXCollections.observableList(list2));
        buschoice.setSelected(true);

//\n1.Tech\n2.Cloth\n3.AD\n4.Artist\n5.Sport"
    }

    public void busfill(){ordchoice.setSelected(false);}
    public void ordfill(){
        buschoice.setSelected(false);
    }

    public String profpath;

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

    public void Back() throws IOException {
        Main.main1.start(mainstage);

    }

    public void Create() throws SQLException, IOException {
        String usernam,pass2,pass1;

        if(usernameF.getText().length()>0&&passF.getText().length()>0&&repassF.getText().length()>0
                &&dateB.getValue()!=null&&profpath!=null){
             usernam=usernameF.getText();
             pass1=passF.getText();
             pass2=repassF.getText();

      if(!existUsername(usernam)){
          if(passtextisnormal(pass1)){
              if(pass1.equals(pass2)){
              if(ordchoice.isSelected()){
                  String ch= "a";
                if(ord.getValue()!=null){   ch=(String) ord.getValue();}
                  if(ch.equals("Private")){
                     OrdinaryUser ordinaryUser=new OrdinaryUser(usernam,pass1,dateB.getValue().toString() ,true,profpath);
                      UserTableDBC.userTableDBC.setUser(ordinaryUser);
                      MAINInformation.mainInformation.users.put(usernam,ordinaryUser);
                  }
                  else {
                     OrdinaryUser ordinaryUser=new OrdinaryUser(usernam,pass1,LocalToDate(dateB.getValue()),false,profpath);
                      UserTableDBC.userTableDBC.setUser(ordinaryUser);
                      MAINInformation.mainInformation.users.put(usernam,ordinaryUser);
                  }
              }
              else {
                  String ch= "a";
                  if(bus.getValue()!=null){   ch=(String) bus.getValue();}
                  BusinessUser businessUser=new BusinessUser(usernam,pass1,LocalToDate(dateB.getValue()),findtype(ch),profpath);
                  UserTableDBC.userTableDBC.setUser(businessUser);
                  MAINInformation.mainInformation.users.put(usernam,businessUser);
              }
              Main.main1.start(mainstage);
              }
              else {label.setText("Re-enter the password correctly !");}
          }
          else {label.setText("Your password must be 8 characters including upper and lower case letters and numbers !");}
      }
      else {label.setText("This Username was taken !");}
        }
        else {
label.setText(" Enter your information correctly ");
        }

    }

    public static Date LocalToDate(LocalDate input){
        ZoneId zoneId=ZoneId.systemDefault();
        Date result=Date.from(input.atStartOfDay(zoneId).toInstant());
        return result;
    }

    public String findtype(String str){
        //\n1.Tech\n2.Cloth\n3.AD\n4.Artist\n5.Sport"
if(str.equals("Technology")){return "1";}
        if(str.equals("Cloth")){return "2";}
        if(str.equals("AD")){return "3";}
        if(str.equals("Art")){return "4";}
        if(str.equals("Sport")){return "5";}
        return "5";

    }

}
