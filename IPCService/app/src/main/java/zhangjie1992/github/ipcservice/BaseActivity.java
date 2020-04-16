package zhangjie1992.github.ipcservice;

import android.app.Activity;
import android.view.View;

import androidx.annotation.StringRes;

public abstract class BaseActivity extends Activity {



    public void showInfo(View view){
        String info = getResources().getString(getInfo());
//        InfoActivity.show(this,info);
    }


    public abstract  @StringRes
    int getInfo();


}
