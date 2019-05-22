package br.edu.ifsp.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import static br.edu.ifsp.todolist.MainActivity.RESULT_EDITING;

public class Main4Activity extends AppCompatActivity {

    private EditText editText;

    private SeekBar seekBar;

    private TextView textView;

    private int quantity;

    private int position;

    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        quantity = getIntent().getIntExtra("quantity", 0);

        editText = findViewById(R.id.editText2);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView3);

        isEditing = getIntent().getBooleanExtra("isEditing", false);
        position = getIntent().getIntExtra("position", -1);
        String elementName = getIntent().getStringExtra("elementName");

        if(position != -1 && elementName != null) {
            editText.setText(elementName);
            seekBar.setProgress(quantity);
            textView.setText("Quantity = " + quantity);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText("Quantity = " + i);
                quantity = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void returnThirdActitivy(View view) {
        Intent intent = new Intent(this, Main3Activity.class);
        intent.putExtra("position", position);
        intent.putExtra("todoitem", editText.getText().toString());
        intent.putExtra("quantity", quantity);
        setResult(isEditing? RESULT_EDITING: RESULT_OK, intent);
        finish();
    }
}
