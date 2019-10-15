
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
	  
public class Killer extends UnicastRemoteObject implements PlayerInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;
	private ObservablePlayer observablePlayer;
	private String waiting;
	private boolean game;
	private boolean currentPlayer;
	private ArrayList<PlayerInterface> players;
	private int[] dices;
	private int score;

	public Killer(String name) throws RemoteException {
		this.name = name;
		this.healthPoints = 30;
		this.waiting = "";
		this.game = false;
		this.currentPlayer = false;
		this.players = new ArrayList<PlayerInterface>();
		this.dices = new int[6];
		this.score = 0;
		
		this.observablePlayer = new ObservablePlayer();
		this.observablePlayer.notifyFrame("init");
	}

	public ObservablePlayer getObservablePlayer() {
		return this.observablePlayer;
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

	public void setHealthPoints(int healthPoints) throws RemoteException {
		this.healthPoints = healthPoints;
		this.observablePlayer.notifyFrame("healthPoints");
	}

	@Override
	public void waiting(String msg) throws RemoteException {
		this.waiting = msg;
		this.observablePlayer.notifyFrame("waiting");
	}

	@Override
	public void initialisation(boolean currentPlayer) throws RemoteException {
		this.game = true;
		this.currentPlayer = currentPlayer;
		this.observablePlayer.notifyFrame("initialisation");
	}
	
	public String getWaiting() {
		return this.waiting;
	}
	
	public boolean getGame() {
		return this.game;
	}
	
	@Override
	public void setPlayers(ArrayList<PlayerInterface> players) {
		this.players = players;
		this.observablePlayer.notifyFrame("setPlayers");
	}
	
	public ArrayList<PlayerInterface> getPlayers() {
		return this.players;
	}
	
	@Override
	public void setDices(int[] rollTheDice) {
		this.dices = rollTheDice;
		this.observablePlayer.notifyFrame("rollTheDice");
	}
	
	public int[] getDices() {
		return this.dices;
	}
	
	@Override
	public void setScore(int score) {
		this.score = score;
		this.observablePlayer.notifyFrame("score");
	}
	
	public int getScore() {
		return this.score;
	}
}
