package nyc.c4q;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.AndroidManifest;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowEnvironment;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.shadows.ShadowNotificationManager;
import org.robolectric.util.ActivityController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import nyc.c4q.json.Zipcode;
import nyc.c4q.json.ZipcodeDeserializer;

import static org.assertj.android.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Unit2AssessmentTests {

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


    // ======================= LISTVIEW TESTS =============================

    @Test
    public void test01ListViewActvityCheckFirstLevelHas2Views() {
        LinearLayout activity_listview = (LinearLayout) Helpers.findViewByIdString(listViewActivity, "activity_listview");
        assertThat(activity_listview.getChildCount(), equalTo(2));
        assertThat(activity_listview).hasOrientation(LinearLayout.VERTICAL);
    }

    @Test
    public void test02ListViewActivityCheckFirstLevelLayoutViewHeader() {
        LinearLayout header = (LinearLayout) Helpers.findViewByIdString(listViewActivity, "header");
        assertThat(header, notNullValue());

        assertThat(header.getLayoutParams()).hasWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(header.getLayoutParams()).hasHeight(0);
        assertThat(((LinearLayout.LayoutParams) header.getLayoutParams()).weight, equalTo(1.0f));
        assertThat(header).hasOrientation(LinearLayout.HORIZONTAL);
    }
    @Test
    public void test03ListViewActivityCheckFirstLevelLayoutViewList() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        assertThat(list, notNullValue());

        assertThat(list.getLayoutParams()).hasWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(list.getLayoutParams()).hasHeight(0);
        assertThat(((LinearLayout.LayoutParams) list.getLayoutParams()).weight, equalTo(9.0f));
    }

    @Test
    public void test04ListViewActivityCheckSecondLevelLayoutViewTextLog() {
        TextView textLog = (TextView) Helpers.findViewByIdString(listViewActivity, "textLog");

        assertThat(textLog, notNullValue());

        assertThat(textLog.getLayoutParams()).hasWidth(0);
        assertThat(textLog.getLayoutParams()).hasHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(((LinearLayout.LayoutParams) textLog.getLayoutParams()).weight, equalTo(3.0f));
        assertThat(textLog).containsText("You have not clicked anything.");
    }

    @Test
    public void test05ListViewActivityCheckSecondLevelLayoutViewAdapterCount() {
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");

        assertThat(adapterCount, notNullValue());

        assertThat(adapterCount.getLayoutParams()).hasWidth(0);
        assertThat(adapterCount.getLayoutParams()).hasHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        assertThat(((LinearLayout.LayoutParams) adapterCount.getLayoutParams()).weight, equalTo(1.0f));
        assertThat(adapterCount.getInputType(), equalTo(InputType.TYPE_CLASS_PHONE));
    }

    @Test
    public void test06ListViewActivityCheckAdapterCount() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));
    }

    @Test
    public void test07ListViewActivityCheckRowBackgroundColors() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View v = adapter.getView(i, null, list);
            assertThat(v, notNullValue());
            assertThat(v.getBackground(), instanceOf(ColorDrawable.class));
            ColorDrawable background = (ColorDrawable) v.getBackground();
            assertThat(background.getColor(), equalTo(Color.parseColor(ListViewActivity.COLORS[i])));
        }
    }

    @Test
    public void test08ListViewActivityViewClickChangesTextLog() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        TextView textLog = (TextView) Helpers.findViewByIdString(listViewActivity, "textLog");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < ListViewActivity.COLORS.length; i++) {
            View itemView = list.getChildAt(i);
            list.performItemClick(itemView, i, adapter.getItemId(i));
            assertThat(textLog).containsText(String.format("You clicked on Item(position=%s, color=%s)", i, ListViewActivity.COLORS[i]));
        }
    }

    @Test
    public void test09ListViewActivityAdapterCountAdjustableInput() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        for (int i = 0; i < 10 * ListViewActivity.COLORS.length; i++) {
            adapterCount.setText(Integer.toString(i));
            assertThat(adapter.getCount(), equalTo(i));
        }
    }

    @Test
    public void test10ListViewActivityAdapterCountInvalidInput() {
        ListView list = (ListView) Helpers.findViewByIdString(listViewActivity, "list");
        EditText adapterCount = (EditText) Helpers.findViewByIdString(listViewActivity, "adapterCount");
        ListAdapter adapter = list.getAdapter();
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        adapterCount.setText("wonk");
        assertThat(adapter.getCount(), equalTo(ListViewActivity.COLORS.length));

        adapterCount.setText("20");
        adapterCount.setText("fifteen");
        assertThat(adapter.getCount(), equalTo(20));

        adapterCount.setText("30");
        adapterCount.setText("zxcv");
        assertThat(adapter.getCount(), equalTo(30));
    }

    // ======================= NETWORK TESTS =============================
    static final String urlParams = "custname=james+dean&custtel=347-841-6090&custemail=hello%40c4q.nyc&size=small&topping=cheese&delivery=18%3A15&comments=Leave+it+by+the+garage+door.+Don't+ask+any+questions.";

    @Test
    public void test11AppHasInternetPermissions() {
        AndroidManifest manifest = Robolectric.getShadowApplication().getAppManifest();
        List<String> usedPermissions = manifest.getUsedPermissions();
        assertThat(usedPermissions, hasItem(Manifest.permission.INTERNET));
    }

    @Test
    public void test12NetworkActivityHTTPUrlConnectionGET() {
        Button httpbinget = (Button) Helpers.findViewByIdString(networkActivity, "httpbinget");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbinget.callOnClick();

        assertThat(httptextlog).containsText(urlParams);
    }

    @Test
    public void test13NetworkActivityHTTPUrlConnectionGETOKHTTP() throws Exception {
        Button httpbingetokhttp = (Button) Helpers.findViewByIdString(networkActivity, "httpbingetokhttp");
        TextView httptextlog = (TextView) Helpers.findViewByIdString(networkActivity, "httptextlog");
        httpbingetokhttp.callOnClick();

        String replaced = urlParams.replaceAll("\\+"," ");
        assertThat(httptextlog).containsText(replaced);
    }

    @Test
    public void test14Missing() {
        // TODO
        // FREE question for now.
    }

    @Test
    public void test15Missing() {
        // TODO
        // FREE question for now.
    }

    // ======================= JSON TESTS =============================
    // C4Q's Zipcode.
    public static final String JSON_ZIPCODE = "{\"_id\":\"11101\",\"city\":\"ASTORIA\",\"loc\":[-73.939393,40.750316],\"pop\":23142,\"state\":\"NY\"}";

    @Test
    public void test16JSONActivityCreateJSONMappingID() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new Gson();
        Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);

        assertThat(Zipcode.class.getField("_id").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("_id").get(z), equalTo("11101"));

        assertThat(Zipcode.class.getField("city").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("city").get(z), equalTo("ASTORIA"));

        assertThat(Zipcode.class.getField("state").get(z), instanceOf(String.class));
        assertThat((String) Zipcode.class.getField("state").get(z), equalTo("NY"));

        assertThat(Zipcode.class.getField("pop").get(z), instanceOf(int.class));
        assertThat((Integer) Zipcode.class.getField("pop").get(z), equalTo(23142));
    }

    @Test
    public void test17JSONActivityCreateJSONMappingLoc() throws NoSuchFieldException, IllegalAccessException {
        Gson gson = new Gson();
        Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);

        assertThat(Zipcode.class.getField("loc").get(z), instanceOf(double[].class));
        assertThat(((double[]) Zipcode.class.getField("loc").get(z))[0], closeTo(-73.939393, 0.01));
        assertThat(((double[]) Zipcode.class.getField("loc").get(z))[1], closeTo(40.750316, 0.01));
    }

    @Test
    public void test18JSONActivityCheckAddJSONButton() {
        Gson gson = new Gson();
        TextView _id = (TextView) jsonActivity.findViewById(R.id.field_idvalue);
        TextView pop = (TextView) jsonActivity.findViewById(R.id.fieldpopvalue);
        TextView city = (TextView) jsonActivity.findViewById(R.id.fieldcityvalue);
        TextView state = (TextView) jsonActivity.findViewById(R.id.fieldstatevalue);
        TextView _lat = (TextView) jsonActivity.findViewById(R.id.fieldloclatvalue);
        TextView _long = (TextView) jsonActivity.findViewById(R.id.fieldloclongvalue);
        Button addjson = (Button) jsonActivity.findViewById(R.id.addjson);

        _id.setText("11101");
        pop.setText("23142");
        city.setText("ASTORIA");
        state.setText("NY");
        _lat.setText("-73.939393");
        _long.setText("40.750316");

        addjson.callOnClick();

        //TODO un-hack
        String result = gson.toJson(jsonActivity.zipcodes.get(0), Zipcode.class);
        assertThat(result, containsString("\"_id\":\"11101\""));
        assertThat(result, containsString("\"pop\":23142"));
        assertThat(result, containsString("\"city\":\"ASTORIA\""));
        assertThat(result, containsString("\"state\":\"NY\""));
        assertThat(result, containsString("\"loc\":[-73.939393,40.750316]"));
    }

    @Test
    public void test19JSONActivityCheckSaveJSONButton() throws FileNotFoundException {
        ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);

        TextView _id = (TextView) jsonActivity.findViewById(R.id.field_idvalue);
        TextView pop = (TextView) jsonActivity.findViewById(R.id.fieldpopvalue);
        TextView city = (TextView) jsonActivity.findViewById(R.id.fieldcityvalue);
        TextView state = (TextView) jsonActivity.findViewById(R.id.fieldstatevalue);
        TextView _lat = (TextView) jsonActivity.findViewById(R.id.fieldloclatvalue);
        TextView _long = (TextView) jsonActivity.findViewById(R.id.fieldloclongvalue);
        Button addjson = (Button) jsonActivity.findViewById(R.id.addjson);
        Button savejson = (Button) jsonActivity.findViewById(R.id.savejson);

        _id.setText("11101");
        pop.setText("23142");
        city.setText("ASTORIA");
        state.setText("NY");
        _lat.setText("-73.939393");
        _long.setText("40.750316");

        addjson.callOnClick();
        savejson.callOnClick();
        File directory = jsonActivity.getExternalCacheDir();
        File file = new File(directory, "zipcodes.json");
        String results = new Scanner(file).useDelimiter("\\Z").next();

        // TODO un-hack
        assertThat(results, containsString("\"_id\":\"11101\""));
        assertThat(results, containsString("\"pop\":23142"));
        assertThat(results, containsString("\"city\":\"ASTORIA\""));
        assertThat(results, containsString("\"state\":\"NY\""));
        assertThat(results, containsString("\"loc\":[-73.939393,40.750316]"));
    }

    @Test
    public void test20JSONActivityCheckLoadJSONButton() throws IOException {
        ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);
        Gson gson = new Gson();
        Button loadjson = (Button) jsonActivity.findViewById(R.id.loadjson);

        File directory = jsonActivity.getExternalCacheDir();
        File file = new File(directory, "zipcodes.json");

        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.write(String.format("[%s]",JSON_ZIPCODE));
        fileWriter.close();

        loadjson.callOnClick();

        String result = gson.toJson(jsonActivity.zipcodes.get(0), Zipcode.class);

        assertThat(result, containsString("\"_id\":\"11101\""));
        assertThat(result, containsString("\"pop\":23142"));
        assertThat(result, containsString("\"city\":\"ASTORIA\""));
        assertThat(result, containsString("\"state\":\"NY\""));
        assertThat(result, containsString("\"loc\":[-73.939393,40.750316]"));
    }

    @Test
    public void test21NotificationActivityCreateAutoCancelNotification(){

        Button autocancelnotification = (Button) notificationActivity.findViewById(R.id.autocancelnotification);

        autocancelnotification.callOnClick();
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);

        assertThat(snm.size(), equalTo(1));
        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        assertThat(n.getContentTitle().toString(), containsString("default@c4q.nyc"));
        assertThat(n.getContentText().toString(), containsString("Touch me to dismiss me!"));
        assertThat(n.getSmallIcon(), equalTo(R.drawable.c4qfavicon));

        assertThat(n.getRealNotification().flags, equalTo(Notification.FLAG_AUTO_CANCEL));
    }

    @Test
    public void test22NotificationActivityCreateSwipeNotification(){

        Button swipenotification = (Button) notificationActivity.findViewById(R.id.swipenotification);

        swipenotification.callOnClick();
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);

        assertThat(snm.size(), equalTo(1));
        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        assertThat(n.getContentTitle().toString(), containsString("swipe@c4q.nyc"));
        assertThat(n.getContentText().toString(), containsString("Swipe right if you want to meet me. Otherwise, I'm not going away."));
        assertThat(n.getSmallIcon(), equalTo(R.drawable.c4qfavicon));

        assertThat(n.getRealNotification().flags, equalTo(0));
    }

    @Test
    public void test23NotificationActivityCreatePermanentNotification(){

        Button permanentnotification = (Button) notificationActivity.findViewById(R.id.permanentnotification);

        permanentnotification.callOnClick();
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);

        assertThat(snm.size(), equalTo(1));
        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        assertThat(n.getContentTitle().toString(), containsString("permanent@c4q.nyc"));
        assertThat(n.getContentText().toString(), containsString("I'm staying planted right here."));
        assertThat(n.getSmallIcon(), equalTo(R.drawable.c4qfavicon));
        assertThat(n.isOngoing(), equalTo(true));
    }

    @Test
    public void test24NotificationActivityDismissPermanentNotification(){

        Button permanentnotification = (Button) notificationActivity.findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) notificationActivity.findViewById(R.id.dismisspermanentnotification);

        permanentnotification.callOnClick();
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        assertThat(snm.size(), equalTo(1));

        dismisspermanentnotification.callOnClick();
        assertThat(snm.size(), equalTo(0));
    }

    @Test
    public void test25NotificationActivityShowMultipleNotificationsAtTheSameTime(){

        Button autocancelnotification = (Button) notificationActivity.findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) notificationActivity.findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) notificationActivity.findViewById(R.id.permanentnotification);

        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        assertThat(snm.size(), equalTo(0));
        autocancelnotification.callOnClick();
        assertThat(snm.size(), equalTo(1));
        swipenotification.callOnClick();
        assertThat(snm.size(), equalTo(2));
        permanentnotification.callOnClick();
        assertThat(snm.size(), equalTo(3));
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
        Zipcode z = gson.fromJson(JSON_ZIPCODE, Zipcode.class);

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
