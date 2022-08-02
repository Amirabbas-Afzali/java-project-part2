package com.example.project_part2;

import com.example.project_part2.BuisnessType;
import com.example.project_part2.MAINInformation;
import com.example.project_part2.Main;
import com.example.project_part2.POST.BusinessPost;
import com.example.project_part2.POST.Post;
import com.example.project_part2.USER.User;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShowTimeline {
    static ShowTimeline showTimeline=new ShowTimeline();

    public void start(User user) throws SQLException {
        boolean flag = true, flag1 = true,flag2=true;
        String input,str, strtemp = "Enter a number:\n1.Show Timeline Posts\n2.Show Hashtags\n3.Show business" +
                "\n4.Show stories\n5.View Suggested Users\n6.Back";


        while (flag1){
            System.out.println("");
            System.out.println(strtemp);
            str= Main.scanner.nextLine();
            flag=true;
            while (flag) {
                if (str.equals("6")) {
                    flag = false;flag1=false;
                }
                else if (str.equals("1")) {
                    TimeLineView(user);
                    flag = false;
                }
                else if (str.equals("2")) {
                    ViewPostsWithHashtag(user);
                    flag = false;
                }
                else if (str.equals("3")) {
                    ViewPostsWithBusType(user);
                    flag = false;
                }
                else if (str.equals("4")) {
                    Map<String,User> temp=new HashMap<>();
                    for (User user1:user.FollowingMap.values()){
                        if(user1.MyStories.size()>0){
                            temp.put(user1.UserName,user1);
                        }
                    }
                    flag2=true;
                    while (flag2) {
                        int c = 1;
                        if(temp.size()>0){
                            System.out.println("Enter a username to see their stories (Enter  'Back'  to end) :");
                            for (User user1 : temp.values()) {
                                System.out.println(c + "." + user1.UserName);
                                c++;
                            }
                            input = Main.scanner.nextLine();
                            if (temp.containsKey(input)) {
                                temp.get(input).ShowStory(user);
                                temp.remove(input);
                            } else if (input.equals("Back")) {
                                flag2 = false;
                            } else {
                                System.out.println("Invalid command!");
                            }
                        }
                        else {System.out.println("Empty !");}
                        flag2=false;
                    }
                    flag = false;

                }
                else  if (str.equals("5")){
                    // System.out.println("this is 5");
                    // TODO: 7/28/2022 suggest user
                    System.out.println("How many?\nBack");
                    String input2=Main.scanner.nextLine().trim();
                    try {
                        List<String> users=getSuggestedUsers(user,Integer.parseInt(input2));
                        while (!input2.equals("Back")){
                            System.out.println("User names are : "+users.toString()+"\nEnter" +
                                    " user name to view or Back");
                            input2=Main.scanner.nextLine().trim();
                            try {
                                if (!input2.equals("Back")){
                                    User.ShowAuser(user,MAINInformation.mainInformation.users.get(input2));
                                }
                            }
                            catch (Exception e){
                                System.out.println("invalid command");
                            }

                        }
                    }
                    catch (Exception e){
                        System.out.println(
                                "invalid command"
                        );
                    }

                }
                else {
                    System.out.println("invalid command!");
                    flag=false;
                }
                flag=false;
            }
        }
    }
    public List<Integer> TimeLinePostCodesList(User user){
        List<Integer> postcodes=new ArrayList<>();
        List<User> Following=user.getFollowing();
        for (User i:Following){
            try {
                for (String j: i.PostCodesList){
                    postcodes.add(Integer.parseInt(j));
                }
            }
            catch (Exception e){

                System.out.println(i.UserName+"cjdan cnjsdc n ");
            }

        }
        Collections.sort(postcodes);
        //for (Integer i : postcodes){
        //    result.add(i.toString());
        //}
        return postcodes;
    }
    public List<Post> TimeLinePosts(int n,User user){
        List<Integer> reference;
        reference=this.TimeLinePostCodesList(user);
        List<Post> result=new ArrayList<>();
        if (reference.size()<n){
            n=reference.size();
        }
        for (int i=0;i<n;i++){
            result.add(MAINInformation.mainInformation.posts.get(reference.get((reference.size()-i-1)).toString()));
        }
        return result;
    }
    public boolean IsHashtagIn(Post input,String Hashtag){
        // List<Integer> Start=new ArrayList<>();
        if (input.Caption.toLowerCase(Locale.ROOT).contains(Hashtag.toLowerCase(Locale.ROOT))){
            // System.out.println("This Has it"+input.PostCode+Hashtag);
            return true;
        }
        if (input.Kind){
            BusinessPost i=(BusinessPost)input;
            // if (i.description.toLowerCase(Locale.ROOT).contains(Hashtag)){
            // System.out.println("This Has it"+input.PostCode+Hashtag);
            // }

            return i.description.toLowerCase(Locale.ROOT).contains(Hashtag.toLowerCase(Locale.ROOT));
        }
        return false;
    }
    public List<Post> getPostWithTopic(BuisnessType buisnessType,Integer length){
        List<Post> result=new ArrayList<>();
        List<Integer> Data=getPostCodesInteger(1);
        BusinessPost businessPost;
        for (int i=Data.size()-1;i>-1;i--){
            businessPost=(BusinessPost) MAINInformation.mainInformation.posts.get(Data.get(i).toString());
            if (businessPost.buisnessType==buisnessType){
                result.add(businessPost);
                length--;
            }
            if (length==0) break;
        }
        return result;
    }
    public List<Integer > getPostCodesInteger(Integer Bus1Pv2Mix3){
        List<Integer> result=new ArrayList<>();
        for (Post i: MAINInformation.mainInformation.posts.values()){
            if ( (Bus1Pv2Mix3==1&&i.Kind)||(Bus1Pv2Mix3==3&&i.Kind)){
                result.add(Integer.parseInt(i.PostCode));
            }
            if ( (Bus1Pv2Mix3==2&&(!i.Kind))||(Bus1Pv2Mix3==3&&!i.Kind)){
                result.add(Integer.parseInt(i.PostCode));
            }
        }
        Collections.sort(result);
        // TODO: 7/27/2022 check Done
        // System.out.println(result.toString());
        return result;

    }
    public List<Integer> PostsWithHashtag(String Hashtag,Integer number){
        List<Integer> result=new ArrayList<>();
        List<Integer> Data=getPostCodesInteger(3);
        // System.out.println(Data.toString());
        for (int i=Data.size()-1;i>-1;i--){
            if (IsHashtagIn(MAINInformation.mainInformation.posts.get(Data.get(i).toString()),Hashtag)){
                result.add(Integer.parseInt(MAINInformation.mainInformation.posts.get(Data.get(i).toString()).PostCode));
            }
        }
        return result;
    }

    // TODO: 7/26/2022 full it
    public List<String> getSuggestedUsers(User user,Integer HowMany){
        return followingInCommonInFollowing(HowMany,user).stream().toList();
    }

    public Set<String> followingInCommonInFollowing(Integer howMany,User user){
        Set<String> result=new HashSet<>();
        List<String> AllIn=new ArrayList<>();
        for (String i:user.FollowingsList){
            AllIn = Stream.concat(AllIn.stream(), MAINInformation.mainInformation.users.get(i).FollowingsList.stream()).toList();
        }
        List<String> changer=new ArrayList<>(AllIn);
        try {
            for (int i = changer.size(); i > 0; i--) {
                if (user.FollowingsList.contains(changer.get(i - 1)) || changer.get(i - 1).equals(user.UserName)) {
                    changer.remove(i - 1);
                }
            }
            String mostHappened = "";
            int sum = 0;
            AllIn = new ArrayList<>(changer);
            while (sum < howMany && AllIn.size() > 0) {
                mostHappened = mostCommon(AllIn);
                result.add(mostHappened);
                String finalMostHappened = mostHappened;
                List<String> finalAllIn = AllIn;
                int[] indexes = IntStream.range(0, AllIn.size()).filter(i -> finalAllIn.get(i).equals(finalMostHappened)).toArray();
                changer = new ArrayList<>(AllIn);
                // System.out.println(changer.toString() + "first");
                for (int i = indexes.length; i > 0; i--) {
                    try {
                        // System.out.println(indexes[i - 1] + changer.toString());
                        changer.remove(indexes[i - 1]);
                    } catch (Exception e) {
                        // e.printStackTrace();
                        System.out.println("line 208");
                        // System.out.println(indexes[i - 1] + "   " + AllIn.size() + AllIn.toString());
                    }
                }
                sum++;
                AllIn = new ArrayList<>(changer);
            }
            //  System.out.println(result.toString() + "result");
        }
        catch (Exception e){

        }
        return result;
    }
    public static <T> T mostCommon(List<T> list) {
        Map<T, Integer> map = new HashMap<>();

        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }

        Map.Entry<T, Integer> max = null;

        for (Map.Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }

        return max.getKey();
    }
    void TimeLineView(User user){
        String input2="";
        while (!input2.equals("Back")){
            System.out.println("How many posts you  want to view?\nBack");
            input2=Main.scanner.nextLine().trim();
            try {
                List<Post> Posts=showTimeline.TimeLinePosts(Integer.parseInt(input2),user);
                for (Post i:Posts){
                    i.preShow();
                }
                String input="";
                while (!input.equals("Back")) {
                    System.out.println("Enter post code to view\nBack");
                    input=Main.scanner.nextLine().trim();
                    try {
                        if(MAINInformation.mainInformation.posts.get(input).Kind) {
                            ((BusinessPost)MAINInformation.mainInformation.posts.get(input)) .ShowBusPost(user);
                        }
                        else {
                            MAINInformation.mainInformation.posts.get(input).ShowPost(user);
                        }
                    }
                    catch (Exception e){
                        if (!input.equals("Back"))
                            System.out.println("Invalid command");
                    }
                }
            }
            catch (Exception e){
                System.out.println("Invalid command");
            }
        }
    }
    void ViewPostsWithHashtag(User Viewer){
        String input="";
        while (!input.equals("Back")){
            System.out.println("Enter Hashtag with #\nBack");
            input=Main.scanner.nextLine().trim();
            if (!input.equals("Back")){
                System.out.println("How many?");
                String input2=Main.scanner.nextLine().trim();
                try {
                    List<Integer>Postcodes=PostsWithHashtag(input,Integer.parseInt(input2));
                    for ( Integer i:Postcodes) {
                        MAINInformation.mainInformation.posts.get(i.toString()).preShow();
                    }
                    System.out.println("Enter Post code to view or Back to ignore");
                    input2=Main.scanner.nextLine().trim();
                    if (!input2.equals("Back")){
                        try {
                            if(MAINInformation.mainInformation.posts.get(input2).Kind) {
                                ((BusinessPost)MAINInformation.mainInformation.posts.get(input2)) .ShowBusPost(Viewer);
                            }
                            else {
                                MAINInformation.mainInformation.posts.get(input2).ShowPost(Viewer);
                            }
                        }
                        catch (Exception e){
                            System.out.println("invalid command");
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("invalid command");
                }
            }
        }
    }
    public void ViewPostsWithBusType(User user){
        String input="";
        while (!input.equals("Back")){
            //Tech,Cloth,AD,Artist,Sport
            System.out.println("Business Type\n1:Tech\n2:cloth\n3:AD\n4:Artist\n5:Sport");
            input=Main.scanner.nextLine().trim();
            System.out.println("How many?");
            String numberStr=Main.scanner.nextLine().trim();

            if (!input.equals("Back")){
                try {
                    String input3="";
                    while (!input3.equals("Back"))  {
                        List<Post> posts=new ArrayList<>();
                        if (input.equals("1")){
                            posts=getPostWithTopic(BuisnessType.Tech,Integer.parseInt(numberStr));
                        }
                        if (input.equals("2")){
                            posts=getPostWithTopic(BuisnessType.Cloth,Integer.parseInt(numberStr));
                        }
                        if (input.equals("3")){
                            posts=getPostWithTopic(BuisnessType.AD,Integer.parseInt(numberStr));
                        }
                        if (input.equals("4")){
                            posts=getPostWithTopic(BuisnessType.Artist,Integer.parseInt(numberStr));
                        }
                        if (input.equals("5")){
                            posts=getPostWithTopic(BuisnessType.Sport,Integer.parseInt(numberStr));
                        }
                        for (Post i:posts){
                            i.preShow();
                        }
                        System.out.println("Enter post number to View or Back");
                        input3= Main.scanner.nextLine().trim();
                        try {
                            MAINInformation.mainInformation.posts.get(input3).ShowPost(user);
                        }
                        catch (Exception e){
                            if (!input3.equals("Back")){
                                System.out.println("invalid command");
                            }
                        }
                    }
                }
                catch (Exception e){
                    System.out.println("invalid command");
                }
            }
        }
    }
}