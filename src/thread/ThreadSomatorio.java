package thread;

import java.util.concurrent.Semaphore;

public class ThreadSomatorio extends Thread {
	private double[][] U;
	private double[] b;
	int n;
	private Semaphore semAtual, semProximo;
	

	public ThreadSomatorio(double[][] U, double[] b, Semaphore semAtual, Semaphore semProximo) {
		this.U = U;
		this.b = b;
		this.n = U.length;		
		this.semAtual = semAtual;
		this.semProximo = semProximo;		
	}

	@Override
	public void run() {
		int rows = U.length; 
		int columns = U[0].length;
		try {
			semAtual.acquire();
			System.out.println("Sistema de equacoes: ");
			for (int i = 0; i < rows;i++) { 				
					for (int j=0 ; j < columns; j++) {
						if( U[i][j] != 0) {
							System.out.printf(U[i][j] + "*x" + (j+1));
							if (j < columns-1)
								System.out.printf(" + ");
						}
				}
					System.out.printf(" = " +b[i]);
					System.out.printf("\n");
			}
			semProximo.release();
		}catch (InterruptedException e) {			
			System.err.println("Erro ao adquirir permissão do semáforo: " +e.getMessage());
		} 
		
	}

}
