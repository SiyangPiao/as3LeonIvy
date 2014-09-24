import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;


public class TextEditor {
	String cmd;
	File videoFile=null;
	JColorChooser colourChooser;
	public TextEditor(){
		JFrame editText = new JFrame("Edit Text");
		JPanel panel=new JPanel(new GridLayout(2,1));
		TextPanel startPanel=new TextPanel("title page");
//		TextPanel endPanel=new TextPanel("credit page");
		
		panel.add(startPanel);
//		panel.add(endPanel);
		editText.add(panel);

		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		editText.setSize(550, 760);
		editText.setLocation(width / 2-200, height / 2 -350);
		editText.setVisible(true);
		

	}
	
	public void getVideo(File videoFile) {
		this.videoFile = videoFile;
	}
	
	class TextPanel extends JPanel implements ActionListener{
		
		final JTextField textField;
		JTextField x;
		JTextField y;
		JTextField time;
		JComboBox fontList;
		JComboBox fontSizeList;
		JButton save;
		JButton previewButton;
		
		
		
		
		private TextPanel(String str){
			super(new GridLayout(7, 1));
			JPanel textPanel= new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel fontPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			final JPanel colourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel locationPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel timePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
//			JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel previewPanel = new JPanel(new GridLayout(1, 2));
			JPanel preview = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			JLabel textLabel;
			JLabel fontLabel = new JLabel("Select a font:           ");
			JLabel sizeLabel = new JLabel("Select a text size:   ");
			JLabel colourLabel = new JLabel("Select a colour:       ");
			JLabel locationLabel = new JLabel("Select a location location: ");
			JLabel timeLabel=new JLabel("Enter the length of time: ");
			JLabel topLabel = new JLabel("Top: ");
			JLabel middleLabel = new JLabel("Middle: ");
			JLabel bottomLabel = new JLabel("Bottom: ");
//			JLabel optionLabel;
			JButton colourButton = new JButton("Choose Colour");
			save = new JButton("Save");
			save.addActionListener(this);
			
			
				

				
				
				
		
			
			
			
			
			

//			JRadioButton optionButton=new JRadioButton();
			JRadioButton topButton=new JRadioButton();
			JRadioButton middleButton=new JRadioButton();
			JRadioButton bottomButton=new JRadioButton();
		
			textField = new JTextField(33);
			x = new JTextField(5);
			y = new JTextField(5);
			time = new JTextField(10);
		
			colourChooser = new JColorChooser();
			previewButton=new JButton("Preview of start");
			if(str.equals("title page")){
				
				textLabel = new JLabel("Text of "+str+"  ");
//				optionLabel = new JLabel("Start: ");
//				save.setVisible(false);
			}else{
				
//				optionLabel = new JLabel("End: ");
				textLabel = new JLabel("Text of "+str+" ");
			}

			textPanel.add(textLabel);
		    textPanel.add(textField);
			add(textPanel);
			textField.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					if (textField.getText().length() >= 30) {
						e.consume();
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub

				}

			});

			GraphicsEnvironment ge = GraphicsEnvironment
					.getLocalGraphicsEnvironment();
			String[] fontNames = ge.getAvailableFontFamilyNames();
			java.awt.Font[] fonts = ge.getAllFonts();
			System.out.println(fonts[2]);
			fontList = new JComboBox(fontNames);
			fontPanel.add(fontLabel);
			fontPanel.add(fontList);

			String[] fontSizes = new String[91];
			int s = 6;
			for (int i = 0; i < 91; i++) {
				fontSizes[i] = String.valueOf(s);
				s = s + 1;
			}
			fontSizeList = new JComboBox(fontSizes);
			sizePanel.add(sizeLabel);
			sizePanel.add(fontSizeList);

			colourPanel.add(colourLabel);
			colourPanel.add(colourButton);
			colourButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Color c = colourChooser.showDialog(colourPanel,
							"Please select", Color.black);
				}

			});
			
			locationPanel.add(locationLabel);
			locationPanel.add(topLabel);
			locationPanel.add(topButton);
			locationPanel.add(middleLabel);
			locationPanel.add(middleButton);
			locationPanel.add(bottomLabel);
			locationPanel.add(bottomButton);
			
			timePanel.add(timeLabel);
			timePanel.add(time);
//			optionPanel.add(optionLabel);
//			optionPanel.add(optionButton);

			preview.add(previewButton);
			
			//33333333333333333333333333333333333333333
			previewButton.addActionListener(this);

			
			
			
			previewPanel.add(preview);
	        savePanel.add(save);
			previewPanel.add(savePanel);

			add(fontPanel);
			add(sizePanel);
			add(colourPanel);
			add(locationPanel);
			add(timePanel);
//			add(optionPanel);
			add(previewPanel);
//			setVisible(true);
//			System.out.println("aaa");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
		
                if(e.getSource()==save){
                	
				myFileChooser mfc = new myFileChooser();
				String nameOfNewFile = mfc.saveChooser();

				cmd = "avconv -i " + videoFile.getAbsolutePath()
						+ " -vf \"drawtext=fontfile='" + "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf"
						+ "': text='" + textField.getText()+ "':x=" + "(main_w-text_w)/2" + ":y=" + "(main_h-text_h)/2"
						+ ":fontsize=" + fontSizeList.getSelectedItem() + ":fontcolor=" + "black"
						+ ":draw='lt(t," + time.getText() + "):\" -c:a copy " + nameOfNewFile + ".mp4";
				
				Task task = new Task(cmd);
				task.execute();
			   
				
		
	
			}
              
                
                else if(e.getSource()==previewButton){
                	
                	
    				cmd = "avplay -i " + videoFile.getAbsolutePath()
    						+ " -vf \"drawtext=fontfile='" + "/usr/share/fonts/truetype/ttf-dejavu/DejaVuSans.ttf"
    						+ "': text='" + textField.getText()+ "':x=" + "(main_w-text_w)/2" + ":y=" + "(main_h-text_h)/2"
    						+ ":fontsize=" + fontSizeList.getSelectedItem() + ":fontcolor=" + "black"
    						+ ":draw='lt(t," + time.getText() + "):\"";
    				System.out.println(cmd);
    				ProcessBuilder downBuilder = new ProcessBuilder("/bin/bash", "-c",
    						cmd);
    				Process process;
    				try {
						process = downBuilder.start();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
    	
		}
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
	
	
	private void logHistory(){
		//Get the log file
		File log = new File(System.getProperty("user.home")+"/.history/log.txt");
		File folder=new File(System.getProperty("user.home")+"/.history");
		String next=System.getProperty("line.separator");
		
		//If the show button been pressed


		try {
			//If the log file not exist, create one
		    if(!folder.exists()){
		    	folder.mkdirs();
		    	
		    }
			
		if (!log.exists()) {

				log.createNewFile();
       } else {
			

				BufferedReader br = new BufferedReader(new FileReader(log));
				String str = null;
//				history.setText("");
				while ((str = br.readLine()) != null) {
					str.trim();
					String[] strList=str.split(" ");
					if (strList[0].equals(videoFile.getAbsolutePath())){
						if(strList[1].equals("start")){
							// set up the start
							
							
							
						}
						else{
							//set up the end
							
							
							
						}
						
					}
				}
			
		}
		}
		catch (IOException e1) {

			e1.printStackTrace();
		}

	
	}
	
	private void write(String function) throws IOException {
		//Get the log file
		File logFile = new File(System.getProperty("user.home")+"/.history/log.txt");
		


		//Write the action to the file
		FileWriter fw = new FileWriter(logFile, true);
	
		fw.write("\t" + function + "\t" +  "\n");
		fw.close();
	}
}
