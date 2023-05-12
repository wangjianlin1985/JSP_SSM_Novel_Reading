// 
// 
// 

package com.lianshuwang.controller;

import java.text.ParseException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import javax.servlet.http.HttpServletResponse;
import com.lianshuwang.domin.User;
import java.util.Date;
import com.lianshuwang.domin.Upload;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Iterator;
import com.lianshuwang.domin.Book;
import java.util.List;
import com.lianshuwang.domin.BookType;
import com.lianshuwang.helper.PageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.lianshuwang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class BookController
{
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    private static final Log logger;
    
    static {
        logger = LogFactory.getLog((Class)BookController.class);
    }
    
    @RequestMapping({ "/bookList" })
    public String getBookList(final String bookType, final String smallType, @RequestParam(value = "pageId", defaultValue = "1") final int pageId, final Model model) {
        BookController.logger.info((Object)"you are visiting the books list page!");
        final List<BookType> smallTypes = this.bookService.getSmallTypesOfBook(bookType);
        model.addAttribute("smallTypesOfBook", (Object)smallTypes);
        model.addAttribute("bookType", (Object)bookType);
        final PageHelper page = new PageHelper();
        page.setCurrentPage(pageId);
        if (smallType == null) {
            final int sumOfBooks = this.bookService.getTotalOfLTBooks(smallTypes);
            page.setTotalRows(sumOfBooks);
            final List<Book> books = this.bookService.getLargeTypeBooks(smallTypes, page);
            model.addAttribute("currentPage", (Object)pageId);
            model.addAttribute("totalPage", (Object)page.getTotalPage());
            model.addAttribute("books", (Object)books);
        }
        else {
            int type_id = 0;
            for (final BookType sBookType : smallTypes) {
                if (sBookType.getSmall_type_name().equals(smallType)) {
                    type_id = sBookType.getId();
                    break;
                }
            }
            final int sumOfBooks2 = this.bookService.getTotalOfSTBooks(type_id);
            page.setTotalRows(sumOfBooks2);
            final List<Book> books2 = this.bookService.getSmallTypeBooks(type_id, page);
            model.addAttribute("currentPage", (Object)pageId);
            model.addAttribute("totalPage", (Object)page.getTotalPage());
            model.addAttribute("books", (Object)books2);
            model.addAttribute("smallType", (Object)smallType);
        }
        return "bookList";
    }
    
    @RequestMapping({ "/bookDetail" })
    public String bookDetail(final long bookID, final Model model) {
        final Book book = this.bookService.getBookDetail(bookID);
        final Upload upload = this.bookService.getUploadInfo(bookID);
        final Date uploadedDate = upload.getUploadedDate();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String uploadDate = dateFormat.format(uploadedDate);
        final User user = this.userService.queryById(upload.getUploader());
        model.addAttribute("book", (Object)book);
        model.addAttribute("uploadedDate", (Object)uploadDate);
        model.addAttribute("uploader", (Object)user.getUserName());
        model.addAttribute("format", (Object)book.getBook_format().toUpperCase());
        BookController.logger.info((Object)("you are looking up the book:" + book.getBook_title()));
        return "bookDetail";
    }
    
    @RequestMapping({ "/getBookCover" })
    public void getBookCover(final String coverPath, final HttpServletResponse response) {
        InputStream in = null;
        BufferedInputStream bis = null;
        OutputStream out = null;
        BufferedOutputStream bos = null;
        final File file = new File(coverPath);
        if (!file.exists() || file.isDirectory()) {
            return;
        }
        try {
            in = new FileInputStream(coverPath);
            bis = new BufferedInputStream(in);
            final byte[] data = new byte[1024];
            int bytes = 0;
            out = (OutputStream)response.getOutputStream();
            bos = new BufferedOutputStream(out);
            while ((bytes = bis.read(data, 0, data.length)) != -1) {
                bos.write(data, 0, bytes);
            }
            bos.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            try {
                if (bos != null) {
                    bos.close();
                }
                if (out != null) {
                    out.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            return;
        }
        finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (out != null) {
                    out.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        try {
            if (bos != null) {
                bos.close();
            }
            if (out != null) {
                out.close();
            }
            if (bis != null) {
                bis.close();
            }
            if (in != null) {
                in.close();
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    @RequestMapping({ "/book_download" })
    public void getBookDownload(final long bookID, final String filePath, final HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        final String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            final long fileLength = new File(filePath).length();
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(filePath));
            bos = new BufferedOutputStream((OutputStream)response.getOutputStream());
            final byte[] buff = new byte[2018];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            this.bookService.addDownloadTimes(bookID);
            BookController.logger.info((Object)("you are downloading the book, the book file is " + fileName));
        }
        try {
            if (bis != null) {
                bis.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        this.bookService.addDownloadTimes(bookID);
        BookController.logger.info((Object)("you are downloading the book, the book file is " + fileName));
    }
    
    @RequestMapping({ "/bookSearch" })
    public String bookSearch(final String searchBy, final String searchTxt, final Model model) throws ParseException {
        BookController.logger.info((Object)"you are searching book!");
        BookController.logger.info((Object)("The search context is " + searchTxt));
        final List<Book> books = this.bookService.searchBook(searchBy, searchTxt);
        model.addAttribute("books", (Object)books);
        model.addAttribute("searchTxt", (Object)searchTxt);
        return "searchResult";
    }
}
