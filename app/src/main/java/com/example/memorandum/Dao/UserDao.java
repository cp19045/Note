package com.example.memorandum.Dao;


import com.example.memorandum.pojo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public interface UserDao {

    long adduser(User user);//增加用户信息

    int updateuser(User user, String User_Phone);//根据用户手机号修改密码

    Boolean FindByPhoneuser(String stu_Id, String password);//查询用户手机号和密码是否存在

    Boolean FindByPhone(String stu_Id);//查询用户手机号是否存在

    List<User> FindByUserName(String phone);//根据手机号查询所有客户信息

}
