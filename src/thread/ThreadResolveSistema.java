package thread;

import java.util.concurrent.Semaphore;

public class ThreadResolveSistema extends Thread{
	private double[][] U;
	private double[] b, x;
	int n;
	private Semaphore semAtual;	
	
	public ThreadResolveSistema(double[][] U, double[] b, Semaphore semAtual) {
		this.U = U;
		this.b = b;
		this.semAtual = semAtual;		
		this.n = U.length;
		this.x = new double[n];
	}		
	
	
	@Override
	public void run() {
		try {
			semAtual.acquire(); // adquire o semáforo atual para iniciar a execução			
			this.x[n - 1] = b[n - 1] / U[n - 1][n - 1];			
	        for (int i = n - 2; i >= 0; i--) {
	            double temp = b[i];
	            for (int j = i + 1; j < n; j++) {
	                temp -= U[i][j] * x[j];
	            }
	            this.x[i] = temp / U[i][i];	            
	        }	        
		} catch (InterruptedException e) {			
			System.err.println("Erro ao adquirir permissão do semáforo: " +e.getMessage());
		} 
		this.printResultado();
	}
	
	private void printResultado() {
		System.out.println("Solucao:");
		for (int i = 0; i < x.length; i++) {
			System.out.println("x[" + i + "] = " + x[i]);
		}
	}
}
