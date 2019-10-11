import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
	  
public class Player extends UnicastRemoteObject implements PlayerInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;
	private ObservablePlayer observablePlayer;
	private String waiting;
	private boolean game;
	private boolean currentPlayer;

	public Player(String name) throws RemoteException {
		this.name = name;
		this.healthPoints = 30;
		this.waiting = null;
		this.game = false;
		this.currentPlayer = false;
		
		this.observablePlayer = new ObservablePlayer();
		this.observablePlayer.notifyFrame();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	@Override
	public int getHealthPoints() throws RemoteException {
		return healthPoints;
	}

	@Override
	public void setHealthPoints(int healthPoints) throws RemoteException {
		this.healthPoints = healthPoints;
		this.observablePlayer.notifyFrame();
	}

	@Override
	public void waiting(String msg) throws RemoteException {
		this.waiting = msg;
		this.observablePlayer.notifyFrame();
	}

	@Override
	public void initialisation(boolean currentPlayer) throws RemoteException {
		this.game = true;
		this.currentPlayer = currentPlayer;
		this.observablePlayer.notifyFrame();
	}
	
	public String getWaiting() {
		return this.waiting;
	}
	
	public boolean getGame() {
		return this.game;
	}
	
	public ObservablePlayer getObservablePlayer() {
		return this.observablePlayer;
	}
}
