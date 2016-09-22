package com.wangjulong.cp5;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import com.wangjulong.cp5.db.DatabaseHelper;
import com.wangjulong.cp5.db.Lottery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends OrmLiteBaseActivity<DatabaseHelper> {
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
    public void updateNet(View view) throws SQLException {
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
    public void dataView(View view) throws SQLException {
        //显示数据
        this.dataListView();
    }

    /**
     * 手动更新单期开奖号码的实现
     *
     * @param view Placeholder
     */
    public void manualUpdateNumbers(View view) throws SQLException {

        Dao<Lottery, Integer> lotteryDao = getHelper().getDao();

        // 获得开奖号码的期数


        int serial = lotteryDao.queryForAll().size() + 1;
        int title = lotteryDao.queryForId(serial - 1).getTitle() + 1;
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

        Lottery lottery = new Lottery(serial, title, n1, n2, n3, n4, n5);
        lotteryDao.create(lottery);

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
            Dao<Lottery, Integer> lotteryDao = null;
            try {
                lotteryDao = getHelper().getDao();

                // 新建数组，利用源代码中的特别字符分割字符串，生成字符串数组
                String[] arr0 = s.split("data-period=\"");

                // 临时变量
                int s0, n1, n2, n3, n4, n5;

                // 清空数据库内容
                TableUtils.clearTable(getConnectionSource(), Lottery.class);
//                lotteryDao.queryForAll().clear();

                // 循环字符串数组

                for (String abc : arr0) {
                    int serial = lotteryDao.queryForAll().size() + 1;

                    // 正则表达式匹配开始的8个字符是否是数字，更新数据库
                    if (abc.substring(0, 8).matches("\\d{8}")) {
                        s0 = Integer.parseInt(abc.substring(0, 8));
                        n1 = Integer.parseInt(abc.substring(22, 24));
                        n2 = Integer.parseInt(abc.substring(25, 27));
                        n3 = Integer.parseInt(abc.substring(28, 30));
                        n4 = Integer.parseInt(abc.substring(31, 33));
                        n5 = Integer.parseInt(abc.substring(34, 36));

                        Lottery lottery = new Lottery(serial, s0, n1, n2, n3, n4, n5);
                        lotteryDao.create(lottery);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把开奖号码呈现在 ListView 中
     */
    public void dataListView() throws SQLException {
        listView = (ListView) findViewById(R.id.listView1);

        // 临时列表：存储开奖号码

        Dao<Lottery,Integer> lotteryIntegerDao = getHelper().getDao();

        List<Lottery> temp1 = lotteryIntegerDao.queryForAll();

        // 倒序的开奖号码
        List<Lottery> kjhList = new ArrayList<>();
        for (int i = temp1.size() - 1; i >= 0; i--) {
            kjhList.add(temp1.get(i));
        }

        listView.setAdapter(new MyBaseAdapter(this, kjhList));
    }
}
