public class PlayerImpl implements Player {
	private String name;
	private int healthPoint;
	
	public PlayerImpl(String name) {
		this.name = name;
		this.healthPoint = 30;
	}
	
	@Override
	public String getName() {
		return this.getName();
	}
	
	@Override
	public int getHealthPoints() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHealthPoints(int healthPoints) {
		this.healthPoint = healthPoint;
	}
}
