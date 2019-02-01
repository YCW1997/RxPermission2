package com.yuan.rxpermission2;

import android.Manifest;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;


public class MainActivity extends AppCompatActivity {

    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView=(ImageView)findViewById(R.id.imageView);

    }

    //读写权限是两个权限，所以要申请两个
    public void myclick(View view){
        new RxPermissions(this)
                .request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            File file=new File(Environment.getExternalStorageDirectory(),"timg.jpg");
                            Glide.with(MainActivity.this).load(file).into(imageView);
                        } else {
                            Log.i("permissions", Manifest.permission.READ_EXTERNAL_STORAGE + "：" + "获取失败");
                        }
                    }
                });
    }
}
