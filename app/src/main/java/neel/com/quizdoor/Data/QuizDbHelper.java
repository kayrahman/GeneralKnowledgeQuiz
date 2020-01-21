package neel.com.quizdoor.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_CHAPTER_NUM;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_FACT_NUM;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.TABLE_NAME_QUESTIONS;

public class QuizDbHelper extends SQLiteOpenHelper {

    private Context mContext;
    String DB_PATH=null;
    private SQLiteDatabase myDatabase;

    //private static final String DATABASE_NAME = "generalknowledge.db";
   // private static final String DATABASE_NAME = "amazed.db";
   // private static final String DATABASE_NAME = "Quiz.db";
    private static final String DATABASE_NAME = "bg_gk.db";

    // If you change the database schema, you must increment the database version
    private static final int DATABASE_VERSION = 1;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContext =context;
        this.DB_PATH="data/data/"+context.getPackageName()+"/"+"databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void createDatabase() throws IOException {

        boolean dbExists=checkDBExists();
        if(dbExists){

        }
        else {
            this.getReadableDatabase();
            try{
                copyDatabase();
            }catch (IOException e){
                throw new Error("ERROR COPYING DATABASE");
            }
        }

    }

    private boolean checkDBExists(){

        SQLiteDatabase checkDB=null;
        try{
            String myPath=DB_PATH+DATABASE_NAME;
            checkDB=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLiteException e){
            e.printStackTrace();
        }
        if (checkDB!=null){
            checkDB.close();
        }
        return checkDB!=null? true:false;
    }


    private void copyDatabase()throws IOException {
        InputStream myInput=mContext.getAssets().open(DATABASE_NAME);
        String outFileName=DB_PATH+DATABASE_NAME;

        OutputStream myOutput=new FileOutputStream(outFileName);
        byte[] buffer=new byte[100];

        int length;

        while((length=myInput.read(buffer))>0){
            myOutput.write(buffer,0,length);

        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }


    public void openDatabase() throws SQLiteException{
        String dbPath=DB_PATH+DATABASE_NAME;
        myDatabase=SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READONLY);
    }


    public Cursor queryQues(String table, String[] columns, String selections, String selectionargs, String groupby, String having, String orderby){
        return myDatabase.query(
                TABLE_NAME_QUESTIONS,null,null,null,null,null,null
        );
    }

    public Cursor queryQuesByChapterAndFact(String fact_num,String chapter_num){

        String[] selection_args = new String[]{fact_num,chapter_num};
        Cursor c = myDatabase.rawQuery("SELECT * FROM "+TABLE_NAME_QUESTIONS+" WHERE "+COLUMN_FACT_NUM +" =?"
                +" AND "+ COLUMN_CHAPTER_NUM +"=?"
                ,selection_args);



        return c;
    }


    @Override
    public synchronized void close(){
        if(myDatabase!=null){
            myDatabase.close();
            super.close();
        }
    }



}
