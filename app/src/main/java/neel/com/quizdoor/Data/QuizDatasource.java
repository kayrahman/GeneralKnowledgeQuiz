package neel.com.quizdoor.Data;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;
import java.util.ArrayList;

import neel.com.quizdoor.model.Question;

import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_ANSWER;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_OPTION_FOUR;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_OPTION_ONE;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_OPTION_THREE;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_OPTION_TWO;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.COLUMN_QUESTION_TITLE;
import static neel.com.quizdoor.Data.DatabaseContracts.generalKnowledgeQuizContract.TABLE_NAME_QUESTIONS;

public class QuizDatasource {

    private Context mContext;
    private Cursor cursor;

    public QuizDatasource(Context context) {
        mContext = context;
    }

    public ArrayList<Question> readQuestions(){

        ArrayList<Question> quesList = new ArrayList<>();

        QuizDbHelper quizDbHelper = new QuizDbHelper(mContext);

        try {
            quizDbHelper.createDatabase();
            quizDbHelper.openDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }

      //  cursor = quizDbHelper.queryQuesByChapterAndFact("2","2");

        cursor=quizDbHelper.queryQues(
                TABLE_NAME_QUESTIONS,
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
                question.setQuestion_title(getStringFromColumnName(cursor,COLUMN_QUESTION_TITLE));
                question.setAnswer(getStringFromColumnName(cursor,COLUMN_ANSWER));
                question.setOption_one(getStringFromColumnName(cursor,COLUMN_OPTION_ONE));
                question.setOption_two(getStringFromColumnName(cursor,COLUMN_OPTION_TWO));
                question.setOption_three(getStringFromColumnName(cursor,COLUMN_OPTION_THREE));
                question.setOption_four(getStringFromColumnName(cursor,COLUMN_OPTION_FOUR));

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
