package amador.com.calls;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.NotificationCompat;
import android.telecom.*;
import android.telecom.Call;
import android.telephony.TelephonyManager;

/**
 * Created by usuario on 15/02/17.
 */

public class Call_Broadcast extends BroadcastReceiver {

    private final int CALL_NOTIFICATE = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        if(bundle != null){

            String state = bundle.getString(TelephonyManager.EXTRA_STATE);

            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){

                String number = bundle.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                Intent intent1 = new Intent(context, MainActivity.class);
                intent1.putExtra("number",number);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, CALL_NOTIFICATE, intent1, PendingIntent.FLAG_ONE_SHOT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                        .setContentTitle("El titulo").setContentText("Llamada de: "+number);
                builder.setSmallIcon(android.R.drawable.sym_def_app_icon);

                builder.setDefaults(Notification.DEFAULT_VIBRATE);
                builder.setDefaults(Notification.DEFAULT_LIGHTS);
                //Añadir
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(CALL_NOTIFICATE, builder.build());




            }
        }
    }
}
