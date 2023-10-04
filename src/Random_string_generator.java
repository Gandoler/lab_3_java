import java.util.Random;

public class Random_string_generator {
    private Random random = new Random();
    public String generate_random_string(){

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder random_string = new StringBuilder();
        for (int i = 0; i< random.nextInt(1,10);i++){
            int index = random.nextInt(characters.length());
            char tmp_char = characters.charAt(index);
            random_string.append(tmp_char);
        }
        return random_string.toString();
    }
}
