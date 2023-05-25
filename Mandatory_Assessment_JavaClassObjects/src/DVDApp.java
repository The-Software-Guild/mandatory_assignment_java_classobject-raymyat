import com.view.*;
import com.dao.*;
import com.controller.*;

public class DVDApp {

	public static void main(String[] args) {
		IO userIO = new IOImpl();
		DVDLibrary dao = new DVDLibraryImpl();
		DVDLibraryView view = new DVDLibraryView(userIO);
		DVDLibraryController controller = new DVDLibraryController(view, dao);
		controller.runProgram();
		
	}

	
}
