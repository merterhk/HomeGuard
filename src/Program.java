
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.media.Manager;
import javax.media.Player;

public class Program {

    public static void main(String[] args) {
        Program p = new Program();
        Photographer ph = new Photographer();
        
        
        BufferedImage bim1 = null;
        BufferedImage bim2 = null;

        System.out.println("Kamera hazýrlanýyor..");
        try {
            ph.basla();
            bim1 = ph.takePhoto();
            //saveJPG(bim1, "c:\\bim1 gece.jpg");
            //Thread.sleep(10000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (true) {
            long a = System.currentTimeMillis();
//            System.out.println("Baþladý..");
            try {
                //ph.takePhoto();
                bim2 = bim1;
//                System.out.println("bim2 çekildi");
                System.out.println("Kameraya el salla");
                bim1 = ph.takePhoto();
                System.out.println("Resim tamam..");
                Thread.sleep(500);
                //bim2 = ph.takePhoto();
               /// System.out.println("bim1 çekildi");

                ImageCompare ic = new ImageCompare(bim1, bim2);
                //ic.saveJPG(bim1, "ilk cekim");

                ic.setParameters(4, 3, 10, 10);
                //ic.setParameters(8, 6, 5, 10);
                // Display some indication of the differences in the image.
                //ic.setDebugMode(1);
                // Compare.
                ic.compare();
                // Display if these images are considered a match according to our parameters.
                System.out.println("Match: " + ic.match());
                // If its not a match then write a file to show changed regions.
                if (!ic.match()) {
                    ic.saveJPG(ic.getChangeIndicator(), "c:\\sonuc.jpg");
                    ic.saveJPG(bim1, "c:\\bim1.jpg");
                    ic.saveJPG(bim2, "c:\\bim2.jpg");
                    break;
                }

                //Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            long fark = System.currentTimeMillis() - a;
            System.out.println("bitti (" + fark + "ms.)");
        }
        ph.bitir();
        alarm();
        //System.out.println("Kalk lan biþeyler oluyor.. ver alarmý..");
    }

    public static void alarm() {
        try {
            while (true) {
                File f = new File("c:\\alarm.wav");
                Player m_Player = Manager.createPlayer(f.toURI().toURL());
                m_Player.start();
                Thread.sleep(3000);
            }
        } catch (Exception e) {
        }

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
