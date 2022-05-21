package com.example.androidfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androidfinal.model.Question;
import com.example.androidfinal.model.Sign;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mDatabase;
    private static final String DATABASE_NAME="final-project.db";
    private static final int DATABASE_VERSION = 2;
    private String path;

    private Context mContext;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
        this.path = "/data/data/"+context.getPackageName()+"/" +"databases/";
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
        closeDatabase();
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
        Log.i("CopyDB","Sucessfully");
        closeDatabase();
    }
    public void closeDatabase(){
        if(mDatabase != null){
            mDatabase.close();
        }
    }
    public int countQuestion(String type){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(*) FROM Questions WHERE type LIKE '" + type +"%';", null);
        int count = 0;
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        closeDatabase();
        return count;
    }
    public int countDoneQuestion(String type){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(*) FROM Questions WHERE type LIKE '" + type +"%' AND isLearned = 1;", null);
        int count = 0;
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        closeDatabase();
        return count;
    }
    public int countSavedQuestion(){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT COUNT(*) FROM Questions WHERE  isSaved = 1;", null);
        int count = 0;
        if(cursor != null){
            if(cursor.getCount() > 0){
                cursor.moveToFirst();
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        closeDatabase();
        return count;
    }
    public ArrayList<Question> getQuestionByType(String name){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Questions WHERE type LIKE '" + name +"%';",null);
        ArrayList<Question> qList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String image = cursor.getString(2);
            String type = cursor.getString(3);
            String des = cursor.getString(4);
            boolean isSaved = (cursor.getInt(5) == 1)? true : false;
            boolean isEss = (cursor.getInt(6) == 1)? true : false;
            String op1 = cursor.getString(7);
            String op2 = cursor.getString(8);
            String op3 = cursor.getString(9);
            String op4 = cursor.getString(10);
            int rightAns = (cursor.getInt(11));
            boolean isLearned = (cursor.getInt(12) == 1)? true : false;
            qList.add(new Question(id,title,image,type,des,isSaved,isEss,op1,op2,op3,op4,rightAns,isLearned));
        }
        cursor.close();
        closeDatabase();
        return qList;
    }
    public ArrayList<Question> getSavedQuestion(){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Questions WHERE isSaved=1;",null);
        ArrayList<Question> qList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String image = cursor.getString(2);
            String type = cursor.getString(3);
            String des = cursor.getString(4);
            boolean isSaved = (cursor.getInt(5) == 1)? true : false;
            boolean isEss = (cursor.getInt(6) == 1)? true : false;
            String op1 = cursor.getString(7);
            String op2 = cursor.getString(8);
            String op3 = cursor.getString(9);
            String op4 = cursor.getString(10);
            int rightAns = (cursor.getInt(11));
            boolean isLearned = (cursor.getInt(12) == 1)? true : false;
            qList.add(new Question(id,title,image,type,des,isSaved,isEss,op1,op2,op3,op4,rightAns,isLearned));
        }
        cursor.close();
        closeDatabase();
        return qList;
    }
    public ArrayList<Sign> getSignByType(String type){
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Sign WHERE type LIKE '" + type +"%';",null);
        ArrayList<Sign> sList = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String name = cursor.getString(2);
            String meaning = cursor.getString(3);
            String image = cursor.getString(4);
            String type1 = cursor.getString(5);
            sList.add(new Sign(id,title,name,meaning,image,type1));
        }
        cursor.close();
        closeDatabase();
        return sList;
    }
    public void updateLearned(int id, int value){
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("isLearned", value);
        try {
            mDatabase.update("Questions",values,"_id = ?",  new String[]{String.valueOf(id)});
        }catch (Exception ex){
            Log.i("update","Something went wrong.");
        }
        closeDatabase();

    }
    public void updateSaved(int id, int value){
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("isSaved", value);
        try {
            mDatabase.update("Questions",values,"_id = ?",  new String[]{String.valueOf(id)});
        }catch (Exception ex){
            Log.i("update","Something went wrong.");
        }
        closeDatabase();

    }
    public void openDatabase(){
        String filePath = mContext.getDatabasePath(DATABASE_NAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(filePath,null,SQLiteDatabase.OPEN_READWRITE);
    }
}
