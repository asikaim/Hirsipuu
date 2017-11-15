import java.util.List;
import java.util.Random;

public class Hirsipuu {
	private String sana;
	private int arvausMax;
	private int arvausMaara;
	private List<Character> arvatut;
	private char[] avatut;
	
	public Hirsipuu(Sanalista sanalista, int arvausMaara){
		this.arvausMax = arvausMaara;
		this.arvausMaara = 0;
		
		Random random = new Random();
		List<String> sanat = sanalista.annaSanat();
		this.sana = sanat.get(random.nextInt(sanat.size()));
		this.avatut = new char[this.sana.length()];
		
		for(int i = 0; i < this.sana.length(); i++){
			if(this.sana.charAt(i) == '-'){
				this.avatut[i] = '-';
			}else{
				this.avatut[i] = '*';
			}
		}
	}
	
	public boolean arvaa(Character merkki){
		boolean onnistunut = false;
		for(int i = 0; i < avatut.length; i++){
			if(sana.charAt(i) == (char)Character.toLowerCase(merkki)){
				avatut[i] = merkki;
				onnistunut = true;
			}
		}
		
		if(!onnistunut)
			arvausMaara++;
		
		return onnistunut;
	}
	
	public List<Character> arvausMaara(){
		return arvatut;
	}
	
	public int arvauksiaOnJaljella(){
		return arvausMax - arvausMaara;
	}
	
	public String sana(){
		return this.sana;
	}
	
	public boolean onLoppu(){
		for(int i = 0; i < this.avatut.length; i++){
			if(this.avatut[i] == '*')
				return false;
		}
		return true;
	}

	public int getArvausMax() {
		return this.arvausMax;
	}

	public void setArvausMax(int arvausMaara) {
		this.arvausMax = arvausMaara;
	}

	public char[] getAvatut() {
		return avatut;
	}

	public void setAvatut(char[] avatut) {
		this.avatut = avatut;
	}
}