package com.boombz.blog.serviceTest;

import com.boombz.blog.domain.Day;
import com.boombz.blog.domain.User;
import com.boombz.blog.repository.DayRepository;
import com.boombz.blog.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @program: we
 * @description:
 * @author: boombaozi.com
 * @create: 2018-05
 **/
@Service
public class DayServiceImpl implements DayService {

    @Autowired
    private DayRepository dayRepository;

    @Transactional
    public ServerResponse addDay(Day day, HttpSession session){
        User user = (User) session.getAttribute("user");
        day.setAuthorid(user.getId());
        day.setGroupid(user.getGroupid());

        day.setCreatetime(new Date());
        day.setUpdatetime(new Date());
        day.setStatus("1");
        day.setIschecked("1");

        Day day1=dayRepository.save(day);
        if(day1!=null) {
            return ServerResponse.createBySuccess();
        }else {
            return ServerResponse.createByErrorMessage("添加失败");
        }
    }

    @Transactional
    @Override
    public ServerResponse<Page<Day>> findAllDayByGroupId(Integer integer, Pageable pageable) {

    Page<Day> days  = dayRepository.findAllBygroupidAndStatusOrderByTimeDesc(integer,"1",pageable);

    return ServerResponse.createBySuccess(days);

    }

    @Override
    public ServerResponse deleteDaybyId(Integer id) {
        Day day = dayRepository.findOne(id);
        day.setStatus("2");
        day.setUpdatetime(new Date());
        dayRepository.save(day);
        return ServerResponse.createBySuccessMessage("删除成功");
    }

    @Override
    public ServerResponse realDeleteDaybyId(Integer id) {
        dayRepository.delete(id);
        return ServerResponse.createBySuccessMessage("真正的删除了");
    }

    @Override
    public ServerResponse recoverDay(Integer id) {
        Day day = dayRepository.findOne(id);
        day.setStatus("1");
        day.setUpdatetime(new Date());
        dayRepository.save(day);
        return ServerResponse.createBySuccessMessage("恢复成功");
    }

    @Override
    public ServerResponse<Page<Day>> findAllDay(Pageable pageable) {
        Page<Day> days = dayRepository.findAllByOrderByIdDesc(pageable);
        if (days == null) {
            return ServerResponse.createByErrorMessage("查询失败");
        } else {
            return ServerResponse.createBySuccess(days);
        }
    }
}
