package QLYSV;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;


public class as {
    Vector liststudent = new Vector(); // Luu thong tin sinh vien
    Vector listcredit = new Vector(); // Luu so tin chi
    Vector listtitle = new Vector(); // Luu thong tin hoc phan
    Vector list = new Vector();
    Scanner sc = new Scanner(System.in);
    public as() {
        while(true) {
            System.out.println(">>>CHUONG TRINH QUAN LY SINH VIEN<<<");
            System.out.println("  1. Nhap danh sach sinh vien   ");
            System.out.println("  2. Xem danh sach sinh vien   ");
            System.out.println("  3. Sap xep sach sinh vien tang dan diem trung binh   ");
            System.out.println("  4. Tim sinh vien theo ten   ");
            System.out.println("  5. Tim cac hoc sinh cung hoc mot hoc phan   ");
            System.out.println("  6. Sap xep giam dan tin chi ");
            System.out.println("  7. Tim kiem va hien thi sinh vien co diem cao nhat hoc phan ");
            System.out.println("  8. Ket thuc chuong trinh ");
            System.out.println("  -------------");
            System.out.println("  Nhap 1 so de chon chuc nang  ");
            int z = sc.nextInt();
            switch(z) {
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
                    searchtitle();
                    break;
                case 6:
                    sortbycredit();
                    break;
                case 7:
                    maxmark();
                    break;
                case 8:
                    System.out.println("END");
                    return;
            }
        }
    }
    public void input() {
        System.out.println("NHAP DANH SACH SINH VIEN");
        int total=0;
        System.out.println("  Nhap so luong sinh vien:  ");
        int n = sc.nextInt();
        for (int i=0; i<n; i++) {
            System.out.println("  Nhap thong tin cho sinh vien thu: " + (i+1));
            System.out.println("  ID: ");
            String id = sc.next();
            sc.nextLine(); // xoa bo dem
            System.out.println("  Ten: ");
            String name = sc.nextLine();
            System.out.println("  So mon da hoc: ");
            int num = sc.nextInt();
            Course[] clist = new Course[num];
            for (int j=0; j<num; j++) {
                System.out.println("  Nhap thong tin hoc phan thu " + (j+1) + ": ");
                System.out.println("  ID: ");
                String idhp = sc.next();
                sc.nextLine();
                System.out.println("  Ten hoc phan: ");
                String title = sc.nextLine();
                System.out.println("  So tin chi: ");
                float credit = sc.nextFloat();
                System.out.println("  Diem: ");
                float note = sc.nextFloat();
                sc.nextLine();
                total += credit;
                clist[j] = new Course(idhp, title, credit, note);
                NoteTitle nt = new NoteTitle(name, id, idhp, title, note);
                listtitle.add(nt);
            }
            Student st = new Student(id, name, num, clist);
            TotalCredit tc = new TotalCredit(name, id, total);
            liststudent.add(st);
            listcredit.add(tc);
            total = 0;
        }
        System.out.println("\n--------------------\n");
    }
    public void view() {
        System.out.println("XEM DANH SACH SINH VIEN");
        System.out.println("  Thong tin danh sach sinh vien");
        Enumeration Enums = liststudent.elements();
        Enumeration Enumc = listcredit.elements();
        int i = 1;
        while (Enums.hasMoreElements()) {
            Student st = (Student) Enums.nextElement();
            TotalCredit tc = (TotalCredit) Enumc.nextElement();
            System.out.println("    " + i + ". ID : " + st.getId() + ", Ten : " + st.getName() + ", So mon :" + st.getList().length);
            for (int j = 0; j < st.getList().length; j++) {
                System.out.println(" ID hoc phan : " + st.getList()[j].getIdcourse() + " ,ten hoc phan : " + st.getList()[j].getNamecourse()
                        + " ,so tin chi : " + st.getList()[j].getCredit() + " ,diem : " + st.getList()[j].getNote());
            }
            System.out.println("Tong tin chi da hoc la: " + tc.getTotal());
            i++;
        }
        System.out.println("\n--------------------\n");
    }
    public void sort() {
        System.out.println("XEP DANH SACH SINH VIEN");
        Student [] st = new Student [liststudent.size()];
        int index = 0;
        Enumeration Enums = liststudent.elements();
        while (Enums.hasMoreElements()) {
            st[index] = (Student) Enums.nextElement();
            index++;
        }
	  /*for (int i=0; i<index-1; i++)
		{
			for (int j=i+1; j<index; j++)
			{
				if (st[i].getAver() > st[j].getAver())
				{
					Student temp = st[i];
					st[i] = st[j];
					st[j] = temp;
				}
			}
		}*/
        Arrays.sort(st);
        System.out.println("\n--Danh sach sinh vien sau khi sap xep--");
        for (index = 0; index < st.length; index++) {
            System.out.println("    " + (index + 1) + ". ID = " + st[index].getId() + ", Ten=" + st[index].getName()
                    + ", Diem trung binh=" + st[index].getAver());
        }
        System.out.println("\n--------------------\n");
    }
    public void search() {
        System.out.println("TIM SINH VIEN THEO TEN");
        System.out.print(" Nhap ten sinh vien can tim : ");
        String name = sc.next();
        Enumeration Enums = liststudent.elements();
        System.out.println("\n--Thong tin tim kiem duoc--");
        while (Enums.hasMoreElements())
        {
            Student st = (Student) Enums.nextElement();
            if (st.getName().indexOf(name) != -1) {
                System.out.println("ID = " + st.getId() + ", Ten = " + st.getName() + ", Diem trung binh = " + st.getAver());
            }
        }
        System.out.println("\n--------------------\n");
    }
    public void searchtitle() {
        System.out.println("TIM SINH VIEN HOC CHUNG 1 HOC PHAN");
        System.out.print(" Nhap ten hoc phan can tim: ");
        String name = sc.next();
        Enumeration Enums = liststudent.elements();
        int i = 1;
        System.out.println(" Cac hoc sinh hoc hoc phan " + name + " la : ");
        while (Enums.hasMoreElements()) {
            Student st = (Student) Enums.nextElement();
            for (int j = 0; j < st.getList().length; j++) {
                if (st.getList()[j].getNamecourse().equalsIgnoreCase(name)) {
                    System.out.println("    " + i + ". ID=" + st.getId() + ", Ten=" + st.getName());
                }
            }
            i++;
        }
        System.out.println("\n--------------------\n");
    }
    public void sortbycredit() {
        System.out.println("XEP DANH SACH SINH VIEN");
        TotalCredit[] tc = new TotalCredit[listcredit.size()];
        int index = 0;
        Enumeration Enumc = listcredit.elements();
        while (Enumc.hasMoreElements()) {
            tc[index] = (TotalCredit) Enumc.nextElement();
            index++;
        }
        for (int i=0; i<index-1; i++)
        {
            for (int j=i+1; j<index; j++)
            {
                if (tc[i].getTotal()<tc[j].getTotal())
                {
                    TotalCredit temp = tc[i];
                    tc[i] = tc[j];
                    tc[j] = temp;
                }
            }
        }
        System.out.println("--Danh sach sinh vien sau khi sap xep giam dan theo tin chi la--");
        for (index=0; index<tc.length; index++)
        {
            System.out.println("    " + (index + 1) + " || Ten: " + tc[index].getName() + " || ID: " + tc[index].getId() + " || Tin chi: " + tc[index].getTotal());
        }
        System.out.println("\n--------------------\n");
    }
    public void maxmark() {
        System.out.println("Nhap ten hoc phan can tim kiem sinh vien diem cao nhat");
        String namehp = sc.next();
        Enumeration Enumt = listtitle.elements();
        int index = 0;
        while (Enumt.hasMoreElements()) {
            NoteTitle nt = (NoteTitle) Enumt.nextElement();
            if (nt.getNamecourse().contains(namehp))
            {
                list.add(nt);
            }
        }

        NoteTitle[] nt = new NoteTitle[list.size()];
        index = 0;
        Enumeration Enum = list.elements();
        while (Enum.hasMoreElements()) {
            nt[index] = (NoteTitle) Enum.nextElement();
            index++;
        }

        float max = 0;
        for (int i=0; i<index; i++)
        {
            if (nt[i].getNote()>=max)
            {
                max = nt[i].getNote();
            }
        }

        System.out.println("Sinh vien co diem cao nhat la: ");
        for (index=0; index<nt.length; index++)
        {
            if (nt[index].getNote()==max)
            {
                System.out.println("ID: " + nt[index].getId() + " || Ten: " + nt[index].getName() + " || Diem: " + nt[index].getNote());
            }
        }
    }

