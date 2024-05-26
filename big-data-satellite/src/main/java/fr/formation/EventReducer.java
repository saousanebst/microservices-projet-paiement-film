package fr.formation;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

//Reducer<Text, IntWritable, Text, IntWritable> : Cette classe est un reducer qui prend en entrée 
//des paires clé-valeur avec une clé de type Text (représentant l'année) et une valeur de 
//type IntWritable (représentant le nombre d'occurrences pour cette année) et produit en sortie 
//des paires clé-valeur avec la même structure.
public class EventReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
        int totalCount = 0;
        // Parcourir les valeurs et les additionner pour obtenir le nombre total d'occurrences
        while (values.hasNext()) {
            totalCount += values.next().get();
        }
        // Émettre la clé (année) et le nombre total d'occurrences
        output.collect(key, new IntWritable(totalCount));
    }
}
