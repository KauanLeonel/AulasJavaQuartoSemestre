package Ex1;

public class ExThread1 extends Thread{
    
    Ex1Contador cont;

    ExThread1(Ex1Contador cont){
        this.cont = cont;
    }

    public  void run(){
      
        while(cont.valor < 20000){
            cont.valor++; 
            System.out.println(cont.valor);
        }
    }
       
    
}
