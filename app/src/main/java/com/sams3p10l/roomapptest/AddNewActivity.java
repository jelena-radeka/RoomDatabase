package com.sams3p10l.roomapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {


    public static final String TITLE ="title";
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        editText=findViewById(R.id.edit_text);
        btn=findViewById(R.id.btn_add);
        btn.setOnClickListener(v -> saveItem());
    }

    private void saveItem() {
        String title = editText.getText().toString();
        if (title.trim().isEmpty()) {
            Toast.makeText(this, "Empty item", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(TITLE, title);
        setResult(RESULT_OK, data);
        finish();
    }
}