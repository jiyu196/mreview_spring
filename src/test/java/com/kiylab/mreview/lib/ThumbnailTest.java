package com.kiylab.mreview.lib;

import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ThumbnailTest {
  @Test
  public void testConvert() throws IOException {
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\test", "cute_cat_final.webp"));
    BufferedImage thumbnail = Thumbnails.of(originalImage)
            .size(200, 200)
            .asBufferedImage();
    ImageIO.write(thumbnail,"webp", new File("C:\\Users\\tj\\Desktop\\test", "cute_cat_final_output.webp"));
  }

  // 원본 이미지의 해상도 유지. 포맷만 webp로 컴버트
  @Test
  public void testConvert2() throws IOException{
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\test", "01d19a58-c5af-47dd-844d-f9b5af08917b_bmp.bmp"));
    ImageIO.write(originalImage,"webp", new File("C:\\Users\\tj\\Desktop\\test", "01d19a58-c5af-47dd-844d-f9b5af08917b_out.bmp"));
//    01d19a58-c5af-47dd-844d-f9b5af08917b_bmp.bmp
  }

//  @Test
//  public void testConvertOnly() throws IOException {
//    BufferedImage originalImage = ImageIO.createImageInputStream(new File("C:\\Users\\tj\\Desktop\\test", "cute_cat_final.webp"));
//    BufferedImage thumbnail = Thumbnails.fromInputStreams(new File())
//  }

}
