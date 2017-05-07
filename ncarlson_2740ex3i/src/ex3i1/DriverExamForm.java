package ex3i1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DriverExamForm extends JFrame {

	private JPanel contentPane;
	private JList responsesList;
	private DefaultListModel responsesListModel;
	private JLabel resultLabel;
	private JTextField inputAnswerTextField;
	private JButton btnMissed;
	private JButton btnIncorrect;
	private JButton btnCorrect;
	private JButton btnPass;
	private JLabel questNumLabel;
	private JButton btnPrev;
	private JButton btnNext;
	private DriverExam exam;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DriverExamForm frame = new DriverExamForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DriverExamForm() {
		setTitle("ncarlsonex3i Driver Exam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblResults = new JLabel("Result:");
		lblResults.setBounds(125, 13, 56, 16);
		contentPane.add(lblResults);
		
		JLabel lblResponses = new JLabel("Responses:");
		lblResponses.setBounds(25, 13, 88, 16);
		contentPane.add(lblResponses);
		
		JList list = new JList();
		list.setBackground(UIManager.getColor("Label.background"));
		list.setEnabled(false);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(25, 46, 26, 190);
		contentPane.add(list);
		
		responsesList = new JList();
		responsesList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				do_responsesList_valueChanged(e);
			}
		});
		responsesList.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		responsesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		responsesList.setBounds(63, 42, 36, 194);
		contentPane.add(responsesList);
		
		resultLabel = new JLabel("");
		resultLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		resultLabel.setBounds(125, 42, 122, 28);
		contentPane.add(resultLabel);
		
		btnCorrect = new JButton("Correct");
		btnCorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnCorrect_actionPerformed(e);
			}
		});
		btnCorrect.setBounds(125, 110, 122, 25);
		contentPane.add(btnCorrect);
		
		btnIncorrect = new JButton("Incorrect");
		btnIncorrect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnIncorrect_actionPerformed(e);
			}
		});
		btnIncorrect.setBounds(125, 137, 122, 25);
		contentPane.add(btnIncorrect);
		
		btnPass = new JButton("Pass");
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPass_actionPerformed(e);
			}
		});
		btnPass.setBounds(125, 83, 122, 25);
		contentPane.add(btnPass);
		
		btnMissed = new JButton("List Incorrect");
		btnMissed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnMissed_actionPerformed(e);
			}
		});
		btnMissed.setBounds(125, 166, 122, 25);
		contentPane.add(btnMissed);
		
		questNumLabel = new JLabel("#0:");
		questNumLabel.setBounds(25, 252, 36, 16);
		contentPane.add(questNumLabel);
		
		inputAnswerTextField = new JTextField();
		inputAnswerTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				do_inputAnswerTextField_focusGained(e);
			}
		});
		inputAnswerTextField.setBounds(63, 249, 36, 22);
		contentPane.add(inputAnswerTextField);
		inputAnswerTextField.setColumns(10);
		
		btnPrev = new JButton("Prev");
		btnPrev.setEnabled(false);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnPrev_actionPerformed(e);
			}
		});
		btnPrev.setBounds(131, 284, 70, 25);
		contentPane.add(btnPrev);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNext_actionPerformed(e);
			}
		});
		btnNext.setBounds(131, 248, 70, 25);
		contentPane.add(btnNext);
		
		DriverExamObjMapper mapper = new DriverExamObjMapper("DriverExam.txt");
		this.exam = mapper.getDriverExam();
		this.responsesListModel = exam.getAnswers();
		this.responsesList.setModel(responsesListModel);
	}
	
	protected void do_btnPass_actionPerformed(ActionEvent e) {
		exam.setResponses((DefaultListModel) this.responsesList.getModel());
		int invalid = exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} 
		else{
			if(this.exam.passed())
				this.resultLabel.setText("Passed!");
			else
				this.resultLabel.setText("Try Again.");
		}
	}
	protected void do_btnCorrect_actionPerformed(ActionEvent e) {
		exam.setResponses((DefaultListModel) this.responsesList.getModel());
		int invalid = exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} 
		else{
			resultLabel.setText("Correct: " + exam.totalCorrect());
		}
	}
	protected void do_btnIncorrect_actionPerformed(ActionEvent e) {
		exam.setResponses((DefaultListModel) this.responsesList.getModel());
		int invalid = exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} 
		else{
			resultLabel.setText("Incorrect: " + exam.totalIncorrect());
		}
	}
	protected void do_btnMissed_actionPerformed(ActionEvent e) {
		exam.setResponses((DefaultListModel) this.responsesList.getModel());
		int invalid = exam.validate();
		if (invalid >= 0){
			resultLabel.setText("Invalid response #" + Integer.toString(invalid + 1));
			responsesList.setSelectedIndex(invalid);
		} 
		else{
			int[] missed = exam.questionsMissed();
			resultLabel.setText("Incorrect: ");
			for(int i=0; missed[i] != 0; i++)
				resultLabel.setText(resultLabel.getText() + " " + Integer.parseInt(String.valueOf(missed[i])));
		}
	}
	protected void do_btnPrev_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() - 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        btnNext.setEnabled(true);
        if (responsesList.getSelectedIndex() == 0) 
            btnPrev.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_btnNext_actionPerformed(ActionEvent e) {
		this.responsesListModel.setElementAt(
                inputAnswerTextField.getText().toUpperCase(), 
                responsesList.getSelectedIndex());
        responsesList.setSelectedIndex(responsesList.getSelectedIndex() + 1);
        questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());
        
        btnPrev.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
        	btnNext.setEnabled(false);
        inputAnswerTextField.requestFocus();
	}
	protected void do_responsesList_valueChanged(ListSelectionEvent e) {
		questNumLabel.setText("#" + Integer.toString((responsesList.getSelectedIndex() + 1)));
        inputAnswerTextField.setText((String)responsesList.getSelectedValue());    

        btnPrev.setEnabled(true);
        btnNext.setEnabled(true);
        if (responsesList.getSelectedIndex() == responsesListModel.getSize() - 1)
        	btnNext.setEnabled(false);
        if (responsesList.getSelectedIndex() == 0) 
        	 btnPrev.setEnabled(false);
        inputAnswerTextField.requestFocus();  
	}
	protected void do_inputAnswerTextField_focusGained(FocusEvent e) {
		  inputAnswerTextField.selectAll();
	}
}
