package uz.ruzimov;

import java.util.Scanner;

public class Robot {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("Tanlang: "
          + "\n 1. Salomlashish"
          + "\n 2. Code Yozish"
          + "\n 3. Raqs tushish\n");
      int x = sc.nextInt();
      switch (x) {
        case 1:
          salomlash();
          break;
        case 2:
          codeYoz();
          break;
        case 3:
          raqsTush("tango");
          break;
        default:
          System.out.println("Hozircha bunday qobiliyatim yo'q!");
      }
    }
  }

  static void salomlash() {
    System.out.println("Assalomu alaykum do'stiiiiim! \nMen Java robotman");
  }

  static void raqsTush(String uslub) {
    System.out.println("Men " + uslub + " uslubida raqsga tushayapman!");
  }

  static void codeYoz() {
    System.out.println("System.out.println(\"HELLO WORLD\")");
  }

}