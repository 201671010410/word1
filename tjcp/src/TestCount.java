
public class TestCount {
	public static void main(String args[])
	{
		Count cu = new Count("test.txt");
		cu.countChar();
		cu.countWord();
		
		KeywordIdentifier key = new KeywordIdentifier("test.java");
		key.countKeyWords();
	}
}
