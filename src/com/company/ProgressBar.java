package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class ProgressBar {
    final HelperClass progress;
    final ThreadBar target;
    static int m;
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to the Progress Bar");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the max number");
        long n = scanner.nextLong();
        System.out.println("Input m number");
        long m = scanner.nextLong();
        ArrayList<Integer> test = new ArrayList<>();
        ProgressBar progressBar = new ProgressBar(n, 70);
        progressBar.start();
        for (int i = 0; i <= n; i++) {
            int sum = i + 5;
            test.add(sum);
            progressBar.refreshProgressBar();
            System.out.format("%s %s %s  ", progressBar.getCurrent(), "/", progressBar.getTotal());
            progressBar.step();
            if (n > 1000) {
                progressBar.sleep(0);
            }
            progressBar.sleep(100);
            if (i == n) {
                break;
            }
        }
        progressBar.stop();

    }
    public ProgressBar(long total, long delay) {
        progress = new HelperClass(total);
        target = new ThreadBar(progress, delay);
    }
    public  void start() throws InterruptedException {
        Thread thread[] = new Thread[m];
        for (int i = 0; i < m; i++) {
            thread[i] = new Thread();
            thread[i].start();
        }
        for (int i = 0; i < m; i++) {
            thread[i].join();
        }
    }
    public void step() {
        progress.stepBy(1);
    }
    public void stop() {
        target.dissolved();
    }
    public long getCurrent() {
        return progress.getCurrent();
    }
    public long getTotal() {
        return progress.getTotal();
    }
    private void sleep(int n) {
        target.sleep(n);
    }
    private void refreshProgressBar() {
        target.refreshProgressBar();
    }
    static class HelperClass {
        long TOTAL_COUNT = 0;
        long current = 0;
        HelperClass(long n) {
            TOTAL_COUNT = n;
        }
        synchronized void stepBy(long i) {
            current = current + i;
            if (current > TOTAL_COUNT) {
                TOTAL_COUNT = current;
            }
        }
        synchronized long getCurrent() {
            return current;
        }
        synchronized long getTotal() {
            return TOTAL_COUNT;
        }
    }
}
