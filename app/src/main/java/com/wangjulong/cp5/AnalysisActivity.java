package com.wangjulong.cp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);


    }

    /**
     * 分析方法入口程序
     * @param view Default
     */
    public void analysisButton(View view) {
        NumberAnalysis numberAnalysis = new NumberAnalysis();
        numberAnalysis.analysis();
    }
}
