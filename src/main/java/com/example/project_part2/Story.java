package com.example.project_part2;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;

import java.sql.Time;
import java.util.*;

public class Story {
    public Story(){}
    public static Integer StoryCodeStatic;

    public String StoryCode="";
    public String usernamestory="";
    public User user;
    public Time date;
    public boolean Private;
    public boolean IsClose;
    public String text;
    public List<String> viewersnameList=new ArrayList<>();
    public List<String> likersnameList=new ArrayList<>();

    public  Story(String code, String txt, User user1, Time date1, boolean isClose){
        this.StoryCode=code;
        this.text=txt;
        this.user=user1;
        this.usernamestory=user1.UserName;
        this.date=date1;
        this.IsClose=isClose;

        if(user1.Kind){this.Private=false;}
        else {OrdinaryUser ordinaryUser=(OrdinaryUser) user1;
            if(!ordinaryUser.Private){this.Private=false;}
            else {this.Private=true;}
        }

    }


}
