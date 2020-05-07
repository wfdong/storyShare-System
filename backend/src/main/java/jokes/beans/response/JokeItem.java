package jokes.beans.response;

import java.util.List;

import jokes.beans.daobeans.JokeComments;
import jokes.beans.daobeans.JokeTable;
import jokes.beans.daobeans.UserFile;

public class JokeItem {

	JokeTable jokeTable;
	
	List<JokeComments> jokeComments;
	
	List<UserFile> userFiles;
	
	public List<UserFile> getUserFiles() {
		return userFiles;
	}

	public void setUserFiles(List<UserFile> userFiles) {
		this.userFiles = userFiles;
	}

	public JokeTable getJokeTable() {
		return jokeTable;
	}

	public void setJokeTable(JokeTable jokeTable) {
		this.jokeTable = jokeTable;
	}

	public List<JokeComments> getJokeComments() {
		return jokeComments;
	}

	public void setJokeComments(List<JokeComments> jokeComments) {
		this.jokeComments = jokeComments;
	}
}
