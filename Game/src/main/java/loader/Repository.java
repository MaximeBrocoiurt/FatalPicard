package loader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private ClassLoader mcl;
    private File repBase;
    private ArrayList<File> listeFile;


    public Repository(File base) {
        this.repBase = base;
        ArrayList<File> arrayRepository = new ArrayList<File>();
        arrayRepository.add(repBase);
        this.mcl = new ClassLoader(arrayRepository);
        this.listeFile = new ArrayList<File>();
    }

    public List<Class<?>> load() {

        List<Class<?>> listeClasses = new ArrayList<Class<?>>();

        File[] listFile= this.repBase.listFiles();
        for (File file : listFile) {
            listeFile.add(file);
        }

        for(File file : listFile) {
            String pathRep = this.repBase.getPath();
            int result = file.getPath().compareTo(pathRep) + 1;
            String pathPackage = "";
            if(result > 0) {
                pathPackage = file.getPath().substring(pathRep.length());
                pathPackage = pathPackage.substring(pathPackage.indexOf("\\") + 1);
            }

            try {
                listeClasses.add(mcl.loadClass(pathPackage));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return listeClasses;
    }



}
