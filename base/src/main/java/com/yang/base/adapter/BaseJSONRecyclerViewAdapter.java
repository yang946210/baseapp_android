package com.yang.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JsonArray;
import com.alibaba.fastjson.JSONObject;
import com.yang.base.R;


/***
 * @desc Recycler的适配器, 数据类型为json
 * @author yang
 */
public abstract class BaseJSONRecyclerViewAdapter extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener, BaseAdapter<JSONObject>, View.OnLongClickListener {

    /***
     * 上下文
     */
    protected Context context;

    /***
     * 数据集合
     */
    protected JsonArray datas;
    /***
     * item函数
     */
    protected OnBaseItemClickListener<JSONObject> itemClickListener;

    /***
     * children函数
     */
    protected OnBaseItemChildrenClickListener<JSONObject> itemChildrenClickListener;

    /***
     * 长按监听器
     */
    protected OnBaseItemLongClickListener<JSONObject> itemLongClickListener;

    /***
     * 构造函数
     * @param context 上下文
     * @param data 数据项
     * @param itemClickListener 回调函数
     */
    public BaseJSONRecyclerViewAdapter(Context context, JsonArray data, @NonNull OnBaseItemClickListener<JSONObject> itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        datas = data;
    }


    /***
     * 构造函数
     * @param context 上下文
     * @param data  数据项
     */
    public BaseJSONRecyclerViewAdapter(Context context, JsonArray data) {
        this(context, data, null);
    }

    /***
     * 构造函数
     * @param context 上下文
     */
    public BaseJSONRecyclerViewAdapter(Context context) {
        this(context, new JsonArray(), null);
    }


    /***
     * 设置数据
     * @param dataList
     */
    public void setData(JsonArray dataList) {
        if (dataList == null) {
            this.datas = new JsonArray();
        } else {
            this.datas = dataList;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutResourceId(position, getData(position));
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
        onItemViewBind(holder, position, getData(position));
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
                    JSONObject tmpData = getData(viewHolder.getItemPosition());
                    if (tmpData != null) {
                        itemClickListener.onItemViewClick(viewHolder, viewHolder.getItemPosition(), tmpData);
                    }
                } else {
                    int position = Integer.parseInt(tag1.toString());
                    JSONObject tmpData = getData(position);
                    if (tmpData != null) {
                        itemChildrenClickListener.onItemChildViewClick(viewHolder, position, tmpData, v);
                    }
                }
            }
        } catch (Throwable e) {
        }
    }


    @Override
    public void addData(JSONObject item) {
        datas.add(item);
        notifyDataSetChanged();
    }

    /***
     * 添加数据
     * @param data 数据项
     */
    public void addData(JsonArray data) {
        if (data == null || data.size() == 0) {
            return;
        }
        for (int i = 0; i < data.size(); i++) {
            this.datas.add(data.getJSONObject(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public void removeData(int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public JSONObject getData(int position) {
        try {
            return datas.getJSONObject(position);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void setOnItemClickListener(OnBaseItemClickListener<JSONObject> listener) {
        this.itemClickListener = listener;
    }

    @Override
    public void setOnItemChildrenClickListener(OnBaseItemChildrenClickListener<JSONObject> listener) {
        this.itemChildrenClickListener=listener;
    }

    @Override
    public void setOnItemLongClickListener(OnBaseItemLongClickListener<JSONObject> listener) {
        this.itemLongClickListener = listener;
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
        childView.setTag(R.id.base_adapter_tag0, holder);
        childView.setTag(R.id.base_adapter_tag1, position);
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
                JSONObject tmpData = getData(viewHolder.getItemPosition());
                if (tmpData != null) {
                    itemLongClickListener.onItemViewLongClick(viewHolder, viewHolder.getItemPosition(), tmpData);
                }
            }
        } catch (Throwable e) {
        }
        return false;
    }
}
