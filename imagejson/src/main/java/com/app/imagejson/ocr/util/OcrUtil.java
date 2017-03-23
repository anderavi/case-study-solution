package com.app.imagejson.ocr.util;

import static org.bytedeco.javacpp.lept.pixDestroy;
import static org.bytedeco.javacpp.lept.pixRead;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;

import com.app.imagejson.ocr.exceptions.OcrException;

public class OcrUtil {

	public static String doOCR(String file) throws OcrException {
		BytePointer outText;

		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata
		// path
		if (api.Init(".", "ENG") != 0) {
			throw new OcrException("Could not initialize tesseract ocr.");
		}

		try {
			// Open input image with leptonica library
			PIX image = pixRead(file);
			api.SetImage(image);
			// Get OCR result
			outText = api.GetUTF8Text();
			String string = outText.getString();
			// Destroy used object and release memory
			api.End();
			outText.deallocate();
			pixDestroy(image);
			return string;
		} catch (Exception e) {
			throw new OcrException("Exception converting image to text with tessaract ocr");
		}

	}
}
