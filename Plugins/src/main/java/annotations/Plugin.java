package annotations;

public @interface Plugin
{
    Type type();
    String method();

    enum Type{ATTACK, GRAPHIC, MOVE}
}
