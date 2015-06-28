package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;

    NotificationCompat.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        //make instance of notification manager
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mBuilder =
                        new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("default@c4q.nyc")
                                .setContentText("Touch me to dismiss me!")
                                .setAutoCancel(true);
                mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
                // Builds the notification and issues it.
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, mBuilder.build());
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mBuilder =
                        new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("swipe@c4q.nyc")
                                .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
                mBuilder.getNotification().flags |= Notification.FLAG_AUTO_CANCEL;
                // Builds the notification and issues it.
                notificationManager.notify(ID_SWIPE_NOTIFICATION, mBuilder.build());
            }


        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mBuilder =
                        new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("permanent@c4q.nyc")
                                .setContentText("I'm staying planted right here.")
                                .setOngoing(true);
                // Builds the notification and issues it.
                notificationManager.notify(ID_PERMANENT_NOTIFICATION, mBuilder.build());
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mBuilder =
                        new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("permanent@c4q.nyc")
                                .setContentText("I'm staying planted right here.");

                notificationManager.cancel(ID_PERMANENT_NOTIFICATION);
            }
        });

        buttonnotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mBuilder =
                        new NotificationCompat.Builder(NotificationActivity.this)
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!")
                                .setAutoCancel(true);
                // Builds the notification and issues it.
                notificationManager.notify(ID_BUTTON_NOTIFICATION, mBuilder.build());
            }
        });

    }

}

