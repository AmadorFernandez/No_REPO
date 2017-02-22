package amador.com.baterycontrol;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by usuario on 13/02/17.
 */

public class Fragment_Batery extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_batery, container, false);
        progressBar = (ProgressBar)rootView.findViewById(R.id.progress);
        textView = (TextView)rootView.findViewById(R.id.txvNose);
        imageView = (ImageView)rootView.findViewById(R.id.imageIO);


        return rootView;
    }

    //Registrar el BroadReceiber que no se lo que es


    @Override
    public void onResume() {
        super.onResume();

        //Crear el intenFilter para el action
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

        //Registrar el BroadCastReceiber
        getActivity().registerReceiver(broadcastReceiver, intentFilter);


    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    /**
     * Este depende del ciclo de vida de la Activity
     *
     *
     * */
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            //Lee la informacion que llega del Inten: Level, status
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float battPct = level/(float)scale;

            progressBar.setProgress((int) battPct);

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            //El estado en el que esta la bateria:

            switch (status){

                case BatteryManager.BATTERY_STATUS_CHARGING:
                    imageView.setImageResource(R.drawable.charging);
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    imageView.setImageResource(R.drawable.full);
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    imageView.setImageResource(R.drawable.unplugged);
                    break;

            }

        }
    };
}
