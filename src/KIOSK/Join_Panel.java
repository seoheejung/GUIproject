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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Join_Panel extends JPanel implements ActionListener, ItemListener {

	JTextField name_tf; // �̸� �Է�â
	JTextField mobile_tf; // ��ȭ��ȣ �Է�â
	JTextField pw_tf; // ��й�ȣ �Է�â
	JTextField confirm_pw_tf; // ��й�ȣ Ȯ�� �Է�â

	String name = "";
	String mobile = "";
	String pw = "";
	String confirm_pw = "";

	int position = -1;

	JCheckBox check = new JCheckBox("����������޹�ħ����"); // ����������޹�ħ���� üũ�ڽ�
	boolean isChecked = false;

	Font font;

	Image img = new ImageIcon("./src/Image/bg.jpg").getImage();

	JButton join_btn; // ȸ������ ��ư
	JButton back_btn; // �ڷΰ��� ��ư
	KeyBoard_btn_Setting keyBoard;

	public Join_Panel() {
		setLayout(null);

		// ȸ������ ���̺�
		font = new Font("�����ٸ����", Font.BOLD, 45);
		JLabel join_LB = new JLabel("ȸ�� ����");
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
			if (name.length() > 0 && temp.equals("��")) {
				name = name.substring(0, name.length() - 1);
			} else {
				name += temp;
			}
			name_tf.setText(name);
		} else if (position == 2) {
			if (mobile.length() > 0 && temp.equals("��")) {
				mobile = mobile.substring(0, mobile.length() - 1);
			} else {
				mobile += temp;
			}
			mobile_tf.setText(mobile);
		} else if (position == 3) {
			if (pw.length() > 0 && temp.equals("��")) {
				pw = pw.substring(0, pw.length() - 1);
			} else {
				pw += temp;
			}
			pw_tf.setText(pw);
		} else if (position == 4) {
			if (confirm_pw.length() > 0 && temp.equals("��")) {
				confirm_pw = confirm_pw.substring(0, confirm_pw.length() - 1);
			} else {
				confirm_pw += temp;
			}
			confirm_pw_tf.setText(confirm_pw);
		}

		repaint();
	}

	void buttonSet() {
		font = new Font("�����ٸ����", Font.BOLD, 16);

		join_btn = new JButton("ȸ������");
		join_btn.setBackground(ColorInfo.instance.button_color);
		join_btn.setForeground(Color.WHITE);
		join_btn.setFont(font);
		join_btn.setBounds(285, 440, 120, 40);
		join_btn.addActionListener(this);
		add(join_btn);

		back_btn = new JButton("���");
		back_btn.setBackground(ColorInfo.instance.button_color);
		back_btn.setForeground(Color.WHITE);
		back_btn.setFont(font);
		back_btn.setBounds(135, 440, 120, 40);
		back_btn.addActionListener(this);
		add(back_btn);
	}

	void textFieldSet() {
		font = new Font("�����ٸ����", Font.PLAIN, 16);

		name_tf = new JTextField(15);
		name_tf.setText("�̸�");
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
		mobile_tf.setText("�޴�����ȣ");
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
		pw_tf.setText("��й�ȣ");
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
		confirm_pw_tf.setText("��й�ȣȮ��");
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

		// ��� ��ư ������ �� �α���â���� �̵�
		if (e.getSource() == back_btn) {
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();

		} else if (e.getSource() == join_btn) {
			// ȸ������ ��ư ������ ��
			if (name_tf.getText().equals("") || mobile_tf.getText().equals("") || pw_tf.getText().equals("")
					|| confirm_pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��� �ʵ带 �Է��ϼ���.", "", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!(pw_tf.getText().equals(confirm_pw_tf.getText()))) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� �ʽ��ϴ�.", "", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!isChecked) {
				JOptionPane.showMessageDialog(null, "����������޹�ħ���Ǹ� üũ���ּ���.", "", JOptionPane.WARNING_MESSAGE);
				return;
			}

			for (int i = 0; i < FileManager.instance.userManager.size(); i++) {
				if (mobile_tf.getText().equals(FileManager.instance.userManager.get(i).getMobile())) {
					JOptionPane.showMessageDialog(null, "������ ��ȣ�� �ֽ��ϴ�.", "Message", JOptionPane.WARNING_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.", "Message", JOptionPane.PLAIN_MESSAGE);
			MainSystem.frame.setContentPane(new Login_Panel());
			MainSystem.frame.revalidate();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// �������� ���� ��ȭ�� ������ ����� ��ȯ
		if (e.getStateChange() == ItemEvent.SELECTED) {
			// ����ڰ� �������� �����ϸ� ItemEvent.SELECTED ���� 1 ��ȯ
			isChecked = true;
		} else if (e.getStateChange() == ItemEvent.DESELECTED) {
			// �ٽ� �������� ������ ItemEvent.DESELECTED ���� 2 ��ȯ
			isChecked = false;
		}

	}
}