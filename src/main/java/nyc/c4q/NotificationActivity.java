package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {
    public NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;

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
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                builder.setContentTitle("default@c4q.nyc");
                builder.setContentText("Touch me to dismiss me!");
                builder.setSmallIcon(R.drawable.c4qfavicon);
                PendingIntent notifyPIntent =
                        PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
                builder.setContentIntent(notifyPIntent);
                builder.setAutoCancel(true);

                notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, builder.build());

            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Notification.Builder notice2=new Notification.Builder(getApplicationContext())
                        .setContentTitle("swipe@c4q.nyc")
                        .setAutoCancel(true)
                        .setContentText("swipe@c4q.nyc")
                        .setSmallIcon(R.drawable.c4qfavicon);

                notificationManager.notify(ID_SWIPE_NOTIFICATION,notice2.build());

            }
        });


        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                builder.setContentTitle("permanent@c4q.nyc");
                builder.setContentText("I'm staying planted right here.");
                builder.setSmallIcon(R.drawable.c4qfavicon);
                PendingIntent notifyPIntent =
                        PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
                builder.setContentIntent(notifyPIntent);
                builder.setOngoing(true);

                notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(ID_PERMANENT_NOTIFICATION, builder.build());
            }
        });


        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancel(ID_PERMANENT_NOTIFICATION);
            }
        });
    }


}
