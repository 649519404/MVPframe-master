package com.crazypeople.common.config;

import android.content.Context;
import android.content.res.AssetManager;

import com.alibaba.fastjson.JSONArray;
import com.crazypeople.common.sugar.RoomType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public final class AppConfig {
	private static List<RoomType> appInfo;
	private AppConfig() {
		
	}
	public static List<RoomType> getAppConfigInfo(Context context) {
		AssetManager am = context.getAssets();
		try {
			InputStream is = am.open("config.json");
			return parse(is);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private static List<RoomType> parse(InputStream is)  {
		ByteArrayOutputStream baos = null;
		List<RoomType> lists= null;
		try {
			baos = new ByteArrayOutputStream();
			int i = -1;
			while ((i = is.read()) != -1) {
                baos.write(i);
            }
			String configString = baos.toString("utf-8");
			System.out.println(configString);
			lists=JSONArray.parseArray(configString,RoomType.class);
			System.out.println(lists.size());
            save(lists);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				baos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

//		save(lists);
		return lists;
	}

	public static void save(List<RoomType> list ){
		for (int i=0;i<list.size();i++) {
			list.get(i).save();
		}
	}

}
