package neel.com.quizdoor.Data;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static neel.com.quizdoor.Data.DatabaseContracts.QuizInfoContract.TABLE_NAME_QUIZ_INFO;

public class QuizInfoDbHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    // The database name
    private static final String DATABASE_NAME = "quiz_info.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;


    public QuizInfoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        // Create a table to hold waitlist data
        final String SQL_CREATE_QUIZ_INFO_TABLE = "CREATE TABLE " + TABLE_NAME_QUIZ_INFO + " (" +
                DatabaseContracts.QuizInfoContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_RESULT_PERCENTAGE + " INTEGER NOT NULL, " +
                DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_CORRECT_ANSWER_TOTAL + " INTEGER NOT NULL, " +
                DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_TAKEN_TIME + " STRING NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_QUIZ_INFO_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public long getTakenQuizCount(){

        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME_QUIZ_INFO);
        db.close();
        return count;

    }





}
