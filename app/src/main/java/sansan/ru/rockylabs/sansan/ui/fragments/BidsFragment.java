package sansan.ru.rockylabs.sansan.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.presenters.BidsPresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.views.BidsView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.adapters.BidsAdapter;
import sansan.ru.rockylabs.sansan.ui.base.BaseFragment;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainFragment;

import static com.google.android.gms.R.id.toolbar;

/**
 * Created by Zinnur on 19.12.16.
 */
public class BidsFragment extends BaseMainFragment implements BidsView {

    @Bind(R.id.bidsRV)
    protected RecyclerView recyclerViewBids;
    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.empty) protected TextView emptyTV;

    private static final String NEW = "Новых заказов нет :( ";
    private static final String STATUS = "новый";
    private Boolean isInit = false;

    private BidsAdapter adapter;
    private Toolbar toolbar;

    @Inject
    BidsPresenter presenter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        presenter.onCreate(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bids, container, false);
        ButterKnife.bind(this,view);
        recyclerViewBids.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
        emptyTV.setText(NEW);
        activityCallback.setTitle("Новые заявки");
        Log.d("back press ","yes");
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInit){
            initAdapter();
        }
        presenter.request();
    }

    @Override
    public void onPause() {
        super.onPause();
        isInit = false;
    }



    @Override
    public void showError(String error) {

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showBids(List<BidsDTO> bids) {

    }

    @Override
    public void clearAdapter() {
        if (!adapterIsNull()) {
            adapter.clear();
        }
    }

    @Override
    public void updateAdapter(List<BidsDTO> bids) {
        if (adapterIsNull()){
            initAdapter(bids);
        } else{
            adapter.update(bids);
        }
    }

    @Override
    public boolean adapterIsNull() {
        boolean res;
        if (adapter != null){
            res = false;
        } else {
            res = true;
        }
        return res;
    }

    @Override
    public void initAdapter(List<BidsDTO> bids) {
        adapter = new BidsAdapter(bids,presenter);
        recyclerViewBids.setAdapter(adapter);
        adapter.setLoadMoreListener(() -> {
            if (getBidsCount() > 10) {
                presenter.request();
            }
        });

    }

    private void initAdapter(){
        adapter = new BidsAdapter(presenter);
        recyclerViewBids.setAdapter(adapter);
        isInit = true;

    }

    @Override
    public int getBidsCount() {
        if (!adapterIsNull()){
            return adapter.getItemCount();
        } else{
            return 0;
        }
    }

    @Override
    public void templateIsShowing(Boolean show) {
        if (show) {
            emptyTV.setVisibility(View.VISIBLE);
        } else {
            emptyTV.setVisibility(View.GONE);
        }
    }

    @Override
    public void openBid(BidsDTO bid) {
        activityCallback.openBid(bid);
    }

    @Override
    public String getStatus() {
        return STATUS;
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }
}
