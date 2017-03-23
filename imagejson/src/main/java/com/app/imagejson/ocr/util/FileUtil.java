package com.app.imagejson.ocr.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.app.imagejson.ocr.exceptions.ImagePathException;

public class FileUtil {
	public static void writeToFile(String file, String text) throws ImagePathException {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			writer.write(text);
		} catch (IOException ex) {
			throw new ImagePathException("Exception writing to output folder.");
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {/* ignore */
			}
		}
	}
}
