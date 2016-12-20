package sansan.ru.rockylabs.sansan.MVP.views;

import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;

/**
 * Created by Zinnur on 20.12.16.
 */

public interface MainView {

    void openNewBids();

    void openActiveBids();

    void openArchivedBids();

    void openProfile();

    void openCreateBidPage();

    void openBid(BidsDTO bid);

}
