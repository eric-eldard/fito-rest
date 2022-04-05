package us.marseilles.fitocracy.model;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.Map;

public final class ModelUtils
{
    /**
     * Recursively converts 0 Double values and blank Strings to null, so they can be stripped during JSON marshalling.
     * Practical space savings are in the 30% range, for both pretty and condensed JSON.
     *
     * For each field of the provided object:
     * - If the field is an Iterable, recursively call this method on each item
     * - If the field is a Map, recursively call this method on each value
     * - If the field's class is from fito-model, recursively call this method on it
     * - If the field is a Double and the value is 0, null it out
     * - If the field is a String and it's blank, null it out
     */
    @SneakyThrows(IllegalAccessException.class)
    public static void nullOutBlanksAndZeros(Object obj)
    {
        if (obj instanceof Iterable<?> iterable)
        {
            iterable.forEach(ModelUtils::nullOutBlanksAndZeros);
            return;
        }
        else if (obj instanceof Map<?, ?> map)
        {
            map.values().forEach(ModelUtils::nullOutBlanksAndZeros);
            return;
        }
        else if (!isFitoModelType(obj))
        {
            return; // prevents illegal introspection of un-exported types passed by initial caller
        }

        for (Field field : obj.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            Object value = field.get(obj);

            if (isFitoModelType(value) || value instanceof Iterable<?> || value instanceof Map<?,?>)
            {
                nullOutBlanksAndZeros(value);
            }
            else if (value instanceof Double dub && dub == 0)
            {
                field.set(obj, null);
            }
            else if (value instanceof String str && str.isBlank())
            {
                field.set(obj, null);
            }
        }
    }

    /**
     * @return <tt>true</tt> if the object's class belongs to fito-model; always returns <tt>false</tt> for null input
     */
    public static boolean isFitoModelType(Object obj)
    {
        return obj != null && obj.getClass().getPackageName().startsWith(ModelUtils.class.getPackageName());
    }

    private ModelUtils()
    {
    }
}