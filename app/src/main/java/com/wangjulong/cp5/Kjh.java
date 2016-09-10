package com.wangjulong.cp5;

import com.orm.SugarRecord;

/**
 * 开奖号码数据库 DAO 类
 * Created by Administrator on 2016/9/6.
 */

class Kjh extends SugarRecord {
    int serial;
    int title;

    int n1;
    int n2;
    int n3;
    int n4;
    int n5;

    Kjh() {
    }

    Kjh(int serial, int title, int n1, int n2, int n3, int n4, int n5) {
        this.serial = serial;
        this.title = title;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.n5 = n5;
    }

}
