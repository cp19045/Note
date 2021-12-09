package com.example.memorandum.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.memorandum.Note;
import com.example.memorandum.db.DatabaseHelper;

import java.util.ArrayList;

public class NoteDaoLmp implements NoteDao {
    private final DatabaseHelper mNoteDatabaseHelper;

    public NoteDaoLmp(Context context) {
        mNoteDatabaseHelper = new DatabaseHelper(context);
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
    public long addNote(Note note) {
        SQLiteDatabase db = mNoteDatabaseHelper.getWritableDatabase();
        ContentValues values =NoteValues(note);
        long result = db.insert(DatabaseHelper.TABLE_NoteNAME, null, values);//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return result;
    }

    private ContentValues NoteValues(Note note) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FIELD_NoteTitle, note.getTitle());
        values.put(DatabaseHelper.FIELD_NoteText, note.getText());
        values.put(DatabaseHelper.FIELD_NoteType, note.getType());
        values.put(DatabaseHelper.FIELD_NoteUri,note.getUri());
        return values;
    }

    @Override
    public void remove(String title,String text,String type,String uri) {
        String sql = "delete from note where title='"+title+"' and text='"+text+"' and type='"+type+"' and uri='"+uri+"'";
        SQLiteDatabase db = mNoteDatabaseHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    @Override
    public void remove(String title, String text, String type) {
        String sql = "delete from note where title='"+title+"' and text='"+text+"' and type='"+type+"'";
        SQLiteDatabase db = mNoteDatabaseHelper.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    @Override
    public ArrayList<Note> FindByNoteType(String type) {
        String sql = "select * from " + DatabaseHelper.TABLE_NoteNAME + " where "+ DatabaseHelper.FIELD_NoteType+ " =? ";
        SQLiteDatabase db = mNoteDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, new String[]{type});//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        ArrayList<Note> notes= new ArrayList<>();
        while (cursor.moveToNext()) {
            notes.add(cursor2User(cursor));
        }
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return notes;
    }

    private Note cursor2User(Cursor cursor) {

        String Title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteTitle));

        String Text = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteText));

        String Type = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteType));

        String Uri= cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteUri));

        return  new Note(Title,Text,Type,Uri);
    }

    @Override
    public ArrayList<Note> FindByNote() {
        String sql = "select * from " + DatabaseHelper.TABLE_NoteNAME ;
        SQLiteDatabase db = mNoteDatabaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql,null);//运行一个预置的SQL语句，返回带游标的数据集（与上面的语句最大的区别 = 防止SQL注入）
        ArrayList<Note> notes= new ArrayList<>();
        while (cursor.moveToNext()) {
            notes.add(cursor3User(cursor));
        }
        /**
         *  关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        db.close();
        return notes;
    }

    private Note cursor3User(Cursor cursor) {

        String Title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteTitle));

        String Text = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteText));

        String Type = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteType));

        String Uri= cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_NoteUri));

        return  new Note(Title,Text,Type,Uri);
    }
}
