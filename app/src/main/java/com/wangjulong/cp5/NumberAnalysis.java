package com.wangjulong.cp5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 号码分析类
 * Created by Administrator on 2016/9/18.
 */

class NumberAnalysis {

    /**
     * 用于存储数据
     * Map 包含的 key :
     * serial(Kjh中的serial),title(Kjh中的title)
     * cause01,cause02,cause03,cause04,cause05(因)
     */
    private static List<Map<String, Integer>> data;

    /**
     * 初始化存储数据的 List
     */
    NumberAnalysis() {
        NumberAnalysis.data = new ArrayList<>();
    }

    /**
     * 入口方法，管理分析流程的方法
     * 本方法调用类的其他方法处理分析数据
     */
    void analysis() {
        // 第一步：计算得到第一阶段的计算结果
        this.s01DataDescToTable();


    }

    /**
     * 1 从数据库获得数据并转化为数组
     *
     */
    private void s01DataDescToTable() {

        // 1 从数据库获得数据并转化为数组 kjhArr(二维数组，行：开奖期数，列：序号，期号，5个号码)
        List<Kjh> kjhList = Kjh.listAll(Kjh.class);
        int[][] kjhArr = new int[kjhList.size()][7];
        for (int i = 0; i < kjhList.size(); i++) {
            kjhArr[i][0] = kjhList.get(i).serial;
            kjhArr[i][1] = kjhList.get(i).title;
            kjhArr[i][2] = kjhList.get(i).n1;
            kjhArr[i][3] = kjhList.get(i).n2;
            kjhArr[i][4] = kjhList.get(i).n3;
            kjhArr[i][5] = kjhList.get(i).n4;
            kjhArr[i][6] = kjhList.get(i).n5;
        }




    }
}
