public class Ingredient {
    private String name_of_ingredient;
    private double quantity;

    public Ingredient(String name_of_ingredient, double quantity){
        this.name_of_ingredient = name_of_ingredient;
        this.quantity = quantity;
    }
    public Ingredient(){
        this.quantity = 0;
        this.name_of_ingredient = "";
    }

    public double getQuantity() {
        return quantity;
    }

    public String getName_of_ingredient() {
        return name_of_ingredient;
    }

    public void setName_of_ingredient(String name_of_ingredient) {
        this.name_of_ingredient = name_of_ingredient;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String to_string(){
        return  this.name_of_ingredient + '-' + this.quantity;
    }
}
