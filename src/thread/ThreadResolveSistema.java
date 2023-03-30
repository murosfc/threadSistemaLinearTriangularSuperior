package thread;

import java.util.concurrent.Semaphore;

public class ThreadResolveSistema extends Thread{		
	private Semaphore semAtual, semProximo;
	private ThreadSoma threadSoma;
	private Matriz mat;
	private int posicaoAtual;
	
	public ThreadResolveSistema(Matriz mat, Semaphore semAtual, Semaphore semProximo,ThreadSoma threadSoma) {		
		this.semAtual = semAtual;
		this.semProximo = semProximo;		
		this.threadSoma = threadSoma;
		this.threadSoma.setCalcX(this);
		this.mat = mat;
		this.posicaoAtual = mat.getN();
	}		
	
	public int getPosicaoAtual() {
		return posicaoAtual;
	}
	
	@Override
	public void run() {
		try {	
			semAtual.acquire(); // adquire o semáforo atual para iniciar a execução	
			for (int i = this.mat.getN() - 1 ; i >= 0; i--) {								
				this.posicaoAtual = i;				
				semProximo.release(); //libera o semáforo para a soma executar
				semAtual.acquire(); //retoma o semáfor após soma atualizar os valores no vetor de soma
				double xAltual = (this.mat.getB()[i] - threadSoma.getSomaNaPosicao(i)) / this.mat.getU()[i][i]; //calcula os valores de x com base no vetor soma
		        mat.setXNaPosicao(i, xAltual);		        		        
			}
			
		} catch (InterruptedException e) {			
			System.err.println("Erro ao adquirir permissão do semáforo: " +e.getMessage());
		} 
		this.printResultado();
	}
	
	private void printResultado() {
		System.out.println("\nSolucao:");
		for (int i = 0; i < this.mat.getX().length; i++) {
			System.out.println("x[" + (i+1) + "] = " + this.mat.getX()[i]);
		}
	}
}
