import java.io.IOException;
import java.util.*;
import java.text.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class twitterMapper extends Mapper<Object, Text, Text, IntWritable> {
    private Text hour = new Text();
    private IntWritable counter = new IntWritable(1);
    private Text data = new Text();


    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    String[] fields = value.toString().split(";");
	try{
    if(fields.length >= 4){
        	//data.set(fields[0].toString());

	        long epochtime = Long.parseLong(fields[0]);
          Date date = new Date(epochtime);
          SimpleDateFormat sdf = new SimpleDateFormat("HH");
          sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		      String dateformatted = sdf.format(date);
		      hour.set(dateformatted);
		      int maxHour = Integer.parseInt(hour.toString());
		if(maxHour == 22) {
      String[] fields1 = fields[2].toString().split("#");
      for(int i = 1; i <= fields1.length-1; i++){
        String[] fields2 = fields1[i].toString().split(" ");
        if(fields2.length>1){
          data.set(fields2[0].toString());
          context.write(data, counter);}
      }

  }
}
}catch(NumberFormatException nfe){}
}
}
