package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class PasswordRecovery implements Initializable {
    static PasswordRecovery passwordRecovery=new PasswordRecovery();
    @FXML
    Label labelpass,label;
    @FXML
    TextField cityF,countryF,usernameF;
    @FXML
    DatePicker dateB;

    public void start(){



        boolean flag1=true,flag2=true,endprogram=false;
        String City,usename="",password="",country,birthdate;

        while (flag1) {
            System.out.println("Enter your Username :");
            usename=Main.scanner.nextLine();
            System.out.println("Enter your City's name :");
            City=Main.scanner.nextLine();
            System.out.println("Enter your Country's name :");
            country=Main.scanner.nextLine();
            System.out.println("Enter your Birthday :");
            birthdate=Main.scanner.nextLine();
            System.out.println(SignIn.signIn.existuser(usename));
            if(SignIn.signIn.existuser(usename).equals("This user exists")){
           /*     if(checkinfo(SignIn.signIn.searchuser(usename),City,country,birthdate)){
                    System.out.println("Your password is : "+SignIn.signIn.searchuser(usename).PassWord);
                    flag1=false;
                }
                else{
                    System.out.println("The information entered is incorrect !");
                }*/
            }

        }
    }
    public boolean checkinfo(User user, String city, String country/*, Date birth*/){
        if (user.City==null){
            System.out.println("You don't have enough info for recovery");
            return false;
        }
        else {
            if(!user.City.equals(city)){return  false;}
        }
        if (user.Country==null){
            System.out.println("You don't have enough info for recovery");
            return false;
        }
        else {
            if(!user.Country.equals(country)){return  false;}
        }
      /*  if (user.Birthdatestr==null){
            System.out.println("You don't have enough info for recovery");
            return false;
        }
        else {
            if(!user.Birthdate.equals(birth)){return  false;}
        }*/

        return  true;
    }

    public void Back() throws IOException {
        Main.main1.start(mainstage);
    }

    public void Next(){
  if(usernameF.getText().length()>0&&cityF.getText().length()>0&&countryF.getText().length()>0&&dateB.getValue()!=null){
      if(SignIn.signIn.existuser(usernameF.getText()).equals("This user exists")){
          if(checkinfo(SignIn.signIn.searchuser(usernameF.getText()),cityF.getText(),countryF.getText()/*,dateB.getValue()*/)){
              labelpass.setText("Your password is  :  "+SignIn.signIn.searchuser(usernameF.getText()).PassWord);
          }
          else{
              label.setText("The information entered is incorrect !");
          }
      }
  }
  else {
      label.setText("You don't have enough info for Password recovery");
  }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.setText("Enter your information :");
    }
}