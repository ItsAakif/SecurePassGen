import java.security.SecureRandom;

public class AdvancedPasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()-+=<>?";
    private static final String AMBIGUOUS_CHAR = "l1I0O";

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java AdvancedPasswordGenerator <password_length> <include_ambiguous>");
            System.exit(1);
        }

        int passwordLength = Integer.parseInt(args[0]);
        boolean includeAmbiguous = Boolean.parseBoolean(args[1]);

        if (passwordLength <= 0) {
            System.out.println("Password length must be greater than 0.");
            System.exit(1);
        }

        String password = generatePassword(passwordLength, includeAmbiguous);
        System.out.println("Generated Password: " + password);
    }

    private static String generatePassword(int length, boolean includeAmbiguous) {
        if (length <= 0) {
            throw new IllegalArgumentException("Password length must be greater than 0.");
        }

        StringBuilder password = new StringBuilder();
        SecureRandom random = new SecureRandom();
        String charLower = CHAR_LOWER;
        String charUpper = CHAR_UPPER;
        String number = NUMBER;
        String otherChar = OTHER_CHAR;

        if (!includeAmbiguous) {
            // Remove ambiguous characters
            charLower = charLower.replaceAll("[" + AMBIGUOUS_CHAR + "]", "");
            charUpper = charUpper.replaceAll("[" + AMBIGUOUS_CHAR + "]", "");
            number = number.replaceAll("[" + AMBIGUOUS_CHAR + "]", "");
            otherChar = otherChar.replaceAll("[" + AMBIGUOUS_CHAR + "]", "");
        }

        for (int i = 0; i < length; i++) {
            int charType = random.nextInt(4); // 0: lowercase, 1: uppercase, 2: number, 3: other character

            switch (charType) {
                case 0:
                    password.append(charLower.charAt(random.nextInt(charLower.length())));
                    break;
                case 1:
                    password.append(charUpper.charAt(random.nextInt(charUpper.length())));
                    break;
                case 2:
                    password.append(number.charAt(random.nextInt(number.length())));
                    break;
                case 3:
                    password.append(otherChar.charAt(random.nextInt(otherChar.length())));
                    break;
            }
        }

        return password.toString();
    }
}
