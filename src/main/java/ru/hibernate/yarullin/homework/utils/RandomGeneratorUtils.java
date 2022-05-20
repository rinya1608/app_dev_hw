package ru.hibernate.yarullin.homework.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGeneratorUtils {

    public static final char[] RU_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toCharArray();

    public static Integer getRandomPassportSeries(){
        return ThreadLocalRandom.current().nextInt(1000,9999);
    }

    public static String getRandomGroup(){
        return String.format("%s%s-%s",getRandomRuChar(), getRandomRuChar(), getRandomCode());
    }

    public static Integer getRandomPassportNumber(){
        return ThreadLocalRandom.current().nextInt(100000, 999999);
    }

    public static Integer getRandomCode(){
        return ThreadLocalRandom.current().nextInt(10000000, 99999999);
    }

    public static char getRandomRuChar(){
        return RU_ALPHABET[ThreadLocalRandom.current().nextInt(RU_ALPHABET.length)];
    }
}
