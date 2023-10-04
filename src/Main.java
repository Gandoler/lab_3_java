import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static <ConfigReader1, ConfigReader2> void main(String[] args)  {


        cfg_reader admin =new cfg_reader("src/cfg_admin.properties");
        cfg_reader user =new cfg_reader("src/cfg_user.properties");

        Scanner scanner= new Scanner(System.in);
        System.out.println("Введите имя пользователя:");
        String login = scanner.nextLine().trim();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine().trim();
        cfg_reader user_log_in = new cfg_reader(login,password);
        Cocktail_data_base base= new Cocktail_data_base("cocktail_base.txt");
        if ((user_log_in.equals(admin)) || (user_log_in.equals(user))){
            boolean choice=true;
            while(choice){
                Global_Menu cickl= new Global_Menu(user_log_in==admin);
                choice = cickl.Menu(base);

            }
        }
        else {
            System.out.println("Такого пользователя в базе нет");
        }



    }
}
