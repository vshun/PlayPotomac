package vadim.potomac.model;

public class CurrentWeather {
	private String temp;
	private String windchill;
	private String humidity;
	private String wind;
	private String condition;
	private String sunrise;
	private String sunset;

	public String getTemp() {
		return temp;
	}
	
	public void setTemp (String temp) {
		this.temp = temp;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getWind() {
		return wind;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getCondition() {
		return condition;
	}

	public String getWindchill() {
		return windchill;
	}

	public void setWindchill(String windchill) {
		this.windchill = windchill;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}
	
}
