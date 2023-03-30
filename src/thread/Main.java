package thread;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		double[][] U = { { 2, 1, 3 }, { 0, 1, -1 }, { 0, 0, 2 } };
		double[] b = { 11, 1, 4 };		
		
		Semaphore semSoma = new Semaphore(1);
		Semaphore semCalcX = new Semaphore(0);
        
        ThreadSomatorio somatorio = new ThreadSomatorio(U, b , semSoma, semCalcX);
        ThreadResolveSistema calcX = new ThreadResolveSistema(U, b , semCalcX);
        
        somatorio.start();
        calcX.start();
	}

}
