package com.example.android.android_me;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.MasterListFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MasterListAdapter extends RecyclerView.Adapter<MasterListAdapter.ViewHolder> {

    private static final String TAG = MasterListAdapter.class.getSimpleName();
    private final List<Integer> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MasterListAdapter(List<Integer> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_masterlist, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) view.getLayoutParams();
        Log.d(TAG, String.format("Height: %d", lp.height));
        lp.height = 180;
        Log.d(TAG, String.format("Adjusted height: %d", lp.height));
        view.setLayoutParams(lp);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setImageResource(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mIdView;
        public int mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (ImageView) view.findViewById(R.id.iv_image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mItem + "'";
        }
    }
}
