package time;

import game.Base;
import game.Game;

public class BaseThread implements Runnable{

	public BaseThread() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	// Le débat ::
	//		Les Thread sont vraiment utiles pour la génération interne d'Agents !
	//		Faut-il 1 Thread pour toutes les Bases ?
	//		ou
	//		Faut-il 1 Thread pour chaque Base ?
	//
	//		1 Thread pour toute les bases : plus facile à mettre en oeuvre, moins de problemes entre processus,
	//		Chaque base mère pond leurs oeufs au meme moment des base mère en genere 1 ou 2, d'autres 5, 6, 7, selon
	//		leur vitesse. On a donc une vue sacadée de l'incrementation / génération des agents
	//
	//		1 Thread par base, --> beaucoup de thread, plus compliqué, peut engendrer des pbm entre processus ?
	//		Les bases mères pondent 1 oeuf 1 par 1 , mais certaines base le font plus rapidement que d'autres selon
	//		leur might ou puissance de génération.
	// Allez y débatez, moi j'aime bien la deux, mais si on veut etre réaliste, il vaut mieux la premiere.
	// Pour l'instant j'ai mis 1 sec la génération pour tout le monde
	@Override
	public void run() {
		while(true) {
			for(Base b : Game.getInstance().getBaseManager().getBases()) {
				b.generateAgent();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
