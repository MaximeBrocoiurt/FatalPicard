package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;


public class MyClassLoader extends SecureClassLoader {

    private ArrayList<File> path;

    public MyClassLoader(ArrayList<File> p) {
        this.path = p;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("On cherche la classe "+name);
        byte[] b = loadClassData(name);
        if(b == null) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, b, 0, b.length);
    }

    /**
     * Recherche du .class qui correspond au nom et lecture des bytes
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    private byte[] loadClassData(String name) throws ClassNotFoundException {

        // Recherche dans la liste du dossier contenant les .class
        // On cherche dans le cas où c'est dans un dossier ou un .jar
        for (File file : path) {
           File[] listFile=file.listFiles();
            System.out.println(listFile);
            for(File fichier : listFile){
            System.out.println("PATH "+fichier.getName());
            if (fichier.getPath().endsWith(".jar")){
                try {
                    JarFile zFile = new JarFile(fichier);
                    Enumeration<? extends ZipEntry> entries = zFile.entries();
                    while(entries.hasMoreElements()){
                        ZipEntry entry = entries.nextElement();
                        if(entry.toString().endsWith(".class")) {
                            String nomPackage = fichier.getPath();
                            nomPackage = nomPackage.replace(fichier.getName(), "");
                            String nomFichier = entry.toString();
                            String nomClasse = nomFichier.replace("/", ".");
                            nomClasse = nomClasse.substring(0, nomClasse.lastIndexOf('.'));
                            if(nomClasse.equals(name)) {
                                Path cheminFichier = Paths.get(nomPackage + nomFichier);
                                System.out.println("CHEMIN: "+cheminFichier);
                                byte[] fileData = Files.readAllBytes(cheminFichier);
                                return fileData;
                            }
                        }
                    }
                } catch (ZipException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        }
        return null;
    }
}