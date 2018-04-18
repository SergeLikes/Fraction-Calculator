import java.util.*;

public class Fraction {
    private int numerator;
    private int denominator;
        public Fraction(int numerator, int denominator){
            if (denominator == 0) {
                throw new IllegalArgumentException("denominator can't be 0");
            }
            else if((denominator < 0 && numerator > 0) || (denominator < 0 && numerator < 0)) {
                this.numerator = -numerator;
                this.denominator = -denominator;
            }
            else {
                this.numerator = numerator;
                this.denominator = denominator;
            }
        }
        public Fraction(int numerator){ //constructor when denominator =1; numerator !=0
                this.numerator = numerator;
                this.denominator = 1;
           }
        public Fraction(){ //constructor when denominator =1; numerator 0=0
            this.numerator = 0;
            this.denominator = 1;
        }
    public int getNumerator(){
        return numerator;
    }
    public int getdenominator(){
        return denominator;
    }
    public String toString(){
        if((this.numerator % this.denominator) != 0) {
            return this.numerator + "/" + this.denominator;
        }
        return String.valueOf(this.numerator/this.denominator);
    }
    public double toDouble(){  // this function wasn't used in this exercise
        return (double)numerator/(double)denominator;
    }
    public Fraction add(Fraction other){
        int a = this.numerator*other.denominator + other.numerator*this.denominator;
        int b = this.denominator*other.denominator;
        return new Fraction(a, b);
    }
    public Fraction subtract(Fraction other){
        int a = this.numerator*other.denominator - other.numerator*this.denominator;
        int b = this.denominator*other.denominator;
        return new Fraction(a, b);
    }
    public Fraction multiply(Fraction other){
        int a = this.numerator*other.numerator;
        int b = this.denominator*other.denominator;
        return new Fraction(a, b);
    }
    public Fraction divide(Fraction other){ //make sure that you don't divide by 0!!!
        int a = this.numerator*other.denominator;
        int b = this.denominator*other.numerator;
        if(b==0){
            throw new IllegalArgumentException("denominator can't be 0");
        }
        return new Fraction(a, b);
    }
    public boolean equals(Object other){
        if (!(other instanceof Fraction)) {
            return false;
        }
        Fraction otherFr = (Fraction) other;
        if(this.numerator*otherFr.denominator == otherFr.numerator*this.denominator){
            return true;
        }
        return false;
    }
    private int gcd(int a, int b){
        if(b==0) {
            return a;
        }
        else {
            return gcd(b, a % b);
        }
    }
    public void toLowestTerms(){
        int commDivisor = gcd(numerator, denominator);
        numerator = numerator / commDivisor;
        denominator = denominator / commDivisor;
    }
}
