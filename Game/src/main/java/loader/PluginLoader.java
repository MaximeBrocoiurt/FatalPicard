package loader;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class PluginLoader {

    private File repBase;

    private List<Class<?>> listClasses;
    private ArrayList<File> listJar = new ArrayList<File>();

    public PluginLoader(File base) {
        this.repBase = base;
        ArrayList<File> arrayRepository = new ArrayList<File>();
        arrayRepository.add(repBase);
        this.listClasses = new ArrayList<Class<?>>();
        System.out.println("Chargement des classes");
        listClasses=this.load();

    }

    /**
     * Charge les classes
     * @return returnListClass
     */
    public List<Class<?>> load() {
        List<Class<?>> listFoundClasses ;
        parcourirEnProfondeur(this.repBase);

        listFoundClasses=loadJars();
        return listFoundClasses;
    }


    /**
     * Cherche des jar dans tous les sous-dossiers
     * @param fichier
     * @return listFoundClasses
     */
    public void parcourirEnProfondeur(File fichier) {
        if(fichier.isDirectory()) {

            File[] listeFichiers = fichier.listFiles();


            if(listeFichiers.length == 0) {
                listeFichiers = fichier.listFiles();
                for (File file : listeFichiers) {
                    parcourirEnProfondeur(file);
                }
            } else {
                for (File file : listeFichiers) {
                    if(file.getName().endsWith(".jar")){
                        listJar.add(file);
                    }
                    parcourirEnProfondeur(file);
                }
            }
        }
    }

    /**
     * Cherche les .class dans le Jar et les ajoute à la liste des classes à charger
     * @param
     */
    private ArrayList<Class<?>> loadJars() {
        ArrayList<Class<?>> listFoundClasses= new ArrayList<Class<?>>();
        URL[] urls = new URL[10];

        for (int i = 0; i < listJar.size(); i++) {
            try {
                urls[i] = new URL("jar:file:" + listJar.get(i).getPath() + "!/");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        URLClassLoader cl = new URLClassLoader(urls);

        for(File file : listJar) {
            JarFile jarFile = null;
            try {
                jarFile = new JarFile(file.getPath());

                Enumeration<JarEntry> e = jarFile.entries();

                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class")) {
                        continue;
                    }
                    // -6 because of .class
                    String className = je.getName().substring(0, je.getName().length() - 6);
                    className = className.replace('/', '.');
                    System.out.println("LCAS "+className);
                    Class c = cl.loadClass(className);

                    listFoundClasses.add(c);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Le probleme est ici");
                e.printStackTrace();
                e.getMessage();
                e.getCause();
                e.getLocalizedMessage();
            }
        }

        return listFoundClasses;


    }


    public List<Class<?>> getListClasses() {
        return listClasses;
    }

    public Class chercherClass(String name){
        System.out.println("ON recherche : "+name);
        for(Class clasz : this.listClasses){
            //  System.out.println("Class.getName : "+clasz.getName());
            if(clasz.getName().contains(name)){
                return clasz;
            }
        }
        return null;
    }

}
