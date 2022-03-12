package com.hui.myapplication.frag_record;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hui.myapplication.R;
import com.hui.myapplication.db.TypeBeam;

import java.util.List;

public class TypeBaseAdapter extends BaseAdapter {

    Context context;
    List<TypeBeam>mDatas;

    int selectPos = 0;      //选中位置

    public TypeBaseAdapter(Context context, List<TypeBeam> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //此适配器不考虑复用问题，因为所有的item都显示在界面上，不会因为滑动就消失，所以没有剩余的convertView，所以不用复写
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_recordfrag_gv,parent,false);

        //查找布局当中的控件
        ImageView iv = convertView.findViewById(R.id.item_recordfrag_iv);
        TextView tv = convertView.findViewById(R.id.item_recordfrag_tv);

        //获取指定位置的数据源
        TypeBeam typeBeam = mDatas.get(position);
        tv.setText(typeBeam.getTypename());

        //判断当前位置是否为选中位置，如果是选中位置，就设置为带颜色的图片，否则为晦涩图片
        if (selectPos == position) {
            iv.setImageResource(typeBeam.getSimageId());
        }else {
            iv.setImageResource(typeBeam.getImageId());
        }

        return convertView;
    }
}
