package com.DataCleanProcess.MR;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DataCleanMapper extends Mapper<LongWritable,Text,Text,Text> {
	
	
	//key默认是要处理的文本中一行的起始偏移量
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		ValueUtil vu = new ValueUtil();
		String processResult = vu.DataParser(line);
		String newKey = "";
		if(processResult != null&&!processResult.equals("")) {
			newKey = processResult.split("\t")[1].substring(0, 11);
		}
		context.write(new Text(newKey), new Text(processResult));
	}
}
