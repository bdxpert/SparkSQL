package cs523.SparkSQL;

import java.io.Serializable;
import org.apache.spark.sql.SparkSession;


public class AppSparkHive implements Serializable{

	private static final long serialVersionUID = 5813011745972622831L;
	public static void main(String[] args) {

	SparkSession spark = SparkSession
            .builder()
            .appName("Java Spark SQL basic example")
            .master("local[*]")
            .config("hive.metastore.warehouse.dir", "/user/hive/warehouse")
			.config("hive.metastore.uris", "thrift://localhost:9083")
			.config("spark.sql.warehouse.dir", "/user/hive/warehouse")
//			.config("hive.exec.scratchdir", "/tmp/a-folder-that-the-current-user-has-permission-to-write-in")
//			.config("spark.yarn.security.credentials.hive.enabled", "true")
			.config("spark.sql.hive.metastore.jars", "maven")
//			.config("spark.sql.hive.metastore.version", "1.2.1")
			.config("spark.sql.catalogImplementation", "hive")
			.enableHiveSupport()
            .getOrCreate();
	
		spark.sql("CREATE TABLE IF NOT EXISTS hbase_hive_table_2 (city STRING, country STRING, lon STRING,lat STRING, year_val STRING, month_val STRING,day_val STRING, min_val STRING, max_val STRING, daytemp STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',';");
		
		spark.sql("LOAD DATA LOCAL INPATH '/home/cloudera/cs523/project/output/part-m-00000' OVERWRITE INTO TABLE hbase_hive_table_2");

		spark.sql("SELECT * FROM hbase_hive_table_2 LIMIT 5").show();

		//spark.sql("SELECT * FROM  LIMIT 10").show();


		spark.stop();
	}
}