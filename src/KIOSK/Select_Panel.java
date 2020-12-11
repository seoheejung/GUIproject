package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Select_Panel extends JPanel implements ActionListener {

	Font font;
	String name;
	String mobile;
	int seatNum;
	String useTime = "";
	int price = 0;

	Image img = new ImageIcon("./src/Image/purchase.jpg").getImage();

	JButton login_btn; // 처음으로 버튼
	JButton seat_btn; // 좌석화면 버튼
	JButton next_btn; // 다음 버튼

	JLabel goods_LB;
	JLabel hour_LB;

	JLabel summary_LB;
	JLabel seatNum_LB;
	JLabel useTime_LB;
	JLabel totalPrice_LB;

	JRadioButton fixedTicket_rd; // 정액권 라디오버튼
	JRadioButton hourTicket_rd; // 시간권 라디오버튼
	ButtonGroup Ticket_gp; // 라디오버튼 그룹

	JRadioButton[] fixed_rd; // 정액권 종류 라디오 버튼
	JRadioButton[] hour_rd; // 시간권 종류 라디오 버튼

	public Select_Panel(String name, String mobile, int seatNum) {
		this.name = name;
		this.mobile = mobile;
		this.seatNum = seatNum;

		setLayout(null); // 배치관리자 설정 x
		info(name, mobile);

		font = new Font("나눔스퀘어", Font.PLAIN, 24);
		goods_LB = new JLabel(" [1] 상품 선택");
		goods_LB.setFont(font);
		goods_LB.setForeground(Color.WHITE);
		goods_LB.setOpaque(true);
		goods_LB.setBackground(ColorInfo.instance.dial_button_color);
		goods_LB.setBounds(50, 200, 170, 50);
		add(goods_LB);

		hour_LB = new JLabel(" [2] 시간 선택");
		hour_LB.setFont(font);
		hour_LB.setForeground(Color.WHITE);
		hour_LB.setOpaque(true);
		hour_LB.setBackground(ColorInfo.instance.dial_button_color);
		hour_LB.setBounds(50, 360, 170, 50);
		hour_LB.setVisible(false);
		add(hour_LB);

		summary_LB = new JLabel(" [3] 내역 확인");
		summary_LB.setFont(font);
		summary_LB.setForeground(Color.WHITE);
		summary_LB.setOpaque(true);
		summary_LB.setBackground(ColorInfo.instance.dial_button_color);
		summary_LB.setBounds(50, 680, 170, 50);
		summary_LB.setVisible(false);
		add(summary_LB);

		goods_radioButtonSet();
		hour_radioButtonSet();
		fixed_radioButtonSet();
		summaryInfo(useTime);
		summary_invisible();

	}

	void goods_radioButtonSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 22);

		Ticket_gp = new ButtonGroup();
		hourTicket_rd = new JRadioButton(" 시간권");
		hourTicket_rd.setBounds(80, 260, 240, 40);
		hourTicket_rd.setFont(font);
		hourTicket_rd.setBackground(new Color(255, 252, 248));
		hourTicket_rd.addActionListener(this);

		fixedTicket_rd = new JRadioButton(" 정액권");
		fixedTicket_rd.setBounds(80, 300, 240, 40);
		fixedTicket_rd.setFont(font);
		fixedTicket_rd.setBackground(new Color(255, 252, 248));
		fixedTicket_rd.addActionListener(this);

		Ticket_gp.add(hourTicket_rd);
		Ticket_gp.add(fixedTicket_rd);

		this.add(hourTicket_rd);
		this.add(fixedTicket_rd);

	}

	void hour_radioButtonSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 20);
		hour_rd = new JRadioButton[6];

		ButtonGroup hourTicket_gp = new ButtonGroup();
		for (int i = 0; i < hour_rd.length; i++) {
			hour_rd[i] = new JRadioButton(" " + (i + 1) * 2 + "시간");
			hour_rd[i].setBounds(80, 420 + i * 40, 240, 40);
			hour_rd[i].setFont(font);
			hour_rd[i].setBackground(new Color(255, 252, 248));
			hour_rd[i].addActionListener(this);
			hour_rd[i].setVisible(false);
			hourTicket_gp.add(hour_rd[i]);
			this.add(hour_rd[i]);
		}
	}

	void fixed_radioButtonSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 20);
		fixed_rd = new JRadioButton[2];

		ButtonGroup fixedTicket_gp = new ButtonGroup();
		for (int i = 0; i < fixed_rd.length; i++) {
			fixed_rd[i] = new JRadioButton(" " + (i + 1) * 50 + "시간");
			fixed_rd[i].setBounds(80, 420 + i * 40, 240, 40);
			fixed_rd[i].setFont(font);
			fixed_rd[i].setBackground(new Color(255, 252, 248));
			fixed_rd[i].addActionListener(this);
			fixed_rd[i].setVisible(false);
			fixedTicket_gp.add(fixed_rd[i]);
			this.add(fixed_rd[i]);
		}
	}

	void radio_visible(JRadioButton[] rd) {
		for (int i = 0; i < rd.length; i++) {
			rd[i].setVisible(true);
		}
	}

	void radio_invisible(JRadioButton[] rd) {
		for (int i = 0; i < rd.length; i++) {
			rd[i].setVisible(false);
		}
	}

	int priceReturn(String text) {
		price = 0;
		String temp = "";
		if (!text.equals("")) {
			for (int i = 0; i < text.length(); i++) {
				if (text.charAt(i) == '시') {
					break;
				}
				temp += text.charAt(i);
			}
			temp = temp.substring(1, temp.length());
			if (hourTicket_rd.isSelected()) {
				price = Integer.parseInt(temp) * 1000 + 1000;
			} else if (fixedTicket_rd.isSelected()) {
				price = Integer.parseInt(temp) * 1000 + 10000;
			}

		}
		return price;
	}

	void summaryInfo(String useTime) {

		summary_LB.setVisible(true);

		font = new Font("나눔스퀘어", Font.PLAIN, 20);
		seatNum_LB = new JLabel("· 이용좌석          " + seatNum + "번");
		seatNum_LB.setFont(font);
		seatNum_LB.setForeground(Color.BLACK);
		seatNum_LB.setBackground(ColorInfo.instance.label_color);
		seatNum_LB.setOpaque(true);
		seatNum_LB.setVisible(true);
		seatNum_LB.setBounds(70, 740, 240, 40);
		add(seatNum_LB);

		useTime_LB = new JLabel("· 이용시간         " + useTime);
		useTime_LB.setFont(font);
		useTime_LB.setForeground(Color.BLACK);
		useTime_LB.setBackground(ColorInfo.instance.label_color);
		useTime_LB.setOpaque(true);
		useTime_LB.setVisible(true);
		useTime_LB.setBounds(70, 780, 240, 40);
		add(useTime_LB);

		totalPrice_LB = new JLabel("· 결제금액          " + priceReturn(useTime) + "원");
		totalPrice_LB.setFont(font);
		totalPrice_LB.setForeground(Color.BLACK);
		totalPrice_LB.setBackground(ColorInfo.instance.label_color);
		totalPrice_LB.setOpaque(true);
		totalPrice_LB.setVisible(true);
		totalPrice_LB.setBounds(70, 820, 240, 40);
		add(totalPrice_LB);

		font = new Font("나눔스퀘어", Font.PLAIN, 24);
		next_btn = new JButton("다음");
		next_btn.setForeground(Color.gray);
		next_btn.setBackground(ColorInfo.instance.label_color);
		next_btn.setFont(font);
		next_btn.setBounds(340, 790, 120, 60);
		next_btn.setVisible(true);
		next_btn.addActionListener(this);
		add(next_btn);
	}

	void summary_invisible() {
		summary_LB.setVisible(false);
		seatNum_LB.setVisible(false);
		useTime_LB.setVisible(false);
		totalPrice_LB.setVisible(false);
		next_btn.setVisible(false);
	}

	void info(String name, String mobile) {
		// 정보 레이블
		font = new Font("나눔스퀘어", Font.BOLD, 40);
		JLabel name_LB = new JLabel(name);
		name_LB.setFont(font);
		name_LB.setForeground(Color.white);
		name_LB.setBounds(40, 60, 120, 40);
		add(name_LB);

		font = new Font("나눔스퀘어", Font.BOLD, 16);
		JLabel mobile_LB = new JLabel("HP." + mobile);
		mobile_LB.setFont(font);
		mobile_LB.setForeground(Color.white);
		mobile_LB.setBounds(40, 110, 200, 30);
		add(mobile_LB);

		font = new Font("나눔스퀘어", Font.BOLD, 22);
		seat_btn = new JButton("좌석화면");
		seat_btn.setBackground(ColorInfo.instance.label_color);
		seat_btn.setForeground(Color.black);
		seat_btn.setFont(font);
		seat_btn.setBounds(240, 70, 120, 60);
		seat_btn.addActionListener(this);
		add(seat_btn);

		login_btn = new JButton("처음으로");
		login_btn.setBackground(ColorInfo.instance.label_color);
		login_btn.setForeground(Color.black);
		login_btn.setFont(font);
		login_btn.setBounds(380, 70, 120, 60);
		login_btn.addActionListener(this);
		add(login_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		} else if (e.getSource() == seat_btn) {
			MainSystem.frame.setContentPane(new Seat_Panel(name, mobile));
			MainSystem.frame.revalidate();

		} else if (e.getSource() == next_btn) {
			MainSystem.frame.setContentPane(new Payment_panel(name, mobile, seatNum, useTime, price));
			MainSystem.frame.revalidate();

		} else if (e.getSource() == hourTicket_rd) {
			hour_LB.setVisible(true);
			radio_visible(hour_rd);
			radio_invisible(fixed_rd);
			repaint();
		} else if (e.getSource() == fixedTicket_rd) {
			hour_LB.setVisible(true);
			radio_visible(fixed_rd);
			radio_invisible(hour_rd);
			repaint();

		} else if (hourTicket_rd.isSelected()) {
			for (int i = 0; i < hour_rd.length; i++) {
				if (e.getSource() == hour_rd[i]) {
					summary_invisible();
					useTime = hour_rd[i].getText();
					summaryInfo(useTime);
					repaint();
				}
			}
		} else if (fixedTicket_rd.isSelected()) {
			for (int i = 0; i < fixed_rd.length; i++) {
				if (e.getSource() == fixed_rd[i]) {
					summary_invisible();
					useTime = fixed_rd[i].getText();
					summaryInfo(useTime);
					repaint();
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
