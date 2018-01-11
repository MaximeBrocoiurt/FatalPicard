package annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Attack
{
    Nature nature();

    enum Nature{MAIN, RANGE, POWER, CONSUMPTION}
}
