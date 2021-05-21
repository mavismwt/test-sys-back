package mwt.testsysback.service.impl;

import mwt.testsysback.mapper.UserMapper;
import mwt.testsysback.entity.User;
import mwt.testsysback.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper UserMapper;

    @Override
    public List<User> getAllUser() {
        System.out.println("业务层：查询用户");
        return UserMapper.getAllUser();
    }

    @Override
    public User getUserById(int user_id){
        System.out.println("业务层：用id查找用户");
        return UserMapper.getUserById(user_id);
    }

    @Override
    public List<User> getUsers(String nickname, String collection, String identity) {
        System.out.println("业务层：条件查询用户");
        return UserMapper.getUsers(nickname, collection, identity);
    }

    @Override
    public int insertUser(User user){
        System.out.println("业务层：注册用户");
        if (UserMapper.getUserByUsername(user.getUsername()) == null) {
            return UserMapper.insertUser(user);
        }
        return 0;
    }

    @Override
    public User login(User user) {
        System.out.println("业务层：用户登录");
        return UserMapper.login(user);
    }

    @Override
    public int updateUser(User user) {
        System.out.println("业务层：修改用户信息");
        return UserMapper.updateUser(user);
    }

    @Override
    public int updateUserScore(double score, String username) {
        System.out.println("业务层：修改成绩");
        return UserMapper.updateUserScore(score, username);
    }

    @Override
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String str) {
        try {
            @SuppressWarnings("resource")

            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("记录");

            HSSFRow row = null;

            int columnIndex = 0;

            row = sheet.createRow(0);
            row.setHeight((short) (22.50 * 20));//设置行高
            row.createCell(columnIndex).setCellValue("序号");
            row.createCell(++columnIndex).setCellValue("姓名");
            row.createCell(++columnIndex).setCellValue("学号");
            row.createCell(++columnIndex).setCellValue("班级");
            row.createCell(++columnIndex).setCellValue("得分");

            String[] items = str.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");


            for (int i = 0; i < items.length; i++) {
                int id = Integer.parseInt(items[i]);
                User user = getUserById(id);
                row = sheet.createRow(i + 1);
                columnIndex = 0;

                JSONArray jsonArray = new JSONArray(user.getCollection());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                row.createCell(columnIndex).setCellValue(i + 1);
                row.createCell(++columnIndex).setCellValue(user.getNickname());
                row.createCell(++columnIndex).setCellValue(user.getUsername());
                row.createCell(++columnIndex).setCellValue(jsonObject.getString("collection_name"));
                row.createCell(++columnIndex).setCellValue(user.getScore());
            }

            sheet.setDefaultRowHeight((short) (16.5 * 20));

            //列宽自适应
            for (int i = 0; i <= 11; i++) {
                sheet.autoSizeColumn(i);
            }

            String title= "name";

            response.setHeader("Content-disposition", "attachment;fileName=" + title + ".xls");
            response.setContentType("application/octet-stream;charset=utf-8");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportExcelAll(HttpServletRequest request, HttpServletResponse response, User user) {
        try {
            List<User> users = getUsers("", user.getCollection(), "student");
            System.out.println(user.getCollection());
            System.out.println(users);

            @SuppressWarnings("resource")
            HSSFWorkbook wb = new HSSFWorkbook();

            HSSFSheet sheet = wb.createSheet("记录");

            HSSFRow row = null;

            int columnIndex = 0;

            row = sheet.createRow(0);
            row.setHeight((short) (22.50 * 20));//设置行高
            row.createCell(columnIndex).setCellValue("序号");
            row.createCell(++columnIndex).setCellValue("姓名");
            row.createCell(++columnIndex).setCellValue("学号");
            row.createCell(++columnIndex).setCellValue("班级");
            row.createCell(++columnIndex).setCellValue("得分");

            for (int i = 0; i < users.size(); i++) {
                row = sheet.createRow(i + 1);
                User record = users.get(i);
                columnIndex = 0;

                JSONArray jsonArray = new JSONArray(record.getCollection());
                JSONObject jsonObject = jsonArray.getJSONObject(0);

                row.createCell(columnIndex).setCellValue(i + 1);
                row.createCell(++columnIndex).setCellValue(record.getNickname());
                row.createCell(++columnIndex).setCellValue(record.getUsername());
                row.createCell(++columnIndex).setCellValue(jsonObject.getString("collection_name"));
                row.createCell(++columnIndex).setCellValue(record.getScore());
            }

            sheet.setDefaultRowHeight((short) (16.5 * 20));

            //列宽自适应
            for (int i = 0; i <= 11; i++) {
                sheet.autoSizeColumn(i);
            }

            String title= "name";

            response.setHeader("Content-disposition", "attachment;fileName=" + title + ".xls");
            response.setContentType("application/octet-stream;charset=utf-8");
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

