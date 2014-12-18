package googleSearch;

import java.io.File;

import org.sikuli.slides.api.Context;
import org.sikuli.slides.api.Slides;

public class GoogleSearch {
	
	public void executePowerPointFile(String powerPointFilename)
			throws Throwable {
		// EXECUTE THE POWERPOINT
		Slides.execute(new File(powerPointFilename));
	}
	
	public void executePowerPointFileWithContext(String powerPointFilename, Context context)
			throws Throwable {
		// EXECUTE THE POWERPOINT
		Slides.execute(new File(powerPointFilename), context);
	}

}
