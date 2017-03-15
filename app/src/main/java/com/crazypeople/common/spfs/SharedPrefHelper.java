package com.crazypeople.common.spfs;

import android.content.Context;
import android.content.SharedPreferences;

import com.crazypeople.MVPframeApplication;

import javax.inject.Singleton;

@Singleton
public class SharedPrefHelper {
	/**
	 * SharedPreferences的名字
	 */
	private static final String SP_FILE_NAME = "APPLICATION_SP";

	private static SharedPreferences sharedPreferences;
	private static SharedPrefHelper sharedPrefHelper;

	public static synchronized SharedPrefHelper getInstance() {
		if (null == sharedPrefHelper) {
			sharedPrefHelper = new SharedPrefHelper();
		}
		return sharedPrefHelper;
	}


	public SharedPrefHelper() {
		sharedPreferences = MVPframeApplication.mContext
				.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	// //////////////////////////////////////////////////////////***手势****///////////////////////////////////////////////////////////

	public void setIsFirst(boolean isFirst) {
		sharedPreferences.edit().putBoolean("isFirst", isFirst).commit();
	}

	public boolean getIsFirst() {
		return sharedPreferences.getBoolean("isFirst", false);
	}

	public void setLoginStatus(boolean loginStatus) {
		sharedPreferences.edit().putBoolean("loginStatus", loginStatus).commit();
	}

	public boolean getLoginStatus() {
		return sharedPreferences.getBoolean("loginStatus", false);
	}
}
