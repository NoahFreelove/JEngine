package com.JEngine.Utility;
/*
 * Copyright (c) 2008, Harald Kuhr
 * All rights reserved.
 */

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;


public class ConvolveWithEdgeOp implements BufferedImageOp, RasterOp {

    /**
     * Alias for {@link ConvolveOp#EDGE_ZERO_FILL}.
     * @see #EDGE_REFLECT
     */
    public static final int EDGE_ZERO_FILL = ConvolveOp.EDGE_ZERO_FILL;
    /**
     * Alias for {@link ConvolveOp#EDGE_NO_OP}.
     * @see #EDGE_REFLECT
     */
    public static final int EDGE_NO_OP = ConvolveOp.EDGE_NO_OP;
    /**
     * Adds a border to the image while convolving. The border will reflect the
     * edges of the original image. This is usually a good default.
     * Note that while this mode typically provides better quality than the
     * standard modes {@code EDGE_ZERO_FILL} and {@code EDGE_NO_OP}, it does so
     * at the expense of higher memory consumption and considerable more computation.
     */
    public static final int EDGE_REFLECT = 2; // as JAI BORDER_REFLECT
    /**
     * Adds a border to the image while convolving. The border will wrap the
     * edges of the original image. This is usually the best choice for tiles.
     * Note that while this mode typically provides better quality than the
     * standard modes {@code EDGE_ZERO_FILL} and {@code EDGE_NO_OP}, it does so
     * at the expense of higher memory consumption and considerable more computation.
     * @see #EDGE_REFLECT
     */
    public static final int EDGE_WRAP = 3; // as JAI BORDER_WRAP

    private final Kernel kernel;
    private final int edgeCondition;

    private final ConvolveOp convolve;

    public ConvolveWithEdgeOp(final Kernel pKernel, final int pEdgeCondition, final RenderingHints pHints) {
        // Create convolution operation
        int edge;

        switch (pEdgeCondition) {
            case EDGE_REFLECT:
            case EDGE_WRAP:
                edge = ConvolveOp.EDGE_NO_OP;
                break;
            default:
                edge = pEdgeCondition;
                break;
        }

        kernel = pKernel;
        edgeCondition = pEdgeCondition;
        convolve = new ConvolveOp(pKernel, edge, pHints);
    }

    public ConvolveWithEdgeOp(final Kernel pKernel) {
        this(pKernel, EDGE_ZERO_FILL, null);
    }

    public BufferedImage filter(BufferedImage pSource, BufferedImage pDestination) {
        if (pSource == null) {
            throw new NullPointerException("source image is null");
        }
        if (pSource == pDestination) {
            throw new IllegalArgumentException("source image cannot be the same as the destination image");
        }

        int borderX = kernel.getWidth() / 2;
        int borderY = kernel.getHeight() / 2;

        BufferedImage original = addBorder(pSource, borderX, borderY);

        // Workaround for what seems to be a Java2D bug:
        // ConvolveOp needs explicit destination image type for some "uncommon"
        // image types. However, TYPE_3BYTE_BGR is what javax.imageio.ImageIO
        // normally returns for color JPEGs... :-/
        BufferedImage destination = pDestination;
        if (original.getType() == BufferedImage.TYPE_3BYTE_BGR) {
            destination = new BufferedImage(pSource.getWidth(), pSource.getHeight(), pSource.getColorModel().getTransparency());
        }

        // Do the filtering (if destination is null, a new image will be created)
        destination = convolve.filter(original, destination);

        if (pSource != original) {
            // Remove the border
            destination = destination.getSubimage(borderX, borderY, pSource.getWidth(), pSource.getHeight());
        }

        return destination;
    }

