package com.org.app.customui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyleConstants;

public class ButtonGradient extends JButton {

    public float getSizeSpeed() {
        return sizeSpeed;
    }

    public void setSizeSpeed(float sizeSpeed) {
        this.sizeSpeed = sizeSpeed;
    }

    public Color getColor1() {
        return color1;
    }
    
    public void setColor1(String hex) {
        setColor1(Color.decode(hex));
    }
    
    public void setColor2(String hex) {
        setColor2(Color.decode(hex));
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }
    
    public void setGradientFocus(int gf) {
        this.gradientFocus = gf;
    }
    
    public void setColorHover1(Color cHover1) {
        if(cHover1 == null) cHover1 = new Color(255, 255, 255, 120); 
       this.cHover1 = cHover1;
    }
    
    public void setColorHover2(Color cHover2) {
        if(cHover2 == null) cHover2 = new Color(255, 255, 255, 5); 
        this.cHover2 = cHover2;
    }
    
    public void borderRadius(float percent) {
        this.borderRadius = percent;
    }

    public Color getHoverColorFore() {
        return hoverColorFore;
    }

    public void setHoverColorFore(Color hoverColorFore) {
        this.hoverColorFore = hoverColorFore;
    }
 
    
    private Font font = new Font("Corbel",Font.PLAIN,12);
    private Color color1 = Color.decode("#0099F7");
    private Color color2 = Color.decode("#F11712");
    private Color cHover1 = new Color(255, 255, 255, 120);
    private Color cHover2 = new Color(255, 255, 255, 5);
    private float borderRadius = 0.4f;
    private Cursor btnCursor = new Cursor(Cursor.HAND_CURSOR);
    private int gradientFocus = 0;
    private  Timer timer;
    private  Timer timerPressed;
    private float alpha = 0.3f;
    private boolean mouseOver;
    private boolean pressed;
    private Point pressedLocation;
    private float pressedSize;
    private float sizeSpeed = 1f;
    private float alphaPressed = 0.5f;
    private boolean isBoldWhenHover = false;
    private Color hoverColorFore = Color.WHITE;

    
    
    public ButtonGradient(String color1, String color2, int range) {
        this();
        setColor1(color1);
        setColor2(color2);
        setGradientFocus(range);
//        timer = new Timer(10,e ->{});       
    }
    
    public ButtonGradient(String color1, String color2, Color h1, Color h2,  int range) {
        this();
        setColor1(color1);
        setColor2(color2);
        setGradientFocus(range);
        setHoverColor(h1, h2);
        
//        timer = new Timer(10,e ->{});       
    }
    
    public void setHoverColor(Color h1, Color h2) {
        setColorHover1(h1);
        setColorHover2(h2);
    }

    public Cursor getBtnCursor() {
        return btnCursor;
    }

    public void setBtnCursor(Cursor btnCursor) {
        this.btnCursor = btnCursor;
        this.setCursor(this.btnCursor);
    }
    
    
    
    public ButtonGradient(boolean hoverFore) {
        this();
        isBoldWhenHover = hoverFore;
        if(isBoldWhenHover) buttonMouseOverForeground(hoverColorFore);
    }
    public ButtonGradient() {
        setContentAreaFilled(false);
//        setForeground(Color.WHITE);
        this.setCursor(getBtnCursor());
        setBorder(new EmptyBorder(10, 20, 10, 20));
        pressedSize = this.getWidth();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
                timer.start();
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
                timer.start();
            }

            @Override
            public void mousePressed(MouseEvent me) {
//                pressedSize = 0;
//                pressedSize = 150;
                alphaPressed = 0.2f;
                pressed = true;
                pressedLocation = me.getPoint();
                timerPressed.setDelay(0);
                timerPressed.start();
            }
        });
        
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (mouseOver) {
                    if (alpha < 0.6f) {
                        alpha += 0.02f;
                        repaint();
                    } else {
                        alpha = 0.6f;
                        timer.stop();
                        repaint();
                    }
                } else {
                    if (alpha > 0.3f) {
                        alpha -= 0.05f;
                        repaint();
                    } else {
                        alpha = 0.2f;
                        timer.stop();
                        repaint();
                    }
                }
            }
        });
        timerPressed = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pressedSize += getSizeSpeed();
                
                if (alphaPressed <= 0) {
                    pressed = false;
                    timerPressed.stop();
                } else {
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        int width = getWidth();
        int height = getHeight();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //  Create Gradients Color
//        GradientPaint gra = new GradientPaint(0, 0, color1, width, 0, color2);
        GradientPaint gp = new GradientPaint(0, 0, color1, width, 0, color2);
        g2.setPaint(gp);
//        g2.fillRoundRect(0, 0, width, height, height, height);
        g2.fillRoundRect(0, 0, width, height,(int) (height*borderRadius),(int) (height*borderRadius));
        
        //  Add Style
        createStyle(g2);
        if (pressed) {
            paintPressed(g2);
        }
        g2.dispose();
        grphcs.drawImage(img, 0, 0, null);
        super.paintComponent(grphcs);
//        if(isBoldWhenHover) buttonMouseOverForeground(hoverColorFore);
    }

    private void createStyle(Graphics2D g2) {
        if(!this.isEnabled()) return;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        int width = getWidth();
        int height = getHeight();
//        GradientPaint gra = new GradientPaint(0, 0, new Color(255, 255, 255, 120), 0, height, new Color(255, 255, 255, 5));
        GradientPaint gra = new GradientPaint(0, 0, cHover1, 0, height,cHover2);
        g2.setPaint(gra);
        Path2D.Float f = new Path2D.Float();
        f.moveTo(0, 0);
//        int controll = height + height / 2;
        int controll = height * 6 ;
        g2.fillRect(0,0, width, height);
//        f.curveTo(0, 0, width / 2, controll, width, 0);
//        f.curveTo(0, width+10, width, controll, width, 0);
//        f.c(0, 0, width, controll, width, 0);
        g2.fill(f);
        
    }

    private void paintPressed(Graphics2D g2) {
//        if (pressedLocation.x - (pressedSize / 2) < 0 && pressedLocation.x + (pressedSize / 2) > getWidth()) {
//            timerPressed.setDelay(0);
//        }
        if(!this.isEnabled()) return;
            timerPressed.setDelay(1);
            alphaPressed -= 0.05f;
            if (alphaPressed < 0) {
                alphaPressed = 0;
            }
        g2.setColor(Color.BLACK);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alphaPressed));
        float x = pressedLocation.x - (pressedSize / 2);
        float y = pressedLocation.y - (pressedSize / 2);
//        g2.fillOval((int) x, (int) y, (int) pressedSize, (int) pressedSize);
//        g2.fillOval(0, this.getHeight()/2, (int) pressedSize, (int) pressedSize);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        
    }
    

    
    private JButton getButton() {return this;}
       public void buttonMouseOverForeground(Color text) {
        Color or = this.getForeground();
           System.out.println("this fore = " +or.toString());
        this.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getButton().setForeground(text);
//                boldText(true, getButton());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getButton().setForeground(or);
//                boldText(false, getButton());
            }
        }
        );
    }
       
      public void boldText(boolean bold, JButton btn) {
        Font f = btn.getFont();
        if (bold) {
            btn.setFont(new Font(f.getName(), Font.BOLD, f.getSize()));
        } else {
            btn.setFont(new Font(f.getName(), Font.PLAIN, f.getSize()));
        }
    }
}
