package cc.gukeer.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 该类是图片处理类
 * 
 * @author 王永丰
 * 
 */
public final class ImageUtil {
    /** 图片格式：JPG */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    private ImageUtil() {
    }
/**
     * 生成组合头像
     * 
     * @param paths
     *            用户图像
     * @throws IOException
     */
    public static void getCombinationOfhead(List<String> paths)
            throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<BufferedImage>();
        // 压缩图片所有的图片生成尺寸同意的 为 50x50
        for (int i = 0; i < paths.size(); i++) {
            bufferedImages.add(ImageUtil.resize2(paths.get(i), 50, 50, true));
        }

        int width = 112; // 这是画板的宽高

        int height = 112; // 这是画板的高度

        // BufferedImage.TYPE_INT_RGB可以自己定义可查看API

        BufferedImage outImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        // 生成画布
        Graphics g = outImage.getGraphics();
        
        Graphics2D g2d = (Graphics2D) g;
        
        // 设置背景色
        g2d.setBackground(new Color(231,231,231));
        
        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.clearRect(0, 0, width, height);
        
        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为4中
        int j = 1;
        for (int i = 1; i <= bufferedImages.size(); i++) {
            if (bufferedImages.size() == 4) {
                if (i <= 2) {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i
                            - 50, 4, null);
                } else {
                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j
                            - 50, 58, null);
                    j++;
                }
            } else if (bufferedImages.size() == 3) {
                if (i <= 1) {

                    g2d.drawImage(bufferedImages.get(i - 1), 31, 4, null);

                } else {

                    g2d.drawImage(bufferedImages.get(i - 1), 50 * j + 4 * j
                            - 50, 58, null);

                    j++;
                }

            } else if (bufferedImages.size() == 2) {

                g2d.drawImage(bufferedImages.get(i - 1), 50 * i + 4 * i - 50,
                        31, null);

            } else if (bufferedImages.size() == 1) {

                g2d.drawImage(bufferedImages.get(i - 1), 31, 31, null);

            }

            // 需要改变颜色的话在这里绘上颜色。可能会用到AlphaComposite类
        }

        String format = "JPG";
        String outPath = "E:\\b.jpg";
        ImageIO.write(outImage, format, new File(outPath));
    }

    /**
     * 图片缩放
     * 
     * @param filePath
     *            图片路径
     * @param height
     *            高度
     * @param width
     *            宽度
     * @param bb
     *            比例不对时是否需要补白
     */
    public static BufferedImage resize2(String filePath, int height, int width,
            boolean bb) {
        try {
            double ratio = 0; // 缩放比例
            File f = new File(filePath);
            BufferedImage bi = ImageIO.read(f);
            Image itemp = bi.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            // 计算比例
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
                if (bi.getHeight() > bi.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bi.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                        AffineTransform.getScaleInstance(ratio, ratio), null);
                itemp = op.filter(bi, null);
            }
            if (bb) {
                // copyimg(filePath, "D:\\img");
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == itemp.getWidth(null))
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                else
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
                            itemp.getWidth(null), itemp.getHeight(null),
                            Color.white, null);
                g.dispose();
                itemp = image;
            }
            return (BufferedImage) itemp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        List path = new ArrayList();
        path.add("C:\\Users\\Administrator\\Pictures\\icon\\excel.png");
        path.add("C:\\Users\\Administrator\\Pictures\\icon\\ppt.png");
        path.add("C:\\Users\\Administrator\\Pictures\\icon\\sharepoint.png");
        path.add("C:\\Users\\Administrator\\Pictures\\icon\\日历.png");
        path.add("C:\\Users\\Administrator\\Pictures\\icon\\excel.png");


        getCombinationOfhead(path);
    }

}