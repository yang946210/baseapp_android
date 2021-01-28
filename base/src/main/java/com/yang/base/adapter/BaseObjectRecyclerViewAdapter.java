package com.yang.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yang.base.R;

import java.util.ArrayList;
import java.util.List;

/***
 * @desc RecyclerView 适配器，数据类型味道javabean
 * @author yang
 */
public abstract class BaseObjectRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener, BaseAdapter<T>, View.OnLongClickListener {

    /***
     * 上下文
     */
    protected Context context;

    /***
     * 数据集合
     */
    protected List<T> datas;

    /***
     * item点击回调
     */
    protected OnBaseItemClickListener<T> itemClickListener;

    /***
     * children函数
     */
    protected OnBaseItemChildrenClickListener<T> itemChildrenClickListener;

    /***
     * item长按回调
     */
    protected OnBaseItemLongClickListener<T> itemLongClickListener;

    /***
     * 构造函数
     * @param context 上下文
     * @param data 数据项
     * @param itemClickListener 回调函数
     */
    public BaseObjectRecyclerViewAdapter(Context context, List<T> data, @NonNull OnBaseItemClickListener<T> itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.datas = data;
    }


    /***
     * 构造函数
     * @param context 上下文
     * @param data  数据项
     */
    public BaseObjectRecyclerViewAdapter(Context context, List<T> data) {
        this(context, data, null);
    }

    /***
     * 构造函数
     * @param context 上下文
     */
    public BaseObjectRecyclerViewAdapter(Context context) {
        this(context, new ArrayList<T>(), null);
    }



    /***
     * 设置数据
     * @param dataList
     */
    public void setData(List<T> dataList) {
        if (dataList == null) {
            this.datas = new ArrayList<>();
        } else {
            this.datas = dataList;
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        return getItemLayoutResourceId(position, datas.get(position));
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(viewType, parent, false);
        return new BaseViewHolder(itemView, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.setItemPosition(position);
        holder.getItemView().setTag(R.id.base_adapter_tag0, holder);
        if (itemClickListener != null) {
            holder.getItemView().setOnClickListener(this);
        }
        if (itemLongClickListener != null) {
            holder.getItemView().setOnLongClickListener(this);
        }
        onItemViewBind(holder, position, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener == null) {
            return;
        }
        try {
            Object tmp = v.getTag(R.id.base_adapter_tag0);
            Object tag1 = v.getTag(R.id.base_adapter_tag1);
            if (tmp != null && tmp instanceof BaseViewHolder) {
                BaseViewHolder viewHolder = (BaseViewHolder) tmp;
                if (tag1 == null) {
                    T tmpData = getData(viewHolder.getItemPosition());
                    if (tmpData != null) {
                        itemClickListener.onItemViewClick(viewHolder, viewHolder.getItemPosition(), tmpData);
                    }
                } else {
                    int position = Integer.parseInt(tag1.toString());
                    T tmpData = getData(position);
                    if (tmpData != null) {
                        itemChildrenClickListener.onItemChildViewClick(viewHolder, position, tmpData, v);
                    }
                }
            }
        } catch (Throwable e) {
        }
    }


    @Override
    public void addData(T item) {
        this.datas.add(item);
        notifyDataSetChanged();
    }

    /***
     * 添加数据
     * @param data 数据项
     */
    public void addData(List<T> data) {
        if (data == null || data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            this.datas.add(data.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public void removeData(int position) {
        this.datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public T getData(int position) {
        try {
            return datas.get(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setOnItemClickListener(OnBaseItemClickListener<T> listener) {
        this.itemClickListener = listener;
    }

    @Override
    public void setOnItemLongClickListener(OnBaseItemLongClickListener<T> listener) {
        this.itemLongClickListener = listener;
    }

    @Override
    public void setOnItemChildrenClickListener(OnBaseItemChildrenClickListener<T> listener) {
        this.itemChildrenClickListener=listener;
    }

    @Override
    public void addItemChildViewClickListener(BaseViewHolder holder, int childViewId, int position) {
        addItemChildViewClickListener(holder, holder.getView(childViewId), position);
    }

    @Override
    public void addItemChildViewClickListener(BaseViewHolder holder, View childView, int position) {
        if (childView == null) {
            return;
        }
        childView.setTag(R.id.base_adapter_tag1, position);
        childView.setTag(R.id.base_adapter_tag0, holder);
        childView.setOnClickListener(this);
    }

    @Override
    public boolean onLongClick(View v) {
        if (itemLongClickListener == null) {
            return false;
        }
        try {
            Object tmp = v.getTag(R.id.base_adapter_tag0);
            if (tmp != null && tmp instanceof BaseViewHolder) {
                BaseViewHolder viewHolder = (BaseViewHolder) tmp;
                T tmpData = getData(viewHolder.getItemPosition());
                if (tmpData != null) {
                    itemLongClickListener.onItemViewLongClick(viewHolder, viewHolder.getItemPosition(), tmpData);
                }
            }
        } catch (Throwable e) {
        }
        return false;
    }
}
