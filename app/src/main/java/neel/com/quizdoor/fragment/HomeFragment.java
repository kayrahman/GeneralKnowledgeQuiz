package neel.com.quizdoor.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import neel.com.quizdoor.R;
import neel.com.quizdoor.ui.QuizActivity;

import static neel.com.quizdoor.ui.QuizResultActivity.PREF_LAST_SCORE;
import static neel.com.quizdoor.ui.QuizResultActivity.PREF_RESULT;
import static neel.com.quizdoor.ui.QuizResultActivity.PREF_TEST_TAKEN;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button mStartQuizButton;
    private TextView mTotalQuizTakenNumTv;
    private TextView mLastScoreTv;
    private ImageButton mGkImageButton,mScienceImageBtn,mSportsImageBtn,mFilmsImageBtn,mMusicImageBtn,mComputerImageBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.content_main, container, false);

        mTotalQuizTakenNumTv = v.findViewById(R.id.tv_ac_content_main_total_quiz_count);
        mLastScoreTv = v.findViewById(R.id.tv_ac_main_last_score);

        mGkImageButton = v.findViewById(R.id.ib_ac_content_main_gk);
        mComputerImageBtn = v.findViewById(R.id.ib_ac_content_main_computer);
        mSportsImageBtn = v.findViewById(R.id.ib_ac_content_main_sports);
        mFilmsImageBtn = v.findViewById(R.id.ib_ac_content_main_films);
        mMusicImageBtn = v.findViewById(R.id.ib_ac_content_main_music);
        mScienceImageBtn = v.findViewById(R.id.ib_ac_content_science);

        mGkImageButton.setOnClickListener(this);
        mComputerImageBtn.setOnClickListener(this);
        mSportsImageBtn.setOnClickListener(this);
        mFilmsImageBtn.setOnClickListener(this);
        mMusicImageBtn.setOnClickListener(this);
        mScienceImageBtn.setOnClickListener(this);


    SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences(PREF_RESULT, 0);
    int last_score = pref.getInt(PREF_LAST_SCORE, 0) ;
    long total_quiz_taken = pref.getLong(PREF_TEST_TAKEN,0) ;


        Log.i("SHARED_PREF_LAST_SCORE", String.valueOf(last_score));
        Log.i("SHARED_PREF_TOTAL_QUIZ", String.valueOf(total_quiz_taken));

        mLastScoreTv.setText(String.valueOf(last_score)+"%");
        mTotalQuizTakenNumTv.setText(String.valueOf(total_quiz_taken));


        return v;
    }



    /*
    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        *//*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*//*




        mTotalQuizTakenNumTv = findViewById(R.id.tv_ac_content_main_total_quiz_count);
        mLastScoreTv = findViewById(R.id.tv_ac_main_last_score);

        mGkImageButton = findViewById(R.id.ib_ac_content_main_gk);
        mComputerImageBtn = findViewById(R.id.ib_ac_content_main_computer);
        mSportsImageBtn = findViewById(R.id.ib_ac_content_main_sports);
        mFilmsImageBtn = findViewById(R.id.ib_ac_content_main_films);
        mMusicImageBtn = findViewById(R.id.ib_ac_content_main_music);
        mScienceImageBtn = findViewById(R.id.ib_ac_content_science);
        mGkImageButton.setOnClickListener(this);
        mComputerImageBtn.setOnClickListener(this);
        mSportsImageBtn.setOnClickListener(this);
        mFilmsImageBtn.setOnClickListener(this);
        mMusicImageBtn.setOnClickListener(this);
        mScienceImageBtn.setOnClickListener(this);

       // mStartQuizButton = findViewById(R.id.btn_ac_content_main_start_quiz_bn);
       *//* mStartQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               // startActivity(new Intent(HomeFragment.this, QuizActivity.class));
            }
        });*//*

        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_RESULT, 0);
        int last_score = pref.getInt(PREF_LAST_SCORE, 0) ;
        long total_quiz_taken = pref.getLong(PREF_TEST_TAKEN,0) ;


        Log.i("SHARED_PREF_LAST_SCORE", String.valueOf(last_score));
        Log.i("SHARED_PREF_TOTAL_QUIZ", String.valueOf(total_quiz_taken));

        mLastScoreTv.setText(String.valueOf(last_score)+"%");
        mTotalQuizTakenNumTv.setText(String.valueOf(total_quiz_taken));









      *//*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*//*
    }



    */

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ib_ac_content_main_gk:

                takeUserToQuizActivity();

                break;

            case R.id.ib_ac_content_main_computer:

                takeUserToQuizActivity();

                break;
            case R.id.ib_ac_content_science:

                takeUserToQuizActivity();

                break;
            case R.id.ib_ac_content_main_films:

                takeUserToQuizActivity();

                break;
            case R.id.ib_ac_content_main_music:

                takeUserToQuizActivity();

                break;
            case R.id.ib_ac_content_main_sports:

                takeUserToQuizActivity();

                break;

        }


    }

    private void takeUserToQuizActivity() {

        Intent intent = new Intent(getActivity(),QuizActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
