package zhangjie1992.github.ipcservice.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import zhangjie1992.github.Person;
import zhangjie1992.github.ipc.IBinderPool;
import zhangjie1992.github.ipc.IMathAidl;
import zhangjie1992.github.ipc.ITool;

public class AidlDemoService extends Service {

    private List<Person> persons;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        Log.e("AidlDemoService", "success onBind");
        return iBinder;
    }

    private IBinder iBinder = new IBinderPool.Stub() {
        int BINDER_CODE_MATH= 1;
        int BINDER_CODE_Tool= 2;
        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            if (binderCode == 1) {
                return iMathBinder;
            } else if (binderCode == 2) {
                return iToolBinder;
            }

            return null;
        }
    };

    private IBinder iMathBinder = new IMathAidl.Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            persons.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return persons;
        }

        @Override
        public int add(int a, int b) throws RemoteException {
            String name = Thread.currentThread().getName();
            Log.e("AidlDemoService", "add thread:" + name);
            return a + b;
        }
    };


    private IBinder iToolBinder = new ITool.Stub() {
        @Override
        public boolean add(int a) throws RemoteException {
            return false;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AidlDemoService", "onCreate: success");
    }

}



