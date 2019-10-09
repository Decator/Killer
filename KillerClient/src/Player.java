import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	  
public class Player extends UnicastRemoteObject implements PlayerInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;
	
	public String waiting;
	public boolean game;
	public boolean currentPlayer;

	public Player(String name) throws RemoteException {
		this.name = name;
		this.healthPoints = 30;
		this.waiting = null;
		this.game = false;
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

	@Override
	public void setHealthPoints(int healthPoints) throws RemoteException {
		this.healthPoints = healthPoints;
	}

  @Override
  public void waiting(String msg) throws RemoteException {
	  this.waiting = msg;

  }

	@Override
	public void initialisation(boolean currentPlayer) throws RemoteException {
		this.game = true;
		this.currentPlayer = currentPlayer;
	}
}
