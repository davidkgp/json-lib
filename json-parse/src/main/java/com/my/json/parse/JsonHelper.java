package com.my.json.parse;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class JsonHelper {
	
	public static boolean isEmptyString(String value) {
		
		if(value==null || (value!=null && value.trim().length()>0)) {
			return true;
		}
		return false;
		
	}
	
	public static Optional<String> getFileContent(File fileObj)
			throws MalformedURLException, IOException, URISyntaxException {

		return Optional.ofNullable(fileObj!=null && fileObj.exists() && fileObj.isFile() && fileObj.canRead()
				? new String(Files.readAllBytes(Paths.get(fileObj.toURI())))
				: null);

	}

}
