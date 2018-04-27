import java.io.Serializable;

public class WhyNotStringBuilder implements Serializable {
//	private static final long serialVersionUID = -3577509395537874381L; // without the field
	private static final long serialVersionUID = -4794117612110281441L; // with the field
	private int x;

	public static void main(String[] args) {
		String s = "a" + "b";
		String x = "b" + s;
		for (int i =0;i<6; i++) {
			x += i;
		}
		System.out.println(x);
	}
}
