package nyc.c4q;

import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * Created by cfalc on 6/21/15.
 */

public class FlickrService {

	public static final String API_URL = "https://api.flickr.com";
	public static final String API_KEY = "123eee6ea239416da1e9c05d7d192c46";
	private static final String TAG = "FlickrService";

	public FlickrResponse GetInterestingPhotos(@Query("per_page") int perPage, @Query("page") int page) {
		return getFlickrServiceInterface().getInterestingPhotos(perPage, page);
	}

	public interface Flickr {
		@GET("/services/rest/?method=flickr.interestingness.getList")
		FlickrResponse getInterestingPhotos(@Query("per_page") int perPage, @Query("page") int page);
	}

	protected Flickr getFlickrServiceInterface() {
		return getRestAdapter().create(Flickr.class);
	}

	public RestAdapter getRestAdapter() {
		OkHttpClient client = new OkHttpClient();
		OkClient retrofitClient = new OkClient(client);
		RestAdapter restAdapter = new RestAdapter.Builder()
				.setEndpoint(API_URL)
				.setRequestInterceptor(new RequestInterceptor() {
					@Override
					public void intercept(RequestFacade request) {
						request.addQueryParam("api_key", API_KEY);
						request.addQueryParam("format", "json");
						request.addQueryParam("nojsoncallback", "1"); //disable the JSONP callback stuff flickr seems to assume we want
					}
				})
				.setLogLevel(RestAdapter.LogLevel.FULL)
				.setLog(new AndroidLog(TAG))
				.setClient(retrofitClient)
				.build();
		return restAdapter;
	}
}
