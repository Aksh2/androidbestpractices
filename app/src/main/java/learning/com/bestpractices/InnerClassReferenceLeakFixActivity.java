package learning.com.bestpractices;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;

public class InnerClassReferenceLeakFixActivity extends AppCompatActivity {

    private LeakyClass leakyClass;

   /*
    *    Never create a static variable of an inner class.
    */

    //private static LeakyClass leakyClass

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner_class_reference_leak_fix);

        /*
         * Inner class is defined here
         */

        leakyClass = new LeakyClass(this);
        leakyClass.redirectToSecondScreen();

    }

    /*
     *  Option 1: The class should be set to static because instances of anonymous do not hold a implicit reference
     *  to the outer class.
     *
     *  Option 2: Use a weakreference of the textview or any view/activity as garbage collector can collect an object if
     *  only weak references are pointing towards it.
     */

    private static class LeakyClass {
        private final WeakReference<Activity> messageViewReference;
        public LeakyClass(Activity activity){
            this.messageViewReference = new WeakReference<>(activity);
        }

        public void redirectToSecondScreen(){
            Activity activity = messageViewReference.get();
            if(activity!=null){
                activity.startActivity(new Intent(activity,MainActivity.class));
            }





        }
    }
}
