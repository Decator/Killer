import java.rmi.RemoteException;

public class ServiceClient {
	private KillerService service;
	private Frame frame;
	private Player player;
	
	ServiceClient(KillerService service, Frame frame) {
		this.service = service;
		this.frame = frame;
	}
	
	public void addPlayer(String name) {
		try {
		  this.player = new Player(name);
			getPlayer().getObservablePlayer().addObserver(this.frame);
			this.service.addPlayer(player);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
}