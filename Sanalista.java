import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sanalista {
	
	private List<String> sanat;
	
	public Sanalista(String filename){
		this.sanat = new ArrayList<String>();
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "utf-8"));
			String rivi;
			rivi = br.readLine();
			while(rivi != null){
				this.sanat.add(rivi);
				rivi = br.readLine();
			};
			br.close();
			
		} catch (IOException e){
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public Sanalista(List<String> sanat){
		this.sanat = sanat;
	}
	
	public List<String> annaSanat( ){
		return sanat;
	}
	
	public Sanalista sanatJoidenPituusOn(int pituus){
		List<String> fl = new ArrayList<String>();
		for(String sana : this.sanat){
			if(sana.length() == pituus){
				fl.add(sana);
			}
		}
		
		return new Sanalista(fl);
}
	
	public Sanalista sanaJoissaMerkit(String mjono){
		List<String> fl = new ArrayList<String>();
		
		for(String sana : this.sanat){
			if(sana.length() != mjono.length()){ 
				continue;
			}
			for(int i = 0; i < mjono.length(); i++){
				if(mjono.charAt(i) != '_'){
					if(mjono.charAt(i) != sana.charAt(i)){
						break;
					}
				}
				if(i == mjono.length()-1){
					fl.add(sana);
				}
			}	
		}
		
		return new Sanalista(fl);
}
	
}