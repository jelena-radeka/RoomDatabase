package com.sams3p10l.roomapptest.acivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sams3p10l.roomapptest.AppExecutors;
import com.sams3p10l.roomapptest.R;
import com.sams3p10l.roomapptest.db.RoomDB;
import com.sams3p10l.roomapptest.model.ItemData;

public class AddNewActivity extends AppCompatActivity {

    public static final String TITLE = "title";
    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        editText = findViewById(R.id.edit_text);
        btn = findViewById(R.id.btn_add);
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