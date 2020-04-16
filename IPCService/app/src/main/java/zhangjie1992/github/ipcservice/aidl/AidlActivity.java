package zhangjie1992.github.ipcservice.aidl;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import zhangjie1992.github.ipcservice.R;

public class AidlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);

        startService(new Intent(this, AidlDemoService.class));
    }


}
