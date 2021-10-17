public class WalkSerial{
  public static void main(String[] args){
    WalkSerial ws = new WalkSerial();
    System.out.println("오감도 - 이상");
    ws.action(13);
  }
  private void action(int size){
    System.out.printf("%d인의아해가도로를질주하오.\n", size);
    System.out.printf("(길은막다른골목이적당하오.)\n\n");
    for(int i=0; i<size; i++){
      printMessage(i+1);
    }
  }
  private Object lock = new Object();
  private void printMessage(int index){
    synchronized(lock){
      try{
        lock.wait(1000L);
      }catch(InterruptedException e){
      }
      System.out.printf("제%d의아해가무섭다고그리오.\n", index);
    }
  }
}
