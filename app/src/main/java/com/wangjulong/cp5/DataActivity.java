package com.wangjulong.cp5;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    ListView listView;

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

        //显示数据
        this.dataListView();
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
        //显示数据
        this.dataListView();
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

        DataInit.addData(serial, title, n1, n2, n3, n4, n5);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.manualLinearLayout);

        if (linearLayout != null) {
            linearLayout.setVisibility(View.GONE);
        }

        //显示数据
        this.dataListView();
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
        }
    }

    /**
     * 把开奖号码呈现在 ListView 中
     */
    public void dataListView() {
        listView = (ListView) findViewById(R.id.listView1);

        // 临时列表：存储开奖号码
        List<Kjh> temp1 = Kjh.listAll(Kjh.class);

        // 倒序的开奖号码
        List<Kjh> kjhList = new ArrayList<>();

        // 提供给 ListView 的数据源
//        List<String> stringList = new ArrayList<>();

        // 开奖号码倒序排列
        for (int i = temp1.size() - 1; i >= 0; i--) {
            kjhList.add(temp1.get(i));
        }

        listView.setAdapter(new MyBaseAdapter(this,kjhList));

    }
}
