package com.example.project_part2.USER;
import com.example.project_part2.*;
import com.example.project_part2.POST.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.*;

import static java.util.Calendar.*;

public class User {
    public static List<String> UserNamesList=new ArrayList<>();
    /////////////////////////////////////////
    public Map<String,User> FollowerMap =new HashMap<>();
    public Map<String,User> FollowingMap =new HashMap<>();
    ///////////////////////////////////////
    public List<String>FollowersList=new ArrayList<>();
    public List<String>FollowingsList=new ArrayList<>();
    ///////////////////////////////////////
    public List<String>BlockedList=new ArrayList<>();
    public Map<String,User> BlockedMap =new HashMap<>();
    //////////////////////////////////////
    public List<String>CloseFriendList=new ArrayList<>();
    public Map<String,User> CloseFriendMap =new HashMap<>();
    //////////////////////////////////////
    public List<String>RequestList=new ArrayList<>();
    public Map<String,User> RequestMap =new HashMap<>();
    //////////////////////////////////////
    public List<String>TimeLinePost=new ArrayList<>();
    public List<Post> tempTimeLinePost=new ArrayList<>();
    public  List<Post> posts=new ArrayList<>();
    ///////////////////////////////////////////////////////////
    public List<String>StoryCodeList=new ArrayList<>();
    public List<Story> MyStories=new ArrayList<>();
    ////////////////////////////////////////////////
    public String UserName;
    public String Name;
    public int age;
    public int Blocked;
    public Date Birthdate;
    public String Birthdatestr;
    public boolean Kind;
    public boolean isman;
    public boolean married;
    public boolean isreport=false;
   public  String profilepicpath;
    public String City;
    public String Country;
    public List<String> PostCodesList=new ArrayList<>();
    public String Bio="";
    public String PassWord="";
    public List<String> GroupCodes=new ArrayList<>();
    public List<String> DirectMassageCodes=new ArrayList<>();
    public List<String> LikedPostCodes=new ArrayList<>();

