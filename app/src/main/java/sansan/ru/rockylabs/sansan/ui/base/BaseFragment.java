package sansan.ru.rockylabs.sansan.ui.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import sansan.ru.rockylabs.sansan.MVP.presenters.Presenter;
import sansan.ru.rockylabs.sansan.ui.activities.StartActivityCallback;

/**
 * Created by Zinnur on 19.12.16.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract Presenter getPresenter();

    protected StartActivityCallback startActivityCallback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            startActivityCallback = (StartActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }
}