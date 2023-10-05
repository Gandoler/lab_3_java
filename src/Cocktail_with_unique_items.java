import java.util.ArrayList;
import java.util.List;

public class Cocktail_with_unique_items extends Cocktail {
    private List<Unigue_ingridients> unigueIngridients;
    public Cocktail_with_unique_items(String name_of_cocktail) {
        super(name_of_cocktail);
        unigueIngridients = new ArrayList<>();
    }
    public Cocktail_with_unique_items() {
        super(new Random_string_generator().generate_random_string());
        unigueIngridients = new ArrayList<>();
    }

    public void setUnigueIngridients(Unigue_ingridients unigueIngridients) {
        this.unigueIngridients.add(unigueIngridients);
    }
    public  List<Unigue_ingridients> get_UnigueIngridients() {
        return this.unigueIngridients;
    }

    @Override
    public void get_info_about_cocktail() {
        super.get_info_about_cocktail();
        System.out.println("\nУникальные ингредиенты:");
        for (Unigue_ingridients unique : unigueIngridients) {
            System.out.println(unique);

        }

    }

}
