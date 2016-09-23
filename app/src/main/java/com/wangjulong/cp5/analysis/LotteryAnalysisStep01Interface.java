package com.wangjulong.cp5.analysis;

/**
 * 分析步骤接口
 * Created by Administrator on 2016/9/23.
 */

public interface LotteryAnalysisStep01Interface {

    /**
     * 根据开奖号码计算得到第一阶段的数据：
     * 在基础号码中分别查找上一期的开奖号码，统计这5个号码的下一期开奖号码
     * 按照降序排序统计得到的数据
     *
     * @param arr 所有的数据
     * @param baseNumber 基础号码的期数
     * @param analysisNumber 需要分析的期数
     * @return 整形数组，行数为 analysisNumber
     */
    int[][] generalDataStage01(int[][] arr,int baseNumber,int analysisNumber);
}
