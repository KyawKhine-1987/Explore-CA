package tourfinder.exploreca.com.kk.freelance.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import tourfinder.exploreca.com.kk.freelance.R;
import tourfinder.exploreca.com.kk.freelance.model.Tour;
import tourfinder.exploreca.com.kk.freelance.utils.ColorUtils;

/**
 * Created by Kyaw Khine on 12/28/2016.
 */
public class TourListRVAdapter extends RecyclerView.Adapter<TourListRVAdapter.TourListViewHolder> {

    public static final String LOG_TAG = TourListRVAdapter.class.getSimpleName();

    Context mContext;
    private List<Tour> mTourListItems;
    final private ListItemClickListener mOnClickListener;
    private static int viewHolderCount;

    public interface ListItemClickListener {
        void onListItemClick(int clickItemIndex);
    }

    public TourListRVAdapter( Context context, List<Tour> tourListOfItems, ListItemClickListener listener) {
        Log.i(LOG_TAG, "TEST: TourListRVAdapter() Constructor called...");

        mContext = context;
        mTourListItems = tourListOfItems;
        mOnClickListener = listener;
        viewHolderCount = 0;
    }

    @Override
    public TourListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(LOG_TAG, "TEST: onCreateViewHolder() called...");

        Context context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View v = inflater.inflate(R.layout.listitem_tour, parent, false);

        TourListViewHolder tourListViewHolder = new TourListViewHolder(v);
        int backgroundColorForViewHolder = ColorUtils.getViewHolderBackgroundColorFromInstance(context, viewHolderCount);
        tourListViewHolder.itemView.setBackgroundColor(backgroundColorForViewHolder);
        viewHolderCount++;

        TourListViewHolder vh = new TourListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TourListViewHolder holder, int position) {
        Log.i(LOG_TAG, "TEST: onBindViewHolder() Constructor called...");

        Tour tour = mTourListItems.get(position);

        holder.titleTextView.setText(tour.getTitle());

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        holder.priceTextView.setText(nf.format(tour.getPrice()));

        int imageResource = mContext.getResources().getIdentifier(tour.getImage() + "", "drawable", mContext.getPackageName());
        if (imageResource != 0) {
            holder.mapImageView.setImageResource(imageResource);
        }
    }

    @Override
    public int getItemCount() {
        Log.i(LOG_TAG, "TEST: getItemCount() called...");

        return mTourListItems.size();
    }


    public class TourListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final String LOG_TAG = TourListViewHolder.class.getSimpleName();

        // Will display the position in the list, ie 0 through getItemCount() - 1
        TextView titleTextView;
        // Will display which ViewHolder is displaying this data
        TextView priceTextView;

        ImageView mapImageView;

        public TourListViewHolder(View itemView) {
            super(itemView);
            Log.i(LOG_TAG, "TEST: TourListViewHolder() called...");

            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);
            mapImageView = (ImageView) itemView.findViewById(R.id.mapImageView);
            itemView.setOnClickListener(this);
        }

        /**
         * Called whenever a user clicks on an item in the list.
         *
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            Log.i(LOG_TAG, "TEST: onClick() called...");
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
