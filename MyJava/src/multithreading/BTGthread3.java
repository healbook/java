package multithreading;
//BAI TAP 3 (TU GIAI)//

public class BTGthread3 {
	threadrandom t1 = new threadrandom();
	threadrandom t2 = new threadrandom();
	threadrandom t3 = new threadrandom();
	threadrandom t4 = new threadrandom();
	threadrandom t5 = new threadrandom();
	threadrandom t6 = new threadrandom();
	threadrandom t7 = new threadrandom();
	public BTGthread3() {
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
	try {
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		int s = t1.getResult() + t2.getResult() + t3.getResult()+t4.getResult()+t5.getResult()+t6.getResult()+t7.getResult();
		System.out.println("Total random number: "+s);
	} catch(Exception e) {
		e.printStackTrace();
	}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BTGthread3();
	}
class threadrandom extends Thread {
	int min=1;
	int max = 30;
	int rn;
	public threadrandom() {
		
	}
	public void run() {
		rn = (int)(Math.random() * (max - min + 1) + min);
		System.out.println("Num:" + rn);
	}
	public int getResult() {
		return rn;
	}
}
}
