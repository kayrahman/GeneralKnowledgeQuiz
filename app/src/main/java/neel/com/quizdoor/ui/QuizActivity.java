package neel.com.quizdoor.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import neel.com.quizdoor.Data.DatabaseContracts;
import neel.com.quizdoor.Data.QuizDatasource;
import neel.com.quizdoor.Data.QuizInfoDbHelper;
import neel.com.quizdoor.R;
import neel.com.quizdoor.model.Question;

import static neel.com.quizdoor.ui.QuizResultActivity.AVERAGE_ANSWER_COUNT;
import static neel.com.quizdoor.ui.QuizResultActivity.CORRECT_ANSWER_COUNT;
import static neel.com.quizdoor.ui.QuizResultActivity.WRONG_ANSWER_COUNT;

public class QuizActivity extends AppCompatActivity {

    private TextView mQuestionTxt;
    private RadioButton mQuestionOneRb,mQuestionTwoRb,mQuestionThreeRb,mQuestionFourRb;
    private Button mCheckBtn,remainingTimeText;
    private RadioGroup mRadioGroup;

    private Button mCorrectAns,mWrongAns;


    ArrayList<Question> mQuestions = new ArrayList<>();
    int position = 0;

    int random_num = 0;

    private CountDownTimer mCountDownTimer;

    private boolean isAnsChecked;
    private boolean showAnswerFlag;

    private int corAnsCount=0;
    private int wrongAnsCount=0;
    private int average = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        QuizDatasource datasource = new QuizDatasource(this);
        mQuestions = datasource.readQuestions();

        /*
        BcsQuizDatasource datasource = new BcsQuizDatasource(this);
        mQuestions = datasource.readQuestions();
       */

        Log.i("QUESTIONS", String.valueOf(mQuestions.size()));

