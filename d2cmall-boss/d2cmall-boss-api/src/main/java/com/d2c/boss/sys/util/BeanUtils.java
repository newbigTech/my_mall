package com.d2c.boss.sys.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.util.Assert;

/**
 * Java Bean 应用类 扩展 , 提供一些反射方面缺失功能的封装.
 * 
 * @author xuhua
 * @FIXME
 */
public class BeanUtils {

	protected static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	private BeanUtils() {
	}

	/**
	 * 获取对象变量值
	 */
	public static Object getPropertyValue(Object object, String propertyName) {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		try {
			return Ognl.getValue(propertyName, object);
		} catch (OgnlException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 设置对象变量值
	 */
	public static void setPropertyValue(Object object, String propertyName, Object newValue) {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		try {
			Ognl.setValue(propertyName, object, newValue);
		} catch (OgnlException e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * 暴力设置对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static void forceSetPropertyValue(Object object, String propertyName, Object newValue)
			throws BeanAccessException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		PropertyDescriptor descriptor;
		try {
			descriptor = new PropertyDescriptor(propertyName, object.getClass());
			Method method = descriptor.getWriteMethod();
			method.invoke(object, new Object[] { newValue });
		} catch (Exception e) {
			Field field = getDeclaredField(object, propertyName);
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			try {
				field.set(object, newValue);
			} catch (IllegalAccessException e1) {
				logger.info("Error won't happen");
			}
			field.setAccessible(accessible);
		}

	}

	/**
	 * 暴力获取对象变量值,忽略private,protected修饰符的限制.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static Object forceGetPropertyValue(Object object, String propertyName) throws BeanAccessException {
		Assert.notNull(object);
		Assert.hasText(propertyName);

		try {
			PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, object.getClass());
			Method method = descriptor.getReadMethod();
			return method.invoke(object, new Object[0]);
		} catch (Exception e) {
			Field field = getDeclaredField(object, propertyName);

			Object result = null;
			boolean accessible = field.isAccessible();
			field.setAccessible(true);
			try {
				result = field.get(object);
			} catch (IllegalAccessException e1) {
				logger.info("error wont' happen");
			}
			field.setAccessible(accessible);

			return result;
		}

	}

	/**
	 * 按Filed的类型取得Field列表.
	 */
	public static List<Field> getFieldsByType(Object object, Class<?> type) {
		List<Field> list = new ArrayList<Field>();
		try {
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				if (field.getType().isAssignableFrom(type)) {
					list.add(field);
				}
			}
		} catch (SecurityException e) {
			logger.info("Error won't happen");
		}
		return list;
	}

	/**
	 * 拷贝属性,默认忽略拷贝Hibernate字节码增强的属性
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @throws BeansException
	 *             异常时抛出.
	 */
	public static void copyProperties(Object source, Object target) throws BeansException {
		final String[] ignoreProperties = new String[] { "interceptFieldCallback" };
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
	}

	/**
	 * 拷贝属性
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param ignoreProperties
	 *            忽略拷贝的属性
	 * @throws BeansException
	 *             异常时抛出.
	 */
	public static void copyProperties(Object source, Object target, String[] ignoreProperties) throws BeansException {
		org.springframework.beans.BeanUtils.copyProperties(source, target, ignoreProperties);
	}

	public static Class<?> getPropertyType(Class<?> clazz, String propertyName) {
		Field field = getDeclaredField(clazz, propertyName);
		return field != null ? field.getType() : null;
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Object object, String propertyName) throws BeanAccessException {
		Assert.notNull(object);
		Assert.hasText(propertyName);
		return getDeclaredField(object.getClass(), propertyName);
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static Field getDeclaredField(Class<?> clazz, String propertyName) {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(propertyName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static List<Field> getAllDeclaredFields(Class<?> clazz) {
		Assert.notNull(clazz);
		List<Field> declaredFields = new ArrayList<Field>();
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			for (Field field : superClass.getDeclaredFields()) {
				declaredFields.add(field);
			}
		}
		return declaredFields;
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 * 
	 * @throws BeanAccessException
	 *             如果没有该Field时抛出.
	 */
	public static List<Method> getAllDeclaredMethods(Class<?> clazz) {
		Assert.notNull(clazz);
		List<Method> declaredFields = new ArrayList<Method>();
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			for (Method method : superClass.getDeclaredMethods()) {
				declaredFields.add(method);
			}
		}
		return declaredFields;
	}

	public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, String propertyName) {
		Assert.notNull(clazz);
		Assert.hasText(propertyName);
		PropertyDescriptor propertyDescriptor = null;
		for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
			propertyDescriptor = org.springframework.beans.BeanUtils.getPropertyDescriptor(clazz, propertyName);
			if (propertyDescriptor.getPropertyType() == null)
				continue;
		}
		return propertyDescriptor;
	}

	/**
	 * 获取声明属性
	 * 
	 */
	public static String[] getDeclaredPropertys(Class<?> clazz) {
		String[] props = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			Vector<String> propVector = new Vector<String>();
			for (PropertyDescriptor propDes : propertyDescriptors) {
				// 如果是class属性 跳过
				if (propDes.getName().equals("class") || propDes.getReadMethod() == null
						|| propDes.getWriteMethod() == null)
					continue;
				propVector.add(propDes.getName());
			}
			props = new String[propVector.size()];
			propVector.toArray(props);
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
		}
		return props;
	}

	/**
	 * 检测类是否包含属性
	 */
	public static boolean hasProperty(Class<?> clazz, String propertyName) {
		boolean has = false;
		try {
			new PropertyDescriptor(propertyName, clazz);
			has = true;
		} catch (IntrospectionException e) {
			logger.info(clazz.getName() + ":" + e.getMessage());
			return false;
		}
		return has;
	}

	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {
		BeanInfo beanInfo = getBeanInfo(clazz);
		return beanInfo.getPropertyDescriptors();
	}

	/**
	 * 根据bean的类取得bean的信息
	 * 
	 * @param clazz
	 * @return
	 */
	public static BeanInfo getBeanInfo(Class<?> clazz) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
		} catch (IntrospectionException e) {
			logger.error(e.getMessage(), e);
		}
		return beanInfo;
	}

	/**
	 * 转化XML格式的到对象
	 * 
	 * @param element
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertXmlToObjByAttribute(Class<T> clazz, Element element) {
		Object object = null;
		try {
			object = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		if (object == null)
			return null;
		// 取得对象所有属性描述信息,根据属性名从xml文件中读取信息
		Iterator iterator = element.elementIterator();
		while (iterator.hasNext()) {
			Element temp = (Element) iterator.next();
			String _propname = temp.attributeValue("name");
			String value = temp.getText();
			setPropertyValue(object, _propname, value);
		}
		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public static <T> T convertXmlToObj(Class<T> clazz, Element element) {
		Object object = null;
		try {
			object = clazz.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		if (object == null)
			return null;
		// 取得对象所有属性描述信息,根据属性名从xml文件中读取信息
		PropertyDescriptor[] propDescs = BeanUtils.getPropertyDescriptors(clazz);
		for (int i = 0; i < propDescs.length; i++) {
			String propname = propDescs[i].getName();
			String value = element.elementText(propname);
			if (value != null && value.trim().length() > 0)
				BeanUtils.setPropertyValue(object, propname, value);
		}
		return (T) object;
	}

}
