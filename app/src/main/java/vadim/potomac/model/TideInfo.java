package vadim.potomac.model;

// the info is populated in tab delimited file from noaa tidesandcurrents web site published yearly
public class TideInfo {
	private String dayOfWeek;
	private String amTideTimeLow;
	private String amTideTimeHigh;
	private String pmTideTimeLow;
	private String pmTideTimeHigh;

	public TideInfo() {
		dayOfWeek = amTideTimeLow = amTideTimeHigh = pmTideTimeLow = pmTideTimeHigh = "NA";
	}
	
	public void setDayOfWeek (String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public void setAmTideTimeLow(String amTideTimeLow) {
		this.amTideTimeLow = amTideTimeLow;
	}
	public String getAmTideTimeLow() {
		return amTideTimeLow;
	}

	public void setAmTideTimeHigh(String amTideTimeHigh) {
		this.amTideTimeHigh = amTideTimeHigh;
	}
	public String getAmTideTimeHigh() {
		return amTideTimeHigh;
	}

	public void setPmTideTimeLow(String pmTideTimeLow) {
		this.pmTideTimeLow = pmTideTimeLow;
	}
	public String getPmTideTimeLow() {
		return pmTideTimeLow;
	}

	public void setPmTideTimeHigh(String pmTideTimeHigh) {
		this.pmTideTimeHigh = pmTideTimeHigh;
	}
	public String getPmTideTimeHigh() {
		return pmTideTimeHigh;
	}
}
