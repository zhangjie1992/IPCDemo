package zhangjie1992.github.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import zhangjie1992.github.Person;
import zhangjie1992.github.ipc.IBinderPool;
import zhangjie1992.github.ipc.IMathAidl;
import zhangjie1992.github.ipc.ITool;
import zhangjie1992.github.ipc.R;

public class AidlActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    private IBinderPool iBinderPool;
    private IMathAidl iMathAidl;
    private ITool iTool;

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
                    int add = iMathAidl.add(1, 2);
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
                    iMathAidl.addPerson(new Person("leo", 3));
                    List<Person> persons = iMathAidl.getPersonList();
                    Log.e(TAG, persons.toString());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        //第一个参数是服务的包名，第二个是服务对象的全类名
        intent.setComponent(new ComponentName("zhangjie1992.github.ipcservice", "zhangjie1992.github.ipcservice.aidl.AidlDemoService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected: success");
            iBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                iMathAidl = IMathAidl.Stub.asInterface(iBinderPool.queryBinder(1));
                iTool = ITool.Stub.asInterface(iBinderPool.queryBinder(2));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e(TAG, "onServiceDisconnected: success");
            iBinderPool = null;
        }
    };

    private RemoteCallbackList mL = new RemoteCallbackList() {
        @Override
        public boolean register(IInterface callback) {
            return super.register(callback);
        }

        @Override
        public boolean unregister(IInterface callback) {
            return super.unregister(callback);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }


}
