import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;


public class Cocktail_data_base {

    private ArrayList<Cocktail_with_unique_items> base  = new ArrayList<>();
    private File File_name;

    public Cocktail_data_base(String File_name,ArrayList<String> logs) {
        this.File_name = new File(File_name);
        if (!this.File_name.getAbsoluteFile().exists() || this.File_name.getAbsoluteFile().length()==0) {
            boolean validInput = false;
            int amount=1;
            System.out.println("Первичное заполнение -введите количество добавляемых коктейлей");
            do {
                try {
                    Scanner scanner = new Scanner(System.in);
                    amount = scanner.nextInt();
                    if (amount > 1 & amount < 100) {
                        validInput = true;
                        logs.add("Первичное заполнение - успешно");
                    } else {
                        System.err.println("Ошибка: Число не должно быть больше 100 и меньше 0.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Введите корректное число.");
                    logs.add("Ошибка,Первичное заполнение-невероне число");
                }
            }while(!validInput);


            for (int i = 0; i < amount; i++) {
                this.base.add(new Cocktail_with_unique_items(new Random_string_generator().generate_random_string()));
            }
        } else {
            List<String> tmp_list = new Data_baze_loader().load_data_baze(this.File_name.getAbsolutePath());
            int amount_of_base = Integer.parseInt(tmp_list.get(0));
            for(int i = 1;i<amount_of_base+1;i++){
                /// бьем по пробелам
                String[] mas = tmp_list.get(i).split(" ");
                int last_index = mas.length;
                int binary_mask =Integer.parseInt(mas[last_index-1],2);
                boolean unique_load = binary_mask % 2 == 1;
                binary_mask = binary_mask >>1;
                boolean Acttions_load = binary_mask % 2 == 1;
                binary_mask = binary_mask >>1;
                boolean Ingredient_load = binary_mask % 2 == 1;
                binary_mask = binary_mask >>1;

                // запись имени
                this.base.add(new Cocktail_with_unique_items(mas[0]));
                //Запись ингердиентов
                if(Ingredient_load) {
                    String[] Ingredient_mas = mas[1].split(";");
                    for (String items : Ingredient_mas) {
                        String[] name_and_value = items.split("-");
                        this.base.get(i - 1).setIngredients(new Ingredient(name_and_value[0], Double.parseDouble(name_and_value[1])));
                    }
                }
                //Запись действий
                if(Acttions_load) {
                    String[] Acttions_mas = mas[2].split(";");
                    for (String item : Acttions_mas) {
                        this.base.get(i - 1).setActions(Actions.valueOf(item));
                    }
                }

                //Запись уникальные действие
                if(unique_load) {
                    String[] unique_mas = mas[3].split(";");
                    for (String item : unique_mas) {
                        this.base.get(i - 1).setUnigueIngridients(Unigue_ingridients.valueOf(item));
                    }
                }
            }
            logs.add("Файл загружен");
        }
    }


    public void Cocktail_add(int amount, ArrayList<String> logs) {
        int index = this.base.size() + amount;
        for (int i = this.base.size(); i < index; i++) {
            System.out.println("Введите название коктейля");
            Scanner scanner = new Scanner(System.in);
            String name_of_cocktail = scanner.nextLine();
            this.base.add( new Cocktail_with_unique_items(name_of_cocktail));
            boolean answer;

            if (answer = new New_cocktail_add_menu().Menu("уникальных ингридиенты")) {
                System.out.println("Сколько добавить уникальных игридиентов?");
                int choice = 0;
                boolean validInput = false;
                while (!validInput) {
                    try {
                        scanner = new Scanner(System.in);
                        choice = scanner.nextInt();
                        if (choice > -1 & choice < 4) {
                            validInput = true;
                            logs.add("Сколько добавить уникальных игридиентов" + choice);
                        } else {
                            System.err.println("Ошибка: Число не должно быть больше 4 и меньше -1.");
                            logs.add("Сколько добавить уникальных игридиентов? - Ошибка число не подходит" );
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                        logs.add("Сколько добавить уникальных игридиентов? - Ошибка число не подходит" );
                    }
                }
                for (int j = 0; j < choice; j++) {
                    Unigue_ingridients[] values = Unigue_ingridients.values();
                    int randomIndex = new Random().nextInt(values.length);
                    this.base.get(i).setUnigueIngridients(values[randomIndex]);
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
                            logs.add("Сколько добавить действий?" + choice);
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                        logs.add("Сколько добавить действий? - Ошибка число не подходит" );
                    }
                }
                for (int j = 0; j < choice; j++) {
                    Actions[] actions = Actions.values();
                    int randomIndex = new Random().nextInt(actions.length);
                    this.base.get(i).setActions(actions[randomIndex]);
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
                            logs.add("Сколько добавить Ингредиентов?" + choice );
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Введите корректное число.");
                        logs.add("Сколько добавить Ингредиентов?- Ошибка число не подходит" );
                    }
                }

                for (int j = 0; j < choice; j++) {

                    System.out.println("Ввeдите название ингридиента");
                    String ingredient = scanner.next();



                    System.out.println("Ввeдите обьем");

                    double value = scanner.nextDouble();


                    this.base.get(i).setIngredients(new Ingredient(ingredient,value));
                }


            }
        }
    }


