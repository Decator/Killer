import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayerImpl extends UnicastRemoteObject implements Player {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoint;

	public PlayerImpl(String name)  throws RemoteException {
		super();
		
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
