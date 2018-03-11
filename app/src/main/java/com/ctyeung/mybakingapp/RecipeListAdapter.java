package com.ctyeung.mybakingapp;
/**
 * Created by ctyeung on 2/25/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.res.Resources;
import com.ctyeung.mybakingapp.utility.JSONHelper;
import com.ctyeung.mybakingapp.data.Recipe;

import com.ctyeung.mybakingapp.R;

    /**
     * Created by ctyeung on 2/25/18.
     */

    public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>
    {
        private static final String TAG = RecipeListAdapter.class.getSimpleName();
        private static int mViewHolderCount;
        private int mNumberItems;
        private List<Recipe> mRecipes;
        private ListItemClickListener mClickListener;

        public interface ListItemClickListener
        {
            void onListItemClick(int clickItemIndex);
        }

        public RecipeListAdapter(int numberOfItems,
                                   ListItemClickListener listener,
                                   List<Recipe> recipes)
        {
            mRecipes = recipes;
            mNumberItems = numberOfItems;
            mClickListener = listener;
        }
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup,
                                                 int viewType)
        {
            Context context = viewGroup.getContext();
            int layoutIdForListItem = R.layout.recycler_list_item;

            LayoutInflater inflater = LayoutInflater.from(context);
            boolean shouldAttachToParentImmediately = false;

            View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
            ItemViewHolder viewHolder = new ItemViewHolder(view);

            Recipe recipe = mRecipes.get(mViewHolderCount);
            String name = recipe.getName();
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
        public void onBindViewHolder(ItemViewHolder holder,
                                     int position)
        {
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

                button = (TextView) itemView.findViewById(R.id.recipe_item);
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
