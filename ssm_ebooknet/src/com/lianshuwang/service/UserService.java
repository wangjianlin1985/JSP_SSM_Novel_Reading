// 
// 
// 

package com.lianshuwang.service;

import com.lianshuwang.domin.Book;
import java.text.SimpleDateFormat;
import com.lianshuwang.domin.Upload;
import java.util.ArrayList;
import com.lianshuwang.helper.UploadHelper;
import java.util.List;
import java.sql.Timestamp;
import com.lianshuwang.domin.Feedback;
import java.util.Date;
import java.util.Random;
import com.lianshuwang.domin.Contribution;
import com.lianshuwang.domin.Avatar;
import com.lianshuwang.helper.UserHelper;
import com.lianshuwang.domin.User;
import com.lianshuwang.dao.BookDao;
import com.lianshuwang.dao.UploadDao;
import com.lianshuwang.dao.FeedbackDao;
import com.lianshuwang.dao.ContributionDao;
import com.lianshuwang.dao.AvatarDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.lianshuwang.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private AvatarDao avatarDao;
    @Autowired
    private ContributionDao contributionDao;
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private UploadDao uploadDao;
    @Autowired
    private BookDao bookDao;
    
    public User queryById(final long id) {
        final User user = this.userDao.queryById(id);
        return user;
    }
    
    public UserHelper getLoginUser(final String userCode, final String userPassword) {
        final User user = this.userDao.queryByLogin(userCode, userPassword);
        if (user != null) {
            final Avatar avatar = this.avatarDao.queryById(user.getAvatarNum());
            final Contribution contribution = this.contributionDao.queryByValue(user.getContribution());
            final UserHelper userHelper = new UserHelper();
            userHelper.setId(user.getId());
            userHelper.setUserCode(user.getUserCode());
            userHelper.setUserName(user.getUserName());
            userHelper.setEmail(user.getEmail());
            userHelper.setAvatar(avatar);
            userHelper.setContributionValue(user.getContribution());
            userHelper.setContribution(contribution);
            userHelper.setCreationDate(user.getCreationDate());
            return userHelper;
        }
        return null;
    }
    
    public int checkUserCode(final String userCode) {
        final int count = this.userDao.queryByUserCode(userCode);
        return count;
    }
    
    public void addUser(final User user) {
        final int avatarNum = new Random().nextInt(10) + 1;
        user.setAvatarNum(avatarNum);
        user.setContribution(0);
        final Date date = new Date();
        user.setCreationDate(date);
        this.userDao.addUser(user);
    }
    
    public void updateUserContribution(final int addValue, final long userID) {
        this.userDao.updateUserContribution(addValue, userID);
    }
    
    public void updateUserInfo(final User user) {
        this.userDao.updateUserInfo(user);
    }
    
    public int getAvatarId(final String avatar_img) {
        final int avatarId = this.avatarDao.queryByImgPath(avatar_img);
        return avatarId;
    }
    
    public void updateUserPassword(final long id, final String password) {
        this.userDao.updateUserPassword(id, password);
    }
    
    public void addFeedback(final long userId, final String contact, final String suggestion) {
        final Feedback feedback = new Feedback();
        feedback.setLoginedUser(userId);
        feedback.setContact(contact);
        feedback.setSuggestion(suggestion);
        feedback.setPostTime(new Timestamp(new Date().getTime()));
        this.feedbackDao.addFeedback(feedback);
    }
    
    public List<UploadHelper> getUploadedBook(final long userId) {
        final List<UploadHelper> uploadHelperList = new ArrayList<UploadHelper>();
        final List<Upload> uploadList = this.uploadDao.queryByUserId(userId);
        for (int i = 0; i < uploadList.size(); ++i) {
            final UploadHelper upload = new UploadHelper();
            upload.setId(i + 1);
            final Book book = this.bookDao.queryById(uploadList.get(i).getUploadedBook());
            upload.setBookTitle(book.getBook_title());
            upload.setBookAuthor(book.getBook_author());
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            final String uploadDate = dateFormat.format(uploadList.get(i).getUploadedDate());
            upload.setUploadedDate(uploadDate);
            upload.setDownloadTimes(book.getDownload_times());
            uploadHelperList.add(upload);
        }
        return uploadHelperList;
    }
}
