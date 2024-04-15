package project.jlgithub.javaservices;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import com.softwareag.util.IDataMap;
import java.io.File;
import java.lang.String;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
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

	// --- <<IS-START-SHARED>> ---

	private static class DirCopier extends SimpleFileVisitor<Path> {

		private Path _src;
		private Path _tgt;

		DirCopier(Path src, Path tgt) {

			this._src = src;
			this._tgt = tgt;
		}
	}

	// --- <<IS-END-SHARED>> ---
}

