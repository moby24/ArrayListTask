package biz.lungo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainClass {
    private static ArrayList<String> al = new ArrayList<String>();
    private static Scanner sc = new Scanner(System.in);
    private static final int REMOVE_ELEMENT_INDEX = 1;
    private static final int REMOVE_ELEMENT_NAME = 2;
    private static final int ADD = 1;
    private static final int SHOW_COLLECTION = 2;
    private static final int REMOVE_ELEMENT = 3;
    private static final int REMOVE_RANGE = 4;
    private static final int ADD_ELEMENT_COMMA = 5;
    private static final int REMOVE_ELEMENT_COMMA = 6;
    private static final int REMOVE_ELEMENT_EXCEPT = 7;
    private static final int EXIT = 8;

    public static void main(String[] args) {

        while (true){
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить слово");
            System.out.println("2. Вывести коллекцию на экран");
            System.out.println("3. Удалить элемент");
            System.out.println("4. Удалить элементы в диапазоне");
            System.out.println("5. Добавить элементы через запятую");
            System.out.println("6. Удалить элементы через запятую");
            System.out.println("7. Удалить все элементы, кроме указанных");
            System.out.println("8. Выйти из программы");
            int choice = sc.nextInt();
            switch (choice){
                case ADD:
                    elementAdd();
                    listShow();
                    break;
            
                case SHOW_COLLECTION:
                    listShow();
                    break;
            
                case REMOVE_ELEMENT:
                    elementRemove();
                    listShow();
                    break;
            
                case REMOVE_RANGE:
                    elementRangeRemove();
                    listShow();
                    break;
            
                case ADD_ELEMENT_COMMA:
                    elementCommaAdd();
                    listShow();
                    break;
            
                case REMOVE_ELEMENT_COMMA:
                    elementCommaRemove();
                    listShow();
                    break;
            
                case REMOVE_ELEMENT_EXCEPT:
                    elementExceptRemove();
                    listShow();
                    break;
            
                case EXIT:
                    break;
            }
        }
    }

    private static void elementExceptRemove() {
        System.out.println("1. Удалить элементы по индексам");
        System.out.println("2. Удалить элементы по значениям");
        int choice = sc.nextInt();
        if (choice == REMOVE_ELEMENT_INDEX){
            System.out.println("Введите через запятую индексы элементов, которые хотите оставить в коллекции (от 0 до " + (al.size()-1) + "):");
            String input = sc.nextLine();
            String[] inputArray = splitToArray(input);
            if (inputArray == null){
                return;
            }
            ArrayList<String> tempList = new ArrayList<String>();
            for (String anInputArray : inputArray) {
                if (Integer.parseInt(anInputArray) >= 0 && Integer.parseInt(anInputArray) < al.size()) {
                    tempList.add(al.get(Integer.parseInt(anInputArray)));
                }
            }
            al.retainAll(tempList);
        }

        else if (choice == REMOVE_ELEMENT_NAME){
            System.out.println("Введите через запятую элементы, которые хотите оставить в коллекции:");
            String input = sc.nextLine();
            String[] inputArray = splitToArray(input);
            if (inputArray == null){
                return;
            }
            al.retainAll(new ArrayList<String>(Arrays.asList(inputArray)));
        }
        al.trimToSize();
    }

    private static String[] splitToArray(String input) {
        String[] inputArray = null;
        if (input.contains(", ")) {
            inputArray = input.split(", ", -2);
        }
        else if (input.contains(",")){
            inputArray = input.split(",", -2);
        }
        else{
            System.out.println("Ошибка ввода! Вы ничего не ввели, либо не разделяли элементы запятой.");
        }
        return inputArray;
    }

    private static void elementCommaRemove() {
        System.out.println("Введите через запятую слова, которые хотите удалить из коллекции:");
        String input = sc.nextLine();
        String[] inputArray = splitToArray(input);
        if (inputArray == null){
            return;
        }
        al.removeAll(new ArrayList<String>(Arrays.asList(inputArray)));
        al.trimToSize();
    }

    private static void elementCommaAdd() {
        System.out.println("Введите через запятую слова, которые хотите добавить в коллекцию:");
        String input = sc.nextLine();
        String[] inputArray = splitToArray(input);
        if (inputArray == null){
            return;
        }
        al.addAll(new ArrayList<String>(Arrays.asList(inputArray)));
    }

    private static void elementRangeRemove() {
        System.out.println("1. Удалить элементы по индексам");
        System.out.println("2. Удалить элементы по значениям");
        int choice = sc.nextInt();
        if (choice == REMOVE_ELEMENT_INDEX){
            System.out.println("Введите начальный индекс диапазона (от 0 до" + (al.size()-1) + "):");
            int startIndex = sc.nextInt();
            System.out.println("Введите конечный индекс диапазона (от 0 до" + (al.size()-1) + "):");
            int endIndex = sc.nextInt();
            removeRange(startIndex, endIndex);
        }
        else if (choice == REMOVE_ELEMENT_NAME){
            int startIndex = 0;
            int endIndex = 0;
            System.out.println("Введите начальное значение диапазона:");
            String startName = sc.next();
            if (al.contains(startName))
                startIndex = al.indexOf(startName);
            else{
                System.out.println("Нет такого слова в списке");
                elementRangeRemove();
            }
            System.out.println("Введите конечное значение диапазона:");
            String endName = sc.next();
            if (al.contains(endName))
                endIndex = al.indexOf(endName);
            else{
                System.out.println("Нет такого слова в списке");
                elementRangeRemove();
            }
            removeRange(startIndex, endIndex);
        }
        al.trimToSize();
    }

    private static void removeRange(int startIndex, int endIndex) {
        if (startIndex > endIndex){
            int tempIndex = startIndex;
            startIndex = endIndex;
            endIndex = tempIndex;
        }
        if (endIndex > al.size()-1 || startIndex < 0){
            System.out.println("Индексы выходят за границы коллекции.");
            elementRangeRemove();
        }
        List<String> subList = al.subList(startIndex, endIndex);
        al.removeAll(subList);
    }

    private static void elementRemove() {
        System.out.println("1. Удалить элемент по индексу");
        System.out.println("2. Удалить элемент по значению");
        int choice = sc.nextInt();
        if (choice == REMOVE_ELEMENT_INDEX){
            System.out.println("Введите индекс элемента, который хотите удалить. От 0 до " + (al.size()-1));
            int input = sc.nextInt();
            if (input >= 0 && input < (al.size()-1)){
                al.remove(input);
            }
            else {
                System.out.println("Неверный индекс элемента");
                elementRemove();
            }
        }
        else if (choice == REMOVE_ELEMENT_NAME){
            System.out.println("Введите значение, которое хотите удалить");
            String input = sc.next();
            al.remove(input);
        }
        al.trimToSize();
    }

    private static void listShow() {
        System.out.println(al);
        System.out.println();
    }

    private static void elementAdd() {
        System.out.println("Введите то, что хотите добавить:");
        String input = sc.next();
        al.add(input);
    }
}
