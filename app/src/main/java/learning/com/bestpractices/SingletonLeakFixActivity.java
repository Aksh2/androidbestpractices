package learning.com.bestpractices;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import learning.com.sampleclasses.SingletonSampleClass;

public class SingletonLeakFixActivity extends AppCompatActivity {

    private SingletonSampleClass singletonSampleClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleton_leak_fix);
        /*
            Option 1: Do not pass activity context to the Singleton class.Instead pass application context.
         */
        singletonSampleClass = SingletonSampleClass.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*
            Option 2: Unregister the singleton class here. i.e. if you pass activity context to the Singleton class,
            then ensure that when the activity is destroyed, the context in the singleton class is set to null.
         */

        singletonSampleClass.onDestroy();


    }
}
