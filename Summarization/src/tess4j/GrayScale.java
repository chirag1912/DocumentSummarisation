package tess4j;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.*;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOInvalidTreeException;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class GrayScale {

	
		public static final String DENSITY_UNITS_NO_UNITS = "00";
		public static final String DENSITY_UNITS_PIXELS_PER_INCH = "01";
		public static final String DENSITY_UNITS_PIXELS_PER_CM = "02";

		private BufferedImage gridImage;

		private void saveGridImage(File output) throws IOException {
		  //  output.delete();

		    final String formatName = "png";

		    for (Iterator<ImageWriter> iw = ImageIO.getImageWritersByFormatName(formatName); iw.hasNext();) {
		       ImageWriter writer = iw.next();
		       ImageWriteParam writeParam = writer.getDefaultWriteParam();
		       ImageTypeSpecifier typeSpecifier = ImageTypeSpecifier.createFromBufferedImageType(BufferedImage.TYPE_INT_RGB);
		       IIOMetadata metadata = writer.getDefaultImageMetadata(typeSpecifier, writeParam);
		       if (metadata.isReadOnly() || !metadata.isStandardMetadataFormatSupported()) {
		          continue;
		       }

		       setDPI(metadata);

		       final ImageOutputStream stream = ImageIO.createImageOutputStream("F:\\Project(Summarization)\\Summarization\\images\\pollu.png");
		       try {
		          writer.setOutput(stream);
		          writer.write(metadata, new IIOImage(gridImage, null, metadata), writeParam);
		       } finally {
		          stream.close();
		       }
		       break;
		    }
		 }

		 private void setDPI(IIOMetadata metadata) throws IIOInvalidTreeException {

		    // for PMG, it's dots per millimeter
		    double dotsPerMilli = 1.0 * 300 / 10 / 2.54;

		    IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
		    horiz.setAttribute("value", Double.toString(dotsPerMilli));

		    IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
		    vert.setAttribute("value", Double.toString(dotsPerMilli));

		    IIOMetadataNode dim = new IIOMetadataNode("Dimension");
		    dim.appendChild(horiz);
		    dim.appendChild(vert);

		    IIOMetadataNode root = new IIOMetadataNode("javax_imageio_1.0");
		    root.appendChild(dim);

		    metadata.mergeTree("javax_imageio_1.0", root);
		 }
	static public void main(String args[]) throws Exception {
		GrayScale obj = new GrayScale();
		obj.saveGridImage(new File("F:\\Project(Summarization)\\Summarization\\images\\pollution1.png"));
	}
}