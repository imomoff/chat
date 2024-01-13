package uz.pdp.entity;

import java.io.Serializable;
import java.util.TimeZone;

public class User implements Serializable {
    private Integer id = counter++;
    private String name;
    private String password;
    private String timeZone;
    private static Integer counter = 1;

    public User(String name, String password, String timeZone) {
        this.name = name;
        this.password = password;
        this.timeZone = timeZone;
    }

    public Integer getId() {
        return id;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
