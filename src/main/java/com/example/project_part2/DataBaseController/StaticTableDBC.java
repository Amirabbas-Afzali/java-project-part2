package com.example.project_part2.DataBaseController;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.*;

public class StaticTableDBC {
    StaticTableDBC() throws ClassNotFoundException , SQLException  {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");
        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    Connection connection;
    public static StaticTableDBC staticTableDBC;
    static {
        try {
            staticTableDBC = new StaticTableDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Integer getCodeNumber(String WhatStatic) throws SQLException {
        String quary="SELECT * FROM staticstable";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(quary);
        Integer result=0;
        while (resultSet.next()){
            if (WhatStatic.contains("Post")){
                result= resultSet.getInt("PostNumber");
            }
            if (WhatStatic.contains("Massage")&&!WhatStatic.contains("Direct")){
                result= resultSet.getInt("MassageNumber");
            }
            if (WhatStatic.contains("DirectMassage")){
                result= resultSet.getInt("DirectMassageNumber");
            }
            if (WhatStatic.contains("User")){
                result= resultSet.getInt("UserNumber");
            }
            if (WhatStatic.contains("Story")){
                result= resultSet.getInt("StoryNumber");
            }
            if (WhatStatic.contains("Like")){
                result= resultSet.getInt("LikeNumber");
            }
            statement.close();
            return result;
        }
        return -1;
    }

    public void SetCodeNumber(String WhatStatic, Integer value1) throws SQLException {
        String field="";
        if (WhatStatic.contains("Post")){
            field="PostNumber";
        }
        if (WhatStatic.contains("Massage")&&!WhatStatic.contains("Direct")){
            field="MassageNumber";
        }
        if (WhatStatic.contains("DirectMassage")){
            field="DirectMassageNumber";
        }
        if (WhatStatic.contains("User")){
            field="UserNumber";
        }
        if (WhatStatic.contains("Story")){
            field="StoryNumber";
        }
        if (WhatStatic.contains("Like")){
            field="LikeNumber";
        }
        String query="UPDATE staticstable SET  "+field+" ="+value1.toString()+" WHERE First=1;";
        PreparedStatement statement= connection.prepareStatement("" +
                query);

        statement.executeUpdate();
        statement.close();

    }
}