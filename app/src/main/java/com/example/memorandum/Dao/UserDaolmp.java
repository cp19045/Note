package com.example.memorandum.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.memorandum.db.DatabaseHelper;
import com.example.memorandum.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UserDaolmp implements UserDao {

    private final DatabaseHelper mUserDatabaseHelper;

    public UserDaolmp(Context context) {
        mUserDatabaseHelper = new DatabaseHelper(context);
    }//构造函数

    //Cursor对象常用方法如下：
    /*    c.move(int offset); //以当前位置为参考,移动到指定行
        c.moveToFirst();    //移动到第一行
        c.moveToLast();     //移动到最后一行
        c.moveToPosition(int position); //移动到指定行
        c.moveToPrevious(); //移动到前一行
        c.moveToNext();     //移动到下一行
        c.isFirst();        //是否指向第一条
        c.isLast();     //是否指向最后一条
        c.isBeforeFirst();  //是否指向第一条之前
        c.isAfterLast();    //是否指向最后一条之后
        c.isNull(int columnIndex);  //指定列是否为空(列基数为0)
        c.isClosed();       //游标是否已关闭
        c.getCount();       //总数据项数
        c.getPosition();    //返回当前游标所指向的行数
        c.getColumnIndex(String columnName);//返回某列名对应的列索引值
        c.getString(int columnIndex);   //返回当前行指定列的值*/


    @Override
    public long adduser(User user) {
        SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
        ContentValues values = UserValues(user);
        long result = db.insert(DatabaseHelper.TABLE_StudentNAME, null, values);//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return result;
    }

    private ContentValues UserValues(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FIELD_StuName, user.getName());
        values.put(DatabaseHelper.FIELD_StuPassWord, user.getPwd());
        values.put(DatabaseHelper.FIELD_StuId, user.getUserId());
        values.put(DatabaseHelper.FIELD_StuPhone,user.getPhone());
        return values;
    }

    @Override
    public int updateuser(User user, String userId) {
        SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
        ContentValues values = User2Values(user);
        int result = db.update(DatabaseHelper.TABLE_StudentNAME, values, userId, null);//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return result;
    }

    private ContentValues User2Values(User user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FIELD_StuPassWord, user.getPwd());
        return values;
    }

    @Override
    public Boolean FindByPhoneuser(String user_Id, String password) {
        String sql = "select * from " + DatabaseHelper.TABLE_StudentNAME + " where "+ DatabaseHelper.FIELD_StuId+ " =? "+" AND "+ DatabaseHelper.FIELD_StuPassWord+ " =? ";
        SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{user_Id,password});//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        if (cursor.moveToFirst()==true) {
            cursor.close();
            return true;
        }
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return false;
    }

    @Override
    public Boolean FindByPhone(String user_Id) {
        String sql = "select * from " + DatabaseHelper.TABLE_StudentNAME + " where "+ DatabaseHelper.FIELD_StuId+ " = ? ";
        SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{user_Id});//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        if (cursor.moveToFirst()==true) {
            cursor.close();
            return true;
        }
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return false;
    }

    @Override
    public List<User> FindByUserName(String phone) {
        String sql = "select * from " + DatabaseHelper.TABLE_StudentNAME + " where "+ DatabaseHelper.FIELD_StuId+ " =? ";
        SQLiteDatabase db = mUserDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{phone});//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        List<User> users = new ArrayList<>();
        while (cursor.moveToNext()) {
            User user = cursor2User(cursor);
            users.add(user);
        }
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return users;
    }

    private User cursor2User(Cursor cursor) {
        User user = new User();

        int userId = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.FIELD_StuID));
        user.setId(userId);

        String userName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_StuName));
        user.setName(userName);

        String userPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_StuPassWord));
        user.setPwd(userPassword);


        String Stu_Id = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_StuId));
        user.setUserId(Stu_Id);

        String userPhone = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_StuPhone));
        user.setPhone(userPhone);
        return user;
    }


}
