package fr.formation.acedscover;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;


//La signature du Mapper indique que notre clé d'entrée est un LongWritable (position du début de la ligne) 
//et que la valeur d'entrée est un Text (la ligne elle-même). La clé de sortie est un Text (l'année) et la 
//valeur de sortie est un IntWritable (le nombre 1 pour chaque événement notable).
//implements <clé d'entree, valeur d'entree, clé de sortie, valeur de sortie>

public class EventMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    // Constante représentant le nombre 1 sous forme d'IntWritable
    private final static IntWritable one = new IntWritable(1);

    // Méthode de mapping
    @Override
    public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter)
            throws IOException {

        if(key.get() == 0){
            return;
        }
        
        // Convertir la ligne en une chaîne de caractères
        String line = value.toString();
        // Diviser la ligne en champs en utilisant la virgule comme séparateur
        String[] fields = line.split(";");
        
        // Vérifier si la ligne a toutes les colonnes nécessaires (Date, Speed, Density, Bt, Bz)
        if (fields.length < 5) {
            // Ignorer la ligne invalide
            return;
        }

        // Extraire la date, Bz (à l'index 4) et Bt (à l'index 3)
        String date = fields[0];
        double bz = Double.parseDouble(fields[4]);
        double magneticVariation = Double.parseDouble(fields[3]);

        // Vérifier si Bz est inférieur à -20 ou si la valeur absolue de Bt est supérieure à 1000
        if (bz < -20 || Math.abs(magneticVariation) > 1000) {
            // Envoyer une paire clé-valeur où la clé est l'année (4 premiers caractères de la date) et la valeur est 1
            output.collect(new Text(date.substring(0, 4)), one);
        }
    }

   
}




