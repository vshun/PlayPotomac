package vadim.potomac;

import vadim.potomac.model.NoaaData;
import vadim.potomac.model.WeatherInfo;

interface FragmentActivityCommunicator {
	  void onForecastLoaded(NoaaData noaaData);
      void onLevelLoaded();
      Playspots getPlayspots ();
      float getCurrentLevel ();
      WeatherInfo getWeatherInfo ();
      NoaaData getNoaaData();
}
