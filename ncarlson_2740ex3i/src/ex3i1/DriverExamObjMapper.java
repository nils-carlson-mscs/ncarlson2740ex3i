package ex3i1;

import java.io.*;
import java.util.Scanner;
import javax.swing.DefaultListModel;

public class DriverExamObjMapper {
	private String fileName;
	private Scanner inputFile;
	private File file;
	
	public DriverExamObjMapper (String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		
		// Open file.
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			
			if (fileOpened) {
			    this.inputFile = new Scanner(file);
			}
	    }
		catch (IOException e) {}
	    
	    return fileOpened;
	}
	
	public void closeInputFile() {
		if(this.inputFile != null)
			this.inputFile.close();
	}
	
	public DriverExam getDriverExam() {
		DriverExam exam = null;
		DefaultListModel driverExamCollection = new DefaultListModel();
		
		if(this.openInputFile()) {
			
			while(this.inputFile.hasNext()) {
				String answer = inputFile.nextLine();
				driverExamCollection.addElement(answer);
			}
			exam = new DriverExam(driverExamCollection);
		}
		
		this.closeInputFile();
		return exam;
	}
}
