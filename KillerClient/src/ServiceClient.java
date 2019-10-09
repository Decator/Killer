import java.rmi.RemoteException;

public class ServiceClient {
	private KillerService service;
	private PlayerInterface player;
	
	ServiceClient(KillerService service) {
		this.service = service;
	}
	
	public void addPlayer(String name) {
		try {
			this.player = this.service.addPlayer(name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public PlayerInterface getPlayer() {
		return this.player;
	}
}