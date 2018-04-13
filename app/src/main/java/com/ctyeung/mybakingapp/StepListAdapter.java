package com.ctyeung.mybakingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ctyeung.mybakingapp.StepListAdapter.ItemViewHolder;
import com.ctyeung.mybakingapp.data.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctyeung on 3/6/18.
 */

public class StepListAdapter extends RecyclerView.Adapter<StepListAdapter.ItemViewHolder>
{
    private static final String TAG = StepListAdapter.class.getSimpleName();
    public static int mViewHolderCount;
    private static List<ItemViewHolder> holders;
    private static int mNumberItems;
    private ItemViewHolder viewHolder = null;
    private List<Step> mSteps;
    private int selected_position = 0;

    final private ListItemClickListener mClickListener;

    public interface ListItemClickListener
    {
        void onListItemClick(int clickItemIndex);
    }

    public StepListAdapter(ListItemClickListener listener,
                           List<Step> steps,
                           int init_selected_postion)
    {
        mNumberItems = steps.size();
        mSteps = steps;
        mClickListener = listener;
        selected_position = init_selected_postion;

        if(null==holders)
            holders = new ArrayList<ItemViewHolder>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                             int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_step_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);

        viewHolder = new ItemViewHolder(view);
        holders.add(viewHolder);

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
                                 int position)
    {
        Log.d(TAG, "#" + position);
        holder.bind(position);
        updateSelected(selected_position);
    }

    public void updateSelected(int index)
    {
        ItemViewHolder holder;

        if(selected_position < holders.size())
        {
            holder = (ItemViewHolder) holders.get(selected_position);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        if(index < holders.size())
        {
            holder = (ItemViewHolder) holders.get(index);
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        selected_position = index;

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

            button = (TextView) itemView.findViewById(R.id.step_item);
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
            int clicked_index = getAdapterPosition();
            updateSelected(clicked_index);
            mClickListener.onListItemClick(clicked_index);
        }
    }
}
