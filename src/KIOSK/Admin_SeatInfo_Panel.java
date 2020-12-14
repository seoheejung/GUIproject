package KIOSK;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_SeatInfo_Panel extends JPanel implements ActionListener {
	Font font;

	Image img = new ImageIcon("./src/Image/seat.jpg").getImage();

	ArrayList<UserInfo> user_list = FileManager.instance.userManager;

	JLabel info_LB;
	JLabel infoName_LB;
	JLabel infoMobile_LB;
	JLabel infoPreTime_LB;
	JLabel infoMaxTime_LB;

	JButton back_btn; // 로그인화면 버튼
	JButton update_btn; // 정보수정 버튼
	JButton[][] seat_btn; // 좌석 버튼

	public Admin_SeatInfo_Panel() {

		setLayout(null); // 배치관리자 설정 x

		// 정보 레이블
		font = new Font("나눔스퀘어", Font.BOLD, 24);
		info_LB = new JLabel("좌석 정보");
		info_LB.setFont(font);
		info_LB.setForeground(Color.white);
		info_LB.setBounds(40, 35, 120, 40);
		add(info_LB);

		font = new Font("나눔스퀘어", Font.PLAIN, 22);
		back_btn = new JButton("돌아가기");
		back_btn.setBackground(ColorInfo.instance.button_sub_color);
		back_btn.setForeground(Color.black);
		back_btn.setFont(font);
		back_btn.setBounds(380, 70, 120, 60);
		back_btn.addActionListener(this);
		add(back_btn);

		font = new Font("나눔스퀘어", Font.PLAIN, 24);
		JLabel seat_LB = new JLabel("좌석 이용 현황 : " + seatUseState());
		seat_LB.setFont(font);
		seat_LB.setForeground(Color.black);
		seat_LB.setBounds(50, 200, 240, 40);
		add(seat_LB);

		seatStateInfo(user_list.get(0));
		seatInfo_invisible();

		seatButtonSet();
	}

	void seatStateInfo(UserInfo user) {

		font = new Font("나눔스퀘어", Font.PLAIN, 16);
		infoName_LB = new JLabel("· 이름 : " + user.getName());
		infoName_LB.setFont(font);
		infoName_LB.setForeground(Color.white);
		infoName_LB.setBounds(40, 80, 200, 20);
		infoName_LB.setVisible(true);
		add(infoName_LB);

		infoMobile_LB = new JLabel("· H.P : " + user.getMobile());
		infoMobile_LB.setFont(font);
		infoMobile_LB.setForeground(Color.white);
		infoMobile_LB.setBounds(40, 100, 200, 20);
		infoMobile_LB.setVisible(true);
		add(infoMobile_LB);

		infoPreTime_LB = new JLabel("· 시작 시간 : " + user.getPreTime());
		infoPreTime_LB.setFont(font);
		infoPreTime_LB.setForeground(Color.white);
		infoPreTime_LB.setBounds(40, 120, 280, 20);
		infoPreTime_LB.setVisible(true);
		add(infoPreTime_LB);

		infoMaxTime_LB = new JLabel("· 종료 시간 : " + endTime(user));
		infoMaxTime_LB.setFont(font);
		infoMaxTime_LB.setForeground(Color.white);
		infoMaxTime_LB.setBounds(40, 140, 280, 20);
		infoMaxTime_LB.setVisible(true);
		add(infoMaxTime_LB);

	}

	String endTime(UserInfo user) {
		String endTime = "";
		if (!user.getPreTime().equals("0")) {
			Date date = null;
			try {
				date = FileManager.instance.format.parse(user.getPreTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.HOUR, user.getMaxTime());
			endTime = FileManager.instance.format.format(cal.getTime());
		}
		return endTime;
	}

	void seatInfo_invisible() {
		infoName_LB.setVisible(false);
		infoMobile_LB.setVisible(false);
		infoPreTime_LB.setVisible(false);
		infoMaxTime_LB.setVisible(false);
	}

	String seatUseState() {
		String state = "";
		int check = 0;
		for (int i = 0; i < FileManager.instance.seatManager.size(); i++) {
			if (!FileManager.instance.seatManager.get(i).isSeatUse()) {
				check += 1;
			}
		}

		state = check + "/" + FileManager.instance.seatManager.size();
		return state;
	}

	void seatButtonSet() {
		seat_btn = new JButton[4][7];
		int num = 1;
		font = new Font("나눔스퀘어", Font.BOLD, 18);
		for (int i = 0; i < seat_btn.length; i++) {
			for (int n = 0; n < seat_btn[i].length; n++) {
				seat_btn[i][n] = new JButton();
				seat_btn[i][n].setText(num + "");
				seat_btn[i][n].setFont(font);
				seat_btn[i][n].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				seat_btn[i][n].setBackground(ColorInfo.instance.disabled_button_color);
				if (FileManager.instance.seatManager.get(num - 1).isSeatUse()) {
					seat_btn[i][n].setBackground(Color.black);
				}
				seat_btn[i][n].setForeground(Color.WHITE);

				if (i < 2) {
					seat_btn[i][n].setBounds(190 + (i * 90), 390 + (n * 58), 44, 44);
				} else if (i >= 2) {
					seat_btn[i][n].setBounds(165 + (i * 90), 390 + (n * 58), 44, 44);
				}
				seat_btn[i][n].addActionListener(this);
				add(seat_btn[i][n]);
				num += 1;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == back_btn) {
			MainSystem.frame.setContentPane(new Admin_Panel());
			MainSystem.frame.revalidate();
		} else {
			for (int i = 0; i < seat_btn.length; i++) {
				for (int n = 0; n < seat_btn[i].length; n++) {
					if (e.getSource() == seat_btn[i][n]) {
						for (int j = 0; j < user_list.size(); j++) {
							int temp = Integer.parseInt(seat_btn[i][n].getText());
							if (FileManager.instance.seatManager.get(temp - 1).isSeatUse()) {
								if (user_list.get(j).getSeatNum() == Integer.parseInt(seat_btn[i][n].getText())) {
									seatInfo_invisible();
									seatStateInfo(user_list.get(j));
									repaint();
								}
							} else {
								seatInfo_invisible();
								repaint();
							}
						}
					}
				}
			}
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

}