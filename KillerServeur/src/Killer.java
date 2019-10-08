import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Killer extends UnicastRemoteObject implements KillerService {

  private static final long serialVersionUID = 1L;

  protected Killer() throws RemoteException {
    super();
  }

  @Override
  public Player addPlayer(String name) {
    return new PlayerImpl(name);
  }
}