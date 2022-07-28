package com.example.project_part2.USER;
import com.example.project_part2.DateFormat;
import com.example.project_part2.Main;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;

import java.sql.SQLException;

public class CompletePersonalInformation {
    public static  CompletePersonalInformation completePersonalInformation=new CompletePersonalInformation();
    public User user;
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

}