    private BufferedImage addBorder(final BufferedImage pOriginal, final int pBorderX, final int pBorderY) {
        if ((edgeCondition & 2) == 0) {
            return pOriginal;
        }

        int w = pOriginal.getWidth();
        int h = pOriginal.getHeight();

        ColorModel cm = pOriginal.getColorModel();
        WritableRaster raster = cm.createCompatibleWritableRaster(w + 2 * pBorderX, h + 2 * pBorderY);
        BufferedImage bordered = new BufferedImage(cm, raster, cm.isAlphaPremultiplied(), null);

        Graphics2D g = bordered.createGraphics();
        try {
            g.setComposite(AlphaComposite.Src);
            g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);

            // Draw original in center
            g.drawImage(pOriginal, pBorderX, pBorderY, null);

            // TODO: I guess we need the top/left etc, if the corner pixels are covered by the kernel
            switch (edgeCondition) {
                case EDGE_REFLECT:
                    // Top/left (empty)
                    g.drawImage(pOriginal, pBorderX, 0, pBorderX + w, pBorderY, 0, 0, w, 1, null); // Top/center
                    // Top/right (empty)

                    g.drawImage(pOriginal, -w + pBorderX, pBorderY, pBorderX, h + pBorderY, 0, 0, 1, h, null); // Center/left
                    // Center/center (already drawn)
                    g.drawImage(pOriginal, w + pBorderX, pBorderY, 2 * pBorderX + w, h + pBorderY, w - 1, 0, w, h, null); // Center/right

                    // Bottom/left (empty)
                    g.drawImage(pOriginal, pBorderX, pBorderY + h, pBorderX + w, 2 * pBorderY + h, 0, h - 1, w, h, null); // Bottom/center
                    // Bottom/right (empty)
                    break;
                case EDGE_WRAP:
                    g.drawImage(pOriginal, -w + pBorderX, -h + pBorderY, null); // Top/left
                    g.drawImage(pOriginal, pBorderX, -h + pBorderY, null); // Top/center
                    g.drawImage(pOriginal, w + pBorderX, -h + pBorderY, null); // Top/right

                    g.drawImage(pOriginal, -w + pBorderX, pBorderY, null); // Center/left
                    // Center/center (already drawn)
                    g.drawImage(pOriginal, w + pBorderX, pBorderY, null); // Center/right

                    g.drawImage(pOriginal, -w + pBorderX, h + pBorderY, null); // Bottom/left
                    g.drawImage(pOriginal, pBorderX, h + pBorderY, null); // Bottom/center
                    g.drawImage(pOriginal, w + pBorderX, h + pBorderY, null); // Bottom/right
                    break;
                default:
                    throw new IllegalArgumentException("Illegal edge operation " + edgeCondition);
            }

        }
        finally {
            g.dispose();
        }

        return bordered;
    }

    /**
     * Returns the edge condition.
     * @return the edge condition of this {@code ConvolveOp}.
     * @see #EDGE_NO_OP
     * @see #EDGE_ZERO_FILL
     * @see #EDGE_REFLECT
     * @see #EDGE_WRAP
     */
    public int getEdgeCondition() {
        return edgeCondition;
    }

    public WritableRaster filter(final Raster pSource, final WritableRaster pDestination) {
        return convolve.filter(pSource, pDestination);
    }

    public BufferedImage createCompatibleDestImage(final BufferedImage pSource, final ColorModel pDesinationColorModel) {
        return convolve.createCompatibleDestImage(pSource, pDesinationColorModel);
    }

    public WritableRaster createCompatibleDestRaster(final Raster pSource) {
        return convolve.createCompatibleDestRaster(pSource);
    }

    public Rectangle2D getBounds2D(final BufferedImage pSource) {
        return convolve.getBounds2D(pSource);
    }

    public Rectangle2D getBounds2D(final Raster pSource) {
        return convolve.getBounds2D(pSource);
    }

    public Point2D getPoint2D(final Point2D pSourcePoint, final Point2D pDestinationPoint) {
        return convolve.getPoint2D(pSourcePoint, pDestinationPoint);
    }

    public RenderingHints getRenderingHints() {
        return convolve.getRenderingHints();
    }

    public Kernel getKernel() {
        return convolve.getKernel();
    }

}