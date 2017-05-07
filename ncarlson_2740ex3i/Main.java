package ex3i1;
import javax.swing.DefaultListModel;

public class Main {

	public static void main(String[] args) {
//		char [] answers = {'B', 'D', 'A', 'A', 'C', 'A', 'B', 'A', 'C', 'D'};
//		DefaultListModel answersList = new DefaultListModel();
//		answersList.addElement("B");
//		answersList.addElement("C");
//		answersList.addElement("A");
//		answersList.addElement("D");
//		answersList.addElement("C");
//		answersList.addElement("A");
//		answersList.addElement("D");
//		answersList.addElement("B");
//		answersList.addElement("A");
//		answersList.addElement("C");
//		DriverExam exam = new DriverExam(answersList);
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		DriverExam exam = mapper.getDriverExam();
		
		DefaultListModel responsesList = new DefaultListModel();
		responsesList.addElement("B");
		responsesList.addElement("C");
		responsesList.addElement("Z");
		responsesList.addElement("D");
		responsesList.addElement("C");
		responsesList.addElement("A");
		responsesList.addElement("D");
		responsesList.addElement("B");
		responsesList.addElement("A");
		responsesList.addElement("C");
		
		exam.setResponses(responsesList);
		
		System.out.println("First invalid response: " + exam.validate());
		System.out.println("Total Correct: " + exam.totalCorrect());
		System.out.println("Total Incorrect: " + exam.totalIncorrect());
		
		if(exam.passed())
			System.out.println("Passed test");
		else
			System.out.println("Try Again");
		
		System.out.print("Questions missed:");
		int[] missed = exam.questionsMissed();
		int i=0;
		
		while(i < missed.length && missed[i] > 0){
			System.out.print(" " + missed[i]);
			i++;
		}
		return;
	}

}

