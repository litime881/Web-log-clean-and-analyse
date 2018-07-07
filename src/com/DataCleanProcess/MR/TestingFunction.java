package com.DataCleanProcess.MR;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class TestingFunction {
	//@Test
	public void test() {
		String SampleLine = "175.151.205.128 - - [30/May/2013:23:59:17 +0800] \"GET /forum.php?mod=ajax&action=forumchecknew&fid=99&time=1369929205&inajax=yes HTTP/1.1\" 200 66";
		String[] lineip = SampleLine.split(" - - ");
		String[] linetime = lineip[1].split("]");
		String[] lineurl = linetime[1].split("\"");
		String lineend = lineurl[2].split(" ")[1];
		System.out.println(lineip[0]);
		System.out.println(linetime[0].substring(1));
		System.out.println(lineurl[1].substring(4));
		System.out.println(lineend);
		System.out.println(lineip[0] + "\t" + linetime[0].substring(1) + "\t" + lineurl[1].substring(4) + "\t" + lineend);
	}
	
	@Test
	public void test1() {
		try {
			File f = new File(("file:///root/eclipse/eclipse-workspace/DataProcess/DemoResult.txt").substring(7));
			if(f.exists()) {
				System.out.println("exist");
			}else {
				System.out.println("not");
			}
		}finally {
			
		}
	}
}
