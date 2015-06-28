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


//        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        Notification.Builder builder = new  Notification.Builder(NotificationActivity.this);
//        builder.setContentTitle("default@c4q.nyc");
//        builder.setContentText("Touch me to dismiss me!");
//        builder.setSmallIcon(R.drawable.c4qfavicon);
//        builder.setAutoCancel(true);
//
//        Notification notification = builder.build();
//        notificationManager.notify(1, notification);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);



    }
    public void sn (View v){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new  Notification.Builder(NotificationActivity.this);
        builder.setContentTitle("swipe@c4q.nyc");
        builder.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
        builder.setSmallIcon(R.drawable.c4qfavicon);

        Notification.Builder builder2 = new  Notification.Builder(NotificationActivity.this);
        builder2.setContentTitle("permanent@c4q.nyc");
        builder2.setContentText("I'm staying planted right here.");
        builder2.setSmallIcon(R.drawable.c4qfavicon);
        builder2.setOngoing(true);

        Notification notification2 = builder2.build();
        notificationManager.notify(1, notification2);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);

    }
    public void pn (View v){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new  Notification.Builder(NotificationActivity.this);
        builder.setContentTitle("permanent@c4q.nyc");
        builder.setContentText("I'm staying planted right here.");
        builder.setSmallIcon(R.drawable.c4qfavicon);
        builder.setOngoing(true);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);

        Notification.Builder builder2 = new  Notification.Builder(NotificationActivity.this);
        builder2.setContentTitle("permanent@c4q.nyc");
        builder2.setContentText("I'm staying planted right here.");
        builder2.setSmallIcon(R.drawable.c4qfavicon);
        builder2.setOngoing(true);

        Notification notification2 = builder2.build();
        notificationManager.notify(1, notification2);

    }
    public void dp (View v){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
    }
    public void ac (View v){
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new  Notification.Builder(NotificationActivity.this);
        builder.setContentTitle("default@c4q.nyc");
        builder.setContentText("Touch me to dismiss me!");
        builder.setSmallIcon(R.drawable.c4qfavicon);
        builder.setAutoCancel(true);

        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }
}
