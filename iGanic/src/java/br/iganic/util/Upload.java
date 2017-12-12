/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.iganic.util;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
/**
 *
 * @author vanilson
 */
public class Upload {
    
    public ArrayList dados = new ArrayList();
    private String idProduto;
    private String name;
    
    public Integer getIdProduto(){
        return Integer.parseInt(this.idProduto);
    }
    
    public String getNameImg(){
        return this.name;
    }
    
    public boolean anexos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (ServletFileUpload.isMultipartContent(request)) {
            int cont = 0;
            ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
            List fileItemsList = null;
            try {
                fileItemsList = servletFileUpload.parseRequest(request);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
            String optionalFileName = "";
            FileItem fileItem = null;
            Iterator it = fileItemsList.iterator();
            do {
                //cont++;
                FileItem fileItemTemp = (FileItem) it.next();
                if (fileItemTemp.isFormField()) {
                    if (fileItemTemp.getFieldName().equals("file")) {
                        optionalFileName = fileItemTemp.getString();
                    }else if(fileItemTemp.getFieldName().equals("idProduto")){
                        this.idProduto = fileItemTemp.getString();
                    }
                } else {
                    fileItem = fileItemTemp;
                }
                if (cont != (fileItemsList.size())) {
                    if (fileItem != null) {
                        String fileName = fileItem.getName();
                        if (fileItem.getSize() > 0) {
                            if (optionalFileName.trim().equals("")) {
                                fileName = FilenameUtils.getName(fileName);
                            } else {
                                fileName = optionalFileName;
                            }
                            
                            String dirName = request.getServletContext().getRealPath("/img_produtos/");
                            File saveTo = new File(dirName + fileName);
                            name = fileName;
                            System.out.println("\n\n\n\nCaminho da imagem: " + saveTo.toString() +"\n\n\n\n\n");
                            try {
                                fileItem.write(saveTo);
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                cont++;
            } while (it.hasNext());
            return true;
        } else {
            return false;
        }
    }
}
