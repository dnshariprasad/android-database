package hari.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hari on 04/02/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    //database helper
    public static final String DATABASE_NAME_ = "users.db";
    public static final int DATABASE_VERSION = 1;

    //User table
    public static final String TABLE_USERS = "users";

    //users table rows
    public static final String USERS_COLUMN_ID = "ID";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_MOBILE = "mobile";
    public static final String USERS_COLUMN_EMAIL = "email";

    //quarries
    //create
    public static final String CREATE_TABLES_USERS = "create table " + TABLE_USERS +
            "(" +
            USERS_COLUMN_ID + " integer primary key," +
            USERS_COLUMN_NAME + " text," +
            USERS_COLUMN_MOBILE + " text," +
            USERS_COLUMN_EMAIL + " text)";

    //drop
    public static final String DROP_TABLES_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;

    //select rows
    public static final String SELECT_ALL_USERS = "SELECT * FROM " + TABLE_USERS;

    //array of columns
    private static String[] USER_COLUMNS = {USERS_COLUMN_NAME, USERS_COLUMN_MOBILE, USERS_COLUMN_EMAIL};


    public DbHelper(Context context) {
        super(context, DATABASE_NAME_, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLES_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete old tables
        db.execSQL(DROP_TABLES_USERS);
        //creating tables again
        onCreate(db);
    }

    /**
     * it inserts user to table
     *
     * @param sqLiteDatabase writable db
     * @param name
     * @param email
     * @param mobile
     */
    public void addUser(SQLiteDatabase sqLiteDatabase, String name, String email, String mobile) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERS_COLUMN_NAME, name);
        contentValues.put(USERS_COLUMN_MOBILE, mobile);
        contentValues.put(USERS_COLUMN_EMAIL, email);
        //insert
        sqLiteDatabase.insert(TABLE_USERS, null, contentValues);
    }

    /**
     * returns all users
     */
    public List<UserModel> getAllUsers(SQLiteDatabase sqLiteDatabase) {
        List<UserModel> userModels = new ArrayList<>();
        //get cur
        Cursor cursor = sqLiteDatabase.query(TABLE_USERS, USER_COLUMNS, null, null, null, null, USERS_COLUMN_ID + " ASC");
        while (cursor.moveToNext()) {
            UserModel userModel = new UserModel();
            userModel.setName(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_NAME)));
            userModel.setEmail(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_EMAIL)));
            userModel.setMobile(cursor.getString(cursor.getColumnIndex(USERS_COLUMN_MOBILE)));
            userModels.add(userModel);

        }

        return userModels;
    }
}
