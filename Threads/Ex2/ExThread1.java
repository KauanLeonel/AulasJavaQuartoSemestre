package Ex2;

public class ExThread1 extends Thread{
    
    Ex2Contador cont;

    ExThread1(Ex2Contador cont){
        this.cont = cont;
    }

    public  void run(){
        synchronized (cont){
        while(cont.valor < 20000){
            cont.valor++; 
            System.out.println(cont.valor);
        }
    }
       
    }
}
