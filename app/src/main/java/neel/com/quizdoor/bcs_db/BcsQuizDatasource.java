package neel.com.quizdoor.bcs_db;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;
import java.util.ArrayList;

import neel.com.quizdoor.model.Question;

public class BcsQuizDatasource {

    private Context mContext;
    private Cursor cursor;

    public BcsQuizDatasource(Context context) {
        mContext = context;
    }

    public ArrayList<Question> readQuestions(){

        ArrayList<Question> quesList = new ArrayList<>();

        BcsQuizDbHelper quizDbHelper = new BcsQuizDbHelper(mContext);

        try {
            quizDbHelper.createDatabase();
            quizDbHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

      //  cursor = quizDbHelper.queryQuesByChapterAndFact("2","2");

        cursor=quizDbHelper.queryQues(
                "questionInfo",
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()) {
            do {
                Question question=new Question();
                question.setQuestion_title(getStringFromColumnName(cursor,"queTitle"));
                question.setAnswer(getStringFromColumnName(cursor,"answer"));
                question.setOption_one(getStringFromColumnName(cursor,"optionA"));
                question.setOption_two(getStringFromColumnName(cursor,"optionB"));
                question.setOption_three(getStringFromColumnName(cursor,"optionC"));
                question.setOption_four(getStringFromColumnName(cursor,"optionD"));

                quesList.add(question);
            } while (cursor.moveToNext());
        }
        cursor.close();
        quizDbHelper.close();
        return quesList;

    }

    private String getStringFromColumnName(Cursor cursor, String columnName) {
        int coulmnIndex = cursor.getColumnIndex(columnName);
        String name = cursor.getString(coulmnIndex);
        return name;
    }



}
