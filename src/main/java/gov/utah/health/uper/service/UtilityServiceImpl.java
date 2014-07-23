package gov.utah.health.uper.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UtilityServiceImpl implements UtilityService {
	
	private static final Logger LOG = LoggerFactory.getLogger(UtilityServiceImpl.class);
	 
	
	/**
	 * Formats StringDate before saving into database
	 */
 	public Date formatDate(String strDate ) {
		 DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
	 	
		//CLEAN UP STRING IF BADLY FORMATTED
		strDate = strDate.replace("/", "-");
		
		//input mask sends unformatted string
		if (strDate.indexOf("-") == -1 && strDate.length()== 8 ){
			StringBuilder sb =  new StringBuilder(strDate);
		    sb.insert(2, "-");
		    sb.insert(5, "-");
		    strDate = sb.toString();
	 	}
	 
		Date returnDate = new Date();
		try {
			returnDate =formatter.parse( strDate )  ;
		}  catch (ParseException pe) {
			// do nothing -- just return formatted current date
			LOG.error("Parse Exception caught formatting date prior to database save. Will default to current date.", pe);
	 	}
		return returnDate;
	}
 	
 	@Override
	public String formatDateToString(Date date) {
 		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
  		String formattedDate = "";
	    if (null != date) {
		   formattedDate = formatter.format(date);
		}
	   return formattedDate;
	}

 	 

	@Override
	public String formatPhone(String telephoneNumber) {
		String formattedPhone = "";
		if (null != telephoneNumber) {
			formattedPhone = "(" + telephoneNumber.substring(0, 3) + ")-"
					+ telephoneNumber.substring(3, 6) + "-"
					+ telephoneNumber.substring(6, 10);
		}
		return formattedPhone;
	}

	
	
	@Override
	public String toProperCase(String stringToFormat) {
		
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(stringToFormat);
		while (st.hasMoreTokens()) {
			String text = st.nextToken();
			sb.append(text.substring(0,1).toUpperCase());
			sb.append(text.substring(1,text.length()).toLowerCase());
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	
	
	
	@Override
	public String formatDopl(String dopl) {
		StringBuilder sb = new StringBuilder(dopl.substring(0, dopl.length()-4));
		String suffix = dopl.substring(dopl.length()-4, dopl.length() );
		sb.append("-");
		sb.append(suffix);
	 	
		return sb.toString();
	}
	
	
	
	
	public int getAge(Date dateOfBirth) {
	    int age = 0;
	    Calendar born = Calendar.getInstance();
	    Calendar now = Calendar.getInstance();
	    if(dateOfBirth!= null) {
	        now.setTime(new Date());
	        born.setTime(dateOfBirth);  
	        if(born.after(now)) {
	            throw new IllegalArgumentException("Can't be born in the future");
	        }
	        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);             
	        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
	            age-=1;
	        }
	    }  
	    return age;
	}

	
}
