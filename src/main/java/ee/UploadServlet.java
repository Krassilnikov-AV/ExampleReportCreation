package ee;

import com.ibm.useful.http.*;
import connection.ConfigurateApp;
import example.exRead.ExReadExcelData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
import java.util.logging.*;

/**
 *
 * @author Aleks
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    /**
     * пока не сработал указанный путь
     * ConfigurateApp configurateApp = new
     * ConfigurateApp(); String pathTo = configurateApp.getPath();
     *
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        ConfigurateApp confApp = new ConfigurateApp();
        //     String pathTo = request.getHeader("pathTo");
        //.getParameter("pathTo");
        //      String listData = request.getParameter("listData");

        ExReadExcelData red = new ExReadExcelData();

//        String result = null;
//        List<String> list = null;
//        int columnIndex = 0;
        /**
         * if (request.getContentType().contains("multipart/form-data"))
         *
         * {
         *
         * //получение пути выбранного файла
         * String pathToFile = multidata.toString();
         *
         * ReadExcelData red = new ReadExcelData(); List<String> result = null;
         *
         * if (data) { result = red.getDataStringIntegerDate(pathToFile, 0); //
         * out.println("<h2>" + result + "/<h2>"); }
         * request.setAttribute("result", "\n" + result.toString());
         * request.getRequestDispatcher("inserting.jsp").forward(request,
         * response); }
         */
         boolean upload = request.getParameter("upload") != null;
        if (request.getContentType().contains("multipart/form-data")) {
// извлечение файла
            PostData multidata = new PostData(request);
            //    String pathToSave = multidata.getParameter("pathToSave");
            //      System.out.println("сервер пытается сохранить Ваш файл по заданному пути: " + pathTo);
            FileData tempFile = multidata.getFileData("fileToUpload");
           
            if (upload && tempFile != null) {
                String pathConf = confApp.getPath();
                String path = multidata.getParameter(pathConf);
                saveFile(tempFile, path);
            }
//            for (String l : list) {
//                request.setAttribute("listData", "\n" + l);
//            }
        }
        try (PrintWriter out = response.getWriter()) {
            // получение ответа
            request.getRequestDispatcher("inserting.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>  

    //   private void saveFile(FileData tempFile, String path) {
    private void saveFile(FileData tempFile, String path) throws IOException {
        //     String path = "D:\tempGirForFiles";
        String filename = path + File.separator + tempFile.getFileName();
        File f = new File(filename);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (fos != null) {
            try {
                fos.write(tempFile.getByteData());  // получение 
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
