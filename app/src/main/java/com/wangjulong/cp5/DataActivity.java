package com.wangjulong.cp5;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    /**
     * 手动更新界面的开关
     *
     * @param view Placeholder
     */
    public void updateManual(View view) {
        LinearLayout manualLayout = (LinearLayout) findViewById(R.id.manualLinearLayout);
        assert manualLayout != null;
        if (manualLayout.getVisibility() == View.GONE) {
            manualLayout.setVisibility(View.VISIBLE);
        } else {
            manualLayout.setVisibility(View.GONE);
        }
    }

    /**
     * 显示数据按钮
     *
     * @param view Placeholder
     */
    public void dataView(View view) {
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

    /**
     * 手动更新单期开奖号码的实现
     *
     * @param view Placeholder
     */
    public void manualUpdateNumbers(View view) {
        // 获得开奖号码的期数
        Kjh qihao = Kjh.last(Kjh.class);

        int serial = qihao.serial + 1;
        int title = qihao.title + 1;
        EditText editText1 = (EditText) findViewById(R.id.editText1);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        EditText editText3 = (EditText) findViewById(R.id.editText3);
        EditText editText4 = (EditText) findViewById(R.id.editText4);
        EditText editText5 = (EditText) findViewById(R.id.editText5);
        int n1 = Integer.parseInt(String.valueOf(editText1 != null ? editText1.getText() : -1));
        int n2 = Integer.parseInt(String.valueOf(editText2 != null ? editText2.getText() : -1));
        int n3 = Integer.parseInt(String.valueOf(editText3 != null ? editText3.getText() : -1));
        int n4 = Integer.parseInt(String.valueOf(editText4 != null ? editText4.getText() : -1));
        int n5 = Integer.parseInt(String.valueOf(editText5 != null ? editText5.getText() : -1));

        DataInit.addData(serial,title,n1,n2,n3,n4,n5);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.manualLinearLayout);
        assert linearLayout != null;
        linearLayout.setVisibility(View.GONE);
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
