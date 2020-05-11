package com.example.exercicioihc2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private EditText editText1;
    private Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        sendButton = findViewById(R.id.buttonSend);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String textContent = editText1.getText().toString();
                openActivity2(textContent);

            }

        });

    }

    public void openActivity2(String sendText) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("Value", sendText);

        startActivity(intent);

    }
}
