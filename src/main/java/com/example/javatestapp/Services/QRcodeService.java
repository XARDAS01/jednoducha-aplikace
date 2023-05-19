package com.example.javatestapp.Services;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;

public class QRcodeService {
    public static boolean create (String data, String format, String fileName, int width, int height) throws WriterException, IOException {
        try {
            BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
            MatrixToImageWriter.writeToPath(matrix, format, Paths.get("src/main/assets/QRcodes/" + fileName + "." + format));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String read (String qrPath) {
        String result = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(new FileInputStream(qrPath));
            BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(bufferedImageLuminanceSource));
            Result binaryBitmapResult = new MultiFormatReader().decode(binaryBitmap);
            result = binaryBitmapResult.getText();
        } catch (Exception e) {
            result = String.valueOf(e);
        } finally {
            return result;
        }
    }
}
