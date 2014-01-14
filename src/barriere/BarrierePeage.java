package barriere;

import java.util.ArrayList;
import types.*;

public class BarrierePeage {

	static BarrierePeage _instance = null;
	
	private int nbrManuel;
	private int nbrTele;
	private int nbrAuto;

	private FileAttente file;
	private Traffic traffic;
	private ArrayList<Borne> listeBorne;

	private int debit_V;
	private int debit_C;
	private int debit_M;

	/**
	 * Constructeur du peage
	 * 
	 * @param lengh
	 *            le nombre de borne de chaque type sous la forme d'un tableau
	 *            de taille 3 (0=Manuel,1=Tele,2=Auto)
	 */
	public BarrierePeage(int[] lengh, int nMaxAvantBouchon, int[] nbVoitCam) {
		
		file = new FileAttente(nMaxAvantBouchon);
		
		traffic = new Traffic(nbVoitCam, file);
		
		debit_V = nbVoitCam[0];
		debit_C = nbVoitCam[1];
		debit_M = nbVoitCam[2];
		
		listeBorne = new ArrayList<Borne>();
		
		
		nbrManuel = lengh[0];
		for (int i = 0; i < nbrManuel; ++i)
			addBorne(TypeBorne.Manuel);

		nbrTele = lengh[1];
		for (int i = 0; i < nbrTele; ++i)
			addBorne(TypeBorne.Tele);

		nbrAuto = lengh[2];
		for (int i = 0; i < nbrAuto; ++i)
			addBorne(TypeBorne.Auto);
		
		
		
	/*	for(Borne borne : listeBorne)
		{
			try 
			{
				Thread.sleep(100);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			borne.start();
		}
		
*/
	}

	public static BarrierePeage getInstance(){
		if(_instance != null)
		{
			return _instance;
		}
		else{
			return null;
		}	
	}
	public static BarrierePeage getInstance(int[] lengh, int nMaxAvantBouchon, int[] nbVoitCam){
		if(_instance == null)
		{
			_instance = new BarrierePeage(lengh, nMaxAvantBouchon, nbVoitCam);
			System.out.println("bla");
			return _instance;
		}
		else{
			return null;
		}	
	}
	
	/**
	 * Adaptateur d'ajout de ArrayList<> vers ArrayList<Borne>
	 * 
	 * @param type
	 *            Le type de la borne a ajoute
	 * 
	 */
	public void addBorne(TypeBorne type) {

		Borne borne = new Borne(file, type, true);
		if (!listeBorne.add(borne))
			System.err.println("Borne non ajoutee");
		borne.start();
	}

	/**
	 * Adaptateur de suppression de ArrayList<> vers ArrayList<Borne>
	 * 
	 * @param numeroDeLaBorne
	 *            Le numero de la borne a supprimer
	 */
	
	
	public void removeBorne(int numeroDeLaBorne) {
		if (numeroDeLaBorne >= 0 && numeroDeLaBorne <= listeBorne.size())
			listeBorne.remove(numeroDeLaBorne);
		else
			System.err.println("IndexTrop Grand");
	}

	/**
	 * Permet de modifier une borne en une autre
	 * 
	 * @param numeroDeLaBorne
	 *            Le numero de la borne a modifier
	 * @param type
	 *            Le type de borne dans lequel elle sera convertie
	 */
	public void changeBorne(int numeroDeLaBorne, TypeBorne type) {

		Borne borne = new Borne(file, type, true);
		listeBorne.set(numeroDeLaBorne, borne);
	}
	
	public int getNbManuel(){
		return nbrManuel;
	}
	
	public int getNbTele(){
		return nbrTele;
	}
	
	public int getNbAuto(){
		return nbrAuto;
	}
	
	public Borne getBorne(int i) {
		return listeBorne.get(i);
		
	}
	
	public FileAttente getFileAttente() {
		return file;
	}

	public int getSize() {
		return file.size;
	}
	
	public int getDebitC() {
		return debit_C;
	}
	
	public int getDebitV() {
		return debit_V;
	}
	
	public int getDebitM() {
		return debit_M;
	}

}