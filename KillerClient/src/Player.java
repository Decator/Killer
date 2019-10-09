import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	  
public class Player extends UnicastRemoteObject implements PlayerInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;

	public Player(String name) throws RemoteException {		
		this.name = name;
		this.healthPoints = 30;
	}
	
	public String getName() throws RemoteException {
		return this.name;
	}
	
	public int getHealthPoints() throws RemoteException {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) throws RemoteException {
		this.healthPoints = healthPoints;
	}
}
