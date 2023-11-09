public class IllegalArgumentException extends  Exception{

    public IllegalArgumentException() {
        super("Минимальный размер пароля не может быть меньше нуля");
    }

    public IllegalArgumentException(int maxRepeats) {
        super("Максимальное количество повторении символа подряд не может быть меньше " +
                "или равно нулю, но вы установили: " + maxRepeats);
    }
}
