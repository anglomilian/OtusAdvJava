package ru.otus;

import java.lang.reflect.*;
import java.util.*;
import javax.json.*;

public class MyJSON {

    public String toJson(Object obj) {
        return toJsonValue(obj).toString();
    }

    private JsonValue toJsonValue(Object obj) {

        if (obj == null) {
            return JsonValue.NULL;
        }

        Class<?> clazz = obj.getClass();

        if (clazz.equals(Byte.class)) {
            return Json.createValue((Byte)obj);
        }
        else if (clazz.equals(Short.class)) {
            return Json.createValue((Short)obj);
        }
        else if (clazz.equals(Integer.class)) {
            return Json.createValue((Integer)obj);
        }
        else if (clazz.equals(Long.class)) {
            return Json.createValue((Long)obj);
        }
        else if (clazz.equals(Float.class)) {
            return Json.createValue((Float)obj);
        }
        else if (clazz.equals(Double.class)) {
            return Json.createValue((Double)obj);
        }
        else if (clazz.equals(Boolean.class)) {
            Boolean value = (Boolean)obj;
            return value ? JsonValue.TRUE : JsonValue.FALSE;
        }
        else if (clazz.equals(String.class) || clazz.equals(Character.class)) {
            return Json.createValue(obj.toString());
        }
        else if (isCollection(clazz)) {
            return collectionToJsonValue((Collection)obj);
        }
        else if (clazz.isArray()) {
            return arrayToJsonValue(obj);
        }
        else {
            return objectToJsonValue(obj);
        }
    }

    private JsonValue collectionToJsonValue(Collection collection) {

        if (collection.isEmpty()) {
            return Json.createArrayBuilder().build();
        }

        Class<?> componentType = collection.stream().findFirst().get().getClass();

        if (componentType.isPrimitive() || isPrimitiveWrapper(componentType) || componentType.equals(String.class)) {
            return Json.createArrayBuilder(collection).build();
        }
        else {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (Object el : collection) {
                arrayBuilder.add(toJsonValue(el));
            }
            return arrayBuilder.build();
        }

    }

    private JsonValue objectToJsonValue(Object obj) {

        JsonObjectBuilder builder = Json.createObjectBuilder();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean isAccessible = field.canAccess(obj);
            if (!isAccessible) { field.setAccessible(true); }

            try {
                JsonValue jsonValue = toJsonValue(field.get(obj));
                builder.add(field.getName(), jsonValue);
            }
            catch (Exception e) {
                System.out.println("Can't create JSON from the given object.");
                System.out.println(e.getMessage());
            }

            if (!isAccessible) { field.setAccessible(false); }
        }
        return builder.build();
    }

    private JsonValue arrayToJsonValue(Object arrayObj) {

        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        if (Array.getLength(arrayObj) == 0) {
            return jsonArray.build();
        }

        Class<?> componentType = arrayObj.getClass().getComponentType();

        if (componentType.isPrimitive()) {
            if (componentType.equals(byte.class)) {
                byte[] array = (byte[])arrayObj;
                for (byte b : array) {
                    jsonArray.add(b);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(short.class)) {
                short[] array = (short[])arrayObj;
                for (short s : array) {
                    jsonArray.add(s);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(int.class)) {
                int[] array = (int[])arrayObj;
                for (int i : array) {
                    jsonArray.add(i);
                }
                return jsonArray.build();
            }

            else if (componentType.equals(long.class)) {
                long[] array = (long[])arrayObj;
                for (long l : array) {
                    jsonArray.add(l);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(float.class)) {
                float[] array = (float[])arrayObj;
                for (float f : array) {
                    jsonArray.add(f);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(double.class)) {
                double[] array = (double[])arrayObj;
                for (double d : array) {
                    jsonArray.add(d);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(boolean.class)) {
                for (boolean el : (boolean[])arrayObj) {
                    Boolean value = (Boolean)el;
                    jsonArray.add(value ? JsonValue.TRUE : JsonValue.FALSE);
                }
                return jsonArray.build();
            }
            else if (componentType.equals(char.class)) {
                for (char el : (char[])arrayObj) {
                    jsonArray.add(Json.createValue(String.valueOf(el)));
                }
                return jsonArray.build();
            }
            else {
                String[] array = (String[])arrayObj;
                return Json.createArrayBuilder(Arrays.asList(array)).build();
            }
        }
        else {
            for (Object el : (Object[])arrayObj) {
                jsonArray.add(toJsonValue(el));
            }
            return jsonArray.build();
        }
    }

    private boolean isCollection(Class<?> type) {
        boolean result = false;

        Class<?>[] interfaces = type.getInterfaces();

        if (Arrays.asList(interfaces).contains(Collection.class)) {
            return true;
        }

        for (Class<?> clazz : interfaces) {
            boolean isCollectionInInterfacesHierarchy = isCollection(clazz);
            if (isCollectionInInterfacesHierarchy) {
                result = true;
                break;
            }
        }

        if (!result) {
            Class<?> superClass = type.getSuperclass();
            if (superClass != null) {
                result = isCollection(superClass);
            }
        }

        return result;
    }

    private boolean isPrimitiveWrapper(Class<?> type) {
        return
                type.equals(Byte.class) ||
                        type.equals(Short.class) ||
                        type.equals(Integer.class) ||
                        type.equals(Long.class) ||
                        type.equals(Float.class) ||
                        type.equals(Double.class) ||
                        type.equals(Boolean.class) ||
                        type.equals(Character.class);
    }
}