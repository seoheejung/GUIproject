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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login_Panel extends JPanel implements ActionListener {
	JButton exit_btn; // ����ϱ� ��ư
	JButton login_btn; // �α��� ��ư
	JButton join_btn; // ȸ������ ��ư

	JButton[][] dial_btn; // ���̾� ��ȣ ��ư �迭

	JTextField mobile_tf; // �ڵ�����ȣ �Է�â
	JTextField pw_tf; // �н����� �Է�â

	String mobile = "010";
	String pw = "";

	Font font;

	Image img = new ImageIcon("./src/Image/main.jpg").getImage();

	public Login_Panel() {
		setLayout(null); // ��ġ������ ���� x

		buttonSet();
		textFieldSet();
		JLabel bg_LB = new JLabel();
		bg_LB.setBackground(ColorInfo.instance.label_color);
		bg_LB.setOpaque(true);
		bg_LB.setBounds(90, 240, 360, 300);
		add(bg_LB);

		dialButtonSet();
	}

	void buttonSet() {
		font = new Font("�����ٸ����", Font.BOLD, 25);

		login_btn = new JButton("�α���");
		login_btn.setBackground(Color.WHITE);
		login_btn.setForeground(ColorInfo.instance.button_color);
		login_btn.setFont(font);
		login_btn.setBounds(135, 400, 270, 50);
		login_btn.addActionListener(this);
		add(login_btn);

		font = new Font("�����ٸ����", Font.BOLD, 16);

		join_btn = new JButton("ȸ������");
		join_btn.setBackground(ColorInfo.instance.button_color);
		join_btn.setForeground(Color.WHITE);
		join_btn.setFont(font);
		join_btn.setBounds(135, 480, 120, 40);
		join_btn.addActionListener(this);
		add(join_btn);

		exit_btn = new JButton("����ϱ�");
		exit_btn.setBackground(ColorInfo.instance.button_color);
		exit_btn.setForeground(Color.WHITE);
		exit_btn.setFont(font);
		exit_btn.setBounds(285, 480, 120, 40);
		exit_btn.addActionListener(this);
		add(exit_btn);
	}

	void dialButtonSet() {
		font = new Font("�����ٸ����", Font.PLAIN, 18);
		dial_btn = new JButton[4][3];
		int num = 1;
		for (int i = 0; i < dial_btn.length; i++) {
			for (int n = 0; n < dial_btn[i].length; n++) {
				dial_btn[i][n] = new JButton();
				dial_btn[i][n].setBackground(ColorInfo.instance.dial_button_color);
				dial_btn[i][n].setForeground(Color.WHITE);
				dial_btn[i][n].setFont(font);
				if (num < 10) {
					dial_btn[i][n].setText(num + "");
				} else if (num == 10) {
					dial_btn[i][n].setText("");
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
					if (!(dial_btn[i][n].getText().equals("DEL"))) {
						if (mobile_tf.getText().length() < 11) {
							mobile += dial_btn[i][n].getText();
							mobile_tf.setText(mobile);
							repaint();
						} else if (mobile_tf.getText().length() > 10) {
							pw += dial_btn[i][n].getText();
							pw_tf.setText(pw);
							repaint();
						}
					} else {
						if (mobile_tf.getText().length() < 11) {
							if (mobile.length() > 0) {
								mobile = mobile.substring(0, mobile.length() - 1);
								mobile_tf.setText(mobile);
							} else {
								return;
							}
							repaint();
						} else if (mobile_tf.getText().length() > 10) {
							if (pw.length() > 0) {
								pw = pw.substring(0, pw.length() - 1);
								pw_tf.setText(pw);
							} else {
								mobile = mobile.substring(0, mobile.length() - 1);
								mobile_tf.setText(mobile);
							}
							repaint();
						}
					}
				}
			}
		}
	}

	void textFieldSet() {
		font = new Font("�����ٸ����", Font.PLAIN, 16);

		mobile_tf = new JTextField(15);
		mobile_tf.setText("010");
		mobile_tf.setBounds(135, 270, 270, 40);
		mobile_tf.setBackground(ColorInfo.instance.textField_color);
		mobile_tf.setForeground(Color.WHITE);
		mobile_tf.setFont(font);
		mobile_tf.setHorizontalAlignment(JTextField.CENTER);
		mobile_tf.addActionListener(this);
		add(mobile_tf);

		pw_tf = new JTextField(5);
		pw_tf.setText("��й�ȣ �Է�");
		pw_tf.setBounds(135, 330, 270, 40);
		pw_tf.setBackground(ColorInfo.instance.textField_color);
		pw_tf.setForeground(Color.WHITE);
		pw_tf.setFont(font);
		pw_tf.setHorizontalAlignment(JTextField.CENTER);
		pw_tf.addActionListener(this);
		add(pw_tf);
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

		// �α��� ��ư�� ������ ��
		if (e.getSource() == login_btn) {
			String log = FileManager.instance.login(mobile_tf.getText(), pw_tf.getText());
			if (log.equals("")) {
				JOptionPane.showMessageDialog(null, "�������� �ʴ� ��ȣ�Դϴ�.", "", JOptionPane.WARNING_MESSAGE);
			} else {
				MainSystem.frame.setContentPane(new Seat_Panel(log, mobile_tf.getText()));
				MainSystem.frame.revalidate();
			}
			// ȸ������ ��ư�� ������ ��
		} else if (e.getSource() == join_btn) {
			MainSystem.frame.setContentPane(new Join_Panel());
			MainSystem.frame.revalidate();
			// ����ϱ� ��ư�� ������ ��
		} else if (e.getSource() == exit_btn) {
			String log = FileManager.instance.login(mobile_tf.getText(), pw_tf.getText());
			if (log.equals("")) {
				JOptionPane.showMessageDialog(null, "�������� �ʴ� ��ȣ�Դϴ�.", "", JOptionPane.WARNING_MESSAGE);
			} else {
				if (FileManager.instance.exit(log)) {
					JOptionPane.showMessageDialog(null, "����� �Ϸ�Ǿ����ϴ�.", "", JOptionPane.WARNING_MESSAGE);
					MainSystem.frame.setContentPane(new Login_Panel());
					MainSystem.frame.revalidate();
				}
			}

		}

	}

}