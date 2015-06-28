package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.RenderScript;
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
        final Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        final Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

        autocancelnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = "default@c4q.nyc";
                String text = "Touch me to dismiss me!";


                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle("default@c4q.nyc")
                                .setContentText(text);

                mBuilder.setAutoCancel(true);


                Notification autoNotification = mBuilder.build();
                notificationManager.notify(1, autoNotification);
            }
        });





        swipenotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "swipe@c4q.nyc";
                String text = "Swipe right if you want to meet me. Otherwise, I'm not going away.";

                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.c4qfavicon)
                                .setContentTitle(title)
                                .setContentText(text);



                Notification swipeNotification = mBuilder.build();
                notificationManager.notify(2, swipeNotification);
            }
        });




        permanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "permanent@c4q.nyc";
                String text = "I'm staying planted right here.";

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                mBuilder.setContentTitle(title);
                mBuilder.setContentText(text);
                mBuilder.setSmallIcon(R.drawable.c4qfavicon);


                mBuilder.setOngoing(true);
                Notification permaNotification = mBuilder.build();
                notificationManager.notify(3, permaNotification);
            }
        });



        dismisspermanentnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.cancel(3);
            }
        });





        buttonnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = "permanent@c4q.nyc";
                String text = "I'm staying planted right here.";

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext());
                mBuilder.setSmallIcon(R.drawable.c4qfavicon);
                mBuilder.setContentTitle(title);
                mBuilder.setContentText(text);

                mBuilder.setOngoing(true);
                Notification buttonNotification = mBuilder.build();
                notificationManager.notify(4, buttonNotification);
            }
        });




    }
}
