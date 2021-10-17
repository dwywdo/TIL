import java.util.concurrent.*;
public class WalkParallel{
  public static void main(String[] args){
    WalkParallel wp = new WalkParallel();
    System.out.println("오감도 - 이상");
    wp.action(13);
  }
  private void action(int size){
    System.out.printf("%d인의아해가도로를질주하오.\n", size);
    System.out.printf("(길은막다른골목이적당하오.)\n\n");
    ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    for(int i=0; i<size; i++){
      tpe.execute(new People(i+1));
    }
    tpe.shutdown();
  }
  class People implements Runnable{
    private int index;
    private Object lock = new Object();
    public People(int index){
      this.index = index;
    }
    @Override
    public void run(){
      synchronized(lock){
        try{
          lock.wait(1000L);
        }catch(InterruptedException e){
        }
        System.out.printf("제%d의아해가무섭다고그리오.\n", index);
      }
    }
  }
}
