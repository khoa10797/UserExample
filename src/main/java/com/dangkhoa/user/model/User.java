package com.dangkhoa.user.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "Users")
public class User {

    @Id
    private ObjectId _id;

    private String name;

    private LocalDate birthday;

    private String phone_number;

    public User() {
    }

    public User(ObjectId _id, String name, LocalDate birthday, String phone_number) {
        this._id = _id;
        this.name = name;
        this.birthday = birthday;
        this.phone_number = phone_number;
    }

    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
