import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class Download extends JFrame implements ActionListener
		{
	private JTextField txt = new JTextField(20);
	private JButton download = new JButton("Download");
	private JButton cancel = new JButton("Cancel");
	private JProgressBar progressBar = new JProgressBar();

	public Download() {
		super("Download");
		setSize(400, 200);
		this.setLocation(700, 400);
		setLayout(new FlowLayout());

		add(txt);
		download.addActionListener(this);
		add(download);
		progressBar.setPreferredSize(new Dimension(350,50));
		progressBar.setStringPainted(true);
		add(progressBar);
		cancel.setVisible(false);
		add(cancel);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Get the URL from user's input
		String input = txt.getText();
		//If the input is empty, tell the user
		if(input.isEmpty()){
			
			JOptionPane.showMessageDialog(Download.this,"Empty input");
			return;
			
		}
		
		//Test whether the file is exist.
		final String URL = input.trim();

		String[] array = URL.split("/");
		String name = array[array.length - 1];
		String myPath = System.getProperty("user.dir");

		String filePath = myPath + "/" + name;
        
	
		
		File file = new File(filePath);
		int has=JOptionPane.YES_OPTION;
        if (file.exists()){
        	//If the file exist, ask user whether to override it or not
        has = JOptionPane.showConfirmDialog(this,"File already exsit, override it?",
        			"Opps",JOptionPane.YES_NO_CANCEL_OPTION);
        	
        }
        
		if (has==JOptionPane.YES_OPTION||has==JOptionPane.NO_OPTION) {
			//If user want to override that file, delete the current file
			if(has==JOptionPane.YES_OPTION){
				file.delete();
				
				}
			
			
			//Ask if the file is open source
			int ifadd = JOptionPane.showConfirmDialog(this,
					"Is it open source?", "yes", JOptionPane.YES_NO_OPTION);
			if (ifadd == JOptionPane.YES_NO_OPTION) {
            //Use swing worker to do the downloading command in background
				class Task extends SwingWorker<Void, Integer> {
                    private int exit=1;
                    Process process;
					@Override
					//Do the "wget" at the background thread
					protected Void doInBackground() throws Exception {
                        //Create a process builder to execute "wget"
						String cmd = "wget -c ";
						cmd = cmd + URL;
						ProcessBuilder downBuilder = new ProcessBuilder(
								"/bin/bash", "-c", cmd);

						downBuilder.redirectErrorStream(true);
						process = downBuilder.start();
                        //Get the standard output and error
						BufferedReader stdout = new BufferedReader(
								new InputStreamReader(process.getInputStream()));

						StringBuffer sb = new StringBuffer();

						String line;
                        //Use the information in standard output to get the progress of this process
						int progress = 0;
						setProgress(progress);
						while (progress < 100) {
							Thread.sleep(100);
							while ((line = stdout.readLine()) != null) {
								sb.append(line);
                                 //Find the percentage in the standard output
								Pattern varPattern = Pattern.compile("\\w*\\%");
								Matcher matcher = varPattern.matcher(sb);
								if (matcher.find()) {
									String var = matcher.group();
									var = var.replaceAll("%", "");
									progress = Integer.parseInt(var);
									//Publish the current progress
                                    publish(progress);
								

								}
								sb.delete(0, sb.length());
							}

						}
						
						exit=process.waitFor();
					
						return null;


					}
					//When the process finished, tell the user whether it is successful or not according to the exit value
					@Override
				       protected void done(){
						
						if(exit==0){
							//If is successful, tell the user, and record it in the log file 
							JOptionPane.showMessageDialog(Download.this,"Finsh");
							
						}
						//If it is failed, show user the message
						else if (exit==1){
							JOptionPane.showMessageDialog(Download.this,"Interrupt");
							cancel.setVisible(false);
							
						}
						
						else if (exit==2){
							JOptionPane.showMessageDialog(Download.this,"Parse error");
							cancel.setVisible(false);
							
						}
						
						else if (exit==3){
							JOptionPane.showMessageDialog(Download.this,"File I/O error");
							cancel.setVisible(false);
							
						}
						
						else if (exit==4){
							JOptionPane.showMessageDialog(Download.this,"Network failure");
							cancel.setVisible(false);
							
						}
						
						else if (exit==5){
							JOptionPane.showMessageDialog(Download.this,"SSL verification failure");
							cancel.setVisible(false);
							
						}
						else if (exit==6){
							JOptionPane.showMessageDialog(Download.this,"Username/password authentication failure");
							cancel.setVisible(false);
							
						}
						
						progressBar.setValue(0);
						txt.setText("");
						
					}
					
					
					
					
					
					
					//Update the intermediate result in the ED thread
				     protected void process(List<Integer> chunks) {
				         for (int number : chunks) {
				        	 progressBar.setValue(number);
				         }
				     }

					

				}
                
				final Task task = new Task();
				
				task.execute();
				//When the cancel button been pressed, kill the process
				cancel.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
					task.process.destroy();
						
					}
					
					
					
					
					
				});
                cancel.setVisible(true);
                
			} else {
				//Show user this message when user want to download not open source files
				JOptionPane.showMessageDialog(this,"Sorry, can not help");
				txt.setText("");
			}
               
		} else {
			txt.setText("");
			JOptionPane.showMessageDialog(this, "Canceled");

		}
	

		
		
	}




}