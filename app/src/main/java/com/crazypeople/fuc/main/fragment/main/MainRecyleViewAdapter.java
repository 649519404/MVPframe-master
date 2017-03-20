package com.crazypeople.fuc.main.fragment.main;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.crazypeople.R;
import com.crazypeople.common.base.baseAdapter.RecycleViewAdapter;
import com.crazypeople.common.base.baseHolder.BaseViewHolder;
import com.crazypeople.fuc.main.entity.DataBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曲志强 on 2017/3/16.
 */

public class MainRecyleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_1 = 0xff01;
    public static final int TYPE_2 = 0xff02;
    public static final int TYPE_3 = 0xff03;
    public static final int TYPE_4 = 0xff04;
    public static final int TYPE_MAIN = 0xffff;
    private Context context;
    private Map<String, List<DataBean>> lists;


    public MainRecyleViewAdapter(Context context) {

        this.context = context;
        lists=new HashMap<String, List<DataBean>>();

    }

    public void setMap(Map<String, List<DataBean>> lists){
        this.lists = lists;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return TYPE_1;
        }else if (position == 1){
            return TYPE_2;
        }else if (position == 2){
            return TYPE_3;
        }else if (position == 3){
            return TYPE_4;
        }else {
            return TYPE_MAIN;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_1:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false), 0);
//            case :
//                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false));
        case TYPE_2:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false), 1);
            case TYPE_3:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false),2);
            case TYPE_4:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false),3);

            default:
                return new MyViewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_hot_person_layout, parent, false),3);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       if(holder instanceof  MyViewHolder2) {
           MyViewHolder2 myViewHolder2 = (MyViewHolder2) holder;

           if (myViewHolder2.type == 0) {
            RecycleViewAdapter mAdapter = new RecycleViewAdapter<DataBean>(R.layout.main_hot_person_item, lists.get("0")) {

                @Override
                protected void convert(BaseViewHolder holder, DataBean dataBeen) {
                    holder.setText(R.id.tx_mina_type_name, dataBeen.getNickName());
                    holder.setImageUrl(R.id.img_main_hot,context,dataBeen.getImg());
                }
            };
               RecyclerView xRecyclerView = myViewHolder2.item_recyc_type2;
               GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 5);
//           linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
               xRecyclerView.setLayoutManager(linearLayoutManager);
//           xRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
               xRecyclerView.setAdapter(mAdapter);

           } else if (myViewHolder2.type == 1) {
               RecycleViewAdapter mAdapter = new RecycleViewAdapter<DataBean>(R.layout.list_item, lists.get("1")) {

                   @Override
                   protected void convert(BaseViewHolder holder, DataBean dataBeen) {
                       holder.setText(R.id.name, dataBeen.getTitle());
                       holder.setText(R.id.nick_name,dataBeen.getNickName());
                       holder.setImageUrl(R.id.picture,context,dataBeen.getImg());
                   }
               };
               RecyclerView xRecyclerView = myViewHolder2.item_recyc_type2;
               myViewHolder2.title.setText("热门推荐");
               GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 2);
//           linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
               xRecyclerView.setLayoutManager(linearLayoutManager);
//           xRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
               xRecyclerView.setAdapter(mAdapter);
           }
           else if (myViewHolder2.type == 2) {
               RecycleViewAdapter mAdapter = new RecycleViewAdapter<DataBean>(R.layout.list_item, lists.get("2")) {

                   @Override
                   protected void convert(BaseViewHolder holder, DataBean dataBeen) {
                       holder.setText(R.id.name, dataBeen.getTitle());
                       holder.setText(R.id.nick_name,dataBeen.getNickName());
                       holder.setImageUrl(R.id.picture,context,dataBeen.getImg());
                   }
               };
               RecyclerView xRecyclerView = myViewHolder2.item_recyc_type2;
               myViewHolder2.title.setText("Java课堂");
               myViewHolder2.imageView.setImageResource(R.mipmap.java);
               GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 2);
//           linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
               xRecyclerView.setLayoutManager(linearLayoutManager);
//           xRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
               xRecyclerView.setAdapter(mAdapter);
           }else if (myViewHolder2.type == 3) {
               RecycleViewAdapter mAdapter = new RecycleViewAdapter<DataBean>(R.layout.list_item, lists.get("3")) {

                   @Override
                   protected void convert(BaseViewHolder holder, DataBean dataBeen) {
                       holder.setText(R.id.name, dataBeen.getTitle());
                       holder.setText(R.id.nick_name,dataBeen.getNickName());
                       holder.setImageUrl(R.id.picture,context,dataBeen.getImg());
                   }
               };
               RecyclerView xRecyclerView = myViewHolder2.item_recyc_type2;
               myViewHolder2.title.setText("Android课堂");
               myViewHolder2.imageView.setImageResource(R.mipmap.android);
               GridLayoutManager linearLayoutManager = new GridLayoutManager(context, 2);
//           linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
               xRecyclerView.setLayoutManager(linearLayoutManager);
//           xRecyclerView.addItemDecoration(new SpaceItemDecoration(10));
               xRecyclerView.setAdapter(mAdapter);
           }
       }
    }

    @Override
    public int getItemCount() {

        return lists.size()<=4?lists.size():4;
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {
//        @Bind(R.id.rv_main_hot)
        RecyclerView item_recyc_type2;
//        @Bind(R.id.tx_main_title)
        TextView title;
        ImageView imageView;
        int type;

        public MyViewHolder2(final View itemView, int type) {
            super(itemView);
            this.type = type;
            item_recyc_type2= (RecyclerView) itemView.findViewById(R.id.rv_main_hot);

            title= (TextView) itemView.findViewById(R.id.tx_main_title);
            imageView= (ImageView) itemView.findViewById(R.id.img_main_title);
        }
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        public RecyclerView item_recyc_type2;

        public MyViewHolder3(final View itemView) {
            super(itemView);
            item_recyc_type2 = (RecyclerView) itemView.findViewById(R.id.rv_main_hot);
        }
    }

}
