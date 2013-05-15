/**
 * Project: analysis-entrance
 * 
 * File Created at May 15, 2013
 * $Id$Corporation
 * 
 * Copyright 2013-2015 Colomob.com Corporation Limited.
 * All rights reserved.
 */
package com.colomob.immortal.analysis.entrance.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

/**
 * @author baowp
 * 
 */
public class BeanUtil {

	private static String GET = "get";
	private static Build extendBuild = new Build() {
		public boolean check(Map.Entry<?, Entry<?, Class<?>>> entry) {
			return entry.getValue().getKey() != null;
		}
	};
	private static Build extendAllBuild = new Build();

	/**
	 * inherit not null properties from parent(s) to self
	 * 
	 * @since 1.0
	 */
	public static <T> T extend(T self, Object... parent) {
		for (Object obj : parent) {
			inherit(self, obj, extendBuild);
		}
		return self;
	}

	/**
	 * @since 1.0
	 * @return new instance inherit parents' not null properties
	 */
	public static <T> T extendz(Class<T> clazz, Object... parent) {
		T newInstance = null;
		try {
			newInstance = clazz.newInstance();
		} catch (Exception e) {
		}
		extend(newInstance, parent);
		return newInstance;
	}

	/**
	 * inherit all properties from parent(s) to self
	 * 
	 * @since 1.0
	 */
	public static <T> T extendAll(T self, Object... parent) {
		for (Object obj : parent) {
			inherit(self, obj, extendAllBuild);
		}
		return self;
	}

	private static void inherit(Object self, Object parent, Build build) {
		Map<String, Entry<?, Class<?>>> map = new HashMap<String, Entry<?, Class<?>>>();
		for (Method method : parent.getClass().getMethods()) {
			String name = method.getName();
			if (name.startsWith(GET)) {
				try {
					map.put(getToSet(name),
							new Entry<Object, Class<?>>(method.invoke(parent),
									method.getReturnType()));
				} catch (Exception e) {
				}
			}
		}
		for (Map.Entry<String, Entry<?, Class<?>>> entry : map.entrySet()) {
			if (!build.check(entry))
				continue;
			String key = build.getKey(entry);
			Entry<?, Class<?>> value = build.getValue(entry);
			try {
				Method method = self.getClass()
						.getMethod(key, value.getValue());
				method.invoke(self, value.getKey());
			} catch (Exception e) {
			}
		}
	}

	private static String getToSet(String getName) {
		return new StringBuilder(getName).deleteCharAt(0).insert(0, 's')
				.toString();
	}

	public static Map<String, Object> bean2map(Object bean) {
		if (bean == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();
		PropertyDescriptor[] propertyDescriptors = BeanUtils
				.getPropertyDescriptors(bean.getClass());
		for (PropertyDescriptor p : propertyDescriptors) {
			if ("class".equals(p.getName())) {
				continue;
			}
			Method read = p.getReadMethod();
			try {
				Object value = read.invoke(bean);
				if (value != null) {
					map.put(p.getName(), value);
				}
			} catch (Exception e) {
			}
		}
		return map;
	}

	private static class Build {
		protected boolean check(Map.Entry<?, Entry<?, Class<?>>> entry) {
			return true;
		}

		protected String getKey(Map.Entry<?, Entry<?, Class<?>>> entry) {
			return (String) entry.getKey();
		}

		protected Entry<?, Class<?>> getValue(
				Map.Entry<?, Entry<?, Class<?>>> entry) {
			return entry.getValue();
		}
	}

	private static class Entry<K, V> {

		private K key;
		private V value;

		private Entry(K k, V v) {
			key = k;
			value = v;
		}

		private K getKey() {
			return key;
		}

		private V getValue() {
			return value;
		}

	}
}
