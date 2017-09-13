package com.smartlibrary.service;

import com.smartlibrary.dao.bigScreenDao;
import com.smartlibrary.domain.BigScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 胡云飞 on 2017/9/11.
 */
@Service
public class bigScreenService {
    @Autowired
    private bigScreenDao bigscreendao;
    public Map<String, String> getNumber(){
        Map<String,String> number = new HashMap<String,String>();
        List<BigScreen> bigScreenList= bigscreendao.getnumber();
        for(int i=0;i<bigScreenList.size();i++){
            if (bigScreenList.get(i).getName().equals("all_gctrl")){
                number.put("all_gctrl",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_lend")){
                number.put("all_lend",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_print")){
                number.put("all_print",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_book")){
                number.put("all_book",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_seats")){
                number.put("all_seats",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("now_seats")){
                number.put("now_seats",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_crooms")){
                number.put("all_crooms",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("now_crooms")){
                number.put("now_crooms",bigScreenList.get(i).getCount());
            }
        }
        return number;
    }
    public Map<String, String> getIcpremonth(){
        Map<String,String> number = new HashMap<String,String>();
        List<BigScreen> bigScreenList= bigscreendao.getnumber();
        for(int i=0;i<bigScreenList.size();i++){
            if (bigScreenList.get(i).getName().equals("premonth_seats")){
                number.put("premonth_seats",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("premonth_crooms")){
                number.put("premonth_crooms",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("premonth_ereads")){
                number.put("premonth_ereads",bigScreenList.get(i).getCount());
            }
        }
        return number;
    }
    public Map<String, String> getPrintpremonth(){
        Map<String,String> number = new HashMap<String,String>();
        List<BigScreen> bigScreenList= bigscreendao.getnumber();
        for(int i=0;i<bigScreenList.size();i++){
            if (bigScreenList.get(i).getName().equals("premonth_print_p")){
                number.put("premonth_print_p",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("premonth_print_c")){
                number.put("premonth_print_c",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("premonth_print_s")){
                number.put("premonth_print_s",bigScreenList.get(i).getCount());
            }
        }
        return number;
    }
    public Map<String, String> getRating(){
        Map<String,String> number = new HashMap<String,String>();
        List<BigScreen> bigScreenList= bigscreendao.getnumber();
        for(int i=0;i<bigScreenList.size();i++){
            if (bigScreenList.get(i).getName().equals("now_seats")){
                number.put("now_seats",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_seats")){
                number.put("all_seats",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("now_crooms")){
                number.put("now_crooms",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_crooms")){
                number.put("all_crooms",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("now_ereads")){
                number.put("now_ereads",bigScreenList.get(i).getCount());
            }
            else if (bigScreenList.get(i).getName().equals("all_ereads")){
                number.put("all_ereads",bigScreenList.get(i).getCount());
            }
        }
        return number;
    }
    public Map<String, List> getLend(){
        Map<String,List> lenddata = new HashMap<String,List>();
        List<BigScreen> bigScreenList= bigscreendao.getlend();
        List<String> hour = new ArrayList<String>();
        List<String> lend = new ArrayList<String>();
        for(int i=0;i<bigScreenList.size();i++){
            hour.add(bigScreenList.get(i).getHour());
            lend.add(bigScreenList.get(i).getLend());
        }
        lenddata.put("hour",hour);
        lenddata.put("lend",lend);
        return lenddata;
    }
    public Map<String, List> getResource(){
        Map<String,List> resourcedata = new HashMap<String,List>();
        List<BigScreen> bigScreenList= bigscreendao.getresource();
        List<String> hour = new ArrayList<String>();
        List<String> lend = new ArrayList<String>();
        List<String> gctrl = new ArrayList<String>();
        List<String> print = new ArrayList<String>();
        List<String> seats = new ArrayList<String>();
        List<String> crooms = new ArrayList<String>();
        List<String> ereads = new ArrayList<String>();
        for(int i=6;i<bigScreenList.size()-2;i++){
            hour.add(bigScreenList.get(i).getHour());
            lend.add(bigScreenList.get(i).getLend());
            gctrl.add(bigScreenList.get(i).getGctrl());
            print.add(bigScreenList.get(i).getPrint());
            seats.add(bigScreenList.get(i).getSeats());
            crooms.add(bigScreenList.get(i).getCrooms());
            ereads.add(bigScreenList.get(i).getEreads());
        }
        resourcedata.put("hour",hour);
        resourcedata.put("lend",lend);
        resourcedata.put("gctrl",gctrl);
        resourcedata.put("print",print);
        resourcedata.put("seats",seats);
        resourcedata.put("crooms",crooms);
        resourcedata.put("ereads",ereads);
        return resourcedata;
    }
    public Map<String, List> getGctrl(){
        Map<String,List> gctrldata = new HashMap<String,List>();
        List<BigScreen> bigScreenList= bigscreendao.getgctrl();
        List<String> academy = new ArrayList<String>();
        List<String> all_times = new ArrayList<String>();
        for(int i=0;i<10;i++){
            academy.add(bigScreenList.get(i).getAcademy());
            all_times.add(bigScreenList.get(i).getAll_times());
        }
        Collections.reverse(academy);
        Collections.reverse(all_times);
        gctrldata.put("academy",academy);
        gctrldata.put("all_times",all_times);
        return gctrldata;
    }
    public Map<String, List> getLend_academy(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 15);
        Date d = c.getTime();
        String firstday = format.format(d);
        c.setTime(new Date());
        c.add(Calendar.DATE, - 1);
        d = c.getTime();
        String lastday = format.format(d);
        StringBuilder sb = new StringBuilder(firstday);
        StringBuilder sb1 = new StringBuilder(lastday);
        sb.replace(0, 4, "2015");
        sb1.replace(0, 4, "2015");
        firstday = sb.toString();
        lastday = sb1.toString();
        BigScreen bs = new BigScreen();
        bs.setFirstday(firstday);
        bs.setLastday(lastday);
        Map<String,List> lend_academydata = new HashMap<String,List>();
        List<BigScreen> bigScreenList= bigscreendao.getlend_academy(bs);
        List<String> academy = new ArrayList<String>();
        List<String> ymdate = new ArrayList<String>();
        List<String> student = new ArrayList<String>();
        List<String> teacher = new ArrayList<String>();
        List<String> graduate = new ArrayList<String>();
        List<String> other = new ArrayList<String>();
        for(int i=0;i<bigScreenList.size();i++){
            if(!academy.contains(bigScreenList.get(i).getAcademy())){
                academy.add(bigScreenList.get(i).getAcademy());
            }
            if(!ymdate.contains(bigScreenList.get(i).getYmddate())){
                ymdate.add(bigScreenList.get(i).getYmddate());
            }
            student.add(bigScreenList.get(i).getStudent());
            teacher.add(bigScreenList.get(i).getTeacher());
            graduate.add(bigScreenList.get(i).getGraduate());
            other.add(bigScreenList.get(i).getOther());
        }
        lend_academydata.put("academy",academy);
        lend_academydata.put("ymdate",ymdate);
        lend_academydata.put("student",student);
        lend_academydata.put("teacher",teacher);
        lend_academydata.put("graduate",graduate);
        lend_academydata.put("other",other);
        return lend_academydata;
    }
    public Map<String, List> getGctrl_academy(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, - 15);
        Date d = c.getTime();
        String firstday = format.format(d);
        c.setTime(new Date());
        c.add(Calendar.DATE, - 1);
        d = c.getTime();
        String lastday = format.format(d);
        StringBuilder sb = new StringBuilder(firstday);
        StringBuilder sb1 = new StringBuilder(lastday);
        sb.replace(0, 4, "2015");
        sb1.replace(0, 4, "2015");
        firstday = sb.toString();
        lastday = sb1.toString();
        BigScreen bs = new BigScreen();
        bs.setFirstday(firstday);
        bs.setLastday(lastday);
        Map<String,List> lend_academydata = new HashMap<String,List>();
        List<BigScreen> bigScreenList= bigscreendao.getgctrl_academy(bs);
        List<String> academy = new ArrayList<String>();
        List<String> ymdate = new ArrayList<String>();
        List<String> f_times = new ArrayList<String>();
        List<String> m_times = new ArrayList<String>();
        for(int i=0;i<bigScreenList.size();i++){
            if(!academy.contains(bigScreenList.get(i).getAcademy())){
                academy.add(bigScreenList.get(i).getAcademy());
            }
            if(!ymdate.contains(bigScreenList.get(i).getYmddate())){
                ymdate.add(bigScreenList.get(i).getYmddate());
            }
            f_times.add(bigScreenList.get(i).getF_times());
            m_times.add(bigScreenList.get(i).getM_times());
        }
        lend_academydata.put("academy",academy);
        lend_academydata.put("ymdate",ymdate);
        lend_academydata.put("f_times",f_times);
        lend_academydata.put("m_times",m_times);
        return lend_academydata;
    }
}
