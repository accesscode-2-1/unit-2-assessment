package nyc.c4q;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import nyc.c4q.model.FlickrResponse;
import nyc.c4q.rest.APIManager;
import nyc.c4q.rest.FlickrService;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Unit2AssessmentActivity extends Activity {

  private static final String TAG = "TAG";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.deckard);
    new Task().execute();
  }

  class Task extends AsyncTask<Void, Void, Void> {

    @Override protected Void doInBackground(Void... params) {
      APIManager.getFlickrService().getInterestingPhotos(10, 10, new Callback<FlickrResponse>() {
        @Override public void success(FlickrResponse flickrResponse, Response response) {
          Log.d(TAG, "got flickerResponse " + flickrResponse);
        }

        @Override public void failure(RetrofitError error) {
          if (error.getKind() == RetrofitError.Kind.NETWORK) {

          }
        }
      });
      return null;
    }
  }
}
