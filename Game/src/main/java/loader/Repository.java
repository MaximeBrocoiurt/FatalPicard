package loader;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



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
        if(fichier.isDirectory()) {
            File[] listeFichiers = fichier.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    Pattern p = Pattern.compile(".*.class");
                    Matcher m = p.matcher(name);
                    return m.matches();
                }
            });
            if(listeFichiers.length == 0) {
                listeFichiers = fichier.listFiles();
                for (File file : listeFichiers) {
                    parcourirEnProfondeur(file);
                }
            } else {
                for (File file : listeFichiers) {
                    listeClasses.add(file);
                    parcourirEnProfondeur(file);
                }
            }
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
