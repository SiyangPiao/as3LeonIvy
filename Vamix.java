import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Vamix extends JFrame implements ActionListener{

	private JPanel panel;
	private JButton download,edit,play;
	
	private Vamix(){
		super("Welcome to VAMIX");
		this.setSize(420, 180);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setSize(this.getSize());
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		download = new JButton("Download");
		drawButton(download); 
		c.gridx=0;
		c.gridy=1;
		c.gridwidth=3;
		c.gridheight=3;
		panel.add(download,c);
		
		edit = new JButton("Edit");
		drawButton(edit); 
		c.gridx=4;
		c.gridy=1;
		c.gridwidth=3;
		c.gridheight=3;
		panel.add(edit,c);
		
		play = new JButton("Play");
		drawButton(play);
		c.gridx=8;
		c.gridy=1;
		c.gridwidth=3;
		c.gridheight=3;
		panel.add(play,c);

		this.add(panel);
	
		download.addActionListener(this);
		edit.addActionListener(this);
		play.addActionListener(this);
		

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == download) {
			new Download();
		} else if (e.getSource() == edit) {
			MediaEditor window = new MediaEditor();
		} else if (e.getSource() == play) {
			new Play();

		}

	}

	
	public void drawButton(JButton button) {
		Dimension size = button.getPreferredSize();
		size.width = size.height =105;
		button.setPreferredSize(size);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Vamix();
			}
		});
	}

}
