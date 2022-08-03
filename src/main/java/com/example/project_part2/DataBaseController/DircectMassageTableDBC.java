package com.example.project_part2.DataBaseController;
import com.example.project_part2.DirectMassage;
import com.example.project_part2.Group;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;


import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DircectMassageTableDBC {
    DircectMassageTableDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");

        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    Connection connection;
    public static DircectMassageTableDBC dircectMassageTableDBC;
    static {
        try {
            dircectMassageTableDBC = new DircectMassageTableDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends DirectMassage> T getDirectMassage(String MassageCode) throws SQLException {
        String query="SELECT * FROM directmassagetable WHERE DirectMassageCode="+"'"+MassageCode+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(query);
        T result =null;
        while (resultSet.next()){

            if(resultSet.getBoolean("isGroup")){
                Group group=new Group();
                group.Code=resultSet.getString("DirectMassageCode");
                group.isGroup=resultSet.getBoolean("isGroup");
                group.MassageCodes=getPostCode(resultSet.getString("MassageCodes"));
                group.UserNamesInChat=getPostCode(resultSet.getString("UserNamesInChat"));
                group.Owener=resultSet.getString("GrOwner");
                group.bio=resultSet.getString("bio");
                group.Admins=getPostCode(resultSet.getString("Admins"));
                group.IsPrivate=resultSet.getBoolean("IsPrivate");
                group.GroupID=resultSet.getString("GroupID");
                group.ProfilePath=resultSet.getString("ProfilePath");
                result=(T) group;
            }
            else {
                DirectMassage group=new DirectMassage();
                group.Code=resultSet.getString("DirectMassageCode");
                group.isGroup=resultSet.getBoolean("isGroup");
                group.MassageCodes=getPostCode(resultSet.getString("MassageCodes"));
                group.UserNamesInChat=getPostCode(resultSet.getString("UserNamesInChat"));
                result=(T) group;
            }
        }
        statement.close();
        return result;
    }

    public <T extends  DirectMassage> void setDirectMassage(T directinput) throws SQLException {
        if(directinput.isGroup){
            Group group=(Group) directinput;
            PreparedStatement statement= connection.prepareStatement("" +
                    "INSERT INTO directmassagetable (DirectMassageCode,isGroup,MassageCodes,UserNamesInChat," +
                    "GrOwner,bio,Admins,GroupID,IsPrivate,ProfilePath) VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1,directinput.Code);
            statement.setBoolean(2,directinput.isGroup);
            statement.setString(3,generatePostCodeString(directinput.MassageCodes));
            statement.setString(4,generatePostCodeString(directinput.UserNamesInChat));
            statement.setString(5,((Group) directinput).Owener);
            statement.setString(6,((Group) directinput).bio);
            statement.setString(7,generatePostCodeString(group.Admins));
            statement.setString(8,group.GroupID);
            statement.setBoolean(9,group.IsPrivate);
            statement.setString(10,((Group) directinput).ProfilePath);
            statement.executeUpdate();
            statement.close();
        }
        else {
            DirectMassage group=(DirectMassage) directinput;
            PreparedStatement statement= connection.prepareStatement("" +
                    "INSERT INTO directmassagetable (DirectMassageCode,isGroup,MassageCodes,UserNamesInChat)" +
                    " VALUES (?,?,?,?)");
            statement.setString(1,directinput.Code);
            statement.setBoolean(2,directinput.isGroup);
            statement.setString(3,generatePostCodeString(directinput.MassageCodes));
            statement.setString(4,generatePostCodeString(directinput.UserNamesInChat));
            statement.executeUpdate();
            statement.close();
        }
    }

    public <T extends  DirectMassage> void EditorDeleteDirect(T directinput,boolean delete)throws SQLException{
        PreparedStatement st = connection.prepareStatement("DELETE FROM directmassagetable WHERE DirectMassageCode = '" + directinput.Code + "';");
        st.executeUpdate();
        st.close();
        if(!delete){
            setDirectMassage(directinput);
        }
    }


    static List<String> getPostCode(String input){
        if(input!=null){
            if (input.length()>0){
                String[] Arr=input.split(",");
                List<String> list=new ArrayList<>(Arrays.stream(Arr).toList());

                return list;
            }
        }
        return new ArrayList<String>();
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

    public List<String> getDirectMassageCodesList() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs = statement.executeQuery("select DirectMassageCode from directmassagetable");
        List<String> arr=new ArrayList<>();
        while(rs.next()) {
            arr.add(rs.getString("DirectMassageCode"));
        }
        statement.close();
        return arr;
    }


}