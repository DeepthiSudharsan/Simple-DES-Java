package MIS;

public class simpledes {

	static String k1 = "", k2 = "";

	public static String P10(String inx) {

		int index[] = { 2, 4, 1, 6, 3, 9, 0, 8, 7, 5 };
		String x = "";
		for (int i = 0; i <= 9; i++) {
			x = x + inx.charAt(index[i]);

		}
		return x;
	}

	public static String P8(String inx) {

		int index[] = { 5, 2, 6, 3, 7, 4, 9, 8 };
		String x = "";
		for (int i = 0; i <= 7; i++) {
			x = x + inx.charAt(index[i]);
		}
		return x;
	}

	public static String SL1(String inx) {
		return inx.substring(1) + inx.charAt(0);
	}

	public static String SL2(String inx) {
		return inx.substring(2) + inx.substring(0, 2);
	}

	public static String[] keygen(String key) {
		String k[] = new String[2];

		String x = P10(key);
		String L = x.substring(0, 5);
		String R = x.substring(5);
		L = SL1(L);
		R = SL1(R);
		k[0] = P8(L + R);
		L = SL2(L);
		R = SL2(R);
		k[1] = P8(L + R);
		return k;

	}

	public static String IP(String inx) {
		int index[] = { 1, 5, 2, 0, 3, 7, 4, 6 };
		String x = "";
		for (int i = 0; i <= 7; i++) {
			x = x + inx.charAt(index[i]);
		}
		return x;
	}

	public static String EP(String inx) {
		int index[] = { 3, 0, 1, 2, 1, 2, 3, 0 };
		String x = "";
		for (int i = 0; i <= 7; i++) {
			x = x + inx.charAt(index[i]);
		}
		return x;
	}

	public static String switch1(String m) {

		return m.substring(4, 8) + m.substring(0, 4);
	}

	public static String EXOR(String str1, String str2) {
		String ans = "";
		int n = str1.length();

		// Loop to iterate over the
		// Binary Strings
		for (int i = 0; i < n; i++) {
			// If the Character matches
			if (str1.charAt(i) == str2.charAt(i))
				ans += "0";
			else
				ans += "1";
		}
		return ans;
	}

	public static String s1box(String inx) {
		int A[][] = { { 1, 0, 3, 2 }, { 3, 2, 1, 0 }, { 0, 2, 1, 3 }, { 3, 1, 3, 2 } };

		int row = Integer.parseInt(inx.substring(0, 2), 2);
		int col = Integer.parseInt(inx.substring(2, 4), 2);
		int x = A[row][col];

		return String.format("%02d", Integer.parseInt(Integer.toBinaryString(x)));
	}

	public static String s2box(String inx) {
		int A[][] = { { 0, 1, 2, 3 }, { 2, 0, 1, 3 }, { 3, 0, 1, 0 }, { 2, 1, 0, 3 } };

		int row = Integer.parseInt(inx.substring(0, 2), 2);
		int col = Integer.parseInt(inx.substring(2, 4), 2);
		int x = A[row][col];
		// System.out.println(String.format("%02d", x));
		return String.format("%02d", Integer.parseInt(Integer.toBinaryString(x)));

	}

	public static String P4(String inx) {
		int index[] = { 1, 3, 2, 0 };
		String x = "";
		for (int i = 0; i <= 3; i++) {
			x = x + inx.charAt(index[i]);
		}
		return x;
	}

	public static String IPinv(String inx) {
		int index[] = { 3, 0, 2, 4, 6, 1, 7, 5 };
		String x = "";
		for (int i = 0; i <= 7; i++) {
			x = x + inx.charAt(index[i]);
		}
		return x;
	}

	public static String fk(String x, String key) {
		String L = x.substring(0, 4);
		String R = x.substring(4);
		// System.out.println(R);
		String R1 = EP(R);
		R1 = EXOR(R1, key);
		System.out.println(R1.substring(0, 4));
		String x1 = s1box(R1.substring(0, 4));
		String x2 = s2box(R1.substring(4));
		x = x1 + x2;
		x = P4(x);
		x = EXOR(x, L);
		x = x + R;

		return x;

	}

	public static String encryptm(String message, String key) {
		String x = "", y = "";
		String k1 = keygen(key)[0];
		String k2 = keygen(key)[1];
		x = IP(message);
		y = fk(x, k1);
		y = switch1(y);
		x = fk(y, k2);
		x = IPinv(x);
		return x;

	}

	public static String decryptm(String message, String key)

	{

		String x = "", y = "";
		String k1 = keygen(key)[0];
		String k2 = keygen(key)[1];
		x = IP(message);
		y = fk(x, k2);
		y = switch1(y);
		x = fk(y, k1);
		x = IPinv(x);
		return x;
	}

	public static void main(String[] args) {

		String message = "10001101";

		String key = "1101001101";
		String x = encryptm(message, key);
		String y = decryptm(x, key);
		System.out.println("Original Message");
		System.out.println(message);
		System.out.println("encrypted Message");
		System.out.println(x);
		System.out.println("decrypted Message");
		System.out.println(y);

//		System.out.println(P10("1101001101"));
//		System.out.println(P8("1101001101"));
//		System.out.println(SL1("abcde"));
//		System.out.println(SL2("abcde"));
//		System.out.println(Arrays.toString(keygen(key)));
//		System.out.println(IP(key));
//		System.out.println(EP(key));
//		System.out.println(switch1(key));
//		System.out.println(EXOR("1001", "1010"));
//		System.out.println(s1box("1011000111"));
//		System.out.println(s2box("1011000111"));
		// System.out.println(P4("1101001101"));
		// System.out.println(IPinv("1101001101"));
		// System.out.println(fk("10001101", "1101001101"));

	}
}
