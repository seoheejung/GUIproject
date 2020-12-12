package KIOSK;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class UserBoardSet extends JPanel implements ActionListener {
	Font font;
	Admin_UserInfo_Panel user_list_board = null;

	JLabel num_LB;
	int num = 0;
	JLabel name_LB;
	JLabel mobile_LB;
	JLabel seatNum_LB;
	JLabel maxTime_LB;

	JButton checkOut_btn;

	public UserBoardSet(int index, UserInfo user, Admin_UserInfo_Panel panel) {

		user_list_board = panel;

		setLayout(null);

		int pos = index % panel.page_user_max;
		setBounds(100, 210 + pos * 100, 350, 80);

		num = index;

		font = new Font("나눔스퀘어", Font.PLAIN, 14);

		num_LB = new JLabel();
		num_LB.setBounds(10, 10, 40, 30);
		num_LB.setFont(font);
		num_LB.setText((num + 1) + ""); // 번호
		add(num_LB);

		name_LB = new JLabel();
		name_LB.setBounds(30, 10, 80, 30);
		name_LB.setBackground(new Color(255, 255, 153));
		name_LB.setFont(font);
		name_LB.setText(user.getName());
		name_LB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(name_LB);

		mobile_LB = new JLabel();
		mobile_LB.setBounds(30, 40, 120, 30);
		mobile_LB.setBackground(new Color(255, 255, 153));
		mobile_LB.setFont(font);
		mobile_LB.setText(user.getMobile());
		mobile_LB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(mobile_LB);
		
		seatNum_LB = new JLabel();
		seatNum_LB.setBounds(130, 10, 80, 30);
		seatNum_LB.setBackground(new Color(255, 255, 153));
		seatNum_LB.setFont(font);
		if(user.getSeatNum() != 0) {
			seatNum_LB.setText("자리 : " + user.getSeatNum() + "번");
		}else {
			seatNum_LB.setText("자리 : X");
		}
		seatNum_LB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(seatNum_LB);
		
		maxTime_LB = new JLabel();
		maxTime_LB.setBounds(130, 40, 120, 30);
		maxTime_LB.setBackground(new Color(255, 255, 153));
		maxTime_LB.setFont(font);
		if(user.getMaxTime() != 0) {
			maxTime_LB.setText("남은시간 : " + user.getMaxTime() + "시간");
		}else {
			maxTime_LB.setText("남은시간 : X");
		}
		maxTime_LB.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(maxTime_LB);

		checkOut_btn = new JButton();
		checkOut_btn.setBounds(260, 26, 80, 30);
		checkOut_btn.setBackground(Color.LIGHT_GRAY);
		checkOut_btn.setFont(font);
		checkOut_btn.setText("강제퇴실");
		checkOut_btn.addActionListener(this);
		checkOut_btn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		add(checkOut_btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}

public class Admin_UserInfo_Panel extends JPanel implements ActionListener {
	Font font;
	Image img = new ImageIcon("./src/Image/bg.jpg").getImage();

	public ArrayList<UserInfo> user_list = FileManager.instance.userManager;
	public ArrayList<UserBoardSet> user_board_list = new ArrayList<>();

	private int page_num = 1;// 현재 페이지 번호
	public int page_user_max = 5; // 한 페이지에 보여줄 회원 수
	private int page_btn_max = 5; // 한 페이지에 보여줄 페이징 번호의 개수
	int page_min = 0; // 현재 페이지의 시작 페이징 번호
	int page_max = 0; // 현재 페이지의 마지막 페이징 번호

	JButton page_btns[] = new JButton[page_btn_max]; // 페이지 버튼
	JButton left_btn;
	JButton right_btn;
	JLabel pageNumText;

	public Admin_UserInfo_Panel() {
		setLayout(null);

		font = new Font("나눔스퀘어", Font.PLAIN, 25);

		JLabel admin_userInfo_LB = new JLabel("사용자정보");
		admin_userInfo_LB.setFont(font);
		admin_userInfo_LB.setForeground(Color.BLACK);
		admin_userInfo_LB.setBackground(ColorInfo.instance.button_sub_color);
		admin_userInfo_LB.setOpaque(true);
		admin_userInfo_LB.setHorizontalAlignment(JTextField.CENTER);
		admin_userInfo_LB.setBounds(170, 120, 200, 50);
		add(admin_userInfo_LB);

		buttonSet();
		userListSet();
	}

	void buttonSet() {
		// 컨포넌트에 버튼이 있으면 삭제
		Component[] componentList = this.getComponents();
		for (Component c : componentList) {
			if (c instanceof JButton) {
				this.remove(c);
			}
		}

		font = new Font("나눔스퀘어", Font.PLAIN, 16);

		page_min = page_num;
		page_max = page_btn_max;

		// 하단에 페이징 번호 버튼 생성
		for (int i = 0; i < page_btn_max; i++) {
			page_btns[i] = new JButton();
			page_btns[i].setBounds(150 + i * 50, 730, 50, 50);
			page_btns[i].setBackground(Color.WHITE);
			page_btns[i].setFont(font);
			page_btns[i].setText((page_num + i) + "");
			page_btns[i].addActionListener(this);
			add(page_btns[i]);
		}

		// < 버튼
		left_btn = new JButton();
		left_btn.setBounds(100, 730, 50, 50);
		left_btn.setBackground(Color.WHITE);
		left_btn.setFont(font);
		left_btn.setText("◁");
		add(left_btn);
		left_btn.addActionListener(this);

		// > 버튼
		right_btn = new JButton();
		right_btn.setBounds(400, 730, 50, 50);
		right_btn.setBackground(Color.WHITE);
		right_btn.setFont(font);
		right_btn.setText("▷");
		add(right_btn);
		right_btn.addActionListener(this);
	}

	public void userListSet() {
		Component[] componentList = this.getComponents();

		for (Component c : componentList) {
			if (c instanceof Admin_UserInfo_Panel) {
				this.remove(c);
			}
		}

		// pageNumText.setText("page : " + page_num);

		int start_index = (page_num - 1) * page_user_max;
		int total = user_list.size() - start_index;
		int count = page_user_max;

		if (total < page_user_max) {
			count = total;
		}

		user_board_list = new ArrayList<>();
		for (int i = 0; i < count; i++) {

			UserBoardSet userBoard = new UserBoardSet(i + start_index, user_list.get(start_index + i), this);
			add(userBoard);
			user_board_list.add(userBoard);

		}
		repaint();
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
