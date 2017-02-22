package amador.com.dowloaderfile;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by usuario on 16/02/17.
 */

public class DownLoadReceiber extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context, "Descargado", Toast.LENGTH_LONG).show();

    }
}
