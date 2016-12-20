package sansan.ru.rockylabs.sansan.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

/**
 * Created by Zinnur on 19.12.16.
 */
public class BidsFragment extends Fragment implements BidsView {

    @Bind(R.id.bidsRV)
    protected RecyclerView recyclerViewBids;
    @Bind(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private BidsAdapter adapter;

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
        presenter.request();
        return view;
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
        adapter = new BidsAdapter(bids);
        recyclerViewBids.setAdapter(adapter);
        adapter.setLoadMoreListener(() -> {
            if (getBidsCount() > 10) {
                presenter.request();
            }
        });
    }

    @Override
    public int getBidsCount() {
        if (!adapterIsNull()){
            return adapter.getItemCount();
        } else{
            return 0;
        }
    }
}