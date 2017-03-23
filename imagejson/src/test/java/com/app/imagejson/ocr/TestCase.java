package com.app.imagejson.ocr;

import org.junit.Test;

public class TestCase {

	@Test
	public void givenTessBaseApi_whenImageOcrd_thenTextDisplayed() throws Exception {
		ImageJson imageJson = new ImageJson();
		imageJson.process();
	}
}