package KIOSK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

	File file = null; // ���� ���� üũ����
	FileWriter fout = null; // ����
	FileReader reader = null; // �б�
	BufferedReader br = null; // ���پ� �б�

	final String USER_PATH = "userdata.txt"; // ȸ������ ���� ���

	String data; // ������ ���ڿ��� ����
	boolean isLoad = false; // ���� �ҷ����� �������� üũ

	static int LOG = -1;

	ArrayList<UserInfo> userManager = null;

	// �̱��� ����
	public static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	private FileManager() {
		userManager = new ArrayList<>();
	}

	// ȸ�� �߰�
	public void addUser(UserInfo user) {
		userManager.add(user);
		addData(user);
		saveUserData();
	}

	private void addData(UserInfo user) {
		// �߰��� ȸ�� ���� ����
		int lastIndex = userManager.size() - 1;
		UserInfo temp = userManager.get(lastIndex);
		data += temp.getName() + "/";
		data += temp.getMobile() + "/";
		data += temp.getPw() + "\n";
		System.out.println(" == save == \n" + data);
	}

	private void saveUserData() {
		try {
			fout = new FileWriter(USER_PATH);
			fout.write(data);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadUser(String userText) {
		String userinfo[] = userText.split("/");
		UserInfo temp = new UserInfo();
		temp.setName(userinfo[0]);
		temp.setMobile(userinfo[1]);
		temp.setPw(userinfo[2]);
		userManager.add(temp);
	}
	
	// ���� �ҷ����� (����� ����)
	void loadData() {
		file = new File(USER_PATH);
		isLoad = false;
		data = "";
		if (!file.exists())
			return;
		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			while (true) {
				String userText = br.readLine();
				if (userText == null)
					break;
				data += userText + "\n";
				loadUser(userText);
				isLoad = true;
			}
			reader.close();
			br.close();
			if (isLoad) {
				System.out.println("== load == \n" + data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �α���
	String login(String id, String pw) {
		String check_id = "";
		for (int i = 0; i < userManager.size(); i++) {
			if (id.equals(userManager.get(i).getMobile()) && pw.equals(userManager.get(i).getPw())) {
				check_id = userManager.get(i).getMobile();
				LOG = i;
				break;
			}
		}
		return check_id;
	}

}
