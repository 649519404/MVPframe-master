package com.crazypeople.common.inter;


import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.entity.User;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ConnectService {

    @FormUrlEncoded
    @POST("/tv/app/all")
    Observable<HttpResult<List<DataBean>>> getNewTopList(@FieldMap Map<String, String> params);
    @FormUrlEncoded
    @POST("/tv/app/user/login")
    Observable<HttpResult<User>> login(@FieldMap Map<String, String> params);

//    @FormUrlEncoded
//    @POST("weixin/query")
//    Observable<WeiChatEntity> getWeiChatList(@FieldMap Map<String, String> params);

}
