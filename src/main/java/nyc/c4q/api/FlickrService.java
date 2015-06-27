package nyc.c4q.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FlickrService {

    @GET("/services/rest/?method=flickr.interestingness.getList")
    void getInterestingPhotos(
            @Query("per_page") int perPage,
            @Query("page") int page,
            Callback<FlickrResponse> callback
    );
}

