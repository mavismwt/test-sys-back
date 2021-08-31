package mwt.testsysback.service.impl;

import mwt.testsysback.service.JudgeService;
import org.apache.poi.ss.usermodel.charts.ScatterChartSeries;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Service
public class JudgeServiceImpl implements JudgeService {

    public static final int init=0,ac=1,wrongAnswer=2,compileError=8;
    private int result;
    private String errorMsg;
    private double accuracy;

    public String executeCode(String filePath, Integer codeId, Integer studentId){

//        CodeQuestion codeQuestion = codeQuestionService.getById(codeId);
//        codeQuestion.setSubmitNum(codeQuestion.getSubmitNum()+1);
//
//        UpdateWrapper<CodeQuestion> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.eq("id",codeId);
//
//        CodeSubmit codeSubmit = new CodeSubmit();
//        codeSubmit.setInput(code);
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        codeSubmit.setTime(simpleDateFormat.format(date.getTime()));
//        codeSubmit.setCodeId(codeId);
//        codeSubmit.setStudentId(studentId);

        String[] array1= filePath.split("/");
        String fileName = array1[array1.length-1];
        String path = filePath.substring(0,filePath.length()-fileName.length());
        String[] array2 = fileName.split("\\.");
        String language = array2[array2.length-1];
        String name = fileName.substring(0,fileName.length()-language.length()-1);

        new File(path).mkdir();
        String compile=null;
        String execute=null;
        errorMsg = null;
        result=init;  //0是初始状态，1为ac，2为答案有错误，8为编译错误
        int state = init;

        File file = null;

        switch(language){
            case "c": //c语言
                compile = "gcc "+path + name + ".c -o " + path + name;
                execute = path + name;
                break;
            case "cpp": //c语言
                compile = "gcc "+path + name + ".cpp -o " + path + name;
                execute = path + name;
                break;
            case "java": //java
                file = new File(path+"Main.java");
                compile = "javac "+path+ name + ".java";
                execute = "java -classpath "+path+" " + name;
                break;
            case "py": //python
                file = new File(path + name + ".py");
                compile = null;
                execute = "python " + path + name +".py";
                break;
        }


//        OutputStream outputStream = null;
//        try{
//            outputStream = new FileOutputStream(file);
//            byte[] bytes= code.getBytes();  //读取输出流中的字节
//            outputStream.write(bytes);     //写入文件
//        }catch(IOException e) {
//            e.printStackTrace();
//            errorMsg = "写文件失败！";
//            return errorMsg;
//        }
//        if(outputStream!=null) {
//            try {
//                outputStream.close();  //关闭输出文件流
//            }catch(IOException el) {
//                errorMsg = "关闭文件输出流失败！";
//                return errorMsg;
//            }
//        }
        try {
            //开始编译
            Process process = null;
            if(compile!=null){
                process = Runtime.getRuntime().exec(compile);
                try {
                    boolean res = process.waitFor(20000, TimeUnit.MILLISECONDS);
                    if (!res) {
                        process.destroy();
                        errorMsg = "编译超时";
                        result = state = compileError;
                        return errorMsg;
                    } else {
                        errorMsg = getErrors(process.getErrorStream());
                        if (errorMsg.indexOf("错误") != -1 || errorMsg.indexOf("Error") != -1 || errorMsg.indexOf("error") != -1 || errorMsg.indexOf("undefined") != -1) {//过滤warning
                            result = state = compileError;
                            return errorMsg;
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //开始执行
            if(state!=compileError) {
//                QueryWrapper<CodeJudgement> queryWrapper = new QueryWrapper<>();
//                queryWrapper.eq("code_id", codeId);
//                List<CodeJudgement> codeJudgementList = codeJudgementService.list(queryWrapper);
//
                int correct = 0;
//                for (CodeJudgement codeJudgement : codeJudgementList) {
//                    String input = codeJudgement.getTestInput();
                process = Runtime.getRuntime().exec(execute);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PrintWriter writer = new PrintWriter(process.getOutputStream());
//                    writer.println(input);
                writer.flush();
                writer.close();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String output = new String();
                String out = null;
                int i = 0;
                try {
                    while ((out = reader.readLine()) != null) {
                        if (i > 0) {
                            output += (" " + out);
                        } else {
                            output += out;
                        }
                        i++;
                    }
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //System.out.println(output);
                result = 3;
//                    if(output.equals(codeJudgement.getTestOutput()))
//                        correct++;
            }

//                if (correct == codeJudgementList.size()) {
//                    result = state = ac;
//                    codeQuestion.setCorrectNum(codeQuestion.getCorrectNum()+1);
//
//                    //给用户加积分
//                    QueryWrapper<CodeSubmit> queryWrapper1 = new QueryWrapper<>();
//                    queryWrapper1.eq("code_id",codeId);
//                    queryWrapper1.eq("student_id",studentId);
//                    List<CodeSubmit> codeSubmitList = codeSubmitService.list(queryWrapper1);
//                    boolean flag = true;
//                    Double accept = new Double("1.0");
//                    for(CodeSubmit codeSubmit1:codeSubmitList){
//                        if(Double.compare(codeSubmit1.getState(),accept)==0){
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if(flag==true){
//                        UpdateWrapper<Students> updateWrapper1 = new UpdateWrapper<>();
//                        updateWrapper1.eq("student_id",studentId);
//                        QueryWrapper<Students> queryWrapper2 = new QueryWrapper<>();
//                        queryWrapper2.eq("student_id",studentId);
//                        Students students = studentsService.getOne(queryWrapper2);
//                        students.setPoints(students.getPoints()+codeQuestion.getDegree()*10);
//                        studentsService.update(students,updateWrapper1);
//                    }
//                }
//                if (correct < codeJudgementList.size())
//                    result = state = wrongAnswer;
//
//                accuracy = (double) correct / codeJudgementList.size();
//                codeSubmit.setState(accuracy);
//                resultObj = new ResultObj(state,"您的程序通过率为" + accuracy*100 + "%");

//            }

        }catch (Exception e){
            e.printStackTrace();
        }
//        codeSubmitService.save(codeSubmit);
//        codeQuestionService.update(codeQuestion,updateWrapper);
        switch(result) {
            case 8:
                return "编译失败";
            case 3:
                return "编译通过";
            default:
                return "运行错误";
        }
    }

    private String getErrors(InputStream in) {
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        StringBuffer content=new StringBuffer();
        String line="";
        try {
            while((line=reader.readLine())!=null){
                int end=line.lastIndexOf("/");
                if(end!= -1) {
                    line=line.substring(end, line.length());
                }
                content.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
