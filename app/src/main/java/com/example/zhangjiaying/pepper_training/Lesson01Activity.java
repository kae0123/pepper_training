package com.example.zhangjiaying.pepper_training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;

public class Lesson01Activity extends AppCompatActivity implements RobotLifecycleCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson01);

        QiSDK.register(this, this);
    }

    @Override
    protected void onDestroy() {
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        Say say = SayBuilder.with(qiContext)
                .withText("こんにちは")
                .build();

        say.run();
    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}
