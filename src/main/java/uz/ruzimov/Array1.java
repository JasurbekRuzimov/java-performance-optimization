package uz.ruzimov;

public class Array1 {

  public static void main(String[] args) {
    int[] score = {81, 88, 89, 92, 66, 71, 90}; // arrayga ma'lumotlar kiritish

    int max = score[0];
    int min = score[0];
    int sum = 0;

    for (int i = 0; i < score.length; i++) {

      sum += score[i];

      if (score[i] > max) {
        max = score[i];
      }

      if (score[i] < min) {
        min = score[i];
      }
    }

    double avarage = (double) sum / score.length;

    System.out.println("Eng yuqori ball: " + max);
    System.out.println("Eng past ball: " + min);
    System.out.println("O'rtacha ball: " + avarage);

  }
}
