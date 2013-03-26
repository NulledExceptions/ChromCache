package Copier;

import java.io.File;
import static java.nio.file.StandardCopyOption.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CacheCopy implements Runnable{

	   private static String OS = null;
	   public static String getOsName()
	   {
	      if(OS == null) { OS = System.getProperty("os.name"); }
	      return OS;
	   }
	   public static boolean isWindows()
	   {
	      return getOsName().startsWith("Windows");
	   }


	   public static void  copyDirectory(File sourceLocation , File targetLocation)
			    throws IOException {
			        
			        if (sourceLocation.isDirectory()) {
			            if (!targetLocation.exists()) {
			                targetLocation.mkdir();
			            }
			            
			            String[] children = sourceLocation.list();
			            for (int i=0; i<children.length; i++) {
			                copyDirectory(new File(sourceLocation, children[i]),
			                        new File(targetLocation, children[i]));
			            }
			        } else {
			            try{
			            InputStream in = new FileInputStream(sourceLocation);
			             
			            OutputStream out = new FileOutputStream(targetLocation);
			            
			            // Copy the bits from instream to outstream
			            byte[] buf = new byte[1024];
			            int len;
			            while ((len = in.read(buf)) > 0) {
			                out.write(buf, 0, len);
			            }
			            in.close();		         
			            out.close();			        
			        
			            }catch(IOException e){
			            	 System.err.println("Caught IOException: " + e.getMessage());
			            }
			        }
			    }
	
	
	
	

public void run(){
		// TODO Auto-generated method stub

	Date date = new Date(System.currentTimeMillis());
	//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
	//String s = formatter.format(date);
	String thisOS = getOsName();
	
	
	//if(isWindows()){}
	String userName = System.getProperty("user.name");

	String dirToCopy = "C:\\Users\\" + userName + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\Cache";
	String destinationDir = "C:\\Users\\" + userName + "\\Desktop\\Backup";
	
	
	
	
	File theDestinationDir = new File(destinationDir);
	if(!theDestinationDir.exists()){
		System.out.println("Destination Directory not found!");
		
	}else{
		
		System.out.println("Destination folder found");
	}
	
	File newDir = new File(destinationDir+"\\"+date);
	
	File sourceDir = new File(dirToCopy);

	
	/*if(!sourceDir.exists()){
		System.out.println("Directory not found!");
		
	}else{
		  File[] fList = sourceDir.listFiles();
		  
	        for (File file : fList){
	            if (file.isFile()){
	                System.out.println(file.getName());
	               
	            }
	        }
	}
	
	
	
	Path source = Paths.get(dirToCopy);
	Path target = Paths.get(destinationDir);
	try {
		Files.copy(source, target);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	*/

	try {
		copyDirectory(sourceDir, newDir);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	System.out.println(thisOS);
	System.out.println(userName);
	System.out.println(dirToCopy);
	System.out.println(destinationDir);
	System.out.println(newDir);
	System.out.println(date);
	 }
}