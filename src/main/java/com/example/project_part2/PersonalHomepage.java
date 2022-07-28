package com.example.project_part2;
import java.sql.SQLException;
import com.example.project_part2.POST.*;
import com.example.project_part2.USER.*;
import com.example.project_part2.DataBaseController.*;
public class PersonalHomepage {
    public static PersonalHomepage personalHomepage=new PersonalHomepage();
    public User user;

    public void start(User usertemp) throws SQLException {
        boolean flag=true;
        while (flag){
            if(!usertemp.isreport){
                String str, strtemp = "Enter a number:\n1.Create new post\n2.show timeline\n3.Complete or Edit personal information" +
                        "\n4.My chats and PVs\n5.My contacts (followers & followings & ...)" +
                        "\n6.Show My posts\n7.Add Story\n8.back ";
                System.out.println(strtemp);
                str = Main.scanner.nextLine();
                str=str.trim();
                if (str.equals("1")) {
                    CreatenewPost.createnewPost.user=usertemp;
                    CreatenewPost.createnewPost.start();
                }
                else if (str.equals("2")) {
                    // ShowTimeline.showTimeline.user=;
                    ShowTimeline.showTimeline.start(usertemp);
                }
                else if (str.equals("3")) {
                    CompletePersonalInformation.completePersonalInformation.user=usertemp;
                    CompletePersonalInformation.completePersonalInformation.start();
                }
                else if (str.equals("4")) {
                    MychatsandPVs.mychatsandPVs.user=usertemp;
                    MychatsandPVs.mychatsandPVs.start();
                }
                else if (str.equals("5")) {
                    Mycontacts.mycontacts.start(usertemp);
                }
                else if (str.equals("6")) {
                    ShowPosts.showPosts.start(usertemp,usertemp);
                }
                else if (str.equals("7")) {
                    CreatenewStory.createnewStory.user=usertemp;
                    CreatenewStory.createnewStory.start();
                }
                else if (str.equals("8")) {
                    flag=false;
                    SignIn.signIn.start();
                }
                else {
                    System.out.println("Invalid command!");
                }}
            else {   String str, strtemp = "Enter a number:\n1.show timeline\n2.Complete or Edit personal information" +
                    "\n3.My chats and PVs\n4.My contacts (followers & followings & ...)" +
                    "\n5.back ";
                System.out.println(strtemp);
                str = Main.scanner.nextLine();
                str=str.trim();

                if (str.equals("1")) {
                    ShowTimeline.showTimeline.start(usertemp);
                }
                else if (str.equals("2")) {
                    CompletePersonalInformation.completePersonalInformation.user=usertemp;
                    CompletePersonalInformation.completePersonalInformation.start();
                }
                else if (str.equals("3")) {
                    MychatsandPVs.mychatsandPVs.user=usertemp;
                    MychatsandPVs.mychatsandPVs.start();
                }
                else if (str.equals("4")) {
                    Mycontacts.mycontacts.start(usertemp);
                }
                else if (str.equals("5")) {
                    flag=false;
                    SignIn.signIn.start();
                }
                else {
                    System.out.println("Invalid command!");
                }
            }

        }
    }
}
