package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.Normalizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Join_Panel extends JPanel implements ActionListener, ItemListener {

	JTextField name_tf; // 이름 입력창
	JTextField mobile_tf; // 전화번호 입력창
	JTextField pw_tf; // 비밀번호 입력창
	JTextField confirm_pw_tf; // 비밀번호 확인 입력창

	String name = "";
	String mobile = "";
	String pw = "";
	String confirm_pw = "";

	int position = -1;

	JCheckBox check = new JCheckBox("개인정보취급방침동의"); // 개인정보취급방침동의 체크박스
	boolean isChecked = false;

	Font font;

	Image img = new ImageIcon("./src/Image/bg.jpg").getImage();

	JButton join_btn; // 회원가입 버튼
	JButton back_btn; // 뒤로가기 버튼
	KeyBoard_btn_Setting keyBoard;

	public Join_Panel() {
		setLayout(null);

		// 회원가입 레이블
		font = new Font("나눔스퀘어", Font.PLAIN, 45);
		JLabel join_LB = new JLabel("회원 가입");
		join_LB.setFont(font);
		join_LB.setForeground(Color.WHITE);
		join_LB.setBounds(180, 50, 270, 50);
		add(join_LB);

		textFieldSet();
		buttonSet();

		JLabel bg_LB = new JLabel();
		bg_LB.setBackground(ColorInfo.instance.label_color);
		bg_LB.setOpaque(true);
		bg_LB.setBounds(90, 120, 360, 400);
		add(bg_LB);

		keyBoard = new KeyBoard_btn_Setting();
		for (int i = 0; i < keyBoard.firstLine_btn.length; i++) {
			keyBoard.firstLine_btn[i].addActionListener(this);
			add(keyBoard.firstLine_btn[i]);
		}
		for (int i = 0; i < keyBoard.secondLine_btn.length; i++) {
			keyBoard.secondLine_btn[i].addActionListener(this);
			add(keyBoard.secondLine_btn[i]);
		}

		for (int i = 0; i < keyBoard.thirdLine_btn.length; i++) {
			keyBoard.thirdLine_btn[i].addActionListener(this);
			add(keyBoard.thirdLine_btn[i]);
		}

		for (int i = 0; i < keyBoard.fourthLine_btn.length; i++) {
			keyBoard.fourthLine_btn[i].addActionListener(this);
			add(keyBoard.fourthLine_btn[i]);
		}
	}

	void keyBoardButtonPress(ActionEvent e) {
		String temp = "";
		temp = keyBoard.keyBoardButtonPress(e);

		if (position == 1) {
			if (temp.equals("◁")) {
				if (name.length() > 0)
					name = name.substring(0, name.length() - 1);
			} else {
				name += temp;
			}
			name_tf.setText(name);
		} else if (position == 2) {
			if (temp.equals("◁")) {
				if (mobile.length() > 0)
					mobile = mobile.substring(0, mobile.length() - 1);
			} else {
				mobile += temp;
				if (mobile.length() > 10) {
					position = 3;
				}
			}
			mobile_tf.setText(mobile);
		} else if (position == 3) {
			if (temp.equals("◁")) {
				if (pw.length() > 0)
					pw = pw.substring(0, pw.length() - 1);
			} else {
				pw += temp;
			}
			pw_tf.setText(pw);
		} else if (position == 4) {
			if (temp.equals("◁")) {
				if (confirm_pw.length() > 0)
					confirm_pw = confirm_pw.substring(0, confirm_pw.length() - 1);
			} else {
				confirm_pw += temp;
			}
			confirm_pw_tf.setText(confirm_pw);
		}

		repaint();
	}

	void buttonSet() {
		font = new Font("나눔스퀘어", Font.BOLD, 16);

		join_btn = new JButton("회원가입");
		join_btn.setBackground(ColorInfo.instance.button_color);
		join_btn.setForeground(Color.WHITE);
		join_btn.setFont(font);
		join_btn.setBounds(285, 440, 120, 40);
		join_btn.addActionListener(this);
		add(join_btn);

		back_btn = new JButton("취소");
		back_btn.setBackground(ColorInfo.instance.button_color);
		back_btn.setForeground(Color.WHITE);
		back_btn.setFont(font);
		back_btn.setBounds(135, 440, 120, 40);
		back_btn.addActionListener(this);
		add(back_btn);
	}

	void textFieldSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 16);

		name_tf = new JTextField(15);
		name_tf.setText("이름");
		name_tf.setBounds(135, 150, 270, 40);
		name_tf.setBackground(ColorInfo.instance.textField_color);
		name_tf.setForeground(Color.WHITE);
		name_tf.setFont(font);
		name_tf.setHorizontalAlignment(JTextField.CENTER);
		name_tf.addActionListener(this);
		add(name_tf);

		name_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				name_tf.setText("");
				position = 1;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		mobile_tf = new JTextField(15);
		mobile_tf.setText("휴대폰번호");
		mobile_tf.setBounds(135, 210, 270, 40);
		mobile_tf.setBackground(ColorInfo.instance.textField_color);
		mobile_tf.setForeground(Color.WHITE);
		mobile_tf.setFont(font);
		mobile_tf.setHorizontalAlignment(JTextField.CENTER);
		mobile_tf.addActionListener(this);
		add(mobile_tf);

		mobile_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				mobile_tf.setText("");
				position = 2;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		pw_tf = new JTextField(5);
		pw_tf.setText("비밀번호");
		pw_tf.setBounds(135, 270, 270, 40);
		pw_tf.setBackground(ColorInfo.instance.textField_color);
		pw_tf.setForeground(Color.WHITE);
		pw_tf.setFont(font);
		pw_tf.setHorizontalAlignment(JTextField.CENTER);
		pw_tf.addActionListener(this);
		add(pw_tf);

		pw_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				pw_tf.setText("");
				position = 3;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		confirm_pw_tf = new JTextField(5);
		confirm_pw_tf.setText("비밀번호확인");
		confirm_pw_tf.setBounds(135, 330, 270, 40);
		confirm_pw_tf.setBackground(ColorInfo.instance.textField_color);
		confirm_pw_tf.setForeground(Color.WHITE);
		confirm_pw_tf.setFont(font);
		confirm_pw_tf.setHorizontalAlignment(JTextField.CENTER);
		confirm_pw_tf.addActionListener(this);
		add(confirm_pw_tf);

		confirm_pw_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				confirm_pw_tf.setText("");
				position = 4;
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});

		check.setBounds(135, 390, 200, 30);
		check.setFont(font);
		check.setBackground(ColorInfo.instance.label_color);
		check.addItemListener(this);
		add(check);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		keyBoardButtonPress(e);

		// 취소 버튼 눌렀을 때 로그인창으로 이동
		if (e.getSource() == back_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();

		} else if (e.getSource() == join_btn) {
			// 회원가입 버튼 눌렀을 때
			if (name_tf.getText().equals("") || mobile_tf.getText().equals("") || pw_tf.getText().equals("")
					|| confirm_pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!(pw_tf.getText().equals(confirm_pw_tf.getText()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!isChecked) {
				JOptionPane.showMessageDialog(null, "개인정보취급방침동의를 체크해주세요.", "", JOptionPane.WARNING_MESSAGE);
				return;
			}

			for (int i = 0; i < FileManager.instance.userManager.size(); i++) {
				if (mobile_tf.getText().equals(FileManager.instance.userManager.get(i).getMobile())) {
					JOptionPane.showMessageDialog(null, "동일한 번호가 있습니다.", "Message", JOptionPane.WARNING_MESSAGE);
					return;
				}

			}

			UserInfo user = new UserInfo();
			user.setName(name_tf.getText());
			user.setMobile(mobile_tf.getText());
			user.setPw(pw_tf.getText());
			user.setSeatNum(0);
			user.setMaxTime(0);
			user.setPreTime(0);

			FileManager.instance.addUser(user);
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "Message", JOptionPane.PLAIN_MESSAGE);
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// 아이템의 상태 변화의 종류를 상수로 반환
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// 사용자가 아이템을 선택하면 ItemEvent.SELECTED 값인 1 반환
			isChecked = true;
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			// 다시 선택하지 않으면 ItemEvent.DESELECTED 값인 2 반환
			isChecked = false;
		}

	}
}