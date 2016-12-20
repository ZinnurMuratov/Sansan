package sansan.ru.rockylabs.sansan.MVP.models;

import java.util.List;

import rx.Observable;
import sansan.ru.rockylabs.sansan.MVP.models.dto.AbsResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.EarnedResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserLoginResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserSignUpResponseDTO;

/**
 * Created by Zinnur on 19.12.16.
 */

public interface Model {

    Observable<UserSignUpResponseDTO> signUp(String name, String phone, String password, String role, String city);

    Observable<UserLoginResponseDTO> signIn(String phone, String password);

    Observable<List<BidsDTO>> getBids(int page, String status, String city);

    Observable<EarnedResponseDTO> getGeneralEarnings(String worker);

    Observable<AbsResponseDTO> createBid(String title, String phone, String city);

    Observable<BidsDTO> updateBid(String bidId, String worker, String status);

    void storeToken(String token);
}