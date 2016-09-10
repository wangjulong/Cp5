package com.wangjulong.cp5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 点击按钮跳转到 DataActivity 处理
     * @param view Default
     */
    public void lotteryData(View view) {
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    /**
     * 分析按钮 跳转到分析 Activity
     * @param view Default
     */
    public void dataAnalysisButton(View view) {

        startActivity(new Intent(this,AnalysisActivity.class));
    }
}
