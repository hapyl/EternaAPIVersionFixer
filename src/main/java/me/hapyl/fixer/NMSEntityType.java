package me.hapyl.fixer;

import net.minecraft.world.entity.EntityTypes;
import org.bukkit.entity.EntityType;
import org.checkerframework.checker.signature.qual.SignatureUnknown;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class NMSEntityType implements Fixer {

    @Override
    @SuppressWarnings("rawtypes")
    public void fix() {
        final Class<EntityTypes> clazz = EntityTypes.class;

        for (Field field : clazz.getDeclaredFields()) {
            final int modifiers = field.getModifiers();

            if (!Modifier.isStatic(modifiers)) {
                continue;
            }

            if (!Modifier.isPublic(modifiers)) {
                continue;
            }

            final Type generic = field.getGenericType();
            String genericName = generic.toString();
            genericName = genericName.substring(genericName.lastIndexOf(".") + 1, genericName.lastIndexOf(">"));
            genericName = genericName.replace("$", "."); // fix inner

            final String fieldName = field.getName();
            String mappedName = (genericName.contains(".")
                    ? genericName.substring(genericName.indexOf(".") + 1)
                    : genericName);
            mappedName = mappedName.replace("Entity", "");
            mappedName = Helpers.toEnumName(mappedName);

            System.out.println("public static final EntityTypes<%s> %s = EntityTypes.%s;".formatted(genericName, mappedName, fieldName));
        }
    }

}
