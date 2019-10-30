package tn.esprit.service;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

import java.io.File;
import java.lang.reflect.Field;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class testjava {
	
	
	
	
	static String SRC_PATH = "C:/Recognize/";
	static String TESS_DATA = "C:/Programs/ocr/Tesseract-OCR/tessdata";
	
	// Create tess obj
	static Tesseract tesseract = new Tesseract();
	
	static {

            
		  //  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);		
		    tesseract.setDatapath("C://Program Files//ocr//Tesseract-OCR//tessdata");
		}
	
	

	String extractTextFromImage(Mat inputMat) {
		String result = "";
		Mat gray = new Mat();
		
		// Convert to gray scale
		cvtColor(inputMat, gray, COLOR_BGR2GRAY);
		Imgcodecs.imwrite(SRC_PATH + "gray.png", gray);
		
		//  Apply closing, opening
		//Mat element = getStructuringElement(MORPH_RECT, new Size(2, 2), new Point(1, 1));
		//dilate(gray, gray, element);
		//erode(gray, gray, element);

		//imwrite(SRC_PATH + "closeopen.png", gray);

		try {
			// Recognize text with OCR
			result = tesseract.doOCR(new File(SRC_PATH + "gray.png"));
		} catch (TesseractException e) {
			e.printStackTrace();
		}

		return result;
	}
	

}
