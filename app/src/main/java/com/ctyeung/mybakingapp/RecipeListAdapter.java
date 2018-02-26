package com.ctyeung.mybakingapp;
/**
 * Created by ctyeung on 2/25/18.
 */

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.res.Resources;

import com.ctyeung.mybakingapp.R;

    /**
     * Created by ctyeung on 2/25/18.
     */

    public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ItemViewHolder>
    {
        private static final String TAG = RecipeListAdapter.class.getSimpleName();
        private int mViewHolderCount;
        private int mNumberItems;
        private JSONArray mJsonArray;

        final private ListItemClickListener mClickListener;

        public interface ListItemClickListener
        {
            void onListItemClick(int clickItemIndex);
        }

        public RecipeListAdapter(int numberOfItems,
                                   ListItemClickListener listener,
                                   JSONArray jsonArray)
        {
            mJsonArray = jsonArray;
            mNumberItems = numberOfItems;
            mClickListener = listener;
        }
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
            return null;
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
                                     int position) {
            Log.d(TAG, "#" + position);
            holder.bind(position);
        }

        @Override
        public int getItemCount() {
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
