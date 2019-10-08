import java.rmi.RemoteException;

public class ServiceClient {
	private KillerService service;
	private PlayerImpl player;
	
	ServiceClient(KillerService service) {
		this.service = service;
	}
	
	public void addPlayer(String name) {
		try {
			this.player = (PlayerImpl) this.service.addPlayer(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public PlayerImpl getPlayer() {
		return this.player;
	}
}