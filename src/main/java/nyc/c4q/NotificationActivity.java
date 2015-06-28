package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;
    Notification autoCancel;
    Notification swipeNotification;
    Notification permNotification;
    Notification btnNotification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Context c = getApplicationContext();
        Notification.Builder builder = new Notification.Builder(c).setSmallIcon(R.drawable.c4qfavicon);
        autoCancel = builder.setAutoCancel(true)
                .setContentTitle("default@c4q.nyc").setContentText("Touch me to dismiss me!").
                        build();

        swipeNotification = builder.setAutoCancel(false).setContentTitle("swipe@c4q.nyc").setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.")
                .build();



        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        permNotification = new Notification.Builder(c)
                .setContentTitle("permanent@c4q.nyc")
                .setSmallIcon(R.drawable.c4qfavicon).setOngoing(true)
                .setContentText("I'm staying planted right here.").build();

        btnNotification = new Notification.Builder(c)
                .setContentTitle("permanent@c4q.nyc")
                .setSmallIcon(R.drawable.c4qfavicon).setOngoing(true)
                .setContentText("I'm staying planted right here").build();




        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        final Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);


        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, autoCancel);
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(ID_SWIPE_NOTIFICATION, swipeNotification);
            }
        });
        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(ID_PERMANENT_NOTIFICATION, permNotification);
            }
        });
        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancelAll();
            }
        });
        buttonnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notifyAll();
            }
        });




    }
}
