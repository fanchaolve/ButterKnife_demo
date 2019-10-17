package com.fancl.butterknife_sample;



import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fancl.knifelibrary.BindId;
import com.fancl.knifelibrary.ContentView;
import com.fancl.knifelibrary.Onclick;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {



    private  int count;
    @BindId(R.id.tv_hello)
    private TextView tv_hello;



    @Onclick({R.id.tv_hello})
    public void onClick(View view){
        count++;
        Toast.makeText(MainActivity.this,count+"",Toast.LENGTH_LONG).show();
    }



}
