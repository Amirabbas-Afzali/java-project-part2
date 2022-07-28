package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        while (!(input.equals("3")&&!Edite)&&!(input.equals("4")&&Edite)){
            System.out.println("Massage Code : "+this.massageCode+"    Date : "+DateFormat.dateFormat.reportdate(date)+
                    "    Sent by : "+SenderUserName+"\n "+massageString+"\n Replies : "+this.ReplyMassagesCodes.size());
            System.out.println("1:view Replies");
            if (this.SenderUserName.equals(viewer.UserName)){
                System.out.println("2:Reply\n3:Edite\n4:Back");
                Edite=true;
            }
            else {
                System.out.println("3:Back ");
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
            if (input.equals("3")&&Edite){
                System.out.println("Enter replacement text : ");
                String input2=Main.scanner.nextLine();
                setMassageString(input2);
            }
        }
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
}