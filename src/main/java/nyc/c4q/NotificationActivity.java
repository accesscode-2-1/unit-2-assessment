package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.shadows.ShadowNotificationManager;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        final Button swipenotification = (Button) findViewById(R.id.swipenotification);
        final Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        final Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);


        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autocancelnotification();
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipenotification();
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permanentnotification();
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismisspermanentnotification();
            }
        });
    }

    private void dismisspermanentnotification() {
        //ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);

    }

    private void permanentnotification() {
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        n.setContentTitle("permanent@c4q.nyc");
        n.setContentText("I'm staying planted right here.");
        n.setSmallIcon(R.drawable.c4qfavicon);
        //n.isOngoing(true);
    }

    private void swipenotification() {
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        n.setContentTitle("swipe@c4q.nyc");
        n.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
        n.setSmallIcon(R.drawable.c4qfavicon);
        n.getRealNotification().flags = 0;
    }

    private void autocancelnotification() {
        // Notification.Builder builder = new Notification.Builder(this);
        ShadowNotificationManager snm = Robolectric.shadowOf(notificationManager);
        //snm.size() = 1;

        ShadowNotification n = Robolectric.shadowOf(snm.getAllNotifications().get(0));

        n.setContentTitle("default@c4q.nyc");
        n.setContentText("Touch me to dismiss me!");
        n.setSmallIcon(R.drawable.c4qfavicon);
        n.getRealNotification().flags = Notification.FLAG_AUTO_CANCEL;
    }
}
