package KIOSK;

import java.awt.Color;

public class ColorInfo {
	public static ColorInfo instance = new ColorInfo();

	Color bg_color = new Color(230, 250, 255); // 배경
	Color textField_bg_color = new Color(245, 255, 255); // 텍스트필드 배경
	Color textField_font_color = new Color(180, 190, 190); // 텍스트필드 폰트

	Color button_color = new Color(0, 160, 255); // 버튼
	Color button_sub_color = new Color(220, 230, 240); // 서브버튼
	Color title_font_color = new Color(20, 100, 160); // 타이틀 폰트
	Color dial_button_color = new Color(255, 252, 248);// 다이얼버튼
	Color disabled_button_color = new Color(130, 140, 150); // 비활성 버튼

}
