import java.io.IOException;
import java.util.Iterator;
import java.util.*;
import java.text.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class twitterReducer extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();

    public void reduce(Text key, Iterable<Text> values, Context context)

              throws IOException, InterruptedException {
       
	for(Text value:values){
           result.set(value.toString());
      }
       context.write(key,result);
    }

}

