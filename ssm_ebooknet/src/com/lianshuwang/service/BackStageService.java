// 
// 
// 

package com.lianshuwang.service;

import com.lianshuwang.domin.Feedback;
import java.util.Collection;
import com.lianshuwang.domin.User;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import com.lianshuwang.domin.Book;
import java.util.Iterator;
import java.util.ArrayList;
import com.lianshuwang.helper.doBookHelper;
import com.lianshuwang.domin.Upload;
import java.util.List;
import com.lianshuwang.util.PropertyConfigurer;
import com.lianshuwang.dao.FeedbackDao;
import com.lianshuwang.dao.UserDao;
import com.lianshuwang.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.dao.UploadDao;
import org.springframework.stereotype.Service;

@Service
public class BackStageService
{
    @Autowired
    private UploadDao uploadDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FeedbackDao feedbackDao;
    
    public boolean getLogin(final String username, final String password) {
        final String default_user = (String)PropertyConfigurer.getProperty("admin_user");
        final String default_pass = (String)PropertyConfigurer.getProperty("admin_password");
        return default_user.equals(username) && default_pass.equals(password);
    }
    
    public List<doBookHelper> iteratorUploadList(final List<Upload> uploadList) {
        final List<doBookHelper> doBookHelperList = new ArrayList<doBookHelper>();
        for (final Upload upload : uploadList) {
            final doBookHelper bookHelper = new doBookHelper();
            final Book book = this.bookDao.queryById(upload.getUploadedBook());
            bookHelper.setId(book.getId());
            bookHelper.setTitle(book.getBook_title());
            bookHelper.setAuthor(book.getBook_author());
            bookHelper.setUploader(upload.getUploader());
            bookHelper.setUploadedDate(upload.getUploadedDate());
            doBookHelperList.add(bookHelper);
        }
        return doBookHelperList;
    }
    
    public List<doBookHelper> getUploadBooks() {
        final List<Upload> uploadList = this.uploadDao.queryByUploadedDate();
        final List<doBookHelper> doBookHelperList = this.iteratorUploadList(uploadList);
        return doBookHelperList;
    }
    
    public List<doBookHelper> getBooksByDays(final int days) {
        List<Upload> uploadList;
        if (days == 30) {
            uploadList = this.uploadDao.searchByThirtyDays();
        }
        else if (days == 7) {
            uploadList = this.uploadDao.searchBySevenDays();
        }
        else {
            uploadList = this.uploadDao.searchByToday();
        }
        final List<doBookHelper> doBookHelperList = this.iteratorUploadList(uploadList);
        return doBookHelperList;
    }
    
    public List<doBookHelper> getBooksByTitle(final String title) {
        final List<doBookHelper> doBookHelperList = new ArrayList<doBookHelper>();
        final List<Book> bookList = this.bookDao.searchBookByTitle(title);
        for (final Book book : bookList) {
            final doBookHelper bookHelper = new doBookHelper();
            bookHelper.setId(book.getId());
            bookHelper.setTitle(book.getBook_title());
            bookHelper.setAuthor(book.getBook_author());
            final Upload upload = this.uploadDao.queryByBookId(book.getId());
            bookHelper.setUploader(upload.getUploader());
            bookHelper.setUploadedDate(upload.getUploadedDate());
            doBookHelperList.add(bookHelper);
        }
        return doBookHelperList;
    }
    
    public List<doBookHelper> getBooksByUserId(final long userId) {
        final List<doBookHelper> doBookHelperList = new ArrayList<doBookHelper>();
        final List<Upload> uploadList = this.uploadDao.queryByUserId(userId);
        for (final Upload upload : uploadList) {
            final Book book = this.bookDao.queryById(upload.getUploadedBook());
            final doBookHelper bookHelper = new doBookHelper();
            bookHelper.setId(book.getId());
            bookHelper.setTitle(book.getBook_title());
            bookHelper.setAuthor(book.getBook_author());
            bookHelper.setUploader(upload.getUploader());
            bookHelper.setUploadedDate(upload.getUploadedDate());
            doBookHelperList.add(bookHelper);
        }
        return doBookHelperList;
    }
    
    public int deleteBook(final long bookId) {
        final Book book = this.bookDao.queryById(bookId);
        final String bookFilePath = book.getBook_file();
        final File file = new File(bookFilePath);
        if (!file.exists()) {
            return 0;
        }
        file.delete();
        final String bookCoverPath = book.getBook_cover();
        final File cover = new File(bookCoverPath);
        if (!cover.exists()) {
            return 0;
        }
        cover.delete();
        final int count = this.bookDao.deleteBook(bookId);
        final int count2 = this.uploadDao.deleteUploadRow(bookId);
        if (count == 0 || count2 == 0) {
            return 0;
        }
        return 1;
    }
    
    public Map<String, Object> getUserByContribution() {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        final List<User> userList = this.userDao.queryUserByContribution();
        resultMap.put("userList", userList);
        resultMap.put("totalUser", this.userDao.queryUserNumber());
        resultMap.put("weekUser", this.userDao.queryUserNumberByWeek());
        resultMap.put("monthUser", this.userDao.queryUserNumberByMonth());
        return resultMap;
    }
    
    public int deleteUser(final long userId) {
        final int count = this.userDao.deleteUser(userId);
        this.uploadDao.deleteUploadRowByUser(userId);
        if (count == 0) {
            return 0;
        }
        return 1;
    }
    
    public List<User> getUserBySearch(final String searchTxt) {
        List<User> userList = new ArrayList<User>();
        if (searchTxt.length() < 5) {
            userList = this.userDao.queryUserByUserName(searchTxt);
        }
        else if (this.isNumeric(searchTxt)) {
            final long userId = Long.parseLong(searchTxt);
            final User user1 = this.userDao.queryById(userId);
            userList.add(user1);
            final List<User> userList2 = this.userDao.queryUserByUserName(searchTxt);
            userList.addAll(userList2);
        }
        return userList;
    }
    
    public boolean isNumeric(final String str) {
        int i = str.length();
        while (--i >= 0) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public List<Feedback> getFeedback() {
        final List<Feedback> feedbackList = this.feedbackDao.queryAllNotRead();
        return feedbackList;
    }
    
    public int setOneFeedbackRead(final int id) {
        final int count = this.feedbackDao.setOneRead(id);
        return count;
    }
    
    public int setAllFeedbackRead() {
        return this.feedbackDao.setAllRead();
    }
}
