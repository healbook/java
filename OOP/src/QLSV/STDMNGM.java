package QLSV;

import java.util.function.Consumer;
import java.util.*;

public class STDMNGM {
	 int id;
	 String ten;
	float aver;
	int sl;
	int sohocphan;
	Vector tinchi = new Vector<>();
	 List<String> dshocphan = new ArrayList<>();
	List<Student> dssv = new ArrayList<>();
	Vector dssvvt = new Vector<>();
public STDMNGM() {
	while(true) {
		System.out.println("MENU CHUC NANG QUAN LY SINH VIEN");
		System.out.println("1.Nhap thong tin sinh vien");
		System.out.println("2.Xem danh sach sinh vien");
		System.out.println("3.Sap xep danh sach sinh vien tang dan theo diem trung binh");
		System.out.println("4.Tim kiem sinh vien theo ten");
		System.out.println("5.Tim kiem sinh vien theo hoc phan");
		System.out.println("6.Sap xep sinh vien theo chieu giam dan cua so tin chi");
		System.out.println("7.Tim sinh vien diem cao nhat trong mot hoc phan");
		System.out.println("8.In ra toan bo sinh vien co diem cao nhat trong tat ca hoc phan");
		System.out.println("9.Thoat chuong trinh");
		Scanner key = new Scanner(System.in);
		int slc;
		System.out.println("Nhap chuc nang can thuc hien:");
		slc = key.nextInt();
		switch(slc) {
		case 1:
			this.nhap();
			break;
		case 2:
			this.in();
			break;
		case 3:
			this.sort();
			break;
		case 4:
			this.searchname();
			break;
		case 5:
			this.searchcourse();
			break;
		case 6:
			this.sortbytinchi();
			break;
		case 7:
			this.searchmax();
			break;
		case 8:
			this.viewallmax();
			break;
		case 9:
			System.out.println("CHUONG TRINH KET THUC!!!");
			return;
	}
}
	

}
	public void nhap() {
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap so luong sinh vien: ");
		sl = key.nextInt();
		for(int i = 0;i<sl;i++) {
			System.out.println("Nhap ID cua sinh vien thu:" + (i+1) +":");
			int id = key.nextInt();
			System.out.println("Nhap ten cua sinh vien thu:" + (i+1) +":");
			key.nextLine();
			String ten = key.nextLine();
			System.out.println("Nhap so hoc phan da hoc:");
			int sohocphan = key.nextInt();
			Course[] dshp = new Course[sohocphan];
			for(int j=0;j<sohocphan;j++) {
				System.out.println("Nhap Id hoc phan thu " + (j+1) +":");
				String idhp = key.next();
				key.nextLine();
				System.out.println("Nhap ten hoc phan thu " + (j+1) +":");
				String tenhp = key.next();
				if(dshocphan.contains(tenhp)==false) {
					dshocphan.add(tenhp);
				}
				System.out.println("Nhap so tin chi cua hoc phan thu" + (j+1) +":");
				float sotc = key.nextFloat();
				System.out.println("Nhap diem cua hoc phan thu " + (j+1) +":");
				float diemhp = key.nextFloat();
				dshp[j] = new Course(idhp,tenhp,sotc,diemhp);
			}
			Student sv = new Student(id,sohocphan,ten,dshp);
			dssv.add(sv);
			dssvvt.add(sv);
		}
		
	}


	public void in() { 
		System.out.println("Danh sach sinh vien:");
		dssv.forEach(e -> System.out.println(e.toString()));
		}
		
		
	public void sort() {
		 Collections.sort(dssv);
		 System.out.println("Danh sach sinh vien sau khi sap xep:\n");
		 dssv.forEach(e -> System.out.println(e.toString()));
	}

