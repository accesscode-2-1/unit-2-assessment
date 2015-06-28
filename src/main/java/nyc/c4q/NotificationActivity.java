package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        final Button swipenotification = (Button) findViewById(R.id.swipenotification);
        final Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);



        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoCanel(view);
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeNotification(view);
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permanNotification(view);
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissPermanNotification(view);
            }
        });
    }


    public void autoCanel (View view) {
        Notification.Builder nb = new Notification.Builder(NotificationActivity.this);
            nb.setContentTitle("default@c4q.nyc");
            nb.setContentText("Touch me to dismiss me!");
            nb.setSmallIcon(R.drawable.c4qfavicon);
            nb.setAutoCancel(true);
            Notification notification = nb.build();
            notificationManager.notify(1,notification);
    }

    public void swipeNotification(View view) {
        Notification.Builder nb = new Notification.Builder(NotificationActivity.this);
            nb.setContentTitle("swipe@c4q.nyc");
            nb.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
            nb.setSmallIcon(R.drawable.c4qfavicon);
        Notification notification = nb.build();
        notificationManager.notify(1,notification);
    }

    public void permanNotification(View view) {
        Notification.Builder pn = new Notification.Builder(NotificationActivity.this);
            pn.setContentTitle("permanent@c4q.nyc");
            pn.setContentText("I'm staying planted right here.");
            pn.setSmallIcon(R.drawable.c4qfavicon);
            pn.setOngoing(true);
        Notification notification = pn.build();
        notificationManager.notify(1,notification);

    }

    public void dismissPermanNotification(View view) {
        notificationManager.cancelAll();

    }


}
