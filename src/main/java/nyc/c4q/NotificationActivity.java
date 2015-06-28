package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
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
        NotificationCompat.Builder _default = new NotificationCompat.Builder(this);
        _default.setContentTitle("default@c4q.nyc");
        _default.setContentText("Touch me to dismiss me!");
        _default.setSmallIcon(R.drawable.c4qfavicon);
        _default.setAutoCancel(true);

        NotificationCompat.Builder swipe = new NotificationCompat.Builder(this);
        swipe.setContentTitle("swipe@c4q.nyc");
        swipe.setContentText("Swipe right if you want to meet me. Otherwise, I'm not going away.");
        swipe.setSmallIcon(R.drawable.c4qfavicon);

        NotificationCompat.Builder permanent = new NotificationCompat.Builder(this);
        permanent.setContentTitle("permanent@c4q.nyc");
        permanent.setContentText("I'm staying planted right here.");
        permanent.setSmallIcon(R.drawable.c4qfavicon);
        permanent.setOngoing(true);

        Notification d = _default.build();
        Notification s = swipe.build();
        Notification p = permanent.build();
        notificationManager.notify(ID_AUTOCANCEL_NOTIFICATION, d);
//        notificationManager.notify(ID_SWIPE_NOTIFICATION, s);
//        notificationManager.notify(ID_PERMANENT_NOTIFICATION, p);

        Button autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        Button swipenotification = (Button) findViewById(R.id.swipenotification);
        Button permanentnotification = (Button) findViewById(R.id.permanentnotification);
        Button dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

    }
//
//    text = (TextView) findViewById(R.id.text);
//    start = (Button) findViewById(R.id.start);
//
//    start.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            startDelay();
//        }
//    });
//}
//
//    public void startDelay() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                showNotification();
//            }
//        }, 5000);
//    }
//
//    private void showNotification() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        updateNotification("The time is...", dateFormat.format(new Date()));
//    }
//
//    private void updateNotification(String text, String contentText) {
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle(text);
//        builder.setContentText(contentText);
//        builder.setSmallIcon(R.drawable.ic_stat_action_accessibility);
//
//        Intent intent = SecondActivity.createIntent(this);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        builder.setAutoCancel(true);
//
//        // add large iamge
//        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
//        Bitmap image;
//        style.bigPicture(image);
//        builder.setStyle(style);
//
//        // Gmail style
//        NotificationCompat.InboxStyle style2 = new NotificationCompat.InboxStyle();
//        style2.addLine("LIne1");
//        style2.addLine("Line2");
//        style2.setSummaryText("Summary");
//
//        // lock screen hide content LOLLOPOP+
//        builder.setVisibility(Notification.VISIBILITY_PRIVATE);
//
//        Notification notification = builder.build();
//        manager.notify(NOTIFICATION_ID, notification);
//    }
}
