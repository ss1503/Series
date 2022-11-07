package com.example.series;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView serieLv;
    TextView x1Txt, dTxt, nTxt, SnTxt;

    Intent gi;

    boolean type;
    double mul, firstValue;
    double valuePlace, sum;

    String[] serie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        gi = getIntent();

        serieLv = (ListView) findViewById(R.id.serieLv);
        x1Txt = (TextView) findViewById(R.id.x1Txt);
        dTxt = (TextView) findViewById(R.id.dTxt);
        nTxt = (TextView) findViewById(R.id.nTxt);
        SnTxt = (TextView) findViewById(R.id.SnTxt);

        mul = gi.getDoubleExtra("mul", -19);
        type = gi.getBooleanExtra("type", false);
        firstValue = gi.getDoubleExtra("firstValue", -19);

         serie = new String[20];
         if(!type)
         {
             for(int i = 0; i < 20; i++)
             {
                 double num = firstValue + (i) * mul;
                 String str = String.valueOf(num);

                 if(str.contains("E"))
                 {
                     NumberFormat numberFormat = new DecimalFormat();
                     numberFormat = new DecimalFormat("0.####E0");
                     serie[i] = numberFormat.format(num);
                 }
                 else
                 {
                     serie[i] = String.valueOf(num);
                 }
             }
         }
         else
         {
             for(int i = 0; i < 20; i++)
             {
                 double num = firstValue * Math.pow(mul, i);
                 String str = String.valueOf(num);

                 if(str.contains("E"))
                 {
                     NumberFormat numberFormat = new DecimalFormat();
                     numberFormat = new DecimalFormat("0.####E0");
                     serie[i] = numberFormat.format(num);
                 }
                 else
                 {
                     serie[i] = String.valueOf(num);
                 }

             }
         }

         serieLv.setOnItemClickListener(this);
         serieLv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

         ArrayAdapter<String> adp = new ArrayAdapter<String>(this,
                 androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, serie);

         serieLv.setAdapter(adp);

         x1Txt.setText("X1 = " + firstValue);
         dTxt.setText("d = " + mul);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
    {
        valuePlace = i + 1;
        nTxt.setText("n = " + (int)valuePlace);

        if(!type)
        {
            sum = (valuePlace * (firstValue + Double.parseDouble(serie[i]))) / 2;
        }
        else
        {
            sum = (firstValue * (Math.pow(mul, valuePlace) - 1)) / (mul - 1);
        }

        SnTxt.setText("Sn = " + sum);
    }

    public void goBack(View view)
    {
        finish();
    }
}