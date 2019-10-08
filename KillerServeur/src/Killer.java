import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Killer extends UnicastRemoteObject implements KillerService {

  private static final long serialVersionUID = 1L;

  protected Killer() throws RemoteException {
    super();
  }

  @Override
  public String getInformation(String txt) throws RemoteException {
    return txt + " est un bg.";
  }

  @Override
  public Player addPlayer(String name) throws RemoteException {
    PlayerImpl newPlayer = new PlayerImpl(name);
    return newPlayer;
  }
}