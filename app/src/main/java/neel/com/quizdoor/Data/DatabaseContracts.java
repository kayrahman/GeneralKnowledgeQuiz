package neel.com.quizdoor.Data;

import android.provider.BaseColumns;

public class DatabaseContracts {

    public DatabaseContracts() {
    }

    public static final class generalKnowledgeQuizContract implements BaseColumns {

       // public static final String TABLE_NAME_QUESTIONS = "question_tbl";
        public static final String TABLE_NAME_QUESTIONS = "bg_gk_tbl";

       /* public static final String COLUMN_SUBJECT_NAME= "subject_name";
        public static final String COLUMN_LEVEL= "level";
        public static final String COLUMN_CHAPTER_NUM = "chapter_num";
        public static final String COLUMN_FACT_NUM = "fact_num";
        public static final String COLUMN_QUESTION_TITLE = "question_title";
        public static final String COLUMN_OPTION_ONE = "option_one";
        public static final String COLUMN_OPTION_TWO = "option_two";
        public static final String COLUMN_OPTION_THREE = "option_three";
        public static final String COLUMN_OPTION_FOUR = "option_four";
        public static final String COLUMN_ANSWER = "answer";
*/

        public static final String COLUMN_SUBJECT_NAME= "subject_name";
        public static final String COLUMN_LEVEL= "level";
        public static final String COLUMN_CHAPTER_NUM = "chapter_num";
        public static final String COLUMN_FACT_NUM = "fact_num";
        public static final String COLUMN_QUESTION_TITLE = "queTitle";
        public static final String COLUMN_OPTION_ONE = "opA";
        public static final String COLUMN_OPTION_TWO = "opB";
        public static final String COLUMN_OPTION_THREE = "opC";
        public static final String COLUMN_OPTION_FOUR = "opD";
        public static final String COLUMN_ANSWER = "corAns";




    }

    public static final class QuizInfoContract implements BaseColumns{

        public static final String TABLE_NAME_QUIZ_INFO = "quiz_info";
        public static final String COLUMN_QUIZ_TAKEN_TIME = "timestamp";
        public static final String COLUMN_QUIZ_RESULT_PERCENTAGE = "result_percentage";
        public static final String COLUMN_QUIZ_CORRECT_ANSWER_TOTAL = "cor_ans_total";

    }


}
