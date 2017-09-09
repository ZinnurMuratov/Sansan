package sansan.ru.rockylabs.sansan.api;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
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

public interface ApiInterface {

    @FormUrlEncoded
    @POST("api/v1/signup")
    Observable<UserSignUpResponseDTO> signUp(@Field("name") String login,
                                             @Field("phone") String phone,
                                             @Field("password") String password,
                                             @Field("role") String role,
                                             @Field("city") String city);

    @FormUrlEncoded
    @POST("api/v1/auth")
    Observable<UserLoginResponseDTO> signIn(@Field("phone") String phone,
                                            @Field("password") String password);

    @GET("api/v1/bids")
    Observable<List<BidsDTO>> getBids(@Query("page") int page,
                                      @Query("per_page") int number,
                                      @Query("status") String status,
                                      @Query("city") String city);

    @GET("api/v1/earned")
    Observable<EarnedResponseDTO> getGeneralRevenue(@Query("worker") String worker);

    @FormUrlEncoded
    @POST("api/v1/bid")
    Observable<AbsDTO> createBid(@Field("title") String title,
                                 @Field("phone") String phone,
                                 @Field("city") String city,
                                 @Field("date") String date);

    @FormUrlEncoded
    @PUT("api/v1/bid/{id}")
    Observable<BidsResponseDTO> updateBid(@Path("id") String bidId,
                                          @Field("worker") String worker,
                                          @Field("status") String status,
                                          @Field("date") String date,
                                          @Field("price") String price);

    @FormUrlEncoded
    @PUT("api/v1/fcm")
    Observable<AbsDTO> fcm(@Field("user_id") String userId,
                           @Field("fcm") String token,
                           @Field("device") String deviceId);
}