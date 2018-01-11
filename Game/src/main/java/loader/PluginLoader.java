package loader;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TimerTask;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public  class  PluginLoader  {

    private File repBase;

    private List<Class<?>> listClassesLoaded;

    private ArrayList<File> listJar = new ArrayList<>();

    public PluginLoader(File base) {
        this.repBase = base;
        ArrayList<File> arrayRepository = new ArrayList<>();
        arrayRepository.add(repBase);
        this.listClassesLoaded = new ArrayList<>();
        listClassesLoaded = this.load();
    }

    /**
     * Trouve toutes les classes dans les jar
     * @return returnListClass
     */
    public List<Class<?>>  load() {
        parcourirEnProfondeur(this.repBase);
        listClassesLoaded=loadJars();
        return listClassesLoaded;
    }

    /**
     * Charge une classe dans un jar
     * @param nameClass
     * @return
     */
    public Class<?> loadFile(String nameClass) {
        return (loadFileInJar(nameClass));
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
    private Class<?> loadFileInJar(String nameClassToLoad) {
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
            String className = null;
            try {
                jarFile = new JarFile(file.getPath());

                Enumeration<JarEntry> e = jarFile.entries();
                while (e.hasMoreElements()) {
                    JarEntry je = e.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class")) {
                        continue;
                    }
                    // -6 because of .class
                    className = je.getName().substring(0, je.getName().length() - 6);
                    className = className.replace('/', '.');

                 //   System.out.println("On recherche "+nameClassToLoad);
                    //  System.out.println("BIS "+className);

                    if(className.equals(nameClassToLoad)){
                        Class c = cl.loadClass(className);
                        System.out.println("Classe Chargée");
                        return c;
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
                e.getMessage();
                e.getCause();
                e.getLocalizedMessage();
            }
        }

        return null;

    }

    /**
     * Charge le contenu des class des Jar trouvé
     * @return
     */
    private List<Class<?>> loadJars() {
        
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
                    Class c = cl.loadClass(className);
                    listClassesLoaded.add(c);
                    }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                //System.out.println("Le probleme est ici");
                e.getLocalizedMessage();
            }
        }

        return listClassesLoaded;

    }



    public List<Class<?>> getListClasses() {
        return listClassesLoaded;
    }



    /**
     * Cherhcer une classe dans la liste de classe chargé
     * @param name
     * @return
     */
    public Class chercherClass(String name){
      //  System.out.println("ON recherche : "+name);
        for(Class clasz : this.listClassesLoaded){
            //  System.out.println("Class.getName : "+clasz.getName());
            if(clasz.getName().contains(name)){
                return clasz;
            }
        }
        return null;
    }



}
