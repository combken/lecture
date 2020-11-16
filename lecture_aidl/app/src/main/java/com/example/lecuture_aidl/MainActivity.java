package com.example.lecuture_aidl;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.RemoteException;
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
                    Log.d("myDebug","繋げなかったよ");
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

        Intent intent = new Intent(IMyAidlInterface.class.getName());
        bindService(intent.setPackage("com.example.lecuture_aidl"),serviceConnection,BIND_AUTO_CREATE);

    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
        Log.d("myDebug","終了");
    }


    public void onClick(View view){
        Log.d("myDebug", "clicked Button.");
        try {
            int human_number = Integer.parseInt(human.getText().toString());
            int money_number = Integer.parseInt(money.getText().toString());
            int w = service.warikan(human_number, money_number);
            result.setText(Integer.toString(w));
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d("myDebug","失敗");
        }


    }

}
