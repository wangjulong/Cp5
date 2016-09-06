package com.wangjulong.cp5;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
    }

    /**
     * 联网更新数据
     *
     * @param view View
     */
    public void updateNet(View view) {
        // 异步任务连接网络接受网页数据
        MyAsynctask task = new MyAsynctask();
        String strUrl = "http://trend.caipiao.163.com/ln11xuan5/?periodNumber=100";
        task.execute(strUrl);
    }

    public void updateManual(View view) {
    }

    public void dataView(View view) {
    }


    /**
     * 内部类，异步任务处理
     */
    public class MyAsynctask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String url = params[0];
            GetFromOkhttp getNumbers = new GetFromOkhttp();
            try {
                return getNumbers.run(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            // 筛选数据并更新数据库文件
            DataInit.dataSave(s);

            // 显示数据 数据库-->textView
            textView = (TextView) findViewById(R.id.dataViews);
            List<Kjh> one = Kjh.listAll(Kjh.class);
            String t = "";

            for (Kjh temp : one) {
                t = t + temp.serial + " " + temp.title + " " + temp.n1 + " " + temp.n2 + " " + temp.n3 + " " + temp.n4 + " " + temp.n5 + "\n";
            }

            textView.setText(t);
            textView.setMovementMethod(ScrollingMovementMethod.getInstance());

        }
    }
}
