package nyc.c4q;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        final Button buttonnotification = (Button) findViewById(R.id.buttonnotification);

        buttonnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = "subject";
                String body = "body";
                
                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify=new Notification(R.drawable.c4qfavicon,buttonnotification.getText().toString(),System.currentTimeMillis());
                PendingIntent pending= PendingIntent.getActivity(getApplicationContext(), 0, new Intent(), 0);

                notify.setLatestEventInfo(getApplicationContext(),subject,body,pending);
                notif.notify(0, notify);
            }
        });

    }
}
