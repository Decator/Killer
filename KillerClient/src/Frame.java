
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements Observer {
	
	private ServiceClient serviceClient;
	
	// Panels variables
	private Menu menu;
	private Waiting waiting;
	
	private CardLayout card;
	private JPanel content;
	
	public Frame(KillerService look_up) {
		super("Killer");
		
		serviceClient = new ServiceClient(look_up, this);
		
		setSize(800,600); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.content = new JPanel();
		this.card = new CardLayout();
		this.content.setLayout(this.card);
		
		this.menu = new Menu(this);
		this.content.add(this.menu, "Menu");
		this.getContentPane().add(this.content);

		this.setVisible(true);
		this.setResizable(false);
	}
	
	/**
	* This method allows to switch page
	* @param page It's the new page(panel)
	*/
	public void switchPage(String page) {
		this.card.show(this.content, page);
	}
	
	public ServiceClient getServiceClient() {
		return this.serviceClient;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(serviceClient.getPlayer().getGame()) {
			/*JPanel panelGame = new JPanel();
			panelGame.add(new JLabel("<h1>Game</h1>"));
			panelGame.add(new JLabel(serviceClient.getPlayer().getName() +" - CurrentPlayer: "+ serviceClient.getPlayer().getCurrentPlayer()));
			setContentPane(panelGame);
			validate();*/
		} else if(serviceClient.getPlayer().getWaiting() != null) {
			this.waiting = new Waiting(this);
			this.content.add(this.waiting, "Waiting");
			switchPage("Waiting");
		}		
	}
}
