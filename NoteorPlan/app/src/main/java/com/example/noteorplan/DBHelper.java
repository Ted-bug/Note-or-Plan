package com.example.noteorplan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="note.db";
    private static final int VERSION=1;
    private static final String TABLE="information";

    //一定要调用父类的构造函数
    public  DBHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TABLE+"(i INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR(256),content varchar(256))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("提示","版本更新了诶");
    }
}
