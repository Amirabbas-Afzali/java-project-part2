package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Calendar;


public class Massage {
    public Massage(){}
    Massage(String text,User user) throws SQLException {
        massageCode= String.valueOf(CreateMassageCode());
        massageString=text;
        SenderUserName=user.UserName;
        date=new Date();
        MAINInformation.mainInformation.massages.put(massageCode,this);
        MassageTableDBC.massageTableDBC.setNewMassage(this);
    }
    public static Integer MassageCodeStatic;
    public static List<String>MassageCodes= new ArrayList<>();
    public List<String> LikeCodes=new ArrayList<>();
    public String massageString;
    public String massageCode="";
    public String SenderUserName="";
    public Date date;

    public List<String> ReplyMassagesCodes=new ArrayList<>();

    public static void addMassage(){}
    public String getMassageCode(){return massageCode;};

    public String getMassageString() {
        return massageString;
    }

    public String getSenderUserName() {
        return SenderUserName;
    }

    @Override
    public String toString() {
        return "Massage{" +
                "massageString='" + massageString + '\'' +
                ", massageCode='" + massageCode + '\'' +
                ", SenderUserName='" + SenderUserName + '\'' +
                ", date=" + date +
                '}';
    }

    public Date getTime() {
        return date;
    }

    // public static Massage getMassage(String MassageCode){return new Massage();}

    public void setMassageString(String massageString) throws SQLException {
        this.massageString = massageString;
        MassageTableDBC.massageTableDBC.EditOrDeleteMassage(this,false);
    }

    public void setTime(Time time) {
        this.date = time;
    }
    public void Update(){}

    public void setSenderUserName(String senderUserName) {
        SenderUserName = senderUserName;
    }
    public static String NewMassage(String SenderName,String text) throws SQLException {
        Massage massage=new Massage(text,MAINInformation.mainInformation.users.get(SenderName));
        return massage.massageCode;
    }
    public void ShowMassage(User viewer) throws SQLException {

        String input="";
        boolean Edite=false;
        while (!(input.equals("5")&&!Edite)&&!(input.equals("6")&&Edite)){
            System.out.println("Massage Code : "+this.massageCode+"    Date : "+DateFormat.dateFormat.reportdate(date)+
                    "    Sent by : "+SenderUserName+"\n "+massageString+"\n Replies : "+this.ReplyMassagesCodes.size());
            System.out.println("1:view Replies");
            if (this.SenderUserName.equals(viewer.UserName)){
                System.out.println("2:Reply");
                if (UserLikedBefore(viewer.UserName)){
                    System.out.println("3:UnLike\n4:Forward\n5:Edite and Likes\n6:Back");
                }
                else {
                    System.out.println("3:Like\n4:Forward\n5:Edite and likes\n6:Back");
                }
                Edite=true;
            }
            else {
                System.out.println("2:Reply");
                if (UserLikedBefore(viewer.UserName)){
                    System.out.println("3:Unlike\n4:Forward\n5:Back");
                }
                else {
                    System.out.println("3:Like\n4:Forward\n5:Back");
                }
            }
            input=Main.scanner.nextLine();
            if (input.equals("1")){
                String input2="";
                while (!input2.equals("-2")){
                    for (String  i:this.ReplyMassagesCodes){
                        MAINInformation.mainInformation.massages.get(i).ShowPreView();
                    }
                    System.out.println(
                            "Enter Massage Code to Open Or -2 to go back");
                    input2=Main.scanner.nextLine();
                    if (MAINInformation.mainInformation.massages.containsKey(input2)){
                        MAINInformation.mainInformation.massages.get(input2).ShowMassage(viewer);
                    }
                    else {
                        if (!input2.equals("-2")){
                            System.out.println("Invalid Commend");
                        }
                    }
                }
            }
            if (input.equals("2")){
                AddReply(viewer);
            }
            if (input.equals("3")){
                if (!UserLikedBefore(viewer.UserName)){
                    AddLike(viewer.UserName);
                    System.out.println("Liked");
                }
                else {
                    RemoveLike(viewer.UserName);
                    System.out.println("Unliked");
                }
            }
            if (input.equals("4")){
                System.out.println("Enter Group or DirectMassage Code to forward or Back");
                for (String i:viewer.DirectMassageCodes){
                    MAINInformation.mainInformation.directmassages.get(i).PreShow();
                }
                String code=Main.scanner.nextLine().trim();
                if (viewer.DirectMassageCodes.contains(code)){
                    String code1=Massage.NewMassage(viewer.UserName,this.massageString);
                    MAINInformation.mainInformation.directmassages.get(code).addMassage(code1);
                    System.out.println("Massage forwarded");
                }
            }
            if (input.equals("5")&&Edite){
                System.out.println("1:Edit\n2:View Likes");
                String input2=Main.scanner.nextLine().trim();
                if (input2.equals("1")){
                    System.out.println("Enter replacement text : ");
                    String input3=Main.scanner.nextLine();
                    setMassageString(input3);
                }
                if (input2.equals("2")){
                    LikeManager();
                }
            }
        }
    }
    void LikeManager(){
        String input="";
        while (!input.equals("Back")){
            System.out.println("Enter\n1:View All Likes\n2:View recentLikes\nBack");
            input=Main.scanner.nextLine().trim();
            if (input.equals("1")){
                int sum=0;
                for (String i:LikeCodes){
                    sum+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(false,new Date(),false,false);
                }
                System.out.println("Sum : "+sum);
            }
            if (input.equals("2")){
                System.out.println("Enter How many days ago?");
                String input2=Main.scanner.nextLine().trim();
                try {
                    int days=Integer.parseInt(input2);
                    Date date=getNDaysAgo(days);
                    days=0;
                    for (String i:LikeCodes){
                        days+=MAINInformation.mainInformation.likeHandleMap.get(i).ShowLikeHandle(true,date,false,false);
                    }
                    System.out.println("Sum : "+days);
                }
                catch (Exception e){
                    System.out.println("Invalid Command");
                }
            }
        }
    }

