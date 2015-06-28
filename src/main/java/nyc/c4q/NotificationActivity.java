package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;
    public static final int NOTIFICATION_ID = 1234;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);


        autocancelnotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                cancelNotification();
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                swipeNotification();
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ongoingNotification();
            }
        });
    }


    private void cancelNotification() {

        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("default@c4q.nyc")
                .setSmallIcon(R.drawable.c4qfavicon)
                .setContentText("Touch me to dismiss me!")
                .setContentIntent(pIntent).getNotification();
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);

    }

    private void swipeNotification() {

        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("swipe@c4q.nyc")
                .setSmallIcon(R.drawable.c4qfavicon)
                .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.")
                .setContentIntent(pIntent).getNotification();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);

    }

    private void ongoingNotification() {

        Intent intent = new Intent();
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("permanent@c4q.nyc")
                .setSmallIcon(R.drawable.c4qfavicon)
                .setContentText("I'm staying planted right here.")
                .setContentIntent(pIntent).getNotification();

        noti.flags = Notification.FLAG_ONGOING_EVENT;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, noti);

    }

}
