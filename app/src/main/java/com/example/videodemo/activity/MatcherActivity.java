package com.example.videodemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.videodemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherActivity extends AppCompatActivity {

    private EditText edit;
    private TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcher);

        edit = (EditText) findViewById(R.id.edit_matcher);
        findViewById(R.id.confirm_matcher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edit.getText().toString().trim())){
                    Toast.makeText(MatcherActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                show.setText(match(edit.getText().toString().trim()));
            }
        });
        show = (TextView) findViewById(R.id.show_matcher);
    }

    public String match(String content){
        String regex="\\$[^\\\\(]+\\\\(([^\\\\)]+)\\\\)\\$";
        Pattern p=Pattern.compile(regex);
        Matcher matcher=p.matcher(content);
        while(matcher.find()){
            matcher.group(1);
        }
        return content;
    }
}
