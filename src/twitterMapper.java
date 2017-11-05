import java.io.IOException;
import java.util.*;
import java.text.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class twitterMapper extends Mapper<Object, Text, IntIntPair, IntWritable> {
    private IntWritable counter = new IntWritable(1);
    private Text data = new Text();
    private Hashtable<String, String> athleteInfo;
    private IntIntPair pair = new IntIntPair();


    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    String[] fields = value.toString().split(";");
	try{
    if(fields.length >= 4){

}
}catch(NumberFormatException nfe){}
}
}

@Override
protected void setup(Context context) throws IOException, InterruptedException {

  athleteInfo = new Hashtable<String, String>();

  // We know there is only one cache file, so we only retrieve that URI
  URI fileUri = context.getCacheFiles()[0];

  FileSystem fs = FileSystem.get(context.getConfiguration());
  FSDataInputStream in = fs.open(new Path(fileUri));

  BufferedReader br = new BufferedReader(new InputStreamReader(in));

  String line = null;
  try {
    // we discard the header row
    br.readLine();

    while ((line = br.readLine()) != null) {
      context.getCounter(CustomCounters.NUM_COMPANIES).increment(1);

      String[] fields = line.split(",");
      // Fields are: 0:id 1:name 2:Nationality 3:sex 4:dob 5:height 6: weight 7: sport 8:gold
      if (fields.length == 9)
        athleteInfo.put(fields[1], fields[7]);
    }
    br.close();
  } catch (IOException e1) {
  }

  super.setup(context);
}

}
