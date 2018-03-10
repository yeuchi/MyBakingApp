package com.ctyeung.mybakingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctyeung.mybakingapp.data.Step;

import java.util.List;

/**
 * Created by ctyeung on 3/6/18.
 */

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ItemViewHolder>
{
    private static final String TAG = StepListAdapter.class.getSimpleName();
    private static int mViewHolderCount;
    private int mNumberItems;
    private List<Step> mSteps;

    final private ListItemClickListener mClickListener;

    public interface ListItemClickListener
    {
        void onListItemClick(int clickItemIndex);
    }

    public StepListAdapter(int numberOfItems,
                           ListItemClickListener listener,
                             List<Step> steps)
    {
        mSteps = steps;
        mNumberItems = numberOfItems;
        mClickListener = listener;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                             int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_detail_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ItemViewHolder viewHolder = new ItemViewHolder(view);

        Step step = mSteps.get(mViewHolderCount);
        String name = step.getShortDescription();
        viewHolder.button.setText(name);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + mViewHolderCount);

        mViewHolderCount++;
        return viewHolder;
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the correct
     * indices in the list for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(StepListAdapter.ItemViewHolder holder,
                                 int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount()
    {
        return mNumberItems;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView button;

        public ItemViewHolder(View itemView)
        {
            super(itemView);

            button = (TextView) itemView.findViewById(R.id.detail_item);
            button.setOnClickListener(this);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex)
        {

            //viewHolderName.setText(String.valueOf(listIndex));
        }

        @Override
        public void onClick(View view)
        {
            int clickPosition = getAdapterPosition();
            mClickListener.onListItemClick(clickPosition);
        }
    }
}
