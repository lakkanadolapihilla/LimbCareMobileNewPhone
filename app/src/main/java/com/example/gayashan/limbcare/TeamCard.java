package com.example.gayashan.limbcare;

public class TeamCard {


    private String id;
    private String fname;
    private String lname;
    private String nic;
    private String job;
    private String email;
    private String birthday;
    private byte[] imgteams;

    public TeamCard(String id, String fname, String lname, String nic, String job, String email, String birthday,byte[] imgteams) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.nic = nic;
        this.job = job;
        this.email = email;
        this.birthday = birthday;
        this.imgteams=imgteams;
    }

    public String getId() {
        return id;
    }

        public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getNic() {
        return nic;
    }

    public String getJob() {
        return job;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }
    public byte[] getImage() {
        return imgteams;
    }
}

