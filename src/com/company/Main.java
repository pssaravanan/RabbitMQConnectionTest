package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int startCount = 0;
        int endCount = 1;
        for(int i = startCount ; i <= endCount; i++ ){
            if(i % 10 == 0)
                Thread.sleep(250);
            (new Thread(new TestConsumer(i))).start();
        }
    }
}
