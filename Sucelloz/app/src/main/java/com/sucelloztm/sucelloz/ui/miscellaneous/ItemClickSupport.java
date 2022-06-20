package com.sucelloztm.sucelloz.ui.miscellaneous;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.sucelloztm.sucelloz.R;

/**
 * item click support class
 */
public class ItemClickSupport {
    private final RecyclerView mRecyclerView;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (mOnItemLongClickListener != null) {
                RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecyclerView, holder.getAdapterPosition(), v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener
            = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnItemClickListener != null) {
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener != null) {
                view.setOnLongClickListener(mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    };

    /**
     * custom constructor
     *
     * @param recyclerView recycler view
     */
    private ItemClickSupport(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mRecyclerView.setTag(R.id.item_click_support, this);
        mRecyclerView.addOnChildAttachStateChangeListener(mAttachListener);
    }

    /**
     * adds the item click support to a recycler view
     *
     * @param view recycler view
     * @return item click support instance
     */
    public static ItemClickSupport addTo(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support == null) {
            support = new ItemClickSupport(view);
        }
        return support;
    }

    public static ItemClickSupport removeFrom(RecyclerView view) {
        ItemClickSupport support = (ItemClickSupport) view.getTag(R.id.item_click_support);
        if (support != null) {
            support.detach(view);
        }
        return support;
    }

    /**
     * setter
     *
     * @param listener on item click listener
     * @return item click support instance
     */
    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
        return this;
    }

    /**
     * setter
     *
     * @param listener on item long click listener
     * @return item click support instance
     */
    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener) {
        mOnItemLongClickListener = listener;
        return this;
    }

    /**
     * detaches item click support instances from a recycler view
     *
     * @param view view
     */
    private void detach(RecyclerView view) {
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support, null);
    }

    /**
     * interface for the on item click listener
     */
    public interface OnItemClickListener {

        /**
         * on item clicked method
         *
         * @param recyclerView recycler view
         * @param position     position
         * @param v            view
         */
        void onItemClicked(RecyclerView recyclerView, int position, View v);
    }

    /**
     * interface for the on item long click listener
     */
    public interface OnItemLongClickListener {

        /**
         * on item long clicked method
         *
         * @param recyclerView recycler view
         * @param position     position
         * @param v            view
         */
        boolean onItemLongClicked(RecyclerView recyclerView, int position, View v);
    }
}
