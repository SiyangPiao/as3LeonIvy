import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.sun.jna.platform.unix.X11.Font;

import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class Editor extends JPanel implements ActionListener {
	private JButton extractAudio, replaceAudio, overlayAudio, addText;
	private File videoFile;
	JColorChooser colourChooser;
	File audioFile;

	public Editor() {
		extractAudio = new JButton("Extract Audio");
		extractAudio.addActionListener(this);
		add(extractAudio);

		replaceAudio = new JButton("Replace Audio");
		replaceAudio.addActionListener(this);
		add(replaceAudio);

		overlayAudio = new JButton("Ovelay Audio");
		overlayAudio.addActionListener(this);
		add(overlayAudio);

		addText = new JButton("Add Text");
		addText.addActionListener(this);
		add(addText);

	}

	public void getVideo(File videoFile) {
		this.videoFile = videoFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == extractAudio) {
			if (videoFile == null) {
				JOptionPane.showMessageDialog(this, "Please open a file.",
						"ERROR", JOptionPane.OK_OPTION);
			} else {
				myFileChooser mfc = new myFileChooser();
				String nameOfNewFile = mfc.saveChooser();
				String cmd = "avconv -i " + videoFile.getAbsolutePath() + " "
						+ nameOfNewFile + ".mp3";
				final Task task = new Task(cmd);

				task.execute();
			}

		} else if (e.getSource() == addText) {
			TextEditor textEditor=new TextEditor();
			textEditor.getVideo(videoFile);
		} else if (e.getSource() == replaceAudio) {
			editAudio("Replace Audio");
		} else if (e.getSource() == overlayAudio) {
			editAudio("Overlay Audio");
		}

	}

	private void editAudio(final String option) {
		final JFrame overlay = new JFrame(option);

		overlay.setVisible(true);
		JPanel panel = new JPanel();
		JButton openFile = new JButton("Select");
		JButton save = new JButton("Save");
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		overlay.setSize(380, 270);
		overlay.setLocation(width / 2 - 200, height / 2 - 200);
		overlay.add(panel);
		panel.add(openFile);
		panel.add(save);

		openFile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFileChooser mfc = new myFileChooser();
				audioFile = mfc.openChooser();
			}

		});

		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				myFileChooser mfc = new myFileChooser();
				String nameOfNewFile = mfc.saveChooser();

				if (nameOfNewFile != null && audioFile != null) {
					String cmd = null;
					if (option.equals("Overlay Audio")) {
						cmd = "avconv -i "
								+ videoFile.getAbsolutePath()
								+ " -i "
								+ audioFile.getAbsolutePath()
								+ " -filter_complex amix=inputs=2 -strict experimental "
								+ nameOfNewFile + ".mp4";
					} else {
						cmd = "avconv -i "
								+ audioFile.getAbsolutePath()
								+ " -i "
								+ videoFile.getAbsolutePath()
								+ " -map 0:0 -map 1:0 -acodec copy -vcodec copy -shortest "
								+ nameOfNewFile + ".mp4";
					}
					Task task = new Task(cmd);
					task.execute();
					audioFile = null;
				}

			}

		});
	}

	class Task extends SwingWorker<Void, Integer> {
		private String cmd;
		private int exit = 1;
		Process process;

		Task(String cmd) {
			this.cmd = cmd;
		}

		@Override
		// Call the shell command, to do the extraction
		protected Void doInBackground() throws Exception {
			ProcessBuilder downBuilder = new ProcessBuilder("/bin/bash", "-c",
					cmd);

			process = downBuilder.start();

			exit = process.waitFor();

			return null;

		}

		// When process finished, use the exist value to tell user the message
		@Override
		protected void done() {
			//
			// if(exit==0){
			// JOptionPane.showMessageDialog(Extract.this,"Finsh");
			// try {
			// History.write("Extract");
			// } catch (IOException e) {
			//
			// e.printStackTrace();
			// }
			// }
			//
			// else{
			// JOptionPane.showMessageDialog(Extract.this,"Interrupt");
			//
			//
			// }
			// clear();
			//

		}

	}

}
