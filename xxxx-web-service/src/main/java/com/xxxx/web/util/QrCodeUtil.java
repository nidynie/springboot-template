package com.xxxx.web.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QrCodeUtil {

    public static String generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriterUtil qrCodeWriter = new QRCodeWriterUtil();

        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", outputStream);

        return "data:image/jpg;base64," + new String(Base64.encodeBase64(outputStream.toByteArray()));

    }


    public static void main(String args[]) throws Exception {
        System.out.println(generateQRCodeImage("xxxxxxxxxxx", 450, 450));
    }
}
