package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.LinkedList;

public class DB  extends SQLiteOpenHelper {


    public static final String MATCH_G = "MatchG";
    public static final String Team = "Team";
    public static final String ID = "id";
    public static final String TEAM_HOME = "TeamHome";
    public static final String TEAM_GUEST = "TeamGuest";
    public static final String GOALS_HOME = "GoalsHome";
    public static final String GOL_GUEST = "GolGuest";
    public static final String TEAM_Name = "TeamName";
    public static final String FOOT_DB = "Foot.db";


    public DB(@Nullable Context context) {
        super(context, FOOT_DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create Table " + MATCH_G + " (" + ID + " NTEGER PRIMARY KEY, " + TEAM_HOME + " Text, " + TEAM_GUEST + " Text, " + GOALS_HOME + " int, " + GOL_GUEST + " int)");
        sqLiteDatabase.execSQL("Create Table " + Team + " (" + ID + " NTEGER PRIMARY KEY, " + TEAM_Name + " Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Matchf");
        onCreate(sqLiteDatabase);
    }

    public LinkedList<Data> GetAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        LinkedList<Data> list = new LinkedList<>();
        Cursor cursor = db.query(DB.MATCH_G,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                int NameHome = cursor.getColumnIndex(DB.TEAM_HOME);
                int NameGuest = cursor.getColumnIndex(DB.TEAM_GUEST);
                int GolHome = cursor.getColumnIndex(DB.GOALS_HOME);
                int GolGouest = cursor.getColumnIndex(DB.GOL_GUEST);

                Data data = new Data(cursor.getString(NameHome), cursor.getString(NameGuest), cursor.getInt(GolHome),
                        cursor.getInt(GolGouest));

                list.add(data);

            }while (cursor.moveToNext());
        }
        db.close();
        return list;
    }
}
