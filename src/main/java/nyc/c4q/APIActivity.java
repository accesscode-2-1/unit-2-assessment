package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import nyc.c4q.api.APIManager;
import nyc.c4q.api.FlickrResponse;
import nyc.c4q.api.FlickrService;
import nyc.c4q.api.Photo;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class APIActivity extends Activity {
    private FlickrService flickrService;
    private Button getinterestingphotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        flickrService = APIManager.getFlickrService();
        getinterestingphotos = (Button) findViewById(R.id.getinterestingphotos);

        getinterestingphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flickrService.getInterestingPhotos(20, 10, new Callback<FlickrResponse>() {
                    @Override
                    public void success(FlickrResponse flickrResponse, Response response) {
                        for (Photo p : flickrResponse.mPhotosInfo.mPhotos){
                            Log.d(Unit2AssessmentActivity.TAG, p.toString());
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });
    }
}
