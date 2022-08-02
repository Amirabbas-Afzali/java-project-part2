package com.example.project_part2.DataBaseController;
import com.example.project_part2.BuisnessType;
import com.example.project_part2.POST.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PostTableDBC {
    PostTableDBC() throws ClassNotFoundException,SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");

        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    Connection connection;
    public static PostTableDBC postTableDBC;
    static {
        try {
            postTableDBC = new PostTableDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public <T extends Post>T getPost(String postCode) throws SQLException {
        String quary="SELECT * FROM posttable WHERE PostCode="+"'"+postCode+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(quary);
        T result = null;
        while (resultSet.next()){
            boolean isBus=resultSet.getBoolean("Kind");
            if (isBus){
                BusinessPost businessPost=new  BusinessPost();
                businessPost.PostCode=resultSet.getString("PostCode");
                businessPost.Caption=resultSet.getString("Caption");
                businessPost.date=resultSet.getDate("date");
                businessPost.Kind=isBus;
                businessPost.RepostersList=getPostCode(resultSet.getString("RepostersList"));
                businessPost.LikedList=getPostCode(resultSet.getString("LikedList"));
                businessPost.CommentsCodesList=getPostCode(resultSet.getString("CommentsCodesList"));
                businessPost.ViewersUserNames=getPostCode(resultSet.getString("BusViewersUserNames"));
                businessPost.PosterName=resultSet.getString("PosterUserName");
                businessPost.description=resultSet.getString("BusDescription");
                businessPost.buisnessType=setBuisnessType(resultSet.getInt("BusTypeINT"));
                businessPost.photopath=resultSet.getString("picpath");
                result=(T)businessPost;
            }
            else {
                OrdinaryPost businessPost=new  OrdinaryPost();
                businessPost.PostCode=resultSet.getString("PostCode");
                businessPost.Caption=resultSet.getString("Caption");
                businessPost.date=resultSet.getDate("date");
                businessPost.Kind=isBus;
                businessPost.RepostersList=getPostCode(resultSet.getString("RepostersList"));
                businessPost.LikedList=getPostCode(resultSet.getString("LikedList"));
                businessPost.CommentsCodesList=getPostCode(resultSet.getString("CommentsCodesList"));
                businessPost.PosterName=resultSet.getString("PosterUserName");
                businessPost.isprivate=resultSet.getBoolean("Ordisprivate");
                //Add User
                businessPost.photopath=resultSet.getString("picpath");
                businessPost.userposter=UserTableDBC.userTableDBC.getUser(businessPost.PosterName);
                result=(T)businessPost;
            }
        }
        statement.close();
        return result;
    }

    public<T extends Post> void setPost(T postinput) throws SQLException {
        java.sql.Date date=new java.sql.Date(postinput.date.getTime());
        if (postinput.Kind){
            BusinessPost post=(BusinessPost) postinput;
            PreparedStatement statement= connection.prepareStatement("" +
                    "INSERT INTO posttable (PostCode,Caption,Kind,date," +
                    "RepostersList,LikedList,CommentsCodesList,Ordisprivate," +
                    "BusViewersUserNames,PosterUserName,BusTypeINT," +
                    "BusDescription,picpath) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            statement.setNString(1,post.PostCode);
            statement.setNString(2,post.Caption);
            statement.setBoolean(3,post.Kind);
            statement.setDate(4,date);
            statement.setNString(5,generatePostCodeString(post.RepostersList));
            statement.setNString(6,generatePostCodeString(post.LikedList));
            statement.setNString(7,generatePostCodeString(post.CommentsCodesList));
            statement.setBoolean(8,false);
            statement.setNString(9,generatePostCodeString(post.ViewersUserNames));
            statement.setNString(10,post.PosterName);
            statement.setInt(11,post.buisnessTypeINT);
            statement.setString(12,post.description);
            statement.setString(13,post.photopath);
            statement.executeUpdate();
            statement.close();
        }
        else {
            OrdinaryPost post=(OrdinaryPost) postinput;
            PreparedStatement statement= connection.prepareStatement("" +
                    "INSERT INTO posttable (PostCode,Caption,Kind,date,RepostersList," +
                    "LikedList,CommentsCodesList,Ordisprivate,PosterUserName,picpath) VALUES (?,?,?,?,?,?,?,?,?,?)");
            statement.setNString(1,post.PostCode);
            statement.setNString(2,post.Caption);
            statement.setBoolean(3,post.Kind);
            statement.setDate(4,date);
            statement.setNString(5,generatePostCodeString(post.RepostersList));
            statement.setNString(6,generatePostCodeString(post.LikedList));
            statement.setNString(7,generatePostCodeString(post.CommentsCodesList));
            statement.setBoolean(8,false);
            statement.setNString(9,post.PosterName);
            statement.setString(10,post.photopath);
            statement.executeUpdate();
            statement.close();
        }

    }

    public List<String> getPostCodesList() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs = statement.executeQuery("select PostCode from posttable");
        List<String> arr=new ArrayList<>();
        while(rs.next()) {
            arr.add(rs.getString("PostCode"));
        }
        statement.close();
        return arr;
    }

    public <T extends Post> void EditorDeletePost(T postinput,boolean delete)throws SQLException {
        PreparedStatement st = connection.prepareStatement("DELETE FROM posttable WHERE PostCode = '" + postinput.PostCode + "';");
        st.executeUpdate();
        st.close();
        if(!delete){
            setPost(postinput);
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

    public BuisnessType setBuisnessType(Integer type){
        BuisnessType result=BuisnessType.AD;
        if(type.equals(1)){result=BuisnessType.Tech;}
        else if(type.equals(2)){result=BuisnessType.Cloth;}
        else  if(type.equals(3)){result=BuisnessType.AD;}
        else  if(type.equals(4)){result=BuisnessType.Artist;}
        else  if(type.equals(5)){result=BuisnessType.Sport;}
        return result;
    }

    public boolean isBusiness(String postCode) throws SQLException {
        boolean result=false;
        String quary="SELECT * FROM posttable WHERE PostCode="+"'"+postCode+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(quary);
        while (resultSet.next()){
            result=resultSet.getBoolean("Kind");
        }
        statement.close();
        return result;
    }

    public <T extends Post> HashMap<String,T> getPosts(List<String> postCodes) throws SQLException {
        HashMap<String,T> result=new HashMap<>();
        for (String i:postCodes){
            result.put(i,getPost(i));
        }
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