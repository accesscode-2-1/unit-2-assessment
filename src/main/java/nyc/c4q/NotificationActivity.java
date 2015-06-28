package nyc.c4q;

import android.app.Activity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationActivity.this)
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentTitle("default@c4q.nyc")
                        .setContentText("Touch me to dismiss me!");

                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(
                        ID_AUTOCANCEL_NOTIFICATION,
                        mBuilder.build());

            }
        });
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(NotificationActivity.this)
                        .setSmallIcon(R.drawable.c4qfavicon)
                        .setContentTitle("swipe@c4q.nyc")
                        .setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");

                PendingIntent resultPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(
                        ID_SWIPE_NOTIFICATION,
                        mBuilder.build());
            }
        });
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

    }
}
