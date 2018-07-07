package com.DataCleanProcess.MR;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MainCleanFunction {
	
	public static void main(String[] arg0)throws IllegalArgumentException, Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		if(arg0.length != 2) {
			System.err.println("url should include map and reducer two");
			System.exit(-1);
		}
		
		Job job = new Job(conf);
		job.setJarByClass(MainCleanFunction.class);
		job.setJobName("MainCleanFunction");
		
		//FileInputFormat.setInputPaths(arg0, arg1);
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		new ValueUtil().dealFileDir(new Path(arg0[1]), conf);
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
		
		job.setMapperClass(DataCleanMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(DataCleanReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		/*
		job.setMapperClass(DataCleanMapper.class);
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		*/
		boolean resl = job.waitForCompletion(true);
		System.out.println(resl?"true":"false");
		System.exit(resl? 0:1);
	}
}
