package uz.ruzimov;

import java.util.Arrays;
import java.util.Scanner;

public class Array2 {
 // 9-masala
  // array[]  va a soni berilgan.
  // Shu massivning n chi indexsini olib tashang.

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = {4, 5, 6, 7,7 ,77,7,7,7,7,7,7,67};

    System.out.println("Olib tashlash zarur bo'lgan indeksni kiriting: ");
    int n = sc.nextInt();

    int[] tozaMassiv = new int[arr.length - 1];

    int indekslash = 0;

    for (int i = 0; i < arr.length - 1; i++) {
      if (i == n) {
        indekslash++;
      }
      tozaMassiv[i] = arr[indekslash];
      indekslash++;
    }

    System.out.println("Asl holati:     "+Arrays.toString(arr));
    System.out.println("Keyingi holati: "+Arrays.toString(tozaMassiv));

  }

}
