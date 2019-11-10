import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Player extends UnicastRemoteObject implements PlayerInterface, Serializable {
	
	private String name;
	private int healthPoints;
	private boolean currentPlayer;
	
	public Player(String name) throws RemoteException {
		this.name = name;
		this.healthPoints = 30;
		this.currentPlayer = false;
	}
	
	@Override
	public String getName() throws RemoteException {
		return this.name;
	}
	
	@Override
	public int getHealthPoints() throws RemoteException {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) throws RemoteException {
		if(healthPoints < 0) {
			this.healthPoints = 0;
		} else {
			this.healthPoints = healthPoints;
		}
	}
	
	@Override
	public boolean getCurrentPlayer() throws RemoteException {
		return this.currentPlayer;
	}

	@Override
	public void setCurrentPlayer(boolean currentPlayer) throws RemoteException {
		this.currentPlayer = currentPlayer;
	}
}