    public boolean get_info_about_cocktail_by_index(int index){
        if (index>this.base.size()){
            System.out.println("Слишком большой индекс введен");
            return false;
        }
        this.base.get(index).get_info_about_cocktail();
        return true;

    }


    public void Cocktail_data_base_safe(ArrayList<String> logs){
        try {
            String encoding = "UTF-8";
            FileOutputStream fos = new FileOutputStream(this.File_name.getAbsolutePath());
            OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
            BufferedWriter writer = new BufferedWriter(osw);
            writer.write(Integer.toString(this.base.size())+'\n');
            for (int i = 0;i<this.base.size();i++){
                int result_load = 0b1;
                //запись имени
                writer.write(this.base.get(i).getName_of_cocktail()+' ');
                StringBuilder result = new StringBuilder();

                //запись ингридиентов
                if(!this.base.get(i).getIngredients().isEmpty()) {
                    for (Ingredient item : this.base.get(i).getIngredients()) {
                        result.append(item.to_string()).append(";");

                    }
                    writer.write(result.toString().trim() + ' ');
                    result.delete(0, result.length());
                    result_load = result_load << 1;
                    result_load = result_load+0b1;
                }else{result_load = result_load << 1 ;}

                //запись Действий
                if(!this.base.get(i).getActions().isEmpty()) {
                    for (Actions item : this.base.get(i).getActions()) {
                        result.append(item).append(";");
                    }
                    writer.write(result.toString().trim() + ' ');
                    result.delete(0, result.length());
                    result_load = result_load << 1;
                    result_load = result_load+0b1;
                }else{result_load = result_load << 1;}


//cNKd 1000
                //запись уникальных ингридиентов
                if (!this.base.get(i).get_UnigueIngridients().isEmpty()) {
                    for (Unigue_ingridients item : this.base.get(i).get_UnigueIngridients()) {
                        result.append(item.toString()).append(";");
                    }
                    writer.write(result.toString().trim() + ' ');
                    result.delete(0, result.length());
                    result_load = result_load << 1;
                    result_load = result_load+0b1;
                }else{result_load = result_load << 1;}


                //запись бинарного числа
                writer.write(Integer.toBinaryString(result_load) + '\n');
            }
            writer.close();
            logs.add("Файл успешно сохранен");

        } catch (IOException e) {
            System.err.println("Ошибка сохранение файла");
            logs.add("Ошибка сохранение файла");
        }

    }


    public int Get_size(){
        return this.base.size();
    }
    public void add_Unique_ingredients(int index){
        Unigue_ingridients[] values = Unigue_ingridients.values();
        int randomIndex = new Random().nextInt(values.length);
        this.base.get(index).setUnigueIngridients(values[randomIndex]);
    }

    public void del_cocktail (int index, ArrayList<String> logs){
        this.base.remove(index);
        logs.add("Коктейл удален");
    }
}
