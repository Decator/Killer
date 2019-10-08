public class ServiceClient {
	private KillerService service;
	private PlayerImpl player;
	
	ServiceClient(KillerService service) {
		this.service = service;
	}
	
	public void addPlayer(String name) {
		this.player = (PlayerImpl) this.service.addPlayer(name);
	}
	
	public PlayerImpl getPlayer() {
		return this.player;
	}
}