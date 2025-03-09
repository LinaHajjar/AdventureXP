package org.example.adventurexp.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "candy")
public class Candy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candy_id;

    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public int getCandyId() {
        return candy_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
