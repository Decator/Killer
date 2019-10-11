import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Frame extends JFrame implements Observer {
	
	private static ServiceClient serviceClient;
	
	private JButton joinGameButton;
	private JTextField nameField;
	
	public Frame(KillerService look_up) {
		super();
		
		serviceClient = new ServiceClient(look_up, this);
		
		setTitle("Killer");
		setSize(800,600); 
		setLocationRelativeTo(null);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(buildContentPane());
	}
	
	public JPanel buildContentPane(){
		JPanel panelStart = new JPanel();
		panelStart.setBackground(Color.RED);
	    this.setContentPane(panelStart); 
		
		nameField = new JTextField();
		nameField.setColumns(10);
		panelStart.add(nameField);
 
		joinGameButton = new JButton("Rejoignez la game");
		joinGameButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e){
				serviceClient.addPlayer(nameField.getText());
			   }
			});
		panelStart.add(joinGameButton);
 
		return panelStart;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(serviceClient.getPlayer().getGame()) {
			JPanel panelGame = new JPanel();
			panelGame.add(new JLabel("Game"));
			panelGame.add(new JLabel(serviceClient.getPlayer().getName() +" - CurrentPlayer: "+ serviceClient.getPlayer().getCurrentPlayer()));
			setContentPane(panelGame);
			validate();
		} else if(serviceClient.getPlayer().getWaiting() != null) {
			JPanel panelWaiting = new JPanel();
			panelWaiting.add(new JLabel(serviceClient.getPlayer().getWaiting()));
			setContentPane(panelWaiting);
			validate();
		}
	}
}
