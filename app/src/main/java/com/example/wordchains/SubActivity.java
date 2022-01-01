package com.example.wordchains;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class SubActivity extends AppCompatActivity {

    private EditText editText;
    private String message;

    //履歴を表示するリスト
    private ArrayList<String> historyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // MainActivityからメッセージを受け取る
        Intent intentMain = getIntent();
        message = intentMain.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView history = findViewById(R.id.history);
        historyList.add(String.valueOf(history));

        // SubActivityで入力した値
        editText = findViewById(R.id.edit_text);

        // MainActivityに戻る
        Button button = findViewById(R.id.button);
        button.setOnClickListener( v -> {
            Intent intentSub = new Intent();
            String str = editText.getText().toString();

            if (!str.equals("")) {
                //MainActivityに値を渡す。
                intentSub.putExtra(MainActivity.EXTRA_MESSAGE, str);
                editText.setText("");
                setResult(RESULT_OK, intentSub);
                finish();
            }
        });
    }
    //ライフサイクル
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("subActivity","onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("subActivity","onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("subActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("subActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("subActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("subActivity","onDestroy()");
    }
}
