package KIOSK;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainSystem {

	static JFrame frame = new JFrame();

	public static void main(String[] args) {
		int WIDTH = 555;
		int HEIGHT = 960;
		frame.setTitle("������ ���͵�ī��");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);

		// ---���� ��ġ�� ����ȭ���� �߾����� �ٲٴ� �ڵ�---
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(screenSize.width / 2 - WIDTH/2, screenSize.height - 1000);

		// �޸��� ������ �����ϸ�, ���� �ҷ�����
		FileManager.instance.loadData();

		// �α��� â ���
		frame.setContentPane(new Login_Panel());
		frame.revalidate();
		
	}
}
