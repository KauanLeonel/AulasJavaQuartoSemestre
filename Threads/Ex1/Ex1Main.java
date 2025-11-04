    package Ex1;

    public class Ex1Main {

        public static void main(String[] args) {
            
            Ex1Contador contador = new Ex1Contador();
            ExThread1 incrementador1 = new ExThread1(contador);
            ExThread1 incrementador2 = new ExThread1(contador);

            incrementador1.start();
            incrementador2.start();

        }
    }