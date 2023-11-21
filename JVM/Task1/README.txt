#Домашнее задание "Понимание JVM"
public class JvmComprehension { //Подгружается класс JvmComprehension в Metaspace, при помощи  Bootstrat ClassLoader, если он не находит то делегирует Зlatform, а он делегирует Аpllicatoin 

    public static void main(String[] args) { // создается фрейм main в стеке(Stack Memory)
        int i = 1;                      // создается переменная i в стеке во фрейме main
        Object o = new Object();        // создается Object в куче(heap), а во фрейме сохраняется ссылка о на этот объект
        Integer ii = 2;                 // 2 создается в куче и во фрейм сохраняется ссылка на нее
        printAll(o, i, ii);             // создается в стеке новый фрейм printAll
        System.out.println("finished"); // 7
    }

    private static void printAll(Object o, int i, Integer ii) { // Во фрейм printAll полностью копируется i, и передаются ссылки на o и ii, которые находятся в куче
        Integer uselessVar = 700;                   // 700 сохраняется в куче, а ссылка uselessVar создается в фрейме printAll
        System.out.println(o.toString() + i + ii);  // создается фрейм sout, и в него копируется i, и передаются ссылки на o и ii
    }
}
