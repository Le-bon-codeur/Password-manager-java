package com.company;

import java.util.Scanner;

public class Main {
    private static PasswordManage pw = new PasswordManage();

    public static void main(String[] args) {
        hello();
    }

    private static void hello() {
        Scanner myScan = new Scanner(System.in);

        System.out.print("\n\n\t// PASSWORD MANAGER //\n\n");
        System.out.print("\t// 1 Retrieve a password\n\t// 2 Add password\n\t// 3 generate and store a new pass\n\t// 4 Leave the program\n\n\tchoice: ");
        int choice = 0;
        while(choice > 4 || choice <= 0) {
            choice = myScan.nextInt();
        }
        if(choice == 1) {
            System.out.print("\n\tEnter your user name: ");
            System.out.print("\n\tYour password is: " + pw.retrieve_pass(myScan.next()) + "\n\n");
        } else if (choice == 2) {
            System.out.print("\n\tEnter your user name: ");
            String username = myScan.next();
            System.out.print("\n\tEnter your password: ");
            String password = myScan.next();
            pw.write_new_pass(username, password);
            System.out.print("\n\tPassword successfully added\n\n");
        } else if (choice == 3) {
            System.out.print("\n\tEnter your user name: ");
            String username = myScan.next();
            String password = pw.generate(20);
            System.out.print("\n\tNew password: " + password + "\n");
            pw.write_new_pass(username, password);
            System.out.print("\n\tPassword successfully added\n\n");
        } else if (choice == 4) {
            System.out.print("\n\t// GOOD BYE //");
        }
    }
}
