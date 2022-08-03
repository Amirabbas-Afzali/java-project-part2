package com.example.project_part2;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.Date;

public class LikeHandle {
    public static int LikeNumber=0;
    public LikeHandle(){}
    LikeHandle(String likerUserName,boolean IsPost,boolean IsView,String postCode) throws SQLException {
        likeCode=CreateLikeHandleCode().toString();
        isPost=IsPost;
        if (isPost){
            PostCode=postCode;
        }
        else {
            PostCode="-1";
        }
        if (IsView){
            PostCode="-2";
        }
        LikerUserName=likerUserName;
        date=new Date();
        MAINInformation.mainInformation.likeHandleMap.put(this.likeCode,this);
        LikeHandleTable.likeHandleTable.setNewLikeHandle(this);
    }
    public String LikerUserName;
    public boolean isPost;
    public String PostCode;
    public Date date;
    public String likeCode;
    Integer CreateLikeHandleCode() throws SQLException {
        LikeNumber++;
        StaticTableDBC.staticTableDBC.SetCodeNumber("Like",LikeNumber);
        return LikeNumber-1;
    }
    public static String NewLikeHandles(String Liker,String PostCode,boolean View) throws SQLException {
        if (PostCode.equals("-1")){
            return (new LikeHandle(Liker,false,false,PostCode)).likeCode;
        }
        else {
            if(!View ){
                LikeHandle likeHandle=new LikeHandle(Liker,true,false,PostCode);
                MAINInformation.mainInformation.users.get(Liker).addLikedPostCode(likeHandle.likeCode,true);
                return likeHandle.likeCode;
            }
            return (new LikeHandle(Liker,false,true,PostCode)).likeCode;
        }
    }
    public Integer ShowLikeHandle(boolean time,Date Date,boolean view,boolean JustCount){
        if (time){
            if (date.compareTo(Date)>=0){
                if ((!this.PostCode.equals("-2"))&&!view){
                    if (!JustCount)
                        System.out.println("User :"+LikerUserName+" Liked at "+DateFormat.dateFormat.reportdate(date));
                    return 1;
                }
                else {
                    if (this.PostCode.equals("-2")&&view){
                        if (!JustCount)
                            System.out.println("User :"+LikerUserName+" Viewed at "+DateFormat.dateFormat.reportdate(date));
                        return 1;
                    }
                }
            }
        }
        else {
            if ((!this.PostCode.equals("-2"))&&!view){
                if (!JustCount)
                    System.out.println("User :"+LikerUserName+" Liked at "+DateFormat.dateFormat.reportdate(date));
                return 1;
            }
            else {
                if ((this.PostCode.equals("-2"))&&view){
                    if (!JustCount)
                        System.out.println("User :"+LikerUserName+" Viewed at "+DateFormat.dateFormat.reportdate(date));
                    return 1;
                }
            }
        }
        return 0;
    }
    public boolean ShowLikeHandle2(boolean time,Date Start,Date End,boolean view,boolean JustCount){
        if (time){
            if (date.compareTo(Start)>=0&&date.compareTo(End)<=0){
                if ((!this.PostCode.equals("-2"))&&!view){
                    if (!JustCount)
                        System.out.println("User :"+LikerUserName+" Liked at "+DateFormat.dateFormat.reportdate(date));
                    return true;
                }
                else {
                    if (this.PostCode.equals("-2")&&view){
                        if (!JustCount)
                            System.out.println("User :"+LikerUserName+" Viewed at "+DateFormat.dateFormat.reportdate(date));
                        return true;
                    }
                }
            }
        }
        else {
            if ((!this.PostCode.equals("-2"))&&!view){
                if (!JustCount)
                    System.out.println("User :"+LikerUserName+" Liked at "+DateFormat.dateFormat.reportdate(date));
                return true;
            }
            else {
                if ((this.PostCode.equals("-2"))&&view){
                    if (!JustCount)
                        System.out.println("User :"+LikerUserName+" Viewed at "+DateFormat.dateFormat.reportdate(date));
                    return true;
                }
            }
        }
        return false;
    }
}