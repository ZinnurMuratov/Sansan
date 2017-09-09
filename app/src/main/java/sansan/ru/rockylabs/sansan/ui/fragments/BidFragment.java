package sansan.ru.rockylabs.sansan.ui.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.presenters.BidPresenter;
import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.MVP.views.BidView;
import sansan.ru.rockylabs.sansan.R;
import sansan.ru.rockylabs.sansan.di.App;
import sansan.ru.rockylabs.sansan.ui.base.BaseMainFragment;
import sansan.ru.rockylabs.sansan.utils.FormatUtil;

/**
 * Created by Zinnur on 20.12.16.
 */

public class BidFragment extends BaseMainFragment implements BidView{


    @Bind(R.id.bid_title) protected TextView bidTitle;
    @Bind(R.id.client_block) protected LinearLayout clientBlock;
    @Bind(R.id.master_block) protected LinearLayout masterBlock;
    @Bind(R.id.status_text) protected TextView statusTv;
    @Bind(R.id.bid_phone) protected TextView phoneTV;
    @Bind(R.id.worker_name) protected TextView worker;
    @Bind(R.id.date) protected TextView dateTv;
    @Bind(R.id.date_label) protected TextView dateLabelTv;
    @Bind(R.id.price_block) protected LinearLayout priceBlock;
    @Bind(R.id.price) protected TextView priceTv;
    @Bind(R.id.cancel_btn) protected Button cancelBtn;
    @Bind(R.id.worked_btn) protected Button finishedBtn;
    @Bind(R.id.call_btn) protected Button callBtn;
    @Bind(R.id.take_bid_btn) protected Button takeBid;
    @Bind(R.id.set_price_btn) protected Button setPriceBtn;
    @Bind(R.id.textViewBlock) protected LinearLayout textViewBlock;
    @Bind(R.id.buttonsBlock) protected LinearLayout buttonsBlock;
    @Bind(R.id.info) protected TextView infoLabel;
    @Bind(R.id.price_input) protected EditText priceInput;
    @Bind(R.id.send_price_btn) protected Button sendPriceBtn;

    private ProgressDialog alertDialog;
    private Boolean bidUpdate = false;


    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Inject
    BidPresenter presenter;

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public static BidFragment newInstance(BidsDTO bid) {
        BidFragment myFragment = new BidFragment();

        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, bid);
        myFragment.setArguments(args);

        return myFragment;
    }

    private BidsDTO getBid() {
        return bidUpdate ? presenter.getBid() : (BidsDTO) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
        presenter.onCreate(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid, container, false);
        ButterKnife.bind(this, view);
        presenter.setData(getBid());
        activityCallback.setTitle("Заявка");
        initBtns();
        return view;
    }

    private void initBtns(){
        takeBid.setOnClickListener(view -> presenter.update(getBid(),"активный"));
        finishedBtn.setOnClickListener(view -> presenter.update(getBid(), "выполнено"));
        cancelBtn.setOnClickListener(view -> presenter.update(getBid(), "отказ"));
        sendPriceBtn.setOnClickListener(view -> presenter.update(getBid(), "выполнено"));
        setPriceBtn.setOnClickListener(view -> presenter.showPriceInput(true));
        callBtn.setOnClickListener(view -> presenter.askToCall());
    }



    @Override
    public void showError(String error) {
        Snackbar.make(bidTitle, error, Snackbar.LENGTH_SHORT).show();
    }


    @Override
    public void showLoading() {
        alertDialog = new ProgressDialog(getContext());
        alertDialog.setMessage("Подождите");
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public void hideLoading() {
        alertDialog.dismiss();
    }

    @Override
    public void setTitle(String title) {
        bidTitle.setText(title);
    }

    @Override
    public void setPhone(String phone) {
        phoneTV.setText(phone);
    }

    @Override
    public void setStatus(String status) {
        statusTv.setText(status);
    }

    @Override
    public void setDateLabel(String dateLabel) {
        dateLabelTv.setText(dateLabel);
    }

    @Override
    public void setDate(String date) {
        dateTv.setText(date);
    }

    @Override
    public void setPrice(String price) {
        priceTv.setText(price);
    }

    @Override
    public void setWorkerName(String workerName) {
        worker.setText(workerName);
    }

    @Override
    public void setPriceBtnVisibility(Boolean visible) {
        if (visible) {
            setPriceBtn.setVisibility(View.VISIBLE);
        } else {
            setPriceBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCancelBtnVisibility(Boolean visible) {
        if (visible) {
            cancelBtn.setVisibility(View.VISIBLE);
        } else {
            cancelBtn.setVisibility(View.GONE);
        }
    }


    @Override
    public void setPhoneVisibility(Boolean visible) {
        if (visible) {
            clientBlock.setVisibility(View.VISIBLE);
        } else {
            clientBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void setCallBtnVisibility(Boolean visible) {
        if (visible) {
            callBtn.setVisibility(View.VISIBLE);
        } else {
            callBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void setSubscriberBtnVisibility(Boolean visible) {
        if (visible) {
            takeBid.setVisibility(View.VISIBLE);
        } else {
            takeBid.setVisibility(View.GONE);
        }
    }

    @Override
    public void setDoneBtnVisibility(Boolean visible) {
        if (visible) {
            finishedBtn.setVisibility(View.VISIBLE);
        } else {
            finishedBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPhoneBlock(Boolean visible) {
        if (visible) {
            clientBlock.setVisibility(View.VISIBLE);
        } else {
            clientBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMasterBlock(Boolean visible) {
        if (visible) {
            masterBlock.setVisibility(View.VISIBLE);
        } else {
            masterBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPriceBlock(Boolean visible) {
        if (visible) {
            priceBlock.setVisibility(View.VISIBLE);
        } else {
            priceBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showButtons(Boolean visible) {
        if (visible) {
            buttonsBlock.setVisibility(View.VISIBLE);
        } else {
            buttonsBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTextViews(Boolean visible) {
        if (visible) {
            textViewBlock.setVisibility(View.VISIBLE);
        } else {
            textViewBlock.setVisibility(View.GONE);
        }
    }

    @Override
    public void showTemplate(Boolean visible) {
        if (visible) {
            infoLabel.setVisibility(View.VISIBLE);
        } else {
            infoLabel.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPriceInput(Boolean visible) {
        if (visible) {
            priceInput.setVisibility(View.VISIBLE);
        } else {
            priceInput.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSendPriceBtn(Boolean visible) {
        if (visible) {
            sendPriceBtn.setVisibility(View.VISIBLE);
        } else {
            sendPriceBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public String getPrice() {
        return FormatUtil.stripNonDigits(priceInput.getText().toString());
    }

    @Override
    public void setUpdated() {
        bidUpdate = true;
    }

    @Override
    public RxPermissions getRxPermissions() {
        return activityCallback.getRxPermissions();
    }

    @Override
    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getBid().getPhone()));
        startActivity(intent);
    }

}
