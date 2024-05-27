package fr.formation;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


public class EventMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)
            throws IOException {
        
            String[] parts = value.toString().split(";");
            
            String date = parts[0];
            double speed = Double.parseDouble(parts[1]);
            double density = Double.parseDouble(parts[2]);
            double bz = Double.parseDouble(parts[3]);
            double magneticVariation = Double.parseDouble(parts[4]);
        
            if (bz < -20 || Math.abs(magneticVariation) > 1000) {
                output.collect(new Text(date.substring(0, 4)), value); // utilise annee comme cle
            }

    }            
}




