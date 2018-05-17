package com.simple.shopping.service.impl;

import com.simple.shopping.domain.ProductImage;
import com.simple.shopping.repository.ProductImageRepository;
import com.simple.shopping.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProductImageServiceImpl implements ProductImageService{
    ProductImageRepository productImageRepository;
    @Value("${filepath}")
    String filePath;

    @Autowired
    public ProductImageServiceImpl(ProductImageRepository productImageRepository){
        this.productImageRepository = productImageRepository;
    }

    @Override
    public ProductImage saveProductImage(MultipartFile multipartFile) {
        ProductImage productImage = new ProductImage();
        UUID uuid = UUID.randomUUID();
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime.getYear()).append("/")
                .append(localDateTime.getMonthValue()).append("/").append(localDateTime.getDayOfMonth()).append("/");
        String dir = filePath+sb.toString();
        productImage.setOriginFileName(multipartFile.getOriginalFilename());
        productImage.setSaveName(uuid.toString());
        productImage.setPath(dir);
        productImage.setContentType(multipartFile.getContentType());
        productImage.setSize(multipartFile.getSize());

        String filePath = dir+productImage.getSaveName();
        File file = new File(dir);
        byte[] fileBuffer = new byte[1024];
        int length = 0;

        if(!file.exists()){
            file.mkdirs();
        }

        try(InputStream is = multipartFile.getInputStream();
            FileOutputStream fos = new FileOutputStream(filePath);){
            while( (length = is.read(fileBuffer)) != -1){
                fos.write(fileBuffer, 0, length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productImage;
    }

    @Override
    public void downProductImage(HttpServletRequest request, HttpServletResponse response, ProductImage productImage){
        OutputStream os = null;
        try{
            os = response.getOutputStream();
        }catch (Exception e){
            e.printStackTrace();
        }

        try(
                FileInputStream fis = new FileInputStream(productImage.getPath()+productImage.getSaveName());){

            //응답 헤더 설정
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(productImage.getOriginFileName(), "UTF-8") + "\";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Type", productImage.getContentType());
            response.setHeader("Content-Length", Long.toString(productImage.getSize()));
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");

            //파일 전송
            int length = 0;
            byte[] fileBuffer = new byte[1024];
            while( (length = fis.read(fileBuffer)) != -1){
                os.write(fileBuffer, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ProductImage findProductImageByNo(Long no) {
        return productImageRepository.findProductImageByNo(no);
    }

    @Override
    public void deleteProductImageFile(ProductImage productImage){
        if(productImage!=null){
            File image = new File(productImage.getPath() + productImage.getSaveName());
            if(image.exists()){
                image.delete();
            }
        }
    }

    @Override
    @Transactional
    public void deleteProductImageByNo(Long no) {
        productImageRepository.deleteProductImageByNo(no);
    }
}
