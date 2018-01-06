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

    private byte[] loadClassData(String name) throws ClassNotFoundException {
        for (File fichier : path) {
            System.out.println("PATH "+fichier.getName());
            if (fichier.isDirectory()) {
                // On v�rifie si la classe n'a pas �t� d�j� charg�e
                // Class<?> classLoaded = findLoadedClass(name);
                // if(classLoaded == null)
                try {
                    String nomPackage = name.replace(".", "/");
                    System.out.println("nomPackageReplace :"+nomPackage);
                    String nomRepertoire = fichier.getPath();
                    System.out.println("nomRepertoire :"+nomRepertoire);

                    String nomClasse = nomRepertoire +File.separatorChar+ nomPackage;
                    System.out.println("nomClasse :"+nomClasse);

                    Path cheminFichier = Paths.get(nomClasse+ ".class");
                    System.out.println("cheminFichier :"+cheminFichier);




                    byte[] fileData = Files.readAllBytes(cheminFichier);
                    return fileData;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if(fichier.getPath().endsWith(".zip") ||  fichier.getPath().endsWith(".jar")) {
                try {
                    ZipFile zFile = new ZipFile(fichier);
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
            }else {
                try {
                    ZipFile zFile = new ZipFile(fichier);
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
        return null;
    }
}