import java.io.File;

import javax.swing.JFileChooser;


public class myFileChooser {
	private  JFileChooser fc;
	public myFileChooser(){
		    fc = new JFileChooser(
					System.getProperty("user.dir"));
			fc.setDialogTitle("Save the output file");
			fc.setMultiSelectionEnabled(false);
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.setFileHidingEnabled(true);
			fc.setAcceptAllFileFilterUsed(false);
	}
	
	public String saveChooser(){
		String nameOfNewFile = null;

		int returnValue = fc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			nameOfNewFile = fc.getSelectedFile().getAbsolutePath();
		}
		
		return nameOfNewFile;
	}
	
	public File openChooser(){
		File fileName = null;

		int returnValue = fc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			fileName = fc.getSelectedFile();
		}
		
		return fileName;
	}

}