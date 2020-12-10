package KIOSK;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class KeyBoard_btn_Setting implements ActionListener {
	
	Font font;
	
	
	JButton[] firstLine_btn = new JButton[10];
	JButton[] secondLine_btn = new JButton[10];
	JButton[] thirdLine_btn = new JButton[9];
	JButton[] fourthLine_btn = new JButton[8];
	
	String [] firstLine_btn_Text = {"1","2","3","4","5","6","7","8","9","0"};
	String [] secondLine_btn_Text = {"¤²","¤¸","¤§","¤¡","¤µ","¤Ë","¤Å","¤Á","¤À","¤Ä"};
	String [] thirdLine_btn_Text = {"¤±","¤¤","¤·","¤©","¤¾","¤Ç","¤Ã","¤¿","¤Ó"};
	String [] fourthLine_btn_Text = {"¤»","¤¼","¤º","¤½","¤Ð","¤Ì","¤Ñ","¢·"};
	
	public KeyBoard_btn_Setting() {
		font = new Font("³ª´®¹Ù¸¥°íµñ", Font.PLAIN, 22);
		for (int i = 0; i < firstLine_btn.length; i++) {
			firstLine_btn[i] = new JButton();
			firstLine_btn[i].setFont(font);
			firstLine_btn[i].setForeground(ColorInfo.instance.button_color);
			firstLine_btn[i].setBackground(Color.WHITE);
			firstLine_btn[i].setOpaque(true);
			firstLine_btn[i].setVisible(true);
			firstLine_btn[i].setText(firstLine_btn_Text[i]);
			firstLine_btn[i].setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
			firstLine_btn[i].setBounds(50 + (i * 45), 580, 40, 40);
		}
		
		for (int i = 0; i < secondLine_btn.length; i++) {
			secondLine_btn[i] = new JButton();
			secondLine_btn[i].setFont(font);
			secondLine_btn[i].setForeground(ColorInfo.instance.button_color);
			secondLine_btn[i].setBackground(Color.WHITE);
			secondLine_btn[i].setOpaque(true);
			secondLine_btn[i].setVisible(true);
			secondLine_btn[i].setText(secondLine_btn_Text[i]);
			secondLine_btn[i].setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
			secondLine_btn[i].setBounds(50 + (i * 45), 650, 40, 40);
		}
		
		for (int i = 0; i < thirdLine_btn.length; i++) {
			thirdLine_btn[i] = new JButton();
			thirdLine_btn[i].setFont(font);
			thirdLine_btn[i].setForeground(ColorInfo.instance.button_color);
			thirdLine_btn[i].setBackground(Color.WHITE);
			thirdLine_btn[i].setOpaque(true);
			thirdLine_btn[i].setVisible(true);
			thirdLine_btn[i].setText(thirdLine_btn_Text[i]);
			thirdLine_btn[i].setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
			thirdLine_btn[i].setBounds(70 + (i * 45), 720, 40, 40);
		}
		
		for (int i = 0; i < fourthLine_btn.length; i++) {
			fourthLine_btn[i] = new JButton();
			fourthLine_btn[i].setFont(font);
			fourthLine_btn[i].setForeground(ColorInfo.instance.button_color);
			fourthLine_btn[i].setBackground(Color.WHITE);
			fourthLine_btn[i].setOpaque(true);
			fourthLine_btn[i].setVisible(true);
			fourthLine_btn[i].setText(fourthLine_btn_Text[i]);
			fourthLine_btn[i].setBorder(BorderFactory.createEmptyBorder(0 , 0 , 0 , 0));
			fourthLine_btn[i].setBounds(90 + (i * 45), 790, 40, 40);
		}
		
	}
	
	String keyBoardButtonPress(ActionEvent e) {
		for (int i = 0; i < firstLine_btn.length; i++) {
			if(e.getSource() == firstLine_btn[i]) {
				return firstLine_btn[i].getText();
			}
		}
		
		for (int i = 0; i < secondLine_btn.length; i++) {
			if(e.getSource() == secondLine_btn[i]) {
				return secondLine_btn[i].getText();
			}
		}
		
		for (int i = 0; i < thirdLine_btn.length; i++) {
			if(e.getSource() == thirdLine_btn[i]) {
				return thirdLine_btn[i].getText();
			}
		}
		
		for (int i = 0; i < fourthLine_btn.length; i++) {
			if(e.getSource() == fourthLine_btn[i]) {
				return fourthLine_btn[i].getText();
			}
		}
		return "";
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
