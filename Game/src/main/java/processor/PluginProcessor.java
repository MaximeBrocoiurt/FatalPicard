package processor;

import entities.Tuple;
import loader.PluginLoader;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * sert à interpréter les plugins après leur chargement
 */
public class PluginProcessor
{
    private PluginLoader pluginLoader;

    public PluginProcessor(PluginLoader pluginLoader)
    {
        this.pluginLoader = pluginLoader;
    }

    public void executeMethod(Tuple<?> tuple, Enum annotationEnum, Object... args) throws InvocationTargetException
    {
        try
        {
            findMethod(tuple.type, annotationEnum).invoke(tuple.instance, args);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    public Method findMethod(Class<?> c, Enum<?> annotationEnum)
    {
        for(Method m : c.getDeclaredMethods())
        {
            for(Annotation a : m.getDeclaredAnnotations())
            {
                if(a.annotationType().getCanonicalName().equals(annotationEnum.getClass().getCanonicalName().substring(0, annotationEnum.getClass().getCanonicalName().lastIndexOf("."))))
                {
                    if(a.toString().contains(annotationEnum.toString()))
                    {
                        return m;
                    }
                }
            }
        }
        return null;
    }
}