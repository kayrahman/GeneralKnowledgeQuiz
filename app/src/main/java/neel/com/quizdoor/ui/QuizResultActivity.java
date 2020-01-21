package neel.com.quizdoor.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import neel.com.quizdoor.Data.QuizInfoDbHelper;
import neel.com.quizdoor.R;

public class QuizResultActivity extends AppCompatActivity {

    public static final String PREF_RESULT = "result_pref";
    public static final String PREF_LAST_SCORE = "last_score_pref";
    public static final String PREF_TEST_TAKEN = "tests_taken_pref";

    public static final String CORRECT_ANSWER_COUNT = "correct_answer_count";
    public static final String WRONG_ANSWER_COUNT = "wrong_answer_count";
    public static final String AVERAGE_ANSWER_COUNT = "average_answer_count";

    private TextView mPercentageTv;
    private TextView mTotalQuestions;
    private TextView mNumberOfRightAnswer;
    private TextView mTotalQuizTakenCountTv;
    private Button mTakeAnotherTest;
    private ProgressBar mProgressBar;

    private int average = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);


        int cor_ans = getIntent().getIntExtra(CORRECT_ANSWER_COUNT,0);
        int wrong_ans = getIntent().getIntExtra(WRONG_ANSWER_COUNT,0);
        average = getIntent().getIntExtra(AVERAGE_ANSWER_COUNT,0);

        Log.i("COR_ANS", String.valueOf(cor_ans));
        Log.i("WRONG_ANS", String.valueOf(wrong_ans));
        Log.i("AVERAGE_ANS", String.valueOf(average));


        mPercentageTv = findViewById(R.id.tv_result_percentage);
        mTotalQuestions = findViewById(R.id.tv_total_questions);
        mNumberOfRightAnswer = findViewById(R.id.tv_correct_ans);
        mTakeAnotherTest = findViewById(R.id.btn_ac_result_take_another_test);
        mTotalQuizTakenCountTv = findViewById(R.id.tv_ac_quiz_result_total_quiz_count);
        mProgressBar = findViewById(R.id.pb_tv_pr_info_destination);
        mProgressBar.setProgress(100);

        mPercentageTv.setText(String.valueOf(average)+"%");
        mNumberOfRightAnswer.setText(String.valueOf(cor_ans));
        mTotalQuestions.setText("10 answers");
        mProgressBar.setProgress(average);


        mTakeAnotherTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizResultActivity.this, MainActivity.class));
            }
        });

        getResultInfoFromDatabase();



    }

    private void getResultInfoFromDatabase() {

        QuizInfoDbHelper dbHelper = new QuizInfoDbHelper(this);
       long total_quiz_taken_count =  dbHelper.getTakenQuizCount();

        mTotalQuizTakenCountTv.setText(String.valueOf(total_quiz_taken_count));

        Log.i("QUIZ_TAKEN_COUNT", String.valueOf(total_quiz_taken_count));


        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_RESULT, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(PREF_LAST_SCORE,average);
        editor.putLong(PREF_TEST_TAKEN,total_quiz_taken_count);
        editor.apply();



    }


    @Override
    protected void onResume() {
        super.onResume();



    }
}
