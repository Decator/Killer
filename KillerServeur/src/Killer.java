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
  public void addPlayer(PlayerInterface player) throws RemoteException {
    this.players.add(player);
    System.out.println("Nouveau joueur : "+ player.getName());
  }
  
  public ArrayList<PlayerInterface> getPlayers() {
    return this.players;
  }

}