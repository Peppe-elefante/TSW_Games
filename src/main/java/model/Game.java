package model;

public class Game {
    private int id;
    private String name;
    private String category;

    private int price;
    private int quantity;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public String getCategory(){
        return category;
    }

    public int getPrice() {
        return price;
    }
    public int getQuantity(){return quantity;}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public void setQuantity(int quantity){this.quantity = quantity;}
}
