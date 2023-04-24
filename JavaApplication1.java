package javaapplication1;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JavaApplication1 {

    public static Random rand = new Random();
    public static int[][] CurrentScreen;
    public static int[][] FINALScreen;
    public static Integer n = 3;

    public static void Print(int[][] A) {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(String.format("%3s", A[i][j]));
            }
            System.out.println();
        }
    }

 

    public static void FillBoard() {
        CurrentScreen = new int[n][n];
        FINALScreen = new int[n][n];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            numbers.add(i);
        }
        for (int i = 0; i < n * n; i++) {
            int j = rand.nextInt(numbers.size());
            CurrentScreen[i / n][i % n] = numbers.get(j);
            numbers.remove(j);
        }

        Integer c = 0;
        for (int i = 1; i < n * n; i++) {
            if (i % 2 == 0) {
                FINALScreen[c / n][c % n] = i;
                c++;
            }
        }
        for (int i = 1; i < n * n; i++) {
            if (i % 2 == 1) {
                FINALScreen[c / n][c % n] = i;
                c++;
            }
        }
        Print(FINALScreen);
    }

    public static void Swap(Integer i1, Integer j1, Integer i2, Integer j2) {
        Integer temp = CurrentScreen[i1][j1];
        CurrentScreen[i1][j1] = CurrentScreen[i2][j2];
        CurrentScreen[i2][j2] = temp;
    }

    public static boolean GoTo(char d) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (CurrentScreen[i][j] == 0) {
                    if (d == 'l' && j > 0) {
                        Swap(i, j, i, j - 1);return true;
                    }
                    if (d == 'r' && j < n - 1) {
                        Swap(i, j, i, j + 1);return true;
                    }
                    if (d =='d' && i < n - 1) {
                        Swap(i, j, i + 1, j);return true;
                    }
                    if (d == 'u' && i > 0) {
                        Swap(i, j, i - 1, j);return true;
                    }
                }
            }
        }
        return false;
    }
    public static void Score(long time, Integer k) {
        System.out.println("total elpased time: "+ time + " seconds");
        System.out.println("Score is: " + (1000.0*n*n)/(Math.log(k*Math.pow(time, 1/3.0) )));
    }
    public static boolean IsDOne() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(CurrentScreen[i][j] !=FINALScreen[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.print("Please Enter n:\t");
        n = in.nextInt();
        FillBoard();
        Print(CurrentScreen);
        long start = System.currentTimeMillis();
        Integer moves = 0;
        while (!IsDOne()) {
            System.out.println("moves: "+moves+"\tElpased Time: "+ (System.currentTimeMillis() - start) / 1000 + "(s)");
            System.out.print("Please Enter direction(r,l,d,u)\t");
            char c = in.next().charAt(0); 
            boolean can = GoTo(c);
            if(can){
                Print(CurrentScreen);
                moves++;
            }
            else{
                System.out.print("wrong input! ");
            }
        }
        Score((System.currentTimeMillis() - start) / 1000, moves);
    }
}
