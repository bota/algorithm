package programming.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 年月日をYYYYMMDDの8桁の整数で表したとき、これを2進数に変換して逆から並べ、
 * さらに10進数に戻したとき、元の日付と同じ日付になるものを探す。
 * 
 * @author bota
 */
public class ConvertNumber {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		// MONTHの値は設定すべき値より1か月小さい値を設定する
		c.set(1964, 9, 10);
		c2.set(2020, 6, 24);
		while (c.before(c2)) {
			// CalendarクラスのMONTHは0から11までの値なので、
			// 1から12までの値を設定するために、1を加算する
			String date = String.format("%2d%02d%02d",
					c.get(Calendar.YEAR),
					c.get(Calendar.MONTH)+1,
					c.get(Calendar.DAY_OF_MONTH));
			output(date);
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
	}
	/**
	 * 変換前の数字と同じならばその数字を出力する
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
	 * 2進数を10進数に変換する
	 * @param binary
	 * @return decimal
	 */
	public static String convertBinaryToDecimal(String binary) {
		List<Integer> list = new ArrayList<Integer>();
		// 2進数を1桁目から(右側から)リストに詰める
		for (int i = binary.length(); i > 0; i--) {
			list.add(Integer.parseInt(binary.substring(i-1, i)));
		}
		int decimal = 0;
		// 1桁目から順に2の乗数をかけ算し、加算していく
		// ex. 1101(2進数) -> 1*2*(2の0乗) + 0*2*(2の1乗) + 1*2*(2の2乗) + 1*2*(2の3乗)
		for (int j = 0; j < list.size(); j++) {
			int num = 0;
			// かけ算用の2の乗数を用意する
			for (int k = 0; k < j + 1; k++) {
				num = (num == 0) ? 1 : 2*num;
			}
			decimal += list.get(j)*num;
		}
		return String.valueOf(decimal);
	}
	/**
	 * 10進数を2進数に変換する
	 * @param decimal
	 * @return binary
	 */
	public static String convertDecimalToBinaray(String decimal) {
		int num = Integer.parseInt(decimal);
		List<Integer> list = new ArrayList<Integer>();
		// 10進数の数字を2で割り切れなくなるまで割っていき、
		// 各余りをリストに詰める
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
