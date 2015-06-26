package nyc.c4q;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
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
    new Task().execute(this);
  }

  public class Task extends AsyncTask<Context, Void, Void> {

    @Override protected Void doInBackground(final Context... params) {
      APIManager.getFlickrService().getInterestingPhotos(10, 10, new Callback<FlickrResponse>() {
        @Override public void success(FlickrResponse flickrResponse, Response response) {
          Log.d(TAG, "got flickerResponse " + flickrResponse);
        }

        @Override public void failure(RetrofitError error) {
          if (error.getKind() == RetrofitError.Kind.NETWORK) {
            Toast.makeText(params[0], "Network Error", Toast.LENGTH_SHORT).show();
          }
        }
      });
      return null;
    }

  }
}
