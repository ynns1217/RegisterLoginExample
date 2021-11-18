package com.example.registerloginexample;

//MainActivity-ArrayList에 데이터를 저장하기 위해 사용됩니다.
//UsersAdapter-ArrayList에 있는 데이터를 RecyclerView에 보여줄 때 사용됩니다.


public class PersonalData {
    private String member_plusSite;
    private String member_plusID;
    private String member_plusPassword;

    public String getMember_plusSite() {
        return member_plusSite;
    }

    public String getMember_plusID() {
        return member_plusID;
    }

    public String getMember_plusPassword() {
        return member_plusPassword;
    }


    public void setMember_plusSite(String plusSite) {
        this.member_plusSite = member_plusSite;
    }

    public void setMember_plusID(String plusID) {
        this.member_plusID = member_plusID;
    }

    public void setMember_plusPassword(String plusPassword) {
        {
            this.member_plusPassword = member_plusPassword;
        }
    }
}
