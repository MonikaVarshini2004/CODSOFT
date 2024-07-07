/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Monika Varshini.S
 */
public abstract class UsBankAccount implements ATMinterface
{

    @Override
    public double withdraw(double eA, double wA)
    {
        if(eA>=wA)
        {
            System.out.println("Your withdrawal of " +wA+ " is successful. Please take your cash.");
            eA=eA-wA;
            return eA;
            
        }
        else
        {
            System.out.println("There is no sufficient balance");
            return eA;
        }  
    }
    @Override
    public double deposit(double eA, double dA)
    {
        System.out.println("You deposited"+dA+"amount successfully");
        eA=eA+dA;
        System.out.println("Deposit successful.");
        return eA;
    }
    
}
