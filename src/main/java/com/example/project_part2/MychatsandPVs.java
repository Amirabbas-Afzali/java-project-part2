package com.example.project_part2;

import com.example.project_part2.DirectMassage;
import com.example.project_part2.Group;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.USER.User;

import java.sql.SQLException;

public class MychatsandPVs {
    static MychatsandPVs mychatsandPVs=new MychatsandPVs();
    User user;
    public void start() throws SQLException {
        String input="";
        while (!input.equals("3")){
            System.out.println("1:View Groups\n2:View PVs\n3:Back");
            input=Main.scanner.nextLine();
            if (input.equals("2")){
                String input2="";
                while (!input2.equals("-2")){
                    for (String i:user.DirectMassageCodes){
                        try {


                            if (!MAINInformation.mainInformation.directmassages.get(i).isGroup){
                                MAINInformation.mainInformation.directmassages.get(i).PreShow();
                            }}
                        catch (Exception e){
                            System.out.println("My chat and PVs Exeption 22"+i);
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Enter Direct Massage Code to open\n-1:Start New Chat" +
                            "\n-2:Back");
                    input2=Main.scanner.nextLine();
                    if (MAINInformation.mainInformation.directmassages.containsKey(input2)){
                        MAINInformation.mainInformation.directmassages.get(input2).Show(user);
                    }
                    if (input2.equals("-1")){
                        // TODO: 7/24/2022
                        System.out.println("Followings:");
                        System.out.println(user.FollowingsList.toString());
                        String input3=Main.scanner.nextLine();
                        if (MAINInformation.mainInformation.users.containsKey(input3)){
                            DirectMassage.NewDirectMassage(user.UserName,input3);
                            System.out.println("Direct Massage Added You Can go Back and Open It");
                        }
                    }
                }
            }
            if (input.equals("1")){
                String input4="";

                while (!input4.equals("-3")){
                    for (String i:user.DirectMassageCodes){
                        try {
                            if (MAINInformation.mainInformation.directmassages.get(i).isGroup){
                                MAINInformation.mainInformation.directmassages.get(i).PreShow();
                            }
                        }
                        catch (Exception e){
                            System.out.println("this"+i+"this is it  ");
                        }
                    }
                    System.out.println("Enter Group ID to Open it\n-1:Create new group\n-2:search Group\n-3:Back");
                    input4=Main.scanner.nextLine();
                    if (MAINInformation.mainInformation.directmassages.containsKey(input4)){
                        if (MAINInformation.mainInformation.directmassages.get(input4).isGroup){
                            Group tempGroup=(Group) MAINInformation.mainInformation.directmassages.get(input4);
                            tempGroup.ShowGroup(user);
                        }
                        else {
                            System.out.println("This Code is not for a group.");
                        }
                    }
                    else {
                        if (input4.equals("-1")){
                            Group.CreateNewGroup(user);
                        }
                        if (input4.equals("-2")){
                            SearchGroup(user);
                        }
                    }
                }
            }
        }
    }
    public void SearchGroup(User user) throws SQLException {
        System.out.println("Enter Group ID");
        String input= Main.scanner.nextLine();
        if (!getCodeforID(input).equals("-1")){
            ((Group) MAINInformation.mainInformation.directmassages.get(getCodeforID(input))).addMember(user.UserName);
            System.out.println("You are added to the group");
        }
        else {
            System.out.println("Group doesn't Exist");
        }
    }
    String getCodeforID(String ID){
        for (DirectMassage i:MAINInformation.mainInformation.directmassages.values()){
            if (i.isGroup){
                if (((Group)i).GroupID.equals(ID)&&!((Group)i).IsPrivate){
                    return i.Code;
                }
            }
        }
        return "-1";
    }
}