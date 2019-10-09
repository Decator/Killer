import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Killer extends UnicastRemoteObject implements KillerService {

  private static final long serialVersionUID = 1L;
  
  private ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();

  protected Killer() throws RemoteException {
    super();
  }

  @Override
  public PlayerInterface addPlayer(String name) throws RemoteException {
	  PlayerInterface player = new Player(name);
    this.players.add(player);
    System.out.println("Nouveau joueur : "+ name);
    return player;
  }
  
  public ArrayList<PlayerInterface> getPlayers() {
    return this.players;
  }
}