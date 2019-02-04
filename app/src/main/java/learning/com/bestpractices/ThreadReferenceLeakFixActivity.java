package learning.com.bestpractices;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ThreadReferenceLeakFixActivity extends AppCompatActivity {

    // The variable must not be static.
    private LeakyThread leakyThread = new LeakyThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_reference_leak_fix);
        createThread();
        redirectToNewScreen();
    }

    private void createThread(){
        leakyThread.start();
    }

    private void redirectToNewScreen(){
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Kill the thread in onDestory
        leakyThread.interrupt();
    }

    public static class LeakyThread extends Thread{
        @Override
        public void run() {
            while(!isInterrupted());
        }
    }
}
