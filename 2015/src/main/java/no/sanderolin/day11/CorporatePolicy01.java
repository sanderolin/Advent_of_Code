package no.sanderolin.day11;

import java.util.Set;

public class CorporatePolicy01 {

    private static final char FIRST_CHAR = 'a';
    private static final char LAST_CHAR = 'z';
    private static final Set<Character> disallowedChars = Set.of('i', 'o', 'l');

    public static void solve() {
        String newPassword = "hepxcrrq";
        while (true) {
            newPassword = incrementString(newPassword);
            if (isValidPassword(newPassword)) {
                System.out.println(newPassword);
                break;
            }
        }
    }

    private static boolean isValidPassword(String password) {
        return checkFirstRequirement(password) && checkSecondRequirement(password) && checkThirdRequirement(password);
    }

    private static boolean checkFirstRequirement(String password) {
        for (int i = 0; i < password.length() - 2; i++) {
            if (password.charAt(i + 1) == password.charAt(i) + 1 &&
                password.charAt(i + 2) == password.charAt(i) + 2)
                return true;
        }
        return false;
    }

    private static boolean checkSecondRequirement(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (disallowedChars.contains(password.charAt(i))) return false;
        }
        return true;
    }

    private static boolean checkThirdRequirement(String password) {
        int pairCount = 0;
        char lastPair = '\0';
        for (int i = 0; i < password.length() - 1; i++) {
            if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) != lastPair) {
                pairCount++;
                lastPair = password.charAt(i);
                i++;
            }
        }
        return pairCount >= 2;
    }

    private static String incrementString(String s) {
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == LAST_CHAR) {
                chars[i] = FIRST_CHAR;
            } else {
                chars[i]++;
                break;
            }
        }
        return new String(chars);
    }
}
