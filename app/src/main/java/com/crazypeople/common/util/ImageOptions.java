package com.crazypeople.common.util;

import android.graphics.Bitmap;

import com.crazypeople.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ImageOptions {
	public static DisplayImageOptions getCollectPictureOptions() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.mipmap.defaultpic)
				.showImageForEmptyUri(R.mipmap.defaultpic)
				.showImageOnFail(R.mipmap.defaultpic).cacheInMemory(true)
				.cacheOnDisc(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		return options;
	}

	
	

}
