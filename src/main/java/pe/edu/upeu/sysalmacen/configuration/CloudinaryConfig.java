package pe.edu.upeu.sysalmacen.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name","ddzr7juty",
                "api_key", "387965542137594",
                "api_secret", "a4jyAIoTix4SjqlAeVqrHhSWw9w"
        ));
    }

}
