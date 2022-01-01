package com.example.wordchains;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // the key constant
    public static final String EXTRA_MESSAGE
            = "com.example.wordchains.MESSAGE";
    //履歴のテキスト
    private TextView history;
    //履歴を表示するリスト
    private ArrayList<String> historyList = new ArrayList<>();

    //しりとり失敗時の文言
    private String failedMessage = "チャレンジ失敗です";

    private String res = "";
    private String strBegin = "";
    private String strEnd = "";

    static final int RESULT_SUBACTIVITY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        history = findViewById(R.id.history);
        final EditText editText= findViewById(R.id.edit_text);

        Button button = findViewById(R.id.button);
        button.setOnClickListener( v -> {
            Intent intent = new Intent(getApplication(), SubActivity.class);
            //入力した値
            String str = editText.getText().toString();

            //MainActivityで入力があった場合。
            if(str != ""){
                history.setText("");
                //SubActivityから受け取った値がある場合
                if(res != ""){
                    // SubActivityから受け取った値(頭文字と語尾の取得)
                    strBegin = res.substring(res.length()-1); //頭文字
                    strEnd = str.substring(0,1); //語尾

                    //ログ
                    Log.d("main", strBegin);
                    Log.d("main", strEnd);

                    //頭文字と語尾の不一致または文字列の中に言葉が含まれている場合
                    if(!strBegin.equals(strEnd) || historyList.contains(str)){
                        res="";
                        history.setText(failedMessage);
                        historyList = new ArrayList<String>() {{ add("");}};
                    }else{
                        historyList.add(str);
                        Log.d("main", String.valueOf(historyList));

                        //SubActivityにデータを渡す。putExtra(key, value)
                        intent.putExtra(EXTRA_MESSAGE, str);
                        startActivityForResult( intent, RESULT_SUBACTIVITY );
                    }
                }else{
                    //SubActivityから受け取った値がない場合
                    historyList.add(str);
                    Log.d("main", String.valueOf(historyList));

                    //SubActivityにデータを渡す。putExtra(key, value)
                    intent.putExtra(EXTRA_MESSAGE, str);
                    startActivityForResult( intent, RESULT_SUBACTIVITY );
                }
            }
            //テキストの初期化
            editText.setText("");

        });
    }

    // SubActivity からの返しの結果を受け取る
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY &&
                null != intent) {

            //SubActivityからの値取得
            res = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

            //MainActivityのリストの最後の単語取得
            String listEnd = historyList.get(historyList.size()-1);

            //頭文字と語尾の取得
            strBegin = res.substring(0,1); //頭文字
            strEnd = listEnd.substring(listEnd.length()-1); //語尾

            //ログチェック
            Log.d("sub", res);
            Log.d("sub", String.valueOf(historyList));
            Log.d("sub", listEnd);
            Log.d("sub", strBegin);
            Log.d("sub", strEnd);

            //【頭文字と語尾が不一致】または【既出の言葉】の場合
            if(!strBegin.equals(strEnd) || historyList.contains(res)){
                history.setText(failedMessage);
                res="";
                historyList = new ArrayList<String>() {{ add("");}};
            }else{
                //含まれていない＝SubActivityで入力した値を追加
                Log.d("result", "onActivityResult: 含まれていない");
                historyList.add(res);
                Log.d("res2", String.valueOf(historyList));
            }

        }
    }

    //ライフサイクル
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity","onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity","onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity","onDestroy()");
    }
}