	public void searchname() {
		Scanner key = new Scanner(System.in);
		String namets;
		int dem=0;
		System.out.println("Nhap ten can tim kiem:");
		namets = key.nextLine();
		for(Student stu: dssv) 
	        if ((stu.getTen().contains(namets)==true)) {
	            System.out.println(stu.toString());
	            dem++;
	        }
	        if(dem==0) System.out.println("Khong tim thay sinh vien");;
}
		
		
	public void searchcourse() {
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap hoc phan can tim kiem:");
		String coursets = key.nextLine();

		if(dshocphan.contains(coursets)) {
			System.out.println("Danh sach sinh vien cua lop hoc phan" + coursets + ":");
			for(Student stu: dssv) {
				for(int j=0;j<stu.getSohocphan();j++) {
					if(stu.getTenhp(j).equalsIgnoreCase(coursets)) {
						System.out.println(stu.toString());
					} 
				}
			}
		}
		else System.out.println("Khong ton tai hoc phan " + coursets);
	}
	public void sortbytinchi() {
		System.out.println("\n--Sap xep theo chieu giam dan cua so luong tin chi da hoc--\n");
		     System.out.println("\n--Danh sach sinh vien sau khi sap xep--");
		Collections.sort(dssv,Collections.reverseOrder(new Comparator<Student>() { 
				 @Override
		            public int compare(Student a, Student b) {
		                return (a.getTongsotc()) > (b.getTongsotc()) ? 1: -1;
		            }
		}));;	
	
		for(Student stu: dssv)
			System.out.println(stu.toString());
	}
	public void searchmax() {
		Scanner key = new Scanner(System.in);
		System.out.println("Nhap hoc phan:");
		String tenhpcantim = key.nextLine();
		float max=0;
		Student[] stu = new Student[dssv.size()];
		int index=0,t=0;;
		Enumeration vEnum = dssvvt.elements();
		while(vEnum.hasMoreElements()) 
		   {
			   stu[index] = (Student)vEnum.nextElement();
			   index++;
		   }
		 if(dshocphan.contains(tenhpcantim)) {
			 for (int i = 0; i<index; i++)
				 for(int j=0;j<stu[i].getSohocphan();j++) 
					 if(stu[i].getTenhp(j).equalsIgnoreCase(tenhpcantim)) {
						 max=stu[i].getDiemhp(j);
						 t=i;
						 i=index;
					   }
			 for (int i = 0; i<index; i++)
				 for(int j=0;j<stu[i].getSohocphan();j++)
					 if(stu[i].getTenhp(j).equalsIgnoreCase(tenhpcantim)) 
						 if(stu[i].getDiemhp(j)>max) {
							 max=stu[i].getDiemhp(j);
							 t=i;
					   	}
			 System.out.println("Sinh vien co diem cao nhat:");
			   System.out.println("ID: "+stu[t].getId()+", Ten: "+stu[t].getTen()+", Diem hoc phan:" + stu[t].getDiemhp(t));
		   }
		   else System.out.println("\nKhong ton tai hoc phan " + tenhpcantim);
	}
	public void viewallmax() {
		  for(int a=0;a<dshocphan.size();a++) {
			   System.out.println("Hoc sinh co diem cao nhat cua hoc phan "+dshocphan.get(a));
			   float max=0;
			   Student[] stu = new Student[dssv.size()];
			   int index=0,t=0;;
			   Enumeration vEnum = dssvvt.elements();
			   while(vEnum.hasMoreElements()) 
			   {
				   stu[index] = (Student)vEnum.nextElement();
				   index++;
			   }
				   for (int i = 0; i<index; i++)
					   for(int j=0;j<stu[i].getSohocphan();j++) 
						   if(stu[i].getTenhp(j).equalsIgnoreCase(dshocphan.get(a))) {
							   max=stu[i].getDiemhp(j);
							   t=i;
							   i=index;
							   break;
						   }  	  
				   for (int i = 0; i<index; i++)
					   for(int j=0;j<stu[i].getSohocphan();j++)
						   if(stu[i].getTenhp(j).equalsIgnoreCase(dshocphan.get(a))) {
							   if(stu[i].getDiemhp(j)>max) {
								   max=stu[i].getDiemhp(j);
								   t=i;
						   	}
						  }
				   System.out.println("ID: "+stu[t].getId()+", Ten: "+stu[t].getTen()+"Diem hoc phan:" + stu[t].getDiemhp(t));
		   }
	   }
}



