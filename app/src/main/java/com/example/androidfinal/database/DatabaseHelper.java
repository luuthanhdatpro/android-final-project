package com.example.androidfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="final-project.db";
    private static final int DATABASE_VERSION = 1;
    private String path;

    static SQLiteDatabase
    Context mContext;
    String dbName;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        this.path = "assets/database/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void checkDB(){
        SQLiteDatabase sqLiteDatabase = null;
        String filePath = path + DATABASE_NAME;
        try{
            sqLiteDatabase = SQLiteDatabase.openDatabase(filePath, null, 0);

        }catch (Exception e){
            e.printStackTrace();
        }
        if (sqLiteDatabase!= null){
            Toast.makeText(mContext, "Already Exist", Toast.LENGTH_SHORT).show();
        }else{
            copyDataBase();
        }
    }
    public void copyDataBase(){
        this.getReadableDatabase();

        try {
            InputStream ios = mContext.getAssets().open(DATABASE_NAME);
            OutputStream os = new FileOutputStream(path+DATABASE_NAME);
            byte[] buffer = new byte[1024];

            int len;
            while ((len = ios.read(buffer))>0){
                os.write(buffer, 0, len);
            }
            os.flush();
            ios.close();
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("CopyDB","Sucessfully");

    }
    public void openDatabase(){
        String filePath = path+DATABASE_NAME;
        SQLiteDatabase.openDatabase(filePath,null,0);
    }
}
