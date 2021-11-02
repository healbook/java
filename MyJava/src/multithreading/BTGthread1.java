package multithreading;
//BAI TAP 1 (TU GIAI)//
import java.util.Scanner;

public class BTGthread1 {
	
	Scanner key = new Scanner(System.in);
	thread1 t1 = new thread1(key.nextFloat(),key.nextFloat());
	thread2 t2 = new thread2();
	thread3 t3 = new thread3();
	public BTGthread1() {
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			float dtich = t2.getResult();
			float cvi = t3.getResult();
			System.out.println("Dien tich cua hinh chu nhat co canh " + t1.getA() + " va " + t1.getB() + " la: " + dtich);
			System.out.println("Chu vi cua hinh chu nhat co canh " + t1.getA() + " va " + t1.getB() + " la: " + cvi);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Nhap a va b:");
		new BTGthread1();
	}
class thread1 extends Thread {
	float a;
	float b;
	public thread1(float x,float y) {
		setPriority(MAX_PRIORITY);
		a=x;
		b=y;
	}
	public void run() {
	}
	public float getA() {
		return a;
	}
	public float getB() {
		return b;
	}
}
class thread2 extends Thread {
	float dt;
	float a,b;
	public thread2() {
		a=t1.getA();
		b=t1.getB();
	}
	public void run() {
		try {
		 dt = a*b;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public float getResult() {
		return dt;
	}
}
class thread3 extends Thread {
	float cv;
	float a,b;
	public thread3() {
		a=t1.getA();
		b=t1.getB();
	}
	public void run() {
		try {
			cv = (a+b)*2;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public float getResult() {
		return cv;
	}
}
}
