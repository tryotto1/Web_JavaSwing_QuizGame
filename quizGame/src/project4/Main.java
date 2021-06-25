package project4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Main {
	private JFrame frame;
	private JPanel currentPanel;
	
	// ȭ�� ũ��
	int WIDTH = 1280, HEIGHT = 720;
	
	// ������ �޾ƿ��� ��, totalQNum : �� ���� ���� �� �����ΰ�? qNum : ���� ����� ��� �����ΰ�? 
	private int totalQNum = 4, qNum = 0;
	String[] radioName = new String[5];
	int myScore = 0;
	
	// JLabel ����
	JLabel score_label, score, rank_label, rank;
	
	// ���� ���
	String question[] = {"What is the world��s longest river called?", "Where is the Great Barrier Reef located?",
						"Which house was Harry Potter almost sorted into?", "Which country gifted the Statue of Liberty to the US?"};
	String correctAns[] = {"The Nile","Australia", "Slytherin ","France"};
	String listAns[][]= {{"The Nile", "The ABC", "The CDE","The SDA","The EQS"},
						{"SVQ","AVS","Australia","XQX","WQS"},
						{"SASC ","SDAV ","SSAV ","Slytherin ","SDX "},
						{"ASS","QQX","QSV","SCA","France"}};
	
	// main �Լ�
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// ������
	public Main() {
		initialize();
	}

	public void initialize() {	
		/* frame ����� */
		frame = new JFrame();
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* startPanel ���� */
		ImagePanel startPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image3.jpg")).getImage());
		String path = "/var/data/stuff/xyz.dat";
		String base = "/var/data";
		String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
		System.out.println(relative);
		
		frame.setSize(startPanel.getDim());
		frame.setPreferredSize(startPanel.getDim());		
		frame.getContentPane().add(startPanel);
		currentPanel = startPanel;
		
		/* QuizPanel ���� */
		ImagePanel quizPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image5.jpg")).getImage());	
		quizPanel.setVisible(false); // ��� ���д�
		frame.getContentPane().add(quizPanel);
		
		/* Summary Panel ���� */
		ImagePanel summaryPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image2.jpg")).getImage());
		summaryPanel.setVisible(false); // ��� ���д�
		frame.getContentPane().add(summaryPanel);
		
		/* startPanel */
		// JLabel
		JLabel titleGame = new JLabel("Game");		
		titleGame.setFont(new Font("Eras Bold ITC", Font.PLAIN, 80));
		titleGame.setBounds(0, 0, WIDTH, 500);
		titleGame.setHorizontalAlignment(JLabel.CENTER);
		titleGame.setForeground(Color.WHITE);
		startPanel.add(titleGame);		
		
		// JButton
		JButton startBtn = new JButton("Start");
		startBtn.setBorder(null);
		startBtn.setBounds(WIDTH/2 - 100, 350, 200, 80);
		startBtn.setHorizontalAlignment(JButton.CENTER);
		startBtn.setIcon(new ImageIcon(getClass().getResource("/images/button2.jpg")));
		startBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button2_clicked.jpg")));
		startBtn.setHorizontalTextPosition(JButton.CENTER);
		startBtn.setVerticalTextPosition(JButton.CENTER);
		startBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		startBtn.setForeground(Color.WHITE);
		
		JButton exitBtn = new JButton("Exit");
		exitBtn.setBorder(null);
		exitBtn.setBounds(WIDTH/2 - 100, 480, 200, 80);
		exitBtn.setIcon(new ImageIcon(getClass().getResource("/images/button3.jpg")));
		exitBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button3_clicked.jpg")));
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.CENTER);
		exitBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		exitBtn.setForeground(Color.WHITE);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���������Ƿ�, ���� ȭ������ �Ѿ�� ���� currentPanel�� �����ְ� quizPanel�� �����Ų��
				currentPanel.setVisible(false);
				currentPanel = quizPanel;
				currentPanel.setVisible(true);
			}
		});

		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				 
				JOptionPane.showMessageDialog(null, "�����ϰڽ��ϴ�");
				System.exit(0);
			}
		});
		
		startPanel.add(startBtn);
		startPanel.add(exitBtn);
		
		/* quizPanel */
		// text
		JLabel qTitle = new JLabel("Quiz");		
		qTitle.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		qTitle.setBounds(0, 20, WIDTH, 49);
		qTitle.setHorizontalAlignment(JLabel.CENTER);
		quizPanel.add(qTitle);
		
		JLabel qTxt = new JLabel("");		
		qTxt.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		qTxt.setBounds(0, 100, WIDTH, 49);
		qTxt.setHorizontalAlignment(JLabel.CENTER);		
		quizPanel.add(qTxt);
		
		// radioButton - answer �κ�
		ButtonGroup group = new ButtonGroup();	
		JRadioButton radio[] = new JRadioButton[5];		

		// DB�κ��� ������ ������ �޾ƿ��� �κ� (ù��° ����) - �߰� �ڵ� �ʿ�
		qTxt.setText(question[0]);	
		
		for(int i=0;i<5;i++) {
			radio[i] = new JRadioButton(listAns[0][i]);						
			radio[i].setBounds(200 + 200*i, 200, 200, 40);		
			radio[i].setOpaque(false);
			radio[i].setFont(new Font("Eras Bold ITC", Font.PLAIN, 13));
			group.add(radio[i]);
			quizPanel.add(radio[i]);
		}
		
		// JButton - submit �κ�
		JButton submitBtn = new JButton("Submit");
		submitBtn.setBorder(null);
		submitBtn.setBounds(950, 550, 200, 80);
		submitBtn.setIcon(new ImageIcon(getClass().getResource("/images/button4.jpg")));
		submitBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button4_clicked.jpg")));
		submitBtn.setHorizontalTextPosition(JButton.CENTER);
		submitBtn.setVerticalTextPosition(JButton.CENTER);
		submitBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		submitBtn.setForeground(Color.WHITE);
		quizPanel.add(submitBtn);
		
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				// ���� ���õ� �׸� DB�� �����ϴ� �ڵ� - ���� ä���� �ϴ� �κ�
				for(int i=0;i<5;i++) {
					if(radio[i].isSelected()==true) {
						if(radio[i].getText().equals(correctAns[qNum])) {
							myScore += 10;							
							score.setText("       " + Integer.toString(myScore));
						}					
						
						// DB�� �������ִ� �ڵ� �κ�
						// ~
						
						break;
					}
				}
				
				// ������ ������ �� - ���� SummaryPanel�� �Ѿ��
				if(qNum == totalQNum - 1) {
					qNum = 1;
					currentPanel.setVisible(false);
					currentPanel = summaryPanel;
					currentPanel.setVisible(true);
				}
				// ������ ������ �ƴ� �� - ���� ������ �����´�
				else {					
					qNum += 1;
					qTxt.setText(question[qNum]);
					for(int i=0;i<5;i++) {
						radio[i].setText(listAns[qNum][i]);						
					}
					group.clearSelection();
				}
			}
		});

		/* SummaryPanel */
		// JButton		
		JButton restartBtn = new JButton("Restart");
		restartBtn.setBorder(null);
		restartBtn.setBounds(950, 550, 200, 80);
		restartBtn.setIcon(new ImageIcon(getClass().getResource("/images/button5.jpg")));
		restartBtn.setPressedIcon(new ImageIcon(getClass().getResource("/images/button5_clicked.jpg")));
		restartBtn.setHorizontalTextPosition(JButton.CENTER);
		restartBtn.setVerticalTextPosition(JButton.CENTER);
		restartBtn.setFont(new Font("Eras Bold ITC", Font.PLAIN, 50));
		restartBtn.setForeground(Color.WHITE);		
		
		restartBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ���� ���������Ƿ�, ���� ȭ������ �Ѿ�� ���� currentPanel�� �����ְ� quizPanel�� �����Ų��
				currentPanel.setVisible(false);
				currentPanel = startPanel;
				currentPanel.setVisible(true);
			}
		});		
		summaryPanel.add(restartBtn);
		
		// text
		score_label = new JLabel("Score");		
		score_label.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		score_label.setBounds(500, 200, 139, 49);
		score_label.setForeground(Color.WHITE);
		summaryPanel.add(score_label);
		
		score = new JLabel("");		
		score.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		score.setBounds(700, 200, 139, 49);
		score.setBackground(Color.WHITE);
		score.setOpaque(true);
		summaryPanel.add(score);
		
		rank_label = new JLabel("Rank");		
		rank_label.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		rank_label.setBounds(500, 300, 139, 49);
		rank_label.setForeground(Color.WHITE);
		summaryPanel.add(rank_label);
		
		rank = new JLabel("");		
		rank.setFont(new Font("Eras Bold ITC", Font.PLAIN, 33));
		rank.setBounds(700, 300, 139, 49);
		rank.setBackground(Color.WHITE);
		rank.setOpaque(true);
		summaryPanel.add(rank);
		
		/* ���� ���� */			
		frame.pack();
	}
}

