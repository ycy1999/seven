package Jobs1;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.*;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class jobs1 {
    public static void main(String[] args) throws IOException {
        String srcCSV = "F:\\123456\\SPark课堂\\任务\\jobs6.csv";
        String targetFile = "F:\\123456\\SPark课堂\\test\\test6.csv";
        CsvReader reader = new CsvReader(srcCSV, ',', Charset.forName("UTF-8"));
        CsvWriter write =new CsvWriter(targetFile,',',Charset.forName("GBK"));
        //各字段以引号标记
        //write.setForceQualifier(true);
        //路过表头
        reader.readHeaders();
        //逐条读取记录，直至读完
        String[] header = reader.getHeaders();
        write.writeRecord(header, true);
        StringBuilder sb = new StringBuilder();

        //把头保存起来
        for(String x:header){
            if(sb.length()>0){
                sb.append(",");
            }
            sb.append(x);
            System.out.println(x);
        }
        System.out.print(sb.toString());

        while (reader.readRecord()) {
            int columnCount = reader.getColumnCount();
//            System.out.println(columnCount);

            String[] line = new String[columnCount];
            for(int i = 0; i<columnCount; i++) {
                String str = reader.get(i);
                Pattern p = Pattern.compile("\\s+|\t+|\n|\r");
                Matcher m =p.matcher(str);
                String s = m.replaceAll("~~");
                if (!(s.equals(sb)) ) {
                    System.out.println(s);
//                write.writeRecord(s.split("~~"));
                    line[i] = s;
                }
                else{
                    continue;
                }
            }
                write.writeRecord(line, true);
            }
            //写入
            /*
            if (!(reader.getRawRecord().equals(sb.toString())) || reader.getCurrentRecord() == 0){
                //System.out.println(reader.getRawRecord());
                write.writeRecord(reader.getRawRecord().split(","));
            }
            */
        write.flush();
        //获取当前记录位置
        System.out.println();
        System.out.println("获取记录数："+reader.getCurrentRecord() + ".");
        reader.close();
        write.close();
    }
}
