package com.example.wordchains;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SubActivity extends AppCompatActivity {

    private EditText editText;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // to get message from MainActivity
        Intent intentMain = getIntent();
        message = intentMain.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.text_view);
        textView.setText(message);

        editText = findViewById(R.id.edit_text);

        // back to MainActivity
        Button button = findViewById(R.id.button);
        button.setOnClickListener( v -> {
            Intent intentSub = new Intent();
            if (editText.getText() != null) {
                String str = message + editText.getText().toString();
                intentSub.putExtra(MainActivity.EXTRA_MESSAGE, str);
            }

            editText.setText("");

            setResult(RESULT_OK, intentSub);

            finish();
        });
    }
}
