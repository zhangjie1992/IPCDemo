package zhangjie1992.github.ipc.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import zhangjie1992.github.bean.Person;
import zhangjie1992.github.ipc.ILeoAidl;

public class LeoAidlService extends Service {

    private ArrayList<Person> persons;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        Log.e("LeoAidlService", "success onBind");
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
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("LeoAidlService", "onCreate: success");
    }
}



