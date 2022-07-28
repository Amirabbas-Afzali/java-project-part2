package com.example.project_part2.DataBaseController;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.project_part2.POST.*;

public class UserTableDBC {

    Connection connection;
    UserTableDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");

        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    public static UserTableDBC userTableDBC;
    static {
        try {
            userTableDBC = new UserTableDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public  <T extends User> T getUser(String userName) throws SQLException {
        String query="SELECT * FROM usertable WHERE UserName="+"'"+userName+"'";
        Statement statement=connection.createStatement();
        T result = null;
        ResultSet resultSet= statement.executeQuery(query);
        Boolean isBus =true;
        while (resultSet.next()){
            isBus=resultSet.getBoolean("Kind");

            if(isBus){
                BusinessUser businessUser1=new BusinessUser();
                businessUser1.UserName=resultSet.getString("UserName");
                businessUser1.Name=resultSet.getString("Name");
                businessUser1.Birthdate=resultSet.getDate("BirthDay");
                businessUser1.age=businessUser1.calculateAGE();
                businessUser1.Kind=resultSet.getBoolean("Kind");
                businessUser1.isman=resultSet.getBoolean("isman");
                businessUser1.married=resultSet.getBoolean("married");
                businessUser1.City=resultSet.getString("City");
                businessUser1.profilepicpath=resultSet.getString("ProfPicPath");
                businessUser1.Country=resultSet.getString("Country");
                businessUser1.PostCodesList=StringtoListCode(resultSet.getString("PostCodeList"));
                businessUser1.FollowersList=StringtoListCode(resultSet.getString("FollowersList"));
                businessUser1.FollowingsList=StringtoListCode(resultSet.getString("FollowingList"));
                businessUser1.Bio=resultSet.getString("bio");
                businessUser1.PassWord=resultSet.getString("Password");
                businessUser1.GroupCodes=StringtoListCode(resultSet.getString("GroupCodes"));
                businessUser1.DirectMassageCodes=StringtoListCode(resultSet.getString("DirectMassagesCodes"));
                businessUser1.LikedPostCodes=StringtoListCode(resultSet.getString("LikedPostsCodes"));
                businessUser1.ProfileViewers=StringtoListCode(resultSet.getString("BusProfileViewers"));
                businessUser1.buisnessTypeINT=resultSet.getInt("BusTypeINT");
                businessUser1.setBuisnessType(Integer.toString(resultSet.getInt("BusTypeINT")));
                businessUser1.BlockedList=StringtoListCode(resultSet.getString("BlockedList"));
                businessUser1.CloseFriendList=StringtoListCode(resultSet.getString("CloseFriendList"));
                businessUser1.StoryCodeList=StringtoListCode(resultSet.getString("StoryCodeList"));
                businessUser1.Blocked=resultSet.getInt("Blocked");
                result= (T) businessUser1;

            }
            else {

                OrdinaryUser businessUser1=new OrdinaryUser();
                businessUser1.UserName=resultSet.getString("UserName");
                businessUser1.Name=resultSet.getString("Name");
                businessUser1.Birthdate=resultSet.getDate("BirthDay");
                businessUser1.age=businessUser1.calculateAGE();
                businessUser1.Kind=resultSet.getBoolean("Kind");
                businessUser1.profilepicpath=resultSet.getString("ProfPicPath");
                businessUser1.isman=resultSet.getBoolean("isman");
                businessUser1.married=resultSet.getBoolean("married");
                businessUser1.Private=resultSet.getBoolean("OrdPrivate");
                businessUser1.City=resultSet.getString("City");
                businessUser1.Country=resultSet.getString("Country");
                businessUser1.PostCodesList=StringtoListCode(resultSet.getString("PostCodeList"));
                businessUser1.FollowersList=StringtoListCode(resultSet.getString("FollowersList"));
                businessUser1.FollowingsList=StringtoListCode(resultSet.getString("FollowingList"));
                businessUser1.Bio=resultSet.getString("bio");
                businessUser1.PassWord=resultSet.getString("Password");
                businessUser1.GroupCodes=StringtoListCode(resultSet.getString("GroupCodes"));
                businessUser1.DirectMassageCodes=StringtoListCode(resultSet.getString("DirectMassagesCodes"));
                businessUser1.LikedPostCodes=StringtoListCode(resultSet.getString("LikedPostsCodes"));
                businessUser1.BlockedList=StringtoListCode(resultSet.getString("BlockedList"));
                businessUser1.CloseFriendList=StringtoListCode(resultSet.getString("CloseFriendList"));
                businessUser1.RequestList=StringtoListCode(resultSet.getString("RequestList"));
                businessUser1.StoryCodeList=StringtoListCode(resultSet.getString("StoryCodeList"));
                businessUser1.Blocked=resultSet.getInt("Blocked");
                result= (T) businessUser1;

            }     }
        statement.close();
        return result;
    }

    public <T extends User> void setUser(T businessUser1) throws SQLException {
        java.sql.Date date=new java.sql.Date(businessUser1.Birthdate.getTime());
        if(businessUser1.Kind) {
            BusinessUser businessUser=(BusinessUser) businessUser1;
            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO usertable (UserName,Name,BirthDay,Kind,isman,married,City" +
                    ",Country,PostCodeList,FollowersList,FollowingList,bio,Password" +
                    ",GroupCodes,DirectMassagesCodes,LikedPostsCodes,BusProfileViewers" +
                    ",BusTypeINT,BlockedList,CloseFriendList,StoryCodeList,Blocked,ProfPicPath) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, businessUser.UserName);
            statement.setString(2, businessUser.Name);
            statement.setDate(3, date);
            statement.setBoolean(4, businessUser.Kind);
            statement.setBoolean(5, businessUser.isman);
            statement.setBoolean(6, businessUser.married);
            statement.setString(7, businessUser.City);
            statement.setString(8, businessUser.Country);
            statement.setString(9, generatePostCodeString(businessUser.PostCodesList));
            statement.setString(10, generatePostCodeString(businessUser.FollowersList));
            statement.setString(11, generatePostCodeString(businessUser.FollowingsList));
            statement.setString(12, businessUser.Bio);
            statement.setString(13, businessUser.PassWord);
            statement.setString(14, generatePostCodeString(businessUser.GroupCodes));
            statement.setString(15, generatePostCodeString(businessUser.DirectMassageCodes));
            statement.setString(16, generatePostCodeString(businessUser.LikedPostCodes));
            statement.setString(17, generatePostCodeString(businessUser.ProfileViewers));
            statement.setInt(18, businessUser.buisnessTypeINT);
            statement.setString(19, generatePostCodeString(businessUser.BlockedList));
            statement.setString(20, generatePostCodeString(businessUser.CloseFriendList));
            statement.setString(21, generatePostCodeString(businessUser.StoryCodeList));
            statement.setInt(22, businessUser.Blocked);
            statement.setString(23, businessUser.profilepicpath);
            statement.executeUpdate();
            statement.close();
        }
        else {
            OrdinaryUser businessUser=(OrdinaryUser) businessUser1;
            PreparedStatement statement= connection.prepareStatement("" +
                    "INSERT INTO usertable (UserName,Name,BirthDay,Kind,isman,married,OrdPrivate,City" +
                    ",Country,PostCodeList,FollowersList,FollowingList,bio,Password" +
                    ",GroupCodes,DirectMassagesCodes,LikedPostsCodes,BlockedList,CloseFriendList,RequestList,StoryCodeList,Blocked,ProfPicPath) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,businessUser.UserName);
            statement.setString(2, businessUser.Name);
            statement.setDate(3, date);
            statement.setBoolean(4,businessUser.Kind);
            statement.setBoolean(5,businessUser.isman);
            statement.setBoolean(6,businessUser.married);
            statement.setBoolean(7,businessUser.Private);
            statement.setString(8,businessUser.City);
            statement.setString(9,businessUser.Country);
            statement.setString(10,generatePostCodeString(businessUser.PostCodesList));
            statement.setString(11,generatePostCodeString(businessUser.FollowersList));
            statement.setString(12,generatePostCodeString(businessUser.FollowingsList));
            statement.setString(13,businessUser.Bio);
            statement.setString(14,businessUser.PassWord);
            statement.setString(15,generatePostCodeString(businessUser.GroupCodes));
            statement.setString(16,generatePostCodeString(businessUser.DirectMassageCodes));
            statement.setString(17,generatePostCodeString(businessUser.LikedPostCodes));
            statement.setString(18, generatePostCodeString(businessUser.BlockedList));
            statement.setString(19, generatePostCodeString(businessUser.CloseFriendList));
            statement.setString(20, generatePostCodeString(businessUser.RequestList));
            statement.setString(21, generatePostCodeString(businessUser.StoryCodeList));
            statement.setInt(22, businessUser.Blocked);
            statement.setString(23, businessUser.profilepicpath);
            statement.executeUpdate();
            statement.close();
        }
    }

    public<T extends User> void  EditorDeleteUser(T user,boolean delete) throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM usertable WHERE UserName = '" + user.UserName + "';");
        st.executeUpdate();
        st.close();
        if(!delete){
            setUser(user);
        }
    }

    public List<String> getUserNamesList() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs = statement.executeQuery("select UserName from usertable");
        List<String> arr=new ArrayList<>();
        while(rs.next()) {
            arr.add(rs.getString("UserName"));
        }
        statement.close();
        return arr;
    }

    public List<String> StringtoListCode(String input){
        if(input!=null){
            if (input.length()>0){
                String[] Arr=input.split(",");
                List<String> list=new ArrayList<>(Arrays.stream(Arr).toList());

                return list;
            }
        }
        return new ArrayList<String>();
    }

    public boolean isBusiness(String username) throws SQLException {
        boolean result=false;
        String quary="SELECT * FROM usertable WHERE UserName="+"'"+username+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(quary);
        while (resultSet.next()){
            result=resultSet.getBoolean("Kind");
        }
        statement.close();
        return result;
    }

    public String generatePostCodeString(List<String> input){
        if(input!=null){
            if (input.size()>0){
                StringBuilder result= new StringBuilder();

                for (String i:input){
                    result.append(",").append(i);
                }
                if (result.length()>0){
                    result = new StringBuilder(result.substring(1));
                    return result.toString();}
            }
        }
        return  null;
    }

}
