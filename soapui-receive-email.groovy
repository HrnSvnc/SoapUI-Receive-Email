import javax.mail.*;
import javax.mail.internet.*;
import java.util.regex.*;
               
// setup connection
Properties props = new Properties();
//Mail host-adress
def host = "";
//e-mail account
def username = "";
//password
def password = "";
 
def provider = "imap";
def someNumber = context.expand('${#TestCase1#someNumber}')
 
//connect to Imap Server
Session session = Session.getDefaultInstance props, null
Store store = session.getStore provider
store.connect host, username, password
 
// Open the folder
Folder inbox = store.getFolder 'INBOX'
if (!inbox) {
    println 'No INBOX'
    System.exit 1
}
 
inbox.open(Folder.READ_ONLY)
 
// Get the messages from the server
Message[] messages = inbox.getMessages()
def arraysize = messages.size();
log.info("arraySize: "+arraysize)
log.info(messages[0].getContent())
log.info(inbox.getMessageCount())
if(messages[0].getContent().contains(verfahrennummer)){
                String content = messages[0].getContent();
                Pattern somePattern = Pattern.compile("");
                Matcher  m = somePattern.matcher(content)
                log.info(m.find());
                log.info(m.group(1));
                }
                log.info("String was found")

// Close the connection
// but don't remove the messages from the server
inbox.close false
store.close()
