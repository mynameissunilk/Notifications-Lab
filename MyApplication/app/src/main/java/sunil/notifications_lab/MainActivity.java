package sunil.notifications_lab;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Main Activity handles the Notification Objects...
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        PendingIntent pending = PendingIntent.getActivity(this,123456,intent,0);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Connection Status")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setPriority(Notification.PRIORITY_MAX)
                .setContentIntent(pending); // button

        ConnectivityManager cMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cMgr.getActiveNetworkInfo();

        if(nInfo !=null && nInfo.isConnected())// if we have a network connection
        {
            // set the notification to have_network
            // i'll make the bitmap smaller later. plenty to catch up on 
            builder.addAction(android.R.drawable.sym_def_app_icon,"CONNECTED",pending);
            NotificationManagerCompat.from(MainActivity.this).notify(7890,builder.build());
        }
        else{
            // set the notification to no_network
            builder.addAction(android.R.drawable.alert_light_frame,"NO CONNECTION",pending);
            builder.setAutoCancel(true);
            NotificationManagerCompat.from(MainActivity.this).notify(7890,builder.build());
        }

    }
}