    public void AddLike(String LikerUserName) throws SQLException {
        this.LikeCodes.add(LikeHandle.NewLikeHandles(LikerUserName,"-1",false));
        MassageTableDBC.massageTableDBC.EditOrDeleteMassage(this,false);
    }
    public void RemoveLike(String LikerUserName)throws SQLException{
        String ii="";
        for (String i:LikeCodes){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName.equals(LikerUserName)){
                ii=i;
            }
        }
        LikeCodes.remove(ii);
        MassageTableDBC.massageTableDBC.EditOrDeleteMassage(this,false);
    }

    public void ShowPreView(){
        String show=massageString;
        if (show.length()>30){
            show=show.substring(0,30);
        }
        System.out.println("Massage Code : "+massageCode+"    text : "+show+" ...");
    }
    Integer CreateMassageCode() throws SQLException {
        MassageCodeStatic++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("Massage",MassageCodeStatic);
        return MassageCodeStatic-1;
    }
    void AddReply(User user) throws SQLException {
        System.out.println("Enter text");
        String input=Main.scanner.nextLine();
        Massage massage=new Massage(input,user);
        AddReplyToList(massage.massageCode);
    }
    public void AddReplyToList(String Code) throws SQLException {
        this.ReplyMassagesCodes.add(Code);
        MassageTableDBC.massageTableDBC.EditOrDeleteMassage(this,false);
    }
    public static Integer sum=0;
    public void GetThreadSum(){
        sum++;
        if (this.ReplyMassagesCodes.size()>0){
            for (String i:this.ReplyMassagesCodes){
                try {
                    MAINInformation.mainInformation.massages.get(i).GetThreadSum();
                }
                catch (Exception e){
                    System.out.println(i+"  " +sum);
                }
            }
        }
    }
    boolean UserLikedBefore(String UserName){
        for (String i:LikeCodes){
            if (MAINInformation.mainInformation.likeHandleMap.get(i).LikerUserName.equals(UserName)){
                return true;
            }
        }
        return false;
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
}