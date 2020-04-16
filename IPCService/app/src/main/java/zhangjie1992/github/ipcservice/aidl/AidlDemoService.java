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
import zhangjie1992.github.ipc.ILeoAidl;

public class AidlDemoService extends Service {

    private List<Person> persons;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        Log.e("AidlDemoService", "success onBind");
        return iBinder;
    }

    private IBinder iBinder = new ILeoAidl.Stub() {
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
            return a + b;
        }

    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AidlDemoService", "onCreate: success");
    }

}



