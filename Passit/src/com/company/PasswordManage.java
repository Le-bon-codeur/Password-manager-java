package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

public class PasswordManage {
    private final SecureRandom random = new SecureRandom();

    private final String PATH;
    private final String ALPHA_CAPS;
    private final String ALPHA;
    private final String NUMERIC;
    private final String SPECIAL_CHARS;

    // CONSTRUCTORS
    public PasswordManage() {
        ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ALPHA = "abcdefghijklmnopqrstuvwxyz";
        NUMERIC = "0123456789";
        SPECIAL_CHARS = "!@#$%^&*_=+-/";
        PATH = "/home/pilou/PassManager/passwords.txt";
        create_pass_list();
    }
    public PasswordManage(String _path) {
        ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        ALPHA = "abcdefghijklmnopqrstuvwxyz";
        NUMERIC = "0123456789";
        SPECIAL_CHARS = "!@#$%^&*_=+-/";
        PATH = _path;
        create_pass_list();
    }

    // METHODS PRIVATE
    private String generatePassword(int _length, String _dictionary) {
        String result = "";
        for (int i = 0; i < _length; i++) {
            int index = random.nextInt(_dictionary.length());
            result += _dictionary.charAt(index);
        }
        return result;
    }

    private void create_pass_list() {
        try {
            File myObj = new File(PATH);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // METHODS PUBLIC
    public String generate(int _password_length) {
        // modify the second parameter to un/include certain chars
        return generatePassword(_password_length, ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC);
    }

    public String retrieve_pass(String _username) {
        try {
            File myObj = new File(PATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(" ");
                if(data[0].equals(_username)) {
                    return data[1];
                }
            }
            myReader.close();
            return null;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public void write_new_pass(String _username, String _password) {
        try {
            FileWriter myWriter = new FileWriter(PATH, true);
            myWriter.write("\n" + _username + " " + _password);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
