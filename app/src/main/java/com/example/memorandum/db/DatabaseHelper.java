package com.example.memorandum.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private final static String DATANAME = "data.db";

    private final  static int VERSION = 1;

    public final static String TABLE_StudentNAME = "user";

    public final static String FIELD_StuID = "_id";

    public final static String FIELD_StuName = "userName";

    public final static String FIELD_StuPassWord = "Pwd";

    public final static String FIELD_StuId = "userId";

    public final static String FIELD_StuPhone = "Phone";

    public final static String TABLE_NoteNAME = "note";

    public final static String FIELD_NoteTitle = "title";

    public final static String FIELD_NoteText = "text";

    public final static String FIELD_NoteType = "type";

    public final static String FIELD_NoteUri = "uri";

    public final static String FIELD_NoteID = "_id";


    // 参数说明
    // context：上下文对象
    // name：数据库名称
    // param：一个可选的游标工厂（通常是 Null）
    // version：当前数据库的版本，值必须是整数并且是递增的状态

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATANAME , null, VERSION);
    }


    /**
     * 复写onCreate（）
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型： 整型数据、字符串类型、日期类型、二进制
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库1张表
        // 通过execSQL（）执行SQL语句（此处创建了1个名为student的表）
        String student="create table "+
                TABLE_StudentNAME+
                "(" +FIELD_StuID+
                " integer primary key autoincrement," +
                FIELD_StuName+" varchar(30),"+
                FIELD_StuPassWord+" varchar(32),"+
                FIELD_StuId+" varchar(322),"+
                FIELD_StuPhone+" varchar(32))";
        db.execSQL(student);

        String Note="create table "+
                TABLE_NoteNAME+
                    "(" +FIELD_NoteID+
                " integer primary key autoincrement," +
                FIELD_NoteTitle+" varchar(255),"+
                FIELD_NoteText+" varchar(255),"+
                FIELD_NoteType+" varchar(255),"+
                FIELD_NoteUri+" varchar(255))";
        db.execSQL(Note);

        // 注：数据库实际上是没被创建 / 打开的（因该方法还没调用）
        // 直到getWritableDatabase() / getReadableDatabase() 第一次被调用时才会进行创建 /

    }

    /**
     * 复写onUpgrade（）
     * 调用时刻：当数据库升级时则自动调用（即 数据库版本 发生变化时）
     * 作用：更新数据库表结构
     * 注：创建SQLiteOpenHelper子类对象时,必须传入一个version参数，该参数 = 当前数据库版本, 若该版本高于之前版本, 就调用onUpgrade()
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 参数说明：
        // db ： 数据库
        // oldVersion ： 旧版本数据库
        // newVersion ： 新版本数据库
    }
}
