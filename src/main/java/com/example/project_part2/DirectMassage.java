package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class DirectMassage {
    public DirectMassage(){}
    DirectMassage(String User1,String User2) throws SQLException {
        UserNamesInChat.add(User1);
        UserNamesInChat.add(User2);
        Code=generateDMCode();
        MAINInformation.mainInformation.directmassages.put(this.Code,this);
        DircectMassageTableDBC.dircectMassageTableDBC.setDirectMassage(this);
    }
    public DirectMassage(String User1){
        UserNamesInChat.add(User1);
    }

    public static String NewDirectMassage(String User1,String User2) throws SQLException {
        DirectMassage directMassage=new DirectMassage(User1,User2);
        MAINInformation.mainInformation.users.get(User1).addDirectMassageCode(directMassage.Code);
        MAINInformation.mainInformation.users.get(User2).addDirectMassageCode(directMassage.Code);
        return directMassage.Code;
    }
    public boolean isGroup=false;
    public static Integer DirectMassageCodeStatic;
    public static List<String>DirectCodesList=new ArrayList<>();
    public List<String>MassageCodes=new ArrayList<>();
    public String Code="";
    List<Massage>MassagesList=new ArrayList<>();
    public List<String>UserNamesInChat=new ArrayList<>();
    public void PreShow(){
        try {
            if (!this.isGroup){
                System.out.println("DM Code : "+this.Code+"  Username in chat : "+UserNamesInChat.toString());
            }
            else {
                System.out.println("Group Code : "+this.Code+"  Username in chat : "+UserNamesInChat.toString());

            }
        }
        catch (Exception e){
            System.out.println("DMs 38");
        }
    }
    public void Show(User Viewer) throws SQLException {
        String input="";
        while (!input.equals("-2")){
            ShowMassages(6);
            System.out.println("Enter Massage Or Inter Massage Code To View it\n-1:View more massage\n-2:Back");
            input=Main.scanner.nextLine();
            if (MAINInformation.mainInformation.massages.containsKey(input)){
                MAINInformation.mainInformation.massages.get(input).ShowMassage(Viewer);
            }
            else {
                if (input.equals("-1")){
                    System.out.println("How many massage you want to view?");
                    input=Main.scanner.nextLine();
                    ShowMassages(Integer.parseInt(input));
                }
                else {
                    if (!input.equals("-2")){
                        //  System.out.println("Inter text");
                        // String text=Main.scanner.nextLine();
                        String code=Massage.NewMassage(Viewer.UserName,input);
                        this.addMassage(code);
                        System.out.println("Massage Added");
                    }
                }
            }
        }
    }
    public void ShowMassages(Integer n){
        if (MassageCodes.size()<n){
            n=MassageCodes.size();
        }
        List<Integer> integerList=new ArrayList<>();
        for (String i:MassageCodes){
            integerList.add(Integer.parseInt(i));
        }
        Collections.sort(integerList);
        for (int i =integerList.size();(integerList.size()-i)<n;i--){
            try {
                MAINInformation.mainInformation.massages.get(integerList.get(i-1).toString()).ShowPreView();
            }
            catch (Exception e){
                System.out.println("DM 35");
            }
        }


    }
    public void addMassage(String code) throws SQLException {
        MassageCodes.add(code);
        DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);
    }
    String generateDMCode() throws SQLException {
        String result=String.valueOf(DirectMassage.DirectMassageCodeStatic);
        DirectMassage.DirectMassageCodeStatic++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("DirectMassage",DirectMassageCodeStatic);
        return result;
    }

    public void LoadMassageCodes(){}
    public void LoadUserNamesInChat(){}
    public List<String>getMassageCodes(){return MassageCodes;}
    public void addMassage(Massage input){}
    public static void addDirect(){}
    public void Update(){}
    public List<String>getUserNamesInChat(){return UserNamesInChat;}
    public  void addUserNameInChat(){}
    public static DirectMassage getDirectMassage(String DirectCode){return new DirectMassage();}
}