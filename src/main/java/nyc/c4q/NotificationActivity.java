package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                builder.setContentTitle("default@c4q.nyc");
                builder.setContentText("Touch me to dismiss me!");
                builder.setSmallIcon(R.drawable.c4qfavicon);

                //disappears when the user read it
                builder.setAutoCancel(true);

                Notification notification = builder.build();
                notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, notification);

            }
        });


        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(NotificationActivity.this);
                builder.setContentTitle("swipe@c4q.nyc");
                builder.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
                builder.setSmallIcon(R.drawable.c4qfavicon);


                //disappears when the user read it
                builder.setAutoCancel(true);

                Notification notification = builder.build();
                notificationManager.notify(ID_SWIPE_NOTIFICATION, notification);

            }
        });

        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.cancel(NotificationActivity.ID_PERMANENT_NOTIFICATION);

            }
        });

    }
}



