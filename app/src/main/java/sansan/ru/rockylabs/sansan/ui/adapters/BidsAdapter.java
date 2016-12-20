package sansan.ru.rockylabs.sansan.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.R;

/**
 * Created by Zinnur on 19.12.16.
 */

public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.ViewHolder> {



    protected List<BidsDTO> list = null;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public BidsAdapter(List<BidsDTO> lists) {
        this.list = lists;
    }

    public void update(final List<BidsDTO> bids){
        list.addAll(bids);
        notifyDataSetChanged();
        isLoading = false;
    }


    public void clear(){
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public BidsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bids_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BidsAdapter.ViewHolder holder, int position) {
        holder.populate(list.get(position));
        if( position >= getItemCount()-1 && isMoreDataAvailable && !isLoading && loadMoreListener!=null){
            isLoading = true;
            loadMoreListener.onLoadMore();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bid_title)
        protected TextView bidTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void populate(BidsDTO bid){
            bidTitle.setText(bid.getTitle());
        }
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}