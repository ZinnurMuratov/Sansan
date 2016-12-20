package sansan.ru.rockylabs.sansan.MVP.views;

import java.util.List;

import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;

/**
 * Created by Zinnur on 19.12.16.
 */

public interface BidsView extends View {

    void showBids(List<BidsDTO> bids);

    void clearAdapter();

    void updateAdapter(List<BidsDTO> bids);

    boolean adapterIsNull();

    void initAdapter(List<BidsDTO> bids);

    int getBidsCount();

}
