package com.example.sunhailong01.demo;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sunhailong01.demo.autofillframework.app.AutoActivity;
import com.example.sunhailong01.demo.notificationchannels.NotificationActivity;
import com.example.sunhailong01.demo.pictureinpicture.PiPActivity;

import org.w3c.dom.Text;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //要确保API Level 大于等于 25才可以创建动态shortcut，否则会报异常。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            initDynamicShortcuts();
        }
        Button button = (Button) findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        Button pipButton = (Button) findViewById(R.id.pip);
        pipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PiPActivity.class);
                startActivity(intent);
            }
        });
        Button autoButton = (Button) findViewById(R.id.auto);
        autoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AutoActivity.class);
                startActivity(intent);
            }
        });

        Button kotlinButton = (Button) findViewById(R.id.kotlin_button);
        kotlinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });



    }



    private void initDynamicShortcuts() {
        //①、创建动态快捷方式的第一步，创建ShortcutManager

        ShortcutManager scManager = getSystemService(ShortcutManager.class);


        //②、构建动态快捷方式的详细信息

        ShortcutInfo scInfoOne  = new ShortcutInfo.Builder(this, "动态快捷方式1")
                .setShortLabel("网页调起")
                .setLongLabel("打开百度主页").setDisabledMessage("1234")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher))
                .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com")))
                .build();


        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW);

        mapIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                & Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        mapIntent.setClassName("com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity");

        ShortcutInfo scInfoTwo = new ShortcutInfo.Builder(this, "动态快捷方式2")
                .setShortLabel("调起应用")
                .setLongLabel("打开google地图")
                .setIcon(Icon.createWithResource(this, R.mipmap.ic_launcher_round))
                .setIntents(new Intent[]{
                        new Intent(Intent.ACTION_MAIN, Uri.EMPTY, this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK),//加该FLAG的目的是让MainActivity作为根activity，清空已有的任务
                        mapIntent
                })
                .build();
        //③、为ShortcutManager设置动态快捷方式集合

        scManager.setDynamicShortcuts(Arrays.asList(scInfoOne, scInfoTwo));

        //如果想为两个动态快捷方式进行排序，可执行下面的代码
        ShortcutInfo dynamicWebShortcut = new ShortcutInfo.Builder(this, "动态快捷方式1")
                .setRank(0)
                .build();
        ShortcutInfo dynamicActivityShortcut = new ShortcutInfo.Builder(this, "动态快捷方式2")
                .setRank(1)
                .build();
        //④、更新快捷方式集合
        scManager.updateShortcuts(Arrays.asList(dynamicWebShortcut, dynamicActivityShortcut));
    }
}
