import java.io.IOException;
import java.util.*;
import java.text.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class twitterMapper extends Mapper<Object, Text, Text, Text> {
    private Text hour = new Text();
    private Text data = new Text();


    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    String[] fields = value.toString().split(";",-1);
	  if(fields.length == 4){
        	data.set(fields[0].toString());

	        long epochtime = Long.parseLong(fields[0]);
          Date date = new Date(epochtime);
          SimpleDateFormat sdf = new SimpleDateFormat("HH");
          sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		      String dateformatted = sdf.format(date);
		     // hour.set(dateformatted);
		if(dateformatted == "13"){
      			hour.set(fields[2].toString());
		context.write(data, hour);
    }else{
	hour.set("-1");
	context.write(data, hour);
}
  }
}}
