package sansan.ru.rockylabs.sansan.MVP.models;

import java.util.List;

import rx.Observable;
import sansan.ru.rockylabs.sansan.MVP.models.dto.AbsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.TokenResponseDTO;
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

    Observable<AbsDTO> createBid(String title, String phone, String city, String date);

    Observable<BidsResponseDTO> updateBid(String bidId, String worker, String status, String date, String price);

    Observable<AbsDTO> fcm(String userId, String token, String deviceId);

    void storeToken(String token);
}