import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Global_Menu {
    private boolean access;
    private Cocktail_data_base base;
    public Global_Menu(boolean access,ArrayList<String> logs){
        this.access = access;
        this.base= new Cocktail_data_base("cocktail_base.txt", logs);

    }
    public boolean Menu(boolean access, ArrayList<String> logs){
        this.access = access;
        int choice = 0;
        boolean validInput = false;
        System.out.println("Выберите действие");
        System.out.println("1-сохранение коктейлей и выход из програмы");
        System.out.println("2-работа с коктейлями");
        while (!validInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice > 0 & choice < 3) {
                    validInput = true;
                    logs.add("выбор в меню" + choice);
                } else {
                    System.err.println("Ошибка: Число не должно быть больше 3 и меньше 1.");
                    logs.add("выбор в меню - Ошибка число не подходит" );
                }
            }catch (NumberFormatException e){
                System.err.println("Ошибка: Введите корректное число.");
                logs.add("выбор в меню - Ошибка число не подходит" );
            }
        }


        switch (choice){
            case 1:
                base.Cocktail_data_base_safe(logs);
                return false;
            case 2:
                int ans = new Work_with_cocktail().Menu(logs);
                switch (ans) {
                    case 0 -> {
                        return true;
                    }
                    case 1 -> {
                        int ans_tmp = 1;
                        boolean validInput_1 = false;
                        System.out.println("Сколько коктейлей добавить?");
                        while (!validInput_1) {
                            try {
                                Scanner scanner = new Scanner(System.in);
                                ans_tmp = scanner.nextInt();
                                if (ans_tmp > 0 & ans_tmp < 100) {
                                    validInput_1 = true;
                                    logs.add("Сколько коктейлей добавить?" + ans_tmp );
                                } else {
                                    System.err.println("Ошибка: Число не должно быть больше 100 и меньше 1.");
                                    logs.add("Сколько коктейлей добавить? - Ошибка число не подходит" );
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Ошибка: Введите корректное число.");
                                logs.add("Сколько коктейлей добавить? - Ошибка число не подходит" );
                            }
                        }
                        base.Cocktail_add(ans_tmp,logs);
                        logs.add("Новый коктейль добавлен в базу" );
                        return true;
                    }

                    case 2->{
                        Scanner scanner= new Scanner(System.in);
                        boolean valid_input = false;
                        int choice_2=0;
                        System.out.println("В каком коктейле вы хотите добавить уникальные ингредиенты?");
                        while (!valid_input){
                            try {
                                choice_2 = scanner.nextInt();
                                if(choice_2>=0 && choice<base.Get_size()){
                                    valid_input = true;
                                }
                                else{
                                    System.out.println("Число должно быть не меньше 0 и не больше количесва коктейлей");
                                }

                            }catch (InputMismatchException e){
                                System.out.println("Ошибка: Введено не число");
                            }
                        }



                        System.out.println("Сколько добавить уникальных игридиентов?");
                        int value = 0;
                        boolean validInput_1 = false;
                        while (!validInput_1) {
                            try {
                                scanner = new Scanner(System.in);
                                value = scanner.nextInt();
                                if (value > -1 & value < 4) {
                                    validInput_1 = true;
                                } else {
                                    System.err.println("Ошибка: Число не должно быть больше 4 и меньше -1.");
                                }
                            } catch (NumberFormatException e) {
                                System.err.println("Ошибка: Введите корректное число.");
                            }
                        }

                        for(int j=0;j<value;j++){
                            base.add_Unique_ingredients(choice_2);
                        }
                        return true;
                    }

                    case 3->{
                        Scanner scanner= new Scanner(System.in);
                        boolean valid_input = false;
                        int choice_2=0;
                        System.out.println("Какой коктейль вы хотите удалить?");
                        while (!valid_input){
                            try {
                                choice_2 = scanner.nextInt();
                                if(choice_2>=0 && choice<base.Get_size()){
                                    valid_input = true;
                                }
                                else{
                                    System.out.println("Число должно быть не меньше 0 и не больше количесва коктейлей");
                                }

                            }catch (InputMismatchException e){
                                System.out.println("Ошибка: Введено не число");
                            }
                        }
                        base.del_cocktail(choice_2,logs);
                        return true;
                    }
                }


            default:
                return false;
        }
    }
}
