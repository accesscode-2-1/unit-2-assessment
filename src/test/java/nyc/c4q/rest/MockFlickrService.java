package nyc.c4q.rest;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nyc.c4q.model.FlickrResponse;
import nyc.c4q.model.Photo;
import nyc.c4q.model.PhotosInfo;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Query;
import retrofit.mime.TypedByteArray;

import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Collections.EMPTY_LIST;

/**
 * Created by cfalc on 6/24/15.
 */
public class MockFlickrService implements FlickrService {

	public static final int PAGE_0 = 0;
	public static final int PAGE_1 = 1;
	public static final int PAGE_2 = 2;

	private Map<Integer, FlickrResponse> flickrResponses;

	public MockFlickrService() {
		flickrResponses = new HashMap<>();
		addResponseForPage(10, 0);
		addResponseForPage(20, 1);
	}

	@Override public void getInterestingPhotos(@Query("per_page") int perPage, @Query("page") int page,
			Callback<FlickrResponse> callback) {
		TypedByteArray body = new TypedByteArray("application/json", "".getBytes());

		FlickrResponse flickrResponse = flickrResponses.get(page);
		if (flickrResponse == null) {
			Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
			FlickrResponse res = new FlickrResponse();
			res.mStatus = "ok";
			res.mPhotosInfo = new PhotosInfo();
			res.mPhotosInfo.mPage = 0;
			res.mPhotosInfo.mPages = 0;
			res.mPhotosInfo.mPerPage = perPage;
			res.mPhotosInfo.mTotal = 500;
			callback.success(res, response);
			return;
		}

		Response response = new Response("", HTTP_OK, "nothing", EMPTY_LIST, body);
		System.out.println(flickrResponse + "");
		System.out.println(callback + "");
		callback.success(flickrResponse, response);
	}

	private void addResponseForPage(int perPage, int page) {
		FlickrResponse res = new FlickrResponse();
		res.mStatus = "ok";
		res.mPhotosInfo = new PhotosInfo();
		res.mPhotosInfo.mPage = page;
		res.mPhotosInfo.mPages = 50;
		res.mPhotosInfo.mPerPage = perPage;
		res.mPhotosInfo.mTotal = 500;
		List<Photo> photos = new ArrayList<>();
		for (int i = 0; i < 10; i ++) {
			photos.add(new Photo());
		}
		res.mPhotosInfo.mPhotos = photos;
		flickrResponses.put(page, res);
	}
}
