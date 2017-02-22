package amador.com.loopservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class InfinityService extends Service {

    private int cont = 0;

    public InfinityService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "ESTAR SERVICEEEEEEEEEEEEEEEEEEE "+(++cont), Toast.LENGTH_SHORT).show();
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "STOP SERVICEEEEEEEEEEEEEEEEEEE ", Toast.LENGTH_SHORT).show();

    }
}
