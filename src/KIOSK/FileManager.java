package KIOSK;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileManager {

	File file = null; // 파일 존재 체크여부
	FileWriter fout = null; // 쓰기
	FileReader reader = null; // 읽기
	BufferedReader br = null; // 한줄씩 읽기

	final String USER_PATH = "userdata.txt"; // 회원정보 파일 경로
	final String SEAT_PATH = "seatdata.txt"; // 회원정보 파일 경로

	String data; // 데이터 문자열로 저장
	boolean isLoad = false; // 파일 불러오기 성공여부 체크

	static int LOG = -1;

	ArrayList<UserInfo> userManager = null;
	ArrayList<SeatInfo> seatManager = null;

	// 싱글톤 패턴
	public static FileManager instance = new FileManager();

	public static FileManager getInstance() {
		return instance;
	}

	private FileManager() {
		userManager = new ArrayList<>();
		seatManager = new ArrayList<>();
	}

	// 회원 추가
	public void addUser(UserInfo user) {
		userManager.add(user);
		addData(user);
		saveUserData();
	}
	
	// 로그인
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

		// 퇴실하기
		boolean exit(String mobile) {
			for (int i = 0; i < userManager.size(); i++) {
				if (mobile.equals(userManager.get(i).getMobile())) {
					if (!userManager.get(i).seatUse) {
						JOptionPane.showMessageDialog(null, "이용중인 좌석이 없습니다.", "", JOptionPane.WARNING_MESSAGE);
						break;
					}
					userManager.get(i).setSeatNum(0);
					userManager.get(i).seatUse = false;
					if (userManager.get(i).getMaxTime() > 0) {
						long time = System.currentTimeMillis() - userManager.get(i).getPreTime();
						userManager.get(i).setMaxTime(time / 360000);
					}
					return true;
				}
			}
			return false;
		}

	private void addData(UserInfo user) {
		// 추가된 회원 정보 저장
		int lastIndex = userManager.size() - 1;
		UserInfo temp = userManager.get(lastIndex);
		data += temp.getName() + "/";
		data += temp.getMobile() + "/";
		data += temp.getPw() + "/";
		data += temp.getSeatNum() + "/";
		data += temp.getMaxTime() + "/";
		data += temp.getPreTime() + "\n";
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
		temp.setSeatNum(Integer.parseInt(userinfo[3]));
		temp.setMaxTime(Integer.parseInt(userinfo[4]));
		temp.setPreTime(Integer.parseInt(userinfo[5]));
		userManager.add(temp);
	}

	// 파일 불러오기 (사용자 정보)
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

	// 파일 저장하기 (좌석)
	public void saveSeatData() {
		String info = "";
		for (int i = 0; i < seatManager.size(); i++) {
			info += seatManager.get(i).getSeatNum();
			info += "/";
			info += seatManager.get(i).getPrice();
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
	}

	public void loadSeat(String seatText) {
		String seatinfo[] = seatText.split("/");
		SeatInfo temp = new SeatInfo();
		temp.setSeatNum(Integer.parseInt(seatinfo[0]));
		temp.setPrice(Integer.parseInt(seatinfo[1]));
		temp.setSeatUse(Boolean.valueOf(seatinfo[2]));
		seatManager.add(temp);
	}

	// 파일 불러오기 (좌석)
	void loadSeatData() {
		file = new File(SEAT_PATH);
		isLoad = false;
		data = "";
		if (!file.exists()) {
			for (int i = 0; i < 28; i++) {
				SeatInfo temp = new SeatInfo();
				temp.setSeatNum(i + 1);
				temp.setPrice(8000);
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
				data += seatText + "\n";
				loadSeat(seatText);
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

}
