
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements Observer {

	public static Frame frame;
	
	public ServiceClient serviceClient;
	private CardLayout card;
	private JPanel content;
	private JGame game;
	private JWaiting waiting;
	
	public Frame(KillerInterface look_up) {
		super("Killer");
		
		this.serviceClient = new ServiceClient(look_up);
		frame = this;
		
		setSize(800,600); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.content = new JPanel();
		this.card = new CardLayout();
		this.content.setLayout(this.card);
		
		JMenu menu = new JMenu();
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
	
	public ServiceClient getServiceClient() {
		return serviceClient;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("waiting")) {
			this.waiting = new JWaiting();
			this.content.add(waiting, "Waiting");
			switchPage("Waiting");
		} else if(arg.equals("initialisation")) {
			serviceClient.getPlayers();
		} else if(arg.equals("setPlayers")) {
			this.game = new JGame(serviceClient.getPlayer());
			this.content.add(game, "Game");
			switchPage("Game");
		} else if(arg.equals("rollTheDice")) {
			this.game.dicesPanel();
		} else if(arg.equals("score")) {
			this.game.scoreLabel();
		}
	}
}
