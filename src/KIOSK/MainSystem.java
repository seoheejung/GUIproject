package KIOSK;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainSystem {

	public static JFrame frame = new JFrame();

	public static void main(String[] args) {

		// ---시작 위치를 바탕화면의 중앙으로 바꾸는 코드---
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int WIDTH = 555;
		int HEIGHT = 995;

		frame.setTitle("쉼표 스터디카페");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);

		frame.setLocation((int) screenSize.getWidth() / 2 - WIDTH / 2, (int) screenSize.getHeight() - 1050);

		// 메모장 파일이 존재하면, 파일 불러오기
		FileManager.instance.loadData();
		FileManager.instance.loadSeatData();

		// frame.setContentPane(new Login_Panel());
		 frame.setContentPane(new Admin_UserInfo_Panel());
		// frame.setContentPane(new Join_Panel());
		// frame.setContentPane(new Seat_Panel("서희정", "01099374928"));
		// frame.setContentPane(new Select_Panel("서희정", "01099374928", 25));
		// frame.setContentPane(new Payment_panel("서희정", "01099374928", 25, "10시간",11000));

		frame.revalidate();

	}
}
