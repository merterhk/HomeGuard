import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.media.Manager;

public class WatchHome extends Thread {

    hgView hv;
    Photographer ph = new Photographer();
    BufferedImage bim1 = null;
    BufferedImage bim2 = null;

    public WatchHome(hgView hv) {
        try {
            this.hv = hv;

        } catch (Exception ex) {
            System.out.println("Const err");
        }
    }

    public void kamerayiHazirla() {
        System.out.println("Kamera hazýrlanýyor..");
        hv.setDurum("Kamera hazýrlanýyor..");
        try {
            ph.basla();
            bim1 = ph.takePhoto();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        hv.setDurum("Hazýr..");
    }

    public void run() {

        long a = 0, fark = 0;
        kamerayiHazirla();
        while (hv.isWatch()) {
            a = System.currentTimeMillis();
//            System.out.println("Baþladý..");
            try {
                //ph.takePhoto();
                bim2 = bim1;
//                System.out.println("bim2 çekildi");
                ///System.out.println("Kameraya el salla");
                //hv.setDurum("Peynir de..");
                bim1 = ph.takePhoto();
                //hv.setDurum("Þip Þak");
                //System.out.println("Resim tamam..");
                bim1.flush();

                hv.pcount++;
                hv.setDurum("Kontrol sayýsý : " + hv.pcount + " Ýþlem süresi : " + fark + "ms");

                //Thread.sleep(500);

                //hv.showPic(bim1);
                //bim2 = ph.takePhoto();
                /// System.out.println("bim1 çekildi");

                ImageCompare ic = new ImageCompare(bim1, bim2);
                //ic.saveJPG(bim1, "ilk cekim");

                ic.setParameters(16, 12, 3, 15);
                //ic.setParameters(8, 6, 5, 10);
                // Display some indication of the differences in the image.
                //ic.setDebugMode(1);
                // Compare.
                ic.compare();
                // Display if these images are considered a match according to our parameters.
                System.out.println("Match: " + ic.match());
                // If its not a match then write a file to show changed regions.
                if (!ic.match()) {
                    //hv.setDurum("DEÐÝÞÝKLÝK FARKEDÝLDÝ!");

                    ImageCompare.saveJPG(ic.getChangeIndicator(), "c:\\sonuc.jpg");
                    ImageCompare.saveJPG(bim1, "c:\\bim1.jpg");
                    ImageCompare.saveJPG(bim2, "c:\\bim2.jpg");

                    // hv.showPic(ic.getChangeIndicator());
                    kamerayiKapat();
                    File f = new File("c:\\alarm.wav");

                    while (true) {
                        Manager.createPlayer(f.toURI().toURL()).start();
                        Thread.sleep(3000);
                    }
                }

                //Thread.sleep(1000);
            } catch (Exception ex) {
                //kamerayiKapat();
                ex.printStackTrace();
            }
            //kamerayiKapat();
            fark = System.currentTimeMillis() - a;
            //System.out.println("bitti (" + fark + "ms.)");
        }
        System.out.println("Thread sonlandý..");
        kamerayiKapat();
        //ph.bitir();

        //System.out.println("Kalk lan biþeyler oluyor.. ver alarmý..");
    }

    public void kamerayiKapat() {
        ph.bitir();
        hv.setDurum("Kamera kapatýldý..");
    }

    protected static void saveJPG(Image img, String filename) {
        BufferedImage bi = imageToBufferedImage(img);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
        } catch (java.io.FileNotFoundException io) {
            System.out.println("File Not Found");
        }
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bi);
        param.setQuality(0.8f, false);
        encoder.setJPEGEncodeParam(param);
        try {
            encoder.encode(bi);
            out.close();
        } catch (java.io.IOException io) {
            System.out.println("IOException");
        }
    }

    protected static BufferedImage imageToBufferedImage(Image img) {
        BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, null, null);
        return bi;
    }
}
