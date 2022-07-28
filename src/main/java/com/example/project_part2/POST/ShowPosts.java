package com.example.project_part2.POST;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;

public class ShowPosts {
    public static ShowPosts showPosts=new ShowPosts();

    public void start(User user,User owner) throws SQLException {
        System.out.println("Hello "+user.Name);

        String input="";
        while (!input.equals("-2")){
            System.out.println("These are Your Posts : ");
            System.out.println(owner.PostCodesList.toString()+"\n inter PostCode to view post \n inter -2 to Exit");
            input= Main.scanner.nextLine();
            if (MAINInformation.mainInformation.posts.containsKey(input.trim())){
                if (MAINInformation.mainInformation.posts.get(input.trim()).Kind){
                    BusinessPost temp=(BusinessPost) MAINInformation.mainInformation.posts.get(input.trim());
                    temp.ShowBusPost(user);
                }
                else {
                    OrdinaryPost ordinaryPost=(OrdinaryPost) MAINInformation.mainInformation.posts.get(input.trim());
                    ordinaryPost.ShowPost(user);
                }
            }
            else {
                if (!input.equals("-2")){
                    System.out.println("Post Code Doesn't Exist");
                }
            }
        }
    }
}