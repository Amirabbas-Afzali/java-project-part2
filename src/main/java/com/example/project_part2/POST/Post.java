package com.example.project_part2.POST;
import java.sql.SQLException;
import java.sql.Time;
import java.util.*;

import com.example.project_part2.*;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;

public class Post {
    static List<String> PostsCodesList1=new ArrayList<>();
    public String PostCode="";
    public static Integer PostCodeStatic;
    public String PosterName="";
    public List<String> RepostersList=new ArrayList<>();
    int NumberOfRetwiets;
    int NumberOfLikes;
    public Boolean Kind;
    public User userposter;
    public Date date;
    public List<String> LikedList=new ArrayList<>();
    public String Caption;
    //List<Massage> Comments=new ArrayList<>();
    public List<String>CommentsCodesList=new ArrayList<>();
    public void ShowPost(User user) throws SQLException {

        String input="";
        while ((!(input.equals("6")&&IsPrivate()))&&(!(input.equals("7")&&!IsPrivate()))){
            System.out.println("Post Code : "+PostCode+"  ,  "+"Poster : "+PosterName +
                    "\n  Date:  "+ DateFormat.dateFormat.reportdate(date)+"   ,    Likes : "+LikedList.size()+"   ,  Comments :  " +CommentsCodesList.size()+
                    "\n Caption :  " +Caption+"   ,    Repost : "+RepostersList.size());
            System.out.println("1:view comments\n 2:view Likes\n 3:view Reposters ");
            if (UserNameLiked(user.UserName)){
                System.out.println("4:UnLike Post");
            }
            else {
                System.out.println("4:Like Post");
            }
            System.out.println("5:Add Comment");
            if (!this.IsPrivate()){
                if (!RepostersList.contains(user.UserName)){
                    System.out.println("6:Repost\n 7:Back");
                }
                else {
                    System.out.println("6:UnRepost\n 7:Back");
                }
            }
            else {
                System.out.println("6:Back");
            }
            input= Main.scanner.nextLine();
            if (input.equals("1")){
                for (String i:CommentsCodesList){
                    // System.out.println(i+"ewdeee");
                    MAINInformation.mainInformation.massages.get(i).ShowPreView();
                }
                String input2="";
                while (!input2.equals("-2")){
                    System.out.println("If you want to view a comment inter comment code\n -2:Back");
                    input2=Main.scanner.nextLine();
                    if (this.CommentsCodesList.contains(input2)){
                        MAINInformation.mainInformation.massages.get(input2).ShowMassage(user);
                    }
                }
                // TODO: 7/23/2022

            }
            if (input.equals("2")){
                for (String i:LikedList){
                    MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(false,new Date(),false,false);
                }
                // TODO: 7/25/2022 View Users
            }
            if (input.equals("3")){
                System.out.println(RepostersList.toString());
                // TODO: 8/25/2022 View Users
            }
            if (input.equals("4")){
                if (!this.UserNameLiked(user.UserName)){
                    String LikeCode= LikeHandle.NewLikeHandles(user.UserName,this.PostCode,false);
                    //user.addLikedPostCode(this.PostCode,true);
                    this.addLikeOrRemove(LikeCode,true);
                    System.out.println("Liked");
                }
                else {
                    String LikeCode="";
                    for (String i:LikedList){
                        if (MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName.equals(user.UserName)){
                            LikeCode=i;
                            user.addLikedPostCode(LikeCode,false);
                        }
                    }
                    this.addLikeOrRemove(LikeCode,false);
                    System.out.println("UnLiked");
                }
            }
            if (input.equals("5")){
                System.out.println("Inter text");
                String text=Main.scanner.nextLine();
                String code= Massage.NewMassage(user.UserName,text);
                this.addComment(code);
                System.out.println("Comment Added");
            }
            if (input.equals("6")&&!IsPrivate()){
                if (RepostersList.contains(user.UserName)){
                    this.RemoveOrAddRetweet(user.UserName,false);
                    user.Retweet(this.PostCode,false);
                    System.out.println("Post Unreposted");
                }
                else {
                    this.RemoveOrAddRetweet(user.UserName,true);
                    user.Retweet(this.PostCode,true);
                    System.out.println("Post reposted");
                }
            }
        }
    }
    void RemoveOrAddRetweet(String UseName,boolean Add) throws SQLException {
        if (Add){
            this.RepostersList.add(UseName);

        }
        else {
            this.RepostersList.remove(UseName);
        }
        PostTableDBC.postTableDBC.EditorDeletePost(this,false);
    }
    public String getPostCode(){return PostCode;}
    public void LoadCommentsCodesList(){}
    public List<String>getCommentsCodesList(){return CommentsCodesList;}
    public void LoadPostsCodeLists(){}
    public void LoadRepostersList(){}
    public List<String>getRepostersList(){return RepostersList;}
    public void LoadLikedList(){}
    public List<String>getLikedList(){return LikedList;}
    public int getNumberOfRetwiets() {
        return NumberOfRetwiets;
    }
    public int getNumberOfLikes() {
        int result=0;
        for (String i:LikedList){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).isPost){
                result++;
            }
        }
        return result;
    }
    public static void NewPost(){}
    // public Massage getComments(){return new Massage();}
    public static Post getPost(String PostCode){return new Post();}
    // public void addComment(Massage comment){}
    public void Update(){}
    public void CommentHandle(){}
    public void addLikeOrRemove(String LikeCode,Boolean add) throws SQLException {
        if (add){
            LikedList.add(LikeCode);
        }
        else{
            LikedList.remove(LikeCode);
        }
        PostTableDBC.postTableDBC.EditorDeletePost(this,false);
    }
    boolean IsPrivate(){
        if (!this.userposter.Kind){
            OrdinaryUser ordinaryUser=(OrdinaryUser) this.userposter;
            return ordinaryUser.Private;
        }
        return false;
    }
    void addComment(String MassageCode) throws SQLException {
        this.CommentsCodesList.add(MassageCode);
        PostTableDBC.postTableDBC.EditorDeletePost(this,false);
    }

    Integer CommentThreeNumber(){
        Massage.sum=0;
        for (String i:this.CommentsCodesList){
            MAINInformation.mainInformation.massages.get(i).GetThreadSum();
        }
        Integer integer=Massage.sum;
        Massage.sum=0;
        return integer;
    }
    boolean UserNameLiked(String Viewer){
        for (String i:LikedList){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName.equals(Viewer)){
                if (MAINInformation.mainInformation.likeHandleMap.get(i).isPost)
                    return true;
            }
        }
        return false;
    }
    public void setCaption(String newCap) throws SQLException {
        this.Caption=newCap;
        PostTableDBC.postTableDBC.EditorDeletePost(this,false);
    }

    public void preShow(){
        System.out.print("Post Code :"+this.PostCode+" , Caption :");

        if (getCaption().length()>30){
            System.out.print(Caption.substring(0,29));
        }
        else {
            System.out.print(getCaption());
        }
        if (this.Kind){
            System.out.print(" , Type : Business");
            BusinessPost businessPost=(BusinessPost) this;
            System.out.print("Description : "+businessPost.description);
        }
        else {
            System.out.print(" , Type : Ordinary");
        }
        System.out.println(" , Posted by : "+this.PosterName);
    }
    public  String getCaption(){
        return this.Caption;
    }
}