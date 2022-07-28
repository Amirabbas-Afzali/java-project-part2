package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mycontacts {
    public static Mycontacts mycontacts=new Mycontacts();

    public void start(User Loginuser) throws SQLException {
        boolean flag = true, flag2 = true;
        String strusename, strtemp = "Enter a number:\n1.My Followers\n2.My Followings\n3.All Suggestions" +
                "\n4.Blocked Users\n5.My Close Friends\n6.Back",str;

        while (flag2){
            System.out.println("");
            System.out.println(strtemp);
            str= Main.scanner.nextLine();
            flag=true;
            while (flag) {
                if (str.equals("6")) {
                    flag = false;flag2=false;
                }
                else if (str.equals("1")) {
                    if(!Loginuser.Kind){
                        OrdinaryUser ordinaryUser1=(OrdinaryUser )Loginuser;
                        if(ordinaryUser1.Private) {
                            ShowFollowers1(Loginuser, str, Loginuser);
                        }
                        else {ShowFollowers(Loginuser,str,Loginuser);}
                    }
                    else {ShowFollowers(Loginuser,str,Loginuser);}
                    flag = false;
                }
                else if (str.equals("2")) {
                    ShowFollowings(Loginuser,str,Loginuser);
                    flag = false;
                }
                else if (str.equals("3")) {
                    if (MAINInformation.mainInformation.users.size() > 1) {
                        int c = 1;
                        Map<String,User>  tempmap=new HashMap<>();
                        for (User user1 : MAINInformation.mainInformation.users.values()) {
                            if (!Loginuser.FollowingMap.containsValue(user1) && !user1.equals(Loginuser)) {
                                System.out.println(c + "." + user1.UserName);
                                tempmap.put(user1.UserName,user1);
                                c++;
                            }
                        }
                        ShowUsergeneralSuggest(Loginuser,str,tempmap,Loginuser);
                    } else {
                        System.out.println("Empty !");
                    }
                    flag = false;
                }
                else if (str.equals("4")) {
                    if(Loginuser.BlockedMap.size()>0){
                        for(User user1:Loginuser.BlockedMap.values()){
                            System.out.println(user1.UserName);
                        }
                        ShowUsergeneralBlock(Loginuser,str,Loginuser);
                    }
                    else {System.out.println("Empty !");}

                    flag = false;
                }
                else if (str.equals("5")) {
                    if(Loginuser.CloseFriendMap.size()>0){
                        for(User user1:Loginuser.CloseFriendMap.values()){
                            System.out.println(user1.UserName);
                        }
                        ShowUsergeneralClose(Loginuser,str,Loginuser);
                    }
                    else {System.out.println("Empty !");}

                    flag = false;
                }
                else {System.out.println("Invalid command!");flag=false;}

            }
        }
    }

    public void ShowUsergeneralFollower(User user1,String str,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(user1.FollowerMap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(user1.FollowerMap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) user1.FollowerMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) user1.FollowerMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(user1.FollowerMap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) user1.FollowerMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) user1.FollowerMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }

    public void ShowUsergeneralFollowing(User user1,String str,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(user1.FollowingMap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(user1.FollowingMap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) user1.FollowingMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) user1.FollowingMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(user1.FollowingMap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) user1.FollowingMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) user1.FollowingMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }

    public void ShowUsergeneralClose(User user1,String str,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(user1.CloseFriendMap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(user1.CloseFriendMap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) user1.CloseFriendMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) user1.CloseFriendMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(user1.CloseFriendMap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) user1.CloseFriendMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) user1.CloseFriendMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }

    public void ShowUsergeneralBlock(User user1,String str,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(user1.BlockedMap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(user1.BlockedMap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) user1.BlockedMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) user1.BlockedMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(user1.BlockedMap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) user1.BlockedMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) user1.BlockedMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }

    public void ShowUsergeneralSuggest(User user1,String str,Map<String,User> tempmap,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(tempmap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(tempmap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) tempmap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) tempmap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(tempmap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) tempmap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) tempmap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }

    public void ShowUsergeneralRequest(User user1,String str,User Loginuser) throws SQLException {
        String strusename;
        System.out.println("");
        System.out.println("Enter a Username to show user's details : ");
        strusename=Main.scanner.nextLine();
        if(user1.RequestMap.containsKey(strusename)){
            if(Loginuser.Kind){
                if(user1.RequestMap.get(strusename).Kind){
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowBusUserByBusUser((BusinessUser) user1.RequestMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    BusinessUser businessUser1;
                    businessUser1= (BusinessUser) Loginuser;
                    businessUser1.ShowOrdUserByBusUser((OrdinaryUser) user1.RequestMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
            else {
                if(user1.RequestMap.get(strusename).Kind){
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowBusUserByOrdUser((BusinessUser) user1.RequestMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
                else {
                    OrdinaryUser businessUser1;
                    businessUser1= (OrdinaryUser) Loginuser;
                    businessUser1.ShowOrdUserByOrdUser((OrdinaryUser) user1.RequestMap.get(strusename),Integer.parseInt(str),Loginuser);
                }
            }
        }
        else {
            System.out.println("Not Exists !");
        }
    }



    public void ShowFollowers(User user2,String str,User Loginuser) throws SQLException {

        if (user2.FollowerMap.size() > 0) {
            int c = 1;
            for (User user1 : user2.FollowerMap.values()) {
                System.out.println(c + "." + user1.UserName);
                c++;
            }
            ShowUsergeneralFollower(user2,str,Loginuser);

        } else {
            System.out.println("Empty !");
        }
    }

    public void ShowFollowers1(User user2,String str,User Loginuser) throws SQLException {
        System.out.println("1.Followers\n2.Follow Requests");
        String temp=Main.scanner.nextLine();
        if(temp.equals("1")){
            if (user2.FollowerMap.size() > 0) {
                int c = 1;
                for (User user1 : user2.FollowerMap.values()) {
                    System.out.println(c + "." + user1.UserName);
                    c++;
                }
                ShowUsergeneralFollower(user2,str,Loginuser);

            } else {
                System.out.println("Empty !");
            }
        }
        else if(temp.equals("2")){
            if (user2.RequestMap.size() > 0) {
                int c = 1;
                for (User user1 : user2.RequestMap.values()) {
                    System.out.println(c + "." + user1.UserName);
                    c++;
                }
                ShowUsergeneralRequest(user2,"7",Loginuser);

            } else {
                System.out.println("Empty !");
            }
        }
        else {System.out.println("Invalid command!");}

    }

    public void ShowFollowings(User user2,String str,User Loginuser) throws SQLException {
        if (user2.FollowingMap.size() > 0) {
            int c = 1;
            for (User user1 : user2.FollowingMap.values()) {
                System.out.println(c + "." + user1.UserName);
                c++;
            }
            ShowUsergeneralFollowing(user2,str, Loginuser);
        }
        else {
            System.out.println("Empty !");
        }
    }
}
