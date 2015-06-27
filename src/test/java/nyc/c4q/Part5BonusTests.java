package nyc.c4q;

import android.app.NotificationManager;
import android.content.Context;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNotificationManager;
import org.robolectric.util.ActivityController;

import nyc.c4q.json.Zipcode;
import nyc.c4q.json.ZipcodeDeserializer;

import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part5BonusTests {

    private ActivityController<Unit2AssessmentActivity> activityController;
    private Unit2AssessmentActivity activity;

    private ActivityController<ListViewActivity> listViewActivityController;
    private ListViewActivity listViewActivity;

    private ActivityController<NetworkActivity> networkActivityController;
    private NetworkActivity networkActivity;

    private ActivityController<JSONActivity> jsonActivityController;
    private JSONActivity jsonActivity;

    private ActivityController<NotificationActivity> notificationActivityController;
    private NotificationActivity notificationActivity;

    private NotificationManager notificationManager;

    @Before
    public void setUp() {
        activityController = Robolectric.buildActivity(Unit2AssessmentActivity.class);
        activityController.setup();
        activity = activityController.get();

        listViewActivityController = Robolectric.buildActivity(ListViewActivity.class);
        listViewActivityController.setup();
        listViewActivity = listViewActivityController.get();

        networkActivityController = Robolectric.buildActivity(NetworkActivity.class);
        networkActivityController.setup();
        networkActivity = networkActivityController.get();

        jsonActivityController = Robolectric.buildActivity(JSONActivity.class);
        jsonActivityController.setup();
        jsonActivity = jsonActivityController.get();

        notificationActivityController = Robolectric.buildActivity(NotificationActivity.class);
        notificationActivityController.setup();
        notificationActivity = notificationActivityController.get();
        notificationManager = (NotificationManager) Robolectric.application.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Test
    public void testBonus01ListViewActivityCheckSecondLevelLayoutViewRowPadding() {
        EditText rowPadding = (EditText) Helpers.findViewByIdString(listViewActivity, "rowPadding");

        assertThat(rowPadding, notNullValue());

        assertThat(rowPadding.getLayoutParams()).hasWidth(0);
        assertThat(rowPadding.getLayoutParams()).hasHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(((LinearLayout.LayoutParams) rowPadding.getLayoutParams()).weight, equalTo(1.0f));
        assertThat(rowPadding.getInputType(), equalTo(InputType.TYPE_CLASS_PHONE));
    }

//    // TODO fix test, figure out why padding is always equal to R.integer.rowPadding
//    @Test
//    public void testBonus02ListViewActivityRowPaddingAdjustableInput() {
//        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
//        EditText rowPadding = (EditText) Helpers.findViewByIdString(listViewActivity, "rowPadding");
//        ListAdapter adapter = list.getAdapter();
//        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));
//
//        for (int i = 0; i < 100; i++) {
//            rowPadding.setText(Integer.toString(i));
////            Unit2AssessmentActivity.ColoredTileAdapter cta = (ListViewActivity.ColoredTileAdapter) adapter;
////            cta.setPadding(i);
////            cta.notifyDataSetChanged();
//            for (int j = 0; j < list.getChildCount(); j++) {
//                View row = list.getChildAt(j);
//                assertThat(row).hasPaddingBottom(i);
//                assertThat(row).hasPaddingTop(i);
//            }
//        }
//    }

    @Test
    public void testBonus03NetworkActivityHTTPUrlConnectionPOST() {
        Button httpbinpost = (Button) Helpers.findViewByIdString(networkActivity, "httpbinpost");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbinpost.callOnClick();

        // TODO figure out a less hacky way to verify the contents of the JSON response.
        assertThat(httptextlog).containsText("\"data\": \"\"");
        assertThat(httptextlog).containsText("\"comments\": \"Leave it by the garage door. Don't ask any questions.\"");
        assertThat(httptextlog).containsText("\"custemail\": \"hello@c4q.nyc\"");
        assertThat(httptextlog).containsText("\"custname\": \"james dean\"");
        assertThat(httptextlog).containsText("\"custtel\": \"347-841-6090\"");
        assertThat(httptextlog).containsText("\"delivery\": \"18:15\"");
        assertThat(httptextlog).containsText("\"size\": \"small\"");
        assertThat(httptextlog).containsText("\"topping\": \"cheese\"");
    }

    @Test
    public void testBonus04NetworkActivityHTTPUrlConnectionGETOKPOST() {
        Button httpbinpostokhttp = (Button) Helpers.findViewByIdString(networkActivity, "httpbinpostokhttp");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbinpostokhttp.callOnClick();

        // TODO figure out a less hacky way to verify the contents of the JSON response.
        assertThat(httptextlog).containsText("\"data\": \"\"");
        assertThat(httptextlog).containsText("\"comments\": \"Leave it by the garage door. Don't ask any questions.\"");
        assertThat(httptextlog).containsText("\"custemail\": \"hello@c4q.nyc\"");
        assertThat(httptextlog).containsText("\"custname\": \"james dean\"");
        assertThat(httptextlog).containsText("\"custtel\": \"347-841-6090\"");
        assertThat(httptextlog).containsText("\"delivery\": \"18:15\"");
        assertThat(httptextlog).containsText("\"size\": \"small\"");
        assertThat(httptextlog).containsText("\"topping\": \"cheese\"");
    }

    @Test
    public void testBonus05JSONActivityCreateJSONMappingLatlong() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Zipcode.class, new ZipcodeDeserializer()).create();
        Zipcode z = gson.fromJson(Part3JSONActivityTests.JSON_ZIPCODE, Zipcode.class);

        assertThat(Zipcode.class.getField("_lat").get(z), instanceOf(double.class));
        assertThat(Zipcode.class.getField("_long").get(z), instanceOf(double.class));
        assertThat(((double) Zipcode.class.getField("_lat").get(z)), closeTo(-73.939393, 0.01));
        assertThat(((double) Zipcode.class.getField("_long").get(z)), closeTo(40.750316, 0.01));
    }

    @Test
    public void testBonus06NotificationActivityShowNotificationsWithID(){
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);

        notificationActivity.findViewById(R.id.autocancelnotification).callOnClick();
        notificationActivity.findViewById(R.id.swipenotification).callOnClick();
        notificationActivity.findViewById(R.id.permanentnotification).callOnClick();
        notificationActivity.findViewById(R.id.buttonnotification).callOnClick();
        assertThat(snm.getNotification(NotificationActivity.ID_AUTOCANCEL_NOTIFICATION), notNullValue());
        assertThat(snm.getNotification(NotificationActivity.ID_SWIPE_NOTIFICATION), notNullValue());
        assertThat(snm.getNotification(NotificationActivity.ID_PERMANENT_NOTIFICATION), notNullValue());
        assertThat(snm.getNotification(NotificationActivity.ID_BUTTON_NOTIFICATION), notNullValue());
    }

    @Test
    public void testBonus07NotificationActivityShowNotificationWithActions(){
        Button buttonnotification = (Button) notificationActivity.findViewById(R.id.buttonnotification);
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        buttonnotification.callOnClick();
        assertThat(snm.getAllNotifications().get(0).actions.length, equalTo(3));
    }

}
