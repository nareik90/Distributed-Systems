package ie.gmit.sw;


public class KeyEnumerator {
	
	private Vigenere cypher;
	private QuadgramMap map = null;
	private String bestKey;
	private float bestScore;
	
	public KeyEnumerator(String filename) throws Exception {
		map = new QuadgramMap(filename);
	}
	
	private char[] getNextKey(char[] key){
		for (int i = key.length - 1; i >=0; i--){
			if (key[i] =='Z'){
				if (i == 0) return null;
				key[i] = 'A';
			}else{
				key[i]++;
				break;
			}
		}
		return key;
	}
	
	
	public String crackCypher(String cypherText, int maxKeyLength){
		char[] key = null;
		
		int counter = 0;
		for (int j = 3; j <= maxKeyLength; j++){
			key = new char[j];
			for (int k = 0; k < key.length; k++) key[k] = 'A';
			
			do{
				counter++;
				String result = new Vigenere(new String(key)).doCypher(cypherText, false);
				float score = map.getScore(result);
				//System.out.println(score);
				
				if (score > bestScore) {
					bestKey = new String(key);
					bestScore = score;
					System.out.println("Best SCore: " + bestScore + " bestKey: " + " result:" + result);
				}
				
				//System.out.println(result);
				//System.out.println(new String(key));
				
			}while ((key = getNextKey(key)) != null);
		}
		
		System.out.println("Enumerated " + counter + " keys.");
		System.out.println("bestKEY: " + bestKey);
		String yahoo = new Vigenere(bestKey).doCypher(cypherText, false);
		
		return yahoo;
	}
	
	public static void main(String[] args) throws Exception {
		//test to see if it will decipher 
//		new KeyEnumerator("./WarAndPeace.txt").crackCypher("MABLBLMAXNEMBFTMXMXLMHYYTMX", 5);
		new KeyEnumerator("./WarAndPeace.txt").crackCypher("GTBWULGJAWFLWVSKKAYFEWFL", 5);
	}
}
