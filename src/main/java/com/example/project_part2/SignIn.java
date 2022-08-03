package com.example.project_part2;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.example.project_part2.CreatAccount.creatAccount;
import static com.example.project_part2.Main.mainstage;

public class SignIn   {
    static SignIn signIn=new SignIn();

    public void start() throws SQLException {
        boolean flag1=true,flag2=true,endprogram=false;
        String inputcaptcha,usename="",password="",str,strtemp="Enter a number:\n1.login\n2.Create new account\n3.Password recovery\n4.end";
        System.out.println(strtemp);
        str= Main.scanner.nextLine();
        str.trim();
        if(str.equals("1")){
            while (flag1) {
                System.out.println("Enter Username :");
                usename = Main.scanner.nextLine();
                System.out.println(existuser(usename));
                if(usename.equals("end")){flag1=false;endprogram=true;}
                if(!endprogram&&existuser(usename).equals("This user exists")) {
                    System.out.println("Enter password :");
                    password = Main.scanner.nextLine();
                    System.out.println(checkpass(searchuser(usename),password));
                    if(checkpass(searchuser(usename),password).equals("Welcome")){
                        while (flag2){
                            String captcha=craetecaptchacode(7);
                            System.out.println("Enter the Captcha code  :  "+captcha);
                            inputcaptcha= Main.scanner.nextLine();
                            if(inputcaptcha.equals(captcha)){
                                System.out.println(checkpass(searchuser(usename),password));
                                flag1=false;flag2=false;
                            }
                            else {
                                System.out.println("Cpatcha Code was entered incorrectly !");
                            }}
                    }
                }

            }
            if(!endprogram&&checkpass(searchuser(usename),password).equals("Welcome"))
            {PersonalHomepage.personalHomepage.start(searchuser(usename));}
        }
        else if(str.equals("2")){
           creatAccount.start();
        }
        else if(str.equals("3")){
            PasswordRecovery.passwordRecovery.start();
        }
        else if(str.equals("4")){
            System.out.println("bye!");
        }
        else {
            System.out.println("Invalid command!");
        }
    }
    public String existuser(String username){
        for(User user: MAINInformation.mainInformation.users.values()){
            if(user.UserName.equals(username)){
                return "This user exists";
            }
        }
        return  "This user not found!";
    }
    public User searchuser(String username){
        for(User user: MAINInformation.mainInformation.users.values()){
            if(user.UserName.equals(username)){
                return user;
            }
        }
        return  null;
    }
    public String checkpass(User user,String password){
        if(user.PassWord.equals(password)){
            return  "Welcome";
        }

        return  "The password is inccorect !";
    }

    static String craetecaptchacode(int n) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, Charset.forName("UTF-8"));

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // remove all spacial char
        String  AlphaNumericString
                = randomString
                .replaceAll("[^A-Za-z0-9]", "");

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < AlphaNumericString.length(); k++) {

            if (Character.isLetter(AlphaNumericString.charAt(k))
                    && (n > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k))
                    && (n > 0)) {

                r.append(AlphaNumericString.charAt(k));
                n--;
            }
        }
        return r.toString();
    }

    public void loginfunc() throws IOException {
        Main.loginSTART();
    }
    public void newaccountfunc() throws IOException {
        Main.createaccountSTART();
    }
    public void recoveryfunc() throws IOException {
        Main.passwordrecovSTART();
    }
    public void exitfunc() throws IOException {
 mainstage.close();
    }

}
