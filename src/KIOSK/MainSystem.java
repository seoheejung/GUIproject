package KIOSK;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class MainSystem {

	public static JFrame frame = new JFrame();

	public static void main(String[] args) {
		
		// ---���� ��ġ�� ����ȭ���� �߾����� �ٲٴ� �ڵ�---
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int WIDTH = 555;
		int HEIGHT = 1000;

		frame.setTitle("������ ���͵�ī��");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);

		frame.setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height - 1050);

		// �޸��� ������ �����ϸ�, ���� �ҷ�����
		FileManager.instance.loadData();
		FileManager.instance.loadSeatData();

		// frame.setContentPane(new Login_Panel());
		// frame.setContentPane(new Join_Panel());
		// frame.setContentPane(new Seat_Panel("������", "01099374928"));
		 // frame.setContentPane(new Select_Panel("������", "01099374928", 25));
		frame.setContentPane(new Payment_panel("������", "01099374928", 25, "10�ð�", 11000));
		frame.revalidate();

	}
}
