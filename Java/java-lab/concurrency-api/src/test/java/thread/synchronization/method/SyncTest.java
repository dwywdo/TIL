package thread.synchronization.method;

import org.junit.jupiter.api.Test;

public class SyncTest {
    /**
     * 이 테스트는 사실딱히 의미가 없다..
     * 코드 상에서 디버깅을 해도 문장 단위로 실행하는 것이기 때문에
     * Happens-before 관계가 보장되는지 여기서의 값 출력이나 디버깅을 통해서 확인하기는 힘들다.
     */
    @Test
    void testSimpleCounter() {
        Counter counter = new Counter();
        Thread threadA = new Thread(new NoSyncRunnableA(counter));
        Thread threadB = new Thread(new NoSyncRunnableB(counter));
        threadA.start();
        threadB.start();
    }

    @Test
    void testSynchronizedCounter() {
        SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        Thread threadA = new Thread(new SyncRunnableA(synchronizedCounter));
        Thread threadB = new Thread(new SyncRunnableB(synchronizedCounter));
        threadA.start();
        threadB.start();
    }
}
