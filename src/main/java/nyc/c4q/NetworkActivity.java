package nyc.c4q;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import nyc.c4q.model.FlickrResponse;
import nyc.c4q.model.Photo;
import nyc.c4q.rest.APIManager;
import nyc.c4q.rest.FlickrService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cfalc on 6/25/15.
 */
public class NetworkActivity extends ListActivity {

	private ArrayAdapter<Photo> adapter;

	private FlickrService flickrService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network);
		adapter = new ArrayAdapter<>(this, android.R.layout.activity_list_item);
		setListAdapter(adapter);

		getApi().getInterestingPhotos(10, 10, new Callback<FlickrResponse>() {
			@Override public void success(FlickrResponse flickrResponse, Response response) {
				adapter.addAll(flickrResponse.mPhotosInfo.mPhotos);
			}

			@Override public void failure(RetrofitError error) {
				Toast.makeText(NetworkActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

			}
		});
	}

	public FlickrService getApi() {
		if (flickrService == null) {
			return APIManager.getFlickrService();
		}
		return flickrService;
	}

	public void setApi(FlickrService flickrService) {
		this.flickrService = flickrService;
	}
}
