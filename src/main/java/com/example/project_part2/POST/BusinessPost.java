package com.example.project_part2.POST;
import com.example.project_part2.*;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BusinessPost extends Post{
    public List<String> ViewersUserNames=new ArrayList<>();
    public List<String>getViewersUserNames(){return ViewersUserNames;}
    public BuisnessType buisnessType;
    public int buisnessTypeINT;
    public String description;
    public BusinessPost(){}
    BusinessPost(String postcode, String text,String describe, BusinessUser user, Date time1) throws SQLException {
        this.userposter=user;
        this.Kind=true;
        this.PostCode=postcode;
        this.Caption=text;
        this.description=describe;
        this.PosterName=user.UserName;
        this.date=time1;
        this.NumberOfLikes=0;
        this.NumberOfRetwiets=0;
        this.buisnessType=user.buisnessType;
        this.buisnessTypeINT=setBuisnessTypeINT(user.buisnessType);
        Post.PostsCodesList1.add(postcode);
        user.addPostCodeToPosts(postcode);
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

    public void ShowBusPost(User Viewer) throws SQLException {
        this.addLikeOrRemove(LikeHandle.NewLikeHandles(Viewer.UserName,"-2",true),true);
        System.out.println("Post Code : "+PostCode+"  ,  "+"Poster : "+PosterName+
                "\n  Date:  "+ DateFormat.dateFormat.reportdate(date)+"   ,   Likes : "+getNumberOfLikes()+"  " +
                " \n Views : "+ ViewersUserNames.size()+"  ,    " +" Repost : "+RepostersList.size()+"  " +
                "\n  Business Type : "+buisnessType.toString()+
                "\n Caption : "+Caption+"  \n  Description : "+description);

        String input="";
        while (!input.equals("Back")){
            System.out.println("");
            System.out.println("1:view comments\n 2:view Likes\n 3:view Reposters");
            if (UserNameLiked(Viewer.UserName)){
                System.out.println("4:UnLike Post");
            }
            else {
                System.out.println("4:Like Post");
            }
            System.out.println("5:Add Comment");
            if (!RepostersList.contains(Viewer.UserName)){
                System.out.println("6:Repost");
            }
            else {
                System.out.println("6:UnRepost");
            }
            if (Viewer.UserName.equals(this.PosterName)){
                System.out.println("7:Exclusive Business\n Back");
            }
            else {
                System.out.println("Back");
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
                        MAINInformation.mainInformation.massages.get(input2).ShowMassage(Viewer);
                    }
                }
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
                if (!this.UserNameLiked(Viewer.UserName)){
                    String LikeCode=LikeHandle.NewLikeHandles(Viewer.UserName,this.PostCode,false);
                    //user.addLikedPostCode(this.PostCode,true);
                    this.addLikeOrRemove(LikeCode,true);
                    System.out.println("Liked");
                }
                else {
                    String LikeCode="";
                    for (String i:LikedList){
                        if (MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName.equals(Viewer.UserName)){
                            LikeCode=i;
                            Viewer.addLikedPostCode(LikeCode,false);
                        }
                    }
                    this.addLikeOrRemove(LikeCode,false);
                    System.out.println("UnLiked");
                }
            }
            if (input.equals("5")){
                System.out.println("Inter text");
                String text=Main.scanner.nextLine();
                String code=Massage.NewMassage(Viewer.UserName,text);
                this.addComment(code);
                System.out.println("Comment Added");
            }
            if (input.equals("6")){
                if (RepostersList.contains(Viewer.UserName)){
                    this.RemoveOrAddRetweet(Viewer.UserName,false);
                    Viewer.Retweet(this.PostCode,false);
                    System.out.println("Post Unreposted");
                }
                else {
                    this.RemoveOrAddRetweet(Viewer.UserName,true);
                    Viewer.Retweet(this.PostCode,true);
                    System.out.println("Post reposted");
                }
            }
            if (input.equals("7")&&Viewer.UserName.equals(this.PosterName)){
                ExclusiveBus();
            }


        }
    }

    void ExclusiveBus() throws SQLException{
        String input="";
        while (!input.equals("Back")){
            System.out.println("1:View Recent Views\n2:View Recent Likes\n3:Edit");
            input=Main.scanner.nextLine().trim();
            if (input.equals("1")){
                ViewViews();
            }
            if (input.equals("2")){
                ViewLikes();
            }
            if (input.equals("3")){
                Edit();
            }
        }
    }

    void ViewViews(){
        String input="";
        while (!input.equals("Back")){
            System.out.println("1:View All Views\n2:View Recent Views\nBack");
            input=Main.scanner.nextLine().trim();
            if (input.equals("1")){
                int sum=0;
                for (String i:LikedList){

                    sum+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(false,new Date(),true,false);

                }
                System.out.println("Sum is : "+sum);
            }
            if (input.equals("2")){
                System.out.println("Enter how many days?");
                String input2=Main.scanner.nextLine().trim();
                try {
                    Integer sum=0;
                    for (String i:LikedList){
                        sum+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(true,getNDaysAgo(Integer.parseInt(input2)),true,false);
                    }
                    System.out.println("Sum is : "+sum);

                }
                catch (Exception e){
                    System.out.println("invalid command");
                }

            }
        }
    }

    void ViewLikes(){
        String input="";
        while (!input.equals("Back")){
            System.out.println("1:View All Likes\n2:View Recent Likess\nBack");
            input=Main.scanner.nextLine().trim();
            if (input.equals("1")){
                int sum=0;
                for (String i:LikedList){

                    sum+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(false,new Date(),false,false);

                }
                System.out.println("Sum is : "+sum);
            }
            if (input.equals("2")){
                System.out.println("Enter how many days?");
                String input2=Main.scanner.nextLine().trim();
                try {
                    Integer sum=0;
                    for (String i:LikedList){
                        sum+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(true,getNDaysAgo(Integer.parseInt(input2)),false,false);
                    }
                    System.out.println("Sum is : "+sum);

                }
                catch (Exception e){
                    System.out.println("invalid command");
                }

            }
        }
    }

    void Edit() throws SQLException {
        String input="";
        while (!input.equals("Back")){
            System.out.println("1:Edit Caption\n2:Edit description\nBack");
            input=Main.scanner.nextLine().trim();
            if (input.equals("1")){
                System.out.println("Enter new caption");
                String input1=Main.scanner.nextLine().trim();
                this.setCaption(input1);
                System.out.println("Caption Edited");
            }
            if (input.equals("2")){
                System.out.println("Enter new description");
                String input2=Main.scanner.nextLine().trim();
                this.setDescription(input2);
                System.out.println("Description Edited");
            }
        }
    }

    static Date getNDaysAgo(int input2){
        //Long miliSecond= input2 *60*60*24*1000;
        Date today = new Date();
        //System.out.println(today);
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        cal.add(Calendar.DAY_OF_MONTH,  -input2);

// convert calendar to date
        Date modifiedDate = cal.getTime();
        return modifiedDate;
    }

    public void setDescription(String newDes) throws SQLException {
        this.description=newDes;
        PostTableDBC.postTableDBC.EditorDeletePost(this,false);
    }

    /////////////////////////////////////////////////////////////

    public void LoadViewersUserNames(){}
    //public static BusinessPost getBusinessPost(String Code){return new BusinessPost();}
    //BuisnessType buisnessType=BuisnessType.NotSpecial;
    //public void setBuisnessType(String input){}
    //public BuisnessType getBuisnessType(){return buisnessType;}
    public static void BuisnessNewPost(){}
    public void addView(String Username){}
    public String getViewersOrigin(){return "CITY";}
}