package uz.ruzimov;

public class Robot {

  public static void main(String[] args) {
    salomlash();
    raqsTush("tango");
    raqsTush("balet");
    codeYoz();
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
