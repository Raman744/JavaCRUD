package Entity;

import java.util.Date;

public class User {
    private int id;
    private String name;
    private String email;
    private String country;
    private Date joinDate;
    private String password;
    private String batch;

    public User() {
    }

    public User(int id, String name, String email, String country, Date joinDate, String password, String batch) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.country = country;
        this.joinDate = joinDate;
        this.password = password;
        this.batch = batch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", email=" + email + ", country=" + country + ", joinDate="
                + joinDate + ", password=" + password + ", batch=" + batch + "]";
    }

}
