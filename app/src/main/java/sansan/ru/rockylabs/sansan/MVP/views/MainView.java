package sansan.ru.rockylabs.sansan.MVP.views;

import com.tbruyelle.rxpermissions.RxPermissions;

import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;

/**
 * Created by Zinnur on 20.12.16.
 */

public interface MainView extends View{

    void openNewBids();

    void openActiveBids();

    void openArchivedBids();

    void openProfile();

    void openCreateBidPage();

    void openBid(BidsDTO bid);

    void setTitle(String title);

    RxPermissions getRxPermissions();

}
