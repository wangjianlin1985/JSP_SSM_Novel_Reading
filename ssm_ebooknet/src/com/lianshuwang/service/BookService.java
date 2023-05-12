// 
// 
// 

package com.lianshuwang.service;

import java.text.ParseException;
import com.lianshuwang.helper.RankingBook;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import com.lianshuwang.helper.PageHelper;
import java.util.Date;
import java.util.ArrayList;
import com.lianshuwang.domin.BookType;
import java.util.List;
import com.lianshuwang.domin.Upload;
import com.lianshuwang.domin.Book;
import org.apache.commons.logging.LogFactory;
import com.lianshuwang.dao.UploadDao;
import com.lianshuwang.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.dao.BookTypeDao;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Service;

@Service
public class BookService
{
    private static final Log logger;
    @Autowired
    private BookTypeDao bookTypeDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UploadDao uploadDao;
    
    static {
        logger = LogFactory.getLog((Class)BookService.class);
    }
    
    public Book getBookDetail(final long id) {
        final Book book = this.bookDao.queryById(id);
        return book;
    }
    
    public Upload getUploadInfo(final long id) {
        final Upload upload = this.uploadDao.queryByBookId(id);
        return upload;
    }
    
    public List<BookType> getSmallTypesOfBook(final String largeTypeName) {
        final List<BookType> bookTypes = this.bookTypeDao.queryByLargeTypeName(largeTypeName);
        return bookTypes;
    }
    
    public long queryByTitle(final String title) {
        final Book book = this.bookDao.queryByTitle(title);
        return book.getId();
    }
    
    public List<String> getTypeNames(final int id) {
        final BookType bookType = this.bookTypeDao.queryById(id);
        final List<String> typeNames = new ArrayList<String>();
        typeNames.add(bookType.getLarge_type_name());
        typeNames.add(bookType.getSmall_type_name());
        return typeNames;
    }
    
    public void uploadBook(final Book book) {
        final int count = this.bookDao.addBook(book);
        if (count == 1) {
            BookService.logger.info((Object)"uploading successful!");
        }
        else {
            BookService.logger.info((Object)"uploading failed!");
        }
    }
    
    public void updateRecords(final long uploader, final long uploadedBook) {
        final Upload upload = new Upload();
        upload.setUploader(uploader);
        upload.setUploadedBook(uploadedBook);
        final Date uploadedDate = new Date();
        upload.setUploadedDate(uploadedDate);
        this.uploadDao.addUploadRecord(upload);
    }
    
    public List<Book> getLargeTypeBooks(final List<BookType> bookTypes, final PageHelper page) {
        final List<Integer> bookTypeIdList = new ArrayList<Integer>();
        for (final BookType bookType : bookTypes) {
            bookTypeIdList.add(bookType.getId());
        }
        final List<Book> books = this.bookDao.getLargeTypeBooks(bookTypeIdList, page.getStartRow(), page.getPageSize());
        return books;
    }
    
    public int getTotalOfLTBooks(final List<BookType> bookTypes) {
        final List<Integer> bookTypeIdList = new ArrayList<Integer>();
        for (final BookType bookType : bookTypes) {
            bookTypeIdList.add(bookType.getId());
        }
        return this.bookDao.getTotalOfLTBooks(bookTypeIdList);
    }
    
    public List<Book> getSmallTypeBooks(final int type_id, final PageHelper page) {
        final List<Book> books = this.bookDao.getSmallTypeBooks(type_id, page.getStartRow(), page.getPageSize());
        return books;
    }
    
    public int getTotalOfSTBooks(final int type_id) {
        return this.bookDao.getTotalOfSTBooks(type_id);
    }
    
    public int queryNumberOfBooks() {
        return this.bookDao.queryNumberOfBooks();
    }
    
    public List<Integer> queryNumberOfSomeTypeBooks() {
        final List<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i < 5; ++i) {
            final int sum = this.bookDao.queryNumberOfSomeTypeBooks(i);
            result.add(sum);
        }
        return result;
    }
    
    public String getMaxUploadDate() {
        final Date date = this.uploadDao.getMaxUploadDate();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        String maxUploadDate = "";
        if (date != null) {
            maxUploadDate = dateFormat.format(date);
        }
        return maxUploadDate;
    }
    
    public List<RankingBook> queryByUploadedDate() {
        final List<RankingBook> rankingBooks = new ArrayList<RankingBook>();
        final List<Upload> uploadRecords = this.uploadDao.queryByUploadedDate();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
        for (final Upload upload : uploadRecords) {
            final RankingBook rankingBook = new RankingBook();
            rankingBook.setId(upload.getUploadedBook());
            final Book book = this.bookDao.queryById(upload.getUploadedBook());
            rankingBook.setBookName(book.getBook_title());
            final String uploadDate = dateFormat.format(upload.getUploadedDate());
            rankingBook.setUploadDate(uploadDate);
            rankingBooks.add(rankingBook);
        }
        return rankingBooks;
    }
    
    public void addDownloadTimes(final long id) {
        this.bookDao.addDownloadTimes(id);
    }
    
    public List<RankingBook> queryByDownloadTimes() {
        final List<RankingBook> rankingBooks = new ArrayList<RankingBook>();
        final List<Book> books = this.bookDao.queryByDownloadTimes();
        for (final Book book : books) {
            final RankingBook rankingBook = new RankingBook();
            rankingBook.setId(book.getId());
            rankingBook.setBookName(book.getBook_title());
            rankingBook.setDownload_times(book.getDownload_times());
            rankingBooks.add(rankingBook);
        }
        return rankingBooks;
    }
    
    public List<Book> searchBook(final String searchBy, final String searchTxt) throws ParseException {
        List<Book> books;
        if (searchBy.equals("book_title")) {
            books = this.bookDao.searchBookByTitle(searchTxt);
        }
        else {
            books = this.bookDao.searchBookByAuthor(searchTxt);
        }
        return books;
    }
}
