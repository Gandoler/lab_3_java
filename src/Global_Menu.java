import java.util.Scanner;

public class Global_Menu {
    private boolean access;
    public Global_Menu(boolean access){
        this.access = access;
    }
    public boolean Menu(Cocktail_data_base base){
        int choice = 0;
        if(this.access){
            boolean validInput = false;
            System.out.println("Выберите действие");
            System.out.println("1-сохранение коктейлей и выход из програмы");
            System.out.println("2-работа с коктейлями");
            while (!validInput) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    if (choice > 1 & choice < 3) {
                        validInput = true;
                    } else {
                        System.err.println("Ошибка: Число не должно быть больше 3 и меньше 1.");
                    }
                }catch (NumberFormatException e){
                    System.err.println("Ошибка: Введите корректное число.");
                }
            }
        }
        else{
            boolean validInput = false;
            System.out.println("Выберите действие");
            System.out.println("1-сохранение коктейлей");
            System.out.println("2-работа с коктейлями");
            System.out.println("3-режим отладки");
            System.out.println("4-режим автотестов");
            while (!validInput) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    choice = scanner.nextInt();
                    if (choice > 0 & choice < 3) {
                        validInput = true;
                    } else {
                        System.err.println("Ошибка: Число не должно быть больше 2 и меньше 1.");
                    }
                }catch (NumberFormatException e){
                    System.err.println("Ошибка: Введите корректное число.");
                }
            }
        }
        switch (choice){
            case 1:
                base.Cocktail_data_base_safe();
                return false;
            case 2:
                int ans = new Work_with_cocktail().Menu();
                switch (ans) {
                    case 0 -> {
                        return true;
                    }
                    case 1 -> {
                        int ans_tmp = 1;
                        boolean validInput = false;
                        System.out.println("Сколько коктейлей добавить?");
                        while (!validInput) {
                            try {
                                Scanner scanner = new Scanner(System.in);
                                ans_tmp = scanner.nextInt();
                                if (ans_tmp > 0 & ans_tmp < 100) {
                                    validInput = true;
                                } else {
                                    System.err.println("Ошибка: Число не должно быть больше 100 и меньше 1.");
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Ошибка: Введите корректное число.");
                            }
                        }
                        base.Cocktail_add(ans_tmp);
                        return true;
                    }
                }


            case 3:
                return true;

            case 4:
                return true;

            default:
                return false;
        }
    }
}
