package br.edu.ifsp.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    private EditText editText;

    private SeekBar seekBar;

    private TextView textView;

    private int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        quantity = 0;

        editText = findViewById(R.id.editText2);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView3);

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
        intent.putExtra("todoitem", editText.getText().toString());
        intent.putExtra("quantity", quantity);
        setResult(RESULT_OK, intent);
        finish();
    }
}
