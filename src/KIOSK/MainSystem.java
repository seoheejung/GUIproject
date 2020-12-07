package KIOSK;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainSystem {

	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		int WIDTH = 555;
		int HEIGHT = 960;
		frame.setTitle("피터팬 스터디카페");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);

		// ---시작 위치를 바탕화면의 중앙으로 바꾸는 코드---
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - WIDTH/2, screenSize.height - 1000);

		// 메모장 파일이 존재하면, 파일 불러오기
		FileManager.instance.loadData();

		// 로그인 창 출력
		frame.setContentPane(new Login_Panel());
		frame.revalidate();
		
	}
}
