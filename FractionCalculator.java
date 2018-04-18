import java.util.*;
public class FractionCalculator {
    public static void main(String[] Args){

        Fraction firstFraction = null; //declaring class object for later use
        Fraction secondFraction = null;
        Fraction fractionResult = null;

        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit.");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers");
        System.out.println("--------------------------------------------------------------------------------");

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
        String operation = getOperation(input);

        while(!operation.equals("q") && !operation.equals("Q")) {
// -----------------------------------------------------------------
// Testing first fraction.
            System.out.print("Please enter a fraction (a/b) or integer (a):");
            String FractionStr = getFraction(input);
            while (!validFraction(FractionStr)) {
                System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
                FractionStr = getFraction(input);
            }
// First fraction is indeed valid. Creating first object (Fraction)
            int negativeNumber = 1;
            int strLength = FractionStr.length();
            if (FractionStr.indexOf('/') < 0) { // only the numerator was entered
                if (FractionStr.charAt(0) == '-') { // in case our numerator is negative, we cut the negative sign out
                    negativeNumber = -1;
                    FractionStr = FractionStr.substring(1, strLength);
                }
                int numValue = Integer.parseInt(FractionStr) * negativeNumber;
                if (numValue != 0) { // so our denominator = 1; numerator either 0 or a digit
                    firstFraction = new Fraction(numValue);
                } else {
                    firstFraction = new Fraction();
                }
                negativeNumber = 1; //our numerator was negative, we instantiated an object and reset the variable for later use
            } else { //the string that was entered contains both the numerator and denominator.
                if (FractionStr.charAt(0) == '-') { // lazy code example. this part (picking the negative sign) is repeated the third time!
                    negativeNumber = -1;
                    FractionStr = FractionStr.substring(1, strLength);
                    strLength = FractionStr.length();
                }
                String numString = FractionStr.substring(0, FractionStr.indexOf('/')); // substring that contains the numerator
                String denomString = FractionStr.substring(FractionStr.indexOf('/') + 1, strLength);
                int numValue = Integer.parseInt(numString) * negativeNumber;        // transform that substring into integer
                int denomValue = Integer.parseInt(denomString);
                firstFraction = new Fraction(numValue, denomValue);
                negativeNumber = 1;
            }
// -----------------------------------------------------------------
// Input for the second fraction
            System.out.print("Please enter a fraction (a/b) or integer (a):");
            FractionStr = getFraction(input);
            while (!validFraction(FractionStr)) {
                System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
                FractionStr = getFraction(input);
            }
// Second fraction is also valid. Creating second object (Fraction)
            strLength = FractionStr.length();
            if (FractionStr.indexOf('/') < 0) { // only the numerator was entered
                if (FractionStr.charAt(0) == '-') { // in case our numerator is negative, we cut the negative sign out
                    negativeNumber = -1;
                    FractionStr = FractionStr.substring(1, strLength);
                }
                int numValue = Integer.parseInt(FractionStr) * negativeNumber;
                if (numValue != 0) { // // so our denominator = 1; numerator either 0 or a digit
                    secondFraction = new Fraction(numValue);
                } else {
                    secondFraction = new Fraction();
                }
                negativeNumber = 1; //our numerator was negative, we instantiated an object and reset the variable for later use
            } else { //the string that was entered contains both the numerator and denominator.
                if (FractionStr.charAt(0) == '-') { // lazy code example. this part (picking the negative sign) is repeated several times
                    negativeNumber = -1;
                    FractionStr = FractionStr.substring(1, strLength);
                    strLength = FractionStr.length();
                }
                String numString = FractionStr.substring(0, FractionStr.indexOf('/')); // substring that contains the numerator
                String denomString = FractionStr.substring(FractionStr.indexOf('/') + 1, strLength);
                int numValue = Integer.parseInt(numString) * negativeNumber;        // transform that substring into integer
                int denomValue = Integer.parseInt(denomString);
                secondFraction = new Fraction(numValue, denomValue);
                negativeNumber = 1;
            }
// -----------------------------------------------------------------
            if(operation.equals("+")) {
                fractionResult = firstFraction.add(secondFraction);
                fractionResult.toLowestTerms();
                System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " + " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " = " + fractionResult.toString());
            }
            else if(operation.equals("-")){
                fractionResult = firstFraction.subtract(secondFraction);
                fractionResult.toLowestTerms();
                System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " - " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " = " + fractionResult.toString());
            }
            else if(operation.equals("/")){
                if(secondFraction.getNumerator()!=0) {
                    fractionResult = firstFraction.divide(secondFraction);
                    fractionResult.toLowestTerms();
                    System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " / " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " = " + fractionResult.toString());
                }
                else {
                    System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " / 0 = Undefined");
                }
            }
            else if(operation.equals("*")){
                fractionResult = firstFraction.multiply(secondFraction);
                fractionResult.toLowestTerms();
                System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " * " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " = " + fractionResult.toString());
            }
            else if(operation.equals("=")){
                if(firstFraction.equals(secondFraction)){
                    System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " = " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " is true");
                }
                else{
                    System.out.println(firstFraction.getNumerator() + "/" + firstFraction.getdenominator() + " = " + secondFraction.getNumerator() + "/" + secondFraction.getdenominator() + " is false");
                }
            }
            System.out.print("Please enter an operation (+, -, /, *, = or Q to quit): ");
            operation = getOperation(input);
        }
        System.out.print("Bye! Have a nice day");
    }
    public static String getOperation(Scanner input){
        String operation = input.nextLine();
        while (!operation.equals("+") && !operation.equals("-") && !operation.equals("/") && !operation.equals("*") && !operation.equals("=") && !operation.equals("q") && !operation.equals("Q")){
            System.out.print("Invalid input (+, -, /, *, = or Q to quit): ");
            operation = input.nextLine();
        }
        return operation;
    }
    public static boolean validFraction(String input){
        String num = ""; // temporary variable for numerator
        String denom = ""; // temporary variable for denominator
        int negativeNumber = 1; // in case first character is negative, we update this variable and get rid of the character
        int strLength = input.length();
        if (input.charAt(0)=='-'){ // getting rid of '-'
            negativeNumber = -1;
            input = input.substring(1, strLength);
            strLength = input.length();
        }
        int slashLoc = input.indexOf('/');
        if(slashLoc > 0){ // break down our string into numerator and denominator if possible
            num = input.substring(0,slashLoc);
            denom = input.substring(slashLoc+1,strLength);
        }
        else{
            num = input;
        }
// Check whether we got real numbers
        if (slashLoc < 0 && isNumber(num) ) { // there were no '/' character or other characters, only integers
            return true;
        }
        else if(slashLoc>0 && isNumber(num) && isNumber(denom)){ //we have two proper integers
            int denomi = Integer.parseInt(denom);
            if(denomi ==0){  // can't divide by zero
                return false;
            }
            else{
                return true;
            }
        }
        return false;
    }
    public static boolean isNumber(String input){
        boolean notNumber = false;
        int strLength = input.length();
        for (int i = 0; i < strLength; i++){
            if(input.charAt(i)!='0' && input.charAt(i)!='1' && input.charAt(i)!='2' && input.charAt(i)!='3' && input.charAt(i)!='4' && input.charAt(i)!='5' && input.charAt(i)!='6' && input.charAt(i)!='7' && input.charAt(i)!='8' && input.charAt(i)!='9'){
                notNumber = true;
                i = strLength; // once we encounter non-digit character, there is no need to run the cycle
            }
        }
        if(strLength > 1 && input.charAt(0)=='0'){ // string like "0123" is considered an invalid input
            notNumber = true;
        }
        else if(strLength == 0){ // empty string means not a number
            notNumber = true;
        }
        if (notNumber){
            return false;
        }
        else{
            return true;
        }
    }
    public static String getFraction(Scanner input){
        String fractionString = input.nextLine();
        while (fractionString.length()==0){
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            fractionString = input.nextLine();
        }
        return fractionString;
    }
}
