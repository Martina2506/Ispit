package com.example.ispit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etapaPuta, vrijemeod,vrijemedo, opis,eText;
    Button spremiButton,pregledButton;
    DatePickerDialog picker;
    Button datum_button;
    TextView tvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etapaPuta = (EditText) findViewById(R.id.etapa_puta_edittext);
        vrijemeod = (EditText) findViewById(R.id.editText_vrijemeod);
        vrijemedo = (EditText) findViewById(R.id.editText_vrijemedo);

        findViewById(R.id.button_spremi).setOnClickListener(this);
        findViewById(R.id.button_pregled).setOnClickListener(this);

        tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        datum_button=(Button)findViewById(R.id.datum_button);
        datum_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvw.setText("Selected Date: "+ eText.getText());
            }
        });
    }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button_spremi:
                    PutModel data=new PutModel();

                    data.etapaPuta = etapaPuta.getText().toString();
                    data.datum = eText.getText().toString();
                    data.vrijemOd=Integer.valueOf(vrijemeod.getText().toString());
                    data.vrijemeDo=Integer.valueOf(vrijemedo.getText().toString());
                    data.opis = opis.getText().toString();
                    data.save(this);
                    finish();
                    Toast.makeText(this, String.format("Spremljeno!"), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_pregled:
                    startActivity(new Intent(this, PregledPutaActivity.class));
                    break;
            }
}
}
