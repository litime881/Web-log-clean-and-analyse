package com.DataCleanProcess.MR;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ValueUtil {
	public String DataParser(String SampleLine) {
		if(SampleLine.isEmpty()) {
			return "";
		}
		String[] lineip = null;
		String[] linetime = null;
		String[] lineurl = null;
		String lineend = new String();
		try {
			lineip = SampleLine.split(" - - ");
			linetime = lineip[1].split("] ");
			lineurl = linetime[1].split("\"");
			lineend = lineurl[2].split(" ")[1];																																																																																																																																																												
		}catch(Exception e) {
			System.out.println(SampleLine);
			System.out.println(linetime[1]);
			System.err.println(e);
			throw e;
		}
		finally {
			return new String(lineip[0] + "\t" + linetime[0].substring(1) + "\t" + lineurl[1].substring(4) + "\t" + lineend);
		}
	}
	
	public void dealFileDir(Path FilePath, Configuration conf) throws Exception {
			FileSystem fs = FilePath.getFileSystem(conf);
			if(fs.exists(FilePath)) {
				fs.delete(FilePath,true);
			}
	}
}
