package KIOSK;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateInfo_Dialog extends JDialog implements ActionListener {
	Font font;

	JTextField pw_tf; // 비밀번호 입력창
	JTextField confirm_pw_tf; // 비밀번호 확인 입력창
	String pw = "";
	String confirm_pw = "";
	String moblie = "";
	int position = -1;

	JButton ok_btn; // 확인 버튼
	JButton cancel_btn; // 취소 버튼
	JButton[][] dial_btn; // 다이얼 번호 버튼 배열

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int subWidth = 450;
	int subHeight = 600;
	int subXpos = (int) (screenSize.getWidth() / 2 - subWidth / 2);
	int subYpos = (int) (screenSize.getHeight() / 2 - subHeight / 2);

	public UpdateInfo_Dialog(String moblie) {
		this.moblie = moblie;
		
		setLayout(null); // 배치관리자 설정 x
		setBounds(subXpos, subYpos, subWidth, subHeight);
		setVisible(true);

		font = new Font("나눔스퀘어", Font.PLAIN, 25);

		JLabel update_LB = new JLabel("비밀번호 변경");
		update_LB.setFont(font);
		update_LB.setForeground(Color.WHITE);
		update_LB.setBackground(ColorInfo.instance.title_font_color);
		update_LB.setOpaque(true);
		update_LB.setHorizontalAlignment(JTextField.CENTER);
		update_LB.setBounds(130, 35, 160, 50);
		add(update_LB);

		textFieldSet();
		dialButtonSet();

		font = new Font("나눔스퀘어", Font.BOLD, 16);

		ok_btn = new JButton("확인");
		ok_btn.setBackground(ColorInfo.instance.button_color);
		ok_btn.setForeground(Color.WHITE);
		ok_btn.setFont(font);
		ok_btn.setBounds(230, 490, 100, 40);
		ok_btn.addActionListener(this);
		add(ok_btn);

		cancel_btn = new JButton("취소");
		cancel_btn.setBackground(ColorInfo.instance.button_sub_color);
		cancel_btn.setForeground(Color.GRAY);
		cancel_btn.setFont(font);
		cancel_btn.setBounds(90, 490, 100, 40);
		cancel_btn.addActionListener(this);
		add(cancel_btn);

		JLabel bg_LB = new JLabel();
		bg_LB.setBackground(ColorInfo.instance.bg_color);
		bg_LB.setOpaque(true);
		bg_LB.setBounds(12, 10, 410, 540);
		add(bg_LB);

	}

	void textFieldSet() {

		font = new Font("나눔스퀘어", Font.PLAIN, 22);
		pw_tf = new JTextField(5);
		pw_tf.setText("변경할 비밀번호");
		pw_tf.setBounds(90, 110, 240, 40);
		pw_tf.setBackground(ColorInfo.instance.textField_bg_color);
		pw_tf.setForeground(ColorInfo.instance.textField_font_color);
		pw_tf.setFont(font);
		pw_tf.setHorizontalAlignment(JTextField.CENTER);
		pw_tf.addActionListener(this);
		add(pw_tf);

		pw_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				pw_tf.setText("");
				position = 1;
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

		confirm_pw_tf = new JTextField(5);
		confirm_pw_tf.setText("비밀번호확인");
		confirm_pw_tf.setBounds(90, 170, 240, 40);
		confirm_pw_tf.setBackground(ColorInfo.instance.textField_bg_color);
		confirm_pw_tf.setForeground(ColorInfo.instance.textField_font_color);
		confirm_pw_tf.setFont(font);
		confirm_pw_tf.setHorizontalAlignment(JTextField.CENTER);
		confirm_pw_tf.addActionListener(this);
		add(confirm_pw_tf);

		confirm_pw_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				confirm_pw_tf.setText("");
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

	void dialButtonSet() {
		font = new Font("나눔스퀘어", Font.PLAIN, 20);
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
				dial_btn[i][n].setBounds(120 + (n * 70), 240 + (i * 60), 40, 40);
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
							if (pw.length() > 0)
								pw = pw.substring(0, pw.length() - 1);
						} else if (dial_btn[i][n].getText().equals("CLE")) {
							pw = "";
						} else {
							pw += dial_btn[i][n].getText();
						}
						pw_tf.setText(pw);
					} else if (position == 2) {
						if (dial_btn[i][n].getText().equals("DEL")) {
							if (confirm_pw.length() > 0)
								confirm_pw = confirm_pw.substring(0, confirm_pw.length() - 1);
						} else if (dial_btn[i][n].getText().equals("CLE")) {
							confirm_pw = "";
						} else {
							confirm_pw += dial_btn[i][n].getText();
						}
						confirm_pw_tf.setText(confirm_pw);
					}
					repaint();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialButtonPress(e);

		if (e.getSource() == ok_btn) {
			if (pw_tf.getText().equals("") || confirm_pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "모든 필드를 입력하세요.", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!(pw_tf.getText().equals(confirm_pw_tf.getText()))) {
				JOptionPane.showMessageDialog(null, "비밀번호가 맞지 않습니다.", "Message", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				for (int i = 0; i < FileManager.instance.userManager.size(); i++) {
					if (moblie.equals(FileManager.instance.userManager.get(i).getMobile())) {
						FileManager.instance.userManager.get(i).setPw(pw_tf.getText());
						FileManager.instance.updateUser(i, FileManager.instance.userManager.get(i));
						break;
					}
				}
				JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.", "Message", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		} else if (e.getSource() == cancel_btn) {
			dispose();
		}
	}

}
