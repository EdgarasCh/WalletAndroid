package com.example.edgaras.btcwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edgaras on 3/4/2018.
 */

public class DatabaseSQLite extends SQLiteOpenHelper  {

    private static final int DATABASE_VERSION   = 1;
    private static final String DATABASE_NAME   = "db";
    private static final String TABLE_USERS     = "users";
    private static final String USER_ID         = "id";
    private static final String USER_LEVEL      = "userlevel";
    private static final String USER_NAME       = "name";
    private static final String USER_PASSWORD   = "password";
    private static final String USER_EMAIL      = "email";

    public DatabaseSQLite(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                                + USER_ID + " INTEGER PRIMARY KEY,"
                                + USER_LEVEL + " TEXT,"
                                + USER_NAME + " TEXT,"
                                + USER_PASSWORD + " TEXT,"
                                + USER_EMAIL + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);
    }

    void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_LEVEL,      user.getUserlevel());
        contentValues.put(USER_NAME,       user.getUsername());
        contentValues.put(USER_PASSWORD,   user.getPassword());
        contentValues.put(USER_EMAIL,      user.getEmail());

        db.insert(TABLE_USERS, null, contentValues);
        db.close();
    }

    User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =  db.query(TABLE_USERS,
             new String[]{USER_ID,USER_LEVEL,USER_NAME,USER_PASSWORD,USER_EMAIL},
             USER_ID + "=?",
             new String[]{String.valueOf(id)}, null, null, null, null);


        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3));
        return user;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_USERS;
        Cursor cursor =  db.rawQuery( selectQuery, null );

        if (cursor.moveToFirst()) {
            do {User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserlevel(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                user.setEmail(cursor.getString(4));

                users.add(user);
               } while (cursor.moveToNext());
             }
        return users;
    }

    public boolean isValidUser(String username, String password){

        String selectQuery = "SELECT * FROM " + TABLE_USERS + " WHERE "
                     + USER_NAME + "='" + username + "'AND " + USER_PASSWORD + "='" + password + "'" ;
        Cursor cursor = getReadableDatabase().rawQuery(selectQuery, null);

        if (cursor.getCount()>0)
            return true;
        return false;
    }

}