    public static void main(String[] args) {
        new StudentManagement();
    }
}
class Student implements Comparable {
    private String id;
    private	String name;
    private int noCourse;
    private Course[] list;
    private float aver;

    public Student() {
        this.id = "";
        this.name = "";
        this.aver = 0.0f;
    }

    public Student(String id, String name, int noCourse, Course[] list) {
        this.id = id;
        this.name = name;
        this.noCourse = noCourse;
        this.list = list;

        int sumNote=0, sumCredit=0;

        for (int i=0; i<list.length; i++) {
            sumNote += this.list[i].getNote()*this.list[i].getCredit();
            sumCredit += this.list[i].getCredit();
        }
        this.aver = (float) sumNote/sumCredit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoCourse() {
        return noCourse;
    }

    public void setNoCourse(int noCourse) {
        this.noCourse = noCourse;
    }

    public Course[] getList() {
        return list;
    }

    public void setList(Course[] list) {
        this.list = list;
    }

    public float getAver() {
        return aver;
    }

    public void setAver(float aver) {
        this.aver = aver;
    }

    @Override
    public int compareTo(Object other) {
        Student otherRect = (Student)other;
        return (int)(this.aver-otherRect.aver);
    }
}
class NoteTitle implements Comparable {
    private String name;
    private String id;
    private String idcourse;
    private String namecourse;
    private float note;

