package com.ctyeung.mybakingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctyeung.mybakingapp.data.Ingredient;
import com.ctyeung.mybakingapp.data.Step;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctyeung on 3/6/18.
 */

public class IngredientListAdapter extends RecyclerView.Adapter<IngredientListAdapter.ItemViewHolder>
{
    private static final String TAG = IngredientListAdapter.class.getSimpleName();
    public static int mViewHolderCount;
    private int mNumberItems;
    private List<Ingredient> mIngredients;
    private List<ItemViewHolder> holders;
    private int selected_position = 0;

    final private ListItemClickListener mClickListener;

    public interface ListItemClickListener
    {
        void onListItemClick(int clickItemIndex);
    }

    public IngredientListAdapter(int numberOfItems,
                                ListItemClickListener listener,
                                List<Ingredient> ingredients)
    {
        mIngredients = ingredients;
        mNumberItems = numberOfItems;
        mClickListener = listener;
        holders = new ArrayList<ItemViewHolder>();

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
        ItemViewHolder viewHolder = new ItemViewHolder(view);
        holders.add(viewHolder);

        Ingredient ingredient = mIngredients.get(mViewHolderCount);
        String quantity = ingredient.getQuantity();
        viewHolder.textViewQuantity.setText("Quantity: "+quantity);

        String measure = ingredient.getMeasure();
        viewHolder.textViewMeasure.setText("Measure: "+measure);

        String stuff = ingredient.getIngredient();
        viewHolder.textViewIngredient.setText("Ingredient: "+stuff);

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
    public void onBindViewHolder(IngredientListAdapter.ItemViewHolder holder,
                                 final int position)
    {
        Log.d(TAG, "#" + position);
        holder.bind(position);
        updateSelected(0);

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
