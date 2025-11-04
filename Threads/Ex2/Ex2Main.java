    package Ex2;

    public class Ex2Main {

        public static void main(String[] args) {
            
            Ex2Contador contador = new Ex2Contador();
            ExThread1 incrementador1 = new ExThread1(contador);
            ExThread1 incrementador2 = new ExThread1(contador);

            incrementador1.start();
            incrementador2.start();

        }
    }