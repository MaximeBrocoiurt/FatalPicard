package loader;
import engine.War;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class testMyClassLoader {

    @Test
    public void testLoadJar(){

        // File basPathPlugin = new File(System.getProperty("user.dir") + File.separatorChar + "Plugins"+  File.separatorChar +"target"+  File.separatorChar +"classes");
        File basPathPlugin = new File(System.getProperty("user.dir") + File.separatorChar + "src"+  File.separatorChar +"test"+  File.separatorChar +"ressources");

        System.out.println("Chemin plugins "+basPathPlugin.getPath().toString());
        //C:\Users\DogiDogiDog\Documents\GitHub\FatalPicard\Game\src\test\ressources
        Repository myLoader = new Repository(basPathPlugin);

        List<Class<?>> myPlugin = myLoader.load();

        assertNotEquals(0,myPlugin.size());
        for(int i=0;i<myPlugin.size();i++){
            System.out.println("listClass : "+ myPlugin.get(i));
        }

        assertEquals(true,myPlugin.contains("class plugins.move.SchwarzeneggerMove"));



    }
}
