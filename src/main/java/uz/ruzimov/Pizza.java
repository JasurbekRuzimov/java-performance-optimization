package uz.ruzimov;

public class Pizza {

  public static void main(String[] args) {
    makePizza("Go'sht", 30);
    makePizza("Kolbasa", 20);
    makePizza("Pishloq", 25);
  }

  static void makePizza(String mahsulot, int minutes) {
    System.out.println(
        mahsulot + " dan tayyorlangan pitsa " + minutes + " daqiqada tayyor bo'ladi!");
  }

}