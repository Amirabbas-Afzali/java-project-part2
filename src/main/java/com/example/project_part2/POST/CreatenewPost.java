package com.example.project_part2.POST;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class CreatenewPost {
    public static CreatenewPost createnewPost = new CreatenewPost();

    public User user;

    public void start() throws SQLException {
        boolean flag = true, flag2 = true;
        String text, description, strtemp = "Enter a number:\n1.Create new post\n2.back",str;
        System.out.println(strtemp);
        str= Main.scanner.nextLine();
        while (flag) {
            if (str.equals("2")) {
                flag = false;
            } else if (str.equals("1")){
                if (user.Kind) {
                    System.out.println("Enter the text of the post :");
                    text = Main.scanner.nextLine();
                    System.out.println("Enter the description of the post :");
                    description = Main.scanner.nextLine();
                    if (text.length() > 0 && description.length() > 0) {
                        String str1=createpostcode();
                       BusinessPost businessPost = new BusinessPost(str1, text, description, (BusinessUser) user, new Date(),"0");
                        PostTableDBC.postTableDBC.setPost(businessPost);
                        MAINInformation.mainInformation.posts.put(str1,businessPost);
                        user.posts.add(businessPost);
                        flag=false;
                        System.out.println("Created successful");
                    }
                } else {
                    System.out.println("Enter the text of the post :");
                    text = Main.scanner.nextLine();
                    if (text.length() > 0 ) {
                        String str11=createpostcode();
                        OrdinaryPost ordinaryPost = new OrdinaryPost(str11, text, (OrdinaryUser) user, new Date(),((OrdinaryUser) user).Private,"0");
                        PostTableDBC.postTableDBC.setPost(ordinaryPost);
                        MAINInformation.mainInformation.posts.put(str11,ordinaryPost);
                        user.posts.add(ordinaryPost);

                        flag=false;
                        System.out.println("Created successful");
                    }
                }
            }
            else {System.out.println("Invalid command!");}
        }
    }
    public String createpostcode() throws SQLException {
        String result=String.valueOf(Post.PostCodeStatic);
        Post.PostCodeStatic++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("Post",Post.PostCodeStatic);
        return result;
    }
    public boolean isprivate(String strr){
        if(strr.equals("1")){return  true;}
        return false;
    }
}
