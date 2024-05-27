package fr.formation.kiruna;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class EventMapperKiruna extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable>{
    // Constante représentant le nombre 1 sous forme d'IntWritable
    private final static IntWritable one = new IntWritable(1);

    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {
        
        // Convertir la ligne en une chaîne de caractères
        String line = value.toString();
        // Diviser la ligne en champs en utilisant la virgule comme séparateur
        String[] fields = line.split(";");

        // Extraire la date, magneticVariation (à l'index 1)
        String date = fields[0];
        double magneticVariation = Double.parseDouble(fields[1]);

        // Vérifier si la valeur absolue de Bt est supérieure à 1000
        if (Math.abs(magneticVariation - 10500) > 1000) {
            // Envoyer une paire clé-valeur où la clé est l'année (4 premiers caractères de la date) et la valeur est 1
            output.collect(new Text(date.substring(0, 4)), one);
        }
    }
    
}