    public NoteTitle(String name, String id, String idcourse, String namecourse, float note) {
        this.name = name;
        this.id = id;
        this.idcourse = idcourse;
        this.namecourse = namecourse;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(String idcourse) {
        this.idcourse = idcourse;
    }

    public String getNamecourse() {
        return namecourse;
    }

    public void setNamecourse(String namecourse) {
        this.namecourse = namecourse;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public int compareTo(Object other) {
        NoteTitle otherRect = (NoteTitle) other;
        return (int) (this.note - otherRect.note);
    }
}
class Course {
    private String idcourse;
    private String namecourse;
    private float credit;
    private float note;

    public Course() {
        this.idcourse = "";
        this.namecourse = "";
        this.credit = 0.0f;
        this.note = 0.0f;
    }

    public Course(String idcourse, String namecourse, float credit, float note) {
        this.idcourse = idcourse;
        this.namecourse = namecourse;
        this.credit = credit;
        this.note = note;
    }

    public String getIdcourse() {
        return idcourse;
    }

    public void setIdcourse(String idcourse) {
        this.idcourse = idcourse;
    }

    public String getNamecourse() {
        return namecourse;
    }

    public void setNamecourse(String namecourse) {
        this.namecourse = namecourse;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }
}
class TotalCredit implements Comparable{
    private String name;
    private String id;
    private float total;

    public TotalCredit(String name, String id, float total) {
        this.name = name;
        this.id = id;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public int compareTo(Object other) {
        TotalCredit otherRect = (TotalCredit) other;
        return (int) (this.total - otherRect.total);
    }
}