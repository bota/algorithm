package programming.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * �N������YYYYMMDD��8���̐����ŕ\�����Ƃ��A�����2�i���ɕϊ����ċt������ׁA
 * �����10�i���ɖ߂����Ƃ��A���̓��t�Ɠ������t�ɂȂ���̂�T���B
 * 
 * @author bota
 */
public class ConvertNumber {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		// MONTH�̒l�͐ݒ肷�ׂ��l���1�����������l��ݒ肷��
		c.set(1964, 9, 10);
		c2.set(2020, 6, 24);
		while (c.before(c2)) {
			// Calendar�N���X��MONTH��0����11�܂ł̒l�Ȃ̂ŁA
			// 1����12�܂ł̒l��ݒ肷�邽�߂ɁA1�����Z����
			String date = String.format("%2d%02d%02d",
					c.get(Calendar.YEAR),
					c.get(Calendar.MONTH)+1,
					c.get(Calendar.DAY_OF_MONTH));
			output(date);
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	/**
	 * �ϊ��O�̐����Ɠ����Ȃ�΂��̐������o�͂���
	 * @param num
	 */
	public static void output(String num) {
		String binary = convertDecimalToBinaray(num);
		String decimal = convertBinaryToDecimal(binary);
		if (decimal.equals(num)) {
			System.out.println(num);
		}
	}
	/**
	 * 2�i����10�i���ɕϊ�����
	 * @param binary
	 * @return decimal
	 */
	public static String convertBinaryToDecimal(String binary) {
		List<Integer> list = new ArrayList<Integer>();
		// 2�i����1���ڂ���(�E������)���X�g�ɋl�߂�
		for (int i = binary.length(); i > 0; i--) {
			list.add(Integer.parseInt(binary.substring(i-1, i)));
		}
		int decimal = 0;
		// 1���ڂ��珇��2�̏搔�������Z���A���Z���Ă���
		// ex. 1101(2�i��) -> 1*2*(2��0��) + 0*2*(2��1��) + 1*2*(2��2��) + 1*2*(2��3��)
		for (int j = 0; j < list.size(); j++) {
			int num = 0;
			// �����Z�p��2�̏搔��p�ӂ���
			for (int k = 0; k < j + 1; k++) {
				num = (num == 0) ? 1 : 2*num;
			}
			decimal += list.get(j)*num;
		}
		return String.valueOf(decimal);
	}
	/**
	 * 10�i����2�i���ɕϊ�����
	 * @param decimal
	 * @return binary
	 */
	public static String convertDecimalToBinaray(String decimal) {
		int num = Integer.parseInt(decimal);
		List<Integer> list = new ArrayList<Integer>();
		// 10�i���̐�����2�Ŋ���؂�Ȃ��Ȃ�܂Ŋ����Ă����A
		// �e�]������X�g�ɋl�߂�
		while (num/2 > 0 || num%2 == 1) {
			list.add(num%2);
			num = num / 2;
		}
		StringBuilder binary = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			binary.append(list.get(i));
		}
		return binary.toString();
	}
}
