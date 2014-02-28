package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import javolution.util.FastBitSet;
import javolution.util.Index;
import util.MyFastBitSet;

public class Test {

	public static <T> void foo(Class<? extends T> type, ArrayList<T> callback) {

		System.out.println("here is foo... a is " + type.getName());
	}

	public static void MyMethod(int criterion) {
		Class<?> a;

		if (criterion > 0) {
			a = Integer.class;
			System.out.println(a.getName());
		} else {
			a = Short.class;
			System.out.println(a.getName());
		}

		useFoo(a);

	}

	public static <T> void useFoo(Class<T> a) {

		Class<?> b = a.getSuperclass();

		System.out.println(b.toString());

		ArrayList<T> arr = new ArrayList<T>();

		try {

			Object newinstance = (Object) b.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

		foo(a, new ArrayList<T>());

	}

	public static void tt() {

		// HashSet<String> abc = new HashSet<String>();

		// String abc = "100";

		Number abc = 100;

		Class<?> clazz = abc.getClass();
		// Class<?> clazz1 = clazz.getSuperclass();

		System.out.println(clazz.toString());
		// System.out.println(clazz1.toString());

		try {

			// Number haha = (Number)clazz.newInstance();

			// Number yy = clazz.getConstructors();
			// Object bubu = clazz1.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	public static void main(String[] args) {

	}

}
