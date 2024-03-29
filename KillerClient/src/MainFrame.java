
import java.awt.CardLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame implements Observer {

	public static MainFrame frame;
	
	public ServiceClient serviceClient;
	private CardLayout card;
	private JPanel content;
	private JGame game;
	private JAttack attack;
	
	public MainFrame(ServerInterface look_up) {
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
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("message")) {
			JMessage message = new JMessage();
			this.content.add(message, "Message");
			switchPage("Message");
		} else if(arg.equals("initialisation")) {
			this.serviceClient.getClients();
		} else if(arg.equals("setClients")) {
			if(this.attack != null) {
				this.attack = null;
			}
			this.game = new JGame(this.serviceClient.getClient());
			this.content.add(this.game, "Game");
			switchPage("Game");
		} else if(arg.equals("rollTheDice")) {
			this.game.dicesPanel();
			if(this.attack != null) {
				this.attack.dicesPanel();
			}
		} else if(arg.equals("score")) {
			this.game.scoreLabel();
		} else if(arg.equals("attack")) {
			this.attack = new JAttack(this.serviceClient.getClient());
			this.content.add(this.attack, "Attack");
			switchPage("Attack");
		} else if(arg.equals("endGame")) {
			JWinner winner = new JWinner();
			this.content.add(winner, "Winner");
			switchPage("Winner");
		} else if(arg.equals("replay")) {
			JMenu menu = new JMenu();
			this.content.add(menu, "Replay");
			switchPage("Replay");
		}
	}
}
