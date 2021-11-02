package Goi1;

public class ClassTestData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte b1 = 2;
		byte b2 = 4;
		short s1 = 12;
		short s2 = 8;
		int i1 = 10;
		int i2 = 20;
		long l1 = 100;
		long l2 = 49;
		float f1 = 12e2f;
		float f2 = 22e4f;
		double d1 = -49e2d;
		double d2 = 14d;
		boolean bo1 = true;
		boolean bo2 = false;
		char c1 = 'D';
		char c2 = 'E';
		System.out.println("Test nhap");
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(i1);
		System.out.println(i2);
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(bo1);
		System.out.println(bo2);
		System.out.println(c1);
		System.out.println(c2);
		System.out.println("Test phep toan");
		System.out.println(b1 + " + " + b2 + " = " + (b1+b2));
		System.out.println(i1 + " - " + i2 + " = " + (i1 - i2) );
		System.out.println(s1 + " * " + s2 + " = " + (s1*s2));
		System.out.println(i2 + " / " + i1 + " = " + (i2/i1));
		System.out.println(s1 + " % " + s2 + " = " + (s1%s2));
		System.out.println(--i1);
		System.out.println(++s2);
		System.out.println("Test gan'");
		System.out.println(i1 += 3);
		System.out.println(s2 -= 6);
		System.out.println(b2 = 9);
		System.out.println(l1 += 3);
		System.out.println(s1 /= 2);
		System.out.println(s2 %= 5);
		System.out.println(b1 &= 2);
		System.out.println("test so sanh'");
	    System.out.println(i1 + " >= " + i2 + " is " + (i1 >= i2));
	    System.out.println(s2 <= s1);
	    System.out.println(l1 = i2);
	    System.out.println(b1 != b2);
	    System.out.println("test logical");
	    System.out.println(i1 + " < " + 12 + " && " + i2 + " > " + 18 + " is " + (i1 < 12 && i2 > 18));
	    System.out.println(b1 + " > " + 2 + " || " + b2 + " < " + 5 + " is " + (b1 > 3 || b2 <5));
	    System.out.println(!(l1 < 102 && l2 > 45));
	    System.out.println(!(b1 > 3 || b2 < 6));
		
		
		
	}

}
