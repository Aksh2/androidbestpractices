package learning.com.bestpractices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class RunnableLeakFixActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runnable_leak_fix);

        textView = findViewById(R.id.sampleTextView);
        new Thread(new LeakyRunnable(textView)).start();


    }

    private static class LeakyRunnable implements Runnable{

        private final WeakReference<TextView> messageViewReference;
        private LeakyRunnable(TextView textView){
            this.messageViewReference= new WeakReference<>(textView);
        }

        @Override
        public void run() {
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            TextView textView = messageViewReference.get();
            if(textView!=null){
                textView.setText("Runnable class has completed its work");
            }
        }
    }
}
