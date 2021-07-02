package helpers;

public class Game {
    public String name;
    public String price;
    public String discountPrice;
    public String tags;
    public String discountPercentage;

    public String toString(){
        return "Game name: " + this.name + "\n" + "Game price: " + this.price + "\n" + "Discount price: " + this.discountPrice
                + "\n" + "Game tags: " + this.tags + "\n" + "Discount percentage: " + this.discountPercentage;
    }
}
