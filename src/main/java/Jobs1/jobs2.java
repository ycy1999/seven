package Jobs1;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class jobs2 {
    public static void main(String[] args) throws IOException {
        String srcCSV = "F:\\123456\\SPark课堂\\任务\\jobs1.csv";
        String targetFile = "F:\\123456\\SPark课堂\\test\\test8.csv";
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
int headcount = 0;
int linecount = 0;
        while (reader.readRecord()) {
            int columnCount = reader.getColumnCount();
            if(header[0].equals(reader.get(0))){
                headcount++;
                continue;
            }
linecount++;
            String[] line = new String[columnCount];
            for(int i = 0; i<columnCount; i++) {
                String str = reader.get(i);
                Pattern p = Pattern.compile("\\s+|\t+|\n|\r");
                Matcher m =p.matcher(str);
                String s = m.replaceAll("~~");

                line[i] = s;
            }
            System.out.println(Arrays.toString(line));
                write.writeRecord(line, true);
            }

        write.flush();
        System.out.println();
        System.out.println("获取记录数："+reader.getCurrentRecord() + ".");
        reader.close();
        write.close();

        System.out.println(headcount+"==="+linecount);
    }
}
