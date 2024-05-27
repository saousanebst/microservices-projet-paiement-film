package fr.formation;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import fr.formation.acedscover.EventMapper;
import fr.formation.acedscover.EventReducer;



public class Application {


    

public static void main(String[] args) throws Exception{

        // Créer une configuration de job
        JobConf conf = new JobConf(Application.class);
        conf.setJobName("EventCount");

        // Spécifier les classes de mapper et de reducer
        conf.setMapperClass(EventMapper.class);
        conf.setReducerClass(EventReducer.class);

        // Spécifier les types de données d'entrée et de sortie
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        // Spécifier les chemins d'entrée et de sortie
        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // Spécifier les types de données intermédiaires
        conf.setMapOutputKeyClass(Text.class);
        conf.setMapOutputValueClass(IntWritable.class);

        // Spécifier les types de données de sortie finale
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);

        // Lancer le job
        JobClient.runJob(conf);
  

    }
}