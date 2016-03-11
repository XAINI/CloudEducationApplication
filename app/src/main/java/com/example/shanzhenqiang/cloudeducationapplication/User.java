package com.example.shanzhenqiang.cloudeducationapplication;

/**
 * Created by shanzhenqiang on 2016/3/8.
 */
public class User {

    public int _id;
    public  String account;
    public  String password;

    public User(){

    }

    public User(String account, String password){
        this.account = account;
        this.password = password;
    }
}
