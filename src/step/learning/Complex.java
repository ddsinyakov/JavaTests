package step.learning;

import java.util.*;

public class Complex {

    public Random rand;
    public Complex() {
        rand = new Random();
    }

    private void arraysDemo() {
        int[] arr1 = new int[4];
        int[] arr2 = new int[] {5, 4, 3, 2, 1};
        int[] arr3 = {5, 4, 3, 2, 1};

        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr2[i]);
        }

        for(int num : arr3) {
            System.out.println(num);
        }

        System.out.println("==========================================");

        int[][] arr4 = {
                { 1, 2, 3 },
                { 4, 5, 6, 7 },
                { 8, 9 }
        };

        printArray(arr4);
        System.out.println("==========================================");

        int[][] arrRand = new int[5][6];
        fillArray(arrRand);
        printArray(arrRand);
        System.out.println("==========================================");
    }

    public void fillArray(int[][] toFill) {
        for (int i = 0; i< toFill.length; i++){
            for (int o = 0; o < toFill[i].length; o++){
                toFill[i][o] = rand.nextInt(38);
            }
        }
    }

    public void printArray(int[][] toShow){
        for(int[] arr : toShow){
            for(int num : arr){
                System.out.print(num + "\t");
            }
            System.out.print("\n");
        }
    }

    private void collectionDemo() {
        List<Integer> arr1 = new ArrayList<>();
        arr1.add(10);
        arr1.add(20);
        arr1.add(30);
        arr1.add(40);

        for(int num: arr1){
            System.out.print(num + " ");
        }
        System.out.print("\n");
        System.out.println("==========================================");

        arr1.set(1, 21);
        arr1.remove(3);
        for (int i = 0; i < arr1.size(); i++){
            System.out.printf("i=%d, x=%d\n", i, arr1.get(i));
        }
        System.out.println("==========================================");

        Map<String, String> map = new HashMap<>();
        map.put("Hello", "Привет");
        map.put("Bye", "Пока");
        map.put("Hi", "Здравствуйте");

        for (String key : map.keySet()){
            System.out.printf("%s -- %s\n", key, map.get(key));
        }
        System.out.println("==========================================");

        Scanner scan = new Scanner(System.in);
        System.out.println(scan.nextLine());
    }

    public void Dictionary() {
        Scanner scan = new Scanner(System.in);
        Dict dict = new Dict(scan);

        boolean exit = false;
        while(!exit) {
            System.out.println("==========================================");
            System.out.println("Choose action:");
            System.out.println("1. Show all");
            System.out.println("2. Translate from English");
            System.out.println("3. Translate from Ukrainian");
            System.out.println("4. Add word");
            System.out.println("0. Exit");
            System.out.print("Write your choice -> ");

            int result = scan.nextInt();
            scan.nextLine();

            if(result >= 0 && result <= 4)
                System.out.println("==========================================");
            else {
                System.out.println("Wrong option. Try again!");
            }

            if (result == 0)
                exit = true;

            switch (result) {
                case 1 -> dict.show();
                case 2 -> dict.showTranslationEng();
                case 3 -> dict.showTranslationUkr();
                case 4 -> dict.addWord();
            }
        }
    }

    public void Run() {
//        arraysDemo();
//        collectionDemo();
        Dictionary();
    }
}
