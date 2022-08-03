package com.example.project_part2;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Group extends DirectMassage{
    public List<String> Admins=new ArrayList<>();
    public String GroupID="";
    public boolean IsPrivate=true;
    int NumberOfUsers;
    public String Owener="";
    public String bio="";
    public String ProfilePath="";
    public Group(){}
    Group(String Owner, List<String> users,String ID,boolean isprivate) throws SQLException {
        this.Owener=Owner;
        this.Code=generateDMCode();
        this.IsPrivate=isprivate;
        this.GroupID=ID;
        this.isGroup=true;
        for (String i:users){
            this.addMember(i);
        }
        this.addMember(Owner);
        MAINInformation.mainInformation.directmassages.put(Code,this);
    }
    public void ShowGroup(User Viewer) throws SQLException {
        String input="";
        while (!input.equals("-3")){
            ShowMassages(6);
            System.out.println("Enter Massage Or Inter Massage Code To View it\n-1:View more massage\n-2:view group info\n-3:Back");
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
                    if (input.equals("-2")){
                        ViewInfo(Viewer);
                    }
                    else {
                        if (!input.equals("-3")){
                            //  System.out.println("Inter text");
                            //String text=Main.scanner.nextLine();
                            String code=Massage.NewMassage(Viewer.UserName,input);
                            this.addMassage(code);
                            System.out.println("Massage Added");
                        }
                    }
                }
            }
        }
    }
    private void ViewInfo(User Viewer) throws SQLException{
        String input="";
        while (!(input.equals("4")&&this.Owener.equals(Viewer.UserName))&&!(input.equals("3")&&!this.Owener.equals(Viewer.UserName))){
            System.out.println("Owner : "+this.Owener+"\nBio : "+this.bio);
            System.out.println("1:Set Bio\n2:add member");
            if(this.Owener.equals(Viewer.UserName)||this.Admins.contains(Viewer.UserName)){
                System.out.println("3:Administration\n4:Back");
            }
            else {
                System.out.println("3:Back");
            }
            input=Main.scanner.nextLine();
            if (input.equals("1")){
                String input2=Main.scanner.nextLine();
                this.setBio(input2);
            }
            if (input.equals("2")){
                String input3;
                // TODO: 7/24/2022  suggest user name
                System.out.println("Enter username : ");
                input3=Main.scanner.nextLine();
                if (MAINInformation.mainInformation.users.containsKey(input3)){
                    try {
                        addMember(input3);
                        System.out.println("Member Added");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
            if (input.equals("3")&&(this.Owener.equals(Viewer.UserName)||this.Admins.contains(Viewer.UserName))){
                String infoSTR="";
                while (!infoSTR.equals("Back")){
                    System.out.println("1:Remove member");
                    if ((this.Owener.equals(Viewer.UserName))){
                        System.out.println("2:Add Admin\n3:Remove Admin\n4:Change Group Privacy\n5:Change Group ID\nBack");
                    }
                    else {
                        System.out.println("Back");
                    }
                    infoSTR=Main.scanner.nextLine().trim();
                    if(infoSTR.equals("1")){
                        removeUser(Viewer.UserName);
                    }
                    if ((this.Owener.equals(Viewer.UserName))){
                        if (infoSTR.equals("2")){
                            for (String i:this.UserNamesInChat){
                                if (!Admins.contains(i)){
                                    System.out.println(i);
                                }
                            }
                            System.out.println("Enter Username");
                            String Admin=Main.scanner.nextLine();
                            AddAdmin(Admin);
                        }
                        if (infoSTR.equals("3")){
                            for (String i:this.Admins){
                                System.out.println(i);
                            }
                            System.out.println("Enter Username");
                            String admin=Main.scanner.nextLine().trim();
                            RemoveAdmin(admin);
                        }
                        if (infoSTR.equals("4")){
                            String privacy="";
                            System.out.println("1:Set Group Private\n2:Set Group Public\ndefault private");
                            privacy=Main.scanner.nextLine().trim();
                            if (privacy.equals("1")){
                                SetPrivacy(true);
                            }else {
                                if (privacy.equals("2")){
                                    SetPrivacy(false);
                                }
                                else {
                                    SetPrivacy(true);
                                }
                            }
                        }
                        if (infoSTR.equals("5")){
                            String NID="";
                            System.out.println("Enter new ID");
                            while (!IDCheck(NID)){
                                NID=Main.scanner.nextLine().trim();
                            }
                            this.GroupID=NID;
                            DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);

                        }
                    }
                }
            }
        }
    }
    void SetPrivacy(boolean input) throws SQLException {
        this.IsPrivate=input;
        DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false );
    }
    public void addMember(String username) throws SQLException {
        this.UserNamesInChat.add(username);
        MAINInformation.mainInformation.users.get(username).addDirectMassageCode(this.Code);
        DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);
    }
    public void removeMember(String username) throws SQLException {
        this.UserNamesInChat.remove(username);
        MAINInformation.mainInformation.users.get(username).removeDirectMassageCode(this.Code);
        DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);

    }
    public void setBio(String _bio) throws SQLException {
        this.bio=_bio;
        DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);
    }
    public String getOwener(){return Owener;}
    public int getNumberOfUsers() {
        return NumberOfUsers;
    }
    public static Group getGroup(String groupCode){return new Group();}
    public void addGroup(){}

    public void AddAdmin(String userName) throws SQLException {
        if (this.UserNamesInChat.contains(userName)){
            this.Admins.add(userName);
            DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);
            System.out.println("Admin added");
        }
        else System.out.println("This user is not a member");
    }
    public void RemoveAdmin(String userName) throws SQLException {
        if (this.Admins.contains(userName) ){
            this.Admins.remove(userName);
            DircectMassageTableDBC.dircectMassageTableDBC.EditorDeleteDirect(this,false);
            System.out.println("Admin removed.");
        }
        else {
            System.out.println("This user is not admin");
        }
    }


    public void removeUser(String viewerUsername){
        String input4;
        // TODO: 7/24/2022  suggest user name
        System.out.println("This group Users are : "+this.UserNamesInChat);
        System.out.println("Enter username to Remove : ");
        input4=Main.scanner.nextLine();
        if (MAINInformation.mainInformation.users.containsKey(input4)&&this.UserNamesInChat.contains(input4)){
            try {
                if (!input4.equals(viewerUsername)){
                    removeMember(input4);
                    System.out.println("Member Removed");
                }
                else {
                    System.out.println("You can't kick yourself ");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public String toString() {
        return "Group{" +
                "isGroup=" + isGroup +
                ", MassageCodes=" + MassageCodes +
                ", Code='" + Code + '\'' +
                ", UserNamesInChat=" + UserNamesInChat +
                ", Owener='" + Owener + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
    public static void CreateNewGroup(User user) throws SQLException {
        // TODO: 7/24/2022 suggest users
        boolean back=false;
        List<String> usernames=new ArrayList<>();
        String input="";
        System.out.println("Enter Group usernames\n-1:Done\n-2:Back and stop process ");
        while (!(input.equals("-2")||input.equals("-1"))){
            input=Main.scanner.nextLine();
            if (MAINInformation.mainInformation.users.containsKey(input)){
                usernames.add(input);
                System.out.println("User added");
            }
            else {
                System.out.println("Invalid Username!!!");
            }
        }
        if (input.equals("-1")){
            System.out.println("Enter group ID");
            String ID="";
            while (!IDCheck(ID)){
                ID=Main.scanner.nextLine();
            }

            System.out.println("Is Group Private? \n1:Yes\n2:No");
            String input2=Main.scanner.nextLine().trim();
            if (input2.equals("1")){
                Group group=new Group(user.UserName,usernames,ID,true);
            }
            else {
                if (input2.equals("2")){
                    Group group=new Group(user.UserName,usernames,ID,false);
                }
                else {
                    System.out.println("Invalid Comment default setting (private group) applied");
                    Group group=new Group(user.UserName,usernames,ID,true);
                }
            }
            System.out.println("New Group Created");
        }
        if (input.equals("-2")){
            System.out.println("Canceled");
        }

    }
    static  boolean IDCheck(String ID){
        for (DirectMassage i:MAINInformation.mainInformation.directmassages.values()){
            if (i.isGroup){
                Group j=(Group) i;
                if (j.GroupID.equals(ID)){
                    System.out.println("This ID Exists");
                    return false;
                }
            }
        }
        if (ID.length()<4){
            System.out.println("ID length must be > 4");
            return false;
        }
        return true;
    }
}