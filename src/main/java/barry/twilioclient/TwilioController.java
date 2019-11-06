package barry.twilioclient;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class TwilioController {
	
	@Value("${ACCOUNT_SID}")
	private String ACCOUNT_SID;

	@Value("${AUTH_TOKEN}")
	private String AUTH_TOKEN;
	
	@Value("${TWILIO_NUMBER}")
	private String TWILIO_NUMBER;
	
	@PostConstruct
	private void setup() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}
	
    @RequestMapping("/")
    public String helloWorld(){
        return "Hello world";
    }
    
    @RequestMapping(value = "/sendSMS",method = RequestMethod.POST)
    public String sendSMS( @RequestBody TwilioMessage msg ) {
    
    	System.out.println("Received:"+msg.toString());
    	
		Message message = Message.creator(
			    new PhoneNumber(msg.getCell()),
			    new PhoneNumber(TWILIO_NUMBER),
			    msg.getMsg())
			.create();

		return message.getStatus().toString();

    }

}