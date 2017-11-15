public class Main {
	
	public static void main(String[] args){
		
		String filename = "sanalista.txt";
		int maara = 8;
		
		Sanalista sanalista = new Sanalista(filename);
		Hirsipuu hirsipuu = new Hirsipuu(sanalista, maara);
		
		GUI kayttis = new GUI(hirsipuu);
		kayttis.start();
	}
}