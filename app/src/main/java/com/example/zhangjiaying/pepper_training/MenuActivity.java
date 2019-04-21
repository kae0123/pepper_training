package com.example.zhangjiaying.pepper_training;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MenuActivity extends AppCompatActivity {

    private static final int MAX_LESSON_NUMBER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        for(int index=0; index<MAX_LESSON_NUMBER; index++){
            setClickListener(index+1);
        }
    }

    private void setClickListener(final int lessonId) {
        int viewId = getResources().getIdentifier(String.format("btn_lesson_%02d", lessonId), "id", getPackageName());

        if(viewId>0){
            findViewById(viewId).setOnClickListener(v -> {
                try{
                    Class<?> cls = Class.forName(String.format("%s.Lesson%02dActivity", getPackageName(), lessonId));
                    startActivity(new Intent(this, cls));
                } catch (ClassNotFoundException e){
                    e.printStackTrace();
                }
            });
        }
    }
}
