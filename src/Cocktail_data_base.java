import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Cocktail_data_base {
    private Cocktail_with_unique_items[] cocktail_list ;
    private File File_name;

    public Cocktail_data_base(int amount,String File_name) {
        if (amount < 1) {
            throw new IllegalArgumentException("Число должно быть больше 0 при создании класса");
        }
        this.File_name = new File(File_name);
        if (!this.File_name.exists() || this.File_name.length()==0) {
            for (int i = 0; i < amount; i++) {
                this.cocktail_list[i] = new Cocktail_with_unique_items(new Random_string_generator().generate_random_string());
            }
        } else {
            List<String> tmp_list = new Data_baze_loader().load_data_baze(this.File_name.getAbsolutePath());
            int amount_of_base = Integer.parseInt(tmp_list.get(0));
            for (int i = 0; i < amount_of_base; i+=4) {
                this.cocktail_list[i] = new Cocktail_with_unique_items(tmp_list.get(i+1));
                String [] actions_from_file = tmp_list.get(i+2).split(" ");
                for(int j=0; j<actions_from_file.length;j++){
                    this.cocktail_list[i].setActions(Actions.valueOf(actions_from_file[j]));
                }
                String [] ingredients_from_file = tmp_list.get(i+3).split(" ");
                for(int j=0; j<ingredients_from_file.length;j++){
                    String[] tmp_for_ingredients =ingredients_from_file[j].split("-");
                    this.cocktail_list[i].setIngredients(new Ingredient(tmp_for_ingredients[0],Double.parseDouble(tmp_for_ingredients[1]) ));
                }
                String[] unique_ingredients_from_file = tmp_list.get(i+4).split(" ");
                for(int j=0; j<unique_ingredients_from_file.length;j++){
                    this.cocktail_list[i].setUnigueIngridients(Unigue_ingridients.valueOf(unique_ingredients_from_file[j]));
                }
            }
            for (int i = this.cocktail_list.length; i < this.cocktail_list.length+amount; i++) {
                this.cocktail_list[i] = new Cocktail_with_unique_items(new Random_string_generator().generate_random_string());
            }
        }
    }


    public void Cocktail_add(int amount) {
        int index = this.cocktail_list.length + amount;
        for (int i = this.cocktail_list.length; i < index; i++) {
            System.out.println("Введите название коктейля");
            Scanner scanner = new Scanner(System.in);
            String name_of_cocktail = scanner.nextLine();
            this.cocktail_list[i] = new Cocktail_with_unique_items(name_of_cocktail);
            boolean answer;

            if (answer = new New_cocktail_add_menu().Menu("уникальных ингридиенты")) {
                System.out.println("Сколько добавить ингредиентов?");
                int choice = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        scanner = new Scanner(System.in);
                        choice = scanner.nextInt();
                        if (choice > -1 & choice < 4) {
                            validInput = true;
                        } else {
                            System.err.println("Ошибка: Число не должно быть больше 4 и меньше -1.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                    }
                }
                for (int j = 0; j < choice; j++) {
                    Unigue_ingridients[] values = Unigue_ingridients.values();
                    int randomIndex = new Random().nextInt(values.length);
                    this.cocktail_list[i].setUnigueIngridients(values[randomIndex]);
                }
            }

            if (answer = new New_cocktail_add_menu().Menu("действия")) {
                System.out.println("Сколько добавить действий?");
                int choice = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        scanner = new Scanner(System.in);
                        choice = scanner.nextInt();
                        if (choice > -1 & choice < 4) {
                            validInput = true;
                        } else {
                            System.err.println("Ошибка: Число не должно быть больше 4 и меньше -1.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                    }
                }
                for (int j = 0; j < choice; j++) {
                    Actions[] actions = Actions.values();
                    int randomIndex = new Random().nextInt(actions.length);
                    this.cocktail_list[i].setActions(actions[randomIndex]);
                }


            }
            if (answer = new New_cocktail_add_menu().Menu("Ингредиенты")) {
                System.out.println("Сколько добавить Ингредиентов?");
                int choice = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        scanner = new Scanner(System.in);
                        choice = scanner.nextInt();
                        if (choice > 0 & choice < 4) {
                            validInput = true;
                        } else {
                            System.err.println("Ошибка: Число не должно быть больше 4 и меньше 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                    }
                }

                for (int j = 0; j < choice; j++) {
                    String ingredient = scanner.nextLine();
                    Double value = scanner.nextDouble();
                    this.cocktail_list[i].setIngredients(new Ingredient(ingredient,value));
                }


            }
        }
    }


    public boolean get_info_about_cocktail_by_index(int index){
        if (index>this.cocktail_list.length){
            System.out.println("Слишком большой индекс введен");
            return false;
        }
        this.cocktail_list[index].get_info_about_cocktail();
        return true;

    }


    public void Cocktail_data_base_safe(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.File_name.getAbsolutePath()))){
            writer.write(this.cocktail_list.length);
            for (int i = 0;i<this.cocktail_list.length;i++){
                writer.write(this.cocktail_list[i].getName_of_cocktail());
                StringBuilder result = new StringBuilder();
                for (Actions item : this.cocktail_list[i].getActions()) {
                    result.append(item).append(" ");
                }
                writer.write(result.toString().trim());
                result.delete(0,result.length());
                for (Ingredient item : this.cocktail_list[i].getIngredients()){
                    result.append(item.to_string()).append(" ");
                }
                writer.write(result.toString().trim());
                result.delete(0,result.length());
                for (Unigue_ingridients item : this.cocktail_list[i].get_UnigueIngridients()){
                    result.append(item.toString()).append(" ");
                }
                writer.write(result.toString().trim());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
