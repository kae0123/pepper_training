package com.example.zhangjiaying.pepper_training;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aldebaran.qi.Consumer;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.sdk.QiContext;
import com.aldebaran.qi.sdk.QiSDK;
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks;
import com.aldebaran.qi.sdk.builder.SayBuilder;
import com.aldebaran.qi.sdk.object.conversation.Say;

public class Lesson04Activity extends AppCompatActivity implements RobotLifecycleCallbacks {

    private QiContext qiContext;
    private Say say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson03);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(say==null) return;

                Future<Void> sayFuture = say.async().run();

                sayFuture.thenConsume(new Consumer<Future<Void>>() {
                    @Override
                    public void consume(Future<Void> voidFuture) throws Throwable {
                        if(voidFuture.isSuccess()){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),
                                            "成功",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });
                        }
                    }
                });
            }
        });

        QiSDK.register(this, this);
    }

    @Override
    protected void onDestroy() {
        QiSDK.unregister(this, this);
        super.onDestroy();
    }

    @Override
    public void onRobotFocusGained(QiContext qiContext) {
        this.qiContext = qiContext;

        Future<Say> future = SayBuilder.with(qiContext)
                .withText("ボタンが押されたよ")
                .buildAsync();

        future.thenConsume(new Consumer<Future<Say>>() {
            @Override
            public void consume(Future<Say> sayFuture) throws Throwable {
                if(sayFuture.isSuccess()){
                    say = sayFuture.get();
                }
            }
        });
    }

    @Override
    public void onRobotFocusLost() {

    }

    @Override
    public void onRobotFocusRefused(String reason) {

    }
}

