public class PasswordChecker {
    private int minLength = -1;
    private int maxRepeats = 0;


    public void setMinLength(int minLength) throws Exception {
        this.minLength = minLength;
        if(minLength < 0) throw new IllegalArgumentException();
    }
    public int getMinLength(){
        return minLength;
    }

    public void setMaxRepeats(int maxRepeats) throws Exception{
        this.maxRepeats = maxRepeats;
        if(maxRepeats <= 0) throw new IllegalArgumentException(maxRepeats);

    }
    public int getMaxRepeats(){
        return maxRepeats;
    }

    public  boolean verify(String password) throws Exception{
        if (minLength == -1 || maxRepeats == 0) throw new IllegalStateException("Вы не установили требование для пароля");
        String[] arrayPassword = password.split("");
        return ((arrayPassword.length > minLength) && checkRepeats(arrayPassword, maxRepeats))? true : false;
    }

    public boolean verify(String password, int minLength, int maxRepeats) throws Exception{
        setMaxRepeats(maxRepeats);
        setMinLength(minLength);
        if (minLength == -1 || maxRepeats == 0) throw new IllegalStateException("Вы не установили требование для пароля");
        String[] arrayPassword = password.split("");
        return ((arrayPassword.length > minLength) && checkRepeats(arrayPassword, maxRepeats))? true : false;
    }

    private boolean checkRepeats(String password[], int maxRepeats) {
        int countOfRepeats = 0;
        int realMaxRepeats = 0;
        int[][] countsOfRepeats = new int[password.length][password.length];
        int[] arrayOfRepeats = new int[password.length];
        for (int i = 0; i < password.length; i++) {
            for (int j = 0; j < password.length; j++) {
                if (password[i].equals(password[j])) {
                    countsOfRepeats[i][j] = 1;
                }
            }
        }

        for(int[] elem : countsOfRepeats) {
            for (int i = 0; i < elem.length; i++) {
                if (elem[i] == 1) {
                    countOfRepeats++;
                    arrayOfRepeats[i] = countOfRepeats;
                } else countOfRepeats = 0;
            }
        }

        for(int elem : arrayOfRepeats) {
            if(elem > realMaxRepeats) realMaxRepeats = elem;
        }
        return (maxRepeats >= realMaxRepeats) ? true : false;

    }
}
