import java.util.Scanner;
public class Main {

    public static void main(String[] args) throws CalcException {


        Scanner scanIn = new Scanner(System.in);
        System.out.println("Введите арабские или римские числа от 1 до 10 и нужную операцию (*,/,-,+)");
        String str0 = scanIn.nextLine();  //принимаем от пользователя строку
        String str1 = str0.toUpperCase(); //правим регистр
        String str2 = str1.replaceAll(" ", ""); // заменяем пробелы

        System.out.println(LogicInside(str2));
    }

    public static String LogicInside(String str2) throws CalcException {
        int result = 0;
        String a = "";
        String b = "";
        boolean a_arab = true; //"маркеры" типа чисел
        boolean b_arab = true;
        char ch = ' '; //тут храним пробел или будущий знак
        for (int i = 0; i < str2.length(); i++) { //проходим по всей длине строки str2
            switch (str2.charAt(i)) {
                case 'I': case 'V': case 'X':
                    if (ch == ' ') {      //проверка на первое или второе число до знака
                        a += str2.charAt(i);
                        a_arab = false; //выключаем арабские числа, если нашли римскую

                        int at = RomanToArabian(a);
                        if (at > 10) {
                            throw new CalcException("Введено значение больше 10");
                        }
                    } else {
                        b += str2.charAt(i);
                        b_arab = false;

                        int bt = RomanToArabian(b);
                        if (bt > 10) {
                            throw new CalcException("Ошибка! Второе значение больше 10.");
                        }
                    }
                    break;

                case '/': case '*': case '-': case '+':
                    if (ch == ' ') {
                        ch = str2.charAt(i);
                    } else {
                        throw new CalcException("Ошибка! Математических знаков больше одного.");
                    }
                    break;

                case '.': case ',':
                    throw new CalcException("Ошибка! Допустимы только целые числа.");


                default:
                    try {if (ch == ' ') {
                        a += str2.charAt(i);

                        int at = Integer.parseInt(a.trim());
                        if (at > 10) {
                            throw new CalcException("Ошибка! Первое значение больше 10.");
                        }
                    } else {
                        b += str2.charAt(i);

                        int bt = Integer.parseInt(b.trim());
                        if (bt > 10) {
                            throw new CalcException("Ошибка! Второе значение больше 10.");
                        } break;
                    }

                    } catch (NumberFormatException e) {
                        throw new CalcException("Ошибка! Недопустимый символ.");
                    }
            }
        }

        if (ch == ' ') {
            throw new CalcException("Ошибка! Нет математических знаков.");
        } else {
            if (a_arab && b_arab) {
                int at = Integer.parseInt(a.trim());
                int bt = Integer.parseInt(b.trim());

                result = calc(at, bt, ch);
                String resultStr = Integer.toString(result);

                return resultStr;
            }

            if (!a_arab && !b_arab) {
                int at = RomanToArabian(a);
                int bt = RomanToArabian(b);

                result = calc(at, bt, ch);
                if (result < 0) {
                    throw new CalcException("Ошибка! Римские цифры не могут быть отрицательными.");
                }

              return ConvertArabianToRoman(result);
            }

            if ((a_arab && !b_arab) || (!a_arab && b_arab)) {
                throw new CalcException("Ошибка! Разные типы значений.");
            }
        }
        return null;


    }

    private static String ConvertArabianToRoman (int arabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        return roman[arabian];
    }

    public static int calc (int a, int b, char c){
        int result = 0;
        switch (c) {
            case '/': result = a/b; break;

            case '*': result = a*b; break;

            case '-': result = a-b; break;

            case '+': result = a+b; break;
        }
        return result;
    }

    private static int RomanToArabian (String roman) throws CalcException {
        int num = 0;
        switch (roman) {
            case "I": num = 1; break;
            case "II": num = 2; break;
            case "III": num = 3; break;
            case "IV": num = 4; break;
            case "V": num = 5; break;
            case "VI": num = 6; break;
            case "VII": num = 7; break;
            case "VIII": num = 8; break;
            case "IX": num = 9; break;
            case "X": num = 10; break;
            default:
                throw new CalcException("Ошибка! Римские цифры не могут быть отрицательными.");
        }

        return num;




    }

    public static class CalcException extends Exception {
        public CalcException(String text) {super(text);}

    }

}
