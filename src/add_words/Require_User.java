package add_words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * This class updates the file to give cadets access to the
 * Cadets portion of the website.
 * 
 * @author <pre>Travis Carson, C/Capt, AFROTC</pre>
 * <pre>Technology Officer, Detachment 330</pre>
 * <pre>February 2017</pre>
 * 
 */
public class Require_User {

	private static final String WRITETO = "require_user.txt";
	
	public static void main(String[] args){
		writeRequireUsers(addBefore(getUsers("users.txt")));
	}
	
	/**
	 * Adds "require user " before each user name
	 * @param user
	 * 
	 * @return LinkedList of ("require user " + user)
	 */
	public static LinkedList<String> addBefore(LinkedList<String> users){
		LinkedList<String> requireUsers = new LinkedList<String>();
		for (String user: users){
			requireUsers.add("require user\t" + user);
		}
		
		return requireUsers;
	}
	
	/**
	 * Reads a file of user names and separates the user names into a linked list.
	 * 
	 * @param file String .txt file
	 * 
	 * @return LinkedList users
	 */
	public static LinkedList<String> getUsers(String file){
		LinkedList<String> users = new LinkedList<String>();
        BufferedReader br = null;
        String user, line = "";
        
        // Regular Expression containing anything but A-Z, a-z, 0-9, @, or .
        String cvsSplitBy = "[ .]";

        try {
        	// Opens file
            br = new BufferedReader(new FileReader(file));
            
            //Reads lines
            while ((line = br.readLine()) != null) {
            	
                // use comma, new line, and space as separator
            	user = line.split(cvsSplitBy)[0];
            	users.add(user);
            }

        } catch (FileNotFoundException e) {
        	print("There was a problem reading the file");
        } catch (IOException e) {
            print("There was a problem reading a user.");
        } finally {
            if (br != null) {
            	
            	// Closes the file
                try {
                    br.close();
                } catch (IOException e) {
                    print("There was an error closing the file.");
                }
            }
        }
        
        return users;
	}
	
	
	/**
	 * Parses and prints the users from the file
	 * for testing purposes.
	 * 
	 * @param file String .txt file
	 */
	private static void readUsers(String file){
		LinkedList<String> users = getUsers(file);
		LinkedList<String> requireUsers = addBefore(users);
		for (String user: requireUsers){
			System.out.println(user);
		}
	}
	
	
	/**
	 * Prints a line to the screen, followed by a new line.
	 * 
	 * @param String s
	 */
	private static void print(String s){
		System.out.println(s);
	}

	
	/**
	 * Writes the new strings to a "require_user.txt" file.
	 * 
	 * @param LinkedList requireUsers
	 */
	private static void writeRequireUsers(LinkedList<String> requireUsers){
		FileWriter fw;
		try {
			fw = new FileWriter(WRITETO);
			for (String requireUser: requireUsers) {
				fw.write(requireUser + "\n");
			}
		 
			fw.close();
		} catch (IOException e) {
			System.err.println("There was a problem writing to the file.");
		}
		 
		
	}
}
