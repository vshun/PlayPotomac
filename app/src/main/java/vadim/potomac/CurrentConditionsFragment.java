package vadim.potomac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import vadim.potomac.model.TideInfo;
import vadim.potomac.model.WeatherInfo;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
	
public class CurrentConditionsFragment extends Fragment {
	private TideInfo mTideInfo = null;
	private Playspots mPlayspots = null;
	private WeatherInfo mWeatherInfo = null;
	private float mCurrentLevel;
	private WeakReference<DownloadWeatherData> weatherTaskWeakReference;
	private WeakReference<USGSTask> usgsTaskWeakReference;
	private static final String TAG = "Potomac.Current";
	
    
    @Override
    public void onCreate (Bundle savedState) {
    	super.onCreate(savedState);
    	setRetainInstance(true);
		try {
			// playspots are needed prior to task execution
			mPlayspots = new Playspots (getResources().getXml(R.xml.playspots));
						
			startNewWeatherAsyncTask();
			startNewUSGSAsyncTask(mPlayspots);
			
			// parse tide - it can be loaded last
			InputStream is = getResources().openRawResource(R.raw.annualtidetable);
 	   	   	mTideInfo = parseTideInfo(is);
 	   	   	is.close();
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}		  	
    }
    
    public void refresh () {
       	startNewWeatherAsyncTask();
    	startNewUSGSAsyncTask(mPlayspots);  	
    }

    private void startNewWeatherAsyncTask() {
    	DownloadWeatherData asyncTask = new DownloadWeatherData(this);
        this.weatherTaskWeakReference = new WeakReference<DownloadWeatherData>(asyncTask );
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getString (R.string.weatherForecastUrl));
    }
    
    private boolean isWeatherAsyncTaskPendingOrRunning() {
        return this.weatherTaskWeakReference != null &&
              this.weatherTaskWeakReference.get() != null && 
              !this.weatherTaskWeakReference.get().getStatus().equals(Status.FINISHED);
    }

    private void startNewUSGSAsyncTask(Playspots playspots) {
    	USGSTask asyncTask = new USGSTask(this, playspots);
        this.usgsTaskWeakReference = new WeakReference<USGSTask>(asyncTask );
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getString (R.string.usgsUrl));
    }
    
    private boolean isUSGSAsyncTaskPendingOrRunning() {
        return this.usgsTaskWeakReference != null &&
              this.usgsTaskWeakReference.get() != null && 
              !this.usgsTaskWeakReference.get().getStatus().equals(Status.FINISHED);
    }
    
    @Override
    public void onResume() {
        super.onResume();
        if (isWeatherAsyncTaskPendingOrRunning()||
        		isUSGSAsyncTaskPendingOrRunning()) {
            LinearLayout nowProgress = (LinearLayout) getView().findViewById(R.id.nowProgress);
            if (nowProgress != null)
            	nowProgress.setVisibility(View.VISIBLE);       }	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.now, container, false);
 
        // most of the info will be populated from AsyncTasks
        // tide info was populated prior so could be loaded now
        if (mTideInfo != null) { // load tide information
			TextView lowTime = (TextView)rootView.findViewById(R.id.lowTide);
			lowTime.setText(mTideInfo.getAmTideTimeLow()+"am and "+ mTideInfo.getPmTideTimeLow()+"pm");

			TextView highTime = (TextView)rootView.findViewById(R.id.highTide);
			highTime.setText(mTideInfo.getAmTideTimeHigh()+"am and "+ mTideInfo.getPmTideTimeHigh()+"pm");
        }
        return rootView;
    }

    public Playspots getPlayspots () {
    	return mPlayspots;
    }
    
    // tide processing functions
    private TideInfo parseTideInfo (InputStream is) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(is));
    	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    	String todayDate = df.format(new Date ());
    	TideInfo ti = new TideInfo ();
    	boolean processingToday = false;
	    String line;
	    while ((line = br.readLine()) != null) {
	    	if (line.startsWith(todayDate)) {
	    		processTideInfo (ti, line);
	    		processingToday = true;
	    	}	else if (processingToday) break; // reaching past today date	
	    }
	    br.close();
	    return ti;
    }	    

    // parses line into POJO
    private void processTideInfo (TideInfo ti, String line) {

		String[] all = line.split("\\s+");
		ti.setDayOfWeek(all[1]);		
		String time = all[2];
		String ampm = all[3];
		String level = all[4];
		String HiLo = all[6];
		if (ampm.equals("AM")) {
			if (HiLo.equals("L")) {
				ti.setAmTideTimeLow(time);
			} else {
				ti.setAmTideTimeHigh(time);
			}
		} else {
			if (HiLo.equals("L")) {
				ti.setPmTideTimeLow(time);
			} else {
				ti.setPmTideTimeHigh(time);
			}
		}
    }

	public float getCurrentLevel() {
		return mCurrentLevel;
	}

	public void setCurrentLevel(float currentLevel) {
		this.mCurrentLevel = currentLevel;
	}

	public WeatherInfo getWeatherInfo() {
		return mWeatherInfo;
	}

	public void setWeatherInfo(WeatherInfo weatherInfo) {
		mWeatherInfo = weatherInfo;
	}
}
