package helpers;

public class Game {
    String name;
    String price;
    String discountPrice;
    String tags;
    String discountPercentage;

    public String toString(){
        return "Game name: " + this.name + "\n" + "Game price: " + this.price + "\n" + "Discount price: " + this.discountPrice
                + "\n" + "Game tags: " + this.tags + "\n" + "Discount percentage: " + this.discountPercentage;
    }
}
