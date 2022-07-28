package com.example.project_part2.USER;
import com.example.project_part2.BuisnessType;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.Mycontacts;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessUser extends User{
    static BusinessUser businessUser=new BusinessUser();
    public BusinessUser(){}
    int ProfileViews;
    public List<String> ProfileViewers=new ArrayList<>();
    int AgeSumOfViewers;
    public BuisnessType buisnessType;
    public int buisnessTypeINT;

    public BusinessUser(String username, String passWord, String birthdate, String type,String profpath){
        this.UserName=username;
        this.PassWord=passWord;
        this.Birthdatestr=birthdate;
        this.profilepicpath=profpath;
        this.setBirthdate();
        this.age=this.calculateAGE();
        this.setBuisnessType(type);
        this.buisnessTypeINT=setBuisnessTypeINT(this.buisnessType);
        this.Kind=true;
        this.Blocked=0;
        this.ProfileViews=0;
        this.AgeSumOfViewers=0;
        User.UserNamesList.add(this.UserName);
        MAINInformation.mainInformation.users.put(this.UserName,this);
    }

    public void setBuisnessType(String type){
        if(type.equals("1")){this.buisnessType=BuisnessType.Tech;}
        else if(type.equals("2")){this.buisnessType=BuisnessType.Cloth;}
        else  if(type.equals("3")){this.buisnessType=BuisnessType.AD;}
        else  if(type.equals("4")){this.buisnessType=BuisnessType.Artist;}
        else  if(type.equals("5")){this.buisnessType=BuisnessType.Sport;}

    }

    public int setBuisnessTypeINT(BuisnessType buisnessType1){
        //1.Tech 2.Cloth 3.AD 4.Artist 5.Sport
        if(buisnessType1.equals(BuisnessType.Tech)){return  1;}
        if(buisnessType1.equals(BuisnessType.Cloth)){return 2;}
        if(buisnessType1.equals(BuisnessType.AD)){return 3;}
        if(buisnessType1.equals(BuisnessType.Artist)){return 4;}
        if(buisnessType1.equals(BuisnessType.Sport)){return  5;}
        return 0;
    }

    @Override
    public String toString() {
        return "BusinessUser{" +
                "buisnessType=" + buisnessType +
                ", buisnessTypeINT=" + buisnessTypeINT +
                ", UserName='" + UserName + '\'' +
                ", Name='" + Name + '\'' +
                ", Birthdate=" + Birthdate +
                ", Kind=" + Kind +
                ", isman=" + isman +
                ", married=" + married +
                ", City='" + City + '\'' +
                ", Country='" + Country + '\'' +
                ", Bio='" + Bio + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

    public void ShowOrdUserByBusUser(OrdinaryUser ordinaryUser,int TYPE,User Loginuser) throws SQLException {
        System.out.println("Name : "+ordinaryUser.Name+"                  (OrdinaryUser)\n"+"UserName : "+ordinaryUser.UserName +
                "\nbio : "+ordinaryUser.Bio+"\nAge : "+ordinaryUser.age+"\nCountry : "+ordinaryUser.Country+"\nCity : "+ordinaryUser.City);

        String input="";
        boolean flag=true;
        while (flag){
            int c=1;
            System.out.println("");
            if(TYPE!=6){
                if(TYPE!=4){
                    if(TYPE!=2){ System.out.println(c+".Follow");c++;}
                    if(TYPE==2){System.out.println(c+".Unfollow");c++;}
                    System.out.println(c+".Block User");c++;
                    System.out.println(c+".Send Massage");c++;
                    System.out.println(c+".Show User's Posts");c++;
                    if(TYPE==1){ System.out.println(c+".add to close friend list");c++;}
                    if(TYPE==5){ System.out.println(c+".Remove from close friend list");c++;}
                    if(TYPE==1||TYPE==5){ System.out.println(c+".Remove");c++;}
                    System.out.println(c+".Show User's followings");c++;
                    System.out.println(c+".Show User's followers");c++;
                    System.out.println(c+".Show User's Stories");c++;
                }
                else {System.out.println(c+".Unblock User");c++;}
                System.out.println(c+".Back");c++;}
            else {
                System.out.println(c+".Follow");c++;
                System.out.println(c+".Block User");c++;
                System.out.println(c+".Send Massage");c++;
                System.out.println(c+".Show User's Posts");c++;
                System.out.println(c+".Show User's followings");c++;
                System.out.println(c+".Show User's followers");c++;
                System.out.println(c+".Show User's Stories");c++;
                System.out.println(c+".Back");c++;
            }


            input= Main.scanner.nextLine();
            if(TYPE==1){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        if(!ordinaryUser.Private){
                            Loginuser.setFollow(ordinaryUser);
                            System.out.println("Followed !");}
                        else {
                            if(!ordinaryUser.RequestMap.containsValue(Loginuser)){
                                Loginuser.setRequest(ordinaryUser);
                                System.out.println("Requested !");}
                            else {
                                System.out.println("You have already requested this user !");
                            }
                        }
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }

                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        //     ShowPosts.showPosts.user = ordinaryUser;
                        ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("5")){
                    if(!Loginuser.CloseFriendMap.containsValue(ordinaryUser)){
                        Loginuser.setCloseFriendUser(ordinaryUser);
                        System.out.println("Added.");
                    }
                    else{System.out.println("This user is on your Close Friend List !");}
                }
                else if (input.equals("6")){
                    //remove
                    if(Loginuser.FollowerMap.containsValue(ordinaryUser)) {
                        Loginuser.setRemove(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else {
                        System.out.println("This user is not in your followers list");
                    }
                }
                else if (input.equals("7")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("8")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("9")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        ordinaryUser.ShowStory(Loginuser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("10")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else  if(TYPE==2){
                if (input.equals("1")){
                    if(Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setUnFollow(ordinaryUser);
                        System.out.println("Unfollowed !");
                    }
                    else {
                        System.out.println("This user is not on your FollowingList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        //  ShowPosts.showPosts.user = ordinaryUser;
                        ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("5")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("6")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("7")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        ordinaryUser.ShowStory(Loginuser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==3){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        if(!ordinaryUser.Private){
                            Loginuser.setFollow(ordinaryUser);
                            System.out.println("Followed !");}
                        else {
                            if(!ordinaryUser.RequestMap.containsValue(Loginuser)){
                                Loginuser.setRequest(ordinaryUser);
                                System.out.println("Requested !");}
                            else {
                                System.out.println("You have already requested this user !");
                            }
                        }
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        //    ShowPosts.showPosts.user = ordinaryUser;
                        ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("5")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("6")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("7")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        ordinaryUser.ShowStory(Loginuser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==4){
                if (input.equals("1")) {
                    if(Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setUnBlockedUser(ordinaryUser);
                        System.out.println("Unblocked !");
                    }
                    else{System.out.println("This user is not in your block list !");}
                }
                else if (input.equals("2")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==5){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        if(!ordinaryUser.Private){
                            Loginuser.setFollow(ordinaryUser);
                            System.out.println("Followed !");}
                        else {
                            if(!ordinaryUser.RequestMap.containsValue(Loginuser)){
                                Loginuser.setRequest(ordinaryUser);
                                System.out.println("Requested !");}
                            else {
                                System.out.println("You have already requested this user !");
                            }
                        }
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }

                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        //   ShowPosts.showPosts.user = ordinaryUser;
                        ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("5")){
                    if(Loginuser.CloseFriendMap.containsValue(ordinaryUser)){
                        Loginuser.setRemoveCloseFriendUser(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else{System.out.println("This user is not in your Close Friend List !");}
                }
                else if (input.equals("6")){
                    //remove
                    if(Loginuser.FollowerMap.containsValue(ordinaryUser)) {
                        Loginuser.setRemove(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else {
                        System.out.println("This user is not in your followers list");
                    }
                }
                else if (input.equals("7")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("8")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("9")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        ordinaryUser.ShowStory(Loginuser);
                    }
                    else {System.out.println("This Account is Private !");}
                }
                else if (input.equals("10")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}
            }
            else if(TYPE==6){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        if(!ordinaryUser.Private){
                            Loginuser.setFollow(ordinaryUser);
                            System.out.println("Followed !");}
                        else {
                            if(!ordinaryUser.RequestMap.containsValue(Loginuser)){
                                Loginuser.setRequest(ordinaryUser);
                                System.out.println("Requested !");}
                            else {
                                System.out.println("You have already requested this user !");
                            }
                        }
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        //ShowPosts.showPosts.user = ordinaryUser;
                        ShowPosts.showPosts.start(Loginuser,ordinaryUser);}
                    else {System.out.println("This Account is Private !");}

                }
                else if (input.equals("5")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}

                }
                else if (input.equals("6")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);}
                    else {System.out.println("This Account is Private !");}

                }
                else if (input.equals("7")){
                    if(!ordinaryUser.Private||Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        ordinaryUser.ShowStory(Loginuser);
                    }
                    else {System.out.println("This Account is Private !");}
                }

                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}
            }


            else {System.out.println("Invalid command!");}

        }
    }

    public void ShowBusUserByBusUser(BusinessUser ordinaryUser,int TYPE,User Loginuser) throws SQLException {
        System.out.println("Name : "+ordinaryUser.Name+"                  (BusinessUser)\n"+
                "UserName : "+ordinaryUser.UserName +
                "\nField : "+ordinaryUser.buisnessType+
                "\nbio : "+ordinaryUser.Bio+"\nAge : "+ordinaryUser.age+"\nCountry : "+ordinaryUser.Country+"\nCity : "+ordinaryUser.City);

        String input="";
        boolean flag=true;
        while (flag){
            int c=1;
            System.out.println("");
            if(TYPE!=6){
                if(TYPE!=4){
                    if(TYPE!=2){ System.out.println(c+".Follow");c++;}
                    if(TYPE==2){System.out.println(c+".Unfollow");c++;}
                    System.out.println(c+".Block User");c++;
                    System.out.println(c+".Send Massage");c++;
                    System.out.println(c+".Show User's Posts");c++;
                    if(TYPE==1){ System.out.println(c+".add to close friend list");c++;}
                    if(TYPE==5){ System.out.println(c+".Remove from close friend list");c++;}
                    if(TYPE==1||TYPE==5){ System.out.println(c+".Remove");c++;}
                    System.out.println(c+".Show User's followings");c++;
                    System.out.println(c+".Show User's followers");c++;
                    System.out.println(c+".Show User's Stories");c++;
                }
                else {System.out.println(c+".Unblock User");c++;}
                System.out.println(c+".Back");c++;}
            else {
                System.out.println(c+".Follow");c++;
                System.out.println(c+".Block User");c++;
                System.out.println(c+".Send Massage");c++;
                System.out.println(c+".Show User's Posts");c++;
                System.out.println(c+".Show User's followings");c++;
                System.out.println(c+".Show User's followers");c++;
                System.out.println(c+".Show User's Stories");c++;
                System.out.println(c+".Back");c++;
            }


            input=Main.scanner.nextLine();
            if(TYPE==1){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setFollow(ordinaryUser);
                        System.out.println("Followed !");
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    // ShowPosts.showPosts.user = ordinaryUser;
                    ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                }
                else if (input.equals("5")){
                    if(!Loginuser.CloseFriendMap.containsValue(ordinaryUser)){
                        Loginuser.setCloseFriendUser(ordinaryUser);
                        System.out.println("Added.");
                    }
                    else{System.out.println("This user is on your Close Friend List !");}
                }
                else if (input.equals("6")){
                    //remove
                    if(Loginuser.FollowerMap.containsValue(ordinaryUser)) {
                        Loginuser.setRemove(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else {
                        System.out.println("This user is not in your followers list");
                    }
                }
                else if (input.equals("7")){
                    Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("8")){
                    Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("9")){
                    ordinaryUser.ShowStory(Loginuser);
                }
                else if (input.equals("10")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}
            }
            else  if(TYPE==2){
                if (input.equals("1")){
                    if(Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setUnFollow(ordinaryUser);
                        System.out.println("Unfollowed !");
                    }
                    else {
                        System.out.println("This user is not on your FollowingList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    //        ShowPosts.showPosts.user = ordinaryUser;
                    ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                }
                else if (input.equals("5")){
                    Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("6")){
                    Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("7")){
                    ordinaryUser.ShowStory(Loginuser);
                }
                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==3){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setFollow(ordinaryUser);
                        System.out.println("Followed !");
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user) !");
                    }
                }
                else if (input.equals("4")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("5")){
                    Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("6")){
                    Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("7")){
                    ordinaryUser.ShowStory(Loginuser);
                }
                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==4){
                if (input.equals("1")) {
                    if(Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setUnBlockedUser(ordinaryUser);
                        System.out.println("Unblocked !");
                    }
                    else{System.out.println("This user is not in your block list !");}
                }
                else if (input.equals("2")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}

            }
            else if(TYPE==5){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setFollow(ordinaryUser);
                        System.out.println("Followed !");
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    //     ShowPosts.showPosts.user = ordinaryUser;
                    ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                }
                else if (input.equals("5")){
                    if(Loginuser.CloseFriendMap.containsValue(ordinaryUser)){
                        Loginuser.setRemoveCloseFriendUser(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else{System.out.println("This user is not on your Close Friend List !");}
                }
                else if (input.equals("6")){
                    //remove
                    if(Loginuser.FollowerMap.containsValue(ordinaryUser)) {
                        Loginuser.setRemove(ordinaryUser);
                        System.out.println("Removed !");
                    }
                    else {
                        System.out.println("This user is not in your followers list");
                    }
                }
                else if (input.equals("7")){
                    Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("8")){
                    Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("9")){
                    ordinaryUser.ShowStory(Loginuser);
                }
                else if (input.equals("10")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}
            }
            else if(TYPE==6){
                if (input.equals("1")){
                    if(!Loginuser.FollowingMap.containsValue(ordinaryUser)) {
                        Loginuser.setFollow(ordinaryUser);
                        System.out.println("Followed !");
                    }
                    else {
                        System.out.println("This user is on your FollowerList");
                    }
                }
                else if (input.equals("2")){
                    if(!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.setBlockedUser(ordinaryUser);
                        System.out.println("Blocked !");
                        flag=false;
                    }
                    else{System.out.println("You have already blocked this user !");}
                }
                else if (input.equals("3")){
                    if(!ordinaryUser.BlockedMap.containsValue(Loginuser)&&!Loginuser.BlockedMap.containsValue(ordinaryUser)){
                        Loginuser.sendMassage(ordinaryUser.UserName);}
                    else {
                        System.out.println("You can't send massage  (You are in the block list of this user or vice versa) !");
                    }
                }
                else if (input.equals("4")){
                    //      ShowPosts.showPosts.user = ordinaryUser;
                    ShowPosts.showPosts.start(Loginuser,ordinaryUser);
                }
                else if (input.equals("5")){
                    //ings
                    Mycontacts.mycontacts.ShowFollowings(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("6")){
                    //ers
                    Mycontacts.mycontacts.ShowFollowers(ordinaryUser,"6",Loginuser);
                }
                else if (input.equals("7")){
                    ordinaryUser.ShowStory(Loginuser);
                }
                else if (input.equals("8")){
                    flag=false;
                }
                else {System.out.println("Invalid command!");}
            }

            else {System.out.println("Invalid command!");}

        }
    }



    //------------------------------------------------------------
    public void addProfileViews(){}
    public void addProfileViewers(String _UserName){}
    public void LoadProfileViewers(){}
    public String getCountryPollName(){
        return "temp";
    }
    public String getCityPollName(){
        return "temp";
    }
    public int getProfileViews(){
        return -1;
    }
    public int getAverageAge(){
        return -1;
    }
}