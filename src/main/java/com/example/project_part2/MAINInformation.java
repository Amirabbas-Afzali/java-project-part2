package com.example.project_part2;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.*;

public class MAINInformation {
    public static MAINInformation mainInformation =new MAINInformation();

    public Map<String,User> users=new HashMap<>();
    public Map<String,Post> posts=new HashMap<>();
    public Map<String,Massage> massages=new HashMap<>();
    public Map<String,DirectMassage> directmassages=new HashMap<>();
    public Map<String,Story> stories=new HashMap<>();
    public Map<String,LikeHandle>likeHandleMap=new HashMap<>();

    public void UpdateMainInfo() throws SQLException {
        fillPosts(PostTableDBC.postTableDBC.getPostCodesList());
        fillStories(StoryTableDBC.storyTableDBC.getStoryCodesList());
        fillusers(UserTableDBC.userTableDBC.getUserNamesList());
        fillMassages(MassageTableDBC.massageTableDBC.getMassageCodesList());
        fillDirectMassages(DircectMassageTableDBC.dircectMassageTableDBC.getDirectMassageCodesList());
        fillsStaticCodes();
        fillFollowers_Followings_Blocked_CloseF();
        processstoryUser();
        fillLikes();
        processUserPoster();
        processReport();
    }

    public void fillLikes()throws SQLException{
        List<String> list=LikeHandleTable.likeHandleTable.getLikeCodesList();
        for (String i:list){
            MAINInformation.mainInformation.likeHandleMap.put(i,LikeHandleTable.likeHandleTable.getLike(i));
        }
    }

    public void fillsStaticCodes() throws SQLException {
        Post.PostCodeStatic=StaticTableDBC.staticTableDBC.getCodeNumber("Post");
        DirectMassage.DirectMassageCodeStatic=StaticTableDBC.staticTableDBC.getCodeNumber("DirectMassage");
        Massage.MassageCodeStatic=StaticTableDBC.staticTableDBC.getCodeNumber("Massage");
        Story.StoryCodeStatic=StaticTableDBC.staticTableDBC.getCodeNumber("Story");
        LikeHandle.LikeNumber=StaticTableDBC.staticTableDBC.getCodeNumber("Like");
    }

    public void fillusers(List<String> usernames) throws SQLException {
        for(int i=0;i<usernames.size();i++){
            MAINInformation.mainInformation.users.put(usernames.get(i),UserTableDBC.userTableDBC.getUser(usernames.get(i)));
            processUserPosts(MAINInformation.mainInformation.users.get(usernames.get(i)));
            processUserStories(MAINInformation.mainInformation.users.get(usernames.get(i)));
            if(!User.UserNamesList.contains(usernames.get(i))) {User.UserNamesList.add(usernames.get(i));}
        }
    }

    public void fillPosts(List<String> postcodes) throws SQLException {
        for(int i=0;i<postcodes.size();i++){
            MAINInformation.mainInformation.posts.put(postcodes.get(i),PostTableDBC.postTableDBC.getPost(postcodes.get(i)));
        }
    }

    public void fillStories(List<String> postcodes) throws SQLException {
        for(int i=0;i<postcodes.size();i++){
            MAINInformation.mainInformation.stories.put(postcodes.get(i),StoryTableDBC.storyTableDBC.getStory(postcodes.get(i)));
        }
    }

    public void fillMassages(List<String> postcodes) throws SQLException {
        for(int i=0;i<postcodes.size();i++){
            MAINInformation.mainInformation.massages.put(postcodes.get(i),MassageTableDBC.massageTableDBC.getMassage(postcodes.get(i)));
        }
    }

    public void fillDirectMassages(List<String> postcodes) throws SQLException {
        for(int i=0;i<postcodes.size();i++){
            MAINInformation.mainInformation.directmassages.put(postcodes.get(i),DircectMassageTableDBC.dircectMassageTableDBC.getDirectMassage(postcodes.get(i)));
        }
    }

    public  void processUserPosts(User user){
        if(null != user.PostCodesList){
            for(String str:user.PostCodesList){
                if(MAINInformation.mainInformation.posts.get(str)!=null){ user.posts.add(MAINInformation.mainInformation.posts.get(str));
                }
            }}
    }

    public void fillFollowers_Followings_Blocked_CloseF(){
        for(User user: MAINInformation.mainInformation.users.values()){
            processUserFollowers(user);
            processUserFollowings(user);
            processUserBlocked(user);
            processUserCloseF(user);
            processUserrequest(user);
        }
    }

    public  void processUserFollowers(User user){
        if(null != user.FollowersList){
            for(String str:user.FollowersList){
                if(MAINInformation.mainInformation.users.get(str)!=null) { user.FollowerMap.put(str,MAINInformation.mainInformation.users.get(str));}
            }}
    }

    public  void processUserFollowings(User user){
        if(null != user.FollowingsList){
            for(String str:user.FollowingsList){
                if(MAINInformation.mainInformation.users.get(str)!=null) {  user.FollowingMap.put(str,MAINInformation.mainInformation.users.get(str));}
            }}
    }

    public  void processUserStories(User user){
        if( user.StoryCodeList.size()>0){
            for(String str:user.StoryCodeList){
                user.MyStories.add(MAINInformation.mainInformation.stories.get(str));
            }}
    }

    public  void processUserBlocked(User user){
        if(null != user.BlockedList){
            for(String str:user.BlockedList){
                if(MAINInformation.mainInformation.users.get(str)!=null) { user.BlockedMap.put(str,MAINInformation.mainInformation.users.get(str));}
            }}
    }

    public  void processUserrequest(User user){
        if(null != user.RequestList){
            for(String str:user.RequestList){
                if(MAINInformation.mainInformation.users.get(str)!=null) { user.RequestMap.put(str,MAINInformation.mainInformation.users.get(str));}
            }}
    }

    public  void processUserCloseF(User user){
        if(null != user.CloseFriendList){
            for(String str:user.CloseFriendList){
                if(MAINInformation.mainInformation.users.get(str)!=null) { user.CloseFriendMap.put(str,MAINInformation.mainInformation.users.get(str));}
            }}
    }

    public void processUserPoster(){
        for (Post post:MAINInformation.mainInformation.posts.values()){
            post.userposter=MAINInformation.mainInformation.users.get(post.PosterName);
        }
    }

    public void processstoryUser(){
        for (Story post:MAINInformation.mainInformation.stories.values()){
            post.user=MAINInformation.mainInformation.users.get(post.usernamestory);
        }
    }

    public void  processReport(){
        for(User user:MAINInformation.mainInformation.users.values()){
            user.CheckReportUser(user);
        }
    }

    public void ChecktoDeleteStory(){
        for(Story story :MAINInformation.mainInformation.stories.values()){

        }
    }

}
