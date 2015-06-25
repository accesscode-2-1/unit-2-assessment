package nyc.c4q.rest;

import nyc.c4q.model.FlickrResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cfalc on 6/24/15.
 */
public interface FlickrService {

	@GET("/services/rest/?method=flickr.interestingness.getList")
	void getInterestingPhotos(@Query("per_page") int perPage, @Query("page") int page, Callback<FlickrResponse> callback);
}
