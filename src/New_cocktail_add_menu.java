import java.util.Scanner;

public class New_cocktail_add_menu {
    public boolean Menu(String Added_string) {
        int choice = 0;

        boolean validInput = false;
        System.out.println("Вы хотите добавить " + Added_string + '?');
        System.out.println("1-Да");
        System.out.println("0-Нет");
        while (!validInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice > -1 & choice < 2) {
                    validInput = true;
                } else {
                    System.err.println("Ошибка: Число не должно быть больше 2 и меньше 1.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка: Введите корректное число.");
            }
        }
    return (choice == 1);
    }
}
