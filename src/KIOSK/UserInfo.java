package KIOSK;

// ȸ�� Ŭ����
public class UserInfo {
	private String name;
	private String mobile;
	private String pw;
	private int seatNum; // �ڸ� ��ȣ
	private long maxTime; // ��� ������ �� �ð�
	private long preTime; // ��� ������ �ð�
	boolean seatUse = false; // �ڸ��� ����ϴ��� ����

	void use() {
		seatUse = true;
		preTime = System.currentTimeMillis();
	}

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

	public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getPreTime() {
		return preTime;
	}

	public void setPreTime(long preTime) {
		this.preTime = preTime;
	}

}