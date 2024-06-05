package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

public class FileUpLoadUtil {
	
	public static void saveFile(String upLoadDir,String fileName,MultipartFile multiPartFile) throws IOException{
		
		Path upLoadPath = Paths.get(upLoadDir);
		
		if(!Files.exists(upLoadPath)) {
			Files.createDirectories(upLoadPath);
		}
		
		try (InputStream inputStream = multiPartFile.getInputStream()){
			            Path filePath = upLoadPath.resolve(fileName);
			            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		}catch (IOException ioe) {
			throw new IOException("Could not save image file" + fileName, ioe);
		}
	}

}
