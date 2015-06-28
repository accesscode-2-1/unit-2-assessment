package nyc.c4q;

import android.app.Activity;
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

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(NotificationActivity.this);

                notificationBuilder.setSmallIcon(R.drawable.c4qfavicon);
                notificationBuilder.setContentTitle("default@c4q.nyc");
                notificationBuilder.setContentText("Touch me to dismiss me!");
                notificationBuilder.setAutoCancel(true);


                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, notificationBuilder.build());

            }
        });

        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(NotificationActivity.this);

                notificationBuilder.setSmallIcon(R.drawable.c4qfavicon);
                notificationBuilder.setContentTitle("swipe@c4q.nyc");
                notificationBuilder.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");

                notificationManager.notify(ID_SWIPE_NOTIFICATION, notificationBuilder.build());
            }
        });

        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(NotificationActivity.this);

                notificationBuilder.setSmallIcon(R.drawable.c4qfavicon);
                notificationBuilder.setContentTitle("permanent@c4q.nyc");
                notificationBuilder.setContentText("I'm staying planted right here.");
                notificationBuilder.setOngoing(true);

                notificationManager.notify(ID_PERMANENT_NOTIFICATION, notificationBuilder.build());

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
