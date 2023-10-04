import java.util.ArrayList;
import java.util.Scanner;

public class Work_with_cocktail {
    public int Menu(){
        int choice = 0;
        boolean validInput = false;
        System.out.println("Выберите действие");
        System.out.println("0-выход");
        System.out.println("1-Добавить коктейль");
        System.out.println("2-Добавить ингредиеенты в n-ый коктейль");
        while (!validInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice >= 0 & choice < 2) {
                    validInput = true;
                } else {
                    System.err.println("Ошибка: Число не должно быть больше 2 и меньше 0.");
                }
            }catch (NumberFormatException e){
                System.err.println("Ошибка: Введите корректное число.");
            }
        }
        return choice;
    }
}
