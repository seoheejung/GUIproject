package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_Panel extends JPanel implements ActionListener {
	// 싱글톤패턴
	public static Admin_Panel instance = new Admin_Panel();

	Font font;
	Image img = new ImageIcon("./src/Image/main.jpg").getImage();;

	JButton userInfo_btn; // 사용자정보
	JButton seatInfo_btn; // 좌석이용현황
	JButton back_btn; // 돌아가기
	JButton shutdown_btn; // 시스템 종료

	public Admin_Panel() {
		setLayout(null);

		font = new Font("나눔스퀘어", Font.PLAIN, 25);

		JLabel admin_LB = new JLabel("마스터 관리");
		admin_LB.setFont(font);
		admin_LB.setForeground(Color.BLACK);
		admin_LB.setBackground(ColorInfo.instance.button_sub_color);
		admin_LB.setOpaque(true);
		admin_LB.setHorizontalAlignment(JTextField.CENTER);
		admin_LB.setBounds(170, 240, 200, 50);
		add(admin_LB);

		font = new Font("나눔스퀘어", Font.PLAIN, 22);

		userInfo_btn = new JButton("사용자정보");
		userInfo_btn.setBackground(ColorInfo.instance.title_font_color);
		userInfo_btn.setForeground(Color.WHITE);
		userInfo_btn.setFont(font);
		userInfo_btn.setBounds(90, 320, 160, 220);
		userInfo_btn.addActionListener(this);
		add(userInfo_btn);

		seatInfo_btn = new JButton("좌석이용현황");
		seatInfo_btn.setBackground(ColorInfo.instance.title_font_color);
		seatInfo_btn.setForeground(Color.WHITE);
		seatInfo_btn.setFont(font);
		seatInfo_btn.setBounds(290, 320, 160, 220);
		seatInfo_btn.addActionListener(this);
		add(seatInfo_btn);

		shutdown_btn = new JButton("시스템종료");
		shutdown_btn.setBackground(ColorInfo.instance.title_font_color);
		shutdown_btn.setForeground(Color.WHITE);
		shutdown_btn.setFont(font);
		shutdown_btn.setBounds(90, 580, 160, 220);
		shutdown_btn.addActionListener(this);
		add(shutdown_btn);

		back_btn = new JButton("메인으로 이동");
		back_btn.setBackground(ColorInfo.instance.title_font_color);
		back_btn.setForeground(Color.WHITE);
		back_btn.setFont(font);
		back_btn.setBounds(290, 580, 160, 220);
		back_btn.addActionListener(this);
		add(back_btn);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == userInfo_btn) {
			MainSystem.frame.setContentPane(new Admin_UserInfo_Panel());
			MainSystem.frame.revalidate();
		} else if (e.getSource() == seatInfo_btn) {
			MainSystem.frame.setContentPane(new Admin_SeatInfo_Panel());
			MainSystem.frame.revalidate();
		} else if (e.getSource() == shutdown_btn) {
			System.exit(0);
		} else if (e.getSource() == back_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		}
	}

}
