package ie.gmit.sw;


public class KeyEnumerator {
	
	private QuadgramMap map;
	private float highestScore = 0;
	private String closestKey;
	
	public KeyEnumerator(String filename) throws Exception {
		map = new QuadgramMap(filename);
	}
	
	private char[] getNextKey(char[] key)
	{
		for (int i = key.length - 1; i >=0; i--)
		{
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
	
	public String crackCypher(String cypherText, int maxKeyLength)
	{
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
				
				if(score > highestScore)
				{
					highestScore = score;
					closestKey = new String(key);
					//System.out.println(closestKey);
					System.out.println("Highest Score " + highestScore + ", Closest Key " + closestKey + " , result : " + result);
				}
				
			}while ((key = getNextKey(key)) != null);
		}
		System.out.println("Enumerated " + counter + " keys.");
		String yahoo = new Vigenere(closestKey).doCypher(cypherText, false);
		
		return yahoo;
	}
	
	/*public static void main(String[] args) throws Exception 
	{
		new KeyEnumerator().crackCypher("MXLMBGZ", 5);
	}*/
}