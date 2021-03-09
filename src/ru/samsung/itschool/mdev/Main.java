package ru.samsung.itschool.mdev;

import java.util.TreeSet;

public class Main {
    // [+][-][+][-]
    public static void main(String[] args) throws InterruptedException {
        PrintThread thread1 = new PrintThread("+");
        thread1.start();
        PrintThread thread2 = new PrintThread("-");
        thread2.start();
        Thread.sleep(2000);
        thread1.flag = false;
        thread1.join(); // ждет завершение потока
        testPrint("Stopped 1!");
    }

    public static Object key = new Object();

    public static void testPrint(String s) {
        synchronized (key) {
            try {
                System.out.print("[");
                Thread.sleep(500);
                System.out.print(s);
                Thread.sleep(500);
                System.out.print("]");
                //key.notify(); // возобновление потока
                //key.wait(); // поставить поток в режим ожидания
            } catch (InterruptedException e) {
                // e.printStackTrace();
                System.out.println(e.getStackTrace());
            }
        }
    }
}
class PrintThread extends Thread {
    private String message;
    public boolean flag = true;
    public PrintThread(String message) {
        this.message = message;
    }
    @Override
    public void run() {
        while(flag) {
            Main.testPrint(message);
        }
    }
}
