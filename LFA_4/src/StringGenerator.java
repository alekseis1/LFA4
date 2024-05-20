import java.util.Random;

public class StringGenerator {
    public static String generateString(String rule) {
        StringBuilder string = new StringBuilder();
        int i = 0;
        Random random = new Random();

        while (i < rule.length()) {
            if (rule.charAt(i) == '(' && rule.indexOf(")", i) == rule.length() - 1
                    || (rule.charAt(i) == '(' && !isSpecialCharacter(rule.charAt(rule.indexOf(")", i) + 1)))) {
                String options = rule.substring(i + 1, rule.indexOf(")", i));
                char chosenChar = choice(options.split("\\|"));
                string.append(chosenChar);
                i = rule.indexOf(")", i);
                System.out.println("Just one occurrence from options: Adding " + chosenChar + " to string => " + string);
            } else if (rule.charAt(i) == '(' && rule.charAt(rule.indexOf(")", i) + 1) == '+') {
                int times = random.nextInt(5) + 1;
                for (int j = 0; j < times; j++) {
                    String options = rule.substring(i + 1, rule.indexOf(")", i));
                    char chosenChar = choice(options.split("\\|"));
                    string.append(chosenChar);
                    System.out.println("One or more occurrences from options: Adding " + chosenChar + " to string => " + string);
                }
                i = rule.indexOf(")", i) + 1;
            } else if (rule.charAt(i) == '(' && rule.charAt(rule.indexOf(")", i) + 1) == '*') {
                int times = random.nextInt(6);
                for (int j = 0; j < times; j++) {
                    String options = rule.substring(i + 1, rule.indexOf(")", i));
                    char chosenChar = choice(options.split("\\|"));
                    string.append(chosenChar);
                    System.out.println("Zero or more occurrences from options: Adding " + chosenChar + " to string => " + string);
                }
                i = rule.indexOf(")", i) + 1;
            } else if (rule.charAt(i) == '(' && rule.charAt(rule.indexOf(")", i) + 1) == '{') {
                int fixedTimes = Character.getNumericValue(rule.charAt(rule.indexOf("{", i) + 1));
                String options = rule.substring(i + 1, rule.indexOf(")", i));
                for (int j = 0; j < fixedTimes; j++) {
                    char chosenChar = choice(options.split("\\|"));
                    string.append(chosenChar);
                    System.out.println("Fixed occurrences from options: Adding " + chosenChar + " to string => " + string);
                }
                i = rule.indexOf("}", i) + 1;
            } else if (rule.charAt(i) == '(' && rule.charAt(rule.indexOf(")", i) + 1) == '?') {
                if (random.nextInt(2) == 1) {
                    String options = rule.substring(i + 1, rule.indexOf(")", i));
                    char chosenChar = choice(options.split("\\|"));
                    string.append(chosenChar);
                    System.out.println("Zero or one occurrence from options: Adding " + chosenChar + " to string => " + string);
                }
                i = rule.indexOf(")", i) + 1;
            } else if (i < rule.length() - 2 && rule.charAt(i + 1) == '?') {
                if (random.nextInt(2) == 1) {
                    string.append(rule.charAt(i));
                    System.out.println("Zero or one occurrence: Adding " + rule.charAt(i) + " to string => " + string);
                }
                i += 2;
            } else if ("(){|+*?}".indexOf(rule.charAt(i)) != -1) {
                i += 1;
            } else {
                string.append(rule.charAt(i));
                System.out.println("Adding " + rule.charAt(i) + " to string => " + string);
                i += 1;
            }
        }
        return string.toString();
    }

    public static char choice(String[] options) {
        Random random = new Random();
        int index = random.nextInt(options.length);
        return options[index].charAt(0);
    }

    public static boolean isSpecialCharacter(char c) {
        return "(){|+*?}".indexOf(c) != -1;
    }
}