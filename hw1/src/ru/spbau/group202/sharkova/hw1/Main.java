package ru.spbau.group202.sharkova.hw1;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* This class serves only the testing purpose
 * Courtesy of stackoverflow users:
   https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
 */
class RandomString {

    /**
     * Generate a random string.
     */
    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx) {
            buf[idx] = symbols[random.nextInt(symbols.length)];
        }
        return new String(buf);
    }

    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String alphanum = upper + lower + digits;

    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomString(int length, Random random, String symbols) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }
        if (symbols.length() < 2) {
            throw new IllegalArgumentException();
        }
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    /**
     * Create session identifiers.
     */
    public RandomString() {
        this(21);
    }

}

public class Main {
    public static void main(String[] args) {
        HashTable ht = new HashTable();
        System.out.println(ht.size());

        RandomString gen = new RandomString(8, ThreadLocalRandom.current());
        for (int i = 0; i < 36; ++i) {
            String str1 = gen.nextString();
            String str2 = gen.nextString();
            ht.put(str1, str2);
        }

        ht.put("0-42L", "String1");
        ht.put("0-43-", "string2");
        ht.put("0-41k", "STRING3");

        System.out.println(ht.size());

        ht.put("", "\n");
        System.out.println(ht.get(""));
        System.out.println(ht.size());
        System.out.println(ht.size());
        ht.put("f5a5a608", "Value");
        ht.put("NonZeroHash", "String");
        System.out.println(ht.size());
        System.out.println(ht.contains("f5a5a608"));
        System.out.println(ht.contains(""));
        ht.remove("");
        System.out.println(ht.size());
        System.out.println(ht.contains(""));
        System.out.println(ht.contains("f5a5a608"));

        ht.put("Word1 Word2", "Value Value");
        System.out.println(ht.size());
        System.out.println(ht.get("Word1 Word2"));
        ht.put("word", "Value\'");
        ht.put("drow", "Value\'\'");
        System.out.println(ht.size());
        System.out.println(ht.get("word"));
        System.out.println(ht.get("drow"));
        System.out.println(ht.size());
        System.out.println(ht.get("word"));
        System.out.println(ht.get("word"));
        System.out.println(ht.get("drow"));
        System.out.println(ht.get("Word1 Word2"));

        ht.put("0-42L", "String1");
        ht.put("0-43-", "string2");
        ht.put("0-41k", "STRING3");
        ht.put("0a42L", "value1");
        ht.put("0a43-", "Value2");
        ht.put("0a41k", "value_3");
        System.out.println(ht.size());
        System.out.println(ht.get("0-43-"));
        System.out.println(ht.get("0-42L"));
        System.out.println(ht.get("0-41k"));
        System.out.println(ht.get("0a43-"));
        System.out.println(ht.get("0a42L"));
        System.out.println(ht.get("0a41k"));
        ht.put("0-43-", "string2");
        System.out.println(ht.get("0-43-"));
        ht.put("0-43-", "NewString2");
        System.out.println(ht.get("0-43-"));
        ht.remove("0-43-");
        System.out.println(ht.size());
        ht.put("0-43-", "string2");
        System.out.println(ht.get("0-43-"));
        System.out.println(ht.size());
        ht.put("0-43-", "NewString2");
        System.out.println(ht.get("0-43-"));
        ht.remove("0-43-");
        System.out.println(ht.size());
        System.out.println(ht.get("0-43-"));
        System.out.println(ht.get("0-42L"));
        System.out.println(ht.size());
        System.out.println(ht.contains("Qord1 Word2"));
        System.out.println(ht.contains("Word1 Word2"));
        System.out.println(ht.contains("0-43-"));
        System.out.println(ht.contains("String1"));
        System.out.println(ht.contains("0-42L"));
        System.out.println(ht.contains("word"));
        System.out.println(ht.contains("Pots"));
        System.out.println(ht.contains("pOTS"));

        ht.clear();
        System.out.println(ht.contains("0-42L"));
        System.out.println(ht.contains("word"));
        System.out.println(ht.contains("Pots"));
        System.out.println(ht.size());
        System.out.println("");


        HashTable aht = new HashTable();
        System.out.println(aht.size());
        aht.put("0-42L", "str1");
        System.out.println(aht.size());
        System.out.println(aht.contains("0-42L"));
        System.out.println(ht.contains("0-42L"));
        System.out.println(ht.size());
        ht.put("0-42L", "str1");
        System.out.println(aht.size());
        System.out.println(aht.contains("0-42L"));
        System.out.println(ht.contains("0-42L"));
        System.out.println(ht.size());

        for (int i = 0; i < 200; i++) {
            aht.put(gen.nextString(), gen.nextString());
        }
        System.out.println(aht.size());
        aht.clear();
        System.out.println(aht.size());

    }


}
