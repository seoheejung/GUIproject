package KIOSK;

public class SeatInfo {
	private int seatNum = 0;
	private int price;
	private boolean seatUse = false; // �ڸ��� ����ϴ��� ����
	
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isSeatUse() {
		return seatUse;
	}
	public void setSeatUse(boolean seatUse) {
		this.seatUse = seatUse;
	}
	

}
