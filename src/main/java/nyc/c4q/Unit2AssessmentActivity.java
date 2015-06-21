package nyc.c4q;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import nyc.c4q.R;

public class Unit2AssessmentActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.deckard);
    FlickrService service = new FlickrService();
    FlickrResponse response = service.GetInterestingPhotos(10, 10);
  }
}
