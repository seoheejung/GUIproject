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

	Font font;

	Image img = new ImageIcon("./src/Image/main.jpg").getImage();

	public Login_Panel() {
		setLayout(null); // ��ġ������ ���� x

		buttonSet();
		textFieldSet();
		JLabel login = new JLabel();
		login.setBackground(new Color(238, 237, 238));
		login.setOpaque(true);
		login.setBounds(90, 240, 360, 300);
		add(login);

		dialButtonSet();
	}

	void buttonSet() {
		font = new Font("Gothic", Font.BOLD, 20);

		login_btn = new JButton("�α���");
		login_btn.setBackground(Color.WHITE);
		login_btn.setForeground(Color.GRAY);
		login_btn.setFont(font);
		login_btn.setOpaque(true);
		login_btn.setBounds(135, 400, 270, 40);
		login_btn.addActionListener(this);
		add(login_btn);

		font = new Font("Gothic", Font.BOLD, 16);

		join_btn = new JButton("ȸ������");
		join_btn.setBackground(new Color(76, 51, 40));
		join_btn.setForeground(Color.WHITE);
		join_btn.setFont(font);
		join_btn.setBounds(135, 490, 120, 30);
		join_btn.addActionListener(this);
		add(join_btn);

		exit_btn = new JButton("����ϱ�");
		exit_btn.setBackground(new Color(76, 51, 40));
		exit_btn.setForeground(Color.WHITE);
		exit_btn.setFont(font);
		exit_btn.setBounds(285, 490, 120, 30);
		exit_btn.addActionListener(this);
		add(exit_btn);
	}

	void dialButtonSet() {
		font = new Font("Gothic", Font.PLAIN, 18);
		dial_btn = new JButton[4][3];
		int num = 1;
		for (int i = 0; i < dial_btn.length; i++) {
			for (int n = 0; n < dial_btn[i].length; n++) {
				dial_btn[i][n] = new JButton();
				dial_btn[i][n].setBackground(Color.WHITE);
				dial_btn[i][n].setForeground(new Color(76, 51, 40));
				dial_btn[i][n].setFont(font);
				if (num < 10) {
					dial_btn[i][n].setText(num + "");
				} else if (num == 10) {
					dial_btn[i][n].setText("*");
				} else if (num == 11) {
					dial_btn[i][n].setText("0");
				} else if (num == 12) {
					dial_btn[i][n].setText("/");
				}
				num++;
				dial_btn[i][n].setBounds(115 + (n * 120), 580 + (i * 80), 70, 70);
				dial_btn[i][n].addActionListener(this);
				add(dial_btn[i][n]);
			}
		}

	}

	void textFieldSet() {
		font = new Font("Gothic", Font.PLAIN, 16);

		mobile_tf = new JTextField(15);
		mobile_tf.setText("�ڵ�����ȣ �Է�");
		mobile_tf.setBounds(135, 270, 270, 40);
		mobile_tf.setBackground(Color.GRAY);
		mobile_tf.setForeground(Color.WHITE);
		mobile_tf.setFont(font);
		mobile_tf.setHorizontalAlignment(JTextField.CENTER);
		mobile_tf.addActionListener(this);
		add(mobile_tf);

		mobile_tf.addMouseListener(new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				mobile_tf.setText("");
				pw_tf.setText("");
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
		pw_tf.setText("��й�ȣ �Է�");
		pw_tf.setBounds(135, 330, 270, 40);
		pw_tf.setBackground(Color.GRAY);
		pw_tf.setForeground(Color.WHITE);
		pw_tf.setFont(font);
		pw_tf.setHorizontalAlignment(JTextField.CENTER);
		pw_tf.addActionListener(this);
		add(pw_tf);

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// �α��� ��ư�� ������ ��
		if (e.getSource() == login_btn) {
			if (mobile_tf.getText().equals("") || pw_tf.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "��� �ʵ带 �Է��ϼ���.", "�� �ʵ� ����", JOptionPane.WARNING_MESSAGE);
			}
			String log = FileManager.instance.login(mobile_tf.getText(), pw_tf.getText());
			System.out.println(log);
			if (log.equals("")) {
				JOptionPane.showMessageDialog(null, "���̵�� ��й�ȣ�� �ٽ� Ȯ���ϼ���", "�α��� ����", JOptionPane.WARNING_MESSAGE);
			} else {
				// MainSystem.frame.setContentPane(new Game_Panel());
				MainSystem.frame.revalidate();
			}
			// ȸ������ ��ư�� ������ ��
		} else if (e.getSource() == join_btn) {
			// MainSystem.frame.setContentPane(new Join_Panel());
			MainSystem.frame.revalidate();
			// ID/PW ã�� ��ư�� ������ ��
		} else if (e.getSource() == exit_btn) {
			// MainSystem.frame.setContentPane(new Find_Panel());
			MainSystem.frame.revalidate();
		}

	}

}