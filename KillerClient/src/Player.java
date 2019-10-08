import java.rmi.RemoteException;

public class Player implements PlayerInterface {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoint;

	public Player(String name)  throws RemoteException {		
		this.name = name;
		this.healthPoint = 30;
	}
	
	@Override
	public String getName() {
		return this.getName();
	}
	
	@Override
	public int getHealthPoints() {
		return healthPoint;
	}

	@Override
	public void setHealthPoints(int healthPoints) {
		this.healthPoint = healthPoint;
	}
}