        initFields();

    }

    private void initFields() {

        mCorrectAns = findViewById(R.id.pop_up_correct_ans);
        mCorrectAns.setVisibility(View.INVISIBLE);
        mWrongAns = findViewById(R.id.pop_up_wrong_ans);
        mWrongAns.setVisibility(View.INVISIBLE);

        mQuestionTxt = findViewById(R.id.tv_fm_quiz_question_txt);

        mQuestionOneRb = findViewById(R.id.rb_fm_quiz_option_one);
        mQuestionTwoRb = findViewById(R.id.rb_fm_quiz_option_two);
        mQuestionThreeRb = findViewById(R.id.rb_fm_quiz_option_three);
        mQuestionFourRb = findViewById(R.id.rb_fm_quiz_option_four);

        mCheckBtn = findViewById(R.id.btn_fm_quiz_check_answer);
        remainingTimeText = findViewById(R.id.btn_remaining_time);

        mRadioGroup = findViewById(R.id.rg_ac_quiz);

        startQuiz();


    }


    private void checkAns(String cor_ans) {

        mCountDownTimer.cancel();

            RadioButton checkedAns = findViewById(mRadioGroup.getCheckedRadioButtonId());

            String ansText = checkedAns.getText().toString();

            if (!TextUtils.isEmpty(ansText) || ansText.length() != 0) {


                if (ansText.equalsIgnoreCase(cor_ans)) {

                    corAnsCount++;

                    mCorrectAns.setVisibility(View.VISIBLE);
                    mCorrectAns.setText("Correct");
                    mCheckBtn.setText("Next");


                } else {

                    wrongAnsCount++;
                    mWrongAns.setVisibility(View.VISIBLE);
                    mCheckBtn.setText("Show The Correct Answer");
                    showAnswerFlag = true;
                }


                isAnsChecked = true;

            }




    }

    private void startQuiz() {

        Random random = new Random();
        random_num = random.nextInt(mQuestions.size()- position);
        final String cor_ans = mQuestions.get(random_num).getAnswer();

       int random_num_ans = random.nextInt(4-1);

        mQuestionTxt.setText(Html.fromHtml( mQuestions.get(random_num).getQuestion_title()));
        mQuestionOneRb.setChecked(true);

       switch (random_num_ans){

           case 1:

               mQuestionOneRb.setText(mQuestions.get(random_num).getAnswer());
               mQuestionTwoRb.setText(mQuestions.get(random_num).getOption_two());
               mQuestionThreeRb.setText(mQuestions.get(random_num).getOption_three());
               mQuestionFourRb.setText(mQuestions.get(random_num).getOption_one());

               break;

           case 2:

               mQuestionOneRb.setText(mQuestions.get(random_num).getOption_two());
               mQuestionTwoRb.setText(mQuestions.get(random_num).getAnswer());
               mQuestionThreeRb.setText(mQuestions.get(random_num).getOption_three());
               mQuestionFourRb.setText(mQuestions.get(random_num).getOption_one());

               break;

           case 3:

               mQuestionOneRb.setText(mQuestions.get(random_num).getOption_one());
               mQuestionTwoRb.setText(mQuestions.get(random_num).getOption_two());
               mQuestionThreeRb.setText(mQuestions.get(random_num).getAnswer());
               mQuestionFourRb.setText(mQuestions.get(random_num).getOption_one());

           default:

               mQuestionOneRb.setText(mQuestions.get(random_num).getOption_one());
               mQuestionTwoRb.setText(mQuestions.get(random_num).getOption_two());
               mQuestionThreeRb.setText(mQuestions.get(random_num).getOption_three());
               mQuestionFourRb.setText(mQuestions.get(random_num).getAnswer());





       }




            startQuizTimer();


        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!isAnsChecked){

                    checkAns(cor_ans);

                }
                else {

                    if (showAnswerFlag) {

                        mCorrectAns.setText(cor_ans);
                        mCorrectAns.setVisibility(View.VISIBLE);
                        mWrongAns.setVisibility(View.INVISIBLE);

                        showAnswerFlag = false;
                        mCheckBtn.setText("Next");

                    } else {


                        mCorrectAns.setVisibility(View.INVISIBLE);
                        mWrongAns.setVisibility(View.INVISIBLE);

                        mCheckBtn.setText("Check");
                        isAnsChecked = false;

                        position++;
                        if (position < 10) {
                            startQuiz();
                        } else {

                            //QUIZ FINISHED HERE
                            mCheckBtn.setText("Let's see the result");
                            mCheckBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {




                                    average = corAnsCount*100/position;


                                    if(recordQuizResultInfoIntoSqlite()) {

                                        Intent result_intent = new Intent(QuizActivity.this, QuizResultActivity.class);
                                        result_intent.putExtra(CORRECT_ANSWER_COUNT, corAnsCount);
                                        result_intent.putExtra(WRONG_ANSWER_COUNT, wrongAnsCount);
                                        result_intent.putExtra(AVERAGE_ANSWER_COUNT, average);
                                        result_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        result_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(result_intent);
                                        finish();

                                    }else{

                                        Toast.makeText(QuizActivity.this, "Result could not be saved into database", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            });
                        }

                    }
                }
            }
        });

    }

    private boolean recordQuizResultInfoIntoSqlite() {


        SQLiteDatabase db;
        QuizInfoDbHelper dbHelper = new QuizInfoDbHelper(QuizActivity.this);
        db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_CORRECT_ANSWER_TOTAL,corAnsCount);
        cv.put(DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_RESULT_PERCENTAGE, average);
        cv.put(DatabaseContracts.QuizInfoContract.COLUMN_QUIZ_TAKEN_TIME, getDateTime());

        boolean isInsertSuccessful =
                db.insert(DatabaseContracts.QuizInfoContract.TABLE_NAME_QUIZ_INFO, null, cv)>0;


        return isInsertSuccessful;


    }


    private boolean isFinished;

    public void startQuizTimer(){

        isFinished=false;

        mCountDownTimer= new CountDownTimer(30 * 1000 + 100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                remainingTimeText.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {
                isFinished=true;
                remainingTimeText.setText("0s");
               // createQuestion(v);
            }
        }.start();
    }



    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    @Override
    protected void onResume() {
        super.onResume();


    }








}
