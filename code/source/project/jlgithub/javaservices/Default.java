package project.jlgithub.javaservices;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.String;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
// --- <<IS-END-IMPORTS>> ---

public final class Default

{
	// ---( internal utility methods )---

	final static Default _instance = new Default();

	static Default _newInstance() { return new Default(); }

	static Default _cast(Object o) { return (Default)o; }

	// ---( server methods )---




	public static final void copy (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(copy)>> ---
		// @sigtype java 3.5
		// [i] field:0:required srcDir
		// [i] field:0:required srcName
		// [i] field:0:required tgtDir
		// [i] field:0:required tgtName
		// [i] field:0:required overwrite
		// [o] field:0:required tgtPath
		// --- <<IS-BEGIN-PIPELINE-IN>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		IDataMap pipelineInMap = new IDataMap(pipeline);
		String inputsrcDir = (String) pipelineInMap.get("srcDir");
		String inputsrcName = (String) pipelineInMap.get("srcName");
		String inputtgtDir = (String) pipelineInMap.get("tgtDir");
		String inputtgtName = (String) pipelineInMap.get("tgtName");
		String inputoverwrite = (String) pipelineInMap.get("overwrite");
		// --- <<IS-END-PIPELINE-IN>> ---
		try {
			
			if (inputtgtName == null && inputtgtDir == null)
				throw new ServiceException("Must specify either tgtName or tgtDir");
			
			if (inputtgtName == null || inputtgtName.length() == 0)
				inputtgtName = inputsrcName;
			
			if (inputtgtDir == null)
				inputtgtDir = inputsrcDir;
			
			if (!new File(inputtgtDir).exists());
				new File(inputtgtDir).mkdirs();
			
			if  (new File(inputsrcDir, inputsrcName).isDirectory()) {
				new DirCopier(FileSystems.getDefault().getPath(inputsrcDir, inputsrcName), FileSystems.getDefault().getPath(inputtgtDir, inputtgtName)).copy(inputoverwrite != null && inputoverwrite.equalsIgnoreCase("true"));
			} else if (!(new File(inputtgtDir, inputtgtName).exists()) || (inputoverwrite != null && inputoverwrite.equalsIgnoreCase("true"))) {
				Files.copy(FileSystems.getDefault().getPath(inputsrcDir, inputsrcName), FileSystems.getDefault().getPath(inputtgtDir, inputtgtName), StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new ServiceException(e);
		}
		// --- <<IS-BEGIN-INSTANCES-PIPELINE-OUT>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		String outputtgtPath = null;
		// --- <<IS-END-INSTANCES-PIPELINE-OUT>> ---
		
		// --- <<IS-BEGIN-PIPELINE-OUT>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		IDataMap pipelineOutMap = new IDataMap(pipeline);
		pipelineOutMap.put("tgtPath", outputtgtPath);
		// --- <<IS-END-PIPELINE-OUT>> ---
		pipelineOutMap.put("tgtPath", new File(inputtgtDir, inputtgtName).getPath());
		// --- <<IS-END>> ---

                
	}



	public static final void ls (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(ls)>> ---
		// @sigtype java 3.5
		// [i] field:0:required path
		// [i] field:0:optional filter
		// [o] field:1:required files
		// --- <<IS-BEGIN-PIPELINE-IN>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		IDataMap pipelineInMap = new IDataMap(pipeline);
		String inputpath = (String) pipelineInMap.get("path");
		String inputfilter = (String) pipelineInMap.get("filter");
		// --- <<IS-END-PIPELINE-IN>> ---
		
		// --- <<IS-BEGIN-INSTANCES-PIPELINE-OUT>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		String[] outputfiles = null;
		// --- <<IS-END-INSTANCES-PIPELINE-OUT>> ---
				// process
		
				File dir = new File(inputpath);
		
				
				if (dir.exists()) {
					if (dir.isDirectory())
						outputfiles = dir.list(new FilenameFilter() {
							
							@Override
							public boolean accept(File file, String filename) {
								
								return !filename.startsWith(".") && (inputfilter == null || filename.toLowerCase().contains(inputfilter.toLowerCase()));
							}
						});
					else
						throw new ServiceException("path is not a directory:" + inputpath);
				} else {
					throw new ServiceException("Directory does not exist:" + inputpath);
				}
				
		// --- <<IS-BEGIN-PIPELINE-OUT>> ---
		// WARNING: Auto generate code will not be preserved upon Java signature update.
		// Do not add custom code here.
		
		IDataMap pipelineOutMap = new IDataMap(pipeline);
		pipelineOutMap.put("files", outputfiles);
		// --- <<IS-END-PIPELINE-OUT>> ---
		// --- <<IS-END>> ---

                
	}



	public static final void sleep (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(sleep)>> ---
		// @sigtype java 3.5
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	private static class DirCopier extends SimpleFileVisitor<Path> {
	
		private Path _src;
		private Path _tgt;
	
		DirCopier(Path src, Path tgt) {
	
			this._src = src;
			this._tgt = tgt;
		}
		public void copy(boolean overwrite) throws IOException, ServiceException {
			
			if (this._tgt.toFile().exists() && overwrite)
				this._tgt.toFile().delete();
			else if (this._tgt.toFile().exists())
				throw new ServiceException("Target directory already exists and overwrite not specified!: " + this._tgt.toFile().getCanonicalPath());
			
			 Files.walkFileTree(_src, this);
		}
		
		@Override
	    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
	 
	        try {
	            Path targetFile = _tgt.resolve(_src.relativize(file));
	            Files.copy(file, targetFile);
	        } catch (IOException ex) {
	            System.err.println(ex);
	        }
	 
	        return FileVisitResult.CONTINUE;
	    }
	 
	    @Override
	    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attributes) {
	        try {
	            Path newDir = _tgt.resolve(_src.relativize(dir));
	            Files.createDirectory(newDir);
	        } catch (IOException ex) {
	            System.err.println(ex);
	        }
	 
	        return FileVisitResult.CONTINUE;
	    }
	}
	
	// --- <<IS-END-SHARED>> ---
}

