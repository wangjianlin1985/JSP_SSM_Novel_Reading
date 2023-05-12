// 
// 
// 

package com.lianshuwang.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import java.text.ParseException;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.util.Random;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import com.lianshuwang.domin.Book;
import java.io.File;
import com.lianshuwang.util.PropertyConfigurer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.lianshuwang.helper.BookHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import com.lianshuwang.helper.UserHelper;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.lianshuwang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.service.BookService;
import org.springframework.stereotype.Controller;

@Controller
public class UploadController
{
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    private static final Log logger;
    
    static {
        logger = LogFactory.getLog((Class)UploadController.class);
    }
    
    @RequestMapping({ "getUploadPage" })
    public String getUploadPage(final HttpSession session) {
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        if (userHelper == null) {
            return "redirect:index";
        }
        UploadController.logger.info((Object)"you are visiting uploading page!");
        return "upload";
    }
    
    @RequestMapping(value = { "doUpload" }, method = { RequestMethod.POST })
    public String doUpload(@ModelAttribute final BookHelper bookHelper, final Model model, final HttpSession session) throws IllegalStateException, IOException, ParseException {
        UploadController.logger.info((Object)"you are uploading a book! ");
        UploadController.logger.info((Object)("This book is " + bookHelper.getTitle() + "!"));
        final String fileName = bookHelper.getBookFile().getOriginalFilename();
        final String bookCover = bookHelper.getBookCover().getOriginalFilename();
        final MultipartFile bookFile = bookHelper.getBookFile();
        final MultipartFile coverFile = bookHelper.getBookCover();
        if (bookFile.isEmpty()) {
            UploadController.logger.info((Object)"Uploading failed! The book you are uploading is empty!");
            return "upload_failed";
        }
        if (coverFile.isEmpty()) {
            UploadController.logger.info((Object)"Uploading failed! The book cover you are uploading is empty!");
            return "upload_failed";
        }
        final String typeId = new StringBuilder().append(bookHelper.getLargeType()).append(bookHelper.getSmallType()).toString();
        final int type_id = Integer.parseInt(typeId);
        final String format = fileName.substring(fileName.lastIndexOf(46) + 1);
        final List<String> typeNames = this.bookService.getTypeNames(type_id);
        final String filePath_pre = (String)PropertyConfigurer.getProperty("book_path");
        final String file_dir_pre = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("").getPath().substring(0, Thread.currentThread().getContextClassLoader().getResource("").getPath().length() - 16)) + filePath_pre;
        final String filePath = String.valueOf(file_dir_pre) + typeNames.get(0) + "/" + typeNames.get(1) + "/" + bookHelper.getTitle() + "." + format;
        File file = new File(String.valueOf(file_dir_pre) + typeNames.get(0) + "/" + typeNames.get(1));
        if (!file.exists()) {
            file.mkdirs();
        }
        final File localBookFile = new File(filePath);
        if (localBookFile.exists()) {
            UploadController.logger.info((Object)"Uploading failed! The book is existed!");
            return "upload_failed2";
        }
        bookFile.transferTo(localBookFile);
        final String coverPath_pre = (String)PropertyConfigurer.getProperty("book_cover_path");
        final String coverPath = String.valueOf(file_dir_pre) + coverPath_pre + typeNames.get(0) + "/" + typeNames.get(1) + "/" + bookHelper.getTitle() + ".jpg";
        file = new File(String.valueOf(file_dir_pre) + coverPath_pre + typeNames.get(0) + "/" + typeNames.get(1));
        if (!file.exists()) {
            file.mkdirs();
        }
        final File localCoverFile = new File(coverPath);
        coverFile.transferTo(localCoverFile);
        UploadController.logger.info((Object)"The book has uploaded to local path successfully!");
        final Book book = new Book();
        book.setBook_title(bookHelper.getTitle());
        book.setBook_author(bookHelper.getAuthor());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        final Date date = dateFormat.parse(bookHelper.getPubYear());
        book.setBook_pubYear(date);
        book.setBook_summary(bookHelper.getSummary());
        book.setType_id(type_id);
        book.setBook_format(format);
        book.setDownload_times(0);
        book.setBook_file(filePath);
        book.setBook_cover(coverPath);
        dateFormat = new SimpleDateFormat("yyMMdd", Locale.CHINESE);
        final String pubDate = dateFormat.format(date);
        final String upDate = dateFormat.format(new Date());
        final int random = new Random().nextInt(900) + 100;
        final String idStr = typeId + pubDate + upDate + random;
        final long bookID = Long.parseLong(idStr);
        UploadController.logger.info((Object)("The book id you uploaded is " + bookID));
        book.setId(bookID);
        this.bookService.uploadBook(book);
        final UserHelper userHelper = (UserHelper)session.getAttribute("userHelper");
        this.bookService.updateRecords(userHelper.getId(), bookID);
        this.userService.updateUserContribution(2, userHelper.getId());
        model.addAttribute("bookID", (Object)bookID);
        UploadController.logger.info((Object)"you are coming to the uploading successful page!");
        return "upload_success";
    }
}
