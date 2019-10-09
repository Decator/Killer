import java.io.Serializable;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;

	public Player(String name) {		
		this.name = name;
		this.healthPoints = 30;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHealthPoints() {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
	}
}
