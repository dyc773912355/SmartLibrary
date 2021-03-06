package com.smartlibrary.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smartlibrary.common.CustomXWPFDocument;
import com.smartlibrary.common.Interface_processing;
import com.smartlibrary.common.Picture;
import com.smartlibrary.common.WordUtil;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 胡云飞 on 2017/11/1.
 */
@Service
public class reportService {
    public String getReport(String picBase64Info){
        Picture picture = new Picture();
        JSONObject json = JSONObject.parseObject(picBase64Info);
        //System.out.println(json.getString("picBase64Info1"));
        Map<String,Object> header1 = picture.returnpicture(json.getString("img_Book_Amount"),400,550);
        Map<String,Object> header2 = picture.returnpicture(json.getString("img_gctrl_amount"),600,600);
        Map<String,Object> header3 = picture.returnpicture(json.getString("img_month_amount"),600,600);
        Map<String,Object> header4 = picture.returnpicture(json.getString("img_type_percent1"),300,500);
        Map<String,Object> header5 = picture.returnpicture(json.getString("img_type_percent2"),400,600);
        Map<String,Object> header6 = picture.returnpicture(json.getString("img_lend_yearamount"),500,550);
        Map<String,Object> header7 = picture.returnpicture(json.getString("img_type_reader"),600,550);
        Map<String,Object> header8 = picture.returnpicture(json.getString("img_academy_img"),600,550);
        Map<String,Object> header9 = picture.returnpicture(json.getString("img_leader_img"),600,550);
        Map<String,Object> header10 = picture.returnpicture(json.getString("img_borrow_img1"),300,500);
        Map<String,Object> header11 = picture.returnpicture(json.getString("img_borrow_img2"),300,500);
        Map<String,Object> header12 = picture.returnpicture(json.getString("img_read_img"),600,550);
        Map<String,Object> header13 = picture.returnpicture(json.getString("img_seat_img"),300,500);
        Map<String,Object> header14 = picture.returnpicture(json.getString("img_ebook_spend"),600,550);
        Map<String,Object> header15 = picture.returnpicture(json.getString("img_print_amount"),600,550);
        Map<String,Object> header16 = picture.returnpicture(json.getString("img_print_dy"),400,500);
        Map<String,Object> header17 = picture.returnpicture(json.getString("img_print_fy"),400,500);
        Map<String,Object> header18 = picture.returnpicture(json.getString("img_print_sm"),400,500);
        Map<String,Object> header19 = picture.returnpicture(json.getString("img_print_day"),600,550);
        Calendar date = Calendar.getInstance();
        String currentyear = String.valueOf(date.get(Calendar.YEAR));
        Interface_processing interface_processing = new Interface_processing();
        String path = this.getClass().getClassLoader().getResource("/").getPath();
        path = path.substring(0,path.indexOf("WEB-INF"))+"report/";
        String wordpath = path + "88.docx";
        String towordpath = path + "2.docx";
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("${school}", "浙江工业");
        param.put("${img_Book_Amount}", header1);
        param.put("${img_gctrl_amount}", header2);
        param.put("${img_month_amount}", header3);
        param.put("${img_type_percent1}", header4);
        param.put("${img_type_percent2}", header5);
        param.put("${img_lend_yearamount}", header6);
        param.put("${img_type_reader}", header7);
        param.put("${img_academy_img}", header8);
        param.put("${img_leader_img}", header9);
        param.put("${img_borrow_img1}", header10);
        param.put("${img_borrow_img2}", header11);
        param.put("${img_read_img}", header12);
        param.put("${img_seat_img}", header13);
        param.put("${img_ebook_spend}", header14);
        param.put("${img_print_amount}", header15);
        param.put("${img_print_dy}", header16);
        param.put("${img_print_fy}", header17);
        param.put("${img_print_sm}", header18);
        param.put("${img_print_day}", header19);
        JSONArray section1_2_1 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getGctrlsCountBy_year");
        JSONArray section1_2_2 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getGctrlsCountBy_month");
        JSONArray section1_2_3 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getmankindCount_Byoneyear");
        JSONArray section2_1 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getBook_LendCountBy_year");
        JSONArray section2_2_1 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getBookkindAllCount_Byyear");
        JSONArray section2_3 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getReder_booklendCount_Byyear");
        JSONArray section2_4_1 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getop20authorBy_year");
        JSONArray section2_4_2 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getop20bookBy_year");
        JSONArray section2_5 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getBook_LendCountBy_academyAndyear");
        JSONArray section2_7 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getbooklendPeopleAndCount_Byhour");
        JSONArray section3_1 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/geICdurationCountBy_year");
        JSONArray section3_3 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getDeviceCount_Byhour");
        JSONObject section4_1 = interface_processing.return_jsonobject("http://localhost:8080/SmartLibrary/printtimes/byyear");
        JSONArray section4_2 = interface_processing.return_json("http://localhost:8080/SmartLibrary/schoolReport/getDeviceCount_Byhour");
        TreeSet<String> max_tree = new TreeSet<String>();
        int max = 0;
        for(int i=0;i<section1_2_1.size();i++){
            max_tree.add(section1_2_1.getJSONObject(i).getString("year"));
            if(i>0){
                if(Integer.valueOf(section1_2_1.getJSONObject(i).getString("gctrls"))>Integer.valueOf(section1_2_1.getJSONObject(i-1).getString("gctrls"))){
                    max = i;
                }
            }
        }
        param.put("${gctrl_year1}",max_tree.first());
        param.put("${gctrl_year2}",max_tree.last());
        param.put("${gctrl_year3}",section1_2_1.getJSONObject(max).getString("year"));
        param.put("${gctrl_amount}",section1_2_1.getJSONObject(max).getString("gctrls"));
        int sum_section1_2_2 = 0;
        int max_section1_2_2 = 0;
        for(int i=0;i<section1_2_2.size();i++){
            sum_section1_2_2+=section1_2_2.getJSONObject(i).getInteger("gctrl_times");
            if(i>0){
                if(section1_2_2.getJSONObject(i).getInteger("gctrl_times")>section1_2_2.getJSONObject(i-1).getInteger("gctrl_times")){
                    max_section1_2_2 = i;
                }
            }
        }
        param.put("${gctrl_month_year1}",section1_2_2.getJSONObject(0).getString("year"));
        param.put("${gctrl_month_month1}",section1_2_2.getJSONObject(0).getString("month"));
        param.put("${gctrl_month_year2}",section1_2_2.getJSONObject(section1_2_2.size()-1).getString("year"));
        param.put("${gctrl_month_month2}",section1_2_2.getJSONObject(section1_2_2.size()-1).getString("month"));
        param.put("${gctrl_month_amount}",String.valueOf(sum_section1_2_2));
        param.put("${gctrl_month_month3}",section1_2_2.getJSONObject(max_section1_2_2).getString("month"));
        param.put("${img_title_year}",section1_2_2.getJSONObject(0).getString("year"));
        param.put("${gctrl_type_year1}",section1_2_3.getJSONObject(0).getString("year"));
        param.put("${student_borrow_year}",section1_2_3.getJSONObject(0).getString("year"));
        param.put("${img_borrow_year1}",section1_2_3.getJSONObject(0).getString("year"));
        param.put("${img_borrow_year2}",section1_2_3.getJSONObject(0).getString("year"));
        List arr1_2_3 = new ArrayList();
        List arr_2_8_2 = new ArrayList();
        int sum1_2_3 = 0;
        int sum_2_8_2 = 0;
        for(int i=0;i<section1_2_3.size();i++){
            arr1_2_3.add(section1_2_3.getJSONObject(i).getInteger("gctrl"));
            sum1_2_3+=section1_2_3.getJSONObject(i).getInteger("gctrl");
            arr_2_8_2.add(section1_2_3.getJSONObject(i).getInteger("lend"));
            sum_2_8_2+=section1_2_3.getJSONObject(i).getInteger("lend");
        }
        float percent1_2_3 = (float)Integer.valueOf(Collections.max(arr1_2_3).toString())/sum1_2_3*100;
        float percent2_8_2 = (float)Integer.valueOf(Collections.max(arr_2_8_2).toString())/sum_2_8_2*100;
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        //String s = df.format(percent1_2_3);//返回的是String类型
        //percent1_2_3 = (float)(Math.round(percent1_2_3*100)/100);
        param.put("${gctrl_type_student}",section1_2_3.getJSONObject(arr1_2_3.indexOf(Collections.max(arr1_2_3))).getString("man_kind"));
        param.put("${gctrl_type_percent}",df.format(percent1_2_3));
        param.put("${student_borrow_type}",section1_2_3.getJSONObject(arr_2_8_2.indexOf(Collections.max(arr_2_8_2))).getString("man_kind"));
        param.put("${student_borrow_percent}",df.format(percent2_8_2));
        ArrayList<Integer> array2_1 = new ArrayList<Integer>();
        for(int i=0;i<section2_1.size();i++){
            array2_1.add(section2_1.getJSONObject(i).getInteger("book_lend_times"));
        }
        ArrayList<Integer> array2_1_sort =new ArrayList<Integer>(array2_1);
        Collections.sort(array2_1_sort);
        int max2_1 = Collections.max(array2_1);
        int secondmax2_1 = array2_1_sort.get(array2_1_sort.size()-2);
        param.put("${lend_top1_year}",section2_1.getJSONObject(array2_1.indexOf(max2_1)).getString("year"));
        param.put("${lend_top1_amount}",String.valueOf(max2_1));
        param.put("${lend_top2_year}",section2_1.getJSONObject(array2_1.indexOf(secondmax2_1)).getString("year"));
        param.put("${lend_top2_amount}",String.valueOf(secondmax2_1));
        ArrayList<Integer> array2_2_1 = new ArrayList<Integer>();
        for(int i=0;i<section2_2_1.size();i++){
            array2_2_1.add(section2_2_1.getJSONObject(i).getInteger("book_lend_times"));
        }
        ArrayList<Integer> array2_2_1_sort =new ArrayList<>(array2_2_1);
        Collections.sort(array2_2_1_sort);
        int max2_2_1 = Collections.max(array2_2_1);
        int secondmax2_2_1 = array2_2_1_sort.get(array2_2_1_sort.size()-2);
        param.put("${lend_book_year}",section2_2_1.getJSONObject(0).getString("year"));
        param.put("${lend_book_type1}",section2_2_1.getJSONObject(array2_2_1.indexOf(max2_2_1)).getString("book_kind"));
        param.put("${lend_book_time1}",String.valueOf(max2_2_1));
        param.put("${lend_book_type2}",section2_2_1.getJSONObject(array2_2_1.indexOf(secondmax2_2_1)).getString("book_kind"));
        param.put("${lend_book_time2}",String.valueOf(secondmax2_2_1));
        param.put("${img_type_year}",section2_2_1.getJSONObject(0).getString("year"));
        param.put("${lend_reader_year1}",currentyear);
        param.put("${lend_reader_name1}",section2_3.getJSONObject(0).getString("name"));
        param.put("${lend_reader_name2}",section2_3.getJSONObject(1).getString("name"));
        param.put("${lend_reader_name3}",section2_3.getJSONObject(2).getString("name"));
        param.put("${lend_reader_times1}",section2_3.getJSONObject(0).getString("all_lend_times"));
        param.put("${lend_reader_times2}",section2_3.getJSONObject(1).getString("all_lend_times"));
        param.put("${lend_reader_times3}",section2_3.getJSONObject(2).getString("all_lend_times"));
        for(int i=0;i<10;i++){
            int number = i+1;
            param.put("${1-"+number+"-1}",section2_3.getJSONObject(i).getString("account"));
            param.put("${1-"+number+"-2}",section2_3.getJSONObject(i).getString("name"));
            param.put("${1-"+number+"-3}",section2_3.getJSONObject(i).getString("academy"));
            param.put("${1-"+number+"-4}",section2_3.getJSONObject(i).getString("all_lend_times"));
        }
        param.put("${form_top10_year}",currentyear);
        param.put("${top_book_name}",section2_4_1.getJSONObject(0).getString("book_name"));
        param.put("${top_book_times}",section2_4_1.getJSONObject(0).getString("lend_times"));
        param.put("${form_numbertop15_year}",section2_4_1.getJSONObject(0).getString("year"));
        for(int i=0;i<15;i++){
            int number = i+1;
            param.put("${2-"+number+"-1}",section2_4_1.getJSONObject(i).getString("book_name"));
            param.put("${2-"+number+"-2}",section2_4_1.getJSONObject(i).getString("author"));
            param.put("${2-"+number+"-3}",section2_4_1.getJSONObject(i).getString("book_pubyear"));
            param.put("${2-"+number+"-4}",section2_4_1.getJSONObject(i).getString("lend_times"));
        }
        param.put("${fav_book_name}",section2_4_2.getJSONObject(0).getString("book_name"));
        param.put("${fav_book_author}",section2_4_2.getJSONObject(0).getString("author"));
        param.put("${form_typetop15_year}",section2_4_2.getJSONObject(0).getString("year"));
        for(int i=0;i<15;i++){
            int number = i+1;
            param.put("${3-"+number+"-1}",section2_4_2.getJSONObject(i).getString("book_name"));
            param.put("${3-"+number+"-2}",section2_4_2.getJSONObject(i).getString("author"));
            param.put("${3-"+number+"-3}",section2_4_2.getJSONObject(i).getString("book_pubyear"));
            param.put("${3-"+number+"-4}",section2_4_2.getJSONObject(i).getString("lend_times"));
        }
        param.put("${lend_top3_accdemy1}",section2_5.getJSONObject(0).getString("academy"));
        param.put("${lend_top3_accdemy2}",section2_5.getJSONObject(1).getString("academy"));
        param.put("${lend_top3_accdemy3}",section2_5.getJSONObject(2).getString("academy"));
        param.put("${img_academy_year}",section2_5.getJSONObject(0).getString("year"));
        param.put("${library_leader_year}",section2_7.getJSONObject(0).getString("year"));
        ArrayList<Integer> arrlend_2_7 = new ArrayList<>();
        ArrayList<Integer> arrpeople2_7 = new ArrayList<>();
        for(int i=0;i<section2_7.size();i++){
            arrlend_2_7.add(section2_7.getJSONObject(i).getInteger("lend"));
            arrpeople2_7.add(section2_7.getJSONObject(i).getInteger("lend_people"));
        }
        param.put("${library_leader_clock1}",section2_7.getJSONObject(arrlend_2_7.indexOf(Collections.max(arrlend_2_7))).getString("hour")+"点");
        param.put("${library_leader_clock2}",section2_7.getJSONObject(arrlend_2_7.indexOf(Collections.max(arrlend_2_7))+1).getString("hour")+"点");
        param.put("${library_book_amount}",section2_7.getJSONObject(arrlend_2_7.indexOf(Collections.max(arrlend_2_7))).getString("lend"));
        param.put("${library_lead_clock3}",section2_7.getJSONObject(arrpeople2_7.indexOf(Collections.max(arrpeople2_7))).getString("hour")+"点");
        param.put("${library_lead_clock4}",section2_7.getJSONObject(arrpeople2_7.indexOf(Collections.max(arrpeople2_7))+1).getString("hour")+"点");
        param.put("${library_leader_amount}",section2_7.getJSONObject(arrpeople2_7.indexOf(Collections.max(arrpeople2_7))).getString("lend_people"));
        param.put("${img_leader_year}",section2_7.getJSONObject(0).getString("year"));
        param.put("${eread_read_year}",section3_1.getJSONObject(0).getString("year"));
        ArrayList<Integer> arr_3_1 = new ArrayList<>();
        for(int i=0;i<section3_1.size();i++){
            arr_3_1.add(section3_1.getJSONObject(i).getInteger("eread_times"));
        }
        param.put("${eread_read_month}",section3_1.getJSONObject(arr_3_1.indexOf(Collections.max(arr_3_1))).getString("month"));
        param.put("${eread_read_time}",String.valueOf(Collections.max(arr_3_1)));
        param.put("${img_read_year}",section3_1.getJSONObject(0).getString("year"));
        ArrayList<Integer> arr_3_3 = new ArrayList<>();
        for(int i=0;i<section3_3.size();i++){
            arr_3_3.add(section3_3.getJSONObject(i).getInteger("ereads"));
        }
        param.put("${ebook_spent_clock1}",section3_3.getJSONObject(arr_3_3.indexOf(Collections.max(arr_3_3))).getString("hour"));
        param.put("${ebook_spent_clock2}",section3_3.getJSONObject(arr_3_3.indexOf(Collections.max(arr_3_3))+1).getString("hour"));
        List<String> year = (List) section4_1.get("year");
        List<Integer> prints = (List) section4_1.get("printtimes");
        param.put("${print_amount_year}",year.get(year.size()-1));
        param.put("${print_amount_page1}",String.valueOf(prints.get(prints.size()-1)));
        int printnumber1 = prints.get(prints.size()-1);
        int printnumber2 = prints.get(prints.size()-2);
        if(printnumber1<printnumber2){
            param.put("${print_amount_page2}","减少");
            param.put("${print_amount_page3}",String.valueOf(printnumber2-printnumber1));
        }
        else{
            param.put("${print_amount_page2}","增加");
            param.put("${print_amount_page3}",String.valueOf(printnumber1-printnumber2));
        }
        ArrayList<Integer> arr_4_2 = new ArrayList<>();
        for(int i=0;i<section4_2.size();i++){
            arr_4_2.add(section4_2.getJSONObject(i).getInteger("prints"));
        }
        param.put("${print_day_clock1}",section4_2.getJSONObject(arr_4_2.indexOf(Collections.max(arr_4_2))).getString("hour"));
        param.put("${print_day_clock2}",section4_2.getJSONObject(arr_4_2.indexOf(Collections.max(arr_4_2))+1).getString("hour"));
        CustomXWPFDocument doc = WordUtil.generateWord(param, wordpath);
        FileOutputStream fopts = null;
        try {
            fopts = new FileOutputStream(towordpath);
            doc.write(fopts);
            fopts.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }
}
