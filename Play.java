import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class Play extends JFrame {

	private JPanel panel, panel1, panel2, panel3, panel4, panel5, play;
	private JButton openfile, stop, forward, backward, mute, addText;
	private JSlider move, voice;
	private JLabel time;
	private Timer ticker, ticker2;
	private File mp3;
	private EmbeddedMediaPlayer video;
	// private boolean speedup;

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	// public static void main(String[] args) {
	// SwingUtilities.invokeLater(new Runnable() {
	// @Override
	// public void run() {
	// new Play();
	// }
	// });
	// }

	public Play() {
		super("Video Player");

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		// setContentPane(screen);

		setLocation(100, 100);
		setSize(1350, 800);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(mediaPlayerComponent);

		panel1 = new JPanel(new BorderLayout());

		panel4 = new JPanel();
		panel1.add(panel4, BorderLayout.CENTER);
		time = new JLabel("Time: 00:00:00");
		time.setPreferredSize(new Dimension(100, 20));
		panel4.add(time);
		move = new JSlider();
		move.setValue(0);
		move.setPreferredSize(new Dimension(this.getWidth() - 150, 20));
		panel4.add(move);
		// panel1.add(move,BorderLayout.CENTER);

		panel2 = new JPanel(new BorderLayout());
		panel3 = new JPanel();
		openfile = new JButton("Open file");
		panel3.add(openfile);

		backward = new JButton("<|");
		panel3.add(backward);

		stop = new JButton(">");
		panel3.add(stop);

		forward = new JButton("|>");
		panel3.add(forward);
		panel2.add(panel3, BorderLayout.CENTER);

		panel5 = new JPanel();
		mute = new JButton("Mute");
		panel5.add(mute);
		voice = new JSlider();
		voice.setPreferredSize(new Dimension(120, 20));
		panel5.add(voice);
		panel2.add(panel5, BorderLayout.EAST);

		panel1.add(panel2, BorderLayout.SOUTH);
		panel.add(panel1, BorderLayout.SOUTH);

		add(panel);
		setContentPane(panel);
		addFile();
		stop();
		forward();
		backward();

		setVolume();
		setMove();

		ticker = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int t = (int) video.getTime() / 1000;
				int hours = t / 3600;
				int minutes = t / 60 - 60 * hours;
				int seconds = (t % 3600) % 60;
				String m = Integer.toString(minutes), s = Integer
						.toString(seconds);
				;
				if (minutes < 10) {
					m = "0" + Integer.toString(minutes);
				}
				if (seconds < 10) {
					s = "0" + Integer.toString(seconds);
				}
				time.setText("Time: 0" + hours + ":" + m + ":" + s);

				int time = (int) video.getTime();
				move.setMaximum((int) video.getLength());
				move.setValue(time);

			}
		});

//		JButton addText = new JButton("Add Text at the start");
//		addText.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String cmd = "avconv -i input.avi -vf drawtext=fontcolor=white:fontsize=30:fontfile=/usr/share/fonts/truetype/ubuntu-font-family/Ubuntu-L.ttf:text='$date':x=30:y=h-text_h-30 ";
//				cmd = cmd + mp3;
//
//				ProcessBuilder downBuilder = new ProcessBuilder("/bin/bash",
//						"-c", cmd);
//
//				Process process;
//				try {
//					process = downBuilder.start();
//					int exit = process.waitFor();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//			}
//		});
//		panel2.add(addText, BorderLayout.WEST);

	}

	private void setVolume() {
		voice.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (video != null) {
					video.mute(false);
					int volume = ((JSlider) e.getSource()).getValue();
					video.setVolume(volume);
				}
			}
		});
		mute.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (video != null) {
					voice.setValue(0);
					video.mute();
				}
			}
		});

	}

	// private void setMove() {
	// move.addChangeListener(new ChangeListener() {
	//
	// @Override
	// public void stateChanged(ChangeEvent e) {
	// // TODO Auto-generated method stub
	// if(video!=null){
	// video.setTime(move.getValue());
	// }
	// }
	// });
	//
	// }

	private void setMove() {
		move.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (video != null) {
					video.setTime(move.getValue());
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

	}

	private void addFile() {
		openfile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(System
						.getProperty("user.dir"));
				fc.setMultiSelectionEnabled(false);
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileHidingEnabled(true);
				fc.setAcceptAllFileFilterUsed(false);
				// fc.setFileFilter(new myFilter("rmvb"));
				int returnValue = fc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					mp3 = fc.getSelectedFile();
				}

				mediaPlayerComponent.getMediaPlayer().playMedia(
						mp3.getAbsolutePath().toString());

				video = mediaPlayerComponent.getMediaPlayer();
				ticker.start();
				stop.setLabel("||");
				video.mute(false);

			}

		});
	}

	private Speed speedup;
	private Speed speeddown;

	private void stop() {
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (video != null) {
					if (speedup != null) {
						speedup.cancel(true);
						speedup = null;
						video.play();
						stop.setLabel("||");
					} else if (speeddown != null) {
						speeddown.cancel(true);
						speeddown = null;
						video.play();
						stop.setLabel("||");
					} else {
						if (stop.getLabel().equals("||")) {
							video.pause();
							stop.setLabel(">");
						} else {
							video.play();
							stop.setLabel("||");
						}
					}
				}
			}

		});
	}

	private void forward() {
		forward.addActionListener(new ActionListener() {
			//
			@Override
			public void actionPerformed(ActionEvent e) {
				if (video != null) {
					if (video.isPlaying()) {
						if (speeddown != null) {
							speeddown.cancel(true);
							speeddown = null;
						}
						speedup = new Speed(5);
						speedup.execute();
					}
				}

			}

		});
	}

	private void backward() {
		backward.addActionListener(new ActionListener() {
			//
			@Override
			public void actionPerformed(ActionEvent e) {
				if (video != null) {
					if (video.isPlaying()) {
						if (speedup != null) {
							speedup.cancel(true);
							speedup = null;
						}
						speeddown = new Speed(-5);
						speeddown.execute();
					}
				}

			}

		});
	}

	class Speed extends SwingWorker<Void, Void> {

		private int value;
		

		Speed(int value) {
			this.value = value;
		}

		@Override
		protected Void doInBackground() throws Exception {
			// TODO Auto-generated method stub
			while (true) {
				if (isCancelled()) {
					return null;
				}
				video.skip(value);
			}
		}

	}

}