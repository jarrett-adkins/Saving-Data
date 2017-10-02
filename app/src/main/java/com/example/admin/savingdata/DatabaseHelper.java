package com.example.admin.savingdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 10/2/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    public static final String DATABASE_NAME = "Persons.db";
    public static final String TABLE_NAME = "Persons";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_AGE = "Age";
    public static final String COLUMN_GENDER = "Gender";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME + " TEXT PRIMARY KEY, "
                + COLUMN_AGE + " TEXT, " + COLUMN_GENDER + " TEXT" + ")";

        sqLiteDatabase.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //called when version number is changed. Like if you edit the class to have a diff schema,
        //change version num to 2.

        sqLiteDatabase.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( sqLiteDatabase );
    }

    public long savePerson( Person p ) {
        SQLiteDatabase database = this.getWritableDatabase();

        //Log.d("DBHelper", "savePerson: " + p.toString());

        //parse object to content value object and add values to database
        ContentValues ct = new ContentValues();
        ct.put( COLUMN_NAME, p.getName() );
        ct.put( COLUMN_AGE, p.getAge() );
        ct.put( COLUMN_GENDER, p.getGender() );

        long l = database.insert( TABLE_NAME, null, ct );

        return l;
    }

    public List<Person> getPersonList() {
        List<Person> personList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery( query, null );

        //loop through cursor, create person objects, add to list
        if( cursor.moveToFirst() ) {
            do {
                Person p = new Person(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2) );

                personList.add( p );
            } while ( cursor.moveToNext() );
        }

        return personList;
    }
}
