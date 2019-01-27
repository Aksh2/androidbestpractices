package learning.com.bestpractices;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class AsyncTaskReferenceLeakFixActivity extends AppCompatActivity {

    private TextView textView;
    private BackgroundTask backgroundTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_reference_leak_fix);
        textView=findViewById(R.id.textViewSample);
        backgroundTask = new BackgroundTask(textView);
        backgroundTask.execute();
    }


    private static class BackgroundTask extends AsyncTask<Void,Void,String>{

        private final WeakReference<TextView> messageViewReference;
        private BackgroundTask(TextView textView){
            this.messageViewReference=new WeakReference<>(textView);
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "The task is completed !";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            TextView textView = messageViewReference.get();
            if(textView!=null){
                textView.setText(s);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(backgroundTask!=null){
            backgroundTask.cancel(true);
        }
    }
}
