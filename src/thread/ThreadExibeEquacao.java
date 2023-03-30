package thread;

import java.util.concurrent.Semaphore;

public class ThreadExibeEquacao extends Thread {
	int n;
	private Semaphore semAtual, semProximo;
	Matriz mat;
	

	public ThreadExibeEquacao(Matriz mat, Semaphore semAtual, Semaphore semProximo) {
		this.mat = mat;	
		this.semAtual = semAtual;
		this.semProximo = semProximo;		
	}

	@Override
	public void run() {
		int rows = mat.getU().length; 
		int columns = mat.getU()[0].length;
		try {
			semAtual.acquire();
			System.out.println("Sistema de equacoes: ");
			for (int i = 0; i < rows;i++) { 				
					for (int j=0 ; j < columns; j++) {
						if( mat.getU()[i][j] != 0) {
							System.out.printf(mat.getU()[i][j] + "*x" + (j+1));
							if (j < columns-1 && mat.getU()[i][j+1] != 0)
								System.out.printf(" + ");
						}
				}
					System.out.printf(" = " +mat.getB()[i]);
					System.out.printf("\n");
			}
			semProximo.release();
		}catch (InterruptedException e) {			
			System.err.println("Erro ao adquirir permissão do semáforo: " +e.getMessage());
		} 
		
	}

}
