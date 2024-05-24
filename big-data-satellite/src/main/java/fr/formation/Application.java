package fr.formation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class Application {
    
// Définir l'URL du point d'accès HDFS
private static final String HDFS_ENDPOINT = "hdfs://20.199.14.75:8020";

public static void main(String[] args) throws Exception{
// Créer une nouvelle configuration Hadoop
Configuration config = new Configuration();

// Essayer de se connecter au serveur Hadoop HDFS
try (FileSystem hdfs = FileSystem.get(new URI(HDFS_ENDPOINT), config)) {
// Spécifier le chemin du fichier à lire sur HDFS
Path file = new Path("/ace-2007-2011/2007/200701.csv");

// Vérifier si le fichier existe
if (!hdfs.exists(file)) {
System.out.println("Le fichier n'existe pas : " + file.toString());
return; // Sortir du programme si le fichier n'existe pas
}

// Ouvrir le fichier pour lecture
try (FSDataInputStream inputStream = hdfs.open(file);
BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

// Lire le fichier ligne par ligne
String line;
while ((line = br.readLine()) != null) {
// Afficher chaque ligne du fichier sur la console
System.out.println(line);
}
}
} catch (Exception e) {
// Gérer et afficher les exceptions éventuelles
e.printStackTrace();
}
}
}