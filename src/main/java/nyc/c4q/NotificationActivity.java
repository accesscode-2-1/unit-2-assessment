package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

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
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        final Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);



        Intent intent = new Intent(this, NotificationActivity.class);
        final PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);


        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification n  = new Notification.Builder(getApplicationContext())
                        .setContentTitle("default@c4q.nyc")
                        .setContentText("Touch me to dismiss me!")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentIntent(pIntent)
                        .setAutoCancel(true)
                        .addAction(R.drawable.c4qfavicon, "And more", pIntent).build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0, n);
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification n  = new Notification.Builder(getApplicationContext())
                        .setContentTitle("swipe@c4q.nyc")
                        .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentIntent(pIntent)
                        .setAutoCancel(false)
                        .addAction(R.drawable.c4qfavicon, "And more", pIntent).build();
                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(1, n);
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Notification n  = new Notification.Builder(getApplicationContext())
                        .setContentTitle("permanent@c4q.nyc")
                        .setContentText("I'm staying planted right here.")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentIntent(pIntent).setOngoing(true)
                        .setAutoCancel(false)
                        .addAction(R.drawable.c4qfavicon, "And more", pIntent).build();


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                n.flags = Notification.FLAG_ONGOING_EVENT;
                notificationManager.notify(0, n);
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification n  = new Notification.Builder(getApplicationContext())
                        .setContentTitle("permanent@c4q.nyc")
                        .setContentText("I'm staying planted right here.")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentIntent(pIntent).setOngoing(false)
                        .setAutoCancel(true)
                        .addAction(R.drawable.c4qfavicon, "And more", pIntent).build();


                NotificationManager notificationManager =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                notificationManager.notify(0, n);

            }
        });






    }
}
