//Grupo: Felipe Muros, Gabriel Siqueira, Rafael Panisset e Rafael Teixeira
package thread;

import java.util.concurrent.Semaphore;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Matriz matriz = new Matriz();
		
		
		Semaphore semPrintEquacao = new Semaphore(1);
		Semaphore semSoma = new Semaphore(0);
		Semaphore semCalcX = new Semaphore(0);
        
        ThreadExibeEquacao printEquacao = new ThreadExibeEquacao(matriz, semPrintEquacao, semCalcX);
        ThreadSoma soma = new ThreadSoma(matriz, semSoma, semCalcX);
        ThreadResolveSistema calcX = new ThreadResolveSistema(matriz, semCalcX, semSoma,  soma);
        
        printEquacao.start();       
        calcX.start();
        soma.start();
	}

}
