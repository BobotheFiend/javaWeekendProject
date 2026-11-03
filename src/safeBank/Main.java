package safeBank;

import safeBank.controllers.AuthController;
import safeBank.controllers.BankingController;
import safeBank.dtos.requests.CreateAccountRequest;
import safeBank.dtos.requests.DepositRequest;
import safeBank.dtos.requests.LoginRequest;
import safeBank.dtos.responses.CreateAccountResponse;
import safeBank.dtos.responses.DepositResponse;
import safeBank.exceptions.AuthAccountException;
import safeBank.exceptions.SafeBankException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SafeBankException {
        mainMenu();
    }


    private static AuthController authController = new AuthController();
    private static BankingController bankingController = new BankingController();
    private static int tries = 1;

    private static void mainMenu() throws SafeBankException {
        print("""
                Welcome to the Safe Bank!
                
                1. Register a new account
                2. Login
                0. EXIT
               
                """);

        int input  = Integer.parseInt(input("Enter Choice!"));
        switch (input) {
            case 1 -> register();
            case 2 -> login();
            case 0 -> exit();
        }

    }

    private static void register() throws SafeBankException {
        CreateAccountRequest request = new CreateAccountRequest();

        print("Welcome to SAFE BANK! Lets Get You Started!");

        request.setFirstName(input("Enter your Name: "));
        request.setLastName(input("Enter your Last Name: "));
        request.setPin(input("Create a 4 digit PIN: "));

        try {
            CreateAccountResponse response = authController.register(request);
            print(String.format("WELCOME ABOARD! %s%n\t\tYour Account number is { %s }", response.getFullName(), response.getNumber()));
        } catch (AuthAccountException e) {
            print(e.getMessage());
            register();
        }

        mainMenu();
    }

    private static void login() throws SafeBankException {
        LoginRequest  request = new LoginRequest();


        if(tries > 3) mainMenu();

        if(tries == 3) {
            print("This is your last Attempt to login! \nIf you have not Created an Account\n\t\t Press 1. To create a new account\n\t\tPress 2. to Continue");
            int choice = Integer.parseInt(input("Enter your choice: "));
            boolean starting = true;
            while(starting) {
                try {
                    switch (choice) {
                        case 1 -> register();
                        case 0 -> print("");
                    }

                } catch (InputMismatchException e) {
                    print(e.getMessage());
                }
                starting = false;
            }
        }


        print("Input Your Valid Details to login");

        request.setAccountNumber(input("Enter your Account Number: "));
        request.setPin(input("Enter your 4 digit PIN: "));

        try {
            tries++;
            String response = authController.login(request);
            print(String.valueOf(response));
        }catch (AuthAccountException e) {
            print(e.getMessage());
            login();
        }

        app();
    }

    private static void app(){
        print("Welcome Aboard!");
        print("""
                What Would Like to do today!
                1. Deposit
                2. Withdraw
                3. View Transaction History
                4. View Balance
                0. LogOut
                """);

        int choice =  Integer.parseInt(input("Enter your choice: "));
        switch (choice) {
            case 1 -> deposit();
            case 2 -> withdraw();
            case 3 -> transcations();
            case 4 -> balance();
            case 0 -> logout();
        }
    }


    private static void  deposit() throws SafeBankException {
        DepositRequest request = new DepositRequest();

        print("How Much Would You Like to Deposit into your Account Today!?");
        print("""
                Remember!
                Amounts in negatives and Zero are not valid!
                """);

        request.setAccountNumber(input("Enter your Account Number: "));
        request.setAmount(input("Enter the Amount To deposit: "));

        try{
            DepositResponse response = bankingController.deposit(request);
            print(String.format("",response));
        } catch (SafeBankException e) {
            print(e.getMessage());

        }

    }


    static void logout() throws SafeBankException {
        mainMenu();
    }


    private static void exit(){
        print("GOOD BYE!!");
    }

    private static void print(String input){
        System.out.println(input);
    }

    private static String input(String input){
        print(input);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }


}