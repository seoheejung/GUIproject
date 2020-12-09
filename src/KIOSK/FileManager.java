package KIOSK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileManager {

	File file = null; // ���� ���� üũ����
	FileWriter fout = null; // ����
	FileReader reader = null; // �б�
	BufferedReader br = null; // ���پ� �б�

	final String USER_PATH = "userdata.txt"; // ȸ������ ���� ���
	final String SEAT_PATH = "seatdata.txt"; // ȸ������ ���� ���

	String userData;
	String seatData;
	boolean isLoad = false; // ���� �ҷ����� �������� üũ

	static int LOG = -1;

	ArrayList<UserInfo> userManager = null;
	ArrayList<SeatInfo> seatManager = null;

	// �̱��� ����
	public static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	private FileManager() {
		userManager = new ArrayList<>();
		seatManager = new ArrayList<>();
	}

	// ȸ�� �߰�
	public void addUser(UserInfo user) {
		userManager.add(user);
		addData(user);
		saveUserData();
	}

	void updateUser(int index, UserInfo user) {
		userManager.set(index, user);
		addData();
		saveUserData();
	}

	// �α���
	String login(String mobile, String pw) {
		String check_id = "";
		for (int i = 0; i < userManager.size(); i++) {
			if (mobile.equals(userManager.get(i).getMobile()) && pw.equals(userManager.get(i).getPw())) {
				check_id = userManager.get(i).getName();
				LOG = i;
				break;
			}
		}
		return check_id;
	}

	// ����ϱ�
	boolean checkOut(String mobile) {
		for (int i = 0; i < userManager.size(); i++) {
			if (mobile.equals(userManager.get(i).getMobile())) {
				if (!userManager.get(i).seatUse) {
					JOptionPane.showMessageDialog(null, "�̿����� �¼��� �����ϴ�.", "Message", JOptionPane.WARNING_MESSAGE);
					break;
				}
				for (int j = 0; j < seatManager.size(); j++) {
					if (seatManager.get(j).getSeatNum() == userManager.get(i).getSeatNum()) {
						seatManager.get(j).setSeatUse(false);
						saveSeatData();
					}
				}
				userManager.get(i).setSeatNum(0);
				userManager.get(i).seatUse = false;
				if (userManager.get(i).getMaxTime() > 0) {
					long time = (System.currentTimeMillis() / (1000 * 60 * 60)) - userManager.get(i).getPreTime();
					System.out.println(System.currentTimeMillis() / (1000 * 60 * 60));
					System.out.println(userManager.get(i).getPreTime());
					time = userManager.get(i).getMaxTime() - time;
					userManager.get(i).setMaxTime(time);
					userManager.get(i).setPreTime(0);
				}
				updateUser(i, userManager.get(i));
				return true;
			}
		}
		return false;
	}

	// �Խ��ϱ�
	void checkIn(String mobile, String useTime, int seatNum) {
		for (int i = 0; i < userManager.size(); i++) {
			if (mobile.equals(userManager.get(i).getMobile())) {
				userManager.get(i).setSeatNum(seatNum);
				userManager.get(i).setMaxTime(Long.parseLong(useTime));
				System.out.println(System.currentTimeMillis() / (1000 * 60 * 60));
				userManager.get(i).setPreTime(System.currentTimeMillis() / (1000 * 60 * 60));
				userManager.get(i).seatUse = true;
				updateUser(i, userManager.get(i));
			} else {
				JOptionPane.showMessageDialog(null, "���������� �����Դϴ�.", "Message", JOptionPane.WARNING_MESSAGE);
				MainSystem.frame.setContentPane(new Login_Panel());
				MainSystem.frame.revalidate();
			}
		}

		for (int i = 0; i < seatManager.size(); i++) {
			if (seatNum == seatManager.get(i).getSeatNum()) {
				seatManager.get(i).setSeatUse(true);
				saveSeatData();
			}
		}
	}

	private void addData() {
		userData = "";
		for (UserInfo user : userManager) {
			userData += user.getName() + "/";
			userData += user.getMobile() + "/";
			userData += user.getPw() + "/";
			userData += user.getSeatNum() + "/";
			userData += user.getMaxTime() + "/";
			userData += user.getPreTime() + "/";
			userData += user.seatUse + "\n";
		}
		System.out.println(" == save == \n" + userData);
	}

	private void addData(UserInfo user) {
		// �߰��� ȸ�� ���� ����
		int lastIndex = userManager.size() - 1;
		UserInfo temp = userManager.get(lastIndex);
		userData += temp.getName() + "/";
		userData += temp.getMobile() + "/";
		userData += temp.getPw() + "/";
		userData += temp.getSeatNum() + "/";
		userData += temp.getMaxTime() + "/";
		userData += temp.getPreTime() + "/";
		userData += temp.seatUse + "\n";
		System.out.println(" == save == \n" + userData);
	}

	private void saveUserData() {
		try {
			fout = new FileWriter(USER_PATH);
			fout.write(userData);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fout = null;
	}

	public void loadUser(String userText) {
		String userinfo[] = userText.split("/");
		UserInfo temp = new UserInfo();
		temp.setName(userinfo[0]);
		temp.setMobile(userinfo[1]);
		temp.setPw(userinfo[2]);
		temp.setSeatNum(Integer.parseInt(userinfo[3]));
		temp.setMaxTime(Integer.parseInt(userinfo[4]));
		temp.setPreTime(Integer.parseInt(userinfo[5]));
		temp.seatUse = Boolean.valueOf(userinfo[6]);
		userManager.add(temp);
	}

	// ���� �ҷ����� (����� ����)
	void loadData() {
		file = new File(USER_PATH);
		isLoad = false;
		userData = "";
		if (!file.exists())
			return;
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			while (true) {
				String userText = br.readLine();
				if (userText == null)
					break;
				userData += userText + "\n";
				loadUser(userText);
				isLoad = true;
			}
			reader.close();
			br.close();
			if (isLoad) {
				System.out.println("== load == \n" + userData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		file = null;
		reader = null;
		br = null;
	}

	// ���� �����ϱ� (�¼�)
	public void saveSeatData() {
		String info = "";
		for (int i = 0; i < seatManager.size(); i++) {
			info += seatManager.get(i).getSeatNum();
			info += "/";
			info += seatManager.get(i).isSeatUse();
			if (i != seatManager.size() - 1) {
				info += "\n";
			}
		}
		try {
			fout = new FileWriter(SEAT_PATH);
			fout.write(info);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fout = null;
	}

	public void loadSeat(String seatText) {
		String seatinfo[] = seatText.split("/");
		SeatInfo temp = new SeatInfo();
		temp.setSeatNum(Integer.parseInt(seatinfo[0]));
		temp.setSeatUse(Boolean.valueOf(seatinfo[1]));
		seatManager.add(temp);
	}

	// ���� �ҷ����� (�¼�)
	void loadSeatData() {
		file = new File(SEAT_PATH);
		isLoad = false;
		seatData = "";
		if (!file.exists()) {
			for (int i = 0; i < 28; i++) {
				SeatInfo temp = new SeatInfo();
				temp.setSeatNum(i + 1);
				temp.setSeatUse(false);
				seatManager.add(temp);
			}
			saveSeatData();
			return;
		}
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			while (true) {
				String seatText = br.readLine();
				if (seatText == null)
					break;
				seatData += seatText + "\n";
				loadSeat(seatText);
				isLoad = true;
			}
			reader.close();
			br.close();
			if (isLoad) {
				System.out.println("== load == \n" + seatData);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		file = null;
		reader = null;
		br = null;
	}

}
