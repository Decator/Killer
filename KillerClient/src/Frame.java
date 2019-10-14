
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements Observer {
	
	private ServiceClient serviceClient;
	
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
		
		Menu menu = new Menu(this);
		this.content.add(menu, "Menu");
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

	@Override
	public void update(Observable o, Object arg) {
		if(serviceClient.getPlayer().getGame()) {
			Game game = new Game(this);
			this.content.add(game, "Game");
			switchPage("Game");
		} else if(serviceClient.getPlayer().getWaiting() != null) {
			Waiting waiting = new Waiting(this);
			this.content.add(waiting, "Waiting");
			switchPage("Waiting");
		}		
	}

	public ServiceClient getServiceClient() {
		return this.serviceClient;
	}
}
