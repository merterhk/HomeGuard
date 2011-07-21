
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


public class webGUI extends javax.swing.JApplet {

    WatchHome wh = new WatchHome(this);
    boolean watch = false;
    int pcount = 0;

    public void showPic(BufferedImage bim) {
        jToggleButton1.setSelectedIcon(new ImageIcon(bim));
    }

    public void setDurum(String s) {
        jLabel1.setText(s);
    }

    public void kilitle() {
    }

    public boolean isWatch() {
        return watch;
    }

    public void showCaps(BufferedImage bim) {
    }

    public void basla() {
//        for (int i = 0; i <  10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//
//            }
//        }
        setDurum("G�zetleme a��k!");
        watch = true;
        wh = new WatchHome(this);
        wh.start();
    }

    public void durdur() {
        setDurum("G�zetleme kapal�..");
        watch = false;
    }

    /** Initializes the applet webGUI */
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guard.png"))); // NOI18N
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/cblackcat.png"))); // NOI18N
        jToggleButton1.setMaximumSize(new java.awt.Dimension(300, 300));
        jToggleButton1.setMinimumSize(new java.awt.Dimension(300, 300));
        jToggleButton1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/webcam.png"))); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        if (jToggleButton1.isSelected()) {
            basla();
        } else {
            durdur();
        }
}//GEN-LAST:event_jToggleButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
