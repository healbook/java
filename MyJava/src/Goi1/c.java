package Goi1;
import java.util.Scanner;
public class c {
    public static int min(int n)
    {
        Scanner kb = new Scanner(System.in);
        int [] A = new int [n];
        System.out.println("Nhập các phần tử của mảng ");
        for (int i=0; i<n; i++)
        {
            System.out.println("Nhập phần tử thứ " +i+ "của mảng");
            A[i] = kb.nextInt();
        }
        int k = A[0];
        for (int i=0; i<n; i++)
            if (A[i]<k)
                k=A[i];
        return k;
    }
    public static void main(String[] args) {
        Scanner kb2 = new Scanner(System.in);
        System.out.println("Nhập số phần tử của mảng");
        int n = kb2.nextInt();
        System.out.println("Kết quả = " + min(n));
    }
}