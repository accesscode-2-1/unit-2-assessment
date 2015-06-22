package nyc.c4q;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Unit2AssessmentActivity extends Activity {

    public static final String TAG = "Unit2Assessment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit2assessment);

        Button listViewActivityButton = (Button) findViewById(R.id.listViewActivity);
        Button networkActivityButton = (Button) findViewById(R.id.networkActivity);

        listViewActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Unit2AssessmentActivity.this, ListViewActivity.class);
                Unit2AssessmentActivity.this.startActivity(intent);
            }
        });

        networkActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Unit2AssessmentActivity.this, NetworkActivity.class);
                Unit2AssessmentActivity.this.startActivity(intent);
            }
        });
    }
}