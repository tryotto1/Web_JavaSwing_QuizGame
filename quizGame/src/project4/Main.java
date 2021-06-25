package project4;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class Main {
	private JFrame frame;
	private JPanel currentPanel;
	
	// 화면 크기
	int WIDTH = 1280, HEIGHT = 720;
	
	// 문제를 받아왔을 때, totalQNum : 총 문제 수가 몇 문제인가? qNum : 지금 퀴즈는 몇번 문항인가? 
	private int totalQNum = 4, qNum = 0;
	String[] radioName = new String[5];
	int myScore = 0;
	
	// JLabel 모음
	JLabel score_label, score, rank_label, rank;
	
	// 문제 목록
	String question[] = {"What is the world’s longest river called?", "Where is the Great Barrier Reef located?",
						"Which house was Harry Potter almost sorted into?", "Which country gifted the Statue of Liberty to the US?"};
	String correctAns[] = {"The Nile","Australia", "Slytherin ","France"};
	String listAns[][]= {{"The Nile", "The ABC", "The CDE","The SDA","The EQS"},
						{"SVQ","AVS","Australia","XQX","WQS"},
						{"SASC ","SDAV ","SSAV ","Slytherin ","SDX "},
						{"ASS","QQX","QSV","SCA","France"}};
	
	// main 함수
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
	
	// 생성자
	public Main() {
		initialize();
	}

	public void initialize() {	
		/* frame 만들기 */
		frame = new JFrame();
		frame.setBounds(100,100,450,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* startPanel 선언 */
		ImagePanel startPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image3.jpg")).getImage());
		String path = "/var/data/stuff/xyz.dat";
		String base = "/var/data";
		String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
		System.out.println(relative);
		
		frame.setSize(startPanel.getDim());
		frame.setPreferredSize(startPanel.getDim());		
		frame.getContentPane().add(startPanel);
		currentPanel = startPanel;
		
		/* QuizPanel 선언 */
		ImagePanel quizPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image5.jpg")).getImage());	
		quizPanel.setVisible(false); // 잠시 꺼둔다
		frame.getContentPane().add(quizPanel);
		
		/* Summary Panel 선언 */
		ImagePanel summaryPanel = new ImagePanel(new ImageIcon(getClass().getResource("/images/image2.jpg")).getImage());
		summaryPanel.setVisible(false); // 잠시 꺼둔다
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
				// 게임 시작했으므로, 다음 화면으로 넘어가기 위해 currentPanel을 지워주고 quizPanel을 등장시킨다
				currentPanel.setVisible(false);
				currentPanel = quizPanel;
				currentPanel.setVisible(true);
			}
		});

		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				 
				JOptionPane.showMessageDialog(null, "종료하겠습니다");
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
		
		// radioButton - answer 부분
		ButtonGroup group = new ButtonGroup();	
		JRadioButton radio[] = new JRadioButton[5];		

		// DB로부터 문제와 선택지 받아오는 부분 (첫번째 문항) - 추가 코드 필요
		qTxt.setText(question[0]);	
		
		for(int i=0;i<5;i++) {
			radio[i] = new JRadioButton(listAns[0][i]);						
			radio[i].setBounds(200 + 200*i, 200, 200, 40);		
			radio[i].setOpaque(false);
			radio[i].setFont(new Font("Eras Bold ITC", Font.PLAIN, 13));
			group.add(radio[i]);
			quizPanel.add(radio[i]);
		}
		
		// JButton - submit 부분
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
				// 현재 선택된 항목 DB에 저장하는 코드 - 마저 채워야 하는 부분
				for(int i=0;i<5;i++) {
					if(radio[i].isSelected()==true) {
						if(radio[i].getText().equals(correctAns[qNum])) {
							myScore += 10;							
							score.setText("       " + Integer.toString(myScore));
						}					
						
						// DB에 저장해주는 코드 부분
						// ~
						
						break;
					}
				}
				
				// 마지막 문항일 때 - 최종 SummaryPanel로 넘어간다
				if(qNum == totalQNum - 1) {
					qNum = 1;
					currentPanel.setVisible(false);
					currentPanel = summaryPanel;
					currentPanel.setVisible(true);
				}
				// 마지막 문항이 아닐 때 - 다음 문제를 가져온다
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
				// 게임 시작했으므로, 다음 화면으로 넘어가기 위해 currentPanel을 지워주고 quizPanel을 등장시킨다
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
		
		/* 최종 부착 */			
		frame.pack();
	}
}

