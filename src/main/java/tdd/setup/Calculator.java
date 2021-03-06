package tdd.setup;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

    private String screen = "0";

    private double latestValue = 0.0;
    private Boolean isSecound = false;

    private String latestOperation = "";

    public String readScreen() {
        return screen;
    }

    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException();

        if (latestOperation.isEmpty()) {
            screen = screen + digit;
        } else {
            if (latestValue == 0.0) {
                latestValue = Double.parseDouble(screen);

                screen = screen.startsWith("-") ? "-" + Integer.toString(digit) : Integer.toString(digit);
            }else {
                if (isSecound){
                    screen = Integer.toString(digit);
                }else {
                    screen = screen + Integer.toString(digit);
                }
            }

        }
    }

    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressOperationKey(String operation) {
        if (latestOperation.isEmpty()) {
            latestOperation = operation;
        }else {
            isSecound = true;
            latestValue = latestValue + Integer.parseInt(screen);
        }
        //screen = "0";
    }

    public void pressDotKey() {
        if (!screen.endsWith(".")) screen = screen + ".";
    }

    public void pressNegative() {

        if (screen.startsWith("-")) {
            if (latestOperation.isEmpty()) {
                screen = screen.substring(1);
            }else {
                if (latestValue == 0.0) {
                    latestValue = Double.parseDouble(screen);
                    screen = "-";
                }
            }
        } else {
            screen = "-" + screen;
        }

    }

    public void pressEquals() {
        var result = switch (latestOperation) {
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if (screen.endsWith(".0")) screen = screen.substring(0, screen.length() - 2);
    }
}
