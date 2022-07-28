package com.example.project_part2;

import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;

public class CreatenewStory {
    static CreatenewStory createnewStory = new CreatenewStory();

    User user;

    public void start() throws SQLException {
        boolean flag = true, flag2 = true;
        String ww,Close,text, inputcode, strtemp = "Enter a number:\n1.My stories\n2.Create new story\n3.back",str;

        while (flag) {
            System.out.println(strtemp);
            str= Main.scanner.nextLine();
            if (str.equals("3")) {
                flag = false;
            }
            else if (str.equals("2")){
                System.out.println("Enter the text of the story :");
                text = Main.scanner.nextLine();
                System.out.println("1.Your Story            2.Close friend");
                Close = Main.scanner.nextLine();
                if (text.length() > 0&&(Close.equals("1")||Close.equals("2"))) {
                    String str1=createstorycode();
                    Story story = new Story(str1, text ,  user, new Time((new Date()).getTime()),isClose(Close));
                    StoryTableDBC.storyTableDBC.setStory(story);
                    MAINInformation.mainInformation.stories.put(str1,story);
                    user.MyStories.add(story);
                    user.StoryCodeList.add(story.StoryCode);
                    UserTableDBC.userTableDBC.EditorDeleteUser(user,false);
                    System.out.println("Created successful");
                }

            }
            else if(str.equals("1")) {
                if(user.StoryCodeList.size()>0){
                    for (Story story : user.MyStories) {
                        System.out.println("Code : " + story.StoryCode + "   ,    Text : " + story.text);
                    }
                    System.out.println("");
                    System.out.println("Enter a Code to show more details : ");
                    inputcode = Main.scanner.nextLine();
                    if (user.StoryCodeList.contains(inputcode)) {
                        System.out.println("Text : " + MAINInformation.mainInformation.stories.get(inputcode).text
                                + "\nViewers : " + MAINInformation.mainInformation.stories.get(inputcode).viewersnameList
                                + "\nLikers : " + MAINInformation.mainInformation.stories.get(inputcode).likersnameList
                                + "\nTime left to delete this story : " +(24- new Date().getHours()+MAINInformation.mainInformation.stories.get(inputcode).date.getHours()) + " hours");
                        if(MAINInformation.mainInformation.stories.get(inputcode).IsClose){   System.out.println("(Close friends)");}
                        System.out.println("");
                        flag2 = true;
                        while (flag2) {
                            System.out.println("1.Delete\n2.Back");
                            ww = Main.scanner.nextLine();
                            if (ww.equals("1")) {
                                StoryTableDBC.storyTableDBC.DeleteStory(MAINInformation.mainInformation.stories.get(inputcode),true);
                                System.out.println("Deleted !");
                                flag2 = false;
                            } else if (ww.equals("2")) {
                                flag2 = false;
                            } else {
                                System.out.println("Invalid command!");
                            }
                        }
                    } else {
                        System.out.println("Story not found !");
                    }
                }
                else{System.out.println("Empty !");}


            }
            else {System.out.println("Invalid command!");}
        }
    }
    public String createstorycode() throws SQLException {
        String result=String.valueOf(Story.StoryCodeStatic);
        Story.StoryCodeStatic++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("Story",Story.StoryCodeStatic);
        return result;
    }
    public boolean isClose(String strr){
        if(strr.equals("1")){return  false;}
        return true;
    }
}

