package amador.com.calls;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> calls = new ArrayList<String>();
    //=============================================================//

    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    private void guardado(){

       // listView = (ListView)findViewById(R.id.listView);

        String[] projection = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER};
        Uri llamadas = CallLog.Calls.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Cursor c = cr.query(llamadas, projection, null, null, null);

        if(c.moveToFirst()){

            Call call = new Call();

            int tipo;
            String tipoLlamada = "";
            String telefono = "";
            int colTipo = c.getColumnIndex(CallLog.Calls.TYPE);
            int colTelefono = c.getColumnIndex(CallLog.Calls.NUMBER);

            do{


                tipo = c.getInt(colTipo);
                call.setNumero(c.getString(colTelefono));

                if(tipo == CallLog.Calls.INCOMING_TYPE){

                    call.setTipo("ENTRADA");

                }else if(tipo == CallLog.Calls.OUTGOING_TYPE){

                    call.setTipo("SALIDA");
                }else if(tipo == CallLog.Calls.MISSED_TYPE){

                    call.setTipo("PERDIDA");
                }

                calls.add(call.toString());

            }while (c.moveToNext());

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, calls);
            listView.setAdapter(adapter);
        }


    }
}
