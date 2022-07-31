package com.example.project_part2.POST;

import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
import java.sql.SQLException;
import java.util.Date;

public class OrdinaryPost extends Post{
    public boolean isprivate;
    public OrdinaryPost(){}
    public OrdinaryPost(String postcode, String text, OrdinaryUser user, Date time1, boolean Isprivate, String path) throws SQLException {
        this.Kind=false;
        this.photopath=path;
        this.userposter=user;
        this.PostCode=postcode;
        this.Caption=text;
        this.PosterName=user.UserName;
        this.date=time1;
        this.isprivate=Isprivate;
        this.NumberOfLikes=0;
        this.NumberOfRetwiets=0;
        Post.PostsCodesList1.add(postcode);
        user.addPostToPosts(this);
    }
}
