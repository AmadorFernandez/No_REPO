package amador.com.bounceservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean mService;
    private BoundService boundService;
    private Button buttonStart, buttonStop;
    private TextView textView;
    private ServiceConnection serviceConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            boundService = ((BoundService.MyBinder)iBinder).getService();
            mService = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            mService = false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();

        if(mService){

            unbindService(serviceConnection);
            mService = false;
        }

        Intent intent = new Intent(MainActivity.this, BoundService.class);
        stopService(intent);
        buttonStop.setEnabled(false);
        textView.setText("");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
      //  stopService(intent);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = (Button)findViewById(R.id.button);
        buttonStop = (Button)findViewById(R.id.button2);
        textView = (TextView)findViewById(R.id.textView);
        buttonStop.setEnabled(false);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView.setText(boundService.getTimeStamp());
                buttonStop.setEnabled(true);
            }
        });


        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mService){

                    unbindService(serviceConnection);
                    mService = false;
                }

                Intent intent = new Intent(MainActivity.this, BoundService.class);
                stopService(intent);
                buttonStop.setEnabled(false);
                textView.setText("");
            }
        });


    }
}
