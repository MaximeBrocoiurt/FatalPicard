package annotations;

public @interface Plugin
{
    Type type();

    enum Type{ATTACK, GRAPHIC, MOVE}
}
