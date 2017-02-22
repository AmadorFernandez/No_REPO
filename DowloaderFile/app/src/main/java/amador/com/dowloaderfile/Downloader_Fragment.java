package amador.com.dowloaderfile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by usuario on 16/02/17.
 */

public class Downloader_Fragment extends Fragment implements View.OnClickListener {

    private Button button;
    private static final int WRITE_EXTERNAL_STORAGE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dowload, container, false);

        button = (Button)rootView.findViewById(R.id.butonnnn);
        button.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View view) {

        if(checkWritePermission()){

            onDowLoad();
        }
    }

    private void onDowLoad() {

        button.setEnabled(false);
        Intent intent = new Intent(getActivity(), Dowloader.class);
        intent.setData(Uri.parse("https://commonsware.com/Android/Android-1_0-CC.pdf"));
        getActivity().startService(intent);

    }

    private boolean checkWritePermission() {

        final String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permission);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED){

            return true;

        }else if(shouldShowRequestPermissionRationale(permission)){

            Snackbar.make(getActivity().findViewById(R.id.parent), "DAME LOS PERMISOS JOPUTA",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    requestPermissions( new String[]{permission}, WRITE_EXTERNAL_STORAGE);

                }
            }).show();

            return false;

        }else {

            requestPermissions( new String[]{permission}, WRITE_EXTERNAL_STORAGE);
        }

        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == WRITE_EXTERNAL_STORAGE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                onDowLoad();

            }else {

                checkWritePermission();
            }
        }
    }
}
