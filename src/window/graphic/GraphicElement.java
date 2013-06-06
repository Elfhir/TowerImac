package window.graphic;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

public class GraphicElement extends Graphics2D {

	public GraphicElement() {
		super();
	}
	
	public GraphicElement(Color c) {
		super();
		this.setColor(c);
	}

	@Override
	public void addRenderingHints(Map<?, ?> arg0) {
	}

	@Override
	public void clip(Shape arg0) {
	}

	@Override
	public void draw(Shape arg0) {
	}

	@Override
	public void drawGlyphVector(GlyphVector arg0, float arg1, float arg2) {
	}

	@Override
	public boolean drawImage(Image arg0, AffineTransform arg1, ImageObserver arg2) {
		return false;
	}

	@Override
	public void drawImage(BufferedImage arg0, BufferedImageOp arg1, int arg2, int arg3) {
	}

	@Override
	public void drawRenderableImage(RenderableImage arg0, AffineTransform arg1) {
	}

	@Override
	public void drawRenderedImage(RenderedImage arg0, AffineTransform arg1) {
	}

	@Override
	public void drawString(String arg0, int arg1, int arg2) {
	}

	@Override
	public void drawString(String arg0, float arg1, float arg2) {
	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, int arg1, int arg2) {
	}

	@Override
	public void drawString(AttributedCharacterIterator arg0, float arg1, float arg2) {
	}

	@Override
	public void fill(Shape arg0) {
	}

	@Override
	public Color getBackground() {
		return null;
	}

	@Override
	public Composite getComposite() { 
		return null;
	}

	@Override
	public GraphicsConfiguration getDeviceConfiguration() { 
		return null;
	}

	@Override
	public FontRenderContext getFontRenderContext() { 
		return null;
	}

	@Override
	public Paint getPaint() { 
		return null;
	}

	@Override
	public Object getRenderingHint(Key arg0) {
		return null;
	}

	@Override
	public RenderingHints getRenderingHints() { 
		return null;
	}

	@Override
	public Stroke getStroke() {
		 
		return null;
	}

	@Override
	public AffineTransform getTransform() { 
		return null;
	}

	@Override
	public boolean hit(Rectangle arg0, Shape arg1, boolean arg2) { 
		return false;
	}

	@Override
	public void rotate(double arg0) {
	}

	@Override
	public void rotate(double arg0, double arg1, double arg2) {
	}

	@Override
	public void scale(double arg0, double arg1) {
	}

	@Override
	public void setBackground(Color arg0) {
	}

	@Override
	public void setComposite(Composite arg0) {
	}

	@Override
	public void setPaint(Paint arg0) {
	}

	@Override
	public void setRenderingHint(Key arg0, Object arg1) {
	}

	@Override
	public void setRenderingHints(Map<?, ?> arg0) {
	}

	@Override
	public void setStroke(Stroke arg0) {
	}

	@Override
	public void setTransform(AffineTransform arg0) {
	}

	@Override
	public void shear(double arg0, double arg1) {
	}

	@Override
	public void transform(AffineTransform arg0) {
	}

	@Override
	public void translate(int arg0, int arg1) {
	}

	@Override
	public void translate(double arg0, double arg1) {
	}

	@Override
	public void clearRect(int x, int y, int width, int height) {
	}

	@Override
	public void clipRect(int x, int y, int width, int height) {
	}

	@Override
	public void copyArea(int x, int y, int width, int height, int dx, int dy) {
	}

	@Override
	public Graphics create() {
		return null;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
	}

	@Override
	public boolean drawImage(Image img, int x, int y, ImageObserver observer) { 
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
		return false;
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
		 
		return false;
	}

	@Override
	public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2,
			int sx1, int sy1, int sx2, int sy2, Color bgcolor,
			ImageObserver observer) {
		 
		return false;
	}
	
	public void drawLine(int x1, int y1, int x2, int y2) {
	}

	@Override
	public void drawOval(int x, int y, int width, int height) {	
	}

	@Override
	public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void drawRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
	}

	@Override
	public void fillArc(int x, int y, int width, int height, int startAngle,
			int arcAngle) {
	}

	@Override
	public void fillOval(int x, int y, int width, int height) {
	}

	@Override
	public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
	}

	@Override
	public void fillRoundRect(int x, int y, int width, int height,
			int arcWidth, int arcHeight) {
	
	}

	@Override
	public Shape getClip() { 
		return null;
	}

	@Override
	public Rectangle getClipBounds() { 
		return null;
	}

	@Override
	public Color getColor() { 
		return null;
	}

	@Override
	public Font getFont() { 
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font f) { 
		return null;
	}

	@Override
	public void setClip(Shape clip) {
	}

	@Override
	public void setClip(int x, int y, int width, int height) {
	}

	@Override
	public void setColor(Color c) {
	}

	@Override
	public void setFont(Font font) {
	}

	@Override
	public void setPaintMode() {
	}

	@Override
	public void setXORMode(Color c1) {
	}

}
