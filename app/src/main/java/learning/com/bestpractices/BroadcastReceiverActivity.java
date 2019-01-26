package learning.com.bestpractices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BroadcastReceiverActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_receiver);
    }

    private void registerBroadCastReceiver(){
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
        registerReceiver(broadcastReceiver,new IntentFilter("SmsMessage.intent.Main"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register the broadcastreceiver in OnStart.
        registerBroadCastReceiver();
    }


    @Override
    protected void onStop() {
        super.onStop();
        // Unregister the broadcastreceiver in onStop. so that it does not hold a reference to the activity.
        if(broadcastReceiver !=null){
            unregisterReceiver(broadcastReceiver);
        }
    }
}
