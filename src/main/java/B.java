public class B
{
   
    public B(int n, int tot)
    {
        System.out.println("Total marks:" +tot);
        int avg=tot/n;
        System.out.println("Average percentage:"+avg+"%");
        if(avg>90&&avg<=100)
            System.out.println("your grade:A");
        else if(avg>80 &&avg<=90)
            System.out.println("your grade:B");
        else if(avg>70 &&avg<=80)
            System.out.println("your grade:C");
        else if(avg>60 &&avg<=70)
            System.out.println("your grade:D");
        else if(avg>50 &&avg<=60)
            System.out.println("your grade:E");
        else
            System.out.println("your grade:F");
            
    }
}