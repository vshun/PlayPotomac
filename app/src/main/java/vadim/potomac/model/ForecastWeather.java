package vadim.potomac.model;

public class ForecastWeather {
	private String low;	
	private String high;
	private String dayOfWeek;

	String getDayOfWeek() {
		return dayOfWeek;
	}

	
	public void setDayOfWeek (String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	public void setLow(String low) {
		this.low = low;
	}


	public String getLow() {
		return low;
	}


	public void setHigh(String high) {
		this.high = high;
	}


	public String getHigh() {
		return high;
	}

}
