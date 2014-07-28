package mapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * 
 * @author epicstar
 *
 */
public class GenericMapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8572398461651470175L;
	private HashMap<String, String> find;
	
	public GenericMapper() {	
		find = new HashMap<String, String>();
	}
	
	private void addToMap(String cui, String string) {
		find.put(string, cui);
	}
	
	public void makeMapping(String fileInputLocation, int language, int cui, int string) {
		BufferedReader buf;
		String[] currentRow;
		try {
			buf = new BufferedReader(new FileReader(fileInputLocation));
			while (buf.ready()) {
				currentRow = buf.readLine().trim().split("|");
				if(language == -1 || currentRow[language] == "ENG") {
					addToMap(currentRow[cui], currentRow[string]);
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public final void makeMapping(String fileInputLocation, int cui, int string) {
		makeMapping(fileInputLocation, -1, cui, string);
	}
	
	public HashMap<String, String> getMapping() {
		return find;
	}
	
	public void makeSerializable(String output) {
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(output));
			out.writeObject(find);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}