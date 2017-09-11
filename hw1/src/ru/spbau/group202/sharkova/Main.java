package ru.spbau.group202.sharkova;

public class Main {
    public static void main(String[] args) {
        HashTable ht = new HashTable();
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



    }
}
