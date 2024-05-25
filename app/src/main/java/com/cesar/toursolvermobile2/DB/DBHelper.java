package com.cesar.toursolvermobile2.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMAIL + " TEXT, " +
                    COLUMN_PASSWORD + " TEXT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT);";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

        // Insertar algunos usuarios de prueba
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ") VALUES ('cesarinss08@gmail.com', 'Abcde-12345', 'Cesar', 'Zapien');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ") VALUES ('erickvillaseor@yahoo.com.mx', 'Abcde-12345', 'Erick', 'Villaseñor');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ") VALUES ('eruiz@itsmarts.com.mx', 'Abcde-12345', 'Israel Eduardo', 'Ruiz');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ") VALUES ('ocalixto@itsmarts.com.mx', 'Abcde-12345', 'Oscar', 'Calixto');");
        db.execSQL("INSERT INTO " + TABLE_USERS + " (" + COLUMN_EMAIL + ", " + COLUMN_PASSWORD + ", " + COLUMN_FIRST_NAME + ", " + COLUMN_LAST_NAME + ") VALUES ('prueba@itsmart.com.mx', 'Abcd1234', 'Prueba', 's');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { COLUMN_ID };
        String selection = COLUMN_EMAIL + " = ? AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = { email, password };
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_EMAIL, COLUMN_PASSWORD, COLUMN_FIRST_NAME, COLUMN_LAST_NAME};
        String selection = COLUMN_EMAIL + " = ?";
        String[] selectionArgs = {email};
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRST_NAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LAST_NAME));
            cursor.close();
            return new User(userEmail, password, firstName, lastName);
        }

        if (cursor != null) {
            cursor.close();
        }
        return null;
    }

}