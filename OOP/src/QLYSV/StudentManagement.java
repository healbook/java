package QLYSV;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;

public class StudentManagement {
	Vector list = new Vector();
	public StudentManagement() {
		while(true) {
			System.out.println("CHUONG TRINH QUAN LY SINH VIEN:");
			System.out.println("Chuc nang chinh:");
			System.out.println("1.Nhap danh sach sinh vien");
			System.out.println("2.Xem danh sach sinh vien");
			System.out.println("3.Sap xep sinh vien tang dan theo diem trung binh");
			System.out.println("4.Tim sinh vien theo ten");
			System.out.println("5.Thoat");
			int num;
			Scanner key = new Scanner(System.in);
			System.out.println("Chon chuc nang can thuc hien:");
			num = key.nextInt();
			switch(num) {
			case 1:
				this.input();
				break;
			case 2:
				this.view();
				break;
			case 3:
				sort();
				break;
			case 4:
				search();
				break;
			case 5:
				System.out.println("Chuong trinh ket thuc!!!!!");
		
			}
		}
	}
	public void input() {
		int n;
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap so luong sinh vien");
		n = key.nextInt();
		for(int i = 1;i<=n;i++) {
			System.out.println("Nhap thong tin sinh vien " + i + ":");
			System.out.println("ID:");
			int id = Integer.parseInt(key.next());
			key.nextLine();
			System.out.println("Nhap ten:");
			String name = key.nextLine();
			System.out.println("Nhap diem trung binh");
			float aver = key.nextFloat();
			Student st = new Student(id, name, aver);
			list.add(st);
		}
	}
	public void view() {
		System.out.println("Thong tin danh sach sinh vien:");
		Enumeration vEnum = list.elements();
		int i = 1;
		while(vEnum.hasMoreElements()) {
			Student sts = (Student)vEnum.nextElement();
			System.out.println("   " + i +"\nID = " +sts.getId() +","
					+ "\nTen:" + sts.getName() 
					+ "\nDiem trung binh:" + sts.getAver());
			i++;
		}
		System.out.println("\n------\n");
	}
	public void sort() {
		Student[] sts = new Student[list.size()];
		int index = 0;
		Enumeration vEnum = list.elements();
		while(vEnum.hasMoreElements()) {
			sts[index] = (Student)vEnum.nextElement();
			index++;
		}
		Arrays.sort(sts);
		System.out.println("Danh sach sinh vien sau khi sap xep:");
		for(index=0;index<sts.length;index++) {
			System.out.println("   " + (index+1) +"\nID=" + sts[index].getId() +
					"Ten=" + sts[index].getName()
					+ "Diem trung binh=" +sts[index].getAver());
			
		}
	}
	public void search() {
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap ten sinh vien can tim:");
		String name = key.next();
		Enumeration vEnum = list.elements();
		while(vEnum.hasMoreElements()) {
			Student sts = (Student)vEnum.nextElement();
			if(sts.getName().indexOf(name)!=-1) {
				System.out.println("\nID=" + sts.getId() +
						"\nTen=" + sts.getName()
						+ "\nDiem trung binh=" +sts.getAver());
			}
			
		}
		System.out.println("\n------\n");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StudentManagement nvm = new StudentManagement();
	
	}
}
class Student implements Comparable {
	private int id;
	private String name;
	private float aver;
	public Student() {
		name = new String("");
		id=0;
		aver=0;
	}
	public Student(int id, String name, float aver) {
		this.id=id;
		this.name=name;
		this.aver=aver;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getAver() {
		return aver;
	}
	public void setAver(float aver) {
		this.aver = aver;
	}
	public int compareTo(Object other) {
		Student otherRect = (Student)other;
		return (int)(this.aver-otherRect.aver);
	}
}


