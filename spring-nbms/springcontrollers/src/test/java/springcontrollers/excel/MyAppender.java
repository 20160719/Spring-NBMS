package springcontrollers.excel;

import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;

public class MyAppender extends RollingFileAppender {

	@Override
	public boolean isAsSevereAsThreshold(Priority priority) {
		return getThreshold().equals(priority);
	}
	
}
