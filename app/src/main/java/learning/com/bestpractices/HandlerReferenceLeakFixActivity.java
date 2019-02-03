package learning.com.bestpractices;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class HandlerReferenceLeakFixActivity extends AppCompatActivity {

    private TextView textView;
    private final LeakyHandler leakyHandler = new LeakyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_reference_leak_fix);
        textView=findViewById(R.id.handlerTv);
        leakyHandler.postDelayed(leakyRunnable,5000);
    }

    private static class LeakyHandler extends Handler{

        private WeakReference<HandlerReferenceLeakFixActivity> weakReference;

        public LeakyHandler(HandlerReferenceLeakFixActivity activity){
            weakReference=new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HandlerReferenceLeakFixActivity activity = weakReference.get();
            if(activity!=null){
                activity.textView.setText("Sample String");
            }
        }
    }

    private static final Runnable leakyRunnable = new Runnable() {
        @Override
        public void run() {
            /* ... */
        }
    };
}
