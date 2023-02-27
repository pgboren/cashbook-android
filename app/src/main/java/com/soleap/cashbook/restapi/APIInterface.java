package com.soleap.cashbook.restapi;

import com.soleap.cashbook.common.document.DocumentSnapshot;
import com.soleap.cashbook.document.Branch;
import com.soleap.cashbook.document.Category;
import com.soleap.cashbook.document.Color;
import com.soleap.cashbook.document.Contact;
import com.soleap.cashbook.document.Institute;
import com.soleap.cashbook.document.Item;
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

    @DELETE("crud/{entity}/{id}")
    Call<Void> delete(@Path("entity") String entity, @Path("id") String id);

    @PUT("crud/category/{id}")
    Call<DocumentSnapshot> updateCategory(@Path("id") String id, @Body Category category);

    @GET("crud/category/{id}")
    Call<Category> getCategory(@Path("id") String id);

    @GET("crud/institute/{id}")
    Call<Institute> getInstitute(@Path("id") String id);

    @GET("crud/branch/{id}")
    Call<Branch> getBranch(@Path("id") String id);

    @GET("crud/category/")
    Call<List<Category>> getCategories();

    @GET("categories/")
    Call<List<Category>> getSubCategories(@Query("parent") String parent);

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

    @PUT("crud/contact/{id}")
    Call<DocumentSnapshot> updateContact(@Path("id") String id, @Body Contact contact);

    @PATCH("crud/contact/{id}")
    Call<Contact> patchContact(@Path("id") String id, @Body Contact contact);

    @GET("crud/contact/{id}")
    Call<Contact> getContact(@Path("id") String id);

    @POST("crud/contact")
    Call<DocumentSnapshot> createContact(@Body Contact model);

    @POST("crud/category")
    Call<DocumentSnapshot> createCategory(@Body Category model);

    @GET("view/{entity}/{id}")
    Call<DocumentSnapshot> viewModel(@Path("entity") String entity, @Path("id") String id);

    @GET("view/{entity}")
    Call<List<DocumentSnapshot>> listViewModel(@Path("entity") String entity);

    @POST("crud/branch")
    Call<DocumentSnapshot> createBranch(@Body Branch document);

    @PUT("crud/branch/{id}")
    Call<DocumentSnapshot> updateBranch(@Path("id") String id, @Body Branch branch);

    @POST("crud/institute")
    Call<DocumentSnapshot> createInstitute(@Body Institute body);

    @PUT("crud/institute/{id}")
    Call<DocumentSnapshot> updateInstitute(@Path("id") String id, @Body Institute institute);

    @POST("crud/color")
    Call<DocumentSnapshot> creatColor(@Body Color color);

    @PUT("crud/color/{id}")
    Call<DocumentSnapshot> updateColor(@Path("id") String id, @Body Color color);

    @GET("crud/color/{id}")
    Call<Color> getColor(@Path("id") String id);

    @POST("crud/item")
    Call<DocumentSnapshot> createItem(@Body Item document);

    @GET("crud/item/{id}")
    Call<Item> getItem(@Path("id") String id);

    @PUT("crud/item/{id}")
    Call<DocumentSnapshot> updateItem(@Path("id") String id, @Body Item item);

    @PATCH("crud/item/{id}")
    Call<DocumentSnapshot> patchItem(@Path("id") String id, @Body Map<String, Object> itemAttributes);

    @PATCH("crud/{doc}/{id}")
    Call<DocumentSnapshot> patch(@Path("doc") String doc, @Path("id") String id, @Body Map<String, Object> itemAttributes);

    @POST("crud/{doc}")
    Call<DocumentSnapshot> post(@Path("doc") String doc, @Body Map<String, Object> data);

    @POST("auth/signin")
    Call<User> login(@Body Map<String, Object> usernameAndPassword);

    @GET("agile/{entity}")
    Call<List<DocumentSnapshot>> getTasks(@Path("entity") String entity, @Query("stages") String[] stages);

}

