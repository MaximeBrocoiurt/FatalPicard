package entities;


public class Tuple<T>
{
    public final Class<T> type;
    public final T instance;
    public Tuple(Class<T> type) throws IllegalAccessException, InstantiationException
    {
        this.type = type;
        this.instance = type.newInstance();
    }
}