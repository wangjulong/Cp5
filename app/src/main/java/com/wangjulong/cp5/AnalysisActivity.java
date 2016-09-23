package com.wangjulong.cp5;

import android.os.Bundle;
import android.view.View;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.Dao;
import com.wangjulong.cp5.db.DatabaseHelper;
import com.wangjulong.cp5.db.Lottery;

import java.sql.SQLException;
import java.util.List;

public class AnalysisActivity extends OrmLiteBaseActivity<DatabaseHelper> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);


    }

    /**
     * 分析方法入口程序
     *
     * @param view Default
     */
    public void analysisButton(View view) throws SQLException {
        // 准备数据
        Dao<Lottery, Integer> lotteryIntegerDao = getHelper().getDao();
        List<Lottery> lotteries = lotteryIntegerDao.queryForAll();
        int[][] allData = new int[lotteries.size()][7];

        for (int i = 0; i < lotteries.size(); i++) {
            allData[i][0] = lotteries.get(i).getSerial();
            allData[i][1] = lotteries.get(i).getTitle();
            allData[i][2] = lotteries.get(i).getN1();
            allData[i][3] = lotteries.get(i).getN2();
            allData[i][4] = lotteries.get(i).getN3();
            allData[i][5] = lotteries.get(i).getN4();
            allData[i][6] = lotteries.get(i).getN5();

            System.out.print(allData[i][0] + " ");
            System.out.print(allData[i][1] + " ");
            System.out.print(allData[i][2] + " ");
            System.out.print(allData[i][3] + " ");
            System.out.print(allData[i][4] + " ");
            System.out.print(allData[i][5] + " ");
            System.out.println(allData[i][6]);
        }


//        NumberAnalysis numberAnalysis = new NumberAnalysis(allData,10,80);
//        numberAnalysis.analysis();
    }
}
