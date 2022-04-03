package ru.gb.lessons.hw4;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triangle {

    private int a;
    private int b;
    private int c;
    private Colour colour;

    public Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        colour = Colour.WHITE;
    }

    private void checkSidesPositive(){
        if (a <= 0 || b <= 0 || c <= 0){
            throw new IllegalArgumentException("Side must be positive");
        }
    }

    private void checkOneSideIsSmallerThenSumOfOthers(){
        if (a + b <= c || b + c <= a || c + a <= b){
            throw new IllegalArgumentException("One side can't be greater then sum of others");
        }
    }

    public int countPerimeter(){
        checkSidesPositive();
        checkOneSideIsSmallerThenSumOfOthers();
        return a + b + c;
    }

    public void paint(Colour colour){
        if(this.colour == colour){
            throw new IllegalArgumentException("New colour must not be equal to old colour");
        }
        this.colour = colour;
    }

    public void paint(String colour){
       paint(Colour.valueOf(colour));
    }
}
