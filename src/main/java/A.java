import java.util.Scanner;
public class A{
    public static void main(String args[])
    {
        Scanner s= new Scanner(System.in);
        System.out.print("Enter number of subject you have:");
        int n=s.nextInt();
        
        int tot=0;
        for(int i=1;i<=n;i++)
        {
            System.out.print("Enter the mark obtained in subject "+i+" out of 100:");
            int m=s.nextInt();
            tot=tot+m;
        }
        B b=new B(n,tot);
    }
}
