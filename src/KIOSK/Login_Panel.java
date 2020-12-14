package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login_Panel extends JPanel implements ActionListener {
	JButton checkOut_btn; // 퇴실하기 버튼
	JButton login_btn; // 로그인 버튼
	JButton join_btn; // 회원가입 버튼

	JButton[][] dial_btn; // 다이얼 번호 버튼 배열

	JTextField mobile_tf; // 핸드폰번호 입력창
	JTextField pw_tf; // 패스워드 입력창

	String mobile = "010";
	String pw = "";

	int position = -1;

	Font font;

	Image img = new ImageIcon("./src/Image/main.jpg").getImage();

	public Login_Panel() {
		setLayout(null); // 배치관리자 설정 x

		buttonSet();
		textFieldSet();
		JLabel bg_LB = new JLabel();
		bg_LB.setBackground(ColorInfo.instance.bg_color);
		bg_LB.setOpaque(true);
		bg_LB.setBounds(90, 240, 360, 300);
		add(bg_LB);

		dialButtonSet();
	}

	void buttonSet() {
		font = new Font("나눔스퀘어", Font.BOLD, 25);

		login_btn = new JButton("로그인");
		login_btn.setBackground(ColorInfo.instance.button_color);
		login_btn.setForeground(Color.WHITE);
		login_btn.setFont(font);
		login_btn.setBounds(135, 390, 270, 50);
		login_btn.addActionListener(this);
		add(login_btn);

		font = new Font("나눔스퀘어", Font.PLAIN, 16);

		join_btn = new JButton("회원가입");
		join_btn.setBackground(ColorInfo.instance.button_sub_color);
		join_btn.setForeground(Color.BLACK);
		join_btn.setFont(font);
		join_btn.setBounds(135, 470, 120, 40);
		join_btn.addActionListener(this);
		add(join_btn);

		checkOut_btn = new JButton("퇴실하기");
		checkOut_btn.setBackground(ColorInfo.instance.button_sub_color);
		checkOut_btn.setForeground(Color.BLACK);
		checkOut_btn.setFont(font);
		checkOut_btn.setBounds(285, 470, 120, 40);
		checkOut_btn.addActionListener(this);
		add(checkOut_btn);
	}

	void dialButtonSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 28);
		dial_btn = new JButton[4][3];
		int num = 1;
		for (int i = 0; i < dial_btn.length; i++) {
			for (int n = 0; n < dial_btn[i].length; n++) {
				dial_btn[i][n] = new JButton();
				dial_btn[i][n].setForeground(ColorInfo.instance.button_color);
				dial_btn[i][n].setBackground(ColorInfo.instance.dial_button_color);
				dial_btn[i][n].setFont(font);
				dial_btn[i][n].setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				if (num < 10) {
					dial_btn[i][n].setText(num + "");
				} else if (num == 10) {
					dial_btn[i][n].setText("CLE");
				} else if (num == 11) {
					dial_btn[i][n].setText("0");
				} else if (num == 12) {
					dial_btn[i][n].setText("DEL");
				}
				num++;
				dial_btn[i][n].setBounds(115 + (n * 120), 580 + (i * 80), 70, 70);
				dial_btn[i][n].addActionListener(this);
				add(dial_btn[i][n]);
			}
		}
	}

	void dialButtonPress(ActionEvent e) {

		for (int i = 0; i < dial_btn.length; i++) {
			for (int n = 0; n < dial_btn[i].length; n++) {
				if (e.getSource() == dial_btn[i][n]) {
					if (position == 1) {
						if (dial_btn[i][n].getText().equals("DEL")) {
							if (mobile.length() > 0)
								mobile = mobile.substring(0, mobile.length() - 1);
						} else if (dial_btn[i][n].getText().equals("CLE")) {
							mobile = "";
						} else {
							mobile += dial_btn[i][n].getText();
							if (mobile.length() > 10) {
								position = 2;
							}
						}
						mobile_tf.setText(mobile);
					} else if (position == 2) {
						if (dial_btn[i][n].getText().equals("DEL")) {
							if (pw.length() > 0)
								pw = pw.substring(0, pw.length() - 1);
						} else if (dial_btn[i][n].getText().equals("CLE")) {
							pw = "";
						} else {
							pw += dial_btn[i][n].getText();
						}
						pw_tf.setText(pw);
					}
					repaint();
				}
			}
		}
	}

	void textFieldSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 16);

		mobile_tf = new JTextField(15);
		mobile_tf.setText("010");
		mobile_tf.setBounds(135, 270, 270, 40);
		mobile_tf.setBackground(ColorInfo.instance.textField_bg_color);
		mobile_tf.setForeground(ColorInfo.instance.textField_font_color);
		mobile_tf.setFont(font);
		mobile_tf.setHorizontalAlignment(JTextField.CENTER);
		mobile_tf.addActionListener(this);
		add(mobile_tf);

		mobile_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				position = 1;
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});

		pw_tf = new JTextField(5);
		pw_tf.setText("비밀번호 입력");
		pw_tf.setBounds(135, 330, 270, 40);
		pw_tf.setBackground(ColorInfo.instance.textField_bg_color);
		pw_tf.setForeground(ColorInfo.instance.textField_font_color);
		pw_tf.setFont(font);
		pw_tf.setHorizontalAlignment(JTextField.CENTER);
		pw_tf.addActionListener(this);
		add(pw_tf);
		pw_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (pw_tf.getText().equals("비밀번호 입력"))
					pw_tf.setText("");
				position = 2;
				repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialButtonPress(e);

		// 로그인 버튼을 눌렀을 때
		if (e.getSource() == login_btn) {
			if (mobile_tf.getText().equals("01000000000") && pw_tf.getText().equals("0000")) {
				MainSystem.frame.setContentPane(new Admin_Panel()); // 관리자모드로 이동
				MainSystem.frame.revalidate();
			} else {
				String log = FileManager.instance.login(mobile_tf.getText(), pw_tf.getText());
				if (log.equals("")) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다.", "Message", JOptionPane.WARNING_MESSAGE);
				} else {
					MainSystem.frame.setContentPane(new Seat_Panel(log, mobile_tf.getText()));
					MainSystem.frame.revalidate();
				}
			}
			// 회원가입 버튼을 눌렀을 때
		} else if (e.getSource() == join_btn) {
			MainSystem.frame.setContentPane(new Join_Panel());
			MainSystem.frame.revalidate();
			// 퇴실하기 버튼을 눌렀을 때
		} else if (e.getSource() == checkOut_btn) {
			String log = FileManager.instance.login(mobile_tf.getText(), pw_tf.getText());
			if (log.equals("")) {
				JOptionPane.showMessageDialog(null, "존재하지 않는 번호입니다.", "Message", JOptionPane.WARNING_MESSAGE);
			} else {
				if (FileManager.instance.checkOut(mobile_tf.getText())) {
					JOptionPane.showMessageDialog(null, "퇴실이 완료되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
					MainSystem.frame.setContentPane(new Login_Panel());
					MainSystem.frame.revalidate();
				}
			}

		}

	}

}