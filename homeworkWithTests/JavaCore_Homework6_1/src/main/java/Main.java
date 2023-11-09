import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            Scanner scanner = new Scanner(System.in);
            PasswordChecker passwordChecker = new PasswordChecker();
            System.out.print("Введите мин. длину пароля: ");
            passwordChecker.setMinLength(Integer.parseInt(scanner.nextLine()));
            System.out.print("Введите макс. допустимое количество повторений символа подряд: ");
            passwordChecker.setMaxRepeats(Integer.parseInt(scanner.nextLine()));

            while (true) {
                System.out.println("Введите пароль или end");
                String input = scanner.nextLine();
                if (!input.equals("end")) {
                    if (passwordChecker.verify(input)) System.out.println("Подходит!");
                    else System.out.println("Не подходит!");
                } else break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}