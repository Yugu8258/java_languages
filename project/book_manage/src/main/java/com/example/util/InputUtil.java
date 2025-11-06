package com.example.util;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class InputUtil {
    private static Scanner scanner = new Scanner(System.in);

    public static int nextInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                sendErrorAndWait("輸入錯誤, 請輸入數字.");
            }
        }
    }

    public static <T> T nextEntity(String prompt, Function<Integer, T> getter) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                T entity = getter.apply(input);
                if (entity != null) {
                    System.out.println("已選擇: " + entity.toString());
                    return entity;
                } else {
                    sendErrorAndWait("未在數據庫中找到此ID的數據, 請重新輸入!");
                }
            } catch (NumberFormatException e) {
                sendErrorAndWait("輸入錯誤, 請輸入數字.");
            }
        }
    }

    public static String nextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String nextLine(String prompt, String... values) {
        List<String> list = Arrays.asList(values);
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine();
            if (list.contains(input)) {
                return input;
            }
            sendErrorAndWait("您輸入的結果不合法, 請重新輸入!");
        }
    }

    private static void sendErrorAndWait(String prompt) {
        System.err.println(prompt);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}
