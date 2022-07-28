package com.example.project_part2.DataBaseController;
import com.example.project_part2.LikeHandle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikeHandleTable {
    LikeHandleTable() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");

        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    Connection connection;
    public static LikeHandleTable likeHandleTable;
    static {
        try {
            likeHandleTable = new LikeHandleTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public LikeHandle getLike(String LikeCode) throws SQLException {
        String query="SELECT * FROM likehandletable WHERE LikeCode="+"'"+LikeCode+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(query);
        LikeHandle likeHandle=new LikeHandle();
        while (resultSet.next()){
            likeHandle.likeCode=resultSet.getString("LikeCode");
            likeHandle.LikerUserName=resultSet.getString("LikerUserName");
            likeHandle.isPost=resultSet.getBoolean("isPost");
            likeHandle.date=resultSet.getDate("Date");
            likeHandle.PostCode=resultSet.getString("PostCode");
        }
        statement.close();
        return likeHandle;
    }
    public void setNewLikeHandle(LikeHandle likeHandle) throws SQLException {
        java.sql.Date date=new java.sql.Date(likeHandle.date.getTime());
        PreparedStatement statement= connection.prepareStatement("" +
                "INSERT INTO likehandletable (LikeCode,LikerUserName,isPost,PostCode,Date) VALUES (?,?,?,?,?)");
        statement.setString(1,likeHandle.likeCode);
        statement.setString(2, likeHandle.LikerUserName);
        statement.setBoolean(3, likeHandle.isPost);
        statement.setDate(5,date);
        statement.setString(4,likeHandle.PostCode);
        statement.executeUpdate();
        statement.close();
    }
    public List<String> getLikeCodesList() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs = statement.executeQuery("select LikeCode from likehandletable");
        List<String> arr=new ArrayList<>();
        while(rs.next()) {
            arr.add(rs.getString("LikeCode"));
        }
        statement.close();
        return arr;
    }

}