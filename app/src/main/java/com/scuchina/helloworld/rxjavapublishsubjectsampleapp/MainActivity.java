package com.scuchina.helloworld.rxjavapublishsubjectsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private PublishSubject<String> subject = PublishSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_main);
        getObservable().subscribe(this::displayMsg);
    }

    private Observable<String> getObservable() {
        return subject.subscribeOn(Schedulers.io()).doOnNext(this::logNext);
    }

    private void logNext(String s) {
        Toast.makeText(this, "next text to be show", Toast.LENGTH_SHORT).show();
    }



    private void displayMsg(String s) {
        textView.setText(s);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btn_publish) {
            if (textView.getText().toString().equals("Hello World")) {
                subject.onNext("Hello Wenbin");
            } else {
                subject.onNext("Hello World");
            }
        }
    }
}
