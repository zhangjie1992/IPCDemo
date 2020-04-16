package zhangjie1992.github.ipcservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhangjie1992.github.ipcservice.aidl.AidlActivity;
import zhangjie1992.github.ipcservice.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void gotoAIDL(View view){
        Intent intent = new Intent(this, AidlActivity.class);
        startActivity(intent);
    }


}
