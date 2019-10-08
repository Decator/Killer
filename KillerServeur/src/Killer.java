import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Killer extends UnicastRemoteObject implements KillerService {

  private static final long serialVersionUID = 1L;
  
  private ArrayList<Player> players = new ArrayList<Player>();

  protected Killer() throws RemoteException {
    super();
  }

  @Override
  public Player addPlayer(String name) throws RemoteException {
	  Player player = new Player(name);
    this.players.add(player);
    return player;
  }
}