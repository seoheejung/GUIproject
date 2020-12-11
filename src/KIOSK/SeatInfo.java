package KIOSK;

public class SeatInfo {
	private int seatNum = 0;
	private boolean seatUse = false; // 자리를 사용하는지 여부

	public int getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}

	public boolean isSeatUse() {
		return seatUse;
	}

	public void setSeatUse(boolean seatUse) {
		this.seatUse = seatUse;
	}

}
