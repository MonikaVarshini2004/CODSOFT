import java.util.Scanner;
public class ATMmachine 
{
    public static void main(String args[])
    {
        UsBankAccount act =new UsBankAccount() {
            @Override
            public void checkBalance() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        Scanner s=new Scanner(System.in);
        double existAmt=0;
        do
        {
            System.out.println("Welcome to ATM");
            System.out.println("Welcome to the ATM :) \n 1.Deposit\n 2. Withdraw\n 3.Check Your Balance\n 4.Exit\n");
            int num=s.nextInt();
            
            switch(num)
            {
                case 1:
                    System.out.println("Enter your deposit amount:");
                    double dAmt=s.nextDouble();
                    existAmt=act.deposit(existAmt,dAmt);
                    break;
                case 2:
                    System.out.println("Enter the Amount to be withdraw:");
                    double wAmt=s.nextDouble();
                    existAmt=act.withdraw(existAmt, wAmt);
                    break;
                case 3:
                    System.out.println("Your balance amount is" +existAmt);
                    break;
                case 4:
                    System.out.println("ThankYou for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("You Choosed invalid number");
                    
            }
            System.out.println("Do you want to choose again(Yes/No):");
            String ch = s.next();
            if(!ch.equalsIgnoreCase("yes"))
            { 
                break;
            }
        }while(true);
        
    }
}