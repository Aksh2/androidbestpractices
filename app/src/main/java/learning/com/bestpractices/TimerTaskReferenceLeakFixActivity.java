package learning.com.bestpractices;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TimerTaskReferenceLeakFixActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_task_reference_leak_fix);
        startTimer();
    }

    public void cancelTimer(){
        if(countDownTimer!=null) countDownTimer.cancel();
    }

    private void startTimer(){
        countDownTimer = new CountDownTimer(1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final int secondsRemaining = (int) (millisUntilFinished/1000);
                //update the UI here.
            }

            @Override
            public void onFinish() {
                //handle onFinish
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onDestroy() {
        // Cancel the timer when the activity is destroyed.
        super.onDestroy();
        cancelTimer();
    }
}
