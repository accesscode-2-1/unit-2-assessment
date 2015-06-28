package nyc.c4q;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.widget.Button;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.shadows.ShadowNotificationManager;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)
public class Part4NotificationActivityTests {

    private NotificationActivity notificationActivity;
    private NotificationManager notificationManager;

    @Before
    public void setUp() {
        notificationActivity = Robolectric.buildActivity(NotificationActivity.class).setup().get();
        notificationManager = (NotificationManager) Robolectric.application.getSystemService(Context.NOTIFICATION_SERVICE);
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

}
