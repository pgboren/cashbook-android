package com.soleap.cashbook.restapi;

import com.soleap.cashbook.common.document.Document;
import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.common.document.PagingRecyclerViewData;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Media;
import com.soleap.cashbook.document.User;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("auth/signin")
    Call<User> login(@Body Map<String, Object> usernameAndPassword);

    @Multipart
    @POST("media/upload")
    Call<Media> mediaUpload(@Part MultipartBody.Part image, @Part("file") RequestBody name);

    @Multipart
    @POST("files/upload/")
    Call<ResponseBody> fileUpload(@Query("folder") String folder,
                                  @Query("thumbnail") String thumbnail,
                                  @Query("thumbnail_width") int thumbnail_width,
                                  @Part MultipartBody.Part image,
                                  @Part("upload") RequestBody name);


    @DELETE("crud/{entity}/{id}")
    Call<Void> delete(@Path("entity") String entity, @Path("id") String id);

    @PATCH("crud/{doc}/{id}")
    Call<DocumentSnapshot> patch(@Path("doc") String doc, @Path("id") String id, @Body Map<String, Object> itemAttributes);

    @POST("crud/{doc}")
    Call<Document> post(@Path("doc") String doc, @Body Map<String, Object> data);

    @PUT("crud/category/{id}")
    Call<DocumentSnapshot> updateCategory(@Path("id") String id, @Body Category category);

    @GET("crud/category/{id}")
    Call<Category> getCategory(@Path("id") String id);

    @GET("crud/category/")
    Call<List<Category>> getCategories();

    @GET("categories/")
    Call<List<Category>> getSubCategories(@Query("parent") String parent);

    @POST("crud/category")
    Call<DocumentSnapshot> createCategory(@Body Category model);

    @GET("view/{entity}/{id}")
    Call<DocumentSnapshot> get(@Path("entity") String entity, @Path("id") String id);

    @GET("view/{docname}")
    Call<Document> list(@Query("page") int page, @Query("limit") String limit);

    @POST("view/{view_name}/{docname}")
    Call<PagingRecyclerViewData> listViewData(@Path("view_name") String viewName, @Path("docname") String document, @Query("page") int page, @Query("limit") int limit, @Body Map<String, Object> data);

    @POST("view/LIST_ITEM_VIEW/{docname}")
    Call<Map<String, Object>> getViewData(@Path("docname") String document, @Query("id") String id);


}

