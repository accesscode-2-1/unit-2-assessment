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
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder _default = new NotificationCompat.Builder(NotificationActivity.this);
                _default.setContentTitle("default@c4q.nyc");
                _default.setContentText("Touch me to dismiss me!");
                _default.setSmallIcon(R.drawable.c4qfavicon);
                _default.setAutoCancel(true);

                Notification d = _default.build();
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, d);
            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder swipe = new NotificationCompat.Builder(NotificationActivity.this);
                swipe.setContentTitle("swipe@c4q.nyc");
                swipe.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
                swipe.setSmallIcon(R.drawable.c4qfavicon);
                Notification s = swipe.build();
                notificationManager.notify(ID_SWIPE_NOTIFICATION, s);
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder permanent = new NotificationCompat.Builder(NotificationActivity.this);
                permanent.setContentTitle("permanent@c4q.nyc");
                permanent.setContentText("I'm staying planted right here.");
                permanent.setSmallIcon(R.drawable.c4qfavicon);
                permanent.setOngoing(true);

                Notification p = permanent.build();
                notificationManager.notify(ID_PERMANENT_NOTIFICATION, p);
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
