package Ex3;

public class Ex3Thread extends Thread {
    private int num;

    Ex3Thread(int num){
        this.num = num;
    }   


    public void run(){
        System.out.println("Thread de número: " + num );
    }
}
