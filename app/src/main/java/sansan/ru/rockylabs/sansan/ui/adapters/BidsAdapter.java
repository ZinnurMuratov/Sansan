package sansan.ru.rockylabs.sansan.ui.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.presenters.BidsPresenter;
import sansan.ru.rockylabs.sansan.R;

/**
 * Created by Zinnur on 19.12.16.
 */

public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.ViewHolder> {



    protected List<BidsDTO> list = null;
    protected BidsPresenter presenter;
    OnLoadMoreListener loadMoreListener;
    boolean isLoading = false, isMoreDataAvailable = true;

    public BidsAdapter(List<BidsDTO> lists, BidsPresenter presenter) {
        this.list = lists;
        this.presenter = presenter;
    }

    public BidsAdapter(BidsPresenter presenter){
        this.presenter = presenter;
    }

    public void update(final List<BidsDTO> bids){
        if (list != null) {
            list.addAll(bids);
        } else {
            this.list = bids;
        }
        notifyDataSetChanged();
        isLoading = false;
    }


    public void clear(){
        if (list != null){
            list.clear();
        }
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
        return list != null ? list.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bid_title) protected TextView bidTitle;
        @Bind(R.id.cv) protected CardView rootCardView;
        @Bind(R.id.client_block) protected LinearLayout clientBlock;
        @Bind(R.id.master_block) protected LinearLayout masterBlock;
        @Bind(R.id.status_text) protected TextView status;
        @Bind(R.id.bid_phone) protected TextView phone;
        @Bind(R.id.worker_name) protected TextView worker;
        @Bind(R.id.date) protected TextView date;
        @Bind(R.id.date_label) protected TextView dateLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void populate(BidsDTO bid){
            bidTitle.setText(bid.getTitle());
            status.setText(bid.getStatus());
            if (bid.getStatus().equals("новый")){
                clientBlock.setVisibility(View.GONE);
                masterBlock.setVisibility(View.GONE);
                dateLabel.setText("Создан:");
                date.setText(bid.getCreated());
            } else if (bid.getStatus().equals("активный")){
                clientBlock.setVisibility(View.VISIBLE);
                masterBlock.setVisibility(View.VISIBLE);
                phone.setText(bid.getPhone());
                worker.setText(bid.getWorkerName());
                dateLabel.setText("Принят:");
                date.setText(bid.getSubscribed());
            } else if (bid.getStatus().equals("выполнено")){
                clientBlock.setVisibility(View.VISIBLE);
                masterBlock.setVisibility(View.VISIBLE);
                phone.setText(bid.getPhone());
                worker.setText(bid.getWorkerName());
                dateLabel.setText("Сделан:");
                date.setText(bid.getClosed());
            } else if (bid.getStatus().equals("отказ")){
                clientBlock.setVisibility(View.VISIBLE);
                masterBlock.setVisibility(View.VISIBLE);
                phone.setText(bid.getPhone());
                worker.setText(bid.getWorkerName());
                dateLabel.setText("Отказ:");
                date.setText(bid.getClosed());
            }
            rootCardView.setOnClickListener(view -> {
                presenter.openBid(bid);
            });
        }




    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}