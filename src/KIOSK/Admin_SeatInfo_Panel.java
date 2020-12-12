package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Admin_SeatInfo_Panel extends JPanel implements ActionListener {
	Font font;
	Image img = new ImageIcon("./src/Image/bg.jpg").getImage();
	
	public ArrayList<SeatInfo> seat_list = FileManager.instance.seatManager;

	public Admin_SeatInfo_Panel() {
		setLayout(null);

		font = new Font("나눔스퀘어", Font.PLAIN, 25);

		JLabel admin_seatInfo_LB = new JLabel("좌석이용현황");
		admin_seatInfo_LB.setFont(font);
		admin_seatInfo_LB.setForeground(Color.BLACK);
		admin_seatInfo_LB.setBackground(ColorInfo.instance.button_sub_color);
		admin_seatInfo_LB.setOpaque(true);
		admin_seatInfo_LB.setHorizontalAlignment(JTextField.CENTER);
		admin_seatInfo_LB.setBounds(170, 90, 200, 50);
		add(admin_seatInfo_LB);

	}

	@Override
	protected void paintComponent(Graphics g) {

		g.drawImage(img, 0, 0, 540, 960, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
