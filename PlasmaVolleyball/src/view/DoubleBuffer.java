package view;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;


public abstract class DoubleBuffer extends Frame {
    private int bufferWidth;
    private int bufferHeight;
    private Image bufferImage;
    private Graphics bufferGraphics;
    
    public DoubleBuffer(String name) {
    	super(name);
    }

    public void paint(Graphics g){
        if (bufferWidth != getSize().width || bufferHeight != getSize().height || bufferImage == null || bufferGraphics == null)
            resetBuffer();
        
        if (bufferGraphics != null) {
            bufferGraphics.clearRect(0, 0, bufferWidth, bufferHeight);
            paintBuffer(bufferGraphics);
            g.drawImage(bufferImage, 0, 0, this);
        }
    }

    private void resetBuffer(){
        bufferWidth = getSize().width;
        bufferHeight = getSize().height;

        if (bufferGraphics != null){
            bufferGraphics.dispose();
            bufferGraphics = null;
        }
        if (bufferImage != null){
            bufferImage.flush();
            bufferImage = null;
        }         
        System.gc();

        bufferImage = createImage(bufferWidth,bufferHeight);
        bufferGraphics = bufferImage.getGraphics();
    }
    
    public abstract void paintBuffer(Graphics g);

    
}
