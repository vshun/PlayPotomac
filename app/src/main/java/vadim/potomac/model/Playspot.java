package vadim.potomac.model;

public class Playspot {
	
	private final String name;
	private final float min;
	private final float max;
	private final float best;
	private final int classification;
	private final PlayspotType type;
	
	public Playspot(String name, float min, float max, float best, int classification, String type) {
		super();
		this.name = name;
		this.min = min;
		this.max = max;
		this.best = best;
		this.classification = classification;
		
		this.type = PlayspotType.get(type);
	}

	public boolean isPlayable (float current) { return current >= min && current <= max; }
	
	public String getName() { return name; }
	
	public float getMin() { return min; }
	
	public float getMax() { return max;}
	
	public float getBest() { return best;}
	
	public int getClassification() { return classification;}

	public boolean typeAcceptable (PlayspotType typeRequested) {
		return type == PlayspotType.All || typeRequested == PlayspotType.All || typeRequested == type;
	}
	
	public String toString () {
		return name + " From " + min + " To " + max + " best at " + best + " class " + classification + " boat " + type.toString();
	}		
}