    public void setName(String name) throws SQLException {
        this.Name=name;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setIsman(boolean isman) throws SQLException {
        this.isman = isman;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setBlocked(boolean increase) throws SQLException {
        if(increase){this.Blocked++;}
        else {this.Blocked--;}
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setMarried(boolean married) throws SQLException {
        this.married = married;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setCity(String city) throws SQLException {
        City = city;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setCountry(String country) throws SQLException {
        Country = country;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void setProfilepicpath(String path) throws SQLException {
        profilepicpath = path;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public  void setUnFollow(User user) throws SQLException {
        this.FollowingMap.remove(user.UserName,user);
        this.FollowingsList.remove(user.UserName);
        user.FollowerMap.remove(this.UserName,this);
        user.FollowersList.remove(this.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public  void setFollow(User user) throws SQLException {
        this.FollowingMap.put(user.UserName,user);
        this.FollowingsList.add(user.UserName);
        user.FollowerMap.put(this.UserName,this);
        user.FollowersList.add(this.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public  void setDeleteRequest(User user) throws SQLException {
        this.RequestMap.remove(user.UserName,user);
        this.RequestList.remove(user.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public  void setRequest(User user) throws SQLException {
        user.RequestMap.put(this.UserName,this);
        user.RequestList.add(this.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public  void setRemove(User user) throws SQLException {
        this.FollowerMap.remove(user.UserName,user);
        this.FollowersList.remove(user.UserName);
        user.FollowingMap.remove(this.UserName,this);
        user.FollowingsList.remove(this.UserName);
        if(this.CloseFriendMap.containsValue(user)){
            this.CloseFriendMap.remove(user.UserName,user);
            this.CloseFriendList.remove(user.UserName);
        }
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public  void setBlockedUser(User user) throws SQLException {
        if(this.FollowerMap.containsValue(user)){
            this.FollowerMap.remove(user);
            this.FollowersList.remove(user.UserName);
            user.FollowingMap.remove(this);
            user.FollowingsList.remove(this.UserName);
        }
        if(this.FollowingMap.containsValue(user)) {
            user.FollowerMap.remove(this);
            user.FollowersList.remove(this.UserName);
            this.FollowingMap.remove(user);
            this.FollowingsList.remove(user.UserName);
        }
        this.BlockedMap.put(user.UserName,user);
        this.BlockedList.add(user.UserName);
        user.setBlocked(true);
        CheckReportUser(user);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public void CheckReportUser(User user){
        if(user.Blocked>=5){user.isreport=true;}
        else {user.isreport=false;}
    }

    public  void setUnBlockedUser(User user) throws SQLException {
        this.BlockedMap.remove(user.UserName,user);
        this.BlockedList.remove(user.UserName);
        user.setBlocked(false);
        CheckReportUser(user);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
        UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
    }

    public  void setCloseFriendUser(User user) throws SQLException {
        this.CloseFriendMap.put(user.UserName,user);
        this.CloseFriendList.add(user.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public  void setRemoveCloseFriendUser(User user) throws SQLException {
        this.CloseFriendMap.remove(user.UserName,user);
        this.CloseFriendList.remove(user.UserName);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void addLikedPostCode(String PostCode,boolean add) throws SQLException {
        if (add){
            this.LikedPostCodes.add(PostCode);
        }
        else {
            this.LikedPostCodes.remove(PostCode);
        }
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }
    //-----------------------------------------------------
    public void LoadPostCodesList(){}
    public void LoadFollowersList(){}
    public void LoadFollowingList(){}
    public void LoadGroupCodes(){}
    public void LoadDirectMassageCodes(){}
    public void LoadLikedPostCodes(){}
    public void LoadTimeLinePost(){}
    public List<String>getTimeLinePost(){return TimeLinePost;}
    public void addFollower(String _UserName){}
    public void addFollowing(String _UserName){}
    public void addDirectMassageCode(){}
    public void addGroup(){}
    public void Update(){}
    public List<String> getFollowers(){
        return FollowersList;
    }
    public List<String>getGroupCodes(){
        return GroupCodes;
    }
    public String getGroup(int index,boolean poll){
        return "temp";
    }
    public List<String>getDirectMassageCodes(){
        return DirectMassageCodes;
    }
    public String getDirectMassage(int index,boolean poll){
        return "temp";
    }
    public List<String>getPostCodesList(){
        return PostCodesList;
    }
    public List<String>getLikedPostCodes(){return LikedPostCodes;}
    String getLikedPost(int index,boolean poll){
        return "temp";
    }
    public String getPost(int index,boolean poll){
        return "temp";
    }
    public void NewPost(){}
    public static User getUserPublic(String UserName){
        return new User();
    }
    public static User getUserPrivate(String UserName,String _PassWord){
        return new User();
    }
    public void TimeLine(){}
    public void DMHandle(){}
    //----------------------------------------------------------
    public void addPostCode(String _PostCode) throws SQLException {
        this.PostCodesList.add(_PostCode);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public void addPostToPosts(Post input) throws SQLException {
        posts.add(input);
        addPostCode(input.PostCode);
    }

    public List<String> getFollowings(){
        return FollowingsList;
    }

    public void setBio(String _Bio) throws SQLException {
        this.Bio=_Bio;
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public List<User> getFollowing(){
        List<User> result=new ArrayList<>();
        for (String i:getFollowings()){
            result.add(MAINInformation.mainInformation.users.get(User.UserNamesList.indexOf(i)));
        }
        return result;
    }

    public String getFollowers(int index,boolean poll){
        return FollowersList.get(index);
    }
    public void setBirthdate(){
        try {
            Birthdate= DateFormat.dateFormat.StrToDate(Birthdatestr);
        }
        catch (Exception e){
            System.out.println("Error at User 139");
        }
        calculateAGE();
    }
    public int calculateAGE(){
        Calendar a=getCalender(this.Birthdate),b=getCalender(new Date());
        int diff = b.get(YEAR) - a.get(YEAR);
        if (a.get(MONTH) > b.get(MONTH) ||
                (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
            diff--;
        }
        try {
            age= diff;
        }
        catch (Exception e){
            System.out.println("Error in User Line 154");
        }
        return this.age;
    }
    public static Calendar getCalender(Date date){
        Calendar calendar=Calendar.getInstance(Locale.US);
        calendar.setTime(date);
        return calendar;
    }
    public void addPostCodeToPosts(String _postCode) throws SQLException {
        this.PostCodesList.add(_postCode);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }
    public void addDirectMassageCode(String Code) throws SQLException {
        this.DirectMassageCodes.add(Code);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }
    public void removeDirectMassageCode(String Code) throws SQLException {
        this.DirectMassageCodes.remove(Code);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    void sendMassage(String toWhome) throws SQLException {
        boolean newChat=true;
        for (String i:this.DirectMassageCodes){
            if (!MAINInformation.mainInformation.directmassages.get(i).isGroup){
                if (MAINInformation.mainInformation.directmassages.get(i).UserNamesInChat.contains(toWhome)){
                    MAINInformation.mainInformation.directmassages.get(i).Show(this);
                    newChat=false;
                }
            }
        }
        if (newChat){
            String massage= DirectMassage.NewDirectMassage(this.UserName,toWhome);
            System.out.println("New DirectMassage created.");
            MAINInformation.mainInformation.directmassages.get(massage).Show(this);
        }
    }

    public void ShowStory(User Loginuser) throws SQLException {
        boolean flag2 = true,f=true;
        String inputcode, ww;
        if(this.CloseFriendMap.containsValue(Loginuser)){
            if (this.StoryCodeList.size() > 0) {
                while (f) {
                    for (Story story : this.MyStories) {
                        System.out.println("Code : " + story.StoryCode + "   ,    Text : " + story.text);
                    }
                    System.out.println("");
                    System.out.println("Enter a Code to show more details (Enter  'Back'  to end) : ");
                    inputcode = Main.scanner.nextLine();
                    if (this.StoryCodeList.contains(inputcode)) {
                        Story temp = MAINInformation.mainInformation.stories.get(inputcode);
                        System.out.println("Text : " + temp.text
                                + "\nTime left to delete this story : " + (24 - (new Date().getTime() - temp.date.getTime()) / (1000 * 60 * 60 * 24)) + " hours");
                        if (temp.IsClose) {
                            System.out.println("(Close friends)");
                        }
                        System.out.println("");
                        flag2 = true;
                        while (flag2) {
                            if (!temp.likersnameList.contains(Loginuser.UserName)) {
                                System.out.println("1.Like");
                            } else {
                                System.out.println("1.dislike");
                            }
                            System.out.println("2.Back");
                            if (!temp.viewersnameList.contains(Loginuser.UserName)) {
                                temp.viewersnameList.add(Loginuser.UserName);
                            }
                            ///up
                            StoryTableDBC.storyTableDBC.DeleteStory(temp, false);
                            ww = Main.scanner.nextLine();
                            if (ww.equals("1")) {
                                if (!temp.likersnameList.contains(Loginuser.UserName)) {
                                    temp.likersnameList.add(Loginuser.UserName);
                                } else {
                                    temp.likersnameList.remove(Loginuser.UserName);
                                }
                                ////up
                                StoryTableDBC.storyTableDBC.DeleteStory(temp, false);
                            } else if (ww.equals("2")) {
                                flag2 = false;
                            } else {
                                System.out.println("Invalid command!");
                            }
                        }
                    }
                    else if (inputcode.equals("Back")) {
                        f = false;
                    }
                    else {
                        System.out.println("Story not found !");
                    }
                }
            }
            else {
                System.out.println("Empty !");
            }
        }
        else {

            if (this.PublicStory(Loginuser).size() > 0) {
                while (f){
                    for (Story story : this.PublicStory(Loginuser)) {
                        System.out.println("Code : " + story.StoryCode + "   ,    Text : " + story.text);
                    }
                    System.out.println("");
                    System.out.println("Enter a Code to show more details (Enter  'Back'  to end) : ");
                    inputcode = Main.scanner.nextLine();
                    if (this.PublicStory(Loginuser).contains(MAINInformation.mainInformation.stories.get(inputcode))) {
                        Story temp = MAINInformation.mainInformation.stories.get(inputcode);
                        System.out.println("Text : " + temp.text
                                + "\nTime left to delete this story : " +(24- new Date().getHours()+MAINInformation.mainInformation.stories.get(inputcode).date.getHours()) + " hours");
                        System.out.println("");
                        flag2 = true;
                        while (flag2) {
                            if (!temp.likersnameList.contains(Loginuser.UserName)) {
                                System.out.println("1.Like");
                            } else {
                                System.out.println("1.dislike");
                            }
                            System.out.println("2.Back");
                            if (!temp.viewersnameList.contains(Loginuser.UserName)) {
                                temp.viewersnameList.add(Loginuser.UserName);
                            }
                            ///up
                            StoryTableDBC.storyTableDBC.DeleteStory(temp, false);
                            ww = Main.scanner.nextLine();
                            if (ww.equals("1")) {
                                if (!temp.likersnameList.contains(Loginuser.UserName)) {
                                    temp.likersnameList.add(Loginuser.UserName);
                                } else {
                                    temp.likersnameList.remove(Loginuser.UserName);
                                }
                                ////up
                                StoryTableDBC.storyTableDBC.DeleteStory(temp, false);
                            } else if (ww.equals("2")) {
                                flag2 = false;
                            } else {
                                System.out.println("Invalid command!");
                            }
                        }
                    }
                    else if (inputcode.equals("Back")) {
                        f = false;
                    }
                    else {
                        System.out.println("Story not found !");
                    }}
            } else {
                System.out.println("Empty !");
            }


        }
    }

    public List<Story> PublicStory(User Loginuser){
        List<Story> result=new ArrayList<>();
        for(Story story:this.MyStories){
            if(!story.IsClose){
                result.add(story);
            }
        }
        return result;
    }
    public void Retweet(String Postcode,boolean Add) throws SQLException {
        if (Add){
            this.addPostCodeToPosts(Postcode);
        }
        else {
            this.removePostCode(Postcode);
        }
    }

    public void removePostCode(String PostCode) throws SQLException {
        this.PostCodesList.remove(PostCode);
        UserTableDBC.userTableDBC.EditorDeleteUser(this,false);
    }

    public  void ShowAuser(User Loginuser,User  mokhatab) throws SQLException {
        if(mokhatab.Kind){
            if(Loginuser.Kind){
                BusinessUser businessUser=(BusinessUser)mokhatab;
                BusinessUser.businessUser.ShowBusUserByBusUser(businessUser,6,Loginuser);
            }
            else {
                BusinessUser businessUser=(BusinessUser)mokhatab;
                OrdinaryUser.ordinaryUser.ShowBusUserByOrdUser(businessUser,6,Loginuser);
            }
        }
        else {
            if(Loginuser.Kind){
                OrdinaryUser ordinaryUser =(OrdinaryUser) mokhatab;
                BusinessUser.businessUser.ShowOrdUserByBusUser(ordinaryUser,6,Loginuser);
            }
            else {
                OrdinaryUser ordinaryUser =(OrdinaryUser) mokhatab;
                OrdinaryUser.ordinaryUser.ShowOrdUserByOrdUser(ordinaryUser,6,Loginuser);
            }
        }
    }

}
