package com.example.lecuture_aidl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private IMyAidlInterface service;
    private EditText money;
    private EditText human ;
    private TextView result;
    private Button calc;
    private ServiceConnection serviceConnection =
            new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder ibinder) {
                    service = IMyAidlInterface.Stub.asInterface(ibinder);
                    Log.d("myDebug", "Service has connected.");
                    calc.setEnabled(true);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    service = null;
                }
            };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("myDebug", "Launched.");

        calc = (Button) findViewById(R.id.calc);
        calc.setOnClickListener(this);

        human = (EditText) findViewById(R.id.human);
        money = (EditText) findViewById(R.id.money);
        result = (TextView) findViewById(R.id.result);

    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    public void onClick(View view){
        Log.d("myDebug", "clicked Button.");
        int human_number = Integer.valueOf(human.getText().toString());
        int money_number = Integer.valueOf(money.getText().toString());
        int wari = money_number / human_number;
        result.setText(Integer.toString(wari));

    }
}
