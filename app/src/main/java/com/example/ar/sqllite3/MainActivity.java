package com.example.ar.sqllite3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText id, name, family;
    TextView t1;
    myDbAdaptor helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.edittext1);
        name = (EditText) findViewById(R.id.edittext2);
        family = (EditText) findViewById(R.id.edittext3);
        t1 = (TextView) findViewById(R.id.t11);
        helper = new myDbAdaptor(this);

    }

    public void insert(View view) {
        int t1 = Integer.parseInt(id.getText().toString());
        String t2 = name.getText().toString();
        String t3 = family.getText().toString();
        if (t2.isEmpty() || t3.isEmpty() || t1 == 0) {
            Message.message(getApplicationContext(), "Enter Name and Family and id");
        } else {
            String idd = helper.inserData(t1, t2, t3);
            if (idd.isEmpty()) {
                Message.message(getApplicationContext(), "Insertion Succesful" + idd);
                name.setText("");
                family.setText("");
                id.setText("");
            } else {
                Message.message(getApplicationContext(), "Insertion Unsuccessful");
                name.setText("");
                family.setText("");
                id.setText("");
            }
        }
    }

    public void show(View view) {   /*try {
        String data = helper.getData();
        Message.message(this, data);
    }catch (Exception e){Message.message(this,""+e);}

        Message.message(this, data);*/
        try {
            String data = helper.getData();
            t1.setText(this+data);
        } catch (Exception e) {t1.setText(this+"");
        }
    }
}
