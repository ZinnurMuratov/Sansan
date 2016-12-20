package sansan.ru.rockylabs.sansan.api;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import sansan.ru.rockylabs.sansan.MVP.models.dto.BidsDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserLoginResponseDTO;
import sansan.ru.rockylabs.sansan.MVP.models.dto.UserSignUpResponseDTO;

/**
 * Created by Zinnur on 19.12.16.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/signup")
    Observable<UserSignUpResponseDTO> signUp(@Field("name") String login,
                                             @Field("phone") String phone,
                                             @Field("password") String password,
                                             @Field("role") String role);

    @FormUrlEncoded
    @POST("api/v1/auth")
    Observable<UserLoginResponseDTO> signIn(@Field("phone") String phone,
                                            @Field("password") String password);

    @GET("api/v1/bids")
    Observable<List<BidsDTO>> getBids(@Query("page") int page,
                                      @Query("per_page") int number,
                                      @Query("status") String status);

}