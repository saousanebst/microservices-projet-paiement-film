package fr.formation.service;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class HdfsService {

    // Méthode pour récupérer les événements notables pour une année donnée
    public List<String> getNotableEvents(int year) throws Exception {
        // Création d'une configuration Hadoop
        Configuration configuration = new Configuration();
        // Obtention du système de fichiers HDFS
        FileSystem fs = FileSystem.get(configuration);

        // Construction du chemin du fichier de résultats solar
        Path[] paths = new Path[]{
                new Path("/solar-" + year + "/GROUPE1"),
                new Path("/Kiruna/" + year + "/GROUPE1_KIRUNA")
        
        };

        // Initialisation de la liste des résultats
        List<String> results = new ArrayList<>();

        for (Path path : paths){
            if (fs.exists(path)){

          

        // Utilisation de try-with-resources pour garantir la fermeture du flux
        try (BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(path)))) {
            String line;
            // Lecture des lignes du fichier
            while ((line = br.readLine()) != null) {
                // Ajout de chaque ligne à la liste des résultats
                results.add(line);
            }
        } finally {
            // Fermeture du système de fichiers
            fs.close();
        }
    }
}
    

        // Retourne la liste des résultats
        return results;
    }
    
}
    
