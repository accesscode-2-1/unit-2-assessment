package nyc.c4q;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;
    boolean permanentNotiOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        final Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);


        // TODO: Re-factor this into a switch if time allows.
        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAutoCancelNotification();
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSwipeNotification();
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPermanentNotification();
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (permanentNotiOn) {
                    notificationManager.cancel(ID_PERMANENT_NOTIFICATION);
                } else {
                    Toast.makeText(NotificationActivity.this, "No permanent notification found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAutoCancelNotification();
                createSwipeNotification();
                createPermanentNotification();
            }
        });
    }

    public void createAutoCancelNotification() {
        // PendingIntent required for touch-to-dismiss to work.
        PendingIntent notifyPIntent =
                PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

        NotificationCompat.Builder autoCancel =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentTitle("default@c4q.nyc")
                        .setContentText("Touch me to dismiss me!")
                        .setAutoCancel(true)
                        .setContentIntent(notifyPIntent);

        notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, autoCancel.build());
    }

    public void createSwipeNotification() {
        NotificationCompat.Builder autoCancel =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentTitle("swipe@c4q.nyc")
                        .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.")
                        .setAutoCancel(true);

        notificationManager.notify(ID_SWIPE_NOTIFICATION, autoCancel.build());
    }

    public void createPermanentNotification() {
        NotificationCompat.Builder permanent =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentTitle("permanent@c4q.nyc")
                        .setContentText("I'm staying planted right here.")
                        .setOngoing(true);

        notificationManager.notify(ID_PERMANENT_NOTIFICATION, permanent.build());
        permanentNotiOn = true;
    }
}
