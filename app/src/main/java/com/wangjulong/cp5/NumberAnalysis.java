package com.wangjulong.cp5;

import com.wangjulong.cp5.analysis.LotteryAnalysisStep01Interface;
import com.wangjulong.cp5.analysis.Stage01DatabaseInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 号码分析类
 * Created by Administrator on 2016/9/18.
 */

class NumberAnalysis {

    private LotteryAnalysisStep01Interface lotteryAnalysisStep01Interface;
    private Stage01DatabaseInterface stage01DatabaseInterface;
    private int[][] arr;
    private int baseNumber;
    private int analysisNumber;

    /**
     * 用于存储数据
     * Map 包含的 key :
     * serial(Kjh中的serial),title(Kjh中的title)
     * cause01,cause02,cause03,cause04,cause05(因)
     */
    private static List<Map<String, Integer>> data;

    /**
     * 在构造函数中初始化成员变量
     */
    NumberAnalysis(int[][] arr,int baseNumber,int analysisNumber) {

    }

    /**
     * 入口方法，管理分析流程的方法
     * 本方法调用类的其他方法处理分析数据
     */
    void analysis() {
        // 第一步：计算得到第一阶段的计算结果
        int[][] temp = lotteryAnalysisStep01Interface.generalDataStage01(arr,baseNumber,analysisNumber);

        // 存入数据库
        stage01DatabaseInterface.stage01IntoDatabase(temp);

    }

    /**
     * 1 从数据库获得数据并转化为数组
     *
     */


}
