package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

        public Properties prop;
        public int Response_Status_Code_200 = 200;
        int Response_Status_Code_404 = 404;
        public String Invalid_Token_Error_Message = "Invalid Token.";
        public String Invalid_UserName_Error_Message = "Invalid Credentials.";
        public String Invalid_Password_Error_Message = "Invalid Credentials.";
        public int Invalid_Method_Response_Status_Code = 405;
        public String Invalid_Method_Message = "Request method 'GET' not supported";
        public String Invalid_Post_Path ="/user/validate";



        public BaseClass(){
            prop = new Properties();
            try {
                prop.load(new FileInputStream(getProperties()));
            }catch (IOException ioeexp){
                ioeexp.printStackTrace();
            }
        }

        public String getProperties(){
            String resourceName = "config.properties";
            ClassLoader classLoader = getClass().getClassLoader();
            File file =new File(classLoader.getResource(resourceName).getFile());
            String absolutePath = file.getAbsolutePath();
            return absolutePath;
        }
    }
