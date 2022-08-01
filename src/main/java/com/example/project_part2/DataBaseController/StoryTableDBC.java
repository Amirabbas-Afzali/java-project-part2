package com.example.project_part2.DataBaseController;

import com.example.project_part2.MAINInformation;
import com.example.project_part2.Story;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoryTableDBC {
    StoryTableDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {

            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/project1","root","Amirafzali1382");

        }
        catch (Exception e){
            System.out.println("Error in Connection !");
        }
    }
    Connection connection;
    public static StoryTableDBC storyTableDBC ;
    static {
        try {
            storyTableDBC = new StoryTableDBC();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Story getStory(String postCode) throws SQLException {
        String quary="SELECT * FROM storytable WHERE Code="+"'"+postCode+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery(quary);
        Story businessPost=null;
        while (resultSet.next()){

            businessPost=new  Story();
            businessPost.StoryCode=resultSet.getString("Code");
            businessPost.picturepath=resultSet.getString("picpath");
            businessPost.text=resultSet.getString("text");
            businessPost.date=resultSet.getTime("date");
            businessPost.viewersnameList=getPostCode(resultSet.getString("ViewersList"));
            businessPost.likersnameList=getPostCode(resultSet.getString("LikerList"));
            businessPost.usernamestory=resultSet.getString("Username");
            businessPost.Private=resultSet.getBoolean("Isprivate");
            businessPost.IsClose=resultSet.getBoolean("IsClose");

        }
        statement.close();
        return businessPost;
    }

    public void setStory(Story post) throws SQLException {
        PreparedStatement statement= connection.prepareStatement("" +
                "INSERT INTO storytable (Code,Username,date," +
                "Isprivate,text,ViewersList,LikerList,IsClose,picpath) VALUES (?,?,?,?,?,?,?,?,?)");
        statement.setString(1,post.StoryCode);
        statement.setString(2,post.usernamestory);
        statement.setTime(3, post.date);
        statement.setBoolean(4,post.Private);
        statement.setString(5,post.text);
        statement.setString(6,generatePostCodeString(post.viewersnameList));
        statement.setString(7,generatePostCodeString(post.likersnameList));
        statement.setBoolean(8,post.IsClose);
        statement.setString(9,post.picturepath);

        statement.executeUpdate();
        statement.close();
    }

    public List<String> getStoryCodesList() throws SQLException {
        Statement statement=connection.createStatement();
        ResultSet rs = statement.executeQuery("select Code from storytable");
        List<String> arr=new ArrayList<>();
        while(rs.next()) {
            arr.add(rs.getString("Code"));
        }
        statement.close();
        return arr;
    }

    public  void DeleteStory(Story story,boolean delete )throws SQLException {
        if(delete) {
            MAINInformation.mainInformation.stories.remove(story);
            story.user.StoryCodeList.remove(story.StoryCode);
            story.user.MyStories.remove(story);
            UserTableDBC.userTableDBC.EditorDeleteUser(story.user, false);
        }
        PreparedStatement st = connection.prepareStatement("DELETE FROM storytable WHERE Code = '" + story.StoryCode + "';");
        st.executeUpdate();
        st.close();
        if(!delete){
            setStory(story);
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

}