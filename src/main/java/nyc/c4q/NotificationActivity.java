package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
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
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification auto = new Notification.Builder(getApplicationContext())
                        .setContentTitle("default@c4q.nyc")
                        .setContentText("Touch me to dismiss me!")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .build();
                auto.flags = Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, auto);
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification swipe = new Notification.Builder(getApplicationContext())
                        .setContentTitle("swipe@c4q.nyc")
                        .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .build();
                notificationManager.notify(ID_SWIPE_NOTIFICATION, swipe);
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notification perm = new Notification.Builder(getApplicationContext())
                        .setContentTitle("permanent@c4q.nyc")
                        .setContentText("I'm staying planted right here.")
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setOngoing(true)
                        .build();
                notificationManager.notify(ID_PERMANENT_NOTIFICATION, perm);
            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.cancel(ID_PERMANENT_NOTIFICATION);
            }
        });
    }
}