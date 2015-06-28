package nyc.c4q;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationActivity extends Activity {
    NotificationManager notificationManager;
    private Button autocancelnotification;
    private Button swipenotification;
    private Button permanentnotification;
    private Button dismisspermanentnotification;
    private Button buttonnotification;

    public static final int ID_AUTOCANCEL_NOTIFICATION = 1;
    public static final int ID_SWIPE_NOTIFICATION = 2;
    public static final int ID_PERMANENT_NOTIFICATION = 3;
    public static final int ID_BUTTON_NOTIFICATION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initializeViews();

        showNotification();

        autoCancelNotification();

//        updateNotification();


    }

    private void initializeViews() {
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        autocancelnotification = (Button) findViewById(R.id.autocancelnotification);
        swipenotification = (Button) findViewById(R.id.swipenotification);
        permanentnotification = (Button) findViewById(R.id.permanentnotification);
        dismisspermanentnotification = (Button) findViewById(R.id.dismisspermanentnotification);
        buttonnotification = (Button) findViewById(R.id.buttonnotification);

    }

//        autocancelnotification.setOnClickListener(this);
//        swipenotification.setOnClickListener(this);
//        permanentnotification.setOnClickListener(this);
//        dismisspermanentnotification.setOnClickListener(this);
//        buttonnotification.setOnClickListener(this);
//    }
//
//
//
//        View cancelButton = findViewById(R.id.autocancelnotification);
//        cancelButton.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                cancelNotification();
//            }
//        });



    private void showNotification()
    {

    }

    private void updateNotification(String titleText, String contentText)
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(true);
        builder.setContentTitle(titleText);
        builder.setContentText(contentText);
        builder.setSmallIcon(R.drawable.c4qfavicon);
        Notification notification = builder.build();
//        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void autoCancelNotification()
    {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(ID_AUTOCANCEL_NOTIFICATION);
    }




}
