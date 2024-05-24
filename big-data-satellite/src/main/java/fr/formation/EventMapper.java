package fr.formation;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EventMapper extends Mapper<LongWritable, Text, Text, Text>{
     
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            
            String[] parts = value.toString().split(";");
            
            String date = parts[0];
            //double vitesseVent = Double.parseDouble(parts[1]);
            double bz = Double.parseDouble(parts[3]);
            double magneticVariation = Double.parseDouble(parts[4]);
    
            if (bz < -20 || Math.abs(magneticVariation) > 1000) {
                context.write(new Text(date.substring(0, 4)), value); // utilise annee comme cle
            }
        }
}




