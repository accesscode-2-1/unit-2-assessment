package nyc.c4q;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotNull;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;
import retrofit.MockRestAdapter;

//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.equalTo;

import static org.assertj.android.api.Assertions.assertThat;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Unit2AssessmentTests {

    private ActivityController<Unit2AssessmentActivity> activityController;
    private Activity activity;

    @Before
    public void setUp() {
        activityController = Robolectric.buildActivity(Unit2AssessmentActivity.class);
        activity = activityController.get();
    }

    @Test
    public void testSomething() {
        activityController.create();
        assertThat(activity).isNotNull();

        TextView tv = (TextView) activity.findViewById(R.id.text);
        assertThat(tv).isNotVisible();
//        assertThat(5, equalTo(3));
//        assertThat(view).isGone();
    }

    @Test
    public void buttonClickStartsAsyncTask() {
        activityController.create();
        assertThat(activity).isNotNull();
        Button button = (Button) activity.findViewById(R.id.button);
        assertNotNull("(@+id/button) should not be null", button);
        button.callOnClick();
        MockRestAdapter mockRestAdapter = MockRestAdapter.from(new FlickrService().getRestAdapter());
        mockRestAdapter.
    }
}
