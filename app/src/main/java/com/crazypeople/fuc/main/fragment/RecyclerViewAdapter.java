package com.crazypeople.fuc.main.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.crazypeople.R;


/**
 * Author       : yanbo
 * Date         : 2015-06-02
 * Time         : 09:47
 * Description  :
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private MyItemClickListener myItemClickListener;

    public void setMyItemClickListener(MyItemClickListener myItemClickListener){
        this.myItemClickListener=myItemClickListener;
    }

    public RecyclerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.mTextView.setBackgroundColor(mContext.getResources().getColor(colors[position%(colors.length)]));
//        holder.mTextView.setText(position + "");

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;

        public ViewHolder(final View view, final MyItemClickListener myItemClickListener) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.name);
           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if(null!=myItemClickListener){
                       myItemClickListener.onItemClick(view,getLayoutPosition());
                   }
               }
           });
        }
    }
    public interface MyItemClickListener {
        public void onItemClick(View view, int postion);
    }
}
