package com.crazypeople.fuc.main.fragment;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.Glide;
import com.crazypeople.fuc.main.entity.DataBean;

import java.util.List;

public class MinePagerAdapter extends PagerAdapter {
	private Context context;
	int flag;
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	private List<DataBean> homePics;

	public List<DataBean> getHomePics() {
		return homePics;
	}

	public void setHomePics(List<DataBean> homePics) {
		this.homePics = homePics;
	}

	public MinePagerAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		int count;

		return homePics == null ? 0 : homePics.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		final DataBean homePic = homePics.get(position);
		ImageView imageView = new ImageView(context);
		imageView.setScaleType(ScaleType.CENTER_CROP);
		Glide.with(context).load(homePic.getImg()).into(imageView);
		container.addView(imageView);
		return imageView;
	}

}
