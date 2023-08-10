package com.example.paydaytrade.helper;

import com.example.paydaytrade.model.dto.request.ProductRequestDto;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class StoreDataServiceHelper {

    public static void photoFileWriter(List<ProductRequestDto> products) {

        int thumbnailCount = 1;
        int imagesProduct = 1;
        int imagesCount = 1;

        try {
            String directoryPathThumbnail = "C:/Users/Admin/Desktop/payday-trade/src/main/resources/thumbnail";
            Files.createDirectory(Path.of(directoryPathThumbnail));

            String directoryPathImages = "C:/Users/Admin/Desktop/payday-trade/src/main/resources/images";
            Files.createDirectory(Path.of(directoryPathImages));

            for (ProductRequestDto productRequestDto : products) {
                String filePathThumbnail = directoryPathThumbnail + "/thumbnail" + thumbnailCount + ".jpg";

                downloadAndSaveImage(productRequestDto.getThumbnail(),filePathThumbnail);

                String imageProduct = directoryPathImages + "/product" +  imagesProduct;
                Files.createDirectory(Path.of(imageProduct));
                for (String photo : productRequestDto.getImages()) {
                    String filePathImages = imageProduct + "/image" + imagesCount + ".jpg";

                    downloadAndSaveImage(photo,filePathImages);
                    imagesCount++;
                }
                imagesCount = 1;
                imagesProduct++;
                thumbnailCount++;
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
        public static void downloadAndSaveImage(String imageUrl, String fileName) throws IOException {
            URL url = new URL(imageUrl);
            try (InputStream in = url.openStream()) {
                Path filePath = Path.of(fileName);
                Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
            }

        }
}