package com.wangjulong.cp5.analysis;

/**
 * 把第一阶段的数据存储到数据库中
 * Created by Administrator on 2016/9/23.
 */

public interface Stage01DatabaseInterface {
    void stage01IntoDatabase(int[][] data);
}
