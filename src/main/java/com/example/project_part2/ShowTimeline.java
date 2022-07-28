package com.example.project_part2;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShowTimeline {
    static ShowTimeline showTimeline=new ShowTimeline();

    public void start(User user) throws SQLException {
        boolean flag = true, flag1 = true,flag2=true;
        String input,str, strtemp = "Enter a number:\n1.Show Timeline Posts\n2.Show Hashtags\n3.Show business" +
                "\n4.Show stories\n5.Back";


        while (flag1){
            System.out.println("");
            System.out.println(strtemp);
            str= Main.scanner.nextLine();
            flag=true;
            while (flag) {
                if (str.equals("5")) {
                    flag = false;flag1=false;
                }
                else if (str.equals("1")) {
                    List<Post> Posts=showTimeline.TimeLinePosts(5,user);
                    for (Post i:Posts){
                        i.preShow();
                    }

                    flag = false;
                }
                else if (str.equals("2")) {


                    flag = false;
                }
                else if (str.equals("3")) {


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
                    }
                    flag = false;
                }
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
        if (input.Caption.contains(Hashtag)){
            return true;
        }
        if (input.Kind){
            BusinessPost i=(BusinessPost)input;
            return i.description.contains(Hashtag);
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
        return result;

    }
    public List<Integer> PostsWithHashtag(String Hashtag,Integer number){
        List<Integer> result=new ArrayList<>();
        List<Integer> Data=getPostCodesInteger(3);
        for (int i=Data.size()-1;i>-1;i--){
            if (IsHashtagIn(MAINInformation.mainInformation.posts.get(Data.get(i).toString()),Hashtag)){
                result.add(i);
            }
        }
        return result;
    }

    // TODO: 7/26/2022 full it
    public List<String> getSuggestedUsers(){
        return new ArrayList<>();
    }

    public Set<String> followingInCommonInFollowing(Integer howMany,User user){
        Set<String> result=new HashSet<>();
        List<String> AllIn=new ArrayList<>();
        for (String i:user.FollowingsList){
            AllIn = Stream.concat(AllIn.stream(), MAINInformation.mainInformation.users.get(i).FollowingsList.stream()).toList();
        }
        String mostHappened="";
        int sum=0;
        while (sum<howMany&&AllIn.size()>0){
            mostHappened=mostCommon(AllIn);
            result.add(mostHappened);
            String finalMostHappened = mostHappened;
            List<String> finalAllIn = AllIn;
            int[] indexes = IntStream.range(0, AllIn.size()).filter(i -> finalAllIn.get(i).equals(finalMostHappened)).toArray();
            for (int i=indexes.length;i>-1;i--){
                AllIn.remove(indexes[i-1]);
            }
            sum++;
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

}