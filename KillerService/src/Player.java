import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Player implements Remote, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoint;

	public Player(String name) throws RemoteException {		
		this.name = name;
		this.healthPoint = 30;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getHealthPoints() {
		return healthPoint;
	}

	public void setHealthPoints(int healthPoints) {
		this.healthPoint = healthPoint;
	}
}
