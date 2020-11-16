package b1013251.itarch.com.aidlpractice;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.lecuture_aidl.IMyAidlInterface;

import java.util.List;

import static java.lang.System.in;

public class cal  extends Service {

    private IMyAidlInterface.Stub stub = new IMyAidlInterface.Stub() {
        @Override
        public int warikan(int money, int human) throws RemoteException {
            return money / human;
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("myDebug", "called IBinder");
        return stub;
    }
}