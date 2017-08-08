package vadim.potomac.model;

public	class RiverData {
	private final String observedAt;
	private final String level;
	private final String temperature;
	
	public RiverData (String observedAt, String level, String temperature) {
		this.observedAt = observedAt;
		this.level = level;
		this.temperature = temperature;
	}
	
	public String getLevel () { return level; } 
	
	public String getWaterTemp () { return temperature; } 
	
	public String getObservedAt () { return observedAt; }
	
	public String toString () {
		return "Observation time -" + observedAt + "\nRiver Level - " + level + "\nWater Temperature - " + temperature;
	}
}