package loader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Repository {

    private MyClassLoader mcl;
    private File repBase;
    private ArrayList<File> listeClasses;

    public Repository(File base) {
        this.repBase = base;
        ArrayList<File> arrayRepository = new ArrayList<File>();
        arrayRepository.add(repBase);
        this.mcl = new MyClassLoader(arrayRepository);
        this.listeClasses = new ArrayList<File>();
    }

    public List<Class<?>> load() {
        List<Class<?>> listeClasses = new ArrayList<Class<?>>();
        ArrayList<File> listeClassesRep = parcourirEnProfondeur(this.repBase);
        for (File file : listeClassesRep) {

            String pathRep = this.repBase.getPath();
            int result = file.getPath().compareTo(pathRep) + 1;
            String nomPackage = "";
            if(result > 0) {
                String pathPackage = file.getPath().substring(pathRep.length());
                pathPackage = pathPackage.substring(0, pathPackage.lastIndexOf("\\"));
                pathPackage = pathPackage.substring(pathPackage.indexOf("\\") + 1);
                nomPackage = pathPackage.replace("\\", ".");
            }
            String nomClasse = file.getName().substring(0, file.getName().lastIndexOf("."));
            try {
                listeClasses.add(mcl.loadClass(nomPackage + "." + nomClasse));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return listeClasses;
    }

    public ArrayList<File> parcourirEnProfondeur(File fichier) {
            //System.out.println("CHEMIN "+fichier.getPath());
        File[] listeFichiers = fichier.listFiles();
        for(File file : listeFichiers){
            System.out.println(file.getPath());
        }
        for(File file : listeFichiers){
            if(file.getPath().endsWith(".jar")){
                try {
                    System.out.println("C'est un jar :"+file.getPath());
                    JarFile zFile = new JarFile(file);
                    Enumeration<? extends JarEntry> entries = zFile.entries();
                    while(entries.hasMoreElements()){
                        JarEntry entry = entries.nextElement();
                        if(entry.toString().endsWith(".class")) {
                            String nomPackage = file.getPath();
                            nomPackage = nomPackage.replace(file.getName(), "");
                            String nomFichier = entry.toString();
                            //String nomClasse = nomFichier.replace("/", ".");
                            //nomClasse = nomClasse.substring(0, nomClasse.lastIndexOf('.'));
                            listeClasses.add(new File(nomPackage + nomFichier));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           // for (File file2 : listeClasses)
            //System.out.println("File2" +file2.getPath());

        }
        return listeClasses;
    }


    public static void main(String[] args) {
        File repMaClasse = new File("C:\\Users\\DogiDogiDog\\Documents\\GitHub\\FatalPicard\\Plugins\\target\\classes");

        Repository r = new Repository(repMaClasse);
        List<Class<?>> classes = r.load();
       /* for (Class<?> classe : classes) {
            System.out.println("Class loaded " + classe);
            System.out.println("Class " + classe + " has been loaded by "
                    + classe.getClassLoader());
        }*/
    }
}
