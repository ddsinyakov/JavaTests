package step.learning;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dict {
    private final Map<String, String> dict;

    private final Scanner scan;

    public Dict(Scanner scan) {
        dict = new HashMap<>();
        this.scan = scan;
    }

    public void show() {
        if (dict.size() == 0)
            System.out.println("Dictionary is empty");
        else {
            for (String key : dict.keySet()) {
                System.out.printf("%s -- %s\n", key, dict.get(key));
            }
        }
    }

    public void showTranslationEng() {
        System.out.print("Write your choice (English) -> ");
        String word = scan.nextLine();

        if(dict.containsKey(word)) {
            System.out.printf("%s -- %s\n", word, dict.get(word));
        } else {
            System.out.println("Unknown word");
        }
    }

    public void showTranslationUkr() {
        System.out.print("Write your choice (Ukrainian) -> ");
        String word = scan.nextLine();
        String translate = "";

        for (String key: dict.keySet())
        {
            if (word.equals(dict.get(key))) {
                translate = key;
                break;
            }
        }

        if(dict.containsValue(word)) {
            System.out.printf("%s -- %s\n", translate, word);
        } else {
            System.out.println("Unknown word");
        }
    }

    public void addWord() {
        System.out.print("Write new word (English)   -> ");
        String word = scan.nextLine();
        System.out.print("Write new word (Ukrainian) -> ");
        String translation = scan.nextLine();

        if(dict.containsKey(word))
            System.out.println("Word already exists");
        else
            dict.put(word, translation);
    }
}
