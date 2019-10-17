package com.fancl.butterknife_sample;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fancl.knifelibrary.InjectMangner;


public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InjectMangner.init(this);
    }
}
