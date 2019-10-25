
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
	private JAttack attack;
	private JWaiting waiting;
	
	public Frame(ServerInterface look_up) {
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
		
		this.content.add(new JMenu(), "Menu");
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
	
	public JAttack getAttack() {
		return this.attack;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("waiting")) {
			this.waiting = new JWaiting();
			this.content.add(this.waiting, "Waiting");
			switchPage("Waiting");
		} else if(arg.equals("initialisation")) {
			this.serviceClient.getPlayers();
		} else if(arg.equals("setPlayers")) {
			if(this.game != null) {
				this.content.remove(this.game);
			}
			this.game = new JGame(this.serviceClient.getClient());
			this.content.add(this.game, "Game");
			switchPage("Game");
		} else if(arg.equals("rollTheDice")) {
			this.game.dicesPanel();
		} else if(arg.equals("score")) {
			this.game.scoreLabel();
		} else if(arg.equals("attack")) {
			if(this.attack != null) {
				this.content.remove(this.attack);
			}
			System.out.println("frame attack");
			this.attack = new JAttack(this.serviceClient.getClient());
			this.content.add(this.attack, "Attack");
			switchPage("Attack");
		}
	}
}
