package com.example.memorandum.Dao;

import com.example.memorandum.Note;

import java.util.ArrayList;
import java.util.List;

public interface NoteDao {
    long addNote(Note note);//增加用户信息

    void remove(String title,String text,String type,String uri);

    void remove(String title,String text,String type);

    //int updateuser(ArrayList<Note> note, String User_Phone);//根据用户手机号修改密码

    ArrayList<Note> FindByNote();//根据手机号查询所有客户信息

    ArrayList<Note> FindByNoteType(String phone);//根据手机号查询所有客户信息
}
