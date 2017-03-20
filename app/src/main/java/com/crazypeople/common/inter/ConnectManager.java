package com.crazypeople.common.inter;

import com.crazypeople.fuc.main.entity.DataBean;
import com.crazypeople.fuc.main.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by 曲志强 on 2017/2/4.
 */
@Singleton
public class ConnectManager {

    @Inject ConnectService connectService;
    @Inject
     public ConnectManager() {

    }
    public Observable<HttpResult<List<DataBean>>> getAll(String type){
        Map<String,String> map=  new HashMap<String,String>();
        map.put("type",type);
       return connectService.getNewTopList(map);
    }

    public Observable<HttpResult<Map<String,List<DataBean>>>> getAllRoom(){
        Map<String,String> map=  new HashMap<String,String>();

        return connectService.getAllRoom(map);
    }

    public Observable<HttpResult<User>>login(String mobile, String password){
        Map<String,String> map=  new HashMap<String,String>();
        map.put("mobile",mobile);
        map.put("password",password);
        return connectService.login(map);
    }
}
