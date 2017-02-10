/*
MIT License

Copyright (c) [2017] [ukiuni]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.ukiuni.spring.session.jpa;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class MapSerializer {

	public static Map<String, Object> desirialize(byte[] data) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(data));
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) objectInputStream.readObject();
			objectInputStream.close();
			return map;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] serialize(Map<String, Object> map) {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
			objectOutputStream.writeObject(map);
			byte[] buffer = out.toByteArray();
			objectOutputStream.close();
			return buffer;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
