package stu.napls.copdmanage.core.response;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import stu.napls.copdmanage.core.constant.Code;
import stu.napls.copdmanage.core.exception.SystemException;

import java.util.Collection;
import java.util.Map;

/**
 * @author Yepeng Ding
 */
public class Assert {
    public static void isTrue(boolean expression, int code, String message) {
        if (!expression) {
            throw new SystemException(code, message);
        }
    }

    public static void isTrue(boolean expression) {
        isTrue(expression, Code.FAILURE, "[Assertion failed] - this expression must be true");
    }

    public static void isNull(Object object, int code, String message) {
        if (object != null) {
            throw new SystemException(code, message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new SystemException(message);
        }
    }

    public static void isNull(Object object) {
        isNull(object, Code.FAILURE, "[Assertion failed] - the object argument must be null");
    }

    public static void notNull(Object object, int code, String message) {
        if (object == null) {
            throw new SystemException(code, message);
        }
    }

    public static void notNull(Object object, int code) {
        if (object == null) {
            throw new SystemException(code, "[Assertion failed] - the object argument must be null");
        }
    }

    public static void notNull(Object object) {
        notNull(object, Code.FAILURE, "[Assertion failed] - this argument is required; it must not be null");
    }

    public static void hasLength(String text, int code, String message) {
        if (!StringUtils.hasLength(text)) {
            throw new SystemException(code, message);
        }
    }

    public static void hasLength(String text) {
        hasLength(text, Code.FAILURE, "[Assertion failed] - this String argument must have length; it must not be null or empty");
    }

    public static void hasText(String text, int code, String message) {
        if (!StringUtils.hasText(text)) {
            throw new SystemException(code, message);
        }
    }

    public static void hasText(String text) {
        hasText(text, Code.FAILURE, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
    }

    public static void notEmpty(Object[] array, int code, String message) {
        if (ObjectUtils.isEmpty(array)) {
            throw new SystemException(code, message);
        }
    }

    public static void notEmpty(Object[] array) {
        notEmpty(array, Code.FAILURE, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Collection<?> collection, int code, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new SystemException(code, message);
        }
    }

    public static void notEmpty(Collection<?> collection) {
        notEmpty(collection, Code.FAILURE, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
    }

    public static void notEmpty(Map<?, ?> map, int code, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new SystemException(code, message);
        }
    }

    public static void notEmpty(Map<?, ?> map) {
        notEmpty(map, Code.FAILURE, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    public static void isInstanceOf(Class<?> clazz, Object obj) {
        isInstanceOf(clazz, obj, "");
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        notNull(type, Code.FAILURE, "Type to check against must not be null");
        if (!type.isInstance(obj)) {
            throw new IllegalArgumentException((StringUtils.hasLength(message) ? message + " " : "") + "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
        }
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        isAssignable(superType, subType, "");
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        notNull(superType, Code.FAILURE, "Type to check against must not be null");
        if (subType == null || !superType.isAssignableFrom(subType)) {
            throw new IllegalArgumentException(message + subType + " is not assignable to " + superType);
        }
    }

    public static void state(boolean expression, int code, String message) {
        if (!expression) {
            throw new SystemException(code, message);
        }
    }

    public static void state(boolean expression) {
        state(expression, Code.FAILURE, "[Assertion failed] - this state invariant must be true");
    }
}
