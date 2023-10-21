public class Main {

    public static void main(String[] args)  {
        Calculator calc = Calculator.instanse.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        if (b != 0) {
            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } else {
            System.out.println("На ноль делить нельзя, b = 0");
        }

        secondVersion();
    }
// Программа не работает, так как в переменную с мы пытаемся записать результат деления на ноль
// Необходимо сделать проверку переменных на неравенство нулю
    public static void secondVersion(){
        Calculator calc = Calculator.instanse.get();
        int a = calc.plus.apply(1, 2);
        int b = calc.minus.apply(1, 1);
        int c;
        if (b == 0 && a != 0) {
            c = calc.devide.apply(b, a);
        } else {
            c = calc.devide.apply(a, b);
        }
        calc.println.accept(c);
    }

}


