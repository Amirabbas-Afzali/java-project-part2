package com.example.project_part2;

import com.example.project_part2.USER.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.ResourceBundle;

import static com.example.project_part2.Main.mainstage;

public class Login2 implements Initializable {
    Image image1=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\sunset-in-the-mountains-60-big.jpg");
    Image image2=new Image("C:\\Users\\TUF\\Desktop\\java project\\Project_part2\\src\\main\\resources\\com\\example\\project_part2\\theme2\\5.jpg");
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

    @FXML
    Label label,captcha;
    @FXML
    TextField usernameF;
    @FXML
    PasswordField passF,passF1;
    @FXML
    ImageView bckgr;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SETThEME();
        label.setText("Enter your information :");
        createcaptcha();
    }

    public void Back() throws IOException {
        Main.main1.start(mainstage);
    }

    public String cap;
    public void createcaptcha(){
        cap=craetecaptchacode(7);
        captcha.setText(cap);
        System.out.println(cap);
    }

    public void login() throws IOException {
        if(usernameF.getText().length()>0&&passF.getText().length()>0&&passF1.getText().length()>0){
            if(existuser(usernameF.getText()).equals("This user exists")) {
                if(checkpass(searchuser(usernameF.getText()),passF.getText()).equals("Welcome")){
                    if(passF1.getText().equals(cap)){

                      //  PersonalHomepage.timelineposts=ShowTimeline.showTimeline.TimeLinePosts(10,MAINInformation.mainInformation.users.get(usernameF.getText()));;
                       PersonalHomepage.timelineposts=MAINInformation.mainInformation.users.get(usernameF.getText()).posts;
                       PersonalHomepage.USER=MAINInformation.mainInformation.users.get(usernameF.getText());
                      //  PersonalHomepage.timelineposts=ShowTimeline.showTimeline.TimeLinePosts(10,PersonalHomepage.USER);

                        Main.personalpageSTART();
                    }
                    else {
                        label.setText("Cpatcha Code was entered incorrectly !");
                    }
                }
            }
        }
        else {
            label.setText(" Enter your information correctly ");
        }
        createcaptcha();
    }
}
