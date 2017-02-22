package amador.com.bounceservice;

import android.app.Service;
import android.content.Intent;

import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoundService extends Service {

    private IBinder mBinder;
    private Chronometer chronometer;
    private String TAG = "boundservice";

    public BoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("TAG", "ON BIND");
        chronometer.start();
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("TAG", "ON UNBIND");
        chronometer.stop();
        return super.onUnbind(intent);

    }

    public String getTimeStamp(){

        long elapset = SystemClock.elapsedRealtime() - chronometer.getBase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        return simpleDateFormat.format(new Date(elapset));

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d("TAG", "ON REBIND");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "ON CREATE");
        chronometer = new Chronometer(this);
        mBinder = new  MyBinder();
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    public class MyBinder extends Binder{

        public BoundService getService(){

            return BoundService.this;
        }
    }
}
