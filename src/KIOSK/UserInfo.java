package KIOSK;

// 회원 클래스
public class UserInfo {
	private String name;
	private String mobile;
	private String pw;
	private int seatNum; // 자리 번호
	private int maxTime; // 사용 가능한 총 시간
	private String preTime; // 사용 시작한 시간
	boolean seatUse = false; // 자리를 사용하는지 여부

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	public String getPreTime() {
		return preTime;
	}

	public void setPreTime(String preTime) {
		this.preTime = preTime;
	}

}