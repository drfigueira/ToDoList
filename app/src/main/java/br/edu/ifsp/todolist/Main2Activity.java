package br.edu.ifsp.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import static br.edu.ifsp.todolist.MainActivity.RESULT_EDITING;

public class Main2Activity extends AppCompatActivity {

    private EditText editText;

    private int position;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.editText);

        isEditing = getIntent().getBooleanExtra("isEditing", false);
        position = getIntent().getIntExtra("position", -1);
        String elementName = getIntent().getStringExtra("elementName");

        if(position != -1 && elementName != null) {
            editText.setText(elementName);
        }

    }

    public void returnMainActitivy(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("todo", editText.getText().toString());
        setResult(isEditing? RESULT_EDITING: RESULT_OK, intent);
        finish();
    }
}
