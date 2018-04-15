package com.ctyeung.mybakingapp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.SharedPrefUtil;
import com.ctyeung.mybakingapp.data.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctyeung on 3/6/18.
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ItemViewHolder>
{
    private static final String TAG = IngredientListAdapter.class.getSimpleName();
    public static int mSelectedPosition = 0;
    public static int mViewHolderCount=0;
    private static List<ItemViewHolder> mHolders=null;

    private List<Ingredient> mIngredients;

    public interface ListItemClickListener
    {
        void onListItemClick(int clickItemIndex);
    }

    public static void Reset()
    {
        mViewHolderCount = 0;
        mSelectedPosition = 0;
        mHolders = null;
    }

    public IngredientListAdapter(ListItemClickListener listener,
                                List<Ingredient> ingredients)
    {
        mIngredients = ingredients;

        if(null==mHolders)
            mHolders = new ArrayList<ItemViewHolder>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                             int viewType)
    {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recycler_ingredient_list_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ItemViewHolder holder = new ItemViewHolder(view);
        mHolders.add(holder);

        Ingredient ingredient = mIngredients.get(mViewHolderCount);
        String quantity = ingredient.getQuantity();
        holder.textViewQuantity.setText(context.getResources().getString(R.string.quantity_)+quantity);

        String measure = ingredient.getMeasure();
        holder.textViewMeasure.setText(context.getResources().getString(R.string.measure_)+measure);

        String stuff = ingredient.getIngredient();
        holder.textViewIngredient.setText(context.getResources().getString(R.string.ingredient_)+stuff);

        Log.d(TAG, context.getResources().getString(R.string.num_viewholders) + mViewHolderCount);

        mViewHolderCount++;
        return holder;
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
    public void onBindViewHolder(IngredientListAdapter.ItemViewHolder holder,
                                 final int position)
    {
        Log.d(TAG, "#" + position);
        holder.bind(position);
        updateSelected(mSelectedPosition);

        holder.itemView.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int clicked_index = position;
                updateSelected(clicked_index);
            }
        });
    }

    public void updateSelected(int index)
    {
        ItemViewHolder holder;
        // clear previous selected position
        if(mSelectedPosition < mHolders.size())
        {
            holder = (ItemViewHolder) mHolders.get(mSelectedPosition);
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        // highlight new position
        if(index < mHolders.size())
        {
            holder = (ItemViewHolder) mHolders.get(index);
            holder.itemView.setBackgroundColor(Color.GREEN);
        }
        mSelectedPosition = index;
    }

    @Override
    public int getItemCount()
    {
        return (null==mIngredients)?0:mIngredients.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView textViewQuantity;
        TextView textViewMeasure;
        TextView textViewIngredient;

        public ItemViewHolder(View itemView)
        {
            super(itemView);

            textViewQuantity = (TextView) itemView.findViewById(R.id.quantity_item);
            textViewMeasure = (TextView) itemView.findViewById(R.id.measure_item);
            textViewIngredient = (TextView) itemView.findViewById(R.id.ingredient_item);
        }

        /**
         * A method we wrote for convenience. This method will take an integer as input and
         * use that integer to display the appropriate text within a list item.
         * @param listIndex Position of the item in the list
         */
        void bind(int listIndex)
        {
        }

        @Override
        public void onClick(View view)
        {
        }
    }
}
