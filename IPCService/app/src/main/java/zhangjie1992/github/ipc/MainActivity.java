package zhangjie1992.github.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import zhangjie1992.github.ipc.aidl.AidlActivity;

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
