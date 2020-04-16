package zhangjie1992.github.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import zhangjie1992.github.Person;
import zhangjie1992.github.ipc.ILeoAidl;
import zhangjie1992.github.ipc.R;

public class AidlActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private ILeoAidl iLeoAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        initView();
        bindService();
    }

    private void initView() {
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int add = iLeoAidl.add(1, 2);
                    Log.e(TAG, "add:" + add);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        findViewById(R.id.btn_add_person).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iLeoAidl.addPerson(new Person("leo", 3));
                    List<Person> persons = iLeoAidl.getPersonList();
                    Log.e(TAG, persons.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("zhangjie1992.github.ipcservice", "zhangjie1992.github.ipc.aidl.LeoAidlService"));
        //todo 这里整错了，全类名啊
        intent.setComponent(new ComponentName("zhangjie1992.github.ipcservice", "zhangjie1992.github.ipcservice.aidl.AidlDemoService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: success");
            iLeoAidl = ILeoAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: success");
            iLeoAidl = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }


}
