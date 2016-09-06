package com.wangjulong.cp5;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Okhttp 访问网络，借鉴官方示例代码修改
 * Created by Administrator on 2016/9/6.
 */

class  GetFromOkhttp{
    private OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return response.body().string();
        }
        return null;
    }
